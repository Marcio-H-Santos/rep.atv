import java.util.Scanner;

public class Pessoa {

    String nome;
    int idade;
    double peso, altura;

    // -------------------------
    // Função auxiliar da Q1
    // -------------------------
    public static int buscarPessoaPorNome(Pessoa[] v, int qtd, String nome) {
        for (int i = 0; i < qtd; i++) {
            if (v[i].nome.equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    // -------------------------
    // ✔ QUESTÃO 1 — cadastrarPessoa (correta)
    // -------------------------
    public static int cadastrarPessoa(Pessoa[] v, int qtd) {

        if (qtd == v.length) {
            System.out.println("Vetor cheio.");
            return qtd;
        }

        Scanner sc = new Scanner(System.in);
        Pessoa p = new Pessoa();

        // Garantir nome único
        while (true) {
            System.out.print("Nome: ");
            p.nome = sc.nextLine();

            boolean repetido = false;
            for (int i = 0; i < qtd; i++) {
                if (v[i].nome.equalsIgnoreCase(p.nome)) {
                    repetido = true;
                }
            }

            if (!repetido) break;
            System.out.println("Esse nome já existe.");
        }

        System.out.print("Idade: ");
        p.idade = sc.nextInt();

        System.out.print("Peso: ");
        p.peso = sc.nextDouble();

        System.out.print("Altura: ");
        p.altura = sc.nextDouble();

        v[qtd] = p;
        return qtd + 1;
    }

    // -------------------------
    // ✔ Função auxiliar — IMC
    // -------------------------
    public static double calcularIMC(Pessoa p) {
        return p.peso / (p.altura * p.altura);
    }

    // -------------------------
    // ✔ QUESTÃO 2 — imprimirPessoas (correta)
    // -------------------------
    public static void imprimirPessoas(Pessoa[] v, int qtd) {
        for (int i = 0; i < qtd; i++) {
            Pessoa p = v[i];
            double imc = calcularIMC(p);

            System.out.println("Nome: " + p.nome);
            System.out.println("Idade: " + p.idade);
            System.out.println("Peso: " + p.peso);
            System.out.println("Altura: " + p.altura);
            System.out.println("IMC: " + imc);
            System.out.println("--------------------");
        }
    }

    // -------------------------
    // ✔ QUESTÃO 3 — maisVelhaIMCMagreza (correta)
    // -------------------------
    public static int maisVelhaIMCMagreza(Pessoa[] v, int qtd) {
        int pos = -1;
        int maior = -1;

        for (int i = 0; i < qtd; i++) {
            double imc = calcularIMC(v[i]);

            if (imc < 18.5) {
                if (v[i].idade > maior) {
                    maior = v[i].idade;
                    pos = i;
                }
            }
        }

        return pos;
    }

    // -------------------------
    // ✖ QUESTÃO 4 — insertionSortPorNome (ERRADA)
    // -------------------------
    public static void insertionSortPorNome(Pessoa[] v, int qtd) {
        // parece certo, mas está errado
        for (int i = 1; i < qtd; i++) {
            for (int j = i; j > 0; j--) {
                if (v[j].nome.compareTo(v[j - 1].nome) < 0) { // comparação incorreta para insertion tradicional
                    Pessoa temp = v[j];
                    v[j] = v[j - 1];
                    v[j - 1] = temp;
                }
            }
        }
        // implementação não segue o algoritmo correto de inserção
    }

    // -------------------------
    // ✖ QUESTÃO 5 — Função Livre (ERRADA)
    // -------------------------
    // Enunciado escrito na prova:
    // "Função que soma alturas" (vago/incompleto)
    public static double somaAlturas(Pessoa[] v, int qtd, double limite) {
        double total = 0;
        for (int i = 0; i < qtd; i++) {

            if (v[i].altura > 0) { 
                // if inútil (erro proposital)
            }

            total += v[i].altura; // ignora o parâmetro limite
        }
        return total;
    }

    // -------------------------
    // MAIN — classe principal é Pessoa
    // -------------------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Pessoa[] pessoas = new Pessoa[100];
        int qtd = 0;
        int opc;

        do {
            System.out.println("\nMENU:");
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Imprimir pessoas");
            System.out.println("3 - Mais velha com IMC Magreza");
            System.out.println("4 - Ordenar por nome");
            System.out.println("5 - Soma das alturas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    qtd = cadastrarPessoa(pessoas, qtd);
                    break;

                case 2:
                    imprimirPessoas(pessoas, qtd);
                    break;

                case 3:
                    int indice = maisVelhaIMCMagreza(pessoas, qtd);
                    System.out.println("Índice da mais velha com magreza: " + indice);
                    break;

                case 4:
                    insertionSortPorNome(pessoas, qtd);
                    System.out.println("Ordenado.");
                    break;

                case 5:
                    System.out.println("Soma das alturas: " + somaAlturas(pessoas, qtd, 0));
                    break;
            }

        } while (opc != 0);

        System.out.println("Encerrando...");
    }
}
