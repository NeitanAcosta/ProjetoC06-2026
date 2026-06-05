package br.inatel;

public class IngressoVIP extends Ingresso implements Validavel {

    public IngressoVIP() {
        super();
    }

    public IngressoVIP(String id, double valorBase, String assento) {
        super(id, valorBase, assento);
    }

    @Override
    public boolean validarEntrada() {
        System.out.println("Entrada Liberada");
        return true;
    }

    @Override
    public double calcularValorFinal() {
        return getValorBase() * 1.30;
    }

}
