package cn.suisun.utils;

import java.security.MessageDigest;

public class MD5 {
	public static String getDigestedString(String source) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.reset();
		md5.update(source.getBytes());
		byte[] digested = md5.digest();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < digested.length; i++) {
			byte b = digested[i];
			int value = (b & 0x7F) + (b >= 0 ? 0 : 128);
			buffer.append(value >= 16 ? "" : "0");
			buffer.append(Integer.toHexString(value));
		}
		return buffer.toString();
	}
}