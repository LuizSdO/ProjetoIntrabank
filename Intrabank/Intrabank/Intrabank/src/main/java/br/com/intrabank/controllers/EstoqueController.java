package br.com.intrabank.controllers;

import br.com.intrabank.models.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueController {
    private Map<Integer, Integer> estoque;

    public EstoqueController() {
        this.estoque = new HashMap<>();
    }

    public void inicializarEstoque(List<Produto> produtos) {
        for (Produto produto : produtos) {
            estoque.put(produto.getId(), produto.getEstoque());
        }
    }


    public int consultarEstoque(int idProduto) {
        Integer quantidade = estoque.get(idProduto);
        return quantidade != null ? quantidade : 0;
    }

    public void atualizarEstoque(int idProduto, int quantidade) {
        int estoqueAtual = consultarEstoque(idProduto);
        estoque.put(idProduto, estoqueAtual + quantidade);
    }

    public void listarEstoque() {
    }

}
