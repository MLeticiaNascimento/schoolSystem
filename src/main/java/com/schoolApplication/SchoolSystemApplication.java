package com.schoolApplication;

import com.schoolApplication.menu.SystemMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class SchoolSystemApplication implements CommandLineRunner{

    public static void main(String[] args){
        Dotenv dotenv = Dotenv.load();
            SpringApplication.run(SchoolSystemApplication.class, args);
    }
    
    @Override
    public void run(String... args){
        SystemMenu.menu();
    }
}
