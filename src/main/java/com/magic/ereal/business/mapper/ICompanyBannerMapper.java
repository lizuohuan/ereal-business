package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Banner;
import com.magic.ereal.business.entity.CompanyBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/8/21 0021.
 */
public interface ICompanyBannerMapper {


    List<Banner> queryBannerForAPP(@Param("companyId") Integer companyId,@Param("type") Integer type);


    Banner queryBannerByIdForAPP(@Param("id") Integer id);

    Integer addCompanyBanner(CompanyBanner companyBanner);


    Integer updateCompanyBanner(@Param("companyBanner") CompanyBanner companyBanner);


    List<CompanyBanner> queryCompanyBannerByItems(Map<String,Object> map);


    Integer countCompanyBannerByItems(Map<String,Object> map);


    Integer delCompanyBanner(@Param("id") Integer id);



    CompanyBanner queryBannerById(@Param("id") Integer id);


}
