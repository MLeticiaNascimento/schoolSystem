package com.schoolApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.schoolApplication"})
public class SchoolSystemApplication{

       public static void main(String[] args){

        System.out.println("DB_URL = " + System.getenv("DB_URL"));
    System.out.println("DB_USERNAME = " + System.getenv("DB_USERNAME"));
    System.out.println("MAILTRAP_USERNAME = " + System.getenv("MAILTRAP_USERNAME"));
    
            SpringApplication.run(SchoolSystemApplication.class, args);
   
    }  
 }  