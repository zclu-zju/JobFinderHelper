package com.zclu.interviewtips.service.impl;

import com.zclu.interviewtips.entity.OsJobs;
import com.zclu.interviewtips.mapper.OsJobsMapper;
import com.zclu.interviewtips.service.OsJobsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OsJobsServiceImpl implements OsJobsService {
    private final OsJobsMapper osJobsMapper;

    public OsJobsServiceImpl(OsJobsMapper osJobsMapper) {
        this.osJobsMapper = osJobsMapper;
    }

    @Override
    public List<OsJobs> getAllOsJobs() {
        return osJobsMapper.getAllOsJobs();
    }

    @Override
    public Optional<OsJobs> getOsJobsById(Integer id) {
        return Optional.ofNullable(osJobsMapper.getOsJobsById(id));
    }

    @Override
    public Optional<List<OsJobs>> getOsJobsByTag(String tag) {
        return Optional.ofNullable(osJobsMapper.getOsJobsByTag(tag));
    }

    @Override
    public OsJobs getRandomOsJobs() {
        return osJobsMapper.getRandomOsJobs();
    }
}
