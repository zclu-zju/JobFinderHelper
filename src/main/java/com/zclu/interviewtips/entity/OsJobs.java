package com.zclu.interviewtips.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OsJobs {
    private Integer id;
    private String question;
    private Integer vote;
    private String tag;
}
