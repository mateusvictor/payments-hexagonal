package com.mv.hexagonal.payments.application.core.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private BigDecimal balance;
}
