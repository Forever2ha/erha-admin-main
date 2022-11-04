package fun.yizhierha.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException{

    private Integer status ;
    private Object data;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Integer status,String message) {
        super(message);
        this.status = status;
    }

    public BadRequestException(Integer status,String message,Object data) {
        super(message);
        this.status = status;
        this.data = data;
    }
}
