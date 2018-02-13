package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.CompanyBanner;
import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.mapper.ICompanyBannerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 公司BANNER
 * Created by Eric Xie on 2017/8/21 0021.
 */
@Service
public class CompanyBannerService {

    @Resource
    private ICompanyBannerMapper companyBannerMapper;


    public void delCompanyBanner(Integer bannerId){
        companyBannerMapper.delCompanyBanner(bannerId);
    }

    public PageList<CompanyBanner> queryCompanyBannerByItems(PageArgs pageArgs, Map<String,Object> baseParams){
        List<CompanyBanner> dataList = new ArrayList<>();
        Integer count = companyBannerMapper.countCompanyBannerByItems(baseParams);
        if (count > 0){
            baseParams.put("limit",pageArgs.getPage());
            baseParams.put("limitSize",pageArgs.getPageSize());
            dataList = companyBannerMapper.queryCompanyBannerByItems(baseParams);
        }
        return new PageList<>(dataList,count);
    }

    public void updateCompanyBanner(CompanyBanner companyBanner){
        companyBannerMapper.updateCompanyBanner(companyBanner);
    }

    public void addCompanyBanner(CompanyBanner companyBanner){
        companyBannerMapper.addCompanyBanner(companyBanner);
    }

    public CompanyBanner queryBannerById(Integer bannerId){
        return companyBannerMapper.queryBannerById(bannerId);
    }


}
