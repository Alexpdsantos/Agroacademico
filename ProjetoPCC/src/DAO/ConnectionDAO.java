package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

	//estabelecendo conexao com o banco
		public Connection obterConexao() throws SQLException{
			//criando a conexao atraves do Drive do MySQL
			String driveMySQL = "jdbc:mysql://localhost:3306/PCC";
			String usuario = "root";
			String senha = "";
			Connection conexao = DriverManager.getConnection(driveMySQL, usuario,senha);
			return conexao;
		}
		
		public void disconnect(Connection conexao) throws SQLException{
			
			if(conexao !=null){
				conexao.close();
			}
		}
	
}
