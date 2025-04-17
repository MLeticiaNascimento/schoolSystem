package org.example.validator;

import org.example.exceptions.InvalidCpfException;

public class CpfValidator {
    
    public static void isValidCpf(String cpf) throws InvalidCpfException{
        
        cpf = cpf.replaceAll("^\\d]", "");

        if(cpf.length () != 11){
            throw new InvalidCpfException("CPF deve conter 11 dígitos.");
        }

        if(cpf.matches("(\\d)\\1{10}")){
            throw new InvalidCpfException("CPF inválido: todos os dígitos são iguais.");
        }
        
        int soma = 0;
        for (int i = 0; i<9 ; i ++){
            soma += Character.getNumericValue(cpf.charAt(i)) * (10-i);
        }
            int primeiroDigito = 11 -(soma % 11);
            if(primeiroDigito >= 10) primeiroDigito = 0;

            if(primeiroDigito != (cpf.charAt(9) - '0')){
                throw new InvalidCpfException("Primeiro dígito verificador inválido.");
            }

        soma = 0;
        for(int i = 0; i < 10; i++){
            soma += Character.getNumericValue(cpf.charAt(i))*(11 - i);
            
        int segundoDigito = 11 -( soma%11);
        if (segundoDigito >= 10) segundoDigito = 0;
        
        if(segundoDigito != Character.getNumericValue(cpf.charAt(10))){
                throw new InvalidCpfException("Segundo dígito verificador inválido.");
            }
        }   
    }
}