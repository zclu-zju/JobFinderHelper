package com.zclu.interviewtips.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tips {
    private Integer id;
    private String text;
    private String source;
    private String title;
}
