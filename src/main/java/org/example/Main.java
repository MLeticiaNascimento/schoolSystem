//estudo de Builder Pattern
package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // Construção do objeto usando Builder
        Student student = new Student.StudentBuilder("12345", "Leticia")
       .city("São Paulo")
        .serie(3)
        .build();

        //Exibindo informações
        System.out.println("RA: " + student.getRa());
        System.out.println("Nome: " + student.getName());
        System.out.println("Cidade: " + student.getCity());
        System.out.println("Série: " + student.getSerie());
    }

        public class SistemaMenu{
            public static void main(String [] args){
                Scanner scan = new Scanner(System.in);
                int opcao = 0;
                //opções de menu: anexar/ atualizar/ excluir/ solicitar mediação
                do {
                    System.out.println("Bem vindo ao menu, escolha a ação necessária:");

                    System.out.println("1. Anexar");
                    System.out.println("2. Atualizar");
                    System.out.println("3. Excluir");
                    System.out.println("4. Solicitar mediação");
                    System.out.println("Digite o numero da opção escolhida: ");

                    //Leitura da opção
                    opcao = scan.nextInt();

                    //Processando opção escolhida
                    switch (opcao) {
                        case 1:
                            System.out.println("Opção 1 selecionada: Anexar estudante.");
                            //método para anexar estudante
                            addStudent();
                            break;
                        case 2:
                            System.out.println("Opção 2 selecionada: Atualizar dados do estudante.");
                            // método para atualizar estudante
                            updateStudent();
                            break;
                        case 3:
                            System.out.println("Opção 3 selecionada: Excluir dados do estudante.");
                            //método para excluir dados do estudante
                            deleteStudent();
                            break;
                        case 4:
                            System.out.println("Opção 4 selecionada: Solicitar mediação sobre estudante.");
                            // método solicitando mediação
                            mediationStudent();
                            break;
                    }
                }while (opcao != 0);
                System.out.println("Você está encerrando o menu.");
                scan.close();
            }
            public static void addStudent(){

            }
            public static void updateStudent(){

            }
            public static void deleteStudent(){

            }
            public static void mediationStudent(){

            }
        }
    static class Student {
        //Atributos
        private final String ra, name;
        private final int cpf, dateBirth, fone;
        private String city;
        private int serie;


        //Construtor privado para impedir criação direta, não é acessado pelo cliente
        private Student(StudentBuilder builder) {
            this.ra = builder.ra;
            this.name = builder.name;
            this.cpf = builder.cpf;
            this.dateBirth = builder.dateBirth;
            this.city = builder.city;
            this.serie = builder.serie;
            this.fone = builder.fone;
        }

        //Para acessar os atributos
        public String getRa() {
            return ra;
        }

        public String getName() {
            return name;}

        public int getCpf() {return cpf;}

        public int getDateBirth() { return dateBirth;}

        public String getCity() { return city;}

        public int getSerie() { return serie;}

        public int getFone() { return fone;}


        //Classe Builder interna
        public static class StudentBuilder {
            private final String ra, name; //obrigatórios
            private final int cpf, dateBirth, fone; // obrigatórios
            private String city;
            private int serie;


            //Construtor do Builder define a obrigatoriedade
            public StudentBuilder(String ra, String name) {
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException(" O campo 'Nome' é obrigatório");
                }
                if (ra == null || ra.isEmpty()) {
                    throw new IllegalArgumentException("O campo 'RA' é obrigatório");
                }
                this.name = name;
                this.ra = ra;
                this.cpf = 0;
                this.dateBirth = 0;
                this.fone = 0;
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

            //Método para construir o objeto final
            public Student build() {
                return new Student(this);
                }

            }
        }
}