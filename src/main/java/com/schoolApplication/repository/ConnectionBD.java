package com.schoolApplication.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.schoolApplication.config.DotenvLoader;


public class ConnectionBD {

    public static Connection connect() throws SQLException{
        String host = DotenvLoader.get("PG_HOST");
        String port = DotenvLoader.get("PG_PORT");
        String database = DotenvLoader.get("PG_DATABASE");
        String username = DotenvLoader.get("PG_USERNAME");
        String password = DotenvLoader.get("PG_PASSWORD");
        
        String url ="jdbc:postgresql://" + host + ":" + port + "/" + database;

        try{
            return DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
            }
        }
}
