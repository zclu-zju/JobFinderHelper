package com.zclu.interviewtips.controller;


import com.zclu.interviewtips.dto.AdministrativeTestDTO;
import com.zclu.interviewtips.entity.AdministrativeTest;
import com.zclu.interviewtips.result.Result;
import com.zclu.interviewtips.service.AdministrativeTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/adtest")
public class AdministrativeTestController extends BaseController {
    final AdministrativeTestService administrativeTestService;

    @Autowired
    public AdministrativeTestController(AdministrativeTestService administrativeTestService) {
        this.administrativeTestService = administrativeTestService;
    }

    @GetMapping("/all")
    public Result<List<AdministrativeTest>> getAllAdministrativeTest() {
        return Result.success(administrativeTestService.findAll());
    }

    @GetMapping("/latest")
    public Result<AdministrativeTest> getLatestAdministrativeTest() {
        return Result.success(administrativeTestService.findLatest());
    }

    @PostMapping("")
    public Result<Void> createAdministrativeTest(@RequestBody AdministrativeTestDTO administrativeTestDTO) {
        administrativeTestService.createAdministrativeTest(administrativeTestDTO);
        return Result.success();
    }
}
