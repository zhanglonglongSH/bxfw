/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.util.DateUtil
* @描          述: 日期工具类
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
	
	public static Map<String,Boolean> tradingDayMap=new HashMap<String, Boolean>();
	public static String startDate="2013-01-01";
	public static String endDate="2020-12-31";
	/**
	 * @Title: getTradeDayDate、
	 * @Description:获取第几个交易日
	 * @param date
	 * @return
	 * @return: String
	 */
	public static String getTradeDay(String dateStr,int n){
		if(tradingDayMap.size()==0){
			return dateStr;
		}
		int _n=Math.abs(n);
		int symbol=1;
		if(n>0){
			symbol=1;
		}else if(n<0){
			symbol=-1;
		}
		int i=0;
		if(tradingDayMap.containsKey(dateStr)){
			i=1;
		}
		for(;i<_n;i++){
			if(i>0){
				dateStr=getDayDate(dateStr, symbol);
			}
			while(!tradingDayMap.containsKey(dateStr)){
				dateStr=getDayDate(dateStr, symbol);
			}
		}
		return dateStr;
	}
	/**
	 * @Title: getNaturalDay、
	 * @Description:获取第几个自然日
	 * @param dateStr
	 * @param n
	 * @return
	 * @return: String
	 */
	public static String getNaturalDay(String dateStr,int n){
		int _n=Math.abs(n);
		int symbol=0;
		if(n>0){
			symbol=n-1;
		}else if(n<0){
			symbol=n+1;
		}
		dateStr=getDayDate(dateStr, symbol);
		return dateStr;
	}
	public static String dateFormat="yyyy-MM-dd HH:mm:ss";
	/*
	 * 名称：strToDate 功能：将指定的字符串转换成日期 输入：aStrValue: 要转换的字符串; aFmtDate: 转换日期的格式,
	 * 默认为:"yyyy/MM/dd" aDteRtn: 转换后的日期
	 */
	public static Date strToDate(String aStrValue, String aFmtDate)
			throws ParseException {
		Date aDteRtn = Calendar.getInstance().getTime();
		// System.out.println(Calendar.getInstance().getTime());
		if (aFmtDate.length() == 0) {
			aFmtDate = "yyyyMMddhhmmss";
		}
		SimpleDateFormat fmtDate = new SimpleDateFormat(aFmtDate);

		try {
			aDteRtn.setTime(fmtDate.parse(aStrValue).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aDteRtn;
	}

	public static Date stringToDate(String str) {
		Date return_date = null;
		String format = "";
		if (str.length() > 16) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (str.length() > 10) {
			format = "yyyy-MM-dd HH:mm";
		} else {
			format = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return_date = sdf.parse(str);
		} catch (ParseException e) {

			e.getMessage();
			return null;
		}
		return return_date;
	}

	// ***************************************************
	// 名称：dateToStr
	// 功能：将指定的日期转换成字符串
	// 输入：aDteValue: 要转换的日期;
	// aFmtDate: 转换日期的格式, 默认为:"yyyy-MM-dd"
	// 输出：
	// 返回：转换之后的字符串
	// ***************************************************
	public static String dateToStr(Date aDteValue, String aFmtDate) {
		String strRtn = null;

		if (aFmtDate.length() == 0) {
			aFmtDate = "yyyy-MM-dd";
		}
		SimpleDateFormat fmtDate = new SimpleDateFormat(aFmtDate);
		// Format fmtDate = new SimpleDateFormat(aFmtDate);
		// try {
		strRtn = fmtDate.format(aDteValue);
		// } catch (Exception e) {
		// return "无录入时间";
		// }
		return strRtn;
	}

	public static Date getThisWeekBegin() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.DAY_OF_WEEK, 1);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		return beginTimecal.getTime();
	}

	public static Date getLastWeekBegin() {
		Calendar beginTimecal = Calendar.getInstance();
		beginTimecal.set(Calendar.WEEK_OF_MONTH,
				Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) - 1);
		beginTimecal.set(Calendar.DAY_OF_WEEK, 1);
		beginTimecal.set(Calendar.HOUR_OF_DAY, 0);
		beginTimecal.set(Calendar.MINUTE, 0);
		beginTimecal.set(Calendar.SECOND, 0);
		return beginTimecal.getTime();
	}

	public static Date getLastWeekEnd() {
		Calendar endTimecal = Calendar.getInstance();
		endTimecal.set(Calendar.WEEK_OF_MONTH,
				Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) - 1);
		endTimecal.set(Calendar.DAY_OF_WEEK, 7);
		endTimecal.set(Calendar.HOUR_OF_DAY, 23);
		endTimecal.set(Calendar.MINUTE, 59);
		endTimecal.set(Calendar.SECOND, 59);
		return endTimecal.getTime();

	}

	/**
	 * 取得今天的最后一个时刻
	 * 
	 * @return 今天的最后一个时刻
	 */
	public static Date currentEndDate() {
		return getEndDate(new Date());
	}

	/**
	 * 取得今天的第一个时刻
	 * 
	 * @return 今天的第一个时刻
	 */
	public static Date currentStartDate() {
		return getStartDate(new Date());
	}

	/**
	 * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的起始时间
	 */
	public static Date getStartDate(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的结束时间
	 */
	public static Date getEndDate(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	public static String getEarlyOneMonthFromNow(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		return dateToStr(cal.getTime(), "");
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return dateToStr(cal.getTime(), "");
	}

	public static String dateToWeek(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		String week = "";
		try {
			c.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return "日期错误";
		}
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			week = "星期日";
			break;
		case 2:
			week = "星期一";
			break;
		case 3:
			week = "星期二";
			break;
		case 4:
			week = "星期三";
			break;
		case 5:
			week = "星期四";
			break;
		case 6:
			week = "星期五";
			break;
		default:
			week = "星期六";
			break;
		}
		return week;
	}

	public static String getToWeek() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String newDate="";
        Date de=null;
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return "日期错误";
		}
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			c.add(Calendar.DATE, -2);//周日
			de = c.getTime();
			newDate=format.format(de);
			break;
		case 7:
			c.add(Calendar.DATE, -1);//周六
			de = c.getTime();
			newDate=format.format(de);
			break;
		case 2:
			c.add(Calendar.DATE, -3);//周一
			de = c.getTime();
			newDate=format.format(de);
			break;
		default:
			c.add(Calendar.DATE, -1);
			de = c.getTime();
			newDate=format.format(de);
			break;
		}
		return newDate;
	}
	
	/**
	 * @Title: getDayDate、
	 * @Description:根据传入的日期计算前几天，后几天
	 * @param date
	 * @param n
	 * @return
	 * @return: String
	 */
	public static String getDayDate(String dateStr,int n){
		Date date=stringToDate(dateStr);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		return dateToStr(calendar.getTime(), "yyyy-MM-dd");
	}
	public static String getDayDateTime(String dateStr,int n){
		Date date=stringToDate(dateStr);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		return dateToStr(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @Title: getDayDate、
	 * @Description:根据传入的日期计算前几天，后几天
	 * @param n
	 * @return
	 * @return: String
	 */
	public static String getDayDate(int n){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, n);
		return dateToStr(calendar.getTime(), "yyyy-MM-dd");
	}
	/**
	 * @Title: getMinuteDate、
	 * @Description:
	 * @param dateStr
	 * @param n
	 * @return
	 * @return: String
	 */
	public static String getMinuteDate(String dateStr,int n){
		return getMinuteDate(dateStr,n,"yyyy-MM-dd HH:mm");
	}
	public static String getMinuteDate(String dateStr,int n,String format){
		Date date=stringToDate(dateStr);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, n);
		return dateToStr(calendar.getTime(), format);
	}
	public static void main(String[] args) {
		// System.out.println("this weekbegin--" + DateUtil.getThisWeekBegin());
		// System.out.println("last weekbegin--" + DateUtil.getLastWeekBegin());
		// System.out.println("last weekend--" + DateUtil.getLastWeekEnd());
		// System.out.println("getCurrentDate" + DateUtil.getCurrentDate());
		// System.out.println("EarlyOneMonthFromNow--"
		// + DateUtil.getEarlyOneMonthFromNow(new Date()));
		// System.out.println(DateUtil.dateToWeek("2008-09-25"));
		System.out.println(getMinuteDate("1970-01-01 00:00:00",-2,"yyyy-MM-dd HH:mm:ss"));
		String date=getCurrentDate();
		System.out.println(getTradeDay(date, 2));
	}

}
