
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Pedro Paul
 */
public class Menu extends javax.swing.JPanel {

    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize(); // pega o tamanho do monitor

    BufferedImage imgMenu;

    /**
     * Creates new form pnMenu
     */
    public Menu() {
        initComponents();
        
        //carregar a imagem do menu
        try {
            imgMenu = ImageIO.read(new File("src\\menu.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método paintComponent vai "pintar" a imagem de fundo do menu no JPanel
     * @param g 
     */
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        
        g.drawImage(imgMenu.getScaledInstance(d.width, d.height, 0), 0, 0, null);
    }

    /**
     * Este método é chamado do construtor para inicializar o formulário.
     * ATENÇÃO: Não modifique este código. O conteúdo deste método é sempre
     * regerado pelo editor de formulário.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btIniciar = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        btSair = new javax.swing.JButton();
        spAjuda = new javax.swing.JScrollPane();
        taExplicacao = new javax.swing.JTextArea();
        lbAjuda = new javax.swing.JLabel();
        spAjuda1 = new javax.swing.JScrollPane();
        taExplicacao1 = new javax.swing.JTextArea();
        lbAjuda1 = new javax.swing.JLabel();

        setLayout(null);

        btIniciar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btIniciar.setText("Iniciar Jogo!");
        btIniciar.setToolTipText("Clique aqui para iniciar o jogo");
        btIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btIniciar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btIniciar.setMaximumSize(new java.awt.Dimension(51, 23));
        btIniciar.setMinimumSize(new java.awt.Dimension(51, 23));
        btIniciar.setPreferredSize(new java.awt.Dimension(51, 23));
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });
        add(btIniciar);
        btIniciar.setBounds(730, 210, 210, 80);

        lbTitulo.setFont(new java.awt.Font("Consolas", 0, 48)); // NOI18N
        lbTitulo.setText("FumbleCG");
        lbTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(lbTitulo);
        lbTitulo.setBounds(730, 60, 220, 110);

        btSair.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        add(btSair);
        btSair.setBounds(730, 300, 210, 80);

        spAjuda.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spAjuda.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taExplicacao.setEditable(false);
        taExplicacao.setColumns(20);
        taExplicacao.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        taExplicacao.setLineWrap(true);
        taExplicacao.setRows(5);
        taExplicacao.setText("Q - Lança para o WR de cor rosa\nW - Lança para o WR de cor vermelha\nE - Lança para o WR de cor verde\nP - Pause\nSetas - Movimentam o Qb\nF1 - Muda a iluminação");
        taExplicacao.setToolTipText("Como Funciona o jogo");
        taExplicacao.setWrapStyleWord(true);
        taExplicacao.setSelectionColor(new java.awt.Color(255, 255, 255));
        spAjuda.setViewportView(taExplicacao);

        add(spAjuda);
        spAjuda.setBounds(40, 560, 280, 120);

        lbAjuda.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lbAjuda.setText("REGRAS");
        add(lbAjuda);
        lbAjuda.setBounds(40, 680, 280, 60);

        spAjuda1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spAjuda1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taExplicacao1.setEditable(false);
        taExplicacao1.setColumns(20);
        taExplicacao1.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        taExplicacao1.setLineWrap(true);
        taExplicacao1.setRows(5);
        taExplicacao1.setText("Em resumo é simples: O time que tem a posse de bola tenta avançar gradualmente em jogadas de curta duração - os chamados \"downs\". Se conseguir levar a bola até a endzone do adversário, marca touchdown e conquista 6 pontos. \n\nAqui abordamos o passe e a recepção:\nO quarterback pode arremessar a bola para um recebedor avançado. Mas isto é permitido apenas uma vez por jogada. E o passe deve ser realizado atrás da linha de scrimmage (a linha onde começou a jogada). A recepção só é válida se o jogador segurar a bola antes de ela tocar no chão.");
        taExplicacao1.setToolTipText("Como Funciona o jogo");
        taExplicacao1.setWrapStyleWord(true);
        taExplicacao1.setSelectionColor(new java.awt.Color(255, 255, 255));
        spAjuda1.setViewportView(taExplicacao1);

        add(spAjuda1);
        spAjuda1.setBounds(40, 740, 370, 300);

        lbAjuda1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lbAjuda1.setText("COMO FUNCIONA O JOGO");
        add(lbAjuda1);
        lbAjuda1.setBounds(40, 500, 280, 60);
    }// </editor-fold>//GEN-END:initComponents

    private void btIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarActionPerformed
        Principal.frame.add(Principal.canvas); //Inicia o Jogo
        Principal.canvas.setVisible(true);
        Principal.menu.setVisible(false);
    }//GEN-LAST:event_btIniciarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btIniciar;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel lbAjuda;
    private javax.swing.JLabel lbAjuda1;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JScrollPane spAjuda;
    private javax.swing.JScrollPane spAjuda1;
    private javax.swing.JTextArea taExplicacao;
    private javax.swing.JTextArea taExplicacao1;
    // End of variables declaration//GEN-END:variables
}
