import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.adicionarCidade("A");
        grafo.adicionarCidade("B");
        grafo.adicionarCidade("C");
        grafo.adicionarCidade("D");
        grafo.adicionarCidade("E");

        grafo.adicionarAresta("A", "B", 10, 5);
        grafo.adicionarAresta("B", "C", 20, 15);
        grafo.adicionarAresta("C", "E", 25, 10);
        grafo.adicionarAresta("B", "D", 30, 20);
        grafo.adicionarAresta("A", "E", 50, 40);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a cidade de partida:");
        String origem = scanner.nextLine();

        System.out.println("Digite a cidade de destino:");
        String destino = scanner.nextLine();

        System.out.println("Digite o custo máximo:");
        int limiteCusto = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite as estradas bloqueadas (ex.: B-C), separadas por vírgulas:");
        String bloqueiosInput = scanner.nextLine();
        Set<String> arestasBloqueadas = new HashSet<>(Arrays.asList(bloqueiosInput.split(",")));

        Caminho resultado = Caminho.encontrarRotaOtima(grafo, origem, destino, limiteCusto, arestasBloqueadas);

        if (resultado == null) {
            System.out.println("Nenhuma rota disponível.");
        } else if (resultado.custoTotal > limiteCusto) {
            System.out.println("Nenhuma rota dentro do limite de custo. Rota alternativa sugerida:");
            System.out.println("Rota: " + resultado.cidades);
            System.out.println("Distância: " + resultado.distanciaTotal + " km");
            System.out.println("Custo: " + resultado.custoTotal);
        } else {
            System.out.println("Rota ótima encontrada:");
            System.out.println("Rota: " + resultado.cidades);
            System.out.println("Distância: " + resultado.distanciaTotal + " km");
            System.out.println("Custo: " + resultado.custoTotal);
        }
    }
}
