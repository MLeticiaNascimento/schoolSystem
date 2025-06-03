package com.schoolApplication.test;

import java.sql.Connection;

import com.schoolApplication.legacy.ConnectionBD;

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
