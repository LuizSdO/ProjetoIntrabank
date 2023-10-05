package br.com.intrabank.views;

import br.com.intrabank.models.Vendedor;

import java.util.List;

public class VendedorView {
    public void mostrarDetalhesVendedor(Vendedor vendedor) {
        if (vendedor != null) {
            System.out.println("Detalhes do Vendedor:");
            System.out.println("ID: " + vendedor.getId());
            System.out.println("Nome: " + vendedor.getNome());
            System.out.println("Email: " + vendedor.getEmail());
        } else {
            System.out.println("Vendedor não encontrado.");
        }
    }

    public void listarVendedores(List<Vendedor> vendedores) {
        if (!vendedores.isEmpty()) {
            System.out.println("Lista de Vendedores:");
            for (Vendedor vendedor : vendedores) {
                System.out.println("ID: " + vendedor.getId() + ", Nome: " + vendedor.getNome() + ", Email: " + vendedor.getEmail());
            }
        } else {
            System.out.println("Nenhum vendedor encontrado.");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
