package in.mahesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.mahesh.entity.Enquiry;
import in.mahesh.service.ImpCounsellor;
import in.mahesh.service.ImpEnquirySerive;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private ImpEnquirySerive eService;
	
	@Autowired
	private ImpCounsellor cService;
	
	@GetMapping("/enquiry")
	public String loadEquniriesDetails(Model model) {
		model.addAttribute("enquiryObj", new Enquiry());
		return "enquiry";
	}
	
	@PostMapping("/enquiry")
	public String handleEquniriesDetails(Model model ,Enquiry enquiry ,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Integer cId = (Integer) session.getAttribute("cId");
		boolean status = eService.saveEnquiry(enquiry, cId);
		if(status) {
			model.addAttribute("sucess", "Saved....");
			model.addAttribute("enquiryObj", new Enquiry());
			return "enquiry";
		}else {
			model.addAttribute("error", "NotSaved....");
			model.addAttribute("enquiryObj", new Enquiry());
			return "enquiry";
		}
		
	}
	
	
	@GetMapping("/enquiries")
	public String getAllDetails(Model model , HttpServletRequest req ,Enquiry enquiry ) {
		HttpSession session = req.getSession(false);
		Integer cId =(Integer) session.getAttribute("cId");
		System.out.println(cId);
		List<Enquiry> enquiries = eService.getEnquiries(enquiry, cId);
		model.addAttribute("allEnquries", enquiries);
		model.addAttribute("enquiryObj", new Enquiry());
		return "viewenquiries";
	}
	
	@PostMapping("/enquiries")
	public String filterenquiries(Model model ,HttpServletRequest req ,Enquiry enquiry) {
		HttpSession session = req.getSession(false);
		Integer cId =(Integer) session.getAttribute("cId");
		List<Enquiry> enquiries = eService.getEnquiries(enquiry, cId);
		model.addAttribute("allEnquries", enquiries);
		return "viewenquiries";
		
	}
	
	
	@GetMapping("/edit")
	public String editAndUpdate(@RequestParam("eId") Integer eId , Model model) {
		Enquiry editEnquiry = eService.editEnquiry(eId);
		model.addAttribute("enquiryObj",editEnquiry);
		return "enquiry";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	    
	
}
