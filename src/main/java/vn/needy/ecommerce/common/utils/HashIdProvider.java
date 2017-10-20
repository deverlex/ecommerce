package vn.needy.ecommerce.common.utils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;

import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
public class HashIdProvider implements Serializable {

	private static final long serialVersionUID = 1452575675L;
		
	private static final String AUTH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
	private static final String DIGITS = "123456789";
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int LENGTH_DIGITS = 9;
	private static final int LENGTH_CHARS = 26;
	
	// This function use for generate Company Code
	public String generateCompanyCode() {
		Hashids hashids = new Hashids(String.valueOf(new Date()), 10, AUTH);
		return hashids.encode(new Date().getTime()).substring(0, 10);
	}
	
	public String generateOrderNumber() {
		return nextId(6);
	}
	
	public String generatePayNumber() {
		return nextId(8);
	}
	
	public String generateStoreNumber() {
		return nextId(5);
	}
	
	public String generateProductNumber() {
		return nextId(5);
	}
	
	public String generateAgreementNumber( ) {
		return nextId(4);
	}
	
	private String nextId(int length) {
		SecureRandom random = new SecureRandom();
		char[] symbols = CHARS.toCharArray();
		char[] buffChars = new char[2];
		for (int i = 0; i < 2; ++i) {
			buffChars[i] = symbols[random.nextInt(LENGTH_CHARS)];
		}
		symbols = DIGITS.toCharArray();
		char[] buffDigits = new char[length - 2];
		for (int i = 0; i < length - 2; ++i) {
			buffDigits[i] = symbols[random.nextInt(LENGTH_DIGITS)];
		}
		return String.valueOf(buffChars) + String.valueOf(buffDigits);
	}
}
