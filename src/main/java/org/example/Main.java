//estudo de Builder Pattern
package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Chamar menu do sistema
        SistemMenu.menu();
        }

    }

class Student {
        //Atributos
        private final String ra, name;
        private final int cpf, dateBirth, fone;
        private String city, group;
        private int serie;


        //Construtor privado para impedir criação direta, não é acessado pelo cliente
        private Student(StudentBuilder builder) {
            this.ra = builder.ra;
            this.name = builder.name;
            this.cpf = builder.cpf;
            this.dateBirth = builder.dateBirth;
            this.city = builder.city;
            this.serie = builder.serie;
            this.group = builder.group;
            this.fone = builder.fone;
        }

        //Para acessar os atributos
        public String getRa() {return ra;}
        public String getName() {return name;}
        public int getCpf() {return cpf;}
        public int getDateBirth() {return dateBirth;}
        public String getCity() { return city;}
        public int getSerie() {return serie;}
        public String getGroup() {return group;}
        public int getFone() { return fone;}


        //Classe Builder interna
        public static class StudentBuilder {
            private final String ra, name; //obrigatórios
            private final int dateBirth, cpf;
            private int fone=0; // obrigatórios
            private String city, group;
            private int serie;


            //Construtor do Builder define a obrigatoriedade
            public StudentBuilder(String ra, String name, int cpf, int dateBirth) {
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException(" O campo 'Nome' é obrigatório");
                }
                if (ra == null || ra.isEmpty()) {
                    throw new IllegalArgumentException("O campo 'RA' é obrigatório");
                }
                if (cpf <= 0){
                    throw new IllegalArgumentException ("O campo cpf é obrigatório e deve ser válido.");
                }
                if(dateBirth <= 0) {
                    throw new IllegalArgumentException("O campos data de nascimento é obrigatório.");
                }

                this.name = name;
                this.ra = ra;
                this.cpf = cpf;
                this.dateBirth = dateBirth;
            }

            // Métodos configuradores
            public StudentBuilder city(String city) {
                this.city = city;
                return this;
            }

            public StudentBuilder serie(int serie) {
                this.serie = serie;

                return this;
            }
            public StudentBuilder group (String group) {
                this.group = null;
                return this;
            }
            public StudentBuilder cpf(int cpf) {
                //verificar se o cpf é valido

            }

            public StudentBuilder fone(int fone) {
                this.fone = fone;
                return this;
            }
            //Método para construir o objeto final
            public Student build() {
                return new Student(this);
                }


        }
}

