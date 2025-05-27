package com.schoolApplication.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentBuilder {
   private final String ra;
   private final String name;
   private final String cpf;
   private LocalDate dateBirth;
   private String fone;
   private String city;
   private String serie;
   private String team;

   public StudentBuilder(String var1, String var2, String var3, String var4) {
      if (var2 != null && !var2.isEmpty()) {
         if (var1 != null && !var1.isEmpty()) {
            if (var3 == null) {
               throw new IllegalArgumentException("O campo 'CPF' é obrigatório e deve ser válido.");
            } else if (var4 != null && !var4.isEmpty()) {
               DateTimeFormatter var5 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               this.dateBirth = LocalDate.parse(var4, var5);
               this.name = var2;
               this.ra = var1;
               this.cpf = var3;
            } else {
               throw new IllegalArgumentException("O campos 'data de nascimento' é obrigatório.");
            }
         } else {
            throw new IllegalArgumentException("O campo 'RA' é obrigatório");
         }
      } else {
         throw new IllegalArgumentException(" O campo 'Nome' é obrigatório");
      }
   }

   public StudentBuilder dateBirth(String var1) {
      this.dateBirth = LocalDate.parse(var1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      return this;
   }

   public LocalDate getDateBirth() {
      return this.dateBirth;
   }

   public StudentBuilder city(String var1) {
      this.city = var1;
      return this;
   }

   public StudentBuilder serie(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         if (!var1.matches("[1-9]")) {
            throw new IllegalArgumentException("O campo 'Serie' deve conter apenas um número de 1 á 9.");
         } else {
            this.serie = var1;
            return this;
         }
      } else {
         throw new IllegalArgumentException("O campo 'Serie' é obrigatório.");
      }
   }

   public StudentBuilder team(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         if (var1.length() != 1) {
            throw new IllegalArgumentException("O campo 'Turma' deve contar com apenas uma única letra.");
         } else {
            this.team = var1.toUpperCase();
            return this;
         }
      } else {
         throw new IllegalArgumentException("O campo'Turma' é obrigatório.");
      }
   }

   public StudentBuilder fone(String var1) {
      this.fone = var1;
      return this;
   }

   public Student build() {
      return new Student(this);
   }
}
