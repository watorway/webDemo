package com.webDemo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class DateUtil implements Converter<String, Date> {

	// 将字符串 转为Date
	public Date convert(String source) {
		try {
			return DateUtils.parseDate(source, "yyyy-MM-dd",
					"yyyy-MM-dd hh:mm:ss");
		} catch (ParseException e) {
			Logger.getLogger(this.getClass()).error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 日期转换为字符串
	 * @param date 日期
	 * @param format 日期格式
	 * @return 字符串
	 */
	public static String dateToStr(Date date, String dateType) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat date_sdf = new SimpleDateFormat(dateType);
		return date_sdf.format(date);
	}
	
	/**
	 * 日期转换为字符串("yyyy-MM-dd")
	 * @param date 日期
	 * @return 字符串
	 */
	public static String dateToStr(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy-MM-dd");
		return date_sdf.format(date);
	}

	/**
	 * 当前日期
	 * @return 系统当前时间 Date
	 */
	public static Date getDate() {
		return new Date();
	}
	
	/**
	* 得到当前日期，日期格式为yyyyMMddHHmmss
    * @return
    */
   public static String getCurrentString(){
   	String fo = "yyyyMMddHHmmss";
   	return getStrDate(new Date(),fo);
   }
   
   public static String getCurrentString2(){
	   	String fo = "yyyyMMddHHmmssSS";
	   	return getStrDate(new Date(),fo);
	   }
   
    // 日期转换为日期字符串
	public static String getStrDate(Date date, String format) {
		if(date == null || format == null){
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String strDate = dateFormat.format(date);
		return strDate;
	}
	
	/**
	 * 获取当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * @return String
	 */
	public static String fromDateH(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date());
	}
	
	/**
	 * 获取当前时间　格式：yyyy-MM-dd
	 * @return String
	 */
	public static String fromDateY(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}

	/**
	 * 当前日期
	 * @return 系统当前时间 String
	 */
	public static String getNowDateTime() {
		Date currentDate = new Date();
		SimpleDateFormat localFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentDateStr = localFormat.format(currentDate);
		return currentDateStr;
	}
	
	/**
	 * 获取前两天的日期
	 * @return 系统当前时间 String
	 */
	public static String getBeforeTwoDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2); //得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	/**
	 * 将String转换给Date
	 * @return  Date
	 */
	public static Date StringToDate(String dateStr,String formatStr){
		if(dateStr == null || formatStr == null){
			throw new IllegalArgumentException("The dateStr and the formatStr must not be null");
		}
		if (dateStr.equals("")){
			return null;
		}
		DateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将String转换给Date(yyyy-MM-dd)
	 * @return  Date
	 */
	public static Date StringToDate(String dateStr){
		DateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 把yyyy-MM-dd转成yyyymmdd格式
	public static String formatDate(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
		String sfstr = "";
		if (str != "" && str != null) {
			try {
				sfstr = sf2.format(sf1.parse(str));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return sfstr;
	}
	
	public static Date ToDate(Object o){
    	try{
    		if(o.getClass() == String.class){
    			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    			o = format.parse(o.toString());  
    			return (Date)o;
    		}
    		return o != null ? (Date)o : null;
		}catch(Exception ex){
			return null;
		}
    }
	
	public static Date ToDateTime(Object o){
    	try{
    		if(o.getClass() == String.class){
    			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			o = format.parse(o.toString());  
    			return (Date)o;
    		}
    		return o != null ? (Date)o : null;
		}catch(Exception ex){
			return null;
		}
    }
	
	/**
	 * 获取上个月的 年度加月份
	 * month: yyyyMM
	 * @param args
	 */
	public static String getPreMonth(){
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMM");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		return ft.format(c.getTime());
	}
	
	/**
	 * month: yyyy-MM
	 * @param args
	 */
	public static String getPreMonth2(){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		return ft.format(c.getTime());
	}
	
	/**
	 * 获取给定月份的年度加月份
	 * month: yyyyMM
	 * @param args
	 */
	public static String getPreMonthByGive(String date){
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMM");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(ft.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		c.add(Calendar.MONTH, -1);
		return ft.format(c.getTime());
	}
	
	/**
	 * 获取前一天的年月日
	 * month: yyyyMM
	 * @param args
	 */
	public static String getPreDayByGive(String date){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(ft.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_YEAR, -1);
		return ft.format(c.getTime());
	}
	
	/**
	 * 获取上个季度的   年度加季度
	 * month: yyyyq
	 * @param args
	 */
	public static String getPreQuarter() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String preQuarter="1";
        int year = c.get(Calendar.YEAR);
        String returnValue="";
        try {
        	if (currentMonth >= 1 && currentMonth <= 3) {
//        		preQuarter="4";
        		preQuarter="12";
            	returnValue=(year-1)+preQuarter;
            } else if (currentMonth >= 4 && currentMonth <= 6) {
//            	preQuarter="1";
            	preQuarter="03";
            	returnValue=year+preQuarter;
            } else if (currentMonth >= 7 && currentMonth <= 9) {
//            	preQuarter="2";
            	preQuarter="06";
            	returnValue=year+preQuarter;
            } else if (currentMonth >= 10 && currentMonth <= 12) {
//            	preQuarter="3";
            	preQuarter="09";
            	returnValue=year+preQuarter;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
	}
	
	/**
	 * 获取上个季度的   年度加季度
	 * month: yyyyq
	 * @param args
	 */
	public static String getPreQuarter2() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String preQuarter="1";
        int year = c.get(Calendar.YEAR);
        String returnValue="";
        try {
        	if (currentMonth >= 1 && currentMonth <= 3) {
        		preQuarter="12";
            	returnValue=(year-1)+"-"+preQuarter;
            } else if (currentMonth >= 4 && currentMonth <= 6) {
            	preQuarter="03";
            	returnValue=year+"-"+preQuarter;
            } else if (currentMonth >= 7 && currentMonth <= 9) {
            	preQuarter="06";
            	returnValue=year+"-"+preQuarter;
            } else if (currentMonth >= 10 && currentMonth <= 12) {
            	preQuarter="09";
            	returnValue=year+"-"+preQuarter;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
	}
	/**
	 * 获取本年的第一个季度
	 * month: yyyyq
	 * @param args
	 */
	public static String getOneQuarter() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        String returnValue=year+"03";
        
        return returnValue;
	}
	
	/**
	 * 获取给定时间的上个季度  年度加季度
	 * month: yyyyq
	 * @param args
	 */
	public static String getPreQuarterByGive(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String preQuarter="1";
        int year = c.get(Calendar.YEAR);
        String returnValue="";
        try {
        	if (currentMonth >= 1 && currentMonth <= 3) {
        		preQuarter="4";
            	returnValue=(year-1)+preQuarter;
            } else if (currentMonth >= 4 && currentMonth <= 6) {
            	preQuarter="1";
            	returnValue=year+preQuarter;
            } else if (currentMonth >= 7 && currentMonth <= 9) {
            	preQuarter="2";
            	returnValue=year+preQuarter;
            } else if (currentMonth >= 10 && currentMonth <= 12) {
            	preQuarter="3";
            	returnValue=year+preQuarter;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
	}
	
	/**
	 * 获取上个季度日期
	 * @param date
	 * @return
	 */
	public static String getPreQuarterByGive(String date) {
		String month = "";
		String year = "";
		if(null != date){
			year = date.substring(0, 4);
			month = date.substring(4, 6);
		}
        if("03".equals(month)){
        	year = String.valueOf(Integer.valueOf(year) - 1);
        	month = "12";
        }else if("06".equals(month)){
        	month = "03";
        }else if("09".equals(month)){
        	month = "06";
        }else if("12".equals(month)){
        	month = "09";
        }
        return year + month;
	}
	
	/**
	 * 获取上个期末季度日期
	 * @param date
	 * @return
	 */
	public static String getPreQuarterByQimo(String date) {
		String month = "";
		String year = "";
		if(null != date){
			year = date.substring(0, 4);
			month = date.substring(4, 6);
		}
		year = String.valueOf(Integer.valueOf(year) - 1);
		month = "12";
        return year + month;
	}
	
	/**
	 * 获取上个年度
	 * month: yyyy
	 * @param args
	 */
	public static String getPreYear() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        String preYear=String.valueOf(year-1);
        return preYear;
	}
	
	/**
	 * 获取给定时间上个年度
	 * month: yyyy
	 * @param args
	 */
	public static String getPreYearByGive(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        String preYear=String.valueOf(year-1);
        return preYear;
	}
	
	public static String getPreYearByGive(String date) {
		String preYear = "";
        if(null != date){
        	preYear = String.valueOf(Integer.valueOf(date) - 1);
        }else{
        	Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            preYear = String.valueOf(year-1);
        }
        return preYear;
	}
	
	/**
	 * 获取    属于当年第几周
	 * month: n
	 * @param args
	 */
	public static String getCurrentWeek() {
		Calendar calendar = Calendar.getInstance();
		//calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-7);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
		Date date=calendar.getTime();
		calendar.setTime(date);
		//calendar.setMinimalDaysInFirstWeek(7);
		String preWeek=String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
		int week=Integer.parseInt(preWeek);
		/*if(week<10){
			preWeek="0"+preWeek;
		}*/
		//为了和前台的时间控件显示的一直，都减去一周
		int newWeek=week-1;
		if(newWeek<10){
			preWeek="0"+newWeek;
		}else{
			preWeek=String.valueOf(week-1);
		}
		return preWeek;
	}
	
	/**
	 * 获取    属于当年第几周   年+周
	 * month: n
	 * @param args
	 */
	public static String getYearWeekByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		//calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-7);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
		calendar.setTime(date);
		//calendar.setMinimalDaysInFirstWeek(7);
		String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
		String preWeek=String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
		int week=Integer.parseInt(preWeek);
		/*if(week<10){
			preWeek="0"+preWeek;
		}*/
		//为了和前台的时间控件显示的一直，都减去一周
		int newWeek=week-1;
		if(newWeek<10){
			preWeek="0"+newWeek;
		}else{
			preWeek=String.valueOf(week-1);
		}
		return currentYear + preWeek;
	}
	
	/**
	 * 获取给定时间上一周  年+周
	 * month: n
	 * @param args
	 */
	public static int getPreYearWeek() {
		String weekd=getYearWeekByDate(new Date());
		if("01".equals(weekd.substring(4, 6))){
			int week=Integer.valueOf(getYearWeekByDate(new Date()));
			return week;
		}
		int newweek=Integer.valueOf(getYearWeekByDate(new Date())) - 1;
		return newweek;
	}
	
	
	public static String getPreWeekByGive(String date) {
//		Calendar calendar = Calendar.getInstance();
//		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String preWeek = "";
		if(null != date){
			if(date.length() > 1){
				preWeek = String.valueOf(Integer.valueOf(date.substring(0, 2)) - 1);
			}else{
				preWeek = String.valueOf(Integer.valueOf(date.substring(0, 1)) - 1);
			}
		}else{
			
		}
		return preWeek;
	}
	
	public static String getNyr(String date) {
		String returnDate=date.substring(0, 4)+"年"+date.substring(4,6)+"月"+date.substring(6, 8)+"日";
		return returnDate;
	}
	
	/**
	 * 获取上周一到周日的 日期
	 * @return
	 */
    public static Map<String,String> getDateFromWeek(){
    	Map<String,String> resultMap=new HashMap<String,String>();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = -1;
        String monday;
        String sunday;
        cal.add(Calendar.DATE, n*7);
        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        monday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        int n2 = 1;
        cal.add(Calendar.DATE, n2*7);
        cal2.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        sunday = new SimpleDateFormat("yyyyMMdd").format(cal2.getTime());
        resultMap.put("beginDate", monday);
        resultMap.put("endDate", sunday);
    	return resultMap;
    }
	
	public static void main(String[] args) {
		System.out.println(getPreYearWeek());
		//String year = String.valueOf(getPreYearWeek()).substring(0, 4);
		//String preWeek = String.valueOf(getPreYearWeek()).substring(4, 6);
		System.out.println(getPreMonth());
		
		Map<String,String> resultMap=getDateFromWeek();
		System.out.println(resultMap.get("beginDate"));
		System.out.println(resultMap.get("endDate"));
	}

}