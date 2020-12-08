package BlackJackIFRN;

public class Dealer extends Jogador{
    private boolean mostrarCarta;
    public Dealer() {
        this.mostrarCarta = false;
    }

    public boolean isMostrarCarta() {
        return mostrarCarta;
    }

    public void setMostrarCarta(boolean isMostrarCarta) {
        this.mostrarCarta = mostrarCarta;
    }
    
    
}
