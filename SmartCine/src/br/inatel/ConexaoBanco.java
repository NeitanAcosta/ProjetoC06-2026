package br.inatel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:mysql://localhost:3306/cinema_db?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String SENHA = "Guppyzera1!";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver do MySQL não encontrado! Adicione o JAR do Driver ao projeto.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
            return null;
        }
    }
}
