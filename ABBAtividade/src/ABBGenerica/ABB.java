package ABBGenerica;

public class ABB<T extends Comparable<T>>{
    
    private ABBNode<T> root;
    
    public boolean isEmpty(){
        return root == null;
    }

    public void insertProduct(T info){
        if(this.isEmpty()){
            root = new ABBNode<>(info);
        } else{
            insertNewLeaf(this.root, new ABBNode<>(info));
        }
    }

    private void insertNewLeaf(ABBNode<T> node, ABBNode<T> newNode){
        int result = newNode.getInfo().compareTo(node.getInfo());
        if(result == 0){
            System.out.println("Valor já inserido na árvore.");
        } else if(result < 0){
            if(node.getLeft() != null){
                insertNewLeaf(node.getLeft(), newNode);
            } else{
                node.setLeft(newNode);
            }
        } else{
            if(node.getRight() != null){
                insertNewLeaf(node.getRight(), newNode);
            } else{
                node.setRight(newNode);
            }
        }
    }

    public void printProductInfo(){
        if(this.isEmpty()){
            System.out.println("Árvore vazia.");
        } else{
            printInOrder(this.root);
        }
    }

    private void printInOrder(ABBNode<T> node){
        if(node != null){
            printInOrder(node.getLeft());
            System.out.println(node.getInfo());
            printInOrder(node.getRight());
        }
    }

    public ABBNode<T> searchProduct(T info){
        if(this.isEmpty()){
            return null;
        } else{
            return searchProductInTree(this.root, info);
        }
    }

    private ABBNode<T> searchProductInTree(ABBNode<T> node, T info){
        
        if(node != null){
            int result = info.compareTo(node.getInfo());
            if(result == 0){
                return node;
            } else if(result < 0){
                return searchProductInTree(node.getLeft(), info);
            } else{
                return searchProductInTree(node.getRight(), info);
            }
        } else{
            return null;
        }
    }

    public void removeProduct(T info){
        if(this.isEmpty()){
            System.out.println("Árvore Vazia");
        } else{
            this.root = removeProductFromTree(this.root, info);
        }
    }

    private ABBNode<T> removeProductFromTree(ABBNode<T> node, T info){
        if(node != null){
            int result = info.compareTo(node.getInfo());
            if(result > 0){
                node.setRight(removeProductFromTree(node.getRight(), info));
            } else if(result < 0){
                node.setLeft(removeProductFromTree(node.getLeft(), info));
            } else{
                if(node.getLeft() == null && node.getRight() == null){
                    return null;
                } else if(node.getLeft() == null){
                    node = node.getRight();
                } else if(node.getRight() == null){
                    node = node.getLeft();
                } else{
                    ABBNode<T> father, son;
                    father = this.root;
                    son = father.getLeft();
                    if(son.getRight() != null){
                        while(son.getRight() != null){
                            father = son;
                            son = son.getRight();
                        }
                        father.setRight(son.getLeft());
                    } else{
                        father.setLeft(son.getLeft());
                    }
                    node.setInfo(son.getInfo());
                }
            } 
            return node;
        }

        return null;
        
    }

}
