package in.mahesh.service;

import java.util.List;

import in.mahesh.dashboard.Dashboard;
import in.mahesh.entity.Enquiry;

public interface EnquiryService {
	
	public boolean saveEnquiry(Enquiry enquiry , Integer cId);
	
	public List<Enquiry> getEnquiries(Enquiry enquiry , Integer cId);
	
	public Enquiry editEnquiry(Integer eId);
	
	public Dashboard getDashboardInfo(Integer cId);

}
