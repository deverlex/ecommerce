package vn.needy.ecommerce.common.utils;

import org.hashids.Hashids;

public class CipherID {
	
	private static final String HASH_AUTH = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final String HASH_TEXT = "ABCDEFGHIJKLMNPQRSTUVWXYZ1234567890";
	
	private static Hashids hashids = new Hashids("thisIsSecret", 14, HASH_AUTH);
	private static Hashids hashtext = new Hashids("thisIsSecret", 14, HASH_TEXT);
	
	public static String encrypt(long id) {
		return hashids.encode(id);
	}
	
	public static long decrypt(String id) {
		return hashids.decode(id)[0];
	}
	
	public static String idToCode(long id) {
		return hashtext.encode(id);
	}
	
	public static long codeToId(String code) {
		return hashtext.decode(code)[0];
	}
}
