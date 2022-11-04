package fun.yizhierha.modules.system.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.modules.system.domain.SysDept;
import fun.yizhierha.modules.system.domain.SysDictDetail;
import fun.yizhierha.modules.system.domain.vo.CreateDictDetailVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDeptVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDictDetailVo;
import fun.yizhierha.modules.system.service.dto.DictDetailDto;
import fun.yizhierha.modules.system.service.dto.SummaryDeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictDetailMapper {

    @Mapping(source = "detailId",target = "id")
    DictDetailDto toDictDetailDto(SysDictDetail sysDictDetail);

    List<DictDetailDto> toDictDetailDtos(List<SysDictDetail> SysDictDetails);

    Page<DictDetailDto> toBeDictDetailDto(IPage<SysDictDetail> sysDictDetailIPage);

    default Page<DictDetailDto> toDictDetailDto(IPage<SysDictDetail> sysDictDetailIPage){
        Page<DictDetailDto> DictDetailDtoPage = toBeDictDetailDto(sysDictDetailIPage);
        DictDetailDtoPage.setRecords(toDictDetailDtos(sysDictDetailIPage.getRecords()));
        return DictDetailDtoPage;
    }

    SysDictDetail toSysDictDetail(CreateDictDetailVo createDictDetailVo);


    @Mapping(target = "detailId", source = "id")
    SysDictDetail toSysDictDetail(UpdateDictDetailVo updateDictDetailVo);

    List<SysDictDetail> toSysDictDetailList(List<UpdateDictDetailVo> updateDictDetailVo);

}
