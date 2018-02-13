package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.SecondTarget;
import com.magic.ereal.business.entity.SecondTargetScore;
import com.magic.ereal.business.mapper.ISecondTargetMapper;
import com.magic.ereal.business.mapper.ISecondTargetScoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 *
 * 第二维 第三种计算方式  打分 业务
 *
 * Created by Eric Xie on 2017/7/3 0003.
 */
@Service
public class SecondTargetScoreService {


    @Resource
    private ISecondTargetScoreMapper secondTargetScoreMapper;
    @Resource
    private ISecondTargetMapper secondTargetMapper;

    /**
     *  打分
     * @param secondTargetScore
     */
    public void addSecondTargetScore(SecondTargetScore secondTargetScore){
        SecondTarget secondTarget = new SecondTarget();
        secondTarget.setScore(secondTargetScore.getScore());
        secondTarget.setScoreTime(new Date());
        secondTarget.setId(secondTargetScore.getSecondTargetId());
        secondTargetMapper.updateSecondTarget(secondTarget);
//        secondTargetScoreMapper.addSecondTargetScore(secondTargetScore);
    }


}
