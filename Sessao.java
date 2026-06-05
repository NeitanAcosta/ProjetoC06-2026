import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Sessao {
    private Filme filme;
    private String horario;
    private int idSala;
    private Assento[][] sala;

    public Sessao(Filme filme, String horario, int idSala) {
        this.filme = filme;
        this.horario = horario;
        this.idSala = idSala;

        sala = new Assento[20][20];
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                sala[i][j] = new Assento(i, j);
            }
        }
    }
    
    public void reservarAssento(String idAssento) throws AssentoIndisponivelException {
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                if(Objects.equals(sala[i][j].getId(), idAssento) && !sala[i][j].isOcupado()){
                    sala[i][j].setOcupado(true);
                    return;
                }
            }
        }
        throw new AssentoIndisponivelException("Assento não encontrado ou já ocupado");
    }
}