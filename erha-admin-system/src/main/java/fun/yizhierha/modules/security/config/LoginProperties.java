package fun.yizhierha.modules.security.config;

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import fun.yizhierha.common.exception.BadConfigurationException;
import fun.yizhierha.common.utils.StringUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Objects;

@Data
@ConfigurationProperties(prefix = "login")
@Component
public class LoginProperties {

    /**
     * 账号单用户 登录
     */
    private boolean singleLogin = false;

    private LoginCodeProperties loginCodeProperties;

    /**
     * 用户登录信息缓存
     */
    private boolean cacheEnable;

    public boolean isSingleLogin() {
        return singleLogin;
    }

    public boolean isCacheEnable() {
        return cacheEnable;
    }

    /**
     * 获取验证码生产类
     *
     * @return /
     */
    public Captcha getCaptcha() {
        if (Objects.isNull(loginCodeProperties)) {
            loginCodeProperties = new LoginCodeProperties();
            if (Objects.isNull(loginCodeProperties.getCodeType())) {
                loginCodeProperties.setCodeType(LoginCodeEnum.ARITHMETIC);
            }
        }
        return switchCaptcha(loginCodeProperties);
    }

    /**
     * 依据配置信息生产验证码
     *
     * @param loginCodeProperties 验证码配置信息
     * @return /
     */
    private Captcha switchCaptcha(LoginCodeProperties loginCodeProperties) {
        Captcha captcha;
        synchronized (this) {
            switch (loginCodeProperties.getCodeType()) {
                case ARITHMETIC:
                    // 算术类型 https://gitee.com/whvse/EasyCaptcha
                    captcha = new FixedArithmeticCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    // 几位数运算，默认是两位
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case CHINESE:
                    captcha = new ChineseCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case CHINESE_GIF:
                    captcha = new ChineseGifCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case GIF:
                    captcha = new GifCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                case SPEC:
                    captcha = new SpecCaptcha(loginCodeProperties.getWidth(), loginCodeProperties.getHeight());
                    captcha.setLen(loginCodeProperties.getLength());
                    break;
                default:
                    throw new BadConfigurationException("验证码配置信息错误！正确配置查看 LoginCodeEnum ");
            }
        }
        if(StringUtils.isNotBlank(loginCodeProperties.getFontName())){
            captcha.setFont(new Font(loginCodeProperties.getFontName(), Font.PLAIN, loginCodeProperties.getFontSize()));
        }
        return captcha;
    }

    static class FixedArithmeticCaptcha extends ArithmeticCaptcha {
        public FixedArithmeticCaptcha(int width, int height) {
            super(width, height);
        }

        @Override
        protected char[] alphas() {
            // 生成随机数字和运算符
            int n1 = num(1, 10), n2 = num(1, 10);
            int opt = num(3);

            // 计算结果
            int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
            // 转换为字符运算符
            char optChar = "+-x".charAt(opt);

            this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
            this.chars = String.valueOf(res);

            return chars.toCharArray();
        }
    }
}
