package br.inatel;

public class IngressoInteira extends Ingresso implements Validavel {

    public IngressoInteira() {
        super();
    }

    public IngressoInteira(String id, double valorBase, String assento) {
        super(id, valorBase, assento);
    }

    @Override
    public boolean validarEntrada() {
        System.out.println("Entrada Liberada");
        return true;
    }

    @Override
    public double calcularValorFinal() {
        return getValorBase();
    }

}
