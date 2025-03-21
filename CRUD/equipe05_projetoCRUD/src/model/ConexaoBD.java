package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection getConexao() throws SQLException {
        try
        {
        //Usado para carregar os drivers do mySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
         //DriverManager - Passa as requisições de conexão para os drivers JDBC.
        // Recebe uma URL (Universal Resource Location) como argumento
        //para a abertura da conexão é feita pelo método.
        return DriverManager.getConnection("jdbc:mysql://localhost/bdFarmacia", "root", "aluno123");
        }
        catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());  
        }
    }
}