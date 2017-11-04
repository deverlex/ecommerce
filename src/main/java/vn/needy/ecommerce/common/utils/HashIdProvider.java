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
		Hashids hashids = new Hashids(String.valueOf(new Date()), 12, AUTH);
		return hashids.encode(new Date().getTime()).substring(0, 12);
	}
	
	public String generateBudgetNumber() {
		Hashids hashids = new Hashids(String.valueOf(new Date()), 12, AUTH);
		return hashids.encode(new Date().getTime()).substring(0, 12);
	}
	
	public String generateOrderNumber() {
		return nextId(4, 10);
	}
	
	public String generatePayNumber() {
		return nextId(4, 8);
	}
	
	public String generateStoreNumber() {
		return nextId(3, 6);
	}
	
	public String generateProductNumber() {
		return nextId(3, 6);
	}
	
	public String generateAgreementNumber( ) {
		return nextId(4, 8);
	}
	
	private String nextId(int sizeCharacters, int sizeDigits) {
		SecureRandom random = new SecureRandom();
		char[] symbols = CHARS.toCharArray();
		char[] buffChars = new char[sizeCharacters];
		for (int i = 0; i < sizeCharacters; ++i) {
			buffChars[i] = symbols[random.nextInt(LENGTH_CHARS)];
		}
		symbols = DIGITS.toCharArray();
		char[] buffDigits = new char[sizeDigits];
		for (int i = 0; i < sizeDigits; ++i) {
			buffDigits[i] = symbols[random.nextInt(LENGTH_DIGITS)];
		}
		return String.valueOf(buffChars) + String.valueOf(buffDigits);
	}
	
	
}
