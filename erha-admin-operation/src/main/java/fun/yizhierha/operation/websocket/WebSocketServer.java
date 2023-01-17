package fun.yizhierha.operation.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 * @author ZhangHouYing
 * @date 2019-08-10 15:46
 */
@ServerEndpoint("/webSocket/{identification}")
@Slf4j
@Component
public class WebSocketServer {

	/**
	 * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	 */
	private static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;

	/**
	 * 接收标识符
	 */
	private String identification="";
	/**
	 * 连接建立成功调用的方法
	 * */
	@OnOpen
	public void onOpen(Session session,@PathParam("identification") String identification) {
		this.session = session;
		//如果存在就先删除一个，防止重复推送消息
		for (WebSocketServer webSocket:webSocketSet) {
			if (webSocket.identification.equals(identification)) {
				webSocketSet.remove(webSocket);
			}
		}
		webSocketSet.add(this);
		this.identification=identification;
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
        log.info("连接关闭啦 "+this.identification);
		webSocketSet.remove(this);
	}

	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息*/
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到来"+identification+"的信息:"+message);
		//群发消息
		for (WebSocketServer item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}
	/**
	 * 实现服务器主动推送
	 */
	private void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}


	/**
	 * 群发自定义消息
	 * */
	public static void sendInfo(SocketMsg socketMsg,@PathParam("identification") String identification) throws IOException {
		String message = JSONObject.toJSONString(socketMsg);
		log.info("推送消息到"+identification+"，推送内容:"+message);
		for (WebSocketServer item : webSocketSet) {
			try {
				//这里可以设定只推送给这个identification的，为null则全部推送
				if(identification==null) {
					item.sendMessage(message);
				}else if(item.identification.equals(identification)){
					item.sendMessage(message);
				}
			} catch (IOException ignored) { }
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WebSocketServer that = (WebSocketServer) o;
		return Objects.equals(session, that.session) &&
				Objects.equals(identification, that.identification);
	}

	@Override
	public int hashCode() {
		return Objects.hash(session, identification);
	}
}
