package RBTreeGenerica;
import QueueGenerica.Queue;

public class RBTree<T extends Comparable<T>>{
    private RBNode<T> root;
    private final RBNode<T> nil;

    public RBTree(){
        this.nil = new RBNode<>();
        this.nil.setColor(1); // nil é sempre preto
        this.nil.setLeft(nil);
        this.nil.setRight(nil);
        this.nil.setPrevious(nil);
        this.root = nil;
    }

    // Identifica se a árvore está vazia
    public boolean isEmpty(){
        return this.root == nil;
    }

    // Insere um valor
    public void insert(T value){
        RBNode<T> newNode = new RBNode<>(value);
        newNode.setColor(0); // vermelho
        newNode.setLeft(nil);
        newNode.setRight(nil);
        newNode.setPrevious(nil);

        if(isEmpty()) {
            this.root = newNode;
            this.root.setColor(1); // black
        }
        else insertNewNode(this.root, newNode);
    }

    private void insertNewNode(RBNode<T> node, RBNode<T> newNode){
        if(node != nil){
            int result = newNode.getInfo().compareTo(node.getInfo());

            // Valor será inserido mais a esquerda
            if(result < 0){
                if(node.getLeft() != nil){
                   insertNewNode(node.getLeft(), newNode); 
                } else{
                    node.setLeft(newNode);
                    newNode.setPrevious(node);
                    insert_fixup(newNode); 
                }
            } 

            // Valor será inserido mais a direita
            else{
                if(node.getRight() != nil){
                    insertNewNode(node.getRight(), newNode);
                } else {
                    node.setRight(newNode);
                    newNode.setPrevious(node);
                    insert_fixup(newNode);
                }
            }
            
        }
    }

    // Corrige possíveis violações de propriedades da árvore
    private void insert_fixup(RBNode<T> node) {
        // Enquanto o nó não for a raiz... deve-se verificar
        while (node != root && node.getPrevious().getColor() == 0) {
            RBNode<T> parent = node.getPrevious();
            RBNode<T> grandParent = parent.getPrevious();

            if (parent == grandParent.getLeft()) {
                // Caso o pai seja filho esquerdo do avô
                RBNode<T> uncle = grandParent.getRight();
                if (uncle != nil && uncle.getColor() == 0) {
                    // Caso 1: Tio vermelho
                    parent.setColor(1);
                    uncle.setColor(1);
                    grandParent.setColor(0);
                    node = grandParent;
                } else {
                    if (node == parent.getRight()) {
                        // Caso 2: Nó é filho direito
                        node = parent;
                        rotateLeft(node);
                    }
                    // Caso 3: Nó é filho esquerdo
                    parent = node.getPrevious();
                    grandParent = parent.getPrevious();
                    parent.setColor(1);
                    grandParent.setColor(0);
                    rotateRight(grandParent);
                }
            } else {
                RBNode<T> uncle = grandParent.getLeft();
                if (uncle != nil && uncle.getColor() == 0) {
                    // Caso 1
                    parent.setColor(1);
                    uncle.setColor(1);
                    grandParent.setColor(0);
                    node = grandParent;
                } else {
                    if (node == parent.getLeft()) {
                        // Caso 2
                        node = parent;
                        rotateRight(node);
                    }
                    // Caso 3
                    parent = node.getPrevious();
                    grandParent = parent.getPrevious();
                    parent.setColor(1);
                    grandParent.setColor(0);
                    rotateLeft(grandParent);
                }
            }
        }
        root.setColor(1); // Garantia
    }


    private void rotateLeft(RBNode<T> node) {
        RBNode<T> son = node.getRight();
        node.setRight(son.getLeft());

        if (son.getLeft() != nil) {
            son.getLeft().setPrevious(node);
        }

        son.setPrevious(node.getPrevious());

        if (node.getPrevious() == nil) {
            this.root = son;
        } else if (node == node.getPrevious().getLeft()) {
            node.getPrevious().setLeft(son);
        } else {
            node.getPrevious().setRight(son);
        }

        son.setLeft(node);
        node.setPrevious(son);
    }

    private void rotateRight(RBNode<T> node) {
        RBNode<T> son = node.getLeft();
        node.setLeft(son.getRight());

        if (son.getRight() != nil) {
            son.getRight().setPrevious(node);
        }

        son.setPrevious(node.getPrevious());

        if (node.getPrevious() == nil) {
            this.root = son;
        } else if (node == node.getPrevious().getRight()) {
            node.getPrevious().setRight(son);
        } else {
            node.getPrevious().setLeft(son);
        }

        son.setRight(node);
        node.setPrevious(son);
    }

    public void walkInOrder(){
        if(this.isEmpty()) System.out.println("Red-Black Tree está vazia.");
        else inOrder(this.root);
    }

    private void inOrder(RBNode<T> node){
        if(node != nil){
            inOrder(node.getLeft());
            String color = node.getColor() == 0 ? "(R)" : "(B)";
            System.out.println(node.getInfo() + " " + color);
            inOrder(node.getRight());
        }
    }

    public void walkPerLevel(){
        if(this.isEmpty()) System.out.println("Red-Black Tree está vazia.");
        else perLevel();
    }

    private void perLevel(){
        Queue<RBNode<T>> newQueue = new Queue<>();
        newQueue.enqueue(this.root);

        while(!newQueue.isEmpty()){
            RBNode<T> nodeSearched = newQueue.dequeue();

            if(nodeSearched == nil) System.out.print("nil (B) ");

            else{
                String color = nodeSearched.getColor() == 0 ? "(R)" : "(B)";
                System.out.print(nodeSearched.getInfo() + " " + color + " ");
        
                newQueue.enqueue(nodeSearched.getLeft());
                newQueue.enqueue(nodeSearched.getRight());
            }
            
        }
        System.out.println();
    }

