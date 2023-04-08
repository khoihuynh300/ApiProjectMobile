package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.api.entity.Users;

@Service
public class MailService {
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Autowired
	private OtpService otpService;
	
	@Value("${spring.mail.username}") private String sender;
	
	public String sendOtpCode(String sendTo) {
		
		try {
            String OtpCode = String.valueOf(otpService.generateOTP(sendTo));

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(sendTo);
            mailMessage.setText("Mã OTP của bạn là " + OtpCode);
            mailMessage.setSubject("Xác nhận tài khoản");
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully";
        }
 
        catch (Exception e) {
        	System.err.println(e.toString());
            return "Error while Sending Mail";
        }
	}

}
