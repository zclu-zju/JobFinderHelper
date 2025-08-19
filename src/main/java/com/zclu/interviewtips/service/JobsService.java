package com.zclu.interviewtips.service;

import com.zclu.interviewtips.entity.Jobs;

import java.util.List;

public interface JobsService {
    List<Jobs> getJobs(Integer pageNum, Integer pageSize);
}
