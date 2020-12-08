package BlackJackIFRN;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

public class Baralho extends QuickSort{
   private List<Carta> cartas;

    Baralho(){
        cartas = new ArrayList<Carta>(); //cria lista para 'cartas'

        String numero = "0";
        String naipe = "fake";
        int valor = 0;
        
        
        for (int num=1; num<14; num++){
            for (int naip=1; naip<5; naip++){
                Carta c = new Carta(); //cria carta
                
                //verifica numero
                if (num==1){
                    numero="A";
                    valor=1;
                }
                else if (num==11){
                    numero="J";
                    valor=10;
                }
                else if (num==12){
                    numero="Q";
                    valor=10;
                }
                else if (num==13){
                    numero="K";
                    valor=10;
                }
                else{
                    numero = String.valueOf(num);
                    valor = num;
                }
                
                //verifica naipe
                switch (naip) {
                    case 1:  naipe = "Paus"; break;
                    case 2:  naipe = "Ouros"; break;
                    case 3:  naipe = "Copas";  break;
                    case 4:  naipe = "Espadas"; break;
                    default: naipe = "fake"; break;
                }
                //seta a carta
                c.setNumero(numero);
                c.setNaipe(naipe);
                c.setValor(valor);
                
                //Adiciona a carta no baralho
                this.cartas.add(c);
            }
        }
        List<String> listaDeImagens = listaImagensOrdenada();
        for(int i = 0; i <= 51; i++){
            cartas.get(i).setImagem(new ImageIcon("/home/douglas/Desktop/Cursos/testes/ProjetoBlackJack/src/BlackJackIFRN/PNG/"+listaDeImagens.get(i)));
        }   
    }
    
    public void listarCartas(){
        for (Carta c : this.cartas) 
            System.out.println(c.getNumero() +" de "+c.getNaipe() +". Valor: "+ c.getValor() +" imagem: " + c.getImagem());
    }
    
    protected void embaralhar(){
        //irei remover 1 aleatória do baralho e readicioná-la ao baralho novamente.
        Random random = new Random();
        Carta c1 = new Carta();
        for (int i=0; i<=500; i++){ 
            c1 = this.cartas.remove( random.nextInt(this.cartas.size()-1)); //gera um numero aleatorio e remove a carta do baralho. c1 recebe esta carga
            this.cartas.add(c1);
        }
    }
    
    protected Carta pegarCarta(){
        Carta c = new Carta();
        c = cartas.remove(0);
        return c;
    }
    
    public int tamanhoBaralho(){
        return this.cartas.size();
    }
    
    public List<String> listaImagensOrdenada(){
        final File folder = new File("/home/douglas/Desktop/Cursos/testes/ProjetoBlackJack/src/BlackJackIFRN/PNG");

        List<String> result = new ArrayList<>();

        search(folder, result);
        qsort(result);
        
        return result;
    }
    
    public void search(final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(f, result);
            }

            if (f.isFile()) {
                    result.add(f.getName());
            }

        }
    }
}   





