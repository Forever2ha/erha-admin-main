package fun.yizhierha.modules.security.service.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserInfoDto {

    @ApiModelProperty
    private UserDetailsDto userDetailsDto;
    @ApiModelProperty
    private String token;
}
