package com.schoolApplication.exceptions;

public class MissingRequiredFieldException extends Exception {

    //Mensagem padrão
    public MissingRequiredFieldException(){
        super ("O campo é obrigatório.");
    }
    // mensagem persobalizada
    public MissingRequiredFieldException(String message){
        super (message);
    }
}
