package fun.yizhierha.modules.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "user-cache")
@Configuration
public class UserCacheProperties {
    private int minEvictableSize;
    private long minEvictableInterval;
    private long minIdleTime;
}
