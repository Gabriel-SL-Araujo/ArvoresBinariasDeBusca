AVL Tree Genérica em JavaEste projeto implementa uma Árvore AVL genérica em Java, permitindo a inserção, remoção e busca de elementos de forma eficiente. Além disso, oferece um método para percorrer os elementos da árvore em diferentes ordens, incluindo um passeio por nível que exibe os nós organizados por altura.
📌 Tecnologias UtilizadasJava (JDK 8+)
Estruturas de Dados Genéricas
📁 Estrutura do ProjetoO projeto é composto pelos seguintes arquivos principais:
Pacote AVLTreeGenericaAVLTree.java → Implementação da árvore AVL com balanceamento automático.
AVLNode.java → Representação de um nó na árvore AVL.
App.java → Classe principal para testar as funcionalidades da árvore AVL.
Pacote QueueGenericaQueue.java → Implementação de uma fila genérica para auxiliar no passeio por nível.
QueueNode.java → Implementação dos nós da fila genérica.
⚙️ Funcionalidades✅ Inserção Balanceada → A árvore se mantém balanceada automaticamente após cada inserção.
✅ Passeio em Ordem → Exibe os elementos da árvore em ordem crescente.
✅ Passeio por Nível → Exibe os nós por altura, incluindo indicações de valores "Null" para posições vazias.
✅ Remoção (a ser implementada) → Planejada para versões futuras.
🏗️ Estrutura e MétodosAVLTree.javaEsta classe contém a lógica de balanceamento da árvore, incluindo as rotações simples e duplas:
insert(T value) → Insere um novo elemento balanceado.
rotateRight(AVLNode<T> a) → Realiza a rotação à direita.
rotateLeft(AVLNode<T> a) → Realiza a rotação à esquerda.
passeioEmOrdem() → Exibe a árvore em ordem crescente.
passeioPorNivelNaArvore(AVLNode<T> node) → Exibe os elementos organizados por altura.
Queue.javaFila genérica implementada para auxiliar na exibição por nível:
add(T value) → Adiciona um elemento à fila.
remove() → Remove e retorna o elemento no início da fila.
