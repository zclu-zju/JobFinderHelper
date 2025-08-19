package com.zclu.interviewtips.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Jobs {
    private Integer id;
    private String url;
    private String title;
    // Date
    private LocalDate date;
}
