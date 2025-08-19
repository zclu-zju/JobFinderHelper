package com.zclu.interviewtips.service;

import com.zclu.interviewtips.entity.OsJobs;

import java.util.*;

public interface OsJobsService {
    List<OsJobs> getAllOsJobs();

    Optional<OsJobs> getOsJobsById(Integer id);

    Optional<List<OsJobs>> getOsJobsByTag(String tag);

    OsJobs getRandomOsJobs();
}
