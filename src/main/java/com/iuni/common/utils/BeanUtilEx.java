package com.iuni.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

/**
 * 对BeanUtils.copyProperties(target, source)方法的封装
 * @ClassName: BeanUtilEx 
 * @Description: 解决 copy时封装类型初始化的问题
 * @author menglei
 * @date 2013-02-21 下午14:59:38 
 * @version gionee-common-1.0.0
 */
public class BeanUtilEx extends BeanUtils {

	private BeanUtilEx() {
		
	}

	static {
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
		ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
	}

	public static void copyProperties(Object target, Object source) {
		try {
			new BeanUtilEx();
			BeanUtils.copyProperties(target, source);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void main(String[] args) {
		String a = "-1";
		int b = Integer.parseInt(a);
		System.out.println("a="+a+", b="+b);
	}

}
