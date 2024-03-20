import java.util.ArrayList;
import java.util.Random;

class SorteioNumeros {
    private ArrayList<Integer> numerosSorteados;

    public SorteioNumeros() {
        numerosSorteados = new ArrayList<>();
        sortearNumeros();
    }

    private void sortearNumeros() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int numero = random.nextInt(50) + 1;
            numerosSorteados.add(numero);
        }
    }

    public boolean verificarAposta(ArrayList<Integer> aposta) {
        return numerosSorteados.containsAll(aposta);
    }

    public void mostrarNumerosSorteados() {
        System.out.println("Números sorteados: " + numerosSorteados);
    }

    public ArrayList<Integer> gerarApostaSurpresa() {
        ArrayList<Integer> apostaSurpresa = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int numero = random.nextInt(50) + 1;
            apostaSurpresa.add(numero);
        }
        int numeroExtra = random.nextInt(50) + 1;
        apostaSurpresa.add(numeroExtra);
        return apostaSurpresa;
    }
}