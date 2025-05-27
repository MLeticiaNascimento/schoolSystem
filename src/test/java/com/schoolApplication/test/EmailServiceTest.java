package com.schoolApplication.test;

import com.schoolApplication.dto.RequestDto;
import com.schoolApplication.service.EmailService;
import com.schoolApplication.SchoolSystemApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SchoolSystemApplication.class)
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail(){

        RequestDto request = new RequestDto();
        request.setName("Funcionario");
        request.setEmail("funcionario@gmail.com");
        request.setMsg("Este é um teste de envio de e-mail. ");

        emailService.sendEmail(request);

        System.out.println("TESTE de envio de email realizaod com sucesso! ");
         
    } 
}
