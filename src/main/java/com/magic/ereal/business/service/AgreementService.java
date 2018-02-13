package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Agreement;
import com.magic.ereal.business.mapper.IAgreementMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Eric Xie on 2017/5/24 0024.
 */
@Service
public class AgreementService {

    @Resource
    private IAgreementMapper agreementMapper;


    public void updateAgreement(Agreement agreement){
        agreementMapper.updateAgreement(agreement);
    }



    public List<Agreement> queryAgreement(){
        return agreementMapper.queryAgreement();
    }

}
