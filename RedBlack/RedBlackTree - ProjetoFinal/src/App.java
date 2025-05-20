import RBTreeGenerica.RBTree;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String textBlock = """
                0 • Sair do Programa;
                1 • Adicionar inteiro à árvore;
                2 • Passear em Ordem pela árvore;
                3 • Passear por Nível pela árvore;
                4 • Remover inteiro da árvore;
                """; // Exibe os comandos possíveis

        int option;
        Scanner scan = new Scanner(System.in);
        RBTree<Integer> treeOfIntegers = new RBTree<>(); // Criação da árvore
        do {
            System.out.println(textBlock);
            option = scan.nextInt();
            scan.nextLine();
            acessarMenuDeOpcoes(option, scan, treeOfIntegers); // Função que acessa as opções e efetua as interações com a árvore
        } while (option != 0);

        scan.close();
    }

    private static void acessarMenuDeOpcoes(int option, Scanner scan, RBTree<Integer> treeOfIntegers) {
        switch (option) {
            case 0 -> {
                System.out.println("Fechando aplicação...");
            }

            case 1 -> {
                System.out.print("Digite um número: ");
                int newNode = scan.nextInt();
                scan.nextLine();
                treeOfIntegers.insert(newNode);
                System.out.println("Valor " + newNode + " foi inserido.\n\n");
                treeOfIntegers.walkPerLevel();

            }

            case 2 -> {
                System.out.println("Passeio em Ordem na árvore:");
                treeOfIntegers.walkInOrder();
                System.out.println();
            }

            
            case 3 -> {
                System.out.println("Passeio por Nível na árvore:");
                treeOfIntegers.walkPerLevel();
                System.out.println();
            }

            case 4 -> {
                System.out.print("Digite um número: ");
                int newNode = scan.nextInt();
                scan.nextLine();
                treeOfIntegers.remove(newNode);
                treeOfIntegers.walkPerLevel();

            }
            
            default -> {
                System.out.println("Comando não reconhecido. Tente novamente.\n");
            }
        }
    }
}
