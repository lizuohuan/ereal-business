package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Accredit;
import com.magic.ereal.business.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 授权 持久层
 * Created by Eric Xie on 2017/5/23 0023.
 */
public interface IAccreditMapper {


    /**
     *  批量新增授权
     * @param accredits
     * @return
     */
    Integer batchAddAccredit(@Param("accredits") List<Accredit> accredits);


    /**
     * 删除授权信息
     * @param fromUserId 授权者ID
     * @param type 授权类型
     * @return
     */
    Integer delAccredit(@Param("fromUserId") Integer fromUserId,@Param("type") Integer type);

    /**
     *  查询被授权用户信息
     * @param fromUserId 授权者
     * @param type 授权类型
     * @return
     */
    List<Accredit> queryAccredit(@Param("fromUserId") Integer fromUserId,@Param("type") Integer type);


    /**
     * 查询 用户 被哪些用户授权
     * @param toUserId 当前用户
     * @param type 类型
     * @return
     */
    User queryAccreditByToUser(@Param("toUserId") Integer toUserId,@Param("type") Integer type);


    /**
     * 判断 新增的用户中是否有被授权
     * @param toUserIds
     * @param originalToUserIds
     * @return
     */
    Integer countAccreditByToUser(@Param("toUserIds") List<Integer> toUserIds,@Param("type") Integer type,
                                  @Param("originalToUserIds") List<Integer> originalToUserIds);





}
