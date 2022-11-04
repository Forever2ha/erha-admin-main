package fun.yizhierha.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("rsa")
public class RsaEncryptConfig {

    // rsa私钥
    private String privateKey;
}
