public class Main {
    public static void main(String[] args) {
        //nessa parte irei criar os filmes
        Filme filme = new Filme(); //aqui falta colocar qual filme irá estar em cartaz

        //nessa parte irei criar as sessões
        Sessao sessao = new Sessao(filme);

        //aqui vou criar os terminais
        TerminalVendasThread terminal1 = new TerminalVendasThread();
        TerminalVendasThread terminal2 = new TerminalVendasThread();

        //aqui irei criar as threads
        Thread thread1 = new Thread(terminal1);
        Thread thread2 = new Thread(terminal2);

        //inicar as vendas
        thread1.start();
        thread2.start();

        System.out.println("Sistema iniciado.");
    }
    
}


