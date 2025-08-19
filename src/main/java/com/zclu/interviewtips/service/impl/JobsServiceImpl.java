package com.zclu.interviewtips.service.impl;

import com.zclu.interviewtips.entity.Jobs;
import com.zclu.interviewtips.mapper.JobsMapper;
import com.zclu.interviewtips.service.JobsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JobsServiceImpl implements JobsService {
    private final JobsMapper jobsMapper;

    public JobsServiceImpl(JobsMapper jobsMapper) {
        this.jobsMapper = jobsMapper;
    }

    @Override
    public List<Jobs> getJobs(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return jobsMapper.getJobsByPage(offset, pageSize);
    }
}
