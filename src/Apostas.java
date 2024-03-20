import java.util.ArrayList;
import java.util.Scanner;

public class Apostas {
    private static final int MAX_TENTATIVAS = 25;

    public static void fazerNovaAposta(Scanner scanner) {
        System.out.print("Digite seu nome: ");
        scanner.nextLine();
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

    public static void fazerApostaSurpresa(Scanner scanner) {
        System.out.print("Digite seu nome: ");
        scanner.nextLine(); // Limpar o buffer do scanner
        String nome = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        SorteioNumeros megaSena = new SorteioNumeros();
        ArrayList<Integer> apostaSurpresa = megaSena.gerarApostaSurpresa();
        System.out.println("\nAposta surpresa gerada pelo sistema: " + apostaSurpresa);

        // Mostrar os números sorteados
        megaSena.mostrarNumerosSorteados();

        // Verificar se o jogador ganhou
        if (megaSena.verificarAposta(apostaSurpresa)) {
            System.out.println("Parabéns, " + nome + "! Aposta surpresa ganhou! Você agora é um milionário");
        } else {
            System.out.println("Que pena, " + nome + ". Não foi dessa vez...");
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
                    System.out.println("Número inválido. O número dever ser de 1 a 50.");
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

    public static void verificarResultado(Pessoa usuario, ArrayList<Integer> aposta, SorteioNumeros megaSena) {
        megaSena.mostrarNumerosSorteados();

        if (megaSena.verificarAposta(aposta)) {
            System.out.println("Parabéns, " + usuario.getNome() + "! Você agora é um milionário!");
        } else {
            System.out.println("Puxa, " + usuario.getNome() + ". não foi dessa vez que você conseguiu...");

            System.out.println("\nRealizando nova aposta...");
            ArrayList<Integer> novaAposta = solicitarAposta(new Scanner(System.in));
            if (validarAposta(novaAposta)) {
                verificarResultado(usuario, novaAposta, megaSena);
            } else {
                System.out.println(
                        "Aposta inválida. Os números devem ser de 1 a 50.");
            }
        }
    }
}