package com.schoolApplication.dto;


public class RequestDto{
    private String name;
    private String email;
    private String license;
    private String msg;

    public RequestDto(){}

    //Nome
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //matrícula do funcionário
    public String getLicense(){
        return license;
    }
    public void setLicense(String license){
        this.license = license;
    }

    //mensagem do email
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }

}