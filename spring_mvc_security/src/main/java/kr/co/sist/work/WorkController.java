package kr.co.sist.work;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class WorkController {

	@Autowired
	private WorkService ws;
	
	@RequestMapping(value="/", method = { GET, POST })
	public String index() {
		return "index";
	} //index
	
	@PostMapping("/sha")
	public String sha(Model model, String pass) {
		String encodePass = ws.bcrypt(pass);
		System.out.println("원본 : "+pass);
		System.out.println("해쉬 : "+encodePass);
		
		model.addAttribute("result", encodePass);
		
		return "shaResult";
	} //sha
	
	
	@PostMapping("/cipher")
	public String cipher(String email, Model model) {
		String cipher = ws.cipher(email);
		
		model.addAttribute("cipherEmail", cipher);
		model.addAttribute("email", email);
		
		return "cipherResult";
	} //cipher
	
	@PostMapping("/plain")
	public String plain(@RequestParam(name = "email") String cipherEmail, Model model) {
		String plainEmail = ws.plain(cipherEmail);
		
		model.addAttribute("plainEmail", plainEmail);
		model.addAttribute("cipherEmail", cipherEmail);
		
		return "plainResult";
	} //plain
	
} //class
