package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Agreement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Eric Xie on 2017/5/24 0024.
 */
public interface IAgreementMapper {


    Integer updateAgreement(@Param("agreement") Agreement agreement);


    List<Agreement> queryAgreement();

}
