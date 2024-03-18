import java.util.Scanner;

public class MegaSena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Mega Sena!");

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Fazer aposta");
            System.out.println("2. Fazer aposta surpresa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            if (opcao == 1) {
                Apostas.fazerNovaAposta(scanner);
            } else if (opcao == 2) {
                Apostas.fazerApostaSurpresa(scanner);
            } else if (opcao != 0) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
