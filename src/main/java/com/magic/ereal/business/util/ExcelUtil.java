package com.magic.ereal.business.util;

import com.magic.ereal.business.entity.*;
import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  数据导出excel工具
 * Created by Eric Xie on 2017/3/15 0015.
 */

public class ExcelUtil {

    private static Logger logger = Logger.getLogger(ExcelUtil.class);



    public static void test(String path, Map<String,Object> map, Writer writer){




    }


    /**
     *  导出项目组的内部项目
     * @param resp
     * @param userName
     * @param password
     * @param departments
     */
    public static void drawExcelProjectInterior(HttpServletResponse resp,String userName,String password,
                                                List<Department> departments,int persons){

        Date date = new Date();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建工作簿
        hssfWorkbook.writeProtectWorkbook(password,userName);
        // 设置样式
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 创建第一行
        HSSFRow rowFrist = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
        rowFrist.createCell(0).setCellValue("一真咨询"+DateTimeHelper.getYearOfDate(date)+"年内部项目台帐");


        int baseColumn = 9;
        // 求 列
        int newReportColumn = 9 + 3 * persons;

        // 验收列
        int otherColumn = 8 + 2 * persons;
        // 设置第二行
        // 第二行合并设置
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,5)); // 项目立项信息 合并
        sheet.addMergedRegion(new CellRangeAddress(1,1,6,8)); // 项目立项信息之后 空格 合并
        sheet.addMergedRegion(new CellRangeAddress(1,1,9,9+newReportColumn)); // 验收阶段 验收结果 合并

        HSSFRow rowSecond = sheet.createRow(1);

        rowSecond.createCell(1).setCellValue("项目立项信息");
        rowSecond.createCell(9).setCellValue("验收阶段验收结果");

        // 设置第三行
        // 合并第三行
        sheet.addMergedRegion(new CellRangeAddress(2,3,0,0)); // 团队 合并
        sheet.addMergedRegion(new CellRangeAddress(2,3,1,1)); // 任务序号 合并
        sheet.addMergedRegion(new CellRangeAddress(2,3,1,1)); // 任务序号 合并


        HSSFRow rowThree = sheet.createRow(2);
        for (int i = 0; i < baseColumn + newReportColumn; i++) {



        }






        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            resp.reset();
            resp.setHeader("Content-disposition", "attachment; filename=details.xls");
            resp.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }



    private static void setRowThreeColounm(){

    }





    public static void drawExcel(HttpServletResponse resp,String userName,String password,
                                 List<UserExcelOfWorkDiary> users,Date monthDate){

        Date date = null == monthDate ? new Date() : monthDate; // 月份
        int daysOfMonth = DateTimeHelper.getDaysOfMonth(date);// 获取本月的天数
        int month = DateTimeHelper.getMonthOfYear(date);
        String title = DateStringUtil.dateToString(date, "yyyy年MM月") + "工作学习时间统计表";
        // 设置总的列
        int column = daysOfMonth + 8;
        // 导出的数据集合
//        List<UserExcelOfWorkDiary> users = data;
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建工作簿
        hssfWorkbook.writeProtectWorkbook(password,userName);
        // 设置样式
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 创建第一行
        HSSFRow rowFrist = sheet.createRow(0);
        rowFrist.createCell(0).setCellValue(title);
        // 第一行 跨列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,column));
        // 设置第二行
        HSSFRow rowSecond = sheet.createRow(1);
        if(null != users && users.size() > 0){

            for (int i = 0; i < column; i++) {

                if(i < 8){
                    // 设置前8列
                    rowSecond.createCell(i).setCellValue(getColumnMessage(i));
                    continue;
                }
                String msg = month+"/"+(i + 1 - 8);
                rowSecond.createCell(i).setCellValue(msg);
            }
            int rows = 2; // 从第三行开始
            // 设置 其他规则行
            for (int i = 0; i < users.size(); i++) {

                UserExcelOfWorkDiary user = users.get(i);
                // 设置行数据  每个用户 三行数据
                HSSFRow userRow1 = sheet.createRow(rows);
                HSSFRow userRow2 = sheet.createRow(rows+1);
                HSSFRow userRow3 = sheet.createRow(rows+2);
                for (int j = 0; j < column; j++) {
                    // 设置第rows行的列
                    if(j == 0){
                        // 第一列合并
                        sheet.addMergedRegion(new CellRangeAddress(rows,rows+2,j,j));
                        userRow1.createCell(j).setCellValue(user.getUserName());
                        continue;
                    }
                    if(j == 1){
                        // 第二列
                        userRow1.createCell(j).setCellValue("工作时间");
                        userRow2.createCell(j).setCellValue("学习时间");
                        userRow3.createCell(j).setCellValue("运动时间");
                        continue;
                    }
                    if(j == 2){
                        // 第三列 合并
                        sheet.addMergedRegion(new CellRangeAddress(rows,rows+2,j,j));
                        userRow1.createCell(j).setCellValue(user.getAverageTime());
                        continue;
                    }
                    if(j == 3){
                        // 第四列 合并
                        sheet.addMergedRegion(new CellRangeAddress(rows,rows+2,j,j));
                        userRow1.createCell(j).setCellValue(user.getConvertDays());
                        continue;
                    }
                    if(j == 4){
                        // 第五列 合并
                        sheet.addMergedRegion(new CellRangeAddress(rows,rows+2,j,j));
                        userRow1.createCell(j).setCellValue(user.getAttendanceDays());
                        continue;
                    }
                    if(j == 5){
                        // 第五列 合并
                        sheet.addMergedRegion(new CellRangeAddress(rows,rows+2,j,j));
                        userRow1.createCell(j).setCellValue(user.getTotalTime());
                        continue;
                    }
                    if(j == 6){
                        // 第六列 合并
                        sheet.addMergedRegion(new CellRangeAddress(rows,rows+2,j,j));
                        userRow1.createCell(j).setCellValue(user.getRanking());
                        continue;
                    }
                    if(j == 7){
                        // 第七列 合并
                        userRow1.createCell(j).setCellValue(user.getWorkTotalTime());
                        userRow2.createCell(j).setCellValue(user.getStudyTotalTime());
                        userRow3.createCell(j).setCellValue(user.getSportTotalTime());
                        continue;
                    }
                    // 其他不用合并且  规则列
                    userRow1.createCell(j).setCellValue(user.getSubs().get(j - 8).getWorkTime());
                    userRow2.createCell(j).setCellValue(user.getSubs().get(j - 8).getStudyTime());
                    userRow3.createCell(j).setCellValue(user.getSubs().get(j - 8).getSportTime());
                }
                rows+=3;
            }

        }
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            resp.reset();
            resp.setHeader("Content-disposition", "attachment; filename=details.xls");
            resp.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }


    /**
     *  跨列合并单元格
     * @param sheet
     * @param userRow
     * @param rows
     * @param j
     */
    private void mergeSheet(HSSFSheet sheet,HSSFRow userRow,int rows,int j,String obj){
        sheet.addMergedRegion(new CellRangeAddress(rows,rows+2,j,j));
        userRow.createCell(j).setCellValue(obj);
    }






    private static String getColumnMessage(int i){
        String msg = null;
        switch (i){
            case 0: msg = "姓名";break;
            case 1: msg = "时间类型";break;
            case 2: msg = "日均时间";break;
            case 3: msg = "折算天数";break;
            case 4: msg = "出勤天数";break;
            case 5: msg = "总时间";break;
            case 6: msg = "总排名";break;
            case 7: msg = "总记";break;
            default:msg = "";break;

        }
        return msg;
    }




    public static void drawExcelOfProject(HttpServletResponse resp,List<ExcelProject> projects,
                                   String password,String userName){

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建工作簿
        sheet.protectSheet(password);
        hssfWorkbook.writeProtectWorkbook(password,userName);
        HSSFRow rowFrist = sheet.createRow(0); // 创建第一行
        int column = 28; // 初始化 28列值
        for (int i = 0; i < column; i++) {
            // 设置第一行 所有列的值
            rowFrist.createCell(i).setCellValue(getOneRowValue(i));
        }
        // 设置第二行以后的数据
        int row = 1;
        if(null != projects){
            for (ExcelProject project : projects) {
                HSSFRow rows = sheet.createRow(row);
                for (int i = 0; i < column ; i++){
                    rows.createCell(i).setCellValue(getOtherRowValue(i,project,row));
                }
                row++;
            }
        }
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            resp.reset();
            resp.setHeader("Content-disposition", "attachment; filename=details.xls");
            resp.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }


    /**
     * 传递卡导出
     * @param resp
     * @param subs
     * @param password
     * @param userName
     */
    public static void drawExcelOfWorkDiary(HttpServletResponse resp,List<ExcelOfWorkDiary> subs,
                                          String password,String userName){

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建工作簿
        hssfWorkbook.writeProtectWorkbook(password,userName);
        HSSFRow rowFrist = sheet.createRow(0); // 创建第一行
        int column = 11; // 初始化 9列值
        for (int i = 0; i < column; i++) {
            // 设置第一行 所有列的值
            rowFrist.createCell(i).setCellValue(getOneRowValueOfWorkDiary(i));
        }
        // 设置第二行以后的数据
        int row = 1;
        if(null != subs){
            for (ExcelOfWorkDiary project : subs) {
                HSSFRow rows = sheet.createRow(row);
                for (int i = 0; i < column ; i++){
                    rows.createCell(i).setCellValue(getOtherRowValueOfWorkDiary(i,project));
                }
                row++;
            }
        }
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            resp.reset();
            resp.setHeader("Content-disposition", "attachment; filename=details.xls");
            resp.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }


    private static String getOtherRowValue(int column,ExcelProject project,int row){
        String msg = null;
        switch (column){
            case 0:
                msg = (row) +"";
                break;
            case 1:msg = getValue(project.getCustomerUnit());
                break;
            case 2:msg = getValue(project.getProjectTypeName());
                break;
            case 3:msg = getValue(project.getCustomerDepartment());
                break;
            case 4:msg = getValue(project.getProjectName());
                break;
            case 5:msg = getValue(project.getProjectNameShort());
                break;
            case 6:msg = getValue(project.getDepartmentName());
                break;
            case 7:
                double s = Math.floor(project.getProgress() * 100);
                msg = s + "%";
                break;
            case 8:
                // 本月目标进度
                msg = "";
                break;
            case 9:
                msg = null == project.getStartTime() ? "" :
                        DateStringUtil.dateToString(project.getStartTime(),"yyyy-MM-dd HH:mm");
                break;
            case 10:msg = getValue(project.getProjectManager());
                break;
            case 11:msg = getValue(project.getcTeacherName());
                break;
            case 12:
                //是否有启动书
                msg = null == project.getConnectStatus() || -1 == project.getConnectStatus() ? "无" : "有"  ;
                break;
            case 13:msg = getValue(project.getCustomerRemarks());
                break;
            case 14:
                //获奖潜质
                msg = "";
                break;
            case 15:
                //一真定位
                msg = "";
                break;
            case 16:msg = getValue(project.getRiskRemarks());
                break;
            case 17:
                if(project.getStatus() >= 5004){
                    msg = "破";
                }else if(project.getStatus() == 5002 || project.getStatus() == 5003 ){
                    msg = "半破";
                }else {
                    msg = "未破";
                }
                break;
            case 18:msg = getValue(project.getPoScore());
                break;
            case 19:msg = getDateStr(project.getPoTime());
                break;
            case 20:
                msg = project.getStatus() >= 5006 ? "是" : "否";
                break;
            case 21:msg = getValue(project.getInteriorScore());
                break;
            case 22:msg = getDateStr(project.getInteriorTime());
                break;
            case 23:msg = project.getStatus() == 5008 ? "是" : "否";
                break;
            case 24:msg = getValue(project.getExteriorScore());
                break;
            case 25:msg = getDateStr(project.getExteriorTime());
                break;
            case 26:
                //入围情况
                msg = "";
                break;
            case 27:
                //获奖情况
                msg = "";
                break;
            default: msg = "";
                break;
        }
        return msg;
    }




    private static String getValue(Object args){
        if(null == args){
            args = "";
        }
        return args.toString();
    }


    private static String getDateStr(Date date){
        String msg = "";
        if(null == date){
            return msg;
        }
        int weekOfMonth = DateTimeHelper.getWeekOfMonth(date);
        int monthOfYear = DateTimeHelper.getMonthOfYear(date);
        msg = monthOfYear +"月-第"+weekOfMonth+"周";
        return msg;
    }


    private static String getOneRowValue(int column){
        String msg = null;
        switch (column){
            case 0:msg = "序号";
                break;
            case 1:msg = "公司";
                break;
            case 2:msg = "课题类型";
                break;
            case 3:msg = "部门";
                break;
            case 4:msg = "课题名称";
                break;
            case 5:msg = "简称";
                break;
            case 6:msg = "跟进团队";
                break;
            case 7:msg = "当前项目进度";
                break;
            case 8:msg = "本月目标进度";
                break;
            case 9:msg = "启动时间";
                break;
            case 10:msg = "项目经理";
                break;
            case 11:msg = "C导师";
                break;
            case 12:msg = "是否有启动书";
                break;
            case 13:msg = "客户定位";
                break;
            case 14:msg = "获奖潜质";
                break;
            case 15:msg = "一真定位";
                break;
            case 16:msg = "项目启动风险";
                break;
            case 17:msg = "是否破题";
                break;
            case 18:msg = "破题质量分";
                break;
            case 19:msg = "破题时间";
                break;
            case 20:msg = "是否内部结项";
                break;
            case 21:msg = "内部结项分数";
                break;
            case 22:msg = "内部结项月份";
                break;
            case 23:msg = "是否外部结项";
                break;
            case 24:msg = "外部结项分数";
                break;
            case 25:msg = "外部结项月份";
                break;
            case 26:msg = "入围情况";
                break;
            case 27:msg = "获奖情况";
                break;
            default: msg = "";
                break;
        }
        return msg;
    }

    private static String getOneRowUserValue(int column){
        switch (column){
            case 0:return "序号";
            case 1:return "账号";
            case 2:return "姓名";
            case 3:return "手机号";
            case 4:return "所属公司";
            case 5:return "所属部门";
            case 6:return "角色名";
            case 7:return "入职时间";
            case 8:return "转正时间";
            case 9:return "工资";
            case 10:return "身份属性";
            case 11:return "性别";
            case 12:return "合作状态";
            case 13:return "创建时间";
            default:return "";
        }
    }

    private static String getOneRowValueOfWorkDiary(int column){
        String msg = "";
        switch (column){

            case 0:msg = "日期";
                break;
            case 1:msg = "月份";
                break;
            case 2:msg = "姓名";
                break;
            case 3:msg = "开始时间";
                break;
            case 4:msg = "结束时间";
                break;
            case 5:msg = "项目";
                break;
            case 6:msg = "阶段";
                break;
            case 7:msg = "分类";
                break;
            case 8:msg = "工作期间";
                break;
            case 9:msg = "用时";
                break;
            case 10:msg = "性质";
                break;
            default: msg = "";
                break;
        }
        return msg;
    }

    private static String getOtherRowValueOfWorkDiary(int column,ExcelOfWorkDiary sub){
        String msg = "";
        switch (column){

            case 0:
                msg = Timestamp.DateTimeStamp(sub.getWorkTime(),"MM月dd日");
                break;
            case 1:msg = Timestamp.DateTimeStamp(sub.getWorkTime(),"yyyy年MM月");
                break;
            case 2:msg = getValue(sub.getUserName());
                break;
            case 3:msg = Timestamp.DateTimeStamp(sub.getStartTime(),"yyyy年MM月dd日 HH:mm:ss");
                break;
            case 4:msg = Timestamp.DateTimeStamp(sub.getEndTime(),"yyyy年MM月dd日 HH:mm:ss");
                break;
            case 5:msg = getValue(sub.getTypeName());
                break;
            case 6:msg = getValue(sub.getJobContent());
                break;
            case 7:msg = getValue(sub.getCategoryName());
                break;
            case 8:msg = getValue(sub.getDuring());
                break;
            case 9:msg = getValue(sub.getTime());
                break;
            case 10:msg = getValue(sub.getTransactionType());
                break;
            default: msg = "";
                break;
        }
        return msg;
    }

    public static void drawExcelOfUser(HttpServletResponse resp,List<User> users,
                                          String password,String userName){

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建工作簿
        sheet.protectSheet(password);
        hssfWorkbook.writeProtectWorkbook(password,userName);
        HSSFRow rowFrist = sheet.createRow(0); // 创建第一行
        int column = 14; // 初始化 13列值
        for (int i = 0; i < column; i++) {
            // 设置第一行 所有列的值
            rowFrist.createCell(i).setCellValue(getOneRowUserValue(i));
        }
        // 设置第二行以后的数据
        int row = 1;
        if(null != users){
            for (User user : users) {
                HSSFRow rows = sheet.createRow(row);
                for (int i = 0; i < column ; i++){
                    rows.createCell(i).setCellValue(getOtherRowValue(i,user,row));
                }
                row++;
            }
        }
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            resp.reset();
            resp.setHeader("Content-disposition", "attachment; filename=user.xls");
            resp.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    private static String getOtherRowValue(int column,User user,int row){
        String msg = null;
        switch (column){
            case 0:
                msg = (row) +"";
                break;
            case 1:msg = getValue(user.getAccount());
                break;
            case 2:msg = getValue(user.getName());
                break;
            case 3:msg = getValue(user.getPhone());
                break;
            case 4:msg = getValue(user.getCompanyName());
                break;
            case 5:msg = getValue(user.getDepartmentName());
                break;
            case 6:msg = getValue(user.getRoleName());
                break;
            case 7:
                //入职时间
                msg = null == user.getEntryTime() ? "" :
                        DateStringUtil.dateToString(user.getEntryTime(),"yyyy-MM-dd HH:mm");
                break;
            case 8:
                //转正时间
                msg = null == user.getPositiveTime() ? "" :
                        DateStringUtil.dateToString(user.getPositiveTime(),"yyyy-MM-dd HH:mm");
                break;
            case 9:
                msg = getValue("￥"+user.getSalary());
                break;
            case 10:
                //身份属性(在职状态)（0实习，1磨合期，2正式，3离职）
                msg = getValue(null == user.getIncumbency() ? "--" :
                        user.getIncumbency() == 0 ? "实习" :
                                user.getIncumbency() == 1 ? "磨合期" :
                                        user.getIncumbency() == 2 ? "正式" : "离职");
                break;
            case 11:
                //性别 0：男 1：女
                msg = getValue(null == user.getSex() ? "--" : user.getSex() == 0 ? "男" : "女");
                break;
            case 12:
                //员工信息状态  0:合作  1:股东
                msg = null == user.getInfoStatus() || 0 == user.getInfoStatus() ? "合作" : "股东"  ;
                break;
            case 13:
                msg = null == user.getCreateTime() ? "" :
                        DateStringUtil.dateToString(user.getCreateTime(),"yyyy-MM-dd HH:mm");
                break;
            default: msg = "";
                break;
        }
        return msg;
    }

}
