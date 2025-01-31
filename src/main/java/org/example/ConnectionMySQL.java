package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {


    private static final String URL =  "jdbc:mysql://localhost:3306/ESCOLA";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static Connection connect(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
            }
        }
}
