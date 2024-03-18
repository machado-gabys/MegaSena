import java.util.ArrayList;
import java.util.Scanner;

public class MegaSena {
    private static final int MAX_TENTATIVAS = 25;

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
                fazerNovaAposta(scanner);
            } else if (opcao == 2) {
                fazerApostaSurpresa();
            } else if (opcao != 0) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void fazerNovaAposta(Scanner scanner) {
        System.out.print("Digite seu nome: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String nome = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        Pessoa usuario = new Pessoa(nome, cpf);
        SorteioNumeros megaSena = new SorteioNumeros();
        int tentativas = 0;

        while (tentativas < MAX_TENTATIVAS) {
            ArrayList<Integer> aposta = solicitarAposta(scanner);

            if (validarAposta(aposta)) {
                verificarResultado(usuario, aposta, megaSena);
                break;
            } else {
                System.out.println(
                        "Aposta inválida. Os números devem estar no intervalo de 1 a 50 e não devem se repetir.");
                tentativas++;
                System.out.println("Tentativas restantes: " + (MAX_TENTATIVAS - tentativas));
            }
        }

        if (tentativas == MAX_TENTATIVAS) {
            System.out.println("Limite de tentativas alcançado. Obrigado por jogar!");
        }
    }

    private static ArrayList<Integer> solicitarAposta(Scanner scanner) {
        ArrayList<Integer> aposta = new ArrayList<>();
        System.out.println("\nFaça sua aposta:");
        for (int i = 0; i < 5; i++) {
            int numero;
            boolean numeroValido = false;
            do {
                System.out.print("Digite o número " + (i + 1) + ": ");
                numero = scanner.nextInt();
                if (numero < 1 || numero > 50) {
                    System.out.println("Número inválido. Deve estar no intervalo de 1 a 50.");
                } else if (aposta.contains(numero)) {
                    System.out.println("Número já escolhido. Escolha outro número.");
                } else {
                    numeroValido = true;
                }
            } while (!numeroValido);
            aposta.add(numero);
        }
        return aposta;
    }

    private static boolean validarAposta(ArrayList<Integer> aposta) {
        for (int numero : aposta) {
            if (numero < 1 || numero > 50 || aposta.indexOf(numero) != aposta.lastIndexOf(numero)) {
                return false;
            }
        }
        return true;
    }

    private static void fazerApostaSurpresa() {
        SorteioNumeros megaSena = new SorteioNumeros();
        System.out.println("\nAposta surpresa gerada pelo sistema: " + megaSena.gerarApostaSurpresa());
    }

    private static void verificarResultado(Pessoa usuario, ArrayList<Integer> aposta, SorteioNumeros megaSena) {
        megaSena.mostrarNumerosSorteados(); // Mostrar os números sorteados

        if (megaSena.verificarAposta(aposta)) {
            System.out.println("Parabéns, " + usuario.getNome() + "! Você acertou!");
        } else {
            System.out.println("Que pena, " + usuario.getNome() + ". Você não acertou.");

            // Realizar nova aposta
            System.out.println("\nRealizando nova aposta...");
            ArrayList<Integer> novaAposta = solicitarAposta(new Scanner(System.in));
            if (validarAposta(novaAposta)) {
                verificarResultado(usuario, novaAposta, megaSena);
            } else {
                System.out.println(
                        "Aposta inválida. Os números devem estar no intervalo de 1 a 50 e não devem se repetir.");
            }
        }
    }
}
