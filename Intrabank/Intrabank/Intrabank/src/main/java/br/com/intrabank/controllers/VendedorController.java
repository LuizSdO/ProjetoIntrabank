package br.com.intrabank.controllers;

import br.com.intrabank.models.Vendedor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VendedorController {
    private List<Vendedor> vendedores;
    private int proximoId;

    public VendedorController() {
        this.vendedores = new ArrayList<>();
        this.proximoId = 1;
    }

    public int criarVendedor(String nome, String email) {
        int id = proximoId;
        Vendedor vendedor = new Vendedor(id, nome, email);
        vendedores.add(vendedor);
        proximoId++;

        salvarVendedoresEmArquivo("vendedores.txt");
        return id;
    }

    public List<Vendedor> listarVendedores() {
        return vendedores;
    }

    public boolean deletarVendedor(int idVendedor) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getId() == idVendedor) {
                vendedores.remove(vendedor);
                salvarVendedoresEmArquivo("vendedores.txt");
                return true;
            }
        }
        return false;
    }

    public boolean atualizarVendedor(int idVendedor, String novoNome, String novoEmail) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getId() == idVendedor) {
                vendedor.setNome(novoNome);
                vendedor.setEmail(novoEmail);
                salvarVendedoresEmArquivo("vendedores.txt");
                return true;
            }
        }
        return false;
    }

    private void salvarVendedoresEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("ID\tNome\tEmail");
            writer.newLine();

            for (Vendedor vendedor : vendedores) {
                writer.write(vendedor.getId() + "\t" + vendedor.getNome() + "\t" + vendedor.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
