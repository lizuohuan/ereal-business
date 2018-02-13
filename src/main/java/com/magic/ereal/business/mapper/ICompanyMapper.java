package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Company;
import com.magic.ereal.business.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 分公司 持久层接口
 * Created by Eric Xie on 2017/4/20 0020.
 */
public interface ICompanyMapper {


    /**
     *  统计查询 生日 到点的用户 包括分公司下的 绩效专员
     * @return
     */
    List<Company> staticsBirthday(@Param("time") Date time);

    /**
     * 添加分公司
     * @param company
     * @return 影响行
     */
    Integer addCompany(@Param("company") Company company);

    /**
     *  删除分公司
     * @param id 分公司ID
     * @return
     */
    Integer delCompany(@Param("id") Integer id);

    /**
     * 根据分公司ID 查询分公司 包括 分公司下所有的部门、部门下所有的员工
     * @param id 分公司ID
     * @return
     */
    Company queryCompanyById(@Param("id") Integer id,@Param("accreditType") Integer accreditType,@Param("userId") Integer userId);

    /**
     * 更新分公司名称
     * @param company
     * @return
     */
    Integer updateCompany(@Param("company") Company company);

    /**
     * 公司列表
     * @return
     */
    List<Company> listForWeb();


    /**
     * 查询分公司 包括 分公司下所有的部门、部门下所有的员工
     * @return
     */
    List<Company> queryAllCompany(@Param("accreditType") Integer accreditType,@Param("userId") Integer userId);


    List<Company> queryBaseCompany();
}
