package in.mahesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.mahesh.dashboard.Dashboard;
import in.mahesh.entity.Counsellor;
import in.mahesh.service.ImpCounsellor;
import in.mahesh.service.ImpEnquirySerive;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private ImpCounsellor cService;
	
	@Autowired
	private ImpEnquirySerive eService;
	
	@GetMapping("/")
	public String loadLoginDetails(Model model) {
		model.addAttribute("loginObj", new Counsellor());
		return "login";
	}
	
	@PostMapping("/login")
	public String handleLoginDetails(Counsellor counsellor ,HttpServletRequest req , Model model) {
		Counsellor status = cService.getCounsellor(counsellor.getEmail(), counsellor.getPassword());
		if(status != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("cId", status.getcId());
			return "redirect:/dashboard";
		}else {
			model.addAttribute("error", "Invaild Creditals");
			model.addAttribute("loginObj", new Counsellor());
			return "login";
		}
	}
	
	@GetMapping("/register")
	public String loadRegisterDetails(Model model) {
		model.addAttribute("registerObj", new Counsellor());
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegisterDetails(Model model , Counsellor counsellor) {
		boolean status = cService.saveCounsellor(counsellor);
		if(status) {
			model.addAttribute("sucess", "Account Created..");
		}else {
			model.addAttribute("error", "Account Alredy Exsist..");
		}
		model.addAttribute("registerObj", new Counsellor());
		return "register";
	}
	
	@GetMapping("/logout")
	public String loadLogout(Model model , HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/dashboard")
	public String dashboardDetails(Model model , HttpServletRequest req , Counsellor counsellor) {
		HttpSession session = req.getSession(false);
		Integer attribute = (Integer)session.getAttribute("cId");
		if(attribute==null) {
			return "redirect:/";
		}
		Dashboard dashboardInfo = eService.getDashboardInfo(counsellor.getcId());
		model.addAttribute("Dashboard", dashboardInfo);
		return "dashboard";
		
	}

}
