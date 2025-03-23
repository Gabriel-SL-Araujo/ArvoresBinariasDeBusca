package Programa;

import ABBGenerica.ABB;
import ABBGenerica.ABBNode;
import java.util.Scanner;

class Cadastro {
    private final ABB<Produto> produtos;

    public Cadastro(){
        this.produtos = new ABB<>();
    }

    public boolean isEmpty(){
        return produtos.isEmpty();
    }

    public Produto searchProduct(String codigo){
        Produto procurado = new Produto(codigo, "", "", 0.0, 0);

        ABBNode<Produto> resultado = produtos.searchProduct(procurado);

        return (resultado != null) ? resultado.getInfo() : null;
    }

    public void cadastrarProduto(String codigo, String descricao,
                                String fornecedor, double preco, int quantidade){
        
        Produto produto = new Produto(codigo, descricao, fornecedor, preco, quantidade);    
        Produto produtoExistente = searchProduct(codigo);

        if(produtoExistente != null){
            System.out.println("Produto já foi inserido.");
        } else{
            produtos.insertProduct(produto);
            System.out.println("Produto foi inserido");
        }
    }

    public void exibirTodasInformacoes(){
        if(produtos.isEmpty()){
            System.out.println("Não há produtos cadastrados.");
        } else{
            System.out.println("Lista de Produtos:");
            produtos.printProductInfo();
        }
    }

    public void procurarProduto(String codigo){
        Produto produtoProcurado = searchProduct(codigo);

        if(produtoProcurado == null){
            System.out.println("Produto não foi cadastrado.");
        } else{
            System.out.println(produtoProcurado.toString());
        }
    }

    public void alterarPreco(String codigo){
        Produto produtoProcurado = searchProduct(codigo);
        
        if(produtoProcurado == null){
            System.out.println("Produto não foi cadastrado.");
        } else{
            alterarPrecoProduto(produtoProcurado);
        }
    }

    public void alterarQuantidade(String codigo){
        Produto produtoProcurado = searchProduct(codigo);
        
        if(produtoProcurado == null){
            System.out.println("Produto não foi cadastrado.");
        } else{
            alterarQuantidadeProduto(produtoProcurado);
        }
    }

    public void exibirInformacoes(String codigo){
        Produto produtoProcurado = searchProduct(codigo);

        if(produtoProcurado == null){
            System.out.println("Produto não foi cadastrado.");
        } else{
            System.out.println(produtoProcurado.toString());
        }
    }

    public void removerProduto(String codigo){
        Produto produtoProcurado = searchProduct(codigo);

        if(produtoProcurado == null){
            System.out.println("Produto não foi cadastrado.");
        } else{
            produtos.removeProduct(produtoProcurado);
        }

    }

    private void alterarPrecoProduto(Produto produto){
        Scanner scan = new Scanner(System.in);
        System.out.print("Insira o novo preço: ");
        double newValue = scan.nextDouble();
        scan.nextLine();

        produto.setPreco(newValue);

        System.out.println("\n--- Informações do Produto ---");
        System.out.println(produto.toString());

    }

    private void alterarQuantidadeProduto(Produto produto){
        Scanner scan = new Scanner(System.in);
        System.out.print("Insira a nova quantidade: ");
        int newQuantity = scan.nextInt();
        scan.nextLine();

        produto.setQuantidade(newQuantity);

        System.out.println("\n--- Informações do Produto ---");
        System.out.println(produto.toString());

    }
    
    
}
