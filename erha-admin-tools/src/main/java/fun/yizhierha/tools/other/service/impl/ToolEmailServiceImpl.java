package fun.yizhierha.tools.other.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.tools.other.domain.ToolEmailConfig;
import fun.yizhierha.tools.other.domain.vo.SendEmailVo;
import fun.yizhierha.tools.other.service.ToolEmailConfigService;
import fun.yizhierha.tools.other.service.ToolEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolEmailServiceImpl implements ToolEmailService {

    @Autowired
    private ToolEmailConfigService toolEmailConfigService;

    @Override
    public void sendEmail(SendEmailVo sendEmailVo) {
        // 查询激活的配置
        ToolEmailConfig toolEmailConfig = toolEmailConfigService.getActive();
        if (toolEmailConfig == null)throw new BadRequestException("不存在激活的邮件配置");

        MailAccount account = getMainAccount(toolEmailConfig);
        // 发送
        try {
            Mail.create(account)
                    .setTos(sendEmailVo.getAddress())
                    .setTitle(sendEmailVo.getTitle())
                    .setContent(sendEmailVo.getContent(),true)
                    // 关闭session
                    .setUseGlobalSession(false)
                    .send();
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    private MailAccount getMainAccount(ToolEmailConfig cfg) {
        MailAccount account = new MailAccount();
        account.setHost(cfg.getHost());
        account.setPort(Integer.valueOf(cfg.getPort()));
        account.setAuth(true);
        account.setFrom(cfg.getUser()+"<"+cfg.getFromUser()+">");
        account.setUser(cfg.getFromUser().split("@")[0]);
        account.setPass(cfg.getPass());
        // ssl
        account.setSslEnable(true);
        account.setStarttlsEnable(true);
        return account;
    }
}
