package fun.yizhierha.modules.security.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import fun.yizhierha.modules.system.domain.SysJob;
import fun.yizhierha.modules.system.domain.SysRole;
import fun.yizhierha.modules.system.service.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Setter
@Getter
@AllArgsConstructor
@ApiModel("自定义springSecurity UserDetails实现类")
public class UserDetailsDto implements UserDetails {

    @ApiModelProperty("用户信息")
    private final UserDto user;
    @ApiModelProperty("数据权限:能看到哪些部门的数据")
    private final Set<Long> dataScope;
    @ApiModelProperty("用户角色信息")
    private final Set<SysRole> roles;
    @ApiModelProperty("用户岗位信息")
    private final Set<SysJob> jobs;
    @ApiModelProperty("当前用户角色")
    private final SysRole nowRole;

    @ApiModelProperty("当前用户角色权限")
    public Set<String> getPermissions(){
        if (authorities != null){
            return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        }
        return null;
    }



    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;//权限
    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @JSONField(serialize = false)
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @JSONField(serialize = false)
    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }
    @JSONField(serialize = false)
    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }
    @JSONField(serialize = false)
    @Override
    @ApiModelProperty(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
