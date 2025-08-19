package com.zclu.interviewtips.controller;

import com.zclu.interviewtips.dto.TipsDTO;
import com.zclu.interviewtips.entity.Tips;
import com.zclu.interviewtips.result.Result;
import com.zclu.interviewtips.service.TipsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tips")
public class TipsReceiverController extends BaseController {
    private final TipsService tipsService;

    @Autowired
    public TipsReceiverController(TipsService tipsService) {
        this.tipsService = tipsService;
    }

    @GetMapping("/all")
    public Result<List<Tips>> getAllTips() {
        List<Tips> tips = tipsService.findAll();
        return Result.success(tips);
    }

    @GetMapping("/{id}")
    public Result<Tips> getTipsById(@PathVariable Integer id) {
        return tipsService.findById(id)
                .map(Result::success)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tip not found"));
    }


    @PostMapping("")
    public Result<Void> createTips(@RequestBody TipsDTO tipsDTO) {
        tipsService.createTips(tipsDTO);
        return Result.success();
    }

//    @PutMapping("/{id}")
//    public Result<Void> updateTips(@PathVariable Integer id, @RequestBody TipsDTO tipsDTO) {
//        tipsDTO.setId(id);
//        tipsService.updateTips(tipsDTO);
//        return Result.success();
//    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteTips(@PathVariable Integer id) {
        tipsService.deleteTips(id);
        return Result.success();
    }
}
