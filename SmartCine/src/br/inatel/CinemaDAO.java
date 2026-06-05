package br.inatel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CinemaDAO {
    
    public CinemaDAO() {
        // Construtor vazio como os seus amigos deixaram
    }
    
    public void salvarVenda(Ingresso ingresso) {
        String sql = "INSERT INTO ingressos (id, assento, tipo_ingresso, valor_final, sessao_id) VALUES (?, ?, ?, ?, ?)";
        
        // Abre a conexão automaticamente usando o try-with-resources
        try (Connection conn = ConexaoBanco.conectar()) {
            if (conn == null) {
                System.out.println("Não foi possível salvar a venda: Sem conexão com o banco.");
                return;
            }
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // 1. Descobrir qual o tipo da classe filha (Herança/Polimorfismo)
                String tipoIngresso = "Inteira";
                if (ingresso instanceof IngressoMeia) {
                    tipoIngresso = "Meia";
                } else if (ingresso instanceof IngressoVIP) {
                    tipoIngresso = "VIP";
                }

                // 2. Preencher os parâmetros do INSERT com os dados do objeto
                stmt.setString(1, ingresso.getId());
                stmt.setString(2, ingresso.getAssento());
                stmt.setString(3, tipoIngresso);
                
                // Aqui o polimorfismo acontece: o Java chamará a versão correta do método de cada classe!
                stmt.setDouble(4, ingresso.calcularValorFinal()); 
                
                // Como as sessões ainda não estão totalmente integradas no banco pelos seus amigos,
                // vamos deixar o ID da sessão como NULL por enquanto para o código não quebrar.
                stmt.setNull(5, java.sql.Types.INTEGER); 

                // 3. Executa o comando no MySQL
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
