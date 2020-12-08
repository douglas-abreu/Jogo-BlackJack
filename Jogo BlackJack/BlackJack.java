package BlackJackIFRN;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class BlackJack extends javax.swing.JFrame {
    
    private Jogador jogador;
    private Dealer dealer;
    private Baralho baralho;
    
    public Jogador getJogador(){
        return jogador;
    }
    
    public Dealer getDealer(){
        return dealer;
    }
    
    public void listarCartas(){
        this.baralho.listarCartas();
    }
    
    public void embaralhar(){
        baralho.embaralhar();
    }
    
    public Carta pegarCarta(){
        Carta c = new Carta();
        c = this.baralho.pegarCarta();
        return c;
    }
    
    public void baralhoMenorQue10(){
        this.baralho = new Baralho();
        baralho.embaralhar();
        try{
            music("Audio/cardOpenPackage1.wav");
            music("Audio/cardFan1.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon icon = new ImageIcon(getClass().getResource("PNG/deckIcone.png"));
        JOptionPane.showMessageDialog(null, "Baralho renovado!", "", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public int indiceDaMaoJogador(){
        return getJogador().getIndiceMao();
    }
    
    public int indiceDaMaoDealer(){
        return getDealer().getIndiceMao();
    }
    
    public void apresentarCartaSacadaJogador(){
        switch(getJogador().getIndiceMao()){
                case 0:
                    lCartaJogador1.setIcon(getJogador().getCartasSacadas().get(indiceDaMaoJogador()).getImagem());
                    break;
                case 1:
                    lCartaJogador2.setIcon(getJogador().getCartasSacadas().get(indiceDaMaoJogador()).getImagem());
                    break;
                case 2:
                    lCartaJogador3.setIcon(getJogador().getCartasSacadas().get(indiceDaMaoJogador()).getImagem());
                    break;
                case 3:
                    lCartaJogador4.setIcon(getJogador().getCartasSacadas().get(indiceDaMaoJogador()).getImagem());
                    break;
                case 4:
                    lCartaJogador5.setIcon(getJogador().getCartasSacadas().get(indiceDaMaoJogador()).getImagem());
                    break;
            }
    }
    
    public void apresentarCartaSacadaDealer(){
        switch(getDealer().getIndiceMao()){
                case 0:
                    lCartaDealer1.setIcon(getDealer().getCartasSacadas().get(indiceDaMaoDealer()).getImagem());
                    break;
                case 1:
                    if(getDealer().isMostrarCarta() == false){
                       lCartaDealer2.setIcon(new ImageIcon("/home/douglas/Desktop/Cursos/testes/ProjetoBlackJack/src/BlackJackIFRN/PNG/blue_back.png")); 
                    }else{
                        lCartaDealer2.setIcon(getDealer().getCartasSacadas().get(indiceDaMaoDealer()).getImagem());
                    }
                    break;
                case 2:
                    lCartaDealer3.setIcon(getDealer().getCartasSacadas().get(indiceDaMaoDealer()).getImagem());
                    break;
                case 3:
                    lCartaDealer4.setIcon(getDealer().getCartasSacadas().get(indiceDaMaoDealer()).getImagem());
                    break;
                case 5:
                    lCartaDealer5.setIcon(getDealer().getCartasSacadas().get(indiceDaMaoDealer()).getImagem());
                    break;
            }
    }
    
    public void jogadorSacando(){
        if(baralho.tamanhoBaralho() > 10){
            getJogador().adicionarCartaSacada(pegarCarta());
            apresentarCartaSacadaJogador();
            getJogador().setIndiceMao(getJogador().getIndiceMao()+1);
        }else{
            baralhoMenorQue10();
            getJogador().adicionarCartaSacada(pegarCarta());
            apresentarCartaSacadaJogador();
            getJogador().setIndiceMao(getJogador().getIndiceMao()+1);
        }
    }
    
    public void dealerSacando(){
        if(baralho.tamanhoBaralho() > 10){
            getDealer().adicionarCartaSacada(pegarCarta());
            apresentarCartaSacadaDealer();    
            getDealer().setIndiceMao(getDealer().getIndiceMao()+1);
        }else{
            baralhoMenorQue10();
            getDealer().adicionarCartaSacada(pegarCarta());
            apresentarCartaSacadaDealer();    
            getDealer().setIndiceMao(getDealer().getIndiceMao()+1);
        }
    }
    
    public void iniciarAposta(){
        if(sliderAposta.getValue() > getJogador().getCreditos()){
            ImageIcon icon = new ImageIcon(getClass().getResource("PNG/chips.png"));
            JOptionPane.showMessageDialog(null, "Você não possui essa quantia de créditos!", "", JOptionPane.INFORMATION_MESSAGE, icon);
        }else{
            getJogador().setCreditos(getJogador().getCreditos() - sliderAposta.getValue());
            txtCreditos.setText(String.valueOf(this.jogador.getCreditos()));
            jogadorSacando();
            dealerSacando();
            jogadorSacando();
            dealerSacando();
            verificarBlackJackJogador();
        }
    }
    
    public void encerrarPartida(){
        getDealer().getCartasSacadas().clear();
        getDealer().setIndiceMao(0);
        getJogador().getCartasSacadas().clear();
        getJogador().setIndiceMao(0);
        bApostar.setEnabled(true);
        bParar.setEnabled(false);
        bSacar.setEnabled(false);
        lPontuacaoDealer.setText("");
        lPontuacaoJogador.setText("");
        qtdCartasBaralho();
        lCartaDealer1.setIcon(new ImageIcon());
        lCartaDealer2.setIcon(new ImageIcon());
        lCartaDealer3.setIcon(new ImageIcon());
        lCartaDealer4.setIcon(new ImageIcon());
        lCartaDealer5.setIcon(new ImageIcon());
        lCartaJogador1.setIcon(new ImageIcon());
        lCartaJogador2.setIcon(new ImageIcon());
        lCartaJogador3.setIcon(new ImageIcon());
        lCartaJogador4.setIcon(new ImageIcon());
        lCartaJogador5.setIcon(new ImageIcon());
        
    }
    
    public void imprimidrPontuacoesIniciais(){
        lPontuacaoJogador.setText(String.valueOf(getJogador().getPontos()));
        lPontuacaoDealer.setText(String.valueOf(getDealer().getCartasSacadas().get(0).getValor()));
    }
    
    public void imprimirPontuacaoJogador(){
        lPontuacaoJogador.setText(String.valueOf(getJogador().getPontos()));
    }
    
    public void imprimirPontuacaoDealer(){
        lPontuacaoDealer.setText(String.valueOf(getDealer().getPontos()));
    }
    
    public void verificarBlackJackJogador(){
        if(verificarSeAsSacadoJogador() == true && verificarSeKJQSacadoJogador() == true){
           lPontuacaoJogador.setText("21");
           vencedorBlackJack();
        }
    }
    
    public boolean verificarSeAsSacadoJogador(){
        if(getJogador().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("a") || getJogador().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("a")){
            return true;
        }
        return false;
    }
    
    public boolean verificarSeKJQSacadoJogador(){
        if(getJogador().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("j") || 
           getJogador().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("j")){
            return true;
        }else if(getJogador().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("q") || 
                 getJogador().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("q")){
            return true;
        }else if(getJogador().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("k") || 
                 getJogador().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("k")){
            return true;
        }else{
            return false;
        }  
    }
    
    public boolean verificarBlackJackDealer(){
        if(verificarSeAsSacadoDealer() == true && verificarSeKJQSacadoDealer() == true){
           lPontuacaoDealer.setText("21");
           derrotaBlackJack();
           return true;
        }else{
            return false;
        }
    }
    
    public boolean verificarSeAsSacadoDealer(){
        if(getDealer().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("a") || getDealer().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("a")){
            return true;
        }
        return false;
    }
    
    public boolean verificarSeKJQSacadoDealer(){
        if(getDealer().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("j") || 
           getDealer().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("j")){
            return true;
        }else if(getDealer().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("q") || 
                 getDealer().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("q")){
            return true;
        }else if(getDealer().getCartasSacadas().get(0).getNumero().equalsIgnoreCase("k") || 
                 getDealer().getCartasSacadas().get(1).getNumero().equalsIgnoreCase("k")){
            return true;
        }else{
            return false;
        }  
    }
    
    public void verificarVencedor(){
        if(getDealer().getPontos() > 21){
            vencedor();
        }else{
            if(getJogador().getPontos() > getDealer().getPontos()){
               vencedor();
            }else if(getJogador().getPontos() < getDealer().getPontos()){
                derrota();
            }else{
                empate();
            }
        }
    }
    
    public void vencedor(){
        try{
            music("Audio/chipsHandle6.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        getJogador().setCreditos(sliderAposta.getValue()*2+getJogador().getCreditos());
        txtCreditos.setText(String.valueOf(getJogador().getCreditos()));
        mensagemVitoria();
        encerrarPartida();
    }
    
    public void vencedorBlackJack(){
        try{
            music("Audio/chipsHandle6.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        getJogador().setCreditos(sliderAposta.getValue()*2+getJogador().getCreditos());
        txtCreditos.setText(String.valueOf(getJogador().getCreditos()));
        mensagemVitoriaBlackJack();
        encerrarPartida();
    }
    
    public void derrotaBlackJack(){
        try{
            music("Audio/chipsHandle6.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensagemDerrota();
        encerrarPartida();
    }
    
    public void derrota(){
        try{
            music("Audio/chipsHandle6.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensagemDerrota();
        encerrarPartida();
    }
    
    public void empate(){
        try{
            music("Audio/chipsHandle6.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        getJogador().setCreditos(sliderAposta.getValue()+getJogador().getCreditos());
        mensagemEmpate();
        encerrarPartida();
    }
    
    
    public void mensagemVitoria(){
        ImageIcon icon = new ImageIcon(getClass().getResource("PNG/chips.png"));
        JOptionPane.showMessageDialog(null, "Você venceu!", "", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public void mensagemVitoriaBlackJack(){
        ImageIcon icon = new ImageIcon(getClass().getResource("PNG/chips.png"));
        JOptionPane.showMessageDialog(null, "BlackJack!", "", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public void mensagemDerrotaBlackJack(){
        ImageIcon icon = new ImageIcon(getClass().getResource("PNG/chips.png"));
        JOptionPane.showMessageDialog(null, "BlackJack do Dealer!", "", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public void mensagemDerrota(){
        ImageIcon icon = new ImageIcon(getClass().getResource("PNG/chips.png"));
        JOptionPane.showMessageDialog(null, "Você perdeu!", "", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public void mensagemEmpate(){
        ImageIcon icon = new ImageIcon(getClass().getResource("PNG/chips.png"));
        JOptionPane.showMessageDialog(null, "Empate!", "", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public void qtdCartasBaralho(){
        lQtdBaralho.setText(String.valueOf(this.baralho.tamanhoBaralho()));
    }
    
    public void music(String caminho) throws IOException{
        InputStream inputStream = getClass().getResourceAsStream(caminho);
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
    }

    
    
    public BlackJack() {
        this.jogador = new Jogador();
        this.dealer = new Dealer();
        this.baralho = new Baralho();
        this.baralho.embaralhar();
        initComponents();
        lValorAposta.setText(String.valueOf(sliderAposta.getValue()));
        txtCreditos.setText(String.valueOf(this.jogador.getCreditos()));
        lQtdBaralho.setText(String.valueOf(this.baralho.tamanhoBaralho()));
        try {
            music("Audio/theme.wav");
        } catch (IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pCartas = new javax.swing.JPanel();
        lCartaDealer1 = new javax.swing.JLabel();
        lCartaDealer2 = new javax.swing.JLabel();
        lCartaDealer3 = new javax.swing.JLabel();
        lCartaDealer4 = new javax.swing.JLabel();
        lCartaDealer5 = new javax.swing.JLabel();
        lCartaJogador1 = new javax.swing.JLabel();
        lCartaJogador2 = new javax.swing.JLabel();
        lCartaJogador3 = new javax.swing.JLabel();
        lCartaJogador4 = new javax.swing.JLabel();
        lCartaJogador5 = new javax.swing.JLabel();
        pOpcoes = new javax.swing.JPanel();
        bApostar = new javax.swing.JButton();
        bSacar = new javax.swing.JButton();
        bParar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        sliderAposta = new javax.swing.JSlider();
        jLabel12 = new javax.swing.JLabel();
        lValorAposta = new javax.swing.JLabel();
        txtCreditos = new javax.swing.JLabel();
        pInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lPontuacaoDealer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lQtdBaralho = new javax.swing.JLabel();
        lPontuacaoJogador = new javax.swing.JLabel();
        bSair = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(BlackJack.MAXIMIZED_BOTH);
        setUndecorated(true);

        pCartas.setBackground(new java.awt.Color(102, 255, 102));
        pCartas.setBorder(null);
        pCartas.setAlignmentX(50.0F);
        pCartas.setAlignmentY(50.0F);

        lCartaDealer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaDealer2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaDealer3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaDealer4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaDealer5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaJogador1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaJogador2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaJogador3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaJogador4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        lCartaJogador5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        pOpcoes.setBackground(new java.awt.Color(102, 255, 102));

        bApostar.setBackground(new java.awt.Color(255, 255, 255));
        bApostar.setFont(new java.awt.Font("LM Roman Dunhill 10", 0, 24)); // NOI18N
        bApostar.setText("Apostar");
        bApostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApostarActionPerformed(evt);
            }
        });

        bSacar.setBackground(new java.awt.Color(255, 255, 255));
        bSacar.setFont(new java.awt.Font("LM Roman Dunhill 10", 0, 24)); // NOI18N
        bSacar.setText("Sacar");
        bSacar.setEnabled(false);
        bSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSacarActionPerformed(evt);
            }
        });

        bParar.setBackground(new java.awt.Color(255, 255, 255));
        bParar.setFont(new java.awt.Font("LM Roman Dunhill 10", 0, 24)); // NOI18N
        bParar.setText("Parar");
        bParar.setEnabled(false);
        bParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPararActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("L M Roman Dunh10", 2, 20)); // NOI18N
        jLabel11.setText("Aposta: R$");

        sliderAposta.setMajorTickSpacing(100);
        sliderAposta.setMaximum(500);
        sliderAposta.setMinimum(100);
        sliderAposta.setMinorTickSpacing(50);
        sliderAposta.setPaintTicks(true);
        sliderAposta.setSnapToTicks(true);
        sliderAposta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderApostaStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("L M Roman Dunh10", 2, 20)); // NOI18N
        jLabel12.setText("Créditos: R$");

        lValorAposta.setFont(new java.awt.Font("L M Roman Dunh10", 2, 20)); // NOI18N
        lValorAposta.setText(".");

        txtCreditos.setFont(new java.awt.Font("L M Roman Dunh10", 2, 20)); // NOI18N
        txtCreditos.setText(".");

        javax.swing.GroupLayout pOpcoesLayout = new javax.swing.GroupLayout(pOpcoes);
        pOpcoes.setLayout(pOpcoesLayout);
        pOpcoesLayout.setHorizontalGroup(
            pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pOpcoesLayout.createSequentialGroup()
                        .addComponent(sliderAposta, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(bApostar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bParar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(55, Short.MAX_VALUE))
                    .addGroup(pOpcoesLayout.createSequentialGroup()
                        .addGroup(pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pOpcoesLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lValorAposta, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pOpcoesLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pOpcoesLayout.setVerticalGroup(
            pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bApostar)
                        .addComponent(bSacar)
                        .addComponent(bParar))
                    .addGroup(pOpcoesLayout.createSequentialGroup()
                        .addGroup(pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lValorAposta))
                        .addGap(18, 18, 18)
                        .addComponent(sliderAposta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(pOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCreditos))
                .addGap(19, 19, 19))
        );

        pInfo.setBackground(new java.awt.Color(102, 255, 102));

        jLabel1.setFont(new java.awt.Font("LM Roman Dunhill 10", 2, 24)); // NOI18N
        jLabel1.setText("Pontuações");

        lPontuacaoDealer.setFont(new java.awt.Font("LM Roman Dunhill 10", 0, 24)); // NOI18N
        lPontuacaoDealer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPontuacaoDealer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("LM Roman Dunhill 10", 2, 24)); // NOI18N
        jLabel3.setText("Cartas no baralho:");

        lQtdBaralho.setFont(new java.awt.Font("LM Roman Dunhill 10", 0, 18)); // NOI18N
        lQtdBaralho.setText(".");

        lPontuacaoJogador.setFont(new java.awt.Font("LM Roman Dunhill 10", 0, 24)); // NOI18N
        lPontuacaoJogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPontuacaoJogador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bSair.setBackground(new java.awt.Color(255, 255, 255));
        bSair.setFont(new java.awt.Font("LM Roman Dunhill 10", 0, 24)); // NOI18N
        bSair.setText("Sair");
        bSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSairActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("LM Roman Dunhill 10", 2, 24)); // NOI18N
        jLabel4.setText("Dealer:");

        jLabel5.setFont(new java.awt.Font("LM Roman Dunhill 10", 2, 24)); // NOI18N
        jLabel5.setText("Jogador:");

        javax.swing.GroupLayout pInfoLayout = new javax.swing.GroupLayout(pInfo);
        pInfo.setLayout(pInfoLayout);
        pInfoLayout.setHorizontalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pInfoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pInfoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lQtdBaralho, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lPontuacaoDealer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lPontuacaoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        pInfoLayout.setVerticalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfoLayout.createSequentialGroup()
                .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(lPontuacaoDealer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lPontuacaoJogador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lQtdBaralho))
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout pCartasLayout = new javax.swing.GroupLayout(pCartas);
        pCartas.setLayout(pCartasLayout);
        pCartasLayout.setHorizontalGroup(
            pCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCartasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCartasLayout.createSequentialGroup()
                        .addComponent(lCartaDealer1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaDealer2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaDealer3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaDealer4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaDealer5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCartasLayout.createSequentialGroup()
                        .addComponent(lCartaJogador1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaJogador2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaJogador3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaJogador4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lCartaJogador5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        pCartasLayout.setVerticalGroup(
            pCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCartasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCartasLayout.createSequentialGroup()
                        .addGroup(pCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCartaDealer5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaDealer1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaDealer2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaDealer3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaDealer4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCartaJogador5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaJogador1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaJogador2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaJogador3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCartaJogador4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Jogar", pCartas);

        jPanel6.setBackground(new java.awt.Color(102, 255, 102));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("L M Roman Dunh10", 0, 18)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("\n\tEm cada rodada de Blackjack, o jogador sentado na mesa começa colocando uma aposta em suas respectivas posições de aposta. Cada mesa indica claramente os limites mínimos e máximos que os jogadores podem apostar, e o tamanho das apostas  pode ser escolhido clicando no Slider de ficha que simboliza o valor correto de uma aposta desejada. O jogador recebem duas cartas com a face para cima em frente às suas posições, e o dealer irá receber duas cartas, uma com a face para     cima e uma com a face para baixo (estilo Atlantic City).\n\n\tO objetivo de qualquer mão de Blackjack é derrotar o dealer. Para fazer isso, você deve ter uma mão em que a pontuação seja mais elevada do que a mão do dealer, mas não exceda 21 no valor total. Como alternativa, você pode ganhar tendo uma pontuação menor que 22 quando o valor da mão do dealer ultrapassar 21. Quando o valor total da sua mão for 22 ou mais, normalmente conhecido como 'estourar', você vai automaticamente perder qualquer dinheiro apostado.\n\n\tQuando solicitado, você pode pedir cartas para a sua mão com o propósito de aumentar o seu valor total pressionando o botão \"Sacar\". Quando terminar de pedir cartas ao pressionar o botão \"Parar\", o dealer irá concluir a sua mão. \n\n\tA melhor mão no Blackjack é, sem surpresas, o 'Blackjack', que é composta por um as e qualquer carta incluindo qualquer valete, dama ou rei. O Blackjack precisa ser feito com as suas duas primeiras cartas para ser considerado, e é imbatível.\n\n\tNo Blackjack, os dez, valetes, damas e reis têm o valor de dez cada um. Os Áses podem ter dois valores diferentes, tanto 1 como 11. ");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Como Jogar - Regras Gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("LM Roman Dunhill 10", 2, 24))); // NOI18N
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(230, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Instruções", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bApostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApostarActionPerformed
        if(getJogador().getCreditos() == 0){
            ImageIcon icon = new ImageIcon(getClass().getResource("PNG/deckIcone.png"));
            JOptionPane.showMessageDialog(null, "Seus créditos acabaram, Fim de jogo!", "", JOptionPane.INFORMATION_MESSAGE, icon);
            System.exit(0);
        }else{
            try {
                music("Audio/chipsStack3.wav");
                music("Audio/chipsStack4.wav");
            } catch (IOException ex) {
                Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
            }
            iniciarAposta();
            imprimidrPontuacoesIniciais();
            qtdCartasBaralho();
            bApostar.setEnabled(false);
            bParar.setEnabled(true);
            bSacar.setEnabled(true);
        }
    }//GEN-LAST:event_bApostarActionPerformed

    private void bSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSacarActionPerformed
        jogadorSacando();
        try{
                music("Audio/cardPlace1.wav");
                music("Audio/cardPlace4.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(getJogador().getCartasSacadas().size() > 4){
            bSacar.setEnabled(false);
        }
        if(getJogador().getPontos() > 21){
            imprimirPontuacaoJogador();
            derrota();
        }else{
            imprimirPontuacaoJogador();
            qtdCartasBaralho();
        }
    }//GEN-LAST:event_bSacarActionPerformed

    private void bPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPararActionPerformed
        lCartaDealer2.setIcon(getDealer().getCartasSacadas().get(1).getImagem());
        imprimirPontuacaoDealer();
        try{
            music("Audio/cardPlace1.wav");
            music("Audio/cardPlace4.wav");
        }catch(IOException ex) {
            Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(verificarBlackJackDealer() == true){
            verificarBlackJackDealer();
        }else{
            if(getDealer().getPontos() >= 17){
                verificarVencedor();
            }else{
                while(getDealer().getPontos() < 17){
                    dealerSacando();
                    imprimirPontuacaoDealer();
                }
                if(getDealer().getPontos() > 21){
                    vencedor();
                }else{
                    verificarVencedor();
                }
            }
            qtdCartasBaralho();
        }
    }//GEN-LAST:event_bPararActionPerformed

    private void sliderApostaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderApostaStateChanged
        lValorAposta.setText(String.valueOf(sliderAposta.getValue()));
    }//GEN-LAST:event_sliderApostaStateChanged

    private void bSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bSairActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BlackJack().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bApostar;
    private javax.swing.JButton bParar;
    private javax.swing.JButton bSacar;
    private javax.swing.JButton bSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lCartaDealer1;
    private javax.swing.JLabel lCartaDealer2;
    private javax.swing.JLabel lCartaDealer3;
    private javax.swing.JLabel lCartaDealer4;
    private javax.swing.JLabel lCartaDealer5;
    private javax.swing.JLabel lCartaJogador1;
    private javax.swing.JLabel lCartaJogador2;
    private javax.swing.JLabel lCartaJogador3;
    private javax.swing.JLabel lCartaJogador4;
    private javax.swing.JLabel lCartaJogador5;
    private javax.swing.JLabel lPontuacaoDealer;
    private javax.swing.JLabel lPontuacaoJogador;
    private javax.swing.JLabel lQtdBaralho;
    private javax.swing.JLabel lValorAposta;
    private javax.swing.JPanel pCartas;
    private javax.swing.JPanel pInfo;
    private javax.swing.JPanel pOpcoes;
    private javax.swing.JSlider sliderAposta;
    private javax.swing.JLabel txtCreditos;
    // End of variables declaration//GEN-END:variables
}
