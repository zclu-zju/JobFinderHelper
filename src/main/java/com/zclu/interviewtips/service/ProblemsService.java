package com.zclu.interviewtips.service;

import com.zclu.interviewtips.dto.ProblemsDTO;
import com.zclu.interviewtips.entity.Problems;

import java.util.List;
import java.util.Optional;

public interface ProblemsService {
    Optional<Problems> findById(Integer id);

    List<Problems> findAll();

    void createProblems(ProblemsDTO problemsDTO);

    List<Problems> searchByQuestion(String kw, String tag, Integer pagesize, Integer page);

    int countProblems(String kw, String tag);

    int updateProblemTag(Integer id, String tag);

    Problems getRandomProblem();
}
