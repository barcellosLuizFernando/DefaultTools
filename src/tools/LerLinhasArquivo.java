/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class LerLinhasArquivo {

    private static final String TRES = "3";
    private static final String CINCO = "5";

    private String conteudo = "";
    private ArrayList lista = new ArrayList();

    /**
     * Método retorna o arquivo em String. Recebe o caminho do arquivo, abre e
     * converte em String.
     *
     * @param caminho
     * @return
     * @throws Exception
     */
    public LerLinhasArquivo(String caminho) throws Exception { // método que lê o arquivo texto
        try {
            BufferedReader in = new BufferedReader(new FileReader(caminho));
            String linha;
            while ((linha = in.readLine()) != null) {
                linha = linha.trim();
                if (linha.length() > 3) {
                    //System.out.println("Linha inteira " + linha);
                    //System.out.println("Conteúdo: " + getCampo(linha, 0, 5));
                    //System.out.println("A ocorrencia " + TRES + " é da posição " + getPosicao(linha, TRES));
                    //System.out.println("A ocorrencia " + CINCO + " é da posição " + getPosicao(linha, CINCO));

                    conteudo += linha + "\n";
                    
                    lista.add(linha);
                    
                }
            }
            in.close();

            System.out.println("Conteúdo do arquivo:\n" + conteudo);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    
    public LerLinhasArquivo(String arquivo, String separador) {

        //abre um arquivo e cria um file
        File file = new File(arquivo);

        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(file);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = new String();

            //ignora a primeira linha do arquivo
            leitor.nextLine();
            
            while (leitor.hasNext()) {

            //recebe cada linha do arquivo
            linhasDoArquivo = leitor.nextLine();
            System.out.println("Linhas: " + linhasDoArquivo);

            //separa os campos entre as virgulas de cada linha
            String[] valoresEntreVirgulas = linhasDoArquivo.split(separador);

            //imprime a coluna que quiser
            //System.out.println(valoresEntreVirgulas[0]);
                System.out.print("Registro 1: " + valoresEntreVirgulas[0]);
                System.out.println(". Registro 2: " + valoresEntreVirgulas[1]);
            

        }
            
            
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao importar arquivo:\n" + e.getMessage());
        }

    }

    public static int getPosicao(String linha, String ocorrencia) {
        return linha.indexOf(ocorrencia) + 1;
    }

    public static String getCampo(String linha, int x, int y) {
        return linha.substring(x, y);
    }

    public ArrayList getLista() {
        return lista;
    }

    public String getConteudo() {
        return conteudo;
    }

    public static void main(String[] args) {

        LerLinhasArquivo ler = new LerLinhasArquivo("C:\\Users\\luiz.barcellos\\Google Drive\\Contabilidade\\PlanoContasAtividadeRural.txt",". ");

    }
}
