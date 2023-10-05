package br.com.intrabank.views;

import br.com.intrabank.models.Cliente;

import java.util.List;

public class ClienteView {
    public void mostrarDetalhesCliente(Cliente cliente) {
        if (cliente != null) {
            System.out.println("\nDetalhes do Cliente:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void listarClientes(List<Cliente> clientes) {
        if (!clientes.isEmpty()) {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                mostrarDetalhesCliente(cliente);
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("Não foi possível encontrar o cliente.");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
