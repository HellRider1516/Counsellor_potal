package in.mahesh.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.mahesh.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
	
	@Query(value = "select *from counsellor where c_id=:cId" , nativeQuery = true)
	public Long getAllEnquiries(Integer cId);
	
	@Query(value = "select *from enquiry where counsellor_c_id=:cId AND status=:status" , nativeQuery = true)
	public Long getAllEnquiries(Integer cId , String status);

}
