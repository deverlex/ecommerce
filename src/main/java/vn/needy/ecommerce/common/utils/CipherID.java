package vn.needy.ecommerce.common.utils;

import org.hashids.Hashids;

public class CipherID {
	
	private static final String HASH_AUTH = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	private static Hashids hashids = new Hashids("thisIsSecret", 16, HASH_AUTH);
	public static synchronized String encrypt(long id) {
		return hashids.encode(id);
	}
	
	public static synchronized long decrypt(String id) {
		return hashids.decode(id)[0];
	}
}
