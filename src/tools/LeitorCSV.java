/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferna
 */
public class LeitorCSV {

    DefaultTableModel lista;

    public LeitorCSV() {

    }

    public LeitorCSV(String arquivo, String tipo) {

        //abre um arquivo e cria um file
        File arquivoCSV = new File(arquivo);

        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = new String();

            //ignora a primeira linha do arquivo
            leitor.nextLine();

            if ("IMPORTAJUROS".equals(tipo)) {
                preparaJuros(leitor, linhasDoArquivo);
            } else {
                JOptionPane.showMessageDialog(null, "Não foram definidas regras de importação para este arquivo.", "Importar dados do arquivo", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao importar arquivo:\n" + e.getMessage());
        }

    }

    public DefaultTableModel tblJurosPadrao(boolean x) {

        if (x) {
            lista = new DefaultTableModel();

            lista.setRowCount(0);
            lista.setColumnCount(0);

            lista.addColumn("Contrato");
            lista.addColumn("Fornecedor");
            lista.addColumn("Competencia");
            lista.addColumn("Vencimento");
            lista.addColumn("Juros");

            System.out.println("Tabela de juros padrão foi criada.");
        }

        return lista;
    }

    private void preparaJuros(Scanner leitor, String linhasDoArquivo) {

        lista = tblJurosPadrao(true);

//percorre todo o arquivo
        while (leitor.hasNext()) {

            //recebe cada linha do arquivo
            linhasDoArquivo = leitor.nextLine();
            System.out.println("Linhas: " + linhasDoArquivo);

            //separa os campos entre as virgulas de cada linha
            String[] valoresEntreVirgulas = linhasDoArquivo.split("	");

            //imprime a coluna que quiser
            //System.out.println(valoresEntreVirgulas[0]);
            lista.addRow(new String[]{
                valoresEntreVirgulas[0],
                valoresEntreVirgulas[1],
                valoresEntreVirgulas[2],
                valoresEntreVirgulas[3],
                valoresEntreVirgulas[4]
            });
            

        }
            System.out.println("Lista: " + lista.getRowCount());
    }

    public static void main(String[] args) {

        //LeitorCSV csv = new LeitorCSV("D:\\Projetos\\PrograminhasImportarCSV\\clientes.txt");
        LeitorCSV csv = new LeitorCSV("D:\\Financiamentos2.txt", "IMPORTAJUROS");

    }
}
