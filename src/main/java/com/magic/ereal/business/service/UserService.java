package com.magic.ereal.business.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.*;
import com.magic.ereal.business.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IUserMapper userMapper;

	@Resource
	private IRoleMapper roleMapper;
	@Resource
	private IAdminRoleMapper adminRoleMapper;
	@Resource
	private ICompanyMapper companyMapper;
	@Resource
	private IAllConfigMapper allConfigMapper;
	@Resource
	private IDepartmentChangeRecordMapper departmentChangeRecordMapper;
	@Resource
	private IWorkDiaryMapper workDiaryMapper;


	/**
	 * 导入用户
	 * @param excelUrl
	 * @throws Exception
     */
	public void importExcelUser(String excelUrl) throws Exception{

		int HttpResult; // 服务器返回的状态
		URL url = new URL(excelUrl); // 创建URL
		URLConnection urlConn = url.openConnection();
		urlConn.connect();
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;
		HttpResult = httpConn.getResponseCode();
		if (HttpResult != HttpURLConnection.HTTP_OK)
			throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据读取失败");
		else {
			Map<Integer, List<String>> map = ExcelReader.readExcelContent(urlConn.getInputStream());
			List<User> users = new ArrayList<>(); // 读取出来的用户列表
			List<String> emails = new ArrayList<>();
			List<String> accounts = new ArrayList<>();
			// 解析数据
			for (Integer cellNum : map.keySet()) {
				List<String> values = map.get(cellNum);
				if (CommonUtil.isEmpty(values.get(0))) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "帐号必须填写");
				}
				if (CommonUtil.isEmpty(values.get(1))) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "姓名必须填写");
				}
				if (CommonUtil.isEmpty(values.get(2))) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "邮箱必须填写");
				}
				if (null == values.get(3)) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "生日必须填写");
				}
				if (CommonUtil.isEmpty(values.get(4))) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "电话号码必须填写");
				}
				if (CommonUtil.isEmpty(values.get(5))) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "身份属性必须填写");
				}
				if (null == values.get(6)) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "入职时间必须填写");
				}
				if (null == values.get(8)) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "薪酬必须填写");
				}
				if (null == values.get(9)) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "性别必须填写");
				}
				if (CommonUtil.isEmpty(values.get(2))) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "合作状态必须填写");
				}
				User temp = new User();
				temp.setAccount(values.get(0));
				temp.setName(values.get(1));
				temp.setEmail(values.get(2));
				temp.setBirthday(null == values.get(3) ? null : DateStringUtil.stringToDate(values.get(3),"yyyy-MM-dd"));
				temp.setPhone(values.get(4));
				if("正式".equals(values.get(5))){
					temp.setIncumbency(2);
				}else if("磨合期".equals(values.get(5))){
					temp.setIncumbency(1);
				}else if("实习".equals(values.get(5))){
					temp.setIncumbency(0);
				}
				temp.setEntryTime(null == values.get(6) ? null : DateStringUtil.stringToDate(values.get(6),"yyyy-MM-dd"));
				temp.setPositiveTime(null == values.get(7) ? null : DateStringUtil.stringToDate(values.get(7),"yyyy-MM-dd"));
				temp.setSalary(null == values.get(8) ? 0.0 : Double.valueOf(values.get(8)));
				if("男".equals(values.get(9))){
					temp.setSex(0);
				}else if("女".equals(values.get(9))){
					temp.setSex(1);
				}
				if("合作".equals(values.get(10))){
					temp.setInfoStatus(0);
				}
				else if("股东".equals(values.get(10))){
					temp.setInfoStatus(1);
				}
				temp.setPassword(MD5Util.md5("111111"));
				emails.add(temp.getEmail());
				accounts.add(temp.getAccount());
				users.add(temp);
			}

			if (users.size() == 0) {
				throw new InterfaceCommonException(StatusConstant.Fail_CODE, "表格数据为空");
			}
			if(users.size() > 0){
				if(emails.size() > 0 && accounts.size() > 0){
					List<User> users1 = userMapper.batchQueryUser(accounts, emails);
					if(null != users1 && users1.size() > 0){
						throw new InterfaceCommonException(StatusConstant.Fail_CODE, "帐号或Email已经存在");
					}
				}else{
					throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据异常");
				}
				userMapper.batchAddUser(users);
			}
		}

	}

	/**
	 * 判断当前人 是否担任过C导师 实用 外部项目
	 * @param userId
	 * @return
     */
	public Map<String,Integer> isCTeacher(Integer userId){
		Map<String,Integer> data = new HashMap<>();
		// 外部项目
		Integer cTeacher = userMapper.isCTeacher(userId, 1);
		data.put("isPowerOfProject", cTeacher > 0 ? 1 : 0);
		data.put("isCTeacher",userMapper.isCTeacherOfProject(userId) > 0 ? 1 : 0);
		// 内部项目
		Integer cTeacher1 = userMapper.isCTeacher(userId, 0);
		data.put("isPowerOfProjectInterior", cTeacher1 > 0 ? 1 : 0);
		data.put("isPowerOfAccredit",cTeacher > 0 || cTeacher1 > 0 ? 1 : 0);
		return data;
	}

	public List<User> queryUserByItemsOfV2(Integer companyId,Integer departmentId,Integer roleId){
		return userMapper.queryUserByItemsOfV2(departmentId,companyId,roleId);
	}

	public List<User> queryAllUser(){
		return userMapper.queryAllUser();
	}

	/**
	 * 导出 用户 工作时间报表 统计数据
	 * @param companyId 公司ID
	 * @param month 月份
     * @return
     */
	public List<UserExcelOfWorkDiary> countUserWorkDiary(Integer companyId,Date month,Integer departmentId){
		// 指定月的第一天
		Date dayOfMonth = DateTimeHelper.getCurrentFirstDayOfMonth(month);
		Date dayOfMonthOfEnd = DateTimeHelper.getCurrentLastDayOfMonth(month);
		int days = DateTimeHelper.getDaysOfMonth(month);
		List<UserExcelOfWorkDiary> excelOfWorkDiaries = userMapper.countUserWorkDiary(companyId, month,departmentId);
		// 计算排名
		if(null != excelOfWorkDiaries && excelOfWorkDiaries.size() > 0){
			AllConfig config = allConfigMapper.queryConfig();
			for (UserExcelOfWorkDiary excelOfWorkDiary : excelOfWorkDiaries) {
				double totalTime = 0.0;
				//补全天数
				for(long i = dayOfMonth.getTime(); i <= dayOfMonthOfEnd.getTime(); i += 24 * 3600 * 1000){
					ExcelOfWorkDiarySub temp = new ExcelOfWorkDiarySub();
					temp.setDate(new Date(i));
					if(null != excelOfWorkDiary.getSubs()){
						boolean isExite = false;
						for (ExcelOfWorkDiarySub excelOfWorkDiarySub : excelOfWorkDiary.getSubs()) {
							if(excelOfWorkDiarySub.getDate().getTime() / 1000 ==
									temp.getDate().getTime() / 1000){
								isExite = true;
								break;
							}
						}
						if(!isExite){
							excelOfWorkDiary.getSubs().add(temp);
						}

					}else{
						List<ExcelOfWorkDiarySub> subs = new ArrayList<>();
						subs.add(temp);
						excelOfWorkDiary.setSubs(subs);
					}
				}
				if(null != excelOfWorkDiary.getSubs() && excelOfWorkDiary.getSubs().size() > 0){
					for (ExcelOfWorkDiarySub sub : excelOfWorkDiary.getSubs()) {
						totalTime += sub.getSportTime() + sub.getStudyTime() + sub.getWorkTime();
					}
				}
				// 总时间
				excelOfWorkDiary.setTotalTime(totalTime);
				// 日均时间
				excelOfWorkDiary.setAverageTime(totalTime == 0 ? 0 : days / totalTime);
				// 折算天数
				excelOfWorkDiary.setConvertDays(totalTime / config.getDayHour());
			}
			// 排序
			Collections.sort(excelOfWorkDiaries);
			// 设置排名
			for (int i = 0; i < excelOfWorkDiaries.size(); i++) {
				excelOfWorkDiaries.get(i).setRanking(i + 1);
			}
		}
		return excelOfWorkDiaries;

	}

	/**
	 *
	 * @param userIds
	 * @return
     */
	public List<User> queryUserDeviceTypeAndToken(Integer... userIds){
		List<User> users = null;
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < userIds.length; i++) {
			ids.add(userIds[i]);
		}
		if(ids.size() > 0){
			users = userMapper.queryUserDeviceTypeAndToken(ids);
		}
		return users;
	}

	/**
	 *
	 * @return
     */
	public List<User> queryUserDeviceTypeAndToken(List<Integer> ids){
		List<User> users = null;
		if(ids.size() > 0){
			users = userMapper.queryUserDeviceTypeAndToken(ids);
		}
		return users;
	}


	public User queryUserByPhone(String phone){
		return userMapper.queryUserByPhone(phone);
	}

	/**
	 * 查询 生日到点 包括绩效专员
	 * @param time  时间
	 * @return
     */
	public List<Company> staticsBirthday(Date time){
		return companyMapper.staticsBirthday(time);
	}


	/**
	 * 统计当天时刻 为审核工作日志的 部门经理  任务调度使用
	 * 查询用户部分字段
	 * @param time 时间
	 * @return
	 */
	public List<User> staticsManager(Date time){
		return userMapper.staticsManager(time);
	}


	/**
	 * 统计当天时刻 为审核工作日志的 综合部经理  任务调度使用
	 * 查询用户部分字段
	 * @param time 时间
	 * @return
	 */
	public List<User> staticsComprehensiveManager(Date time){
		return userMapper.staticsComprehensiveManager(time);
	}

	/**
	 *  统计当天 时刻 未提交日志的用户   用于 SubmitWorkDiaryTask 任务使用
	 *  查询用户部分字段
	 * @param time 时间
	 * @return
	 */
	public List<User> staticsNOSubmitWorkdiary(Date time){
		return userMapper.staticsNOSubmitWorkdiary(time);
	}


	public void setDeviceNull(Integer userId){
		userMapper.setDeviceNull(userId);
	}


	/**
	 * 统计K王
	 * @param companyId
     * @return
     */
	public List<User> staticsMaxK(Integer companyId, Date date) throws Exception{
		//开始时间
		Date startTime = new Date() ;
		//结束时间
		Date endTime = new Date();
		Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
		Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");

		if (null != date){
			bDate = Timestamp.parseDate(String.valueOf(date.getTime() / 1000) , "yyyy-MM-dd");
			eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
		}
		//获取这个时间的这月月初的日期 开始时间
		startTime = DateTimeHelper.getMonthByDate(bDate,"first");
		//获取这个时间的这月月末的日期 结束时间
		endTime = DateTimeHelper.getMonthByDate(eDate,"last");
		List<User> users = userMapper.staticsMaxK(companyId, startTime, endTime);
		List<User> data = new ArrayList<>();
		if(null != users && users.size() > 0){
			for (int i = 0; i < users.size(); i++) {
				if(i < 10){
					data.add(users.get(i));
				}else{
					break;
				}
			}
//			double k = users.get(0).getTotalK();
//			Iterator<User> iterator = users.iterator();
//			while (iterator.hasNext()){
//				User next = iterator.next();
//				if(next.getTotalK() != k || next.getTotalK() == 0){
//					iterator.remove();
//				}
//			}
		}
		return data;
	}

	/**
	 * 根据公司ID 查询
	 * @param companyId 公司ID
	 * @return
     */
	public List<User> queryUserByCompany(Integer companyId){
		return userMapper.queryUserByCompany(companyId);
	}

	/**
	 * 统计 项目周验收 用户的 当前工时  和 总工时
	 * @param projectId
	 * @return
	 */
	public List<UserH> queryWeekUserHByProject(Integer projectId){
		return userMapper.queryWeekUserHByProject(projectId);
	}


	/**
	 * 查询用户的 基础信息
	 * @param userId
	 * @return
     */
	public User queryBaseInfo(Integer userId){
		return userMapper.queryBaseInfo(userId);
	}

	/**
	 * 验证登录
	 *
	 * @return
	 * @throws InterfaceCommonException
	 */
	public User checkLogin(String account, String password) throws InterfaceCommonException {
		// 查找是否存在用户
		User user = userMapper.queryUserByPhoneOrAccountForWeb(account);
		if (user == null) {
			throw new InterfaceCommonException(StatusConstant.USER_DOES_NOT_EXIST, "用户不存在");
		}else if(null == user.getRoleId()){
			throw new InterfaceCommonException(StatusConstant.Fail_CODE, "账户不可用");
		}
		else {
			if (user.getPassword().equals(password)) {
				// 登录成功
//				user.setPassword(null);
				//放入缓存 获取token
//				String token = LoginHelper.addToken(user);
//				user.setToken(token);
				return user;
			}
			else {
				throw new InterfaceCommonException(StatusConstant.PASSWORD_ERROR, "用户名或密码错误");
			}

		}

	}


	public User queryUserByPhoneOrAccountForWeb(String account){
		return userMapper.queryUserByPhoneOrAccountForWeb(account);
	}


	public List<User> queryUserByDepartment2(Integer departmentId){
		return userMapper.queryUserByDepartment2(departmentId);
	}

	/**
	 * 添加用户
	 * @param user
     */
	@Transactional
	public void insert(User user) {
		if (null == user.getDepartmentId() || null == user.getAccount() || null == user.getPhone()) {
			throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
		}
		// 查找是否存在用户
		User isAccount = userMapper.getUserByAccount(user.getAccount());
		if (null != isAccount) {
			throw new InterfaceCommonException(StatusConstant.USER_NAME_ALREADY_EXISTS, "此账号已经存在");
		}
		User isPhone = userMapper.getUserByAccount(user.getPhone());
		if (null != isPhone) {
			throw new InterfaceCommonException(StatusConstant.PHONE_NUMBER_THERE, "此手机号码已经存在");
		}
		User isEmail = userMapper.getUserByAccount(user.getEmail());
		if (null != isEmail) {
			throw new InterfaceCommonException(StatusConstant.PHONE_NUMBER_THERE, "此邮箱已经注册");
		}
		List<User> u = userMapper.getUserIsManagerList(user.getDepartmentId());
		if(null != u && u.size() > 0){



		}
		if (null != u && u.size() > 0) {
			List<Role> list = roleMapper.list(null);
			for (Role role : list) {
				if (user.getRoleId().equals(role.getId())  &&
						(null != role.getLevel() && role.getLevel() == 1)) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此部门已存在经理级别员工，不能重复添加经理级别员工");
				}
			}
		} else {
			List<Role> list = roleMapper.list(null);
			for (Role role : list) {
				if (user.getRoleId().equals(role.getId())  && (null == role.getLevel() || role.getLevel() != 1)) {
					throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "此部门不存在经理级别员工，请先添加经理级别员工");
				}
			}
		}
		int isCTeacher = 0;
		if(null != user.getRoleIds() && user.getRoleIds().trim().length() != 0){
			String[] split = user.getRoleIds().split(",");
			for (int i = 0; i < split.length; i++){
				if(Integer.valueOf(split[i]) == 7){
					// 如果包含了C导师
					isCTeacher = 1;
				}
			}
		}
		// 判断是否有C导师角色
		user.setIsCTeacher(user.getRoleId() == 7 ? 1 : isCTeacher);
		userMapper.insert(user);
		// 封装 多角色
		List<AdminRole> adminRoles = new ArrayList<>();
		if(null != user.getRoleIds() && user.getRoleIds().trim().length() != 0){
			String[] split = user.getRoleIds().split(",");
			for (int i = 0; i < split.length; i++){
				AdminRole adminRole = new AdminRole();
				adminRole.setUserId(user.getId());
				adminRole.setRoleId(Integer.valueOf(split[i]));
				adminRoles.add(adminRole);
				if(adminRole.getRoleId() == 7){
					// 如果包含了C导师
					isCTeacher = 1;
				}
			}
		}

		if(adminRoles.size() > 0){
			adminRoleMapper.batchAddAdminRole(adminRoles);
		}
	}

	/**
	 * 分页查询用户（web）
	 * @param user 用户实体
	 * @param pageArgs 分页实体
	 * @return object
	 */
	public PageList<User> findUserPageForWeb(PageArgs pageArgs,User user) throws Exception {
		PageList<User> pageList = new PageList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("user",user);
		//获取总数
		List<User> totalUser = userMapper.findUserPageForWeb(map);
		//总条数
		int count = 0;
		if (null != totalUser) {
			count = totalUser.size();
			map.put("pageArgs", pageArgs);
			pageList.setList(userMapper.findUserPageForWeb(map));
		}
		pageList.setTotalSize(count);
		return pageList;
	}

	/**
	 * 分页查询用户（web）
	 * @param user 用户实体
	 * @param pageArgs 分页实体
	 * @return object
	 */
	public PageList<User> findUserPageForWeb2(PageArgs pageArgs,User user) throws Exception {
		PageList<User> pageList = new PageList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("user",user);
		//获取总数
		List<User> totalUser = userMapper.findUserPageForWeb2(map);
		//总条数
		int count = 0;
		if (null != totalUser) {
			count = totalUser.size();
			map.put("pageArgs", pageArgs);
			pageList.setList(userMapper.findUserPageForWeb2(map));
		}
		pageList.setTotalSize(count);
		return pageList;
	}


	/**
	 * 分页查询用户（web）
	 * @param user 用户实体
	 * @param pageArgs 分页实体
	 * @return object
	 */
	public PageList<User> findUserPageFoIsValid(PageArgs pageArgs,User user) throws Exception {
		PageList<User> pageList = new PageList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("user",user);
		//获取总数
		List<User> totalUser = userMapper.queryUserByIsValid(map);
		//总条数
		int count = 0;
		if (null != totalUser) {
			count = totalUser.size();
			map.put("pageArgs", pageArgs);
			pageList.setList(userMapper.queryUserByIsValid(map));
		}
		pageList.setTotalSize(count);
		return pageList;
	}

	 /**
	  * 更改密码
	  * @param mobile
	  * @param newPwd
	  * @param session
	  * @return
	  * @throws InterfaceCommonException
	  */
	 @Transactional
     public int updatePassword(String mobile,String newPwd,HttpSession session) throws InterfaceCommonException{

		String code = (String) session.getAttribute(mobile);
		int count = 0;

		/*if(code == null || "".equals(code)){

			throw new InterfaceCommonException(ErrorCode.Fail, "手机号码错误");
		}else{

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("mobile",mobile);

			newPwd = Sha1Util.SHA1(newPwd);

			map.put("newPwd", newPwd);
			count = userMapper.updatePassword(map);

			if(count !=1){

				throw new InterfaceCommonException(ErrorCode.Fail, "密码更新失败");
			}else{

				// 验证成功,清空验证码session
				session.removeAttribute(mobile);
			}
		}*/

		return count;
	}

	/**
	 * 根据id查询用户.
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id) {

		return userMapper.getUserById(id);
	}

	/**
	 * 用户账号是否重复
	 * @param id
	 */
	public void userAccountExist(String id, String account) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("account", account);

		int count = userMapper.userAccountExist(params);
		if (count > 0) {
			throw new InterfaceCommonException(StatusConstant.Fail_CODE, "用户账号已存在");
		}
	}



	/**
	 *  根据 手机号 或者 帐号 查询用户
	 * @param account 手机号 或者 帐号
	 * @return
     */
	public User queryUserByItem(String account){
		return userMapper.queryUserByPhoneOrAccount(account);
	}

	/**
	 *  更新用户不为空的字段
	 * @param user
     */
	@Transactional
	public void updateUser(User user) throws Exception{
		User oldUser = userMapper.queryBaseInfo(user.getId());
		if(null == oldUser){
			throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"用户不存在");
		}

		// 记录部门变更
		if(null != user.getDepartmentId() && null != oldUser.getDepartmentId() &&
				!oldUser.getDepartmentId().equals(user.getDepartmentId())){
			DepartmentChangeRecord record1 = departmentChangeRecordMapper.queryRecord(user.getId(), new Date());
			if(null != record1){
				record1.setFromDepartmentId(oldUser.getDepartmentId());
				record1.setToDepartmentId(user.getDepartmentId());
				departmentChangeRecordMapper.updateDepartmentChangeRecord(record1);
			}
			else{
				DepartmentChangeRecord record = new DepartmentChangeRecord();
				record.setFromDepartmentId(oldUser.getDepartmentId());
				record.setToDepartmentId(user.getDepartmentId());
				record.setUserId(user.getId());
				record.setDateMonth(new Date());
				departmentChangeRecordMapper.addDepartmentChangeRecord(record);
			}
		}
		userMapper.updateUser(user);
	}


	/**
	 * 根据角色 查询用户
	 * @param roleId
	 * @param  departmentType  部门类型
	 * @return
	 */
	public List<User> queryUserByRole(Integer roleId,Integer departmentId,Integer departmentType) throws Exception {
		return userMapper.queryUserByRole(roleId,departmentId,departmentType);
	}



	/**
	 *  查询C导师和 B导师
	 * @return
	 */
	public List<User> queryUserForWeb(Integer companyId,Integer departmentId) throws Exception {
		return userMapper.queryUserForWeb(companyId,departmentId);
	}



	/**
	 * 根据角色 查询用户（当前公司下）
	 * @param roleId
	 * @return
	 */
	public List<User> getCompanyIdRole(Integer roleId,Integer departmentId) throws Exception {
		return userMapper.getCompanyIdRole(roleId,departmentId);
	}

	/**
	 * 查询部门里的 经理
	 * @param departmentId 部门ID
	 * @return
	 */
	public User queryManagerByDepartment(Integer departmentId) throws Exception {
		return userMapper.queryManagerByDepartment(departmentId);
	}

	/**
	 * 通过项目ID  查询  参数该外部项目的 用户  查询部分字段
	 * @param projectId 项目ID
	 * @return
	 */
	public List<User> queryUserByProject(Integer projectId){
		return userMapper.queryUserByProject(projectId);
	}


	public int verifyWordDiary(Integer userId){
		List<WorkDiary> workDiaries = workDiaryMapper.queryWorkDiaryVerifier(null, userId);
		if(null == workDiaries || workDiaries.size() == 0){
			return  0;
		}else{
			return 1;
		}
	}

}
