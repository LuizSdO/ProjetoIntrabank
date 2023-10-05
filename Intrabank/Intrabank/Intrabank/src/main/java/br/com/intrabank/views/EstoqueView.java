package br.com.intrabank.views;

import br.com.intrabank.controllers.EstoqueController;
import java.util.Scanner;

public class EstoqueView {
    private EstoqueController estoqueController;
    private Scanner scanner;

    public EstoqueView(EstoqueController estoqueController) {
        this.estoqueController = estoqueController;
        this.scanner = scanner;
    }

    public void menuEstoque() {
        int opcao;
        do {
            System.out.println("\n=-=-=-=Menu de Estoque:-=-=-=-");
            System.out.println("1- Atualizar Estoque");
            System.out.println("2- Consultar Estoque");
            System.out.println("3- Listar Estoque");
            System.out.println("0- Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    atualizarEstoque();
                    break;
                case 2:
                    consultarEstoque();
                    break;
                case 3:
                    estoqueController.listarEstoque();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void atualizarEstoque() {
        System.out.print("Informe o ID do produto: ");
        int produtoId = scanner.nextInt();
        System.out.print("Informe a quantidade: ");
        int quantidade = scanner.nextInt();

        estoqueController.atualizarEstoque(produtoId, quantidade);
    }

    public void consultarEstoque() {
        System.out.print("Informe o ID do produto: ");
        int produtoId = scanner.nextInt();
        int estoque = estoqueController.consultarEstoque(produtoId);
        if (estoque != 0) {
            System.out.println("Estoque atual do produto: " + estoque);
        } else {
            System.out.println("Produto não encontrado no estoque.");
        }
    }

    public void mostrarMensagem(String s) {
        System.out.println(s);
    }
}
