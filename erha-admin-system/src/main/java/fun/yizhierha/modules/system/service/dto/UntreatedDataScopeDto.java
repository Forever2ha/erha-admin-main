package fun.yizhierha.modules.system.service.dto;

import lombok.Data;

@Data
public class UntreatedDataScopeDto {
    private Long deptId; // 部门id
    private Long pid; // 此部门的父id
    private String dataScope; // 说明拥有的部门权限的位置: 详情看DataScopeEnum
}
