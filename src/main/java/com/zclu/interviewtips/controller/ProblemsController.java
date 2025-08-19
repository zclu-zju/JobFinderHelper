package com.zclu.interviewtips.controller;

import com.zclu.interviewtips.dto.ProblemsDTO;
import com.zclu.interviewtips.entity.Problems;
import com.zclu.interviewtips.result.Result;
import com.zclu.interviewtips.service.ProblemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/problems")
public class ProblemsController extends BaseController {
    final ProblemsService problemsService;

    @Autowired
    public ProblemsController(ProblemsService problemsService) {
        this.problemsService = problemsService;
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public Result<List<Problems>> getAllProblems() {
        log.info("getAllProblems");
        return Result.success(problemsService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Problems> getProblemsById(@PathVariable Integer id) {
        log.info("getProblemsById");
        return Result.success(problemsService.findById(id).orElse(null));
    }

    @PostMapping("")
    public Result<Void> createProblems(@RequestBody ProblemsDTO problemsDTO) {
        log.info("createProblems");
        problemsService.createProblems(problemsDTO);
        return Result.success();
    }

    @GetMapping("/search")
    public Result<Map<String, Object>> searchByQuestion(
            @RequestParam(required = false) String kw,
            @RequestParam(required = false) String tag,
            @RequestParam(defaultValue = "10") Integer pagesize,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        log.info("searchByQuestion");
        List<Problems> problems = problemsService.searchByQuestion(kw, tag, pagesize, page);
        int total = problemsService.countProblems(kw, tag);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("problems", problems);
        return Result.success(result);
    }

    @PostMapping("/tag/{id}/{tag}")
    public Result<Integer> updateProblemTag(@PathVariable Integer id, @PathVariable String tag) {
        log.info("updateProblemTag");
        return Result.success(problemsService.updateProblemTag(id, tag));
    }

    @GetMapping("/random")
    public Result<Problems> getRandomProblem() {
        log.info("getRandomProblem");
        return Result.success(problemsService.getRandomProblem());
    }
}
