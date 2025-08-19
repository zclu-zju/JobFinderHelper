package com.zclu.interviewtips.controller;

import com.zclu.interviewtips.entity.Jobs;
import com.zclu.interviewtips.result.Result;
import com.zclu.interviewtips.service.JobsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/jobs")
public class JobsController extends BaseController {
    private final JobsService jobsService;

    @Autowired
    public JobsController(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @GetMapping("/")
    public Result<List<Jobs>> getJobs(@RequestParam Integer pagenum, @RequestParam Integer pagesize) {
        return Result.success(jobsService.getJobs(pagenum, pagesize));
    }
}
