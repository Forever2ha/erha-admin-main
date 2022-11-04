package fun.yizhierha.common.annotation.valid.validator;

import cn.hutool.cron.CronException;
import fun.yizhierha.common.annotation.valid.CronExpression;
import fun.yizhierha.common.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CronExpressionValidator implements ConstraintValidator<CronExpression,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || org.springframework.scheduling.support.CronExpression.isValidExpression(value);
    }
}
