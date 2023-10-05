package org.example;

import br.com.intrabank.controllers.*;
import br.com.intrabank.views.*;

import java.util.Scanner;

public class IntrabankApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstoqueController estoqueController = new EstoqueController();
        ProdutoController produtoController = new ProdutoController(estoqueController);
        VendaController vendaController = new VendaController(produtoController, estoqueController);
        ClienteController clienteController = new ClienteController();
        ClienteView clienteView = new ClienteView();
        VendedorController vendedorController = new VendedorController();
        VendedorView vendedorView = new VendedorView();
        ProdutoView produtoView = new ProdutoView();
        VendaView vendaView = new VendaView(vendaController);
        EstoqueView estoqueView = new EstoqueView(estoqueController);

        int opcao;
        do {
            System.out.println("\n=-=-=-=-=-=-Menu Principal=-=-=-=-=-=-");
            System.out.println("1- Gerenciar Produtos");
            System.out.println("2- Gerenciar Vendas");
            System.out.println("3- Gerenciar Estoque");
            System.out.println("4- Gerenciar Clientes");
            System.out.println("5- Gerenciar Vendedores");
            System.out.println("0- Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciarProdutos(scanner, produtoController, produtoView);
                    break;
                case 2:
                    gerenciarVendas(scanner, vendaController, vendaView);
                    break;
                case 3:
                    gerenciarEstoque(scanner, estoqueController, estoqueView);
                    break;
                case 4:
                    gerenciarClientes(scanner, clienteController, clienteView);
                    break;
                case 5:
                    gerenciarVendedores(scanner, vendedorController, vendedorView);
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarVendedor(Scanner scanner, VendedorController vendedorController, VendedorView vendedorView) {
    }

    private static void gerenciarProdutos(Scanner scanner, ProdutoController produtoController, ProdutoView produtoView) {
        int opcao;
        do {
            System.out.println("\n=-=-=-Menu de Produtos=-=-=-");
            System.out.println("\n1- Criar Produto");
            System.out.println("2- Listar Produtos");
            System.out.println("3- Atualizar Produto");
            System.out.println("4- Deletar Produto");
            System.out.println("0- Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarProduto(scanner, produtoController, produtoView);
                    break;
                case 2:
                    produtoView.listarProdutos(produtoController.listarProdutos());
                    break;
                case 3:
                    atualizarProduto(scanner, produtoController, produtoView);
                    break;
                case 4:
                    deletarProduto(scanner, produtoController, produtoView);
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarProduto(Scanner scanner, ProdutoController produtoController, ProdutoView produtoView) {
        scanner.nextLine();
        System.out.print("\nInforme o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o preço do produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Informe a quantidade em estoque: ");
        int estoqueInicial = scanner.nextInt();

        int id = produtoController.criarProduto(nome, preco, estoqueInicial);
        produtoView.mostrarMensagem("\nProduto cadastrado com sucesso!");
    }

    private static void atualizarProduto(Scanner scanner, ProdutoController produtoController, ProdutoView produtoView) {
        System.out.print("\nInforme o ID do produto que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Informe nome do produto: ");
        String novoNome = scanner.nextLine();
        System.out.print("Informe o novo preço do produto: ");
        double novoPreco = scanner.nextDouble();

        if (produtoController.atualizarProduto(id, novoNome, novoPreco)) {
            produtoView.mostrarMensagem("\nProduto atualizado com sucesso!");
        } else {
            produtoView.mostrarMensagem("Produto não encontrado.");
        }
    }

    private static void deletarProduto(Scanner scanner, ProdutoController produtoController, ProdutoView produtoView) {
        System.out.print("\nInforme o ID do produto que deseja deletar: ");
        int id = scanner.nextInt();

        if (produtoController.deletarProduto(id)) {
            produtoView.mostrarMensagem("\nProduto deletado com sucesso!");
        } else {
            produtoView.mostrarMensagem("Produto não encontrado.");
        }
    }

    private static void gerenciarVendas(Scanner scanner, VendaController vendaController, VendaView vendaView) {
        int opcao;
        do {
            System.out.println("\n=-=-=-Menu de Vendas=-=-=-");
            System.out.println("1- Realizar Venda");
            System.out.println("2- Listar Vendas");
            System.out.println("0- Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    realizarVenda(scanner, vendaController, vendaView);
                    break;
                case 2:
                    vendaView.listarVendas(vendaController.listarVendas());
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void realizarVenda(Scanner scanner, VendaController vendaController, VendaView vendaView) {
        System.out.print("\nInforme o ID do produto a ser vendido: ");
        int idProduto = scanner.nextInt();
        System.out.print("Informe a quantidade desejada: ");
        int quantidade = scanner.nextInt();
        int vendaId = vendaController.realizarVenda(idProduto, quantidade);

        if (vendaId != -1) {
            vendaView.mostrarMensagem("\nVenda realizada com sucesso!!");
        } else {
            vendaView.mostrarMensagem("Não foi possível realizar a venda. Verifique o estoque.");
        }
    }

    private static void gerenciarEstoque(Scanner scanner, EstoqueController estoqueController, EstoqueView estoqueView) {
        int opcao;
        do {
            System.out.println("\n=-=-=-Menu de Estoque=-=-=-");
            System.out.println("1- Consultar Estoque de Produto");
            System.out.println("2- Atualizar Estoque de Produto");
            System.out.println("0- Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    consultarEstoque(scanner, estoqueController, estoqueView);
                    break;
                case 2:
                    atualizarEstoque(scanner, estoqueController, estoqueView);
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void consultarEstoque(Scanner scanner, EstoqueController estoqueController, EstoqueView estoqueView) {
        System.out.print("\nInforme o ID  do produto para a cuonsulta de estoque: ");
        int idProduto = scanner.nextInt();
        int estoque = estoqueController.consultarEstoque(idProduto);
        estoqueView.mostrarMensagem("Estoque do Produto: " + estoque);
    }

    private static void atualizarEstoque(Scanner scanner, EstoqueController estoqueController, EstoqueView estoqueView) {
        System.out.print("\nInforme o ID do produto para atualizar o estoque: ");
        int idProduto = scanner.nextInt();
        System.out.print("Informe a quantidade a ser adicionada ao estoque: ");
        int quantidade = scanner.nextInt();
        estoqueController.atualizarEstoque(idProduto, quantidade);
        estoqueView.mostrarMensagem("\nEstoque atualizado com sucesso!");
    }
    private static void gerenciarClientes(Scanner scanner, ClienteController clienteController, ClienteView clienteView) {
        int opcao;
        do {
            System.out.println("\n=-=-=-Menu de Cliente=-=-=-");
            System.out.println("1- Criar Cliente");
            System.out.println("2- Listar Clientes");
            System.out.println("3- Atualizar Cliente");
            System.out.println("4- Deletar Cliente");
            System.out.println("0- Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarCliente(scanner, clienteController, clienteView);
                    break;
                case 2:
                    clienteView.listarClientes(clienteController.listarClientes());
                    break;
                case 3:
                    atualizarCliente(scanner, clienteController, clienteView);
                    break;
                case 4:
                    deletarCliente(scanner, clienteController, clienteView);
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarCliente(Scanner scanner, ClienteController clienteController, ClienteView clienteView) {
        scanner.nextLine();
        System.out.print("\nInforme o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o email do cliente: ");
        String email = scanner.nextLine();

        int id = clienteController.criarCliente(nome, email);
        clienteView.mostrarMensagem("\nCliente cadastrado com sucesso!");
    }

    private static void atualizarCliente(Scanner scanner, ClienteController clienteController, ClienteView clienteView) {
        System.out.print("\nInforme o ID do cliente que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Informe o novo nome do cliente: ");
        String novoNome = scanner.nextLine();
        System.out.print("Informe o novo email do cliente: ");
        String novoEmail = scanner.nextLine();

        if (clienteController.atualizarCliente(id, novoNome, novoEmail)) {
            clienteView.mostrarMensagem("\nCliente atualizado com sucesso!");
        } else {
            clienteView.mostrarMensagem("Cliente não encontrado.");
        }
    }

    private static void deletarCliente(Scanner scanner, ClienteController clienteController, ClienteView clienteView) {
        System.out.print("\nInforme o ID do cliente que deseja deletar: ");
        int id = scanner.nextInt();

        if (clienteController.deletarCliente(id)) {
            clienteView.mostrarMensagem("\nCliente deletado com sucesso!");
        } else {
            clienteView.mostrarMensagem("Cliente não encontrado.");
        }
    }
    private static void gerenciarVendedores(Scanner scanner, VendedorController vendedorController, VendedorView vendedorView) {
        int opcao;
        do {
            System.out.println("\n-=-=-Menu de Vendedores=-=-=-");
            System.out.println("1- Criar Vendedor");
            System.out.println("2- Listar Vendedores");
            System.out.println("3- Atualizar Vendedor");
            System.out.println("4- Deletar Vendedor");
            System.out.println("0- Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarVendedor(scanner, vendedorController, vendedorView);
                    break;
                case 2:
                    vendedorView.listarVendedores(vendedorController.listarVendedores());
                    break;
                case 3:
                    atualizarVendedor(scanner, vendedorController, vendedorView);
                    break;
                case 4:
                    deletarVendedor(scanner, vendedorController, vendedorView);
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarVendedor(Scanner scanner, VendedorController vendedorController, VendedorView vendedorView) {
        scanner.nextLine();
        System.out.print("\nInforme o nome do vendedor: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o email do vendedor: ");
        String email = scanner.nextLine();

        int id = vendedorController.criarVendedor(nome, email);
        vendedorView.mostrarMensagem("\nVendedor cadastrado com sucesso! ");
    }

    private static void atualizarVendedor(Scanner scanner, VendedorController vendedorController, VendedorView vendedorView) {
        System.out.print("\nInforme o ID do vendedor que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Informe o novo nome do vendedor: ");
        String novoNome = scanner.nextLine();
        System.out.print("Informe o novo email do vendedor: ");
        String novoEmail = scanner.nextLine();

        if (vendedorController.atualizarVendedor(id, novoNome, novoEmail)) {
            vendedorView.mostrarMensagem("\nVendedor atualizado com sucesso!");
        } else {
            vendedorView.mostrarMensagem("Vendedor não encontrado.");
        }
    }

    private static void deletarVendedor(Scanner scanner, VendedorController vendedorController, VendedorView vendedorView) {
        System.out.print("\nInforme o ID do vendedor que deseja deletar: ");
        int id = scanner.nextInt();

        if (vendedorController.deletarVendedor(id)) {
            vendedorView.mostrarMensagem("\nVendedor deletado com sucesso!");
        } else {
            vendedorView.mostrarMensagem("Vendedor não encontrado.");
        }
    }


}
