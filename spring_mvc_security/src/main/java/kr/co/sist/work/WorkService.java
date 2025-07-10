package kr.co.sist.work;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WorkService {
	
	public String bcrypt(String pass) {
		String sha="";
		
		//1. PasswordEncoder 생성
		PasswordEncoder pe = new BCryptPasswordEncoder();
		//2. 일방향 해쉬 생성
		sha = pe.encode(pass);
		System.out.println(sha);
		
		//비교하기
		String temp="$2a$10$.IgPdlJVtjP3mWbUcM03guJKS2MkiCDmK21oHYlb5UcKC80zZ/dAi";
		System.out.println(pe.matches(pass, temp));
		
		return sha;
	} //bcrypt
	
	
	public String cipher(String email) {
		String key="sist123456789";
		// 16진수로 변환가능(A-F) 8byte의 문자열
		String salt="f1f1f1f1f1f1f1f1";
		
		String cipherText="";
		
		// 1. 암호화 객체 생성
		TextEncryptor te = Encryptors.text(key, salt);
		
		// 2. 평문 -> 암호문 만들기
		cipherText= te.encrypt(email);
		
		return cipherText;
	} //cipher
	
	public String plain(String cipherEmail) {
		String key="sist123456789";
		// 16진수로 변환가능(A-F) 8byte의 문자열
		String salt="f1f1f1f1f1f1f1f1";
		
		String plainText="";
		
		// 1. 암호화 객체 생성
		TextEncryptor te = Encryptors.text(key, salt);
		
		// 2. 암호문 -> 평문 만들기
		plainText= te.decrypt(cipherEmail);
		
		return plainText;
	} //plain
	
} //class
