package cn.itcast.oa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密类（封装jdk自带的md5加密方法）
 *
 */
public class MD5Util {

	/**
	 * MD5加密字符串
	 * 
	 * @param source
	 *            待加密字符串
	 * @return 加密之后的字符串
	 */
	public static String encrypt(String source) {
		return encodeMd5(source.getBytes());
	}

	private static String encodeMd5(byte[] source) {
		try {
			return encodeHex(MessageDigest.getInstance("MD5").digest(source));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	private static String encodeHex(byte[] bytes) {
		StringBuffer buffer = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buffer.append("0");
			}
			buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(encrypt("admin"));
		System.out.println(encrypt("21232f297a57a5a743894a0e4a801fc3"));
	}
}
