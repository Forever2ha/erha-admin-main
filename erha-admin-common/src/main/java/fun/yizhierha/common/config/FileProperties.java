package fun.yizhierha.common.config;

import fun.yizhierha.common.constant.EhAdminConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zheng Jie
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /** 文件大小限制 */
    private Long maxSize;

    /** 头像大小限制 */
    private Long avatarMaxSize;

    private EhPath mac;

    private EhPath linux;

    private EhPath windows;

    public EhPath getPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith(EhAdminConstant.WIN)) {
            return windows;
        } else if(os.toLowerCase().startsWith(EhAdminConstant.MAC)){
            return mac;
        }
        return linux;
    }

    @Data
    public static class EhPath{

        private String path;

        private String avatar;
    }
}
