package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ExportProjectControl;
import com.magic.ereal.business.entity.User;
import com.magic.ereal.business.entity.UserExcelOfWorkDiary;
import com.magic.ereal.business.entity.UserH;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface IUserMapper {


	List<User> queryUserByDepartment2(@Param("departmentId") Integer departmentId);

	List<ExportProjectControl> queryExportProjectControl(@Param("projectId") Integer projectId);

	/**
	 * 批量查询用户
	 * @param accounts
	 * @return
     */
	List<User> batchQueryUser(@Param("accounts") List<String> accounts,@Param("emails") List<String> emails);

	/**
	 * 批量新增用户
	 * @param users
	 * @return
     */
	Integer batchAddUser(@Param("users") List<User> users);

	/**
	 * 判断当前人 是否担任过 B导师 实用 外部项目
	 * @param userId
	 * @return
     */
	Integer isCTeacherOfProject(@Param("userId") Integer userId);


	/**
	 * 判断当前人 是否担任过C导师  实用 外部项目
	 * @param userId
	 * @return
     */
	Integer isCTeacher(@Param("userId") Integer userId,@Param("isExternal") Integer isExternal);



	List<User> queryUserByItemsOfV2(@Param("departmentId") Integer departmentId,@Param("companyId") Integer companyId,@Param("roleId") Integer roleId);


	/*******************************************************************/
	/*******************************************************************/
	/******************            以上接口 V2 版本使用         **********/
	/*******************************************************************/
	/*******************************************************************/

	List<User> queryAllUser();

	/**
	 * 导出 用户 工作时间报表 统计数据
	 * @param companyId
	 * @param month
     * @return
     */
	List<UserExcelOfWorkDiary> countUserWorkDiary(@Param("companyId") Integer companyId,@Param("month") Date month,
												  @Param("departmentId") Integer departmentId);


	List<User> queryUserByDepartmentId(@Param("departmentId") Integer departmentId);


	/**
	 * 查询分公司下的 绩效专员
	 * @param departmentId
	 * @return
     */
	List<User> queryPerformanceEmployee(@Param("departmentId") Integer departmentId);

	/**
	 * 置空 用户的 设备类型 和设备token
	 * @param userId
	 * @return
     */
	Integer setDeviceNull(@Param("userId") Integer userId);

	/**
	 *  查询用户 的 设备类型 和 设备token 用于推送
	 * @param ids 用户ID 集合
	 * @return
     */
	List<User> queryUserDeviceTypeAndToken(@Param("ids") List<Integer> ids);

	/**
	 *  查询 分公司下所有的 项目处执委 和 绩效专员
	 *  推送专用
	 * @param departmentId
	 * @return
     */
	List<User> queryUserDevice(@Param("departmentId") Integer departmentId);


	User queryUserByPhone(@Param("phone") String phone);

	/**
	 * 统计 生日提醒
	 * @return
     */
	List<User> staticsBirthday(Map<String,Object> map);

	/**
	 * 通过公司ID 查询 公司下的绩效专员
	 * @param companyId
	 * @return
     */
	Set<User> queryPerformanceEmployees(@Param("companyId") Integer companyId);

	/**
	 * 统计当天时刻 为审核工作日志的 部门经理  任务调度使用
	 * 查询用户部分字段
	 * @param time 时间
	 * @return
	 */
	List<User> staticsManager(@Param("time") Date time);


	/**
	 * 统计当天时刻 为审核工作日志的 综合部经理  任务调度使用
	 * 查询用户部分字段
	 * @param time 时间
	 * @return
     */
	List<User> staticsComprehensiveManager(@Param("time") Date time);

	/**
	 *  统计当天 时刻 未提交日志的用户   用于 SubmitWorkDiaryTask 任务使用
	 *  查询用户部分字段
	 * @param time 时间
	 * @return
     */
	List<User> staticsNOSubmitWorkdiary(@Param("time") Date time);


	/**
	 * 月度K王
	 * @param companyId
	 * @return
     */
	List<User> staticsMaxK(@Param("companyId") Integer companyId, @Param("startTime") Date startTime,@Param("endTime") Date endTime);


	/**
	 *  通过项目ID 计算统计 当周 在该项目下的工时
	 * @param projectId 项目ID
	 * @return
     */
	List<UserH> queryUserH(@Param("projectId") Integer projectId);

	/**
	 * 通过记录ID 计算统计 申请内部结项 在该项目下的工时
	 * @param projectRecordId
	 * @return
     */
	List<UserH> queryInnerUserH(@Param("projectRecordId") Integer projectRecordId);

	/**
	 * 通过记录ID 计算统计 申请外部结项 在该项目下的工时
	 * @param projectRecordId
	 * @return
	 */
	List<UserH> queryOuterUserH(@Param("projectRecordId") Integer projectRecordId);


	/**
	 * 根据id查询用户.
	 * @param id 用户id
	 * @return
	 */
	User getUserById(@Param("id") Integer id);

	/**
	 * 添加用户
	 * @param users
	 * @return
	 */
	int insert(User users);

	/**
	 * 根据账号查询用户.
	 * @param account
	 * @return
	 */
	User getUserByAccount(@Param("account") String account);

	/**
	 * 更改密码
	 * @param map
	 * @return
	 */
	int updatePassword(Map<String,Object> map);


	/**
	 * 用户账号是否存在.
	 * @param params
	 * @return
	 */
	int userAccountExist(Map<String, Object> params);

	/**
	 *  根据ID更新用户 不为空的字段
	 * @param user 用户
	 * @return
     */
	Integer updateUser(@Param("user") User user);

	/**
	 *  根据手机号 或者 用户名 查询 用户
	 * @param account
	 * @return
     */
	User queryUserByPhoneOrAccount(@Param("account") String account);

	/**
	 *  根据手机号 或者 用户名 查询 用户
	 * @param account
	 * @return
     */
	User queryUserByPhoneOrAccountForWeb(@Param("account") String account);

	/**
	 * 分页查询用户（web）
	 * @param map
	 * @return
     */
	List<User> findUserPageForWeb(Map<String,Object> map);

	/**
	 * 分页查询用户（web）
	 * @param map
	 * @return
     */
	List<User> findUserPageForWeb2(Map<String,Object> map);


	/**
	 *  根据部门ID  查询部门下的所有员工
	 * @param departmentId
	 * @return
     */
	List<User> queryUserByDepartment(Map<String,Object> map);

	/**
	 * 根据ID 查询 用户的部分字段
	 * @param id 用户ID
	 * @return 用户
     */
	User queryUserOtherInfo(@Param("id") Integer id);

	/**
	 * 根据 组员ID 集合 查询组员对象集合
	 * @param members (1,2,3)
	 * @return
     */
	List<User> queryUserByProjectGroup(@Param("members") String members);


	/**
	 * 获取当前部门的所有经理级别用户（判断当前部门是否已经设置经理）
	 * @param departmentId
	 * @return
     */
	List<User> getUserIsManagerList(@Param("departmentId")Integer departmentId);
	/**
	 * 查询部门里的 经理
	 * @param departmentId 部门ID
	 * @return
     */
	User queryManagerByDepartment(@Param("departmentId") Integer departmentId);

	/**
	 * 根据角色 查询用户
	 * @param roleId
	 * @return
     */
	List<User> queryUserByRole(@Param("roleId") Integer roleId,@Param("departmentId") Integer departmentId,
							   @Param("departmentType") Integer departmentType);

	/**
	 * @return
     */
	List<User> queryUserForWeb( @Param("companyId") Integer companyId,@Param("departmentId") Integer departmentId);

	/**
	 * 根据角色 查询用户（当前公司下）
	 * @param roleId
	 * @return
     */
	List<User> getCompanyIdRole(@Param("roleId") Integer roleId,
								@Param("departmentId") Integer departmentId);


	/**
	 * 通过项目ID  查询  参数该外部项目的 用户  查询部分字段
	 * @param projectId 项目ID
	 * @return
     */
	List<User> queryUserByProject(@Param("projectId") Integer projectId);


	/**
	 * 根据ID  查询 用户的基本信息
	 * @param id ID
	 * @return
     */
	User queryBaseInfo(@Param("id") Integer id);


	/**
	 * 统计 项目周验收 用户的 当前工时  和 总工时
	 * @param projectId
	 * @return
     */
	List<UserH> queryWeekUserHByProject(@Param("projectId") Integer projectId);



	User queryManagerByUser(@Param("departmentId") Integer departmentId);


	/**
	 * 通过部门ID  查询 该分公司的综合部经理
	 * @param departmentId 部门ID
	 * @return
     */
	User queryComprehensiveManager(@Param("departmentId") Integer departmentId);


	/**
	 * 查询该用户的 经理
	 * @param userId 用户ID
	 * @return
     */
	User queryManager(@Param("userId") Integer userId);


	/**
	 * 通过 公司ID 查询公司下所有的员工 部分字段
	 * @param companyId 公司ID
	 * @return
     */
	List<User> queryUserByCompany(@Param("companyId") Integer companyId);


	/**
	 * 获取没有部门 或者 公司的用户
	 * @param limit
	 * @param limitSize
     * @return
     */
	List<User> queryUserByIsValid(Map<String,Object> map);


	Integer countUserByIsValid(Map<String,Object> map);

}



