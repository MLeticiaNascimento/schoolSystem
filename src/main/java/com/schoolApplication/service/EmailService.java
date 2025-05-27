package com.schoolApplication.service;

import com.schoolApplication.dto.RequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail (RequestDto dto){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("sistema.escola@gmail.com");
        message.setReplyTo(dto.getEmail());
        message.setTo("equipe.assessoria@email.com");
        message.setSubject("Nova solicitação de mediação.");
        
        String bodyEmail = "Nome: " + dto.getName() +
                            "\nEmail: "+ dto.getEmail() +
                            "\nMatrícula do servidor: " + dto.getLicense() +
                            "\nMensagem: " + dto.getMsg();
                            
        message.setText(bodyEmail);

        mailSender.send(message);
    } 
}
