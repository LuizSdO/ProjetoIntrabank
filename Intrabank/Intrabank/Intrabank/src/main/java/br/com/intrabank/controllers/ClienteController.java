package br.com.intrabank.controllers;

import br.com.intrabank.models.Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;
    private int proximoId;

    public ClienteController() {
        this.clientes = new ArrayList<>();
        this.proximoId = 1;
    }

    public int criarCliente(String nome, String email) {
        int id = proximoId++;
        Cliente novoCliente = new Cliente(id, nome, email);
        clientes.add(novoCliente);

        salvarClientesEmArquivo("clientes.txt");

        return id;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente obterClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public boolean atualizarCliente(int id, String novoNome, String novoEmail) {
        Cliente cliente = obterClientePorId(id);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setEmail(novoEmail);
            salvarClientesEmArquivo("clientes.txt");
            return true;
        }
        return false;
    }

    public boolean deletarCliente(int id) {
        Cliente cliente = obterClientePorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
            salvarClientesEmArquivo("clientes.txt");
            return true;
        }
        return false;
    }

    public void salvarClientesEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("ID\tNome\tEmail");
            writer.newLine();

            for (Cliente cliente : clientes) {
                writer.write(cliente.getId() + "\t" + cliente.getNome() + "\t" + cliente.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
