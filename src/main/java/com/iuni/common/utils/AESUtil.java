package com.iuni.common.utils;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * AES加解密编码工具类
 * @author CaiKe
 * @version gionee-common-1.0.0
 */
public class AESUtil {

	public static final String AES = "AES";
	
	/**
	 * Key Generation
	 * 直接生成密钥的原生byte[]
	 * @param seed
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static byte[] genAesKey(String seed) throws GeneralSecurityException {
		byte[] rawKeyData = null;
		
		SecureRandom sr = null;
		if (StringUtils.isNotBlank(seed)) {
			sr = new SecureRandom(seed.getBytes());
		} else {
			sr = new SecureRandom();
		}
		KeyGenerator keyGen = KeyGenerator.getInstance(AES);
		keyGen.init(128, sr);
		SecretKey sKey = keyGen.generateKey();
		rawKeyData = sKey.getEncoded();
		
		return rawKeyData;
	}
	
	/**
	 * Key Generation
	 * 使用16进制转换byte[]
	 * @param seed
	 * @return String
	 * @throws GeneralSecurityException
	 */
	public static String genKeyByHex(String seed)
			throws GeneralSecurityException {
		return byte2hex(genAesKey(seed));
	}
	
	/**
	 * Key Generation
	 * 使用BASE64转换byte[]
	 * @param seed
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static String genKeyByBase64(String seed) 
			throws GeneralSecurityException {
		return Base64Util.encode(genAesKey(seed));
	}
	
	/**
	 * 
	 * @param rawKeyData
	 * @param decryptData
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static byte[] encrypt(byte[] rawKeyData, byte[] decryptData) throws GeneralSecurityException {
		SecureRandom sr = new SecureRandom();
		SecretKeySpec key = new SecretKeySpec(rawKeyData, AES);
		Cipher cipher = Cipher.getInstance(AES);
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		byte[] encryptedData = cipher.doFinal(decryptData);
		
		return encryptedData;
	}
	
	/**
	 * Encrypt Value Return String
	 * 使用16进制转换加密结果
	 * 使用Key的二进制数组
	 * @param rawKeyData
	 * @param encryptValue
	 * @return String
	 * @throws GeneralSecurityException
	 */
	public static String getEncryptValueByHex(String rawKeyData, String encryptValue)
			throws GeneralSecurityException {
		return byte2hex(encrypt(hex2byte(rawKeyData.getBytes()), encryptValue.getBytes()));
	}
	
	/**
	 * Encrypt Value Return String
	 * 使用Base64转换加密结果
	 * 使用Base64转换Key
	 * @param rawKeyData
	 * @param encryptValue
	 * @return String
	 * @throws GeneralSecurityException
	 */
	public static String getEncryptValueByBase64(String rawKeyData, String encryptValue) 
			throws GeneralSecurityException {
		return Base64Util.encode(encrypt(Base64Util.decode(rawKeyData), encryptValue.getBytes()));
	}

	
	/**
	 * 
	 * @param rawKeyData
	 * @param encryptData
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static byte[] decrypt(byte[] rawKeyData, byte[] encryptData) throws GeneralSecurityException {
		SecureRandom sr = new SecureRandom();
		SecretKeySpec key = new SecretKeySpec(rawKeyData, AES);
		Cipher cipher = Cipher.getInstance(AES);
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		byte[] decryptedData = cipher.doFinal(encryptData);
		
		return decryptedData;
	}
	
	/**
	 * Decrypt Value Return String
	 * 使用16进制编码
	 * @param rawKeyData
	 * @param decryptValue
	 * @return String
	 * @throws GeneralSecurityException
	 */
	public static String getDecryptValueByHex(String rawKeyData, String decryptValue)
			throws GeneralSecurityException {
		return new String(decrypt(hex2byte(rawKeyData.getBytes()),
				hex2byte(decryptValue.getBytes())));
	}
	
	/**
	 * Decrypt Value Return String
	 * 使用Base64编码
	 * @param rawKeyData
	 * @param decryptValue
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static String getDecryptValueByBase64(String rawKeyData, String decryptValue) 
			throws GeneralSecurityException {
		return new String(decrypt(Base64Util.decode(rawKeyData),
				Base64Util.decode(decryptValue)));
	}
	
	/**
	 * 一个字节的数转成16进制字符串
	 * 
	 * @param b
	 * @return String
	 */
	public static String byte2hex(byte[] byteArray) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < byteArray.length; n++) {
			// 整数转成十六进制表示
			stmp = (Integer.toHexString(byteArray[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // 转成大写
	}

	/**
	 * 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
	 * 
	 * @param byteArray
	 * @return
	 */
	public static byte[] hex2byte(byte[] byteArray) {
		if ((byteArray.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[byteArray.length / 2];
		for (int n = 0; n < byteArray.length; n += 2) {
			String item = new String(byteArray, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}
	
	public static void main(String[] args) {
		String seed = "wanjiquan2013s";
		try {
			String key16 = genKeyByHex(seed);
			String key64 = genKeyByBase64(seed);
			System.out.println("key16: " + key16 + " key64: " + key64);
			String e1 = getEncryptValueByBase64(key64, "352212046606773");
			String d1 = getDecryptValueByBase64(key64, e1);
			System.out.println(e1);
			System.out.println(d1);
			String e2 = getEncryptValueByHex(key16, "352212046606773");
			String d2 = getDecryptValueByHex(key16, e2);
			System.out.println(e2);
			System.out.println(d2);
			
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} 
	}
	
}
