package br.com.SistemaPetShop.dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Julia Fideles
 */
public abstract class ModuloConexao {
    public static Connection conector() {
		// metodo responsavel por estalebecer a conexão com o banco;
		//conexao = variavel de armazenamento
		java.sql.Connection conexao = null;
		// a linha abaixo "chama" o driver;
		String driver = "com.mysql.jdbc.Driver";
		// Armazenando informações referentes ao banco
		String url = "jdbc:mysql://localhost/dbpetshop";
		String user = "root";
		String password = "";
		// Estabelecendo a conexao com o banco 
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
			} catch (Exception e) {
				//auxilio de erro de conexao
				//System.out.println(e);
				return null;
			}
                
	}
        public abstract void armazenar(Object a);
}
