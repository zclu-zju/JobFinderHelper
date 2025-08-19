package com.zclu.interviewtips.service.impl;

import com.zclu.interviewtips.dto.ProblemsDTO;
import com.zclu.interviewtips.entity.Problems;
import com.zclu.interviewtips.mapper.ProblemsMapper;
import com.zclu.interviewtips.service.ProblemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProblemsServiceImpl implements ProblemsService {
    private final ProblemsMapper problemsMapper;

    public ProblemsServiceImpl(ProblemsMapper problemsMapper) {
        this.problemsMapper = problemsMapper;
    }

    @Override
    public Optional<Problems> findById(Integer id) {
        return Optional.ofNullable(problemsMapper.findById(id));
    }

    @Override
    public List<Problems> findAll() {
        return problemsMapper.findAll();
    }

    @Override
    public void createProblems(ProblemsDTO problemsDTO) {
        problemsMapper.insert(Problems.builder().question(problemsDTO.getQuestion()).source(problemsDTO.getSource()).build());
    }

    @Override
    public List<Problems> searchByQuestion(String kw, String tag, Integer pagesize, Integer page) {
        int offset = (page - 1) * pagesize;
        return problemsMapper.searchByQuestion(kw, tag, offset, pagesize);
    }

    @Override
    public int countProblems(String kw, String tag) {
        return problemsMapper.countProblems(kw, tag);
    }

    @Override
    public int updateProblemTag(Integer id, String tag) {
        return problemsMapper.updateTag(id, tag);
    }

    @Override
    public Problems getRandomProblem() {
        return problemsMapper.getRandomProblem();
    }
}
