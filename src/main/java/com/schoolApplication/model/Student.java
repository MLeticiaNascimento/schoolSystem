package com.schoolApplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



@Entity
public class Student {

        @Id
        @Column(unique=true, nullable = false)

        private String ra, name, cpf, fone, city, serie, team;
        private LocalDate dateBirth;
        
        //construtor
        public Student(){

        }

        //Construtor privado para impedir criação direta, não é acessado pelo cliente
        private Student(StudentBuilder builder) {
            this.ra = builder.ra;
            this.name = builder.name;
            this.cpf = builder.cpf;
            this.dateBirth = builder.dateBirth;
            this.city = builder.city;
            this.serie = builder.serie;
            this.team = builder.team;
            this.fone = builder.fone;
        }

        //Para acessar os atributos
        public String getRa() {return ra;}
        public String getName() {return name;}
        public String getCpf() {return cpf;}
        public LocalDate getDateBirth() {return dateBirth;}
        public String getCity() { return city;}
        public String getSerie() {return serie;}
        public String getTeam() {return team;}
        public String getFone() { return fone;}


        //Classe Builder interna
        public static class StudentBuilder {
            private final String ra, name, cpf; //obrigatórios
            private LocalDate dateBirth; // obrigatório
            private String fone, city, serie, team;



            //Construtor do Builder define a obrigatoriedade
            public StudentBuilder(String ra, String name, String cpf, String dateBirthStr) {
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException(" O campo 'Nome' é obrigatório");
                }
                if (ra == null || ra.isEmpty()) {
                    throw new IllegalArgumentException("O campo 'RA' é obrigatório");
                }
                if (cpf == null){
                    throw new IllegalArgumentException ("O campo 'CPF' é obrigatório e deve ser válido.");
                }
                if(dateBirthStr == null || dateBirthStr.isEmpty()) {
                    throw new IllegalArgumentException("O campos 'data de nascimento' é obrigatório.");
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.dateBirth = LocalDate.parse(dateBirthStr, formatter);
                this.name = name;
                this.ra = ra;
                this.cpf = cpf;
                
            }

            // Métodos configuradores
            public StudentBuilder dateBirth(String dateBirthStr) {
                this.dateBirth = LocalDate.parse(dateBirthStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return this;
            }
            public LocalDate getDateBirth() {
                return dateBirth;
            }

            public StudentBuilder city(String city) {
                this.city = city;
                return this;
            }

            public StudentBuilder serie(String serie) {
                if(serie == null || serie.trim().isEmpty()){
                    throw new IllegalArgumentException("O campo 'Serie' é obrigatório.");
                }
                if (!serie.matches("[1-9]")){
                    throw new IllegalArgumentException("O campo 'Serie' deve conter apenas um número de 1 á 9.");
                }
                this.serie = serie;
                return this;
            }

            public StudentBuilder team(String team) {
                if(team == null || team.trim().isEmpty()){
                    throw new IllegalArgumentException ("O campo'Turma' é obrigatório.");
                }
                if (team.length() != 1){
                    throw new IllegalArgumentException ("O campo 'Turma' deve contar com apenas uma única letra.");
                }
                this.team = team.toUpperCase();
                return this;
            }


            public StudentBuilder fone(String fone) {
                this.fone = fone;
                return this;
            }
            //Método para construir o objeto final
            public Student build() {
                return new Student(this);
                }


        }
}
