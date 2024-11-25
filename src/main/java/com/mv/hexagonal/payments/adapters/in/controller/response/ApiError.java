package com.mv.hexagonal.payments.adapters.in.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    private String code;

    private String error;

    private String message;

    private Integer status;
}
