public abstract class Ingresso {

    private String id;
    private double valorBase;
    private String assento;

    public Ingresso() {
        this.id = "";
        this.valorBase = 0.0;
        this.assento = "";
    }

    public Ingresso(String id, double valorBase, String assento) {
        this.id = id;
        this.valorBase = valorBase;
        this.assento = assento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public abstract double calcularValorFinal();

    public double calcularValor() {
        return calcularValorFinal();
    }

}
