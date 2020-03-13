package com.parser.model.dto;

import com.parser.model.common.Sex;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class CommonFilter {
    private Long id;
    private BigDecimal min;
    private BigDecimal max;
    private Sex sex;
    private List<String> brand;
}