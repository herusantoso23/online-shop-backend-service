package com.herusantoso.tokonezia.exception;

import com.herusantoso.tokonezia.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokoneziaException extends RuntimeException{

    private StatusCode code = StatusCode.ERROR;

    public TokoneziaException(){
        super();
    }

    public TokoneziaException(String message){
        super(message);
        log.info("ERROR: {}",message);
    }

    public TokoneziaException(StatusCode code, String message) {
        super(message);
        this.code = code;
        log.info("ERROR: {}",message);
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }

}
