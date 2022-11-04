package fun.yizhierha.common.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.filter.Filter;

public class LogbackFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        // 禁用ExceptionHandlerExceptionResolver的log.warn(..)
        Level level = event.getLevel();
        if (event.getLoggerName().contains("org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver") && level == Level.WARN) {
            return FilterReply.DENY;
        } else {
            return FilterReply.ACCEPT;
        }
    }
}
