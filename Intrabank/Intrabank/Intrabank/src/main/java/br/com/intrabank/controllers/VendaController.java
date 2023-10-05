package br.com.intrabank.controllers;

import br.com.intrabank.models.Venda;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaController {
    private List<Venda> vendas;
    private int proximoId;
    private ProdutoController produtoController;
    private EstoqueController estoqueController;

    public VendaController(ProdutoController produtoController, EstoqueController estoqueController) {
        this.vendas = new ArrayList<>();
        this.proximoId = 1;
        this.produtoController = produtoController;
        this.estoqueController = estoqueController;
    }

    public int obterProximoIdVenda() {
        return proximoId++;
    }

    public int realizarVenda(int idProduto, int quantidade) {
        if (produtoController.obterProdutoPorId(idProduto) != null &&
                estoqueController.consultarEstoque(idProduto) >= quantidade) {

            Venda venda = new Venda(obterProximoIdVenda(), idProduto, quantidade, new Date());
            vendas.add(venda);

            salvarVendasEmArquivo("vendas.txt");

            estoqueController.atualizarEstoque(idProduto, -quantidade);

            return venda.getId();
        } else {
            return -1;
        }
    }

    public List<Venda> listarVendas() {
        return vendas;
    }

    public void salvarVendasEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Venda venda : vendas) {
                writer.write(venda.getId() + "," + venda.getIdProduto() + "," + venda.getQuantidade() + "," + venda.getData());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
