package br.inatel.comp.smartcine.thread;
import br.inatel.com.smartcine.exception.AssentoIndisponivelException;
import br.inatel.Sessao;
import br.inatel.CinemaDAO;
import br.inatel.Assento;
import br.inatel.ConexaoBanco;
import br.inatel.Filme;
import br.inatel.Ingresso;
import br.inatel.IngressoInteira;
import br.inatel.IngressoMeia;
import br.inatel.IngressoVIP;
import br.inatel.Validavel;

public class TerminalVendasThread implements Runnable {

    private final String nomeTerminal;
    private final Sessao sessao;
    private final String idAssento;
    private final int idade;
    private final String tipoIngresso;
    private final CinemaDAO dao;

    public TerminalVendasThread(String nomeTerminal, Sessao sessao, String idAssento, int idade, String tipoIngresso) {
        this.nomeTerminal = nomeTerminal;
        this.sessao = sessao;
        this.idAssento = idAssento;
        this.idade = idade;
        this.tipoIngresso = tipoIngresso;
        this.dao = new CinemaDAO();
    }

    @Override
    public void run() {
        System.out.println("[" + nomeTerminal + "] Tentando reservar o assento " + idAssento + "...");

        try {
            sessao.reservarAssento(idAssento, idade);

            Ingresso ingresso;
            double valorBase = 30.0;
            String idIngresso = nomeTerminal + "-" + idAssento;

            switch (tipoIngresso.toUpperCase()) {
                case "MEIA":
                    ingresso = new IngressoMeia(idIngresso, valorBase, idAssento);
                    break;
                case "VIP":
                    ingresso = new IngressoVIP(idIngresso, valorBase, idAssento);
                    break;
                default:
                    ingresso = new IngressoInteira(idIngresso, valorBase, idAssento);
                    break;
            }

            if (ingresso instanceof Validavel) {
                ((Validavel) ingresso).validarEntrada();
            }

            System.out.printf("[%s] br.inatel.Ingresso %s | br.inatel.Assento: %s | Valor: R$ %.2f%n",
                    nomeTerminal, tipoIngresso, idAssento, ingresso.calcularValorFinal());

            dao.salvarVenda(ingresso);

        } catch (AssentoIndisponivelException e) {
            System.out.println("[" + nomeTerminal + "] FALHA: " + e.getMessage());
        }
    }
}
