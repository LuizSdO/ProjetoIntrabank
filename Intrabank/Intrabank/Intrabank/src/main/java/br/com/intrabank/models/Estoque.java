package br.com.intrabank.models;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<EstoqueItem> itens;

    public Estoque() {
        this.itens = new ArrayList<>();
    }

    public List<EstoqueItem> getItens() {
        return itens;
    }

    public void setItens(List<EstoqueItem> itens) {
        this.itens = itens;
    }

    public void adicionarItem(EstoqueItem item) {
        itens.add(item);
    }

    public void removerItem(EstoqueItem item) {
        itens.remove(item);
    }

    public EstoqueItem encontrarItemPorProduto(Produto produto) {
        for (EstoqueItem item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                return item;
            }
        }
        return null;
    }
}
