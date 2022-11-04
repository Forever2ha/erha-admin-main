package fun.yizhierha.modules.system.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.base.BaseMapper;
import fun.yizhierha.modules.system.domain.SysDept;
import fun.yizhierha.modules.system.domain.vo.CreateDeptVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDeptVo;
import fun.yizhierha.modules.system.service.dto.DeptDto;
import fun.yizhierha.modules.system.service.dto.SummaryDeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeptMapper extends BaseMapper<DeptDto,SysDept>{
    @Mapping(source = "deptId",target = "id")
    SummaryDeptDto toSummaryDeptDto(SysDept sysDept);

    List<SummaryDeptDto> toSummaryDeptDtos(List<SysDept> sysDepts);

    SysDept toSysDept(CreateDeptVo createDeptVo);

    @Mapping(source = "id",target = "deptId")
    SysDept toSysDept(UpdateDeptVo updateDeptVo);

    List<SysDept> toSysDepts(List<UpdateDeptVo> updateDeptVo);

    Page<SummaryDeptDto> toBeSummaryDeptDto(IPage<SysDept> sysDeptIPage);

    default Page<SummaryDeptDto> toSummaryDeptDto(IPage<SysDept> sysDeptIPage){
        Page<SummaryDeptDto> summaryDeptDtoPage = toBeSummaryDeptDto(sysDeptIPage);
        summaryDeptDtoPage.setRecords(toSummaryDeptDtos(sysDeptIPage.getRecords()));
        return  summaryDeptDtoPage;
    }


}
