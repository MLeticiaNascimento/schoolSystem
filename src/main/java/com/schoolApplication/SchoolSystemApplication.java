package com.schoolApplication;

import com.schoolApplication.menu.SystemMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class SchoolSystemApplication implements CommandLineRunner{

    @Autowired
    private SystemMenu menu;

    public static void main(String[] args){
        Dotenv dotenv = Dotenv.load();
            SpringApplication.run(SchoolSystemApplication.class, args);
    }
    
    @Override
    public void run(String... args){
        menu.menu();
    }
}
