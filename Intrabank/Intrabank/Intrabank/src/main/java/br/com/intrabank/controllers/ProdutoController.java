package br.com.intrabank.controllers;

import br.com.intrabank.models.Produto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private List<Produto> produtos;
    private int proximoId;
    private EstoqueController estoqueController;

    public ProdutoController(EstoqueController estoqueController) {
        this.produtos = new ArrayList<>();
        this.proximoId = 1;
        this.estoqueController = estoqueController;
    }

    public int criarProduto(String nome, double preco, int estoqueInicial) {
        int id = proximoId++;
        Produto novoProduto = new Produto(id, nome, preco, estoqueInicial);
        produtos.add(novoProduto);

        List<Produto> produtosAtuais = new ArrayList<>();
        produtosAtuais.add(novoProduto);
        estoqueController.inicializarEstoque(produtosAtuais);
        salvarProdutosEmArquivo("produtos.txt");

        return id;
    }



    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto obterProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public boolean atualizarProduto(int id, String novoNome, double novoPreco) {
        Produto produto = obterProdutoPorId(id);
        if (produto != null) {
            produto.setNome(novoNome);
            produto.setPreco(novoPreco);
            return true;
        }
        return false;
    }

    public boolean deletarProduto(int id) {
        Produto produto = obterProdutoPorId(id);
        if (produto != null) {
            produtos.remove(produto);
            return true;
        }
        return false;
    }
    public void salvarProdutosEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("ID\tNome\tPreço\tEstoque");
            writer.newLine();

            for (Produto produto : produtos) {
                writer.write(produto.getId() + "\t" + produto.getNome() + "\t" + produto.getPreco() + "\t" + produto.getEstoque());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
