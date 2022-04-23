/**
 *
 * @author Beatriz de Oliveira Cavalheri
 * @author Gabriella Alves de Oliveira
 * @author Jordan Rodrigues Moura
 */
package jogoAdvinhacao;
import java.util.*;

public class Advinhacao {
    
    /**
     * - A variável "numero" diz respeito ao número que o usuário irá advinhar;
     * - A variável "teste" diz respeito a verificação se o usuário acertou ou não a advinhação;
     * - A lista "lista" diz respeito a lista de 100 números aleatórios de cada jogador;
     */
    
    public int numero;
    public boolean teste = false;
    List<List<Integer>> lista;

    public Advinhacao(int nroJogadores) {
        /**
         * Este método cria uma lista de números aleatórios para cada jogador.
         */
        lista = new ArrayList<>();
        
        for (int i = 0; i < nroJogadores; i++) {
            lista.add(this.gerarLista());
        }
    }

    private List<Integer> gerarLista() {
        
        /**
         * Este método gera uma lista de números aleatórios.
         * - A variável "gerador" diz respeito ao gerador de números inteiros aleatórios;
         * - A lista "temp_list" diz respeito a lista de 100 posições que armazena os números
         *   aleatórios gerados por "gerador";
         */
        
        Random gerador = new Random();
        List<Integer> temp_list = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            temp_list.add(gerador.nextInt(10));
        }
        return temp_list;
    }

    public void verificador(int jogadorDaVez) {
        
        /**
         * Este método verifica se o usuário acertou o número da lista de números aleatórios
         */

        if (lista.get(jogadorDaVez).contains(numero)) {
            System.out.println("Voce acertou!");
            teste = true;
        } else {
            System.out.println("Voce errou!");
            teste = false;
        }
        
    }

    public void seErro() {
        /**
         * Este verifica se o usuário deseja continuar jogando.
         * - A variável "continuar" diz respeito a escolha do usuário
         *   de querer continuar ou não o jogo;
         */
        char continuar;
        
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Deseja continuar? (S / N)");
            continuar = input.next().charAt(0);
            continuar = Character.toUpperCase(continuar);
            
            switch (continuar) {
                
                case ('S'):
                    return;

                case ('N'):
                    System.out.println("Fim do jogo!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente!");
                    break;
            }
            
        } while (continuar != 'N');
        
    }

    public int[] seAcerto(int jogadorDaVez, int nroJogadores) {
        
        /**
         * Este método questiona ao usuário quantas vezes o número escolhido da
         * lista aparece e estabelece uma quantidade de chances que ele pode dar
         * palpite.
         *
         * - A variável "tentativa" diz respeito ao limite de palpites;
         * - A variável "contagem" conta quantas vezes o número escolhido/acertado
         *   pelo usuário aparece;
         * - O array "pontuacao" computa a pontuacao de cada usuário com base em
         *   quantas vezes seu palpite chegou bem próximo donúmero de vezes que
         *   o número aparece na lista (essa variável permite com que não haja empate);
         */
        
        Scanner sc = new Scanner(System.in);
        int tentativa = 5, contagem = 0;
        int[] pontuacao = new int[nroJogadores];

        for (int i = 0; i < 100; i++) {
            
            if (lista.get(jogadorDaVez).get(i) == numero) {
                contagem++;
            }
        
        }

        System.out.println("Quantas vezes este numero aparece na lista?");
        
        for (int i = 0; i < tentativa; i++) {

            /**
             * - A variável "pergunta" diz respeito ao palpite do usuário;
             * - A variável "diferenca" diz respeito a diferença entre a quantidade
             *   real de vezes que o número aprece na lista e o palpite do usuário
             *   (diferenca: 20 = congelando, 15 = frio, 10 = esquentando, 5 = quente);
             */
            int pergunta = sc.nextInt();
            int diferenca = pergunta - contagem;

            if (Math.abs(diferenca) == 0) {
                System.out.println("ACERTOU!!!\nParabens! O jogador " + (jogadorDaVez + 1) + " ganhou!");
                System.exit(0);
            } else if (Math.abs(diferenca) >= 20) {
                System.out.println("Esta congelando! Tente novamente");
            } else if (Math.abs(diferenca) >= 15) {
                System.out.println("Esta frio! Tente novamente");
            } else if (Math.abs(diferenca) >= 10) {
                System.out.println("Esta esquentando! Tente novamente");
            } else if (Math.abs(diferenca) >= 1) {
                System.out.println("Esta quente!!!!");
                pontuacao[jogadorDaVez]++;
            }
            
        }
        return pontuacao;
    }
    
}
