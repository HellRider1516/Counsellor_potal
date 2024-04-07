package in.mahesh.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mahesh.entity.Counsellor;
import java.util.List;
import java.util.Optional;


public interface CounsellorRepo extends JpaRepository<Counsellor, Integer>{
	
	public Optional<Counsellor> findByEmail(String email);
	
	public Counsellor findByEmailAndPassword(String email, String password);

}
