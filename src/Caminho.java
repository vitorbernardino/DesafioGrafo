import java.util.*;

public class Caminho {
    List<String> cidades = new ArrayList<>();
    int distanciaTotal = 0;
    int custoTotal = 0;

    Caminho() {}

    Caminho(Caminho caminho) {
        this.cidades.addAll(caminho.cidades);
        this.distanciaTotal = caminho.distanciaTotal;
        this.custoTotal = caminho.custoTotal;
    }


    public static Caminho encontrarRotaOtima(Grafo grafo, String origem, String destino, int limiteCusto, Set<String> arestasBloqueadas) {
        PriorityQueue<Caminho> fila = new PriorityQueue<>(Comparator.comparingInt(c -> c.custoTotal));
        Caminho caminhoInicial = new Caminho();
        caminhoInicial.cidades.add(origem);
        fila.add(caminhoInicial);

        Caminho menorCaminhoExcedendoCusto = null;

        while (!fila.isEmpty()) {
            Caminho caminhoAtual = fila.poll();
            String ultimaCidade = caminhoAtual.cidades.get(caminhoAtual.cidades.size() - 1);

            if (ultimaCidade.equals(destino)) {
                if (caminhoAtual.custoTotal <= limiteCusto) {
                    return caminhoAtual;
                }
                if (menorCaminhoExcedendoCusto == null || caminhoAtual.distanciaTotal < menorCaminhoExcedendoCusto.distanciaTotal) {
                    menorCaminhoExcedendoCusto = caminhoAtual;
                }
                continue;
            }

            for (Aresta aresta : grafo.obterArestas(ultimaCidade)) {
                if (arestasBloqueadas.contains(ultimaCidade + "-" + aresta.destino)) continue;

                if (!caminhoAtual.cidades.contains(aresta.destino)) {
                    Caminho novoCaminho = new Caminho(caminhoAtual);
                    novoCaminho.cidades.add(aresta.destino);
                    novoCaminho.distanciaTotal += aresta.distancia;
                    novoCaminho.custoTotal += aresta.custo;
                    fila.add(novoCaminho);
                }
            }
        }

        return menorCaminhoExcedendoCusto;
}
}
