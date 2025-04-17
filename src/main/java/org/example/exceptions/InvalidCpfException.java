package org.example.exceptions;

 public class InvalidCpfException extends Exception  {
    
    //Construtor padrão
    public InvalidCpfException(){
        super ("CPF inválido! Por favor os 11 numeros.");
    }


    //Construtor para mensagerns personalizadas
   public InvalidCpfException (String message){
        super (message);
    }
    
}
