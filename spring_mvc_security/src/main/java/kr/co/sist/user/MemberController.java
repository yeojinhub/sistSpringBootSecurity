package kr.co.sist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@SessionAttributes("session_id")
@Controller
public class MemberController {
	
	@Autowired(required = false)
	private MemberService ms;

	@GetMapping("/user/memberFrm")
	public String joinFrm() {
		return "user/joinFrm";
	} //joinFrm
	
	@PostMapping("/user/memberProcess")
	public String joinFrmProcess(MemberDTO mDTO, HttpServletRequest request, Model model) {
		// IP 설정
		mDTO.setIp(request.getRemoteAddr());
		
		model.addAttribute("addResult", ms.addMember(mDTO));
		model.addAttribute("id", mDTO.getId());
		model.addAttribute("name", mDTO.getName());
		
		return "user/joinResult";
	} //joinFrmProcess
	
	@GetMapping("/user/login")
	public String loginFrm(HttpSession session) {
		String id=(String)session.getAttribute("session_id");
		System.out.println("----loginFrm----"+id);
		
		String moveURL = "user/loginFrm";
		if(id != null) {
			moveURL = "index";
		} //end if
		
		System.out.println("----loginFrm----"+moveURL);
		
		return moveURL;
	} //loginFrm
	
	@PostMapping("/user/loginProcess")
	// ViewResolver 를 거치지 않고 직접 응답(Model 실행되지 않음 > HttpSession 실행)
	@ResponseBody
	public String loginProcess(LoginDTO lDTO, HttpSession session) {
		System.out.println("----------------loginProcess---------------");
		
		String jsonObj = ms.selectLogin(lDTO);
		if( jsonObj.contains("true")) {
			session.setAttribute("session_id", lDTO.getId());
		} //end if
		System.out.println(jsonObj);
		
		return jsonObj;
	} //loginProcess
	
} //class
