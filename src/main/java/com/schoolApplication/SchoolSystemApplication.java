package com.schoolApplication;

import com.schoolApplication.menu.SystemMenu;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SchoolSystemApplication implements CommandLineRunner{

    @Autowired
    private SystemMenu menu;

    public static void main(String[] args){

       Dotenv dotenv = Dotenv.load();
        System.setProperty("PG_URL", dotenv.get("PG_URL"));
        System.setProperty("PG_USERNAME", dotenv.get("PG_USERNAME"));
        System.setProperty("PG_PASSWORD", dotenv.get("PG_PASSWORD"));
        System.setProperty("MAILTRAP_USERNAME", dotenv.get("MAILTRAP_USERNAME"));
        System.setProperty("MAILTRAP_PASSWORD",dotenv.get("MAILTRAP_PASSWORD"));

            SpringApplication.run(SchoolSystemApplication.class, args);
    }
    
    @Override
    public void run(String... args){
        menu.menu();
    }
}
