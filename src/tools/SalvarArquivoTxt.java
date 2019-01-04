/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luiz.barcellos
 */
public class SalvarArquivoTxt {

    public SalvarArquivoTxt(String conteudo, String caminho, String nome) throws IOException {

        System.out.println("\n\nConteúdo recebido para grabar:\n" + conteudo);

        String x_caminho = "";

        if (!(caminho.charAt(caminho.length() - 1) + "").equals("\\")) {
            x_caminho = caminho + "\\";
        } else {
            x_caminho = caminho;
        }

        FileWriter arq = new FileWriter(x_caminho + nome + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(conteudo);

        arq.close();

    }

    public static void main(String[] args) {
        try {
            SalvarArquivoTxt x = new SalvarArquivoTxt("lkjasdlkjsadlkj", "D:/", "arquivoTeste");
        } catch (IOException ex) {
            Logger.getLogger(SalvarArquivoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