    public void remove(T value){
        if(this.isEmpty()) System.out.println("Red-Black Tree está vazia.");
        else {
            RBNode<T> targetNode = nodeToBeSearched(this.root, value);
            if(targetNode == nil){
                System.out.println("Valor não foi encontrado.");
                return;
            }
            removeNode(targetNode);
            System.out.println("Valor " + value + " foi removido.\n\n");
              
        }
        
    }

    private void removeNode(RBNode<T> targetNode) {
        RBNode<T> originalNode = targetNode; // Nó que será removido originalmente
        int originalNodeColor = originalNode.getColor(); // Cor do nó original
        RBNode<T> substituteNode; // Nó que irá substituir o removido

        if (targetNode.getLeft() == nil) {
            // Caso 1: Nó tem filho esquerdo nil
            substituteNode = targetNode.getRight();
            transplant(targetNode, targetNode.getRight());
        } else if (targetNode.getRight() == nil) {
            // Caso 2: Nó tem filho direito nil
            substituteNode = targetNode.getLeft();
            transplant(targetNode, targetNode.getLeft());
        } else {
            // Caso 3: Nó tem dois filhos
            RBNode<T> successorNode = findSuccessor(targetNode.getRight());
            originalNodeColor = successorNode.getColor();
            substituteNode = successorNode.getRight();

            if (successorNode.getPrevious() == targetNode) {
                // Se sucessor é filho direto do nó a ser removido
                if (substituteNode != nil) substituteNode.setPrevious(successorNode);
            } else {
                transplant(successorNode, successorNode.getRight());
                successorNode.setRight(targetNode.getRight());
                if(successorNode.getRight() != nil){
                    successorNode.getRight().setPrevious(successorNode);
                }
            }

            transplant(targetNode, successorNode);
            successorNode.setLeft(targetNode.getLeft());
            if(successorNode.getLeft() != nil){
                successorNode.getLeft().setPrevious(successorNode);
            }
            successorNode.setColor(targetNode.getColor());
        }

        if (originalNodeColor == 1) {
            delete_fixup(substituteNode);
        }
    }



    private RBNode<T> findSuccessor(RBNode<T> node){
        while(node.getLeft() != nil) node = node.getLeft();
        return node;
    }

    private RBNode<T> nodeToBeSearched(RBNode<T> node, T value){
        if(node != nil){
            int result = value.compareTo(node.getInfo());
            if(result < 0){
                if(node.getLeft() != nil) return nodeToBeSearched(node.getLeft(), value);
            }
            else if(result > 0){
                if(node.getRight() != nil) return nodeToBeSearched(node.getRight(), value);
            }
            else{
                return node;
            }

        }
        return nil;
    }

    private void transplant(RBNode<T> targetNode, RBNode<T> substituteNode) {
        if (targetNode.getPrevious() == nil) {
            this.root = substituteNode;
        } else if (targetNode == targetNode.getPrevious().getLeft()) {
            targetNode.getPrevious().setLeft(substituteNode);
        } else {
            targetNode.getPrevious().setRight(substituteNode);
        }

        if (substituteNode != nil) {
            substituteNode.setPrevious(targetNode.getPrevious());
        }
    }

    private int safeColor(RBNode<T> node) {
        return (node == nil) ? 1 : node.getColor();
    }

    

    private void delete_fixup(RBNode<T> node) {
        while (node != root && safeColor(node) == 1) {
            RBNode<T> parent = node.getPrevious();
            RBNode<T> brother;

            if (node == parent.getLeft()) {
                brother = parent.getRight();

                if (safeColor(brother) == 0) {
                    // Caso 1: irmão vermelho
                    brother.setColor(1);
                    parent.setColor(0);
                    rotateLeft(parent);
                    brother = parent.getRight();
                }

                if (safeColor(brother.getLeft()) == 1 && safeColor(brother.getRight()) == 1) {
                    // Caso 2: irmãos com filhos pretos
                    brother.setColor(0);
                    node = parent;
                } else {
                    if (safeColor(brother.getRight()) == 1) {
                        // Caso 3: filho direito do irmão é preto
                        if (brother.getLeft() != nil) brother.getLeft().setColor(1);
                        brother.setColor(0);
                        rotateRight(brother);
                        brother = parent.getRight();
                    }
                    // Caso 4: filho direito do irmão é vermelho
                    brother.setColor(parent.getColor());
                    parent.setColor(1);
                    if (brother.getRight() != nil) brother.getRight().setColor(1);
                    rotateLeft(parent);
                    node = root;
                }

            } else {
                brother = parent.getLeft();

                if (safeColor(brother) == 0) {
                    brother.setColor(1);
                    parent.setColor(0);
                    rotateRight(parent);
                    brother = parent.getLeft();
                }

                if (safeColor(brother.getLeft()) == 1 && safeColor(brother.getRight()) == 1) {
                    brother.setColor(0);
                    node = parent;
                } else {
                    if (safeColor(brother.getLeft()) == 1) {
                        if (brother.getRight() != nil) brother.getRight().setColor(1);
                        brother.setColor(0);
                        rotateLeft(brother);
                        brother = parent.getLeft();
                    }

                    brother.setColor(parent.getColor());
                    parent.setColor(1);
                    if (brother.getLeft() != nil) brother.getLeft().setColor(1);
                    rotateRight(parent);
                    node = root;
                }
            }
        }

        if (node != nil) {
            node.setColor(1);
        }
    }



}