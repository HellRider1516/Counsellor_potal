package in.mahesh.service;

import in.mahesh.entity.Counsellor;

public interface CounsellorService {
	
	public boolean saveCounsellor(Counsellor counsellor);
	
	public Counsellor getCounsellor(String mail , String password);

}
