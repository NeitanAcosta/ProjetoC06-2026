package br.inatel;

import java.util.HashMap;
import java.util.Map;
import br.inatel.com.smartcine.exception.AssentoIndisponivelException;

public class Sessao {
    private Filme filme;
    private String horario;
    private int idSala;
    private Map<String, Assento> sala;

    public Sessao(Filme filme, String horario, int idSala) {
        this.filme = filme;
        this.horario = horario;
        this.idSala = idSala;

        sala = new HashMap<>();
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                Assento assento = new Assento(i, j);
                sala.put(assento.getId(), assento);
            }
        }
    }
    
    public void reservarAssento(String idAssento, int idade) throws AssentoIndisponivelException {
        Assento assento = sala.get(idAssento);

        if(assento == null){
            throw new AssentoIndisponivelException("Assento não encontrado");
        }
        if(assento.isOcupado()){
            throw new AssentoIndisponivelException("Assento já está ocupado");
        }
        if(idade >= filme.getClassificacaoIndicativa()){
            assento.setOcupado(true);
        } else {
            throw new AssentoIndisponivelException("Sua idade não condiz com a classificação indicativa para assistir "+filme.getTitulo());
        }
    }
}
