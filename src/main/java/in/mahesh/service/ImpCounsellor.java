package in.mahesh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.mahesh.Email.EmailUtils;
import in.mahesh.Repo.CounsellorRepo;
import in.mahesh.entity.Counsellor;
@Service
public class ImpCounsellor implements CounsellorService {
	
	@Autowired
	private CounsellorRepo cRepo;
	
	@Autowired
	private EmailUtils mail;
	
	@Override
	public boolean saveCounsellor(Counsellor counsellor) {
		Optional<Counsellor> email = cRepo.findByEmail(counsellor.getEmail());
		if(email.isPresent()) {
			return false;
		}
		mail.mailSent(counsellor.getEmail(), "Account Creation","hey "+counsellor.getName()+", yor Account has been Created Sucessfully......");
		Counsellor save = cRepo.save(counsellor);
		return save.getcId() != null;
		
		
	}

	@Override
	public Counsellor getCounsellor(String mail, String password) {
		 return cRepo.findByEmailAndPassword(mail, password);
	}

}
