package com.example.client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MenuPerguntas {
    private static final String API_URL = "http://localhost:8080/contas";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU CONTAS BANCÁRIAS ---");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Buscar conta por ID");
            System.out.println("4 - Depositar");
            System.out.println("5 - Sacar");
            System.out.println("6 - Ativar/Desativar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarConta(scanner);
                case 2 -> listarContas();
                case 3 -> buscarContaPorId(scanner);
                case 4 -> depositar(scanner);
                case 5 -> sacar(scanner);
                case 6 -> ativarDesativar(scanner);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void criarConta(Scanner scanner) throws IOException {
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Nome do titular: ");
        String nomeTitular = scanner.nextLine();
        System.out.print("Conta ativa? (true/false): ");
        boolean ativa = scanner.nextBoolean();

        String json = String.format("{\"numeroConta\":\"%s\",\"nomeTitular\":\"%s\",\"ativa\":%s}", 
                                     numeroConta, nomeTitular, ativa);

        sendRequest(API_URL, "POST", json);
    }

    private static void listarContas() throws IOException {
        sendRequest(API_URL, "GET", null);
    }

    private static void buscarContaPorId(Scanner scanner) throws IOException {
        System.out.print("ID da conta: ");
        long id = scanner.nextLong();
        sendRequest(API_URL + "/" + id, "GET", null);
    }

    private static void depositar(Scanner scanner) throws IOException {
        System.out.print("ID da conta: ");
        long id = scanner.nextLong();
        System.out.print("Valor a depositar: ");
        double valor = scanner.nextDouble();
        String json = String.format("{\"valor\": %s}", valor);
        sendRequest(API_URL + "/" + id + "/depositar", "PUT", json);
    }

    private static void sacar(Scanner scanner) throws IOException {
        System.out.print("ID da conta: ");
        long id = scanner.nextLong();
        System.out.print("Valor a sacar: ");
        double valor = scanner.nextDouble();
        String json = String.format("{\"valor\": %s}", valor);
        sendRequest(API_URL + "/" + id + "/sacar", "PUT", json);
    }

    private static void ativarDesativar(Scanner scanner) throws IOException {
        System.out.print("ID da conta: ");
        long id = scanner.nextLong();
        System.out.print("Ativar (true) ou Desativar (false): ");
        boolean ativa = scanner.nextBoolean();
        sendRequest(API_URL + "/" + id + "/status?ativa=" + ativa, "PUT", null);
    }

    private static void sendRequest(String urlStr, String method, String body) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");

        if (body != null && !body.isEmpty()) {
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        int code = con.getResponseCode();
        System.out.println("Status HTTP: " + code);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println("Resposta: " + response);
        } catch (IOException e) {
            System.out.println("Erro ao ler resposta.");
        }
    }
}
