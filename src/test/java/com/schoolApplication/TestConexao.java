package com.schoolApplication;

import com.schoolApplication.repository.ConnectionBD;
import java.sql.Connection;

public class TestConexao {

    public static void main(String[] args){
        try{
            Connection conexao = ConnectionBD.connect();
            System.out.println("Conectado com sucesso!");
            conexao.close();
        } catch (Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    
}
