package kr.co.sist.user;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("memberDomain")
@Getter
@Setter
@ToString
public class MemberDomain {
	
	private String id, pass, name, birth, tel, email, domain, useEmail, gender, zipcode, addr, addr2, intro, ip;
	private Date input_date;	

} //class
