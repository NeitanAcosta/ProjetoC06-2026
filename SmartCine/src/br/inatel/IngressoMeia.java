public class IngressoMeia extends Ingresso implements Validavel {

    public IngressoMeia() {
        super();
    }

    public IngressoMeia(String id, double valorBase, String assento) {
        super(id, valorBase, assento);
    }

    @Override
    public boolean validarEntrada() {
        System.out.println("Apresente a carteirinha de estudante");
        return true;
    }

    @Override
    public double calcularValorFinal() {
        return getValorBase() * 0.5;
    }

}
