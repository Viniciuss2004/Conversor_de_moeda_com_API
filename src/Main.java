import ExceptionP.OpcaoInvalidaException;
import Service.ConversorMoeda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ConversorMoeda conversor = new ConversorMoeda();

        while (true) {
            System.out.println("****************************************");
            System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]\n");
            System.out.print("1) Dólar ⟶ Peso Argentino" +
                    "\n2) Peso Argentino ⟶ Dólar" +
                    "\n3) Dólar ⟶ Real Brasileiro" +
                    "\n4) Real Brasileiro ⟶ Dólar" +
                    "\n5) Dólar ⟶ Peso Colombiano" +
                    "\n6) Peso Colombiano ⟶ Dólar" +
                    "\n7) Sair\n\n");
            System.out.print("Escolha uma opção válida: ");

            int opcao = 0;
            try {
                opcao = sc.nextInt();
                sc.nextLine();
                if (opcao < 1 || opcao > 7) {
                    throw new OpcaoInvalidaException("Opção inválida número " + opcao + " não está nas opções!");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Digite apenas números inteiros entre 1 e 7!");
                continue;
            }

            System.out.print("****************************************\n\n");

            conversor.converterMoeda(opcao);
        }
    }
}