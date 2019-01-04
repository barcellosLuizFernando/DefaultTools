/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoMySQL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author luiz.barcellos
 */
public class ImprimeRelatorio {

    private ConexaoMySQL cn;
    private SimpleDateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");

    public ImprimeRelatorio(ConexaoMySQL conn) {
        this.cn = conn;
    }

    public void imprimir(String sql, String relatorio, String titulo) {

        String rel = "./src/reports/" + relatorio;//"reports/Capa.jasper";

        try {
            cn.iniciarTransacao();
            cn.executeConsulta(sql);

            JRResultSetDataSource relatResult = new JRResultSetDataSource(cn.rs);

            JasperPrint jasperprint;
            jasperprint = JasperFillManager.fillReport(rel, new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jasperprint, false);

            jv.setTitle(titulo);
            jv.setVisible(true);

            System.out.println("Relatório emitido.");

        } catch (JRException | NullPointerException | SQLException je) {
            JOptionPane.showMessageDialog(null, je);
        } finally {
            cn.finalizarTransacao(true);
        }

    }

    public void imprimir(String sql, String relatorio, String titulo, Date dti, Date dtf, String param1, String param2) {

        String rel = "./src/reports/" + relatorio;//"reports/Capa.jasper";
        //String rel_sub = "./src/reports/conhecimentosRpa_detalhe.jasper";//"reports/Capa.jasper";

        try {
            cn.iniciarTransacao();
            cn.executeConsulta(sql);

            JRResultSetDataSource relatResult = new JRResultSetDataSource(cn.rs);
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("dataInicio", dti);
            parametros.put("dataFim", dtf);
            parametros.put("SUBREPORT_DIR", "./src/reports/");
            parametros.put("REPORT_CONNECTION", cn.getConexao());
            parametros.put("detalhe", param1);
            parametros.put("totalUnidades", param2);
            System.out.println("Parametros definidos: " + parametros);

            JasperPrint jasperprint;
            jasperprint = JasperFillManager.fillReport(rel, parametros, relatResult);
            System.out.println("JasperPrint: " + jasperprint);
            JasperViewer jv = new JasperViewer(jasperprint, false);
            System.out.println("JasperViewer: " + jv);

            jv.setTitle(titulo);
            jv.setVisible(true);

            System.out.println("Relatório emitido.");

        } catch (Exception je) {
            JOptionPane.showMessageDialog(null, je);
        } finally {
            cn.finalizarTransacao(true);
        }
    }

//    public static void main(String[] args) {
//        ImprimeRelatorio print = new ImprimeRelatorio();
//
//        //print.imprimir("SELECT * FROM rel_rpa a order by a.cod_emp ASC,a.cod_est ASC,a.cod_pessoa ASC,a.numero ASC", "conhecimentosRpa.jasper", "Acompanhamento de Recibos", new Date("2017/01/01"), new Date("2017/05/31"), "S", "S");
//        String rel = "SELECT * FROM rel_rpa WHERE codigo = 296"
//                + " union all "
//                + "SELECT * FROM rel_rpa WHERE codigo = 296;";
//        print.imprimir(rel, "rpa.jasper", "Recibo de Pagamento de Autônomo");
//    }
}
