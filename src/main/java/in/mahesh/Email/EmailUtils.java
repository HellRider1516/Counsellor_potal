package in.mahesh.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailUtils {
	
	@Autowired
	private JavaMailSender sender;
	
	public void mailSent(String to , String sub , String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("hellridermahesh@gmail.com");
		message.setText(body);
		message.setTo(to);
		message.setSubject(sub);
		sender.send(message);
	}

}
