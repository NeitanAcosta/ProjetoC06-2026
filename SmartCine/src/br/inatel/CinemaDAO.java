package br.inatel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CinemaDAO {
    
    public CinemaDAO() {
    }
    
    public void salvarVenda(Ingresso ingresso) {
        String sql = "INSERT INTO ingressos (id, assento, tipo_ingresso, valor_final, sessao_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBanco.conectar()) {
            if (conn == null) {
                System.out.println("Não foi possível salvar a venda: Sem conexão com o banco.");
                return;
            }
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                String tipoIngresso = "Inteira";
                if (ingresso instanceof IngressoMeia) {
                    tipoIngresso = "Meia";
                } else if (ingresso instanceof IngressoVIP) {
                    tipoIngresso = "VIP";
                }

                stmt.setString(1, ingresso.getId());
                stmt.setString(2, ingresso.getAssento());
                stmt.setString(3, tipoIngresso);
                
                stmt.setDouble(4, ingresso.calcularValorFinal()); 
                
                stmt.setNull(5, java.sql.Types.INTEGER); 

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Venda do ingresso " + ingresso.getId() + " (" + tipoIngresso + ") salva no banco de dados com sucesso!");
                }
                
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar o comando SQL de salvamento.");
            e.printStackTrace();
        }
    }
}
