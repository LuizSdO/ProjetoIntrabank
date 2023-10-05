package br.com.intrabank.models;

import java.util.Date;

public class Venda {
    private int id;
    private int idProduto;
    private int quantidade;
    private Date data;

    public Venda(int id, int idProduto, int quantidade, Date data) {
        this.id = id;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Date getData() {
        return data;
    }
}
