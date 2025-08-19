package com.zclu.interviewtips.controller;

import com.zclu.interviewtips.entity.OsJobs;
import com.zclu.interviewtips.result.Result;
import com.zclu.interviewtips.service.OsJobsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/osjobs")
public class OsJobsController extends BaseController {
    private final OsJobsService osJobsService;

    @Autowired
    public OsJobsController(OsJobsService osJobsService) {
        this.osJobsService = osJobsService;
    }

    @GetMapping("/all")
    public Result<List<OsJobs>> getAllOsJobs() {
        List<OsJobs> osJobs = osJobsService.getAllOsJobs();
        return Result.success(osJobs);
    }

    @GetMapping("/{id}")
    public Result<OsJobs> getOsJobsById(@PathVariable Integer id) {
        OsJobs osJobs = osJobsService.getOsJobsById(id).orElse(null);
        return Result.success(osJobs);
    }

    @GetMapping("/")
    public Result<List<OsJobs>> getOsJobsByTag(@RequestParam String tag) {
        List<OsJobs> osJobs = osJobsService.getOsJobsByTag(tag).orElse(null);
        return Result.success(osJobs);
    }

    @GetMapping("/random")
    public Result<OsJobs> getRandomOsJobs() {
        OsJobs osJobs = osJobsService.getRandomOsJobs();
        return Result.success(osJobs);
    }
}
