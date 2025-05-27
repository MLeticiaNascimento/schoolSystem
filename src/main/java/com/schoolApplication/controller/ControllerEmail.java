package com.schoolApplication.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.schoolApplication.service.EmailService;
import com.schoolApplication.dto.RequestDto;
 

@RestController

@RequestMapping("/call")
public class ControllerEmail {
    
    private final EmailService emailService;
    
    public ControllerEmail(EmailService emailService){
        this.emailService = emailService; //dependência
    }

    // o Spring vai mapear o Json para o RequestDto
    @PostMapping
    public String openCall(@RequestBody RequestDto requestDto){
               
        emailService.sendEmail(requestDto);

        return "Email enviado com sucesso!";
    }    
}
