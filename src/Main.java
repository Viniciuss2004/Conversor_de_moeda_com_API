import Service.ConversorMoeda;
import Service.ListarMoedas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ConversorMoeda conversor = new ConversorMoeda();
        ListarMoedas listar = new ListarMoedas();

        int opcao = 0;
        while (opcao != 3) {
            System.out.println("**********************************************************************");
            System.out.println("\u001B[32m             Seja bem-vindo/a ao Conversor de Moeda =]\n\u001B[0m");
            System.out.println("Escolha uma opção:\n");
            System.out.print("1 - Converter Moeda\n2 - Listar Moedas\n3 - Sair\n\nR:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\u001B[32m\nOBS: Digite apenas o código da moeda, por exemplo (BRL, USD, EUR...)\u001B[0m");

                    System.out.print("\nDigite o código da moeda que deseja converter: ");
                    String moeda1 = sc.nextLine();

                    System.out.print("\nDigite o código da moeda para qual será convertida: ");
                    String moeda2 = sc.nextLine();

                    conversor.converterMoeda(moeda1, moeda2);
                    break;

                case 2:
                    listar.listarMoedas();
                    break;

                case 3:
                    System.out.println("\u001B[32m\nSaindo...");
                    sc.close();
                    break;

                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        }
    }
}