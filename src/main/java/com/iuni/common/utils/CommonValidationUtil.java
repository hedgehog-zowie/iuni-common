package com.iuni.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: CommonValidationUtil
 * @Description: 数据格式校验类 
 * @author menglei
 * @date 2011-8-24 上午10:52:17 
 * @version gionee-common-1.0.0
 */
public class CommonValidationUtil {
	
    /**
     * 得到字符串长度
     * @Title: strLength 
     * @param  String 
     * @return int    返回类型 
     * @throws
     */
	public static int strLength(String str) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < str.length(); i++) {
			String temp = str.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}
	
	/**
	 * 校验手机
	 * @Title: isMobileNO 
	 * @param  mobile 
	 * @return boolean    返回类型 
	 * @throws
	 */
	public static boolean isMobileNO(String mobile){
        String str = "^(1[3,4,5,8])\\d{9}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(mobile);     
        return m.matches();     
    } 
   
	/**
	 * 校验邮箱
	 * @Title: isEmail 
	 * @param  email 
	 * @return boolean    返回类型 
	 * @throws
	 */
	public static boolean isEmail(String email){     
		String str = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile(str);     
        Matcher m = p.matcher(email);
        return m.matches();     
    }
	   
	/**
	 * 校验IP地址
	 * @Title: isIp 
	 * @param  ip
	 * @return boolean    返回类型 
	 * @throws
	 */
	public static boolean isIp(String ip){     
		String str="([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern p = Pattern.compile(str);     
        Matcher m = p.matcher(ip);
        return m.matches();     
    } 
	   
	/**
	 * 校验数字：检查输入字符串，是否是一数值型,包括：整数和浮点数
	 * @Title: isNumber  
	 * @param  str
	 * @return boolean    返回类型 
	 * @throws
	 */
	public static boolean isNumber(String str){
		Pattern p = Pattern.compile("\\d+(.\\d+)??");     
        Matcher m = p.matcher(str);
        return m.matches();  
    } 
	
	/**
	 * 校验数字:检查输入字符串，是否是一数值型,包括：大于0的整数
	 * @Title: isInteger 
	 * @param  str
	 * @return boolean    返回类型 
	 * @throws
	 */
	public static boolean isInteger(String str){
		Pattern p = Pattern.compile("^[1-9]\\d*$");     
        Matcher m = p.matcher(str);
        return m.matches();  
    } 
    
    /**
     * 校验对象取出空格后的长度范围
     * @param obj
     * @param minLen
     * @param maxLen
     * @return
     */
    public static boolean checkExactingLength(Object obj,int minLen, int maxLen){
    	if(null == obj){
    		return true;    		
    	}
    	String str = String.valueOf(obj).trim();
    	int len = strLength(str);
    	if(len < minLen || len > maxLen){
    		return false;
    	} 	
    	return true;
    }
    
    /**
     * 校验对象取出空格后是否为空
     * @param str
     * @param minLen
     * @param maxLen
     * @return
     */
    public static boolean isEmpty(Object obj){
    	if(null == obj){
    		return true;
    	}
    	String str = String.valueOf(obj).trim();
    	if(str.length()==0){
    		return true;
    	}
    	return false;
    }
    
    
    /**
     * 判断两个对象是否相等(null = "")
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Object a, Object b){
    	if(isEmpty(a) && isEmpty(b)){
    		return true;
    	}else if(null != a && null != b && (a==b || a.equals(b))){
    		return true;
    	}
    	return false;
    }
    
    public static void main(String arg[]){
    	//System.out.println(CommonValidationUtil.isEmail("aaa@qq.com"));
    	//System.out.println(CommonValidationUtil.isEmail("aaa@vip.qq.com"));
    	//System.out.println(CommonValidationUtil.isEmail("aa-a-@vip.qq.com"));
    	//System.out.println(CommonValidationUtil.isEmail("aaa_2345_-34aa@vip-.qq.com-_sss"));
    	//System.out.println(CommonValidationUtil.isEmail("kevin.huo@xiu.com"));
    	System.out.println(CommonValidationUtil.checkExactingLength("11",0,2));
    	System.out.println(CommonValidationUtil.checkExactingLength(11,0,2));
    }

}
