
import java.util.Scanner;

public class MegaSena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Mega Sena! Boa sorte!");

        int op = 0;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Fazer aposta");
            System.out.println("2. Fazer aposta surpresa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

            switch (op) {
                case 0:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
                case 1:
                    Apostas.fazerNovaAposta(scanner);
                    break;
                case 2:
                    Apostas.fazerApostaSurpresa(scanner);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (op != 0);
    }
}
