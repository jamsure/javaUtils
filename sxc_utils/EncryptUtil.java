package com.xz.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

public class EncryptUtil {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String password,String salt) {
		if (StringUtils.isBlank(password)) {
			return null;
		}
		byte[] hashPassword = Digests.sha1(password.getBytes(), Encodes.decodeHex(salt), HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);

	}
	
	
	public static String getSalt(){
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		return Encodes.encodeHex(salt);
	}

}
