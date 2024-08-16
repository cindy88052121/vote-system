package com.wy.project.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {

    private String code;
    private String message;
    private Object data;

}
