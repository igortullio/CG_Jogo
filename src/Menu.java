
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Paul
 */
public class Menu extends javax.swing.JPanel {

    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize(); // pega o tamanho da tela

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

        setMinimumSize(new java.awt.Dimension(d.height, d.width));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btIniciar.setText("Iniciar Jogo!");
        btIniciar.setToolTipText("Clique aqui para iniciar o jogo");
        btIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btIniciar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });
        add(btIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 110, -1));

        lbTitulo.setText("jLabel1");
        add(lbTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 40, 20));

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        add(btSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 60, -1));
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
    private javax.swing.JLabel lbTitulo;
    // End of variables declaration//GEN-END:variables
}
