package com.schoolApplication.repository;

import com.schoolApplication.model.Student;
import java.sql.*;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;



@Repository
public class StudentDAO {

    private final DataSource dataSource;

    public StudentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

        public void insertStudent(Student student) throws SQLException {
            //comando para inserir o aluno na tabela students
            String sql = "INSERT INTO students(ra, name, cpf, dateBirth, fone, city, serie, team) VALUES(?,?,?,?,?,?,?,?)";

            Connection connection = null;

            try {
                connection = ConnectionBD.connect();
                connection.setAutoCommit(false);

                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, student.getRa());
                    stmt.setString(2, student.getName());
                    stmt.setString(3, student.getCpf());
                    //Converte LocalDate para Date
                    stmt.setDate(4,Date.valueOf(student.getDateBirth()));
                    stmt.setString(5, student.getFone());
                    stmt.setString(7, student.getSerie());
                    stmt.setString(6, student.getCity());
                    stmt.setString(8, student.getTeam());

                    //faz a inserção no banco de dados
                    stmt.executeUpdate();
                }

                connection.commit();
                System.out.print("Aluno inserido com sucesso!");

            } catch (SQLException e) {
                System.out.println("Erro ao inserir aluno" + e.getMessage());
                if(connection != null){ 
                    try{
                        connection.rollback();
                    }catch (SQLException rollbackEx){
                        System.out.println("Erro no rollback: " + e.getMessage());
                    }

                }
            }finally{
                if(connection != null){
                    try{
                        connection.close();
                    }catch(SQLException closException){
                        System.out.println("Erro ao fechar a conexão: "+ closException.getMessage());
                    }
                }
            }
        }

        public void update(Student student){
            String sql = "UPDATE student SET name = ?, cpf = ?, dateBirth = ?, fone = ?, city = ?, serie = ?, team = ? WHERE ra = ?";

            try(Connection connection = ConnectionBD.connect();
            PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setString(1, student.getName());
                stmt.setString(2, student.getCpf());
                stmt.setDate(3, Date.valueOf(student.getDateBirth()));
                stmt.setString(4, student.getFone());
                stmt.setString(5, student.getCity());
                stmt.setString(6, student.getSerie());
                stmt.setString(7, student.getTeam());
                stmt.setString(8, student.getRa());

                stmt.executeUpdate();

                System.out.println("Aluno atualizado com sucesso!");
            }catch(SQLException e){
                System.out.println("Erro ao atualizar o aluno: "+ e.getMessage());
                throw new RuntimeException(e);
            }

        }

        public Student findByRa(String ra){
            String sql = "SELECT * FROM students WHERE ra = ?";
            try(Connection connection = ConnectionBD.connect();
                PreparedStatement stmt = connection.prepareStatement(sql)){

                stmt.setString(1,ra);
                ResultSet result = stmt.executeQuery();

                    if(result.next()){
                        return buildStudentFromResultSet(result);
                        }
                    }catch(SQLException e){
                        System.out.println("Erro ao buscar aluno pelo RA: " + e.getMessage());
                    }
                    return null;
                }
        
        public Student findByName(String name){
            String sql = "SELECT * FROM students WHERE name = ?";
            try(Connection connection = ConnectionBD.connect();
            PreparedStatement stmt = connection.prepareStatement(sql)){

                stmt.setString(1, name);
                ResultSet result = stmt.executeQuery();

                if(result.next()){ 
                    return buildStudentFromResultSet(result);
                }
            }catch (SQLException e){
                System.out.println("Erro ao buscar o aluno pelo nome: " + e.getMessage());
            }
            return null;
        }

    private Student buildStudentFromResultSet (ResultSet result) throws SQLException{

        Student.StudentBuilder builder = new Student.StudentBuilder(
            result.getString("ra"),
            result.getString("name"),
            result.getString("cpf"),
            result.getDate("dateBirth").toLocalDate()
        );
            

            if(result.getString("fone") != null){
                builder.fone(result.getString ("fone"));
            }
            if(result.getString("city") != null){
                builder.city(result.getString("city"));
            }
            if(result.getString("serie") != null){
                builder.serie(result.getString("serie"));
            }
            if(result.getString("team") != null){
                builder.team(result.getString("team"));
            }

        return builder.build();
    }

    public void delete(Student student){
        String sql = " DELETE FROM students WHERE ra = ?";

        try(Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, student.getRa());
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Erro ao deletar os dados do estudante. "+ e.getMessage(), e);
        }
    }
}

