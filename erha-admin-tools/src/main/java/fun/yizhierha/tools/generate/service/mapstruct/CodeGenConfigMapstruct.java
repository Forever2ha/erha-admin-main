package fun.yizhierha.tools.generate.service.mapstruct;

import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import fun.yizhierha.tools.generate.domain.vo.UpdateOrSaveCodeGenConfigVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodeGenConfigMapstruct {

    CodeGenConfig toCodeGenConfig(UpdateOrSaveCodeGenConfigVo updateOrSaveCodeGenConfigVo);
}
