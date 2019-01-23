package com.herusantoso.tokonezia.dto;

import lombok.Data;

@Data
public class ResultPageDTO extends ResultDTO {

    private String pages;
    private String elements;

    public ResultPageDTO() {
    }

    public ResultPageDTO(String pages, String elements) {
        this.pages = pages;
        this.elements = elements;
    }

}
