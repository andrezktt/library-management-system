package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Conexão com banco de dados estabelecida!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }
}