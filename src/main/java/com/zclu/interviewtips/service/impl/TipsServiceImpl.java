package com.zclu.interviewtips.service.impl;

import com.zclu.interviewtips.dto.TipsDTO;
import com.zclu.interviewtips.entity.Tips;
import com.zclu.interviewtips.mapper.TipsMapper;
import com.zclu.interviewtips.service.TipsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TipsServiceImpl implements TipsService {
    private final TipsMapper tipsMapper;

    public TipsServiceImpl(TipsMapper tipsMapper) {
        this.tipsMapper = tipsMapper;
    }

    @Override
    public Optional<Tips> findById(Integer id) {
        return Optional.ofNullable(tipsMapper.findById(id));
    }

    @Override
    public List<Tips> findAll() {
        return tipsMapper.findAll();
    }

    @Override
    public void createTips(TipsDTO tipsDTO) {
        log.info("SSSS");
        Tips tips = Tips.builder().text(tipsDTO.getText()).source(tipsDTO.getSource()).title(tipsDTO.getTitle()).build();
        tipsMapper.insert(tips);
    }

    @Override
    public void deleteTips(Integer id) {
        tipsMapper.deleteById(id);
    }

//    @Override
//    public void updateTips(TipsDTO tipsDTO) {
//        Tips tips = tipsMapper.findById(tipsDTO.getId());
//        if (tips != null) {
//            tipsMapper.update(Tips.builder().id(tipsDTO.getId()).text(tipsDTO.getText()).source(tipsDTO.getSource()).build());
//        }
//    }
}
