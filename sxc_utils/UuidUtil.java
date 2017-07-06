package com.xz.base.utils;

import java.util.UUID;

public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
	
	public static String getSerialNumber(){
		 return MemberUUIDMake.produceUID();
	}
}

