package com.schoolApplication.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name= "students")
public class Student {

    @Id
    private String ra;
    private String name;
    private String cpf;

    @Column(name="datewBirth")
    private LocalDate dateBirth;

    private String fone;
    private String city;
    private String serie;
    private String team;

    public Student(){

    }

    public String getRa(){
        return ra;
    }

    public void setRa(String ra){
        this.ra = ra;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public LocalDate getDateBirth(){
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth){
        this.dateBirth = dateBirth;
    }

    public String getFone(){
        return fone;
    }

    public void setFone(String fone){
        this.fone = fone;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }
    
    public String getSerie(){
        return serie;
    }

    public void setSerie(String serie){
        this.serie = serie;
    }

    public String getTeam(){
        return team;
    }

    public void setTeam(String team){
        this.team = team;
    }
}
