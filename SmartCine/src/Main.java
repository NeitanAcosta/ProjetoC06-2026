import br.inatel.Filme;
import br.inatel.Sessao;
import br.inatel.comp.smartcine.thread.TerminalVendasThread;
public class Main {
    public static void main(String[] args) {

        // ── 1. CADASTRO DE FILMES ─────────────────────────────────────────
        Filme filme1 = new Filme("Interestelar", 169.0f, 12);
        Filme filme2 = new Filme("A Quiet Place", 90.0f, 16);

        System.out.println("=== SmartCine - Sistema de Gerenciamento ===\n");
        System.out.println("Filmes em cartaz:");
        System.out.println("  - " + filme1.getTitulo() + " | " + filme1.getDuracao() + " min | Classificação: " + filme1.getClassificacaoIndicativa() + "+");
        System.out.println("  - " + filme2.getTitulo() + " | " + filme2.getDuracao() + " min | Classificação: " + filme2.getClassificacaoIndicativa() + "+\n");

        // ── 2. CRIAÇÃO DAS SESSÕES ────────────────────────────────────────
        Sessao sessao1 = new Sessao(filme1, "14:00", 1);
        Sessao sessao2 = new Sessao(filme2, "17:30", 2);

        System.out.println("Sessões abertas:");
        System.out.println("  - Sala 1: " + filme1.getTitulo() + " às 14:00");
        System.out.println("  - Sala 2: " + filme2.getTitulo() + " às 17:30\n");

        // ── 3. SIMULAÇÃO DE CONCORRÊNCIA (o grande diferencial!) ──────────
        // Dois terminais tentando comprar o MESMO assento ao mesmo tempo
        System.out.println("--- Simulando concorrência: 2 terminais pelo assento C5 ---\n");

        TerminalVendasThread terminal1 = new TerminalVendasThread("Terminal-1", sessao1, "C5", 20, "INTEIRA");
        TerminalVendasThread terminal2 = new TerminalVendasThread("Terminal-2", sessao1, "C5", 25, "MEIA");

        Thread thread1 = new Thread(terminal1);
        Thread thread2 = new Thread(terminal2);

        thread1.start();
        thread2.start();

        // Aguarda as threads de concorrência terminarem antes de continuar
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrompida: " + e.getMessage());
        }

        // ── 4. VENDA NORMAL DE OUTROS ASSENTOS ───────────────────────────
        System.out.println("\n--- Vendas normais em outros assentos ---\n");

        // br.inatel.Assento VIP (linhas J ou K)
        TerminalVendasThread terminalVIP = new TerminalVendasThread("Terminal-3", sessao1, "J1", 18, "VIP");
        Thread threadVIP = new Thread(terminalVIP);
        threadVIP.start();

        // br.inatel.Assento meia em outra sessão
        TerminalVendasThread terminalSessao2 = new TerminalVendasThread("Terminal-4", sessao2, "A1", 17, "MEIA");
        Thread threadSessao2 = new Thread(terminalSessao2);
        threadSessao2.start();

        // Tentativa com idade abaixo da classificação (deve lançar exceção)
        TerminalVendasThread terminalBloqueado = new TerminalVendasThread("Terminal-5", sessao2, "B3", 15, "INTEIRA");
        Thread threadBloqueado = new Thread(terminalBloqueado);
        threadBloqueado.start();

        try {
            threadVIP.join();
            threadSessao2.join();
            threadBloqueado.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrompida: " + e.getMessage());
        }

        System.out.println("\n=== Sistema SmartCine encerrado. ===");
    }
}
