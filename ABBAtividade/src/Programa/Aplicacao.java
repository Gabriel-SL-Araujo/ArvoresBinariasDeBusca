package Programa;

import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args){

        Cadastro cadastro = new Cadastro();
        Scanner scan = new Scanner(System.in);

        String textBlock = """
        1. Cadastrar novo produto\n
        2. Exibir Informações dos produtos\n
        3. Procurar produto\n
        4. Alterar preço de um produto\n
        5. Alterar quantidade em estoque\n
        6. Exibir informações de um determinado produto\n
        7. Remover produto do cadastro\n""";

        int option = -1;

        while(option != 0){
            System.out.println(textBlock);
            System.out.println("Escolha uma opção:");
            option = scan.nextInt();
            scan.nextLine();
            menu(cadastro, option, scan);
        }
        
    }

    private static void menu(Cadastro cadastro, int option, Scanner scan){
        switch(option){
            case 1: //Cadastrar Produto
                System.out.println("\n--- Cadastro de Produto ---");
                System.out.print("Código: ");
                String codigo = scan.nextLine();

                System.out.print("Descrição: ");
                String descricao = scan.nextLine();

                System.out.print("Fornecedor: ");
                String fornecedor = scan.nextLine();

                System.out.print("Preço: ");
                double preco = scan.nextDouble();
                scan.nextLine();

                System.out.print("Quantidade: ");
                int quantidade = scan.nextInt();
                scan.nextLine();

                cadastro.cadastrarProduto(codigo, descricao, fornecedor, preco, quantidade);
                break;

            case 2: //Imprimir produtos em estoque
                System.out.println("\n--- Lista de Produtos ---");
                cadastro.exibirTodasInformacoes(); 
                System.out.println();
                break;


            case 3: //Procurar produto em estoque
                System.out.println("\n--- Busca por Produto ---");
                System.out.print("Digite o código do produto: ");
                String codigoProcurado = scan.nextLine();
                System.out.println("\n--- Informações do Produto ---");
                cadastro.procurarProduto(codigoProcurado);
                System.out.println();
                break;

            case 4: //Alterar preço de um produto em estoque
                System.out.println("\n--- Alterar Preço do Produto ---");
                System.out.print("Insira o código do produto: ");
                String codigoProcuradoParaAlterarPreco = scan.nextLine();
                cadastro.alterarPreco(codigoProcuradoParaAlterarPreco);
                break;

            case 5: //Alterar quantidade em estoque
                System.out.println("\n--- Alterar Quantidade do Produto ---");
                System.out.print("Insira o código do produto: ");
                String codigoProcuradoParaAlterarQuantidade = scan.nextLine();
                cadastro.alterarQuantidade(codigoProcuradoParaAlterarQuantidade);
                break;

            case 6: //Exibir informações de um determinado produto em estoque
                System.out.println("\n--- Informações do Produto ---");
                System.out.print("Insira o código do produto: ");
                String codigoProcuradoParaImprimirInformacoes = scan.nextLine();

                System.out.println("\n--- Informações do Produto ---");
                cadastro.exibirInformacoes(codigoProcuradoParaImprimirInformacoes); 
                System.out.println();
                break;

            case 7: //Remover produto do cadastro 
                System.out.println("\n--- Remoção do Produto ---");
                System.out.println("Insira o código do produto: ");
                String codigoQueSeraRemovido = scan.nextLine();
                cadastro.removerProduto(codigoQueSeraRemovido);
                System.out.println("Produto removido com sucesso.");
                break;

            default:
                System.out.println("Comando não reconhecido.");
        }
    }
}
