package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Accredit;
import com.magic.ereal.business.entity.User;
import com.magic.ereal.business.mapper.IAccreditMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 授权 业务层
 * Created by Eric Xie on 2017/5/23 0023.
 */
@Service
public class AccreditService {

    @Resource
    private IAccreditMapper accreditMapper;


    /**
     * 获取 授权人 列表
     * @param toUserId 当前人
     * @param type 类型
     * @return
     */
    public User queryAccreditByToUser(Integer toUserId,Integer type){
        return accreditMapper.queryAccreditByToUser(toUserId,type);
    }

    /**
     * 新增 或者 更新 授权目录
     * @param accredits
     * @param fromUserId
     * @param type
     * @throws Exception
     */
    @Transactional
    public void addAccredit(List<Accredit> accredits,Integer fromUserId,Integer type) throws Exception{

        if(type != null){
            accreditMapper.delAccredit(fromUserId,type);
        }
        accreditMapper.batchAddAccredit(accredits);
    }


    public List<Accredit> queryAccredit(Integer fromUserId,Integer type){
        return accreditMapper.queryAccredit(fromUserId,type);
    }


    public void delAccredit(Integer fromUserId,Integer type){
        accreditMapper.delAccredit(fromUserId,type);
    }


    public int countAccreditByToUser(List<Integer> toUserIds,  Integer type, List<Integer> originalToUserIds){
        return accreditMapper.countAccreditByToUser(toUserIds,type,originalToUserIds);
    }

}
