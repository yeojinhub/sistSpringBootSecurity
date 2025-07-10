package kr.co.sist.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CipherUtil {

	@Value("${prj.key}")
	private String key;
	// 16진수로 변환가능한 8byte 문자열
	@Value("${prj.salt}")
	private String salt;
	
	/**
	 * 일방향 해쉬
	 * @param plainText
	 * @return sha
	 */
	public String sha(String plainText) {
		String sha="";
		
		PasswordEncoder pe = new BCryptPasswordEncoder();
		sha = pe.encode(plainText);
		
		return sha;
	} //sha
	
	/**
	 * 일방향 해쉬 sha와 값이 같은지 확인
	 * @param plainText
	 * @param bcryptText
	 * @return
	 */
	public boolean shaMatches(String plainText, String bcryptText) {
		boolean flag = false;
		
		PasswordEncoder pe = new BCryptPasswordEncoder();
		flag = pe.matches(plainText, bcryptText);
		
		return flag;
	} //shaMatches
	
	public String cipherText(String plainText) {
		String cipherText="";
		
		System.out.println(key+"/"+salt);
		TextEncryptor te = Encryptors.text(key, salt);
		cipherText = te.encrypt(plainText);
		
		return cipherText;
	} //cipherText
	
	public String plainText(String cipherText) {
		String plainText="";
		
		System.out.println(key+"/"+salt);
		TextEncryptor te = Encryptors.text(key, salt);
		plainText = te.decrypt(cipherText);
		
		return plainText;
	} //plainText
	
} //class
