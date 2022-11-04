package fun.yizhierha.modules.system.service.impl;

import fun.yizhierha.common.utils.SecurityUtils;
import fun.yizhierha.common.utils.enums.DataScopeEnum;
import fun.yizhierha.modules.system.domain.SysRole;
import fun.yizhierha.modules.system.service.dto.UntreatedDataScopeDto;
import fun.yizhierha.modules.system.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.domain.SysRolesDepts;
import fun.yizhierha.modules.system.mapper.SysRolesDeptsMapper;
import fun.yizhierha.modules.system.service.SysRolesDeptsService;
@Service
public class SysRolesDeptsServiceImpl extends ServiceImpl<SysRolesDeptsMapper, SysRolesDepts> implements SysRolesDeptsService{


    @Override
    public Set<Long> getDataScopeByRoles(List<SysRole> roles, UserDto userDto) {
        Set<Long> dataScope = new HashSet<>();
        List<Long> roleIds = roles.stream().map(SysRole::getRoleId).collect(Collectors.toList());

        List<UntreatedDataScopeDto> untreatedDataScopeDto = null;
        boolean hasOnlyThisLevelRoleFlag = false;
        boolean hasCustomizeFlag = false;
        for (SysRole role : roles) {
            DataScopeEnum dse = DataScopeEnum.find(role.getDataScope());
            if ((!hasOnlyThisLevelRoleFlag) && dse == DataScopeEnum.Only_This_Level){
                // 处理Only_This_Level的角色
                dataScope.add(userDto.getDeptId());
                hasOnlyThisLevelRoleFlag = true;
            }else{
                if (untreatedDataScopeDto == null){
                    untreatedDataScopeDto = baseMapper.selectUntreatedDataScopeByRolesId(roleIds);
                }
                if (dse == DataScopeEnum.Only_Below_This_Level){
                    // 处理Only_Below_This_Level
                    dataScope.addAll(getSonDept(userDto.getDeptId(),untreatedDataScopeDto));
                }else if (dse == DataScopeEnum.This_And_Below_Level){
                    // 处理This_And_Below_Level
                    dataScope.add(userDto.getDeptId());
                    dataScope.addAll(getSonDept(userDto.getDeptId(),untreatedDataScopeDto));
                }else if ( (!hasCustomizeFlag) && dse == DataScopeEnum.CUSTOMIZE){
                    // 处理Customize
                    Set<Long> deptIds = untreatedDataScopeDto
                            .stream()
                            .filter((t) -> t.getDataScope() != null)
                            .map(UntreatedDataScopeDto::getDeptId)
                            .collect(Collectors.toSet());
                    dataScope.addAll(deptIds);
                    for (Long deptId : deptIds) {
                        dataScope.addAll(getSonDept(deptId,untreatedDataScopeDto));
                    }

                    hasCustomizeFlag = true;
                }else if (dse == DataScopeEnum.All){
                    // 走到这里说明就是ALL了，直接退出循环
                    dataScope = new HashSet<Long>(){{add(0L);}};
                    break;
                }else {
                    //意外情况
                    dataScope.add(-1L);
                }
            }
        }


        return dataScope;
    }



    private Set<Long> getSonDept(Long deptId, List<UntreatedDataScopeDto> allDepts) {
        HashSet<Long> res = new HashSet<>();

        Set<Long> collect = allDepts
                .stream()
                .filter((t) -> deptId.equals(t.getPid()))
                .map(UntreatedDataScopeDto::getDeptId)
                .collect(Collectors.toSet());

        if (!collect.isEmpty()){
            res.addAll(collect);
            for (Long deptId1 : collect) {
                res.addAll(getSonDept(deptId1,allDepts));
            }
        }

        return res;
    }
}
