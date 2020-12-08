
package BlackJackIFRN;

import java.util.ArrayList;
import java.util.List;

public class Jogador{
    
    private int creditos;
    private List<Carta> cartasSacadas;
    private int indiceMao;

    public Jogador() {
        this.creditos = 1000;
        this.cartasSacadas = new ArrayList<>();
        this.indiceMao = 0;
    }

    public int getCreditos() {
        return creditos;
    }

    public List<Carta> getCartasSacadas() {
        return cartasSacadas;
    }
    
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    protected int getPontos(){
        int valorTotal = 0;
        for(int i = 0; i < cartasSacadas.size(); i++){
            valorTotal = valorTotal + cartasSacadas.get(i).getValor();
        }
        return valorTotal;
    }

    public int getIndiceMao() {
        return indiceMao;
    }

    public void setIndiceMao(int indiceMao) {
        this.indiceMao = indiceMao;
    }
    
    public void adicionarCartaSacada(Carta c){
        cartasSacadas.add(c);
    }
}
