package fun.yizhierha.tools.other.service.mapstruct;

import fun.yizhierha.tools.other.domain.vo.CreateToolQiniuConfigVo;
import fun.yizhierha.tools.other.domain.vo.UpdateToolQiniuConfigVo;
import org.mapstruct.Mapper;
import fun.yizhierha.tools.other.domain.ToolQiniuConfig;


/** generated by EH-Admin
* @author erha
* @date Sun Jan 08 13:57:58 CST 2023
**/
@Mapper(componentModel = "spring")
public interface ToolQiniuConfigMapstruct{

    ToolQiniuConfig toToolQiniuConfig(UpdateToolQiniuConfigVo updateToolQiniuConfigVo);

    ToolQiniuConfig toToolQiniuConfig(CreateToolQiniuConfigVo createToolQiniuConfigVo);

}