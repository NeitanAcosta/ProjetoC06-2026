public class Assento {
    private String id;
    private boolean ocupado;        // true = ocupado, false = livre
    private boolean ehVIP;          // true = Vip, false = não VIP

    public Assento(int linha, int coluna) {
        this.ocupado = false;

        switch (linha){             // Definindo o id para cada posição. Ex.: linha=0 e coluna=0, logo, id=A1
            case 0: id = "A" + (coluna+1); break;
            case 1: id = "B" + (coluna+1); break;
            case 2: id = "C" + (coluna+1); break;
            case 3: id = "D" + (coluna+1); break;
            case 4: id = "E" + (coluna+1); break;
            case 5: id = "F" + (coluna+1); break;
            case 6: id = "G" + (coluna+1); break;
            case 7: id = "H" + (coluna+1); break;
            case 8: id = "I" + (coluna+1); break;
            case 9: id = "J" + (coluna+1); break;
            case 10: id = "K" + (coluna+1); break;
            case 11: id = "L" + (coluna+1); break;
            case 12: id = "M" + (coluna+1); break;
            case 13: id = "N" + (coluna+1); break;
            case 14: id = "O" + (coluna+1); break;
            case 15: id = "P" + (coluna+1); break;
            case 16: id = "Q" + (coluna+1); break;
            case 17: id = "R" + (coluna+1); break;
            case 18: id = "S" + (coluna+1); break;
            case 19: id = "T" + (coluna+1); break;
        }

        assert id != null;             // Garante que id não esteja vazio
        if(id.startsWith("J") || id.startsWith("K")){       // As linhas J e K são assentos VIP
            ehVIP = true;
        }
    }

    // Getter e Setters
    public String getId() {
        return id;
    }

    public boolean EhVIP() {
        return ehVIP;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
