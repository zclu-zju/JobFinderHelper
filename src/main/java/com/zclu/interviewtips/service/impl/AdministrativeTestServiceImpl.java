package com.zclu.interviewtips.service.impl;

import com.zclu.interviewtips.dto.AdministrativeTestDTO;
import com.zclu.interviewtips.entity.AdministrativeTest;
import com.zclu.interviewtips.mapper.AdministrativeTestMapper;
import com.zclu.interviewtips.service.AdministrativeTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdministrativeTestServiceImpl implements AdministrativeTestService {
    AdministrativeTestMapper administrativeTestMapper;

    public AdministrativeTestServiceImpl(AdministrativeTestMapper administrativeTestMapper) {
        this.administrativeTestMapper = administrativeTestMapper;
    }

    @Override
    public AdministrativeTest findLatest() {
        return administrativeTestMapper.findLatest();
    }

    @Override
    public List<AdministrativeTest> findAll() {
        return administrativeTestMapper.findAll();
    }

    @Override
    public void createAdministrativeTest(AdministrativeTestDTO administrativeTestDTO) {
        administrativeTestMapper.insert(AdministrativeTest.builder()
                .question(administrativeTestDTO.getQuestion())
                .build());
    }
}
