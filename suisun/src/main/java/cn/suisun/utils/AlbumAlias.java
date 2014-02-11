package cn.suisun.utils;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-1-13 Time: 下午3:56 To
 * change this template use File | Settings | File Templates.
 */
public class AlbumAlias {
	public static void main(String[] args) {
		System.out.println(getAlbumAlist());
	}

	public static String getAlbumAlist() {
		return shorUrl(getUUID());
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return str + "," + temp;
	}

	private static String shorUrl(String str) {

		char[] base32 = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5' };

		String hex = MD5(str);
		int hexLen = hex.length();
		int subHexLen = hexLen / 8;

		String out = "";
		for (int i = 0; i < subHexLen; i++) {
			String subHex = hex.substring(i * 8, (i + 1) * 8);
			int a = 0x3FFFFFFF & (1 * Long.valueOf(subHex, 16).intValue());

			for (int j = 0; j < 6; j++) {
				int val = 0x0000001F & a;
				out += base32[val];
				a = a >> 5;
			}
		}
		// System.out.println(out + "  " + out.length());

		char[] output = out.toCharArray();
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			sb.append(output[random.nextInt(out.length() - 1)]);

		}

		return sb.toString();
	}

	private final static char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public final static String MD5(String s) {

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
