import AVLTreeGenerica.AVLTree;

public class App {
    public static void main(String[] args) throws Exception {
        AVLTree<Integer> numbers = new AVLTree<>();
        
        numbers.insert(2);

        numbers.insert(1);
        numbers.insert(4);
        numbers.insert(5);
        numbers.insert(9);
        numbers.insert(3);
        numbers.insert(6);
        numbers.insert(7);

        System.out.println("Passeio em ordem: ");

        numbers.passeioEmOrdem();

        System.out.println("Passeio por nível: \n");

        numbers.passeioPorNivel();


    }
}
