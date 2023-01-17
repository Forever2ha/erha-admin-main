package fun.yizhierha.operation.util;

import cn.hutool.core.io.IoUtil;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Vector;

/**
 * 执行shell命令
 *
 * @author: xaopohi
 * @date: 2023年1月6日 17:11:45
 */
@Slf4j
public class ExecuteShellUtil {

    private Vector<String> stdout;

    private Session session;


    public Session getSession() {
        return session;
    }

    public ExecuteShellUtil(final String ipAddress, final String username, final String password, int port) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, ipAddress, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(3000);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    public int executeServer(final String command) {
        int returnCode = 0;
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            InputStream in = channel.getInputStream();
            log.info("The remote command is: " + command);
            channel.setCommand(command);
//            channel.setPty(true);
            channel.connect();
            byte[] tmp = new byte[1024];
            int time = 0, size = 100, len = 30 * size;
            boolean isAvailable = false;
            long curr = System.currentTimeMillis();
            while (true) {//有时候返回结果要等待
                while (in.available() > 0) {
                    isAvailable = true;
                    time = 0;
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    log.info(new String(tmp, 0, i));
                }
                //if (isAvailable){break;}
                try {
                    Thread.sleep(size);
                } catch (Exception ee) {
                }
                time += size;
                if (time >= len) {//默认2秒钟跳出
                    break;
                }
            }


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return -1;
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
        return returnCode;
    }

    public int execute(final String command) {
        int returnCode = 0;
        ChannelShell channel = null;
        PrintWriter printWriter = null;
        BufferedReader input = null;
        stdout = new Vector<String>();
        try {
            channel = (ChannelShell) session.openChannel("shell");
            channel.connect();
            printWriter = new PrintWriter(channel.getOutputStream());
            printWriter.println(command);
            printWriter.println("exit");
            printWriter.flush();
            input = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            log.info("The remote command is: ");
            String line;
            while ((line = input.readLine()) != null) {
                stdout.add(line);
                log.info(line);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return -1;
        } finally {
            IoUtil.close(printWriter);
            IoUtil.close(input);
            if (channel != null) {
                channel.disconnect();
            }
        }
        return returnCode;
    }

    public void close() {
        if (session != null) {
            session.disconnect();
        }
    }

    public String executeForResult(String command) {
        execute(command);
        StringBuilder sb = new StringBuilder();
        for (String str : stdout) {
            sb.append(str);
        }
        return sb.toString();
    }

}
