import java.util.Random;
import java.util.Scanner;

/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;

    /**
     * Código de teste 1. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa a soma do resto dos numeros nos indices pares
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) { // f(n) = piso n^2 * 1
            resposta += vetor[i]%2; // operaçao relevante
            operacoes++;
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa os numeros nos indices pares do vetor
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) { // f(n) = piso log(n-1)
            for (int i = 0; i <= k; i++) {                // 0(n) = 2n
                contador++; // operaçao relevante
                operacoes ++;
            }

        }
        return contador;
    }

    /**
     * Código de teste 3. Este método...
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) { // bubble sort
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] < vetor[menor]) // operaçao relevante
                    menor = j;
                    operacoes++;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo). Este método...
     * @param n Ponto inicial do algoritmo
     * @return Um inteiro que significa fibonacci
     */
    static int codigo4(int n) {
        operacoes ++;
        if (n <= 2)
            return 1; // operacao relevante
        else
            return codigo4(n - 1) + codigo4(n - 2); // operaçao relevante
    }

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2); 
        }
        return vetor;
        
    }
    public static void main(String[] args) {
        testeCodigo();
    }

    public static void testeCodigo(){
        for (int i = 0; i < tamanhosTesteGrande.length; i++){
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo1(gerarVetor(tamanhosTesteGrande[i]));
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println("Numero de operaçoes no codigo 1 para vetor no indice " +i+": "+operacoes+"\nTempo para o codigo ser executado: "+tempo);
        }
        for (int i = 0; i < tamanhosTesteGrande.length; i++){
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo2(gerarVetor(tamanhosTesteGrande[i]));
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println("Numero de operaçoes no codigo 2 para vetor no indice " +i+": "+operacoes+"\nTempo para o codigo ser executado: "+tempo);
        }
        for (int i = 0; i < tamanhosTesteMedio.length; i++){
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo3(gerarVetor(tamanhosTesteMedio[i]));
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println("Numero de operaçoes no codigo 3 para vetor no indice " +i+": "+operacoes+"\nTempo para o codigo ser executado: "+tempo);
        }
         for (int i = 0; i < tamanhosTestePequeno.length; i++){
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo4(i);
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println("Numero de operaçoes no codigo 4 para vetor no indice " +i+": "+operacoes+"\nTempo para o codigo ser executado: "+tempo);
        }
    }


}
