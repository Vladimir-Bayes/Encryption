package com.terabits.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

/** 
 * @author ����Vladimir E-mail: gyang.shines@gmail.com
 * @version ����ʱ�䣺2017��12��27�� ����4:39:12 
 * ��˵�� 
 */  
public class Providertest {

	@SuppressWarnings("null")
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println("Hello world");
		//		Provider provider = null;
		//		provider.getName();
		//		provider.getVersion();
		//		provider.getInfo();
		//		provider.toString();

		//		Security.getProperty(key);
//		for (Provider provider2 : Security.getProviders()) {
//			System.out.println(provider2);
//			for (Map.Entry<Object, Object> entry : provider2.entrySet()) {
//				System.out.println("\t"+entry.getKey());
//			}
//		}

//		MessageDigest.getInstance("MD5");

		byte[] input = "sha".getBytes();
		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(input);
		byte[] output = sha.digest();
		output = Base64.getEncoder().encode(output);
		System.out.println(new String(output));
	}

}
