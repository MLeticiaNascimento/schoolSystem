package org.example;

public class Student {
        //Atributos
        private final String ra, name;
        private final int cpf, dateBirth, fone;
        private String city, serie, team;



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
        public int getCpf() {return cpf;}
        public int getDateBirth() {return dateBirth;}
        public String getCity() { return city;}
        public String getSerie() {return serie;}
        public String getTeam() {return team;}
        public int getFone() { return fone;}


        //Classe Builder interna
        public static class StudentBuilder {
            private final String ra, name; //obrigatórios
            private final int dateBirth, cpf; // obrigatório
            private int fone=0;
            private String city, serie, team;



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

            public StudentBuilder serie(String serie) {
                this.serie = serie;
                return this;
            }
            public StudentBuilder team(String team) {
                this.team = team;
                return this;
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
