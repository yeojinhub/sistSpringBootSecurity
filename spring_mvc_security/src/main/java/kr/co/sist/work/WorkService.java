package kr.co.sist.work;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
} //class
