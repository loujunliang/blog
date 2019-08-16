package com.hcjava.util;

import java.security.MessageDigest;
import java.util.UUID;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 1.生成UUID 2.密码加密
 * 
 * @author huachuang
 *
 */
public class NoteUtil {

	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	public static String md5(String src) throws Exception {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(src.getBytes());
			// 将MD5处理结果利用base64转成字符串
			String string = Base64.encode(digest);
			return string;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(createUUID());
		System.out.println(md5("123456"));
	}
}
