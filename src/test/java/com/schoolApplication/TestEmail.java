package com.schoolApplication;

import com.schoolApplication.service.EmailService;
import com.schoolApplication.dto.RequestDto;

public class TestEmail {

    public static void main(String[] args){

        EmailService service = new EmailService();
        
        RequestDto request = new RequestDto();
        request.setEmail("suportepedagogico@sistema.com");
        request.setName("Teste de e-mail");
        request.setMsg("Este é um teste de envio de email pelo Mailtrap.");

        service.sendEmail(request);

        System.out.println("Email enviado!");
    }
    
}
