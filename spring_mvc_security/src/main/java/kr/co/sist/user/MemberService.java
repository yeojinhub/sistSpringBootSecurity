package kr.co.sist.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.sist.util.CipherUtil;

@Service
public class MemberService {
	
	@Autowired
	private CipherUtil cu;
	
	@Autowired(required =  false)
	private MemberMapper mm;

	@Transactional
	public boolean addMember(MemberDTO mDTO) {
		boolean flag = false;
		
		// 이메일 = 이메일1+domain
		mDTO.setEmail(mDTO.getEmail()+"@"+mDTO.getDomain());
		
		// 일방향 hash : 비밀번호
		mDTO.setPass(cu.sha(mDTO.getPass()));
		
		// 암호문 : 이름, 전화번호, 이메일
		mDTO.setName(cu.cipherText(mDTO.getName()));
		mDTO.setTel(cu.cipherText(mDTO.getTel()));
		mDTO.setEmail(cu.cipherText(mDTO.getEmail()));
		
		flag = mm.insertMember(mDTO) == 1;
		
		return flag;
	} //addMember
	
	/**
	 * @param lDTO
	 * @return
	 */
	public String selectLogin(LoginDTO lDTO) {
		String jsonObj = "";
		MemberDomain md = null;
		
		md = mm.selectLogin(lDTO.getId());
		if( md != null ) {
			// 비밀번호를 비교
			if(cu.shaMatches(lDTO.getPass(), md.getPass())) {
				// 복호화
				md.setName(cu.plainText(md.getName()));
				md.setTel(cu.plainText(md.getTel()));
				md.setEmail(cu.plainText(md.getEmail()));
			} else {
				// 비밀번호가 틀리면 null 설정
				md = null;
			} //end if else
		} //end if
		System.out.println(md);
		
		ObjectMapper objMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// {"resultFlag": true, 이름:값}
		map.put("resultFlag", (md != null));
		if(md != null) {
			map.put("name", md.getName());
			map.put("tel", md.getTel());
			map.put("email", md.getEmail());
		} //end if
		
		try {
			jsonObj = objMapper.writeValueAsString(map);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
		} //end try catch
		
		return jsonObj;
	} //selectLogin
	
} //class
