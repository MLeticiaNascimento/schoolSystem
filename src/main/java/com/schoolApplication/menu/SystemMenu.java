package com.schoolApplication.menu;

import com.schoolApplication.repository.StudentDAO;
import com.schoolApplication.model.*;
import com.schoolApplication.dto.RequestDto;
import com.schoolApplication.exceptions.*;
import com.schoolApplication.validator.*;
import com.schoolApplication.service.*;


import java.sql.SQLException;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Component  
public class SystemMenu{

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDAO studentDAO;

   
    public void menu() {
                Scanner scan = new Scanner(System.in);
                
                
                int opcao = 0;
              
            while(true){
                System.out.println("Bem vindo ao menu, escolha a ação necessária:");
                System.out.println("1. Anexar");
                System.out.println("2. Atualizar");
                System.out.println("3. Excluir");
                System.out.println("4. Solicitar mediação");
                System.out.println("5. Sair");
                System.out.println("Digite o numero da opção escolhida: ");

                try{
                    opcao = Integer.parseInt(scan.nextLine());
                    scan.nextLine();
                }catch (NumberFormatException e){
                    System.out.println("Opção inválida. Por favor Digite um dos números do Menu.");
                    continue;
                }

                //Processando opção escolhida
                switch (opcao) {
                    case 1:
                        System.out.println("Opção 1 selecionada: Anexar estudante.");
                        //método para anexar estudante
                        addStudent(scan, studentDAO);
                        break;
                    case 2:
                        System.out.println("Opção 2 selecionada: Atualizar dados do estudante.");
                        System.out.println("Digite o RA do estudante");
                        String ra = scan.nextLine();
                        Student student = studentService.searchStudentByRa(ra);
                        updateStudent(scan, student, this.studentDAO);
                        break;
                    case 3:
                        System.out.println("Opção 3 selecionada: Excluir dados do estudante.");
                        //método para excluir dados do estudante
                        deleteStudent();
                        break;
                    case 4:
                        System.out.println("Opção 4 selecionada: Solicitar mediação para estudante.");
                        // método solicitando mediação
                        mediationStudent(scan, studentService);
                        break;
                    case 5:
                    System.out.println("Você escolheu encerrar o programa. Até breve!");
                    return;

                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
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
                Student student = new Student.StudentBuilder(ra,name, cpf, dateBirth)
                        .city(city)
                        .serie(serie)
                        .team(team)
                        .fone(fone)  
                        .build();

                //StudentDAO studentDAO = new StudentDAO();
                studentDAO.insertStudent(student);
                
                System.out.println("Aluno cadastrado com sucesso!" );
            

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

            public void updateStudent(Scanner scan, Student student, StudentDAO studentDAO){
                    System.out.println("Atualizando dados de : " + student.getName());
                    
                    if(student != null){
                        System.out.println("Estudante encontrado: " + student.getName());

                        boolean continuar = true;

                        while(continuar){

                            System.out.println("Escolha o campos que deseja atualizar:");
                                    System.out.println("1. Nome");
                                    System.out.println("2. Data de Nascimento");
                                    System.out.println("3. Cidade");
                                    System.out.println("4. Serie");
                                    System.out.println("5. Turma");
                                    System.out.println("6. Telefone");
                                    System.out.println("7. Todos os dados");
                                    System.out.println("Digite a opção escolhida:");
    
                                    String opcao = scan.nextLine();

                                    switch (opcao) {
                                        case "1":
                                            System.out.println("Novo nome: ");
                                            student.setName(scan.nextLine());
                                            break;
                                        case "2":
                                            System.out.println("Nova data de nascimento ( favor digitar no formato dia/mês/ano):");
                                            String data = scan.nextLine();
                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                            student.setDateBirth(LocalDate.parse(data, formatter));
                                            break;
                                        case "3":
                                            System.out.println("Nova cidade:");
                                            student.setCity(scan.nextLine());
                                            break;
                                        case "4":
                                            System.out.println("Nova série:");
                                            student.setSerie(scan.nextLine());
                                            break;
                                        case "5":
                                            System.out.println("Nova turma: ");
                                            student.setTeam(scan.nextLine());
                                            break;
                                        case "6":
                                            System.out.println("Novo telefone:");
                                            student.setFone(scan.nextLine());
                                            break;
                                        case "7":
                                            System.out.println("Novo nome: ");
                                            student.setName(scan.nextLine());

                                            System.out.println("Nova data de nascimento ( favor digitar no formato dia/mês/ano):");
                                            String novaData = scan.nextLine();

                                            DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                            try{
                                                LocalDate dataConvertida = LocalDate.parse(novaData,formatar);
                                                student.setDateBirth(dataConvertida);
                                            }catch(DateTimeParseException e){
                                                System.out.println("Formato de data inválido. Use DD/MM/AAAA");
                                                return;
                                            }

                                                System.out.println("Nova cidade:");
                                                student.setCity(scan.nextLine());

                                                System.out.println("Nova série:");
                                                student.setSerie(scan.nextLine());

                                                System.out.println("Nova turma: ");
                                                student.setTeam(scan.nextLine());

                                                System.out.println("Novo telefone:");
                                                student.setFone(scan.nextLine());
                                                break;
                                                          
                                        default:
                                        System.out.println("Opção inválida.");
                                        return;                                           
                                    }

                        }
                            studentDAO.update(student);
                            System.out.println("Atualização concluída!");
                            System.out.println(student.getName());
                            }
                    }
                                         
                    
            private void deleteStudent(){
                Scanner scan = new Scanner(System.in);

                System.out.println(" Digite o RA do estudante que deseja excluir do sistema: ");
                String ra = scan.nextLine();

                try{
                    Student student = studentService.searchStudentByRa(ra);

                    System.out.println("Estudante encontrado: " + student.getName() + " da turma " +student.getSerie() +  student.getTeam()+".");
                    System.out.println("Deseja excluir os dados deste estudante? Digite 'SIM' para excluir ou 'NÃO' para não excluir.");

                            String escolha = scan.nextLine().trim().toLowerCase();

                            if(escolha.equals("s") || escolha.equals("sim")){
                                studentService.deleteStudentData(ra);
                                System.out.println("Cadastro do estudante excluído com sucesso.");
                            }else{
                            System.out.println("Estudante não encontrado, favor confirmar os dados para nova solicitação.");
                            }
                }catch (RuntimeException e){
                    System.out.println("Erro: " + e.getMessage());

                }
                scan.close();
            }

        private EmailService emailService;

            public void mediationStudent(Scanner scan, StudentService studentService){

                System.out.println("Solicitação de Mediação.");

                System.out.println("Por favor, digite a sua matrícula: ");
                    String license = scan.nextLine();
                
               

                System.out.println("Digite o RA(Registro do Aluno):");
                    String ra = scan.nextLine();
                
                System.out.println("Buscando perfil do estudante.   ");

                    try{
                        Student student = studentService.searchStudentByRa(ra);    

                        System.out.println("Você deseja fazer a solicitação para este estudante? Digite 'SIM' para confirmar ou 'NÃO' para cancelar.");
                        System.out.println("Nome: "+ student.getName());
                        System.out.println("RA: " + student.getRa());
                        System.out.println("Turma: " + student.getSerie() + " " + student.getTeam());
    
                       String confirm = scan.nextLine();

                       if(confirm.equalsIgnoreCase("SIM")){
                        System.out.println("Por favor digite o motivo da solicitação e as ações já realizadas: ");
                            String reason = scan.nextLine();
   
                            System.out.println("Digite seu e-mail:");
                                String email = scan.nextLine();
   
                            RequestDto dto = new RequestDto(
                            student.getName(),
                            email,
                            license,                             
                            " Solicitação de mediação para o estudante: " + student.getName() + 
                            "\nRA: " + student.getRa() + 
                            "\nMotivo: " + reason + 
                            "\nSolicitante: " + license
                            );
                    
                            emailService.sendEmail(dto);

                            System.out.println("SolicitaÇÃO ENVIADA COM SUCESSO!");
                        } else{
                                System.out.println("Solicitação cancelada");
                        }
                        
                        }catch(RuntimeException e){
                            System.out.println("Erro: " + e.getMessage());
                        }

                    }

            }

            
