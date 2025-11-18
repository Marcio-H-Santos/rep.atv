import java.util.Scanner;

public class Pessoa {

    String nome;
    int idade;
    double peso, altura;

    // --------------------------------------------------
    // Função auxiliar da Q1 – buscar pessoa por nome
    // --------------------------------------------------
    public static int buscarPessoaPorNome(Pessoa[] v, int qtd, String nome) {
        for (int i = 0; i < qtd; i++) {
            if (v[i].nome.equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    // --------------------------------------------------
    // Q1 – cadastrarPessoa (CORRETA)
    // --------------------------------------------------
    public static int cadastrarPessoa(Pessoa[] v, int qtd) {

        if (qtd == v.length) {
            System.out.println("Vetor cheio.");
            return qtd;
        }

        Scanner sc = new Scanner(System.in);
        Pessoa p = new Pessoa();

        // Nome único
        while (true) {
            System.out.print("Nome: ");
            p.nome = sc.nextLine();

            if (buscarPessoaPorNome(v, qtd, p.nome) == -1) break;

            System.out.println("Nome já existe, digite outro.");
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

    // --------------------------------------------------
    // Função auxiliar – calcular IMC
    // --------------------------------------------------
    public static double calcularIMC(Pessoa p) {
        return p.peso / (p.altura * p.altura);
    }

    // --------------------------------------------------
    // Q2 – imprimirPessoas (CORRETA)
    // --------------------------------------------------
    public static void imprimirPessoas(Pessoa[] v, int qtd) {
        for (int i = 0; i < qtd; i++) {
            Pessoa p = v[i];
            double imc = calcularIMC(p);

            System.out.println("Nome: " + p.nome);
            System.out.println("Idade: " + p.idade);
            System.out.println("Peso: " + p.peso);
            System.out.println("Altura: " + p.altura);
            System.out.println("IMC: " + imc);
            System.out.println("------------------------");
        }
    }

    // --------------------------------------------------
    // Q3 – maisVelhaIMCMagreza (CORRETA)
    // --------------------------------------------------
    public static int maisVelhaIMCMagreza(Pessoa[] v, int qtd) {
        int indice = -1;
        int maiorIdade = -1;

        for (int i = 0; i < qtd; i++) {
            double imc = calcularIMC(v[i]);

            if (imc < 18.5) {
                if (v[i].idade > maiorIdade) {
                    maiorIdade = v[i].idade;
                    indice = i;
                }
            }
        }

        return indice;
    }

    // --------------------------------------------------
    // Q4 – insertionSortPorNome (CORRETA)
    // --------------------------------------------------
    public static void insertionSortPorNome(Pessoa[] v, int qtd) {
        for (int i = 1; i < qtd; i++) {
            Pessoa aux = v[i];
            int j = i - 1;

            while (j >= 0 && v[j].nome.compareToIgnoreCase(aux.nome) > 0) {
                v[j + 1] = v[j];
                j--;
            }

            v[j + 1] = aux;
        }
    }

    // --------------------------------------------------
    // Q5 – Função Livre (CORRETA)
    // ENUNCIADO:
    // "Função que conta quantas pessoas têm idade maior ou igual à idadeMinima."
    // --------------------------------------------------
    public static int contarIdadeMinima(Pessoa[] v, int qtd, int idadeMinima) {
        int cont = 0;

        for (int i = 0; i < qtd; i++) {
            if (v[i].idade >= idadeMinima) {
                cont++;
            }
        }

        return cont;
    }

    // --------------------------------------------------
    // MAIN (opcional, mas funcional)
    // --------------------------------------------------
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
            System.out.println("5 - Contar idade mínima");
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
                    System.out.println("Índice: " + maisVelhaIMCMagreza(pessoas, qtd));
                    break;

                case 4:
                    insertionSortPorNome(pessoas, qtd);
                    System.out.println("Ordenado.");
                    break;

                case 5:
                    System.out.print("Idade mínima: ");
                    int x = sc.nextInt();
                    System.out.println("Quantidade: " + contarIdadeMinima(pessoas, qtd, x));
                    break;
            }

        } while (opc != 0);

        System.out.println("Encerrando...");
    }
}
