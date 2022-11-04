package fun.yizhierha.common.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DataScopeEnum {


    /* 全部的数据权限 */
    All("全部", "全部的数据权限"),

    /* 仅自己部门的数据权限 */
    Only_This_Level("仅本级", "仅自己部门的数据权限,不包括本部门以下的部门数据"),

    /* 仅自己部门以下的数据权限 */
    Only_Below_This_Level("仅本级以下","仅自己部门以下的数据权限"),

    /* 本级与本级以下的数据权限 */
    This_And_Below_Level("本级与本级以下","本部门与本部门以下的数据选取"),

    /* 自定义的数据权限 */
    CUSTOMIZE("自定义", "自定义的数据权限");

    private final String value;
    private final String description;

    public static DataScopeEnum find(String val) {
        for (DataScopeEnum dataScopeEnum : DataScopeEnum.values()) {
            if (dataScopeEnum.getValue().equals(val)) {
                return dataScopeEnum;
            }
        }
        return null;
    }
}
