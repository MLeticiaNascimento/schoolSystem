package com.schoolApplication.exceptions;

public class DatabaseException extends Exception {
    private static final String DEFAULT_MESSAGE = "Erro ao acessar o banco de dados.";

    //Mensagem padrão
    public DatabaseException(){
        super(DEFAULT_MESSAGE);
    }
    //Mensagem personalizada
    public DatabaseException (String message) {
        super(message);
    }
    
}