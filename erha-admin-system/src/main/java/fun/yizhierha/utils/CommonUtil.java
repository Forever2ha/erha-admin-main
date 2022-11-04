package fun.yizhierha.utils;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.system.domain.vo.UpdateVo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    /**
     * 获取验证bean错误信息
     * @param bindingResult
     * @return
     */
    public static List<BaseErrDto> getBaseErrDtoByBindingRes(BindingResult bindingResult){
        ArrayList<BaseErrDto> res = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(
                (err) -> {
                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setErrorField(err.getField());
                    baseErrDto.setErrorMsg(err.getDefaultMessage());
                    baseErrDto.setErrorVal(err.getRejectedValue());
                    res.add(baseErrDto);
                }
        );
    return res;
    }

    /**
     * 获取验证bean错误信息
     * @param updateVoList
     * @param bindingResult
     * @param <T>
     * @return
     */
    public static <T extends UpdateVo> List<BaseErrDto> getBaseErrDtoByBindingRes(ValidList<T> updateVoList, BindingResult bindingResult) {
        List<BaseErrDto> res = new ArrayList<>();
        Pattern pattern = Pattern.compile("list\\[\\d+");
        ValidList<T> target = (ValidList<T>) bindingResult.getTarget();

        Set<Long> toRemoveIds = new HashSet<>();
        for (FieldError err : bindingResult.getFieldErrors()) {
            // list[1].phone
            String field = err.getField();
            Matcher matcher = pattern.matcher(field);

            if (matcher.find()){
                int index = Integer.parseInt(matcher.group(0).split("\\[")[1] + "");
                T updateVo = target.get(index);
                String errField = field.split("\\.")[1];
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(updateVo.getId());
                baseErrDto.setErrorField(errField);
                baseErrDto.setErrorMsg(err.getDefaultMessage());
                baseErrDto.setErrorVal(err.getRejectedValue());
                res.add(baseErrDto);

                toRemoveIds.add(updateVo.getId());

            }
        }

        // 删除有错误的vo,不用去修改了
        for (Long id : toRemoveIds) {
            updateVoList.removeIf((t) -> t.getId().equals(id));
        }


        return res;
    }

    /**
     * 获取异常堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
