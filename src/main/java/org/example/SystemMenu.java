package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class SystemMenu{

            public static void menu() {
                Scanner scan = new Scanner(System.in);
                StudentDAO studentDAO = new StudentDAO();

                int opcao = 0;
                //opções de menu: anexar/ atualizar/ excluir/ solicitar mediação

                System.out.println("Bem vindo ao menu, escolha a ação necessária:");
                System.out.println("1. Anexar");
                System.out.println("2. Atualizar");
                System.out.println("3. Excluir");
                System.out.println("4. Solicitar mediação");
                System.out.println("5. Sair");
                System.out.println("Digite o numero da opção escolhida: ");

                //Leitura da opção
                opcao = scan.nextInt();
                scan.nextLine();

                //Processando opção escolhida
                switch (opcao) {
                    case 1:
                        System.out.println("Opção 1 selecionada: Anexar estudante.");
                        //método para anexar estudante
                        addStudent(scan, studentDAO);
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
                    default:
                        System.out.println("Você está encerrando o menu.");
                     }
                }

            private static void addStudent(Scanner scan, StudentDAO studentDAO) {
            System.out.print("Digite o RA(Registro do aluno): ");
            String ra = scan.nextLine();

            System.out.print("Digite o nome do aluno: ");
            String name = scan.nextLine();

            System.out.print("Digite a cidade do aluno: ");
            String city = scan.nextLine();

            System.out.print("Digite a série do aluno: ");
            String serie = scan.nextLine();

            System.out.print("Digite a turma do aluno: ");
            String team = scan.nextLine();

            System.out.print("Digite o CPF do aluno: ");
            int cpf = scan.nextInt();
            scan.nextLine();

            System.out.print("Digite a data de nascimento do aluno: ");
            int dateBirth = scan.nextInt();
            scan.nextLine();

            System.out.print("Digite o telefone do aluno(somente numeros): ");
            int fone = scan.nextInt();
            scan.nextLine();

            //Criar o estudante
            Student student = new Student.StudentBuilder(ra,name, cpf,dateBirth)
                    .city(city)
                    .serie(serie)
                    .team(team)
                    .fone(fone)
                    .build();

            //inserindo o aluno no banco de dados
            try{
                //StudentDAO studentDAO = new StudentDAO();
                StudentDAO.insertStudent(student);
                System.out.println("Aluno cadastrado com sucesso");
            }catch (SQLException e) {
                System.out.println("Erro ao inserir novo aluno: "+e.getMessage());
            }

            //Exibindo informações
                System.out.println();
                System.out.println("Dados do aluno: ");
            System.out.println("RA: " + student.getRa());
            System.out.println("Nome: " + student.getName());
            System.out.println("Cidade: " + student.getCity());
            System.out.println("Série: " + student.getSerie() + student.getTeam());

            }

            public static void updateStudent(){

            }
            public static void deleteStudent(){



            }
            public static void mediationStudent(){

            }

    }

