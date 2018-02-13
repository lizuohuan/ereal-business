package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Awards;
import com.magic.ereal.business.entity.DepartmentAwards;
import com.magic.ereal.business.entity.SpiritAwards;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IDepartmentAwardsMapper;
import com.magic.ereal.business.mapper.ISpiritAwardsMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 奖项 数据
 * Created by Eric Xie on 2017/7/3 0003.
 */
@Service
public class AwardsService {

    @Resource
    private IDepartmentAwardsMapper departmentAwardsMapper;
    @Resource
    private ISpiritAwardsMapper spiritAwardsMapper;



    /**
     * 新增 确认 三维绩效奖 | 结项奖
     * @param departmentAwards
     * @throws Exception
     */
    public void addAwards(DepartmentAwards departmentAwards) throws Exception{

        DepartmentAwards awards = departmentAwardsMapper.queryDepartmentAwards(departmentAwards.getDepartmentId(), departmentAwards.getMonth(),
                departmentAwards.getType());
        if(null != awards){
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"奖项已经设置");
        }
        departmentAwardsMapper.addDepartmentAwards(departmentAwards);
    }


    /**
     * 新增确认  月度K王 奖
     * @param spiritAwards
     */
    public void addAwards(SpiritAwards spiritAwards) throws Exception{
        List<Awards> awardses = spiritAwardsMapper.querySpiritAwardsByUser(spiritAwards.getUserId(),
                spiritAwards.getMonth(), spiritAwards.getType());
        if(null != awardses && awardses.size() > 0){
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"奖项已经设置");
        }
        spiritAwardsMapper.addSpiritAwards(spiritAwards);
    }



}
