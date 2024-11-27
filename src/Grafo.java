import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    Map<String, List<Aresta>> listaAdjacente = new HashMap<>();

    void adicionarCidade(String cidade) {
        listaAdjacente.putIfAbsent(cidade, new ArrayList<>());
    }

    void adicionarAresta(String origem, String destino, int distancia, int custo) {
        listaAdjacente.get(origem).add(new Aresta(destino, distancia, custo));
        listaAdjacente.get(destino).add(new Aresta(origem, distancia, custo));
    }

    void removerAresta(String origem, String destino) {
        listaAdjacente.get(origem).removeIf(aresta -> aresta.destino.equals(destino));
        listaAdjacente.get(destino).removeIf(aresta -> aresta.destino.equals(origem));
    }

    List<Aresta> obterArestas(String cidade) {
        return listaAdjacente.get(cidade);
    }
}
