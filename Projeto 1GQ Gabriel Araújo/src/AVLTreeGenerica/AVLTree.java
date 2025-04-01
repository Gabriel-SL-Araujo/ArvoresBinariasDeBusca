package AVLTreeGenerica;

import QueueGenerica.*;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;
    private boolean status;

    public AVLNode<T> getRoot() {
        return this.root;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isEmpty() {
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
            passeioEmOrdemNaArvore(node.getLeft());
            System.out.println(node.getInfo());
            passeioEmOrdemNaArvore(node.getRight());
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
        Queue <AVLNode<T>> queue;
        AVLNode<T> aux = node;

        int multiplier = 1;
        int numberOfTerms = 0;
        
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

                if(numberOfTerms == multiplier){
                    System.out.println();
                    multiplier *= 2;
                    numberOfTerms = 0;
                }
                
                
                    
            }
        }
    }
}
