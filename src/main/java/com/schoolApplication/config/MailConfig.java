package com.schoolApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    JavaMailSender javaMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.mailtrap.io");
        sender.setPort(587);
        sender.setUsername(DotenvLoader.get("MAILTRAP_USERNAME"));
        sender.setPassword(DotenvLoader.get("MAILTRAP_PASSWORD"));

        Properties prop = sender.getJavaMailProperties();
        prop.put("mail.stmp.auth", "true");
        prop.put("mail.stmp.starttls.enable", "true");

        return sender;
    }
}
