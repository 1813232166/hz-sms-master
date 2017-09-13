package com.hzdjr.hzwd.modules.rebate.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESUtil {
	public static final String dKey = "278e60fc68fd967b106e7fd9f081ba8f";
	public static String enCrypt(String content, String strKey) throws Exception {
		String str = content;

		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(strKey.getBytes());
		keygen.init(128, secureRandom);
		SecretKey desKey = keygen.generateKey();
		Cipher c = Cipher.getInstance("AES");

		c.init(1, desKey);

		byte[] cByte = c.doFinal(str.getBytes("UTF-8"));

		return parseByte2HexStr(cByte);
	}

	public static String deCrypt(String src, String strKey) throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");

		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(strKey.getBytes());
		keygen.init(128, secureRandom);

		SecretKey desKey = keygen.generateKey();
		Cipher c = Cipher.getInstance("AES");

		c.init(2, desKey);

		byte[] encrytByte = parseHexStr2Byte(src);
		byte[] cByte = c.doFinal(encrytByte);

		return new String(cByte, "UTF-8");
	}

	public static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; ++i) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; ++i) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}