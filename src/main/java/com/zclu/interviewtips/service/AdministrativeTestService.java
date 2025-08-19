package com.zclu.interviewtips.service;

import com.zclu.interviewtips.dto.AdministrativeTestDTO;
import com.zclu.interviewtips.entity.AdministrativeTest;

import java.util.List;

public interface AdministrativeTestService {
    AdministrativeTest findLatest();

    List<AdministrativeTest> findAll();

    void createAdministrativeTest(AdministrativeTestDTO administrativeTestDTO);
}
