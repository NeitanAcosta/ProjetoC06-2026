public class Filme {
    //final para que seja atribuido apenas uma vez no construtor
    private final String titulo;
    private final float duracao;
    private final int classificacaoIndicativa;

    public Filme(String titulo, float duracao, int classificacaoIndicativa) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.classificacaoIndicativa = classificacaoIndicativa;
    }
    
    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public float getDuracao() {
        return duracao;
    }

    public int getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }
}