package com.herusantoso.tokonezia.dto;

import lombok.Data;

@Data
public class ResultDTO {

    private String message;
    private Object result;

    public ResultDTO() {
    }

    public ResultDTO(String message, Object result) {
        this.message = message;
        this.result = result;
    }

}
