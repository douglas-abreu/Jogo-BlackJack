package BlackJackIFRN;

import javax.swing.ImageIcon;

public class Carta {
    private String numero;
    private String naipe;
    private int valor;
    private ImageIcon imagem;
    
    Carta(){
        this.numero="0";
        this.naipe="fake";
        this.valor=0;
    }

    public Carta(String numero, String naipe, int valor) {
        this.numero = numero;
        this.naipe = naipe;
        this.valor = valor;    
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    } 
}