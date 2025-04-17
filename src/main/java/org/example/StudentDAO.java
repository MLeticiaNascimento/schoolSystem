package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class StudentDAO {
    public StudentDAO() {}

        public static void insertStudent(Student student) throws SQLException {
            //comando para inserir o aluno na tabela students
            String sql = "INSERT INTO students(ra, name, cpf, dateBirth, fone, city, serie, team) VALUES(?,?,?,?,?,?,?,?)";

            try (Connection connection = ConnectionMySQL.connect();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                //substitui os "?" pelos valores reais
                stmt.setString(1, student.getRa());
                stmt.setString(2, student.getName());
                stmt.setString(3, student.getCpf());

                //Converte LocalDate para Date
                stmt.setDate(4,Date.valueOf(student.getDateBirth()));

                stmt.setString(5, student.getFone());
                stmt.setString(6, student.getCity());
                stmt.setString(7, student.getSerie());
                stmt.setString(8, student.getTeam());

                //faz a inserção no banco de dados
                stmt.executeUpdate();
                System.out.print("Aluno inserido com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao inserir aluno" + e.getMessage());
            }
        }

}
