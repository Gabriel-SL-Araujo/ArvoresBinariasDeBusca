package AVLTreeGenerica;

import QueueGenerica.*;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;
    private boolean status;

    public void setStatus(boolean status) { // Define o status da árvore como balanceada ou não
        this.status = status;
    }

    public boolean isEmpty() { // Verifica se a raiz é nula
        return this.root == null;
    }

    public void insert(T value) { 
        this.root = insertNode(this.root, value);
        this.status = false;
    }

    private AVLNode<T> insertNode(AVLNode<T> r, T value) {
        if (r == null) {
            this.status = true;
            return new AVLNode<>(value);
        }

        if (value.compareTo(r.getInfo()) < 0) {
            r.setLeft(insertNode(r.getLeft(), value));

            if (this.status) {
                switch (r.getFatBal()) {
                    case 1 -> {
                        r.setFatBal(0);
                        this.status = false;
                    }
                    case 0 -> r.setFatBal(-1);
                    case -1 -> {
                        r = this.rotateRight(r);
                        this.status = false;
                    }
                }
            }
        } else if (value.compareTo(r.getInfo()) > 0) {
            r.setRight(insertNode(r.getRight(), value));

            if (this.status) {
                switch (r.getFatBal()) {
                    case -1 -> {
                        r.setFatBal(0);
                        this.status = false;
                    }
                    case 0 -> r.setFatBal(1);
                    case 1 -> {
                        r = rotateLeft(r);
                        this.status = false;
                    }
                }
            }
        } else {
            this.status = false;  // Valor já existe na árvore, sem alteração
        }

        return r;
    }

    private AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> b = a.getLeft();

        if (b.getFatBal() <= 0) { // Rotação simples
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setFatBal(0);
            b.setFatBal(0);
            return b;
        } else { // Rotação dupla
            AVLNode<T> c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);

            if (c.getFatBal() == -1) {
                a.setFatBal(1);
            } else {
                a.setFatBal(0);
            }

            if (c.getFatBal() == 1) {
                b.setFatBal(-1);
            } else {
                b.setFatBal(0);
            }

            c.setFatBal(0);
            return c;
        }
    }

    private AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();

        if (b.getFatBal() >= 0) { // Rotação simples
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setFatBal(0);
            b.setFatBal(0);
            return b;
        } else { // Rotação dupla
            AVLNode<T> c = b.getLeft();
            a.setRight(c.getLeft());
            c.setLeft(a);
            b.setLeft(c.getRight());
            c.setRight(b);

            if (c.getFatBal() == 1) {
                a.setFatBal(-1);
            } else {
                a.setFatBal(0);
            }

            if (c.getFatBal() == -1) {
                b.setFatBal(1);
            } else {
                b.setFatBal(0);
            }

            c.setFatBal(0);
            return c;
        }
    }

    public void passeioEmOrdem() {
        if (this.isEmpty()) {
            System.out.println("Árvore está vazia.");
        } else {
            passeioEmOrdemNaArvore(this.root);
        }
    }

    private void passeioEmOrdemNaArvore(AVLNode<T> node) {
        if (node != null) {
            passeioEmOrdemNaArvore(node.getLeft()); // Entra em uma função recursiva para acessar os nós a esqueda da raiz da subárvore
            System.out.println(node.getInfo());
            passeioEmOrdemNaArvore(node.getRight()); // Entra em uma função recursiva para acessar os nós a direita da raiz da subárvore
        }
    }

    public void passeioPorNivel() {
        if (this.isEmpty()) {
            System.out.println("Árvore está vazia.");
        } else {
            passeioPorNivelNaArvore(this.root);
        }
    }

    private void passeioPorNivelNaArvore(AVLNode<T> node){
        Queue <AVLNode<T>> queue; // Lista para armazenar os nós
        AVLNode<T> aux = node;

        int multiplier = 1; // Variável que ajudará a manipular os valores por linha durante a exibição dos nós com base em uma proporção de 2
        int numberOfTerms = 0; // Conta quantos nós estão sendo impressos numa linha para controlar a quantidade de termos exibidos 
        
        if(!this.isEmpty()){
            queue = new Queue<>();
            queue.add(node);
            while(!queue.isEmpty()){

                aux = queue.remove();
                
                if (aux != null) {
                    System.out.print(aux.getInfo() + " ");
                    numberOfTerms++;

                    queue.add(aux.getLeft());
                    queue.add(aux.getRight());
                } else {
                    System.out.print("Null ");
                    numberOfTerms++; 
                }

                if(numberOfTerms == multiplier){ // Quando o número de termos se torna igual ao número esperado de nós por linha, o "multiplier" é multiplicado por 2
                    System.out.println();
                    multiplier *= 2;
                    numberOfTerms = 0;
                }
  
            }
        }
    }

    public void remove(T info){
        if(this.isEmpty()){
            System.out.println("Árvore vazia."); 
        } else{
            this.root = removeNode(this.root, info); // Efetua as modificações na árvore e retorna à raiz
            System.out.println("Nó " + info + " foi removido da árvore.");
        }
    }

    private AVLNode<T> removeNode(AVLNode<T> node, T info){
        if(node != null){

            int result = info.compareTo(node.getInfo()); // Primeiramente, deve-se encontrar o valor a ser removido ao comparar os termos da árvore com o valor a ser removido

            if(result < 0){
                node.setLeft(removeNode(node.getLeft(), info)); // Se o valor buscado for menor que o do nó atual, a busca irá ser a esquerda
            } else if(result > 0){
                node.setRight(removeNode(node.getRight(), info)); // Se o valor buscado for menor que o do nó atual, a busca irá ser a direita
            } else{ // Valor encontrado
                if(node.getLeft() == null && node.getRight() == null){ // Quando o nó não tem filhos... 
                    return null; // O nó removido será transformado em "null"
                } else if(node.getLeft() == null){ // Quando o nó não tem filhos à esquerda... 
                    return node.getRight(); // O nó será transformado no nó a sua direita
                } else if(node.getRight() == null){ // Quando o nó não tem filhos à esquerda... 
                    return node.getLeft(); // O nó será transformado no nó a sua esquerda
                
                } else{
                    AVLNode<T> aux = searchLowestValue(node.getRight()); // Quando o nó tem ambos os filhos, ele irá procurar o menor a partir da direita dele... 
                    node.setInfo(aux.getInfo()); // Começa o processo de substituição dos nó removido pelo nó supracitado
                    node.setRight(removeNode(node.getRight(), aux.getInfo())); 
                    

                }
            }
            node = balancearAVLTree(node); // Função para balancear a árvore após a remoção e reorganização dos nós
            return node; 

        } else{
            return null; // Ao receber um nó vazio, ele deve retornar um nó vazio também
        }
    }

    private AVLNode<T> searchLowestValue(AVLNode<T> lowestNode){ // Método que busca intensivamente o menor nó a esquerda
        while (lowestNode.getLeft() != null) {
            lowestNode = lowestNode.getLeft();
        }
        return lowestNode;
    }

    private AVLNode<T> balancearAVLTree(AVLNode<T> node){ // Método para balancear a árvore
        if(node != null){ 

            int fatBalOfRemovedNode = node.getFatBal(); // Fator de balanceamento do nó a ser removido

            // Quando a organização ao remover o nó produzir uma rotação simples à esquerda
            if(node.getRight() != null && fatBalOfRemovedNode == 2 && node.getRight().getFatBal() == 1){
                return rotateLeft(node);
            }

            // Quando a organização ao remover o nó produzir uma rotação simples à direita
            if(node.getRight() != null && fatBalOfRemovedNode == -2 && node.getLeft().getFatBal() == -1){
                return rotateRight(node);
            }

            // Quando a organização ao remover o nó produzir uma rotação dupla à direita
            if(fatBalOfRemovedNode == -2 && node.getLeft().getFatBal() == 1){
                node.setLeft(rotateLeft(node.getLeft())); // Provoca uma rotação simples a esquerda
                return rotateRight(node);
            }

            // Quando a organização ao remover o nó produzir uma rotação dupla à direita
            if(fatBalOfRemovedNode == 2 && node.getRight().getFatBal() == -1){
                node.setRight(rotateRight(node.getRight())); // Provoca uma rotação simples a direita
                return rotateLeft(node);
            } 

            return node; // No caso da árvore estar balanceada, nenhum procedimento será executado

        } else{
            return null; 
        }
    }

}
