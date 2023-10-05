package br.com.intrabank.views;

import br.com.intrabank.controllers.VendaController;
import br.com.intrabank.models.Venda;

import java.util.List;
import java.util.Scanner;

public class VendaView {
    private VendaController vendaController;
    private Scanner scanner;

    public VendaView(VendaController vendaController, Scanner scanner) {
        this.vendaController = vendaController;
        this.scanner = scanner;
    }

    public VendaView(VendaController vendaController) {
    }

    public void realizarVenda() {
        System.out.print("Informe o ID do produto: ");
        int produtoId = scanner.nextInt();
        System.out.print("Informe a quantidade desejada: ");
        int quantidade = scanner.nextInt();
        int vendaId = vendaController.realizarVenda(produtoId, quantidade);

        if (vendaId != -1) {
            System.out.println("Venda realizada com sucesso!!" );
        } else {
            System.out.println("Não foi possível realizar a venda. Verifique o estoque.");
        }
    }

    public void listarVendas(List<Venda> vendas) {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada.");
        } else {
            System.out.println("\nListagem de Vendas:");
            for (Venda venda : vendas) {
                System.out.println("\nID da Venda: " + venda.getId());
                System.out.println("ID do Produto: " + venda.getIdProduto());
                System.out.println("Quantidade: " + venda.getQuantidade());
                System.out.println("Data: " + venda.getData());
                System.out.println("=======================");
            }
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
