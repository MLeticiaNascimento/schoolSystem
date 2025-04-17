package org.example.validator;

import org.example.exceptions.MissingRequiredFieldException;

public class NameValidator {
    
    public static boolean isFullNameValidator(String name) throws MissingRequiredFieldException{

        if(name == null || name.trim().isEmpty()){
            throw new MissingRequiredFieldException(); // mensagem padrão
        }
        String [] partes = name.trim().split("\\s+");

        int partesValidas = 0;
        for (String parte : partes){
            
            if (parte.matches("[A-Za-z]\\.")){
                throw new MissingRequiredFieldException("Abreviações como 'A.' não são permitidas. Por favor informe o nome completo.");
            }
            if (parte.length() > 1){
                partesValidas++;
            }
        }
            if(partesValidas < 2){
                throw new MissingRequiredFieldException("Por favor, informe o nome completo sem abreviações.");
        }
           return true;
    }
}
