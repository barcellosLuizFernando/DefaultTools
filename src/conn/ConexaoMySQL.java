/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class ConexaoMySQL {

    public static void main(String[] args) {

        try {
            conn.ConexaoMySQL cn = new ConexaoMySQL();

            cn.conecta("localhost", "root", JOptionPane.showInputDialog("Digite sua Senha:"), 3317);
            cn.executeAtualizacao("insert into personal_finances.pessoas (cpf,nome,endereco,cidade) values ('41156242053','aaaaaaaa','qqq',4170);");
            //cn.executeConsulta("SELECT * FROM personal_finances.pessoas where id = 7;");
            while (cn.rs.next()) {
                System.out.println(cn.rs.getString(1));
            }
            cn.finalizarTransacao(true);
            cn.desconecta();
            

        } catch (Exception ex) {
            System.out.println(ex + "");
            tools.DefaultMsg.errorMsg(ex.getMessage());
        }

    }
    private Connection conexao;
    public ResultSet rs;
    private Statement st;

    /**
     * CONEXÃO PADRÃO PARA MYSQL.
     *
     * @param local Informar apenas o IP ou ALIAS do servidor
     * @param usuario
     * @param senha
     * @param porta
     * @throws Exception
     */
    public void conecta(String local, String usuario, String senha, int porta) throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://" + local + ":" + porta + "?useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexao = DriverManager.getConnection(url, usuario, senha);
        conexao.setAutoCommit(false);

    }

    private void desconecta() throws SQLException {
        conexao.close();
    }

    /**
     * DEFINE AUTOCOMMIT = OFF.
     */
    public void iniciarTransacao() throws SQLException {
        conexao.setAutoCommit(false);
    }

    /**
     * Executa INSERT e retorna o número dos registros gerados.
     *
     * @param sql
     * @throws SQLException
     */
    public void executeAtualizacao(String sql) throws SQLException {
        st = conexao.createStatement();
        st.executeUpdate(sql, st.RETURN_GENERATED_KEYS);
        rs = st.getGeneratedKeys();
    }

    public void executeConsulta(String sql) throws SQLException {
        st = conexao.createStatement();
        rs = st.executeQuery(sql);
    }

    public void finalizarTransacao(Boolean commit) {
        try {
            if (commit) {
                conexao.commit();
            } else {
                conexao.rollback();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível finalizar a transação.\n"
                    + ex
                    + "\nSistema será encerrado.", "Erro com o Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public Connection getConexao() {
        return this.conexao;
    }

}
