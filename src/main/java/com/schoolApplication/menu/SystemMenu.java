package com.schoolApplication.menu;

import com.schoolApplication.repository.StudentDAO;
import com.schoolApplication.model.*;
import com.schoolApplication.exceptions.*;
import com.schoolApplication.validator.*;

import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
                //add Registro do aluno(RA)
                String ra = null;
                boolean raValido = false;

                while(!raValido){
                System.out.print("Digite o RA(Registro do aluno): ");
                ra = scan.nextLine();
                
                try {
                    if (ra == null || ra.trim().isEmpty()){
                        throw new MissingRequiredFieldException("O RA não pode estar vazio.");
                    }
                        raValido = true;
                        System.out.println("RA do estudante: " + ra);    

                    } catch(MissingRequiredFieldException e){
                        System.out.println("Erro: " + e.getMessage());
                        System.out.println("Por favor, tente novamente.");
                    }

                }

                //add nome
                String name = null;
                boolean ValidName = false;         

                while(!ValidName){
                        System.out.print("Digite o nome do aluno: ");
                        name = scan.nextLine();
                        try{
                            NameValidator.isFullNameValidator(name);
                            ValidName = true;
                            System.out.println("Nome válido: " + name);

                        }catch(MissingRequiredFieldException e){
                        System.out.println("Erro: "+ e.getMessage());
                        System.out.println("Por favor, tente novamente.");
                        }
                    }
                //add cidade  
                System.out.print("Digite a cidade do aluno: ");
                String city = scan.nextLine();

                //add serie
                System.out.print("Digite a série do aluno: ");
                String serie = scan.nextLine();

                //add turma
                System.out.print("Digite a turma do aluno: ");
                String team = scan.nextLine();

                //add cpf
                String cpf = null;
                boolean validCpf = false;

                while (!validCpf){

                    System.out.print("Digite o CPF do aluno: ");
                    cpf = scan.nextLine();

                    try{
                        CpfValidator.validarCpf(cpf);
                        validCpf = true;
                        System.out.println("CPF valido: " + cpf);
                        } catch (InvalidCpfException e){
                                System.out.println("Erro: "+ e.getMessage());
                                System.out.println("Por favor, digite o CPF novamente. ");
                        }
                    }


                // add data de nascimento
                System.out.print("Digite a data de nascimento do aluno: ");
                String dateBirthStr = scan.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateBirth = LocalDate.parse(dateBirthStr, formatter);


                //add telefone
                System.out.print("Digite o telefone do aluno(somente numeros): ");
                String fone = scan.nextLine();



                //Inserindo no banco de dados
                try{
                Student student = new Student.StudentBuilder(ra,name, cpf, dateBirthStr)
                        .city(city)
                        .serie(serie)
                        .team(team)
                        .fone(fone)  
                        .build();

                //StudentDAO studentDAO = new StudentDAO();
                StudentDAO.insertStudent(student);
                System.out.println("Aluno cadastrado com sucesso!");
            

                //Exibindo informações
            System.out.println();
            System.out.println("Dados do aluno: ");
            System.out.println("RA: " + student.getRa());
            System.out.println("Nome: " + student.getName());
            System.out.println("Cidade: " + student.getCity());
            System.out.println("Série: " + student.getSerie() + student.getTeam());
                }catch(SQLException e){
                    System.out.println("Erro ao inserir no banco de dados: " + e.getMessage());
                }
              
        }

            public static void updateStudent(){

            }
            public static void deleteStudent(){



            }
            public static void mediationStudent(){

            }

    }

