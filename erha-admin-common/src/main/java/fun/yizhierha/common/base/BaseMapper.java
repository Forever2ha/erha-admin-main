package fun.yizhierha.common.base;

import java.util.List;

public interface BaseMapper<D,S> {
    // sys -> dto
    S toSysEntity(D dto);
    // dto -> sys
    D toDto(S sysEntity);
    // list sys -> dto
    List<S> toSysEntityList(List<D> dList);
    // list dto -> sys
    List<D> toDtoList(List<S> sysEntityList);
}
