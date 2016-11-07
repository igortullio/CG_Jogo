
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Jogador implements MouseListener, KeyListener {

    private static int mao = 0, ombro = 0, cotovelo = 0, perna = 0, tronco = 0;
    private float yBraco = 0, xBracoEsquerdo = -1.0f, xBracoDireito = 1.0f;
    float xEscalaOmbro = 2.0f, yEscalaOmbro = 0.5f, zEscalaOmbro = 0.4f;
    float xEscalaTronco = 1.0f, yEscalaTronco = 1.3f, zEscalaTronco = 0.4f;
    float xEscalaPerna = 0.5f, yEscalaPerna = 2.0f, zEscalaPerna = 0.4f;
    
    
    
    public void desenhaJogador(GL2 gl, GLU glu, GLUT glut, float posicaoX, float posicaoY, float posicaoZ) {       
        
        gl.glTranslatef (posicaoX, posicaoY, posicaoZ);
        gl.glPushMatrix();
        
            gl.glRotatef(tronco, 0.0f, 0.0f, 1.0f);
            gl.glTranslatef (0.0f, 2.0f, 0.0f);
        
            //Tronco ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glPushMatrix();
                    gl.glTranslatef (0.0f, -1.3f, 0.0f);
                    gl.glScalef (xEscalaTronco, yEscalaTronco, zEscalaTronco);
                    glut.glutWireCube (2.0f);
                gl.glPopMatrix();              
                            
            gl.glPopMatrix();
            
            //Cabeça ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glTranslatef (0.0f, 0.8f, 0.0f);
                glut.glutWireCube (1.0f);
            
            gl.glPopMatrix();                                               
                        
            //Perda Esquerda ----------------------------------------------------------------------------
            gl.glPushMatrix();                                            
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);  
                gl.glTranslatef (-0.8f, -3.6f, 0.0f);                                                             
                gl.glPushMatrix();                    
                    gl.glScalef (xEscalaPerna, yEscalaPerna, zEscalaPerna);
                    glut.glutWireCube (1.0f);
                gl.glPopMatrix();   
                            
                                                            
            gl.glPopMatrix();
            
            //Perda Direita -----------------------------------------------------------------------------
            gl.glPushMatrix();                                        
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);    
                gl.glTranslatef (0.8f, -3.6f, 0.0f);                             
                gl.glPushMatrix();                    
                    gl.glScalef (xEscalaPerna, yEscalaPerna, zEscalaPerna);
                    glut.glutWireCube (1.0f);
                gl.glPopMatrix();               
                                                                                            
            gl.glPopMatrix();
        
            //Braço Direito -----------------------------------------------------------------------------
            
            gl.glPushMatrix();
            
                gl.glTranslatef (1.0f, -0.1f, 0.0f);  
                gl.glRotatef(55.0f, 0.0f, 0.0f, 1.0f);   
                gl.glTranslatef (1.0f, -0.1f, 0.0f);  
                gl.glPushMatrix();
                    gl.glScalef (xEscalaOmbro, yEscalaOmbro, zEscalaOmbro);                    
                    glut.glutWireCube (1.0f);
                gl.glPopMatrix();
                                    
            gl.glPopMatrix();            
            
            //Braço Esquerdo -----------------------------------------------------------------------------
            gl.glPushMatrix();       
                            
                gl.glTranslatef (-1.6f, -1.0f, 0.0f);
                gl.glRotatef(70.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (-0.0f, -0.1f, 0.0f);
                gl.glPushMatrix();
                    gl.glScalef (xEscalaOmbro, yEscalaOmbro, zEscalaOmbro);
                    glut.glutWireCube (1.0f);
                gl.glPopMatrix();          
    
            gl.glPopMatrix();
        
        gl.glPopMatrix();
           
        gl.glFlush();
        
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}