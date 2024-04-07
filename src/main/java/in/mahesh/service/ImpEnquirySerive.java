package in.mahesh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;

import in.mahesh.Repo.CounsellorRepo;
import in.mahesh.Repo.EnquiryRepo;
import in.mahesh.dashboard.Dashboard;
import in.mahesh.entity.Counsellor;
import in.mahesh.entity.Enquiry;

@Controller
public class ImpEnquirySerive implements EnquiryService {
	@Autowired
	private CounsellorRepo cRepo;
	
	@Autowired
	private EnquiryRepo eRepo;

	@Override
	public boolean saveEnquiry(Enquiry enquiry , Integer cId) {
		Counsellor counsellor = cRepo.findById(cId).orElseThrow();
		enquiry.setCounsellor(counsellor); // asscoation mapping
		Enquiry save = eRepo.save(enquiry);
		return save.geteId() != null;
	}

	@Override
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer cId) {
		Counsellor counsellor = cRepo.findById(cId).orElseThrow();
		enquiry.setCounsellor(counsellor);
		Example<Enquiry> of = Example.of(enquiry);
		return eRepo.findAll(of);
	}

	@Override
	public Enquiry editEnquiry(Integer eId) {
		return eRepo.findById(eId).orElseThrow();
	}

	@Override
	public Dashboard getDashboardInfo(Integer cId) {
		Long totalEnquire = eRepo.getAllEnquiries(cId);
		Long openEnquire = eRepo.getAllEnquiries(cId , "open");
		Long enrolledEnquire = eRepo.getAllEnquiries(cId , "enrolled");
		Long lostEnquire = eRepo.getAllEnquiries(cId , "lost");
		Dashboard d =new Dashboard();
		if(totalEnquire == null && openEnquire==null && enrolledEnquire==null && lostEnquire==null) {
			d.setEnrolledEnquires((long)0);
			d.setLostEnquires((long)0);
			d.setOpenEnquires((long)0);
			d.setTotalEnquires((long)0);
		}else {
			d.setEnrolledEnquires(enrolledEnquire);
			d.setLostEnquires(lostEnquire);
			d.setOpenEnquires(openEnquire);
			d.setTotalEnquires(totalEnquire);
		}
		
		return d;
	}

}
