package br.com.intrabank.views;

import br.com.intrabank.controllers.ProdutoController;
import br.com.intrabank.models.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    private ProdutoController produtoController;
    private Scanner scanner;

    public ProdutoView(ProdutoController produtoController, Scanner scanner) {
        this.produtoController = produtoController;
        this.scanner = scanner;
    }

    public ProdutoView() {

    }

    public void mostrarDetalhesProduto(Produto produto) {
        System.out.println("Detalhes do Produto:");
        System.out.println("ID: " + produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Estoque: " + produto.getEstoque());
    }

    public void listarProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Não foi possível encontrar este produto.");
        } else {
            System.out.println("\n     Lista de Produtos");
            for (Produto produto : produtos) {
                System.out.println("\nID do Produto: " + produto.getId());
                System.out.println("Nome do produto: " + produto.getNome());
                System.out.println("Preço: " + produto.getPreco());
                System.out.println("Estoque: " + produto.getEstoque());
                System.out.println("------------------------------");
            }
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
