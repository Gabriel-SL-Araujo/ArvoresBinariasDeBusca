import AVLTreeGenerica.AVLTree;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        
        String textBlock = """
                0 • Sair do Programa;
                1 • Adicionar inteiro à árvore;
                2 • Passear em Ordem pela árvore;
                3 • Passear por Nível pela árvore;
                4 • Remover inteiro da árvore; 
                """; // Exibe os comandos possíveis

        int option; 
        Scanner scan = new Scanner(System.in);
        AVLTree<Integer> treeOfIntegers = new AVLTree<>(); // Criação da árvore
        do { 
            System.out.println(textBlock);
            option = scan.nextInt();
            scan.nextLine();
            acessarMenuDeOpcoes(option, scan, treeOfIntegers); // Função que acessa as opções e efetua as interações com a árvore
        } while (option != 0);

        scan.close();
    
    }
        
    private static void acessarMenuDeOpcoes(int option, Scanner scan, AVLTree<Integer> treeOfIntegers){
        switch(option){
            case 0 ->{ // Sair do programa
                System.out.println("Fechando aplicação...");
               }
                
            case 1 ->{ // Inserir um nó Integer na árvore
                System.out.println("Digite um número: ");
                int newNode = scan.nextInt();
                scan.nextLine();
                treeOfIntegers.insert(newNode);
                System.out.println("Valor " + newNode + " foi inserido.");
                System.out.println();
            }

            case 2 ->{ // Passear em ordem pela árvore
                System.out.println("Passeio em Ordem na árvore: ");
                treeOfIntegers.passeioEmOrdem();
                System.out.println();
            }

            case 3 ->{ // Passear por nível na árvore
                System.out.println("Passeio por Nível na árvore: ");
                treeOfIntegers.passeioPorNivel();
                System.out.println();
            }

            case 4 ->{ // Remover um nó Integer da árvore
                System.out.println("Digite o inteiro que queira remover da árvore: ");
                int nodeToBeRemoved = scan.nextInt();
                scan.nextLine();
                treeOfIntegers.remove(nodeToBeRemoved);
                System.out.println();
            }

            default ->{ // Comandos inválidos serõ desconsiderados
                System.out.println("Comando não reconhecido. Tente novamente.");
            }
        }
    }
}
