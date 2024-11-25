package com.mv.hexagonal.payments.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull(message = "Balance is required")
    @PositiveOrZero(message = "Balance must be greater than or equal to zero")
    private BigDecimal balance;
}
