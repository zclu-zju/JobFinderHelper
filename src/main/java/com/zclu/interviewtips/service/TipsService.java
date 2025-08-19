package com.zclu.interviewtips.service;

import com.zclu.interviewtips.dto.TipsDTO;
import com.zclu.interviewtips.entity.Tips;

import java.util.List;
import java.util.Optional;

public interface TipsService {
    Optional<Tips> findById(Integer id);

    List<Tips> findAll();

    //新建
    void createTips(TipsDTO tipsDTO);

    void deleteTips(Integer id);

//    void updateTips(TipsDTO tipsDTO);
}
