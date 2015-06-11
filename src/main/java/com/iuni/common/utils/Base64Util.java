package com.iuni.common.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * 基于Java的Base64算法实现
 * @author CaiKe
 * @version gionee-common-1.0.0
 */
public class Base64Util {

	private static final Base64 base64 = new Base64();
	
	/**
	 * Encode
	 * @param decodeValue
	 * @return String
	 */
	public static String encode(String decodeValue) {
		return new String(base64.encode(decodeValue.getBytes()));
	}
	
	/**
	 * Encode
	 * @param decodeValue
	 * @return String
	 */
	public static String encode(byte[] decodeValue) {
		return new String(base64.encode(decodeValue));
	}
	
	/**
	 * Decode
	 * @param encodeValue
	 * @return String
	 */
	public static byte[] decode(String encodeValue) {
		return base64.decode(encodeValue.getBytes());
	}
	
	/**
	 * Decode
	 * @param encodeValue
	 * @return String
	 */
	public static String decode(byte[] encodeValue) {
		return new String(base64.decode(encodeValue));
	}
	
	public static void main(String[] args) {
//		String value = "你好123jiami";
		String value = "gNn4pD:jZPR81n:b6";
		String encodeVal = encode(value);
		String decodeVal = new String(decode(encodeVal));
		System.out.println("encodeVal: " + encodeVal + " length: " + encodeVal.length());
		System.out.println("decodeVal：" + decodeVal + " length: " + decodeVal.length());
	}
	
}
