package com.zclu.interviewtips.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Problems {
    private Integer id;
    private String question;
    private String source;
    private String tag;
}
