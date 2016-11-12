
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Principal  implements GLEventListener, KeyListener {

    /*----- Variáveis da classe -----*/
    static JFrame frame; // A janela
    static Menu menu; // JPanel do Menu
    static GLCanvas canvas; // tela do jogo
    
    private boolean luz;

    Campo campo;
    GL2 gl;
    GLU glu;
    GLUT glut; 
    float xPosicaoQB, yPosicaoQB, zPosicaoQB, 
            xPosicaoWR1, yPosicaoWR1, zPosicaoWR1,
            xPosicaoWR2, yPosicaoWR2, zPosicaoWR2,
            xPosicaoWR3, yPosicaoWR3, zPosicaoWR3,
            xPosicaoBola, yPosicaoBola, zPosicaoBola;
    int tempo, frames;   
    boolean movimentacaoBola;
    char teclaApertada;
    
    public static void main(String[] args) {
        
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        canvas = new GLCanvas(caps);
        
        menu = new Menu(); // cria o objeto do tipo JPanel

        frame = new JFrame("CG - Futebol Americano");
        frame.setSize(menu.d);
        menu.setSize(menu.d);
        frame = new JFrame("FumbleCG");
        frame.setSize(1900, 1000);
        frame.add(menu, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Deixar frame maximizado 
        

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Principal app = new Principal();
        canvas.addGLEventListener(app);
        canvas.addKeyListener(app);
        menu.addKeyListener(app);
                
        canvas.setVisible(false);
        menu.setVisible(true);
        
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
        
    }        
    
    
    
    @Override
    public void init(GLAutoDrawable glad) {
                
        campo = new Campo();
        
        gl = glad.getGL().getGL2();
        glu = new GLU();
        glut = new GLUT();
        luz = true;
        
        //Habilita a iluminação
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        
        // Habilita o modelo de colorização de Gouraud
        gl.glShadeModel(GL2.GL_SMOOTH);
        
        gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);	

        // Cor da tela de fundo
        gl.glClearColor(0.52f, 0.8f, 0.92f, 0.5f);
                
        xPosicaoQB = 0.0f;
        yPosicaoQB = 2.0f;
        zPosicaoQB = 7.0f;
        
        xPosicaoWR1 = -4.0f;
        yPosicaoWR1 = 2.0f;
        zPosicaoWR1 = 5.5f;
        
        xPosicaoWR2 = 2.0f;
        yPosicaoWR2 = 2.0f;
        zPosicaoWR2 = 5.5f;
        
        xPosicaoWR3 = 4.0f;
        yPosicaoWR3 = 2.0f;
        zPosicaoWR3 = 5.5f;
                
        xPosicaoBola = 0.4f;
        yPosicaoBola = -0.7f;
        zPosicaoBola = 7.0f;
                
        tempo = 0;
        frames = 0;
        
        movimentacaoBola = false;
                
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        
    }

    @Override
    public void display(GLAutoDrawable glad) {
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
                
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
//        gl.glLoadIdentity();
        
        /*glu.gluLookAt(0f, 0f, 0f, 
                      0, 0, 0, 
                      0, 0, 0);*/
        
        // acende ou apaga a luz dependendo do valor de "luz"
        if (luz)
            gl.glEnable(GL2.GL_LIGHT0);
        else
            gl.glDisable(GL2.GL_LIGHT0);
        defineIluminacao();
                      
        campo.renderizaCampo(gl, glu, glut);
        
        if (!movimentacaoBola) {
            frames += 1;        
            
            if (frames == 60) {
                tempo += 1;

                if (zPosicaoWR1 > 0.5f) {
                    zPosicaoWR1 -= 0.2f;
                    zPosicaoWR2 -= 0.2f;
                    zPosicaoWR3 -= 0.2f;                
                }                
                
                frames = 0;                            
            }            
        } else {
            switch (teclaApertada) {
                case 'Q':
                    if (zPosicaoBola > zPosicaoWR1) {                        
                        zPosicaoBola -= 0.2f;
                    }
                    if (xPosicaoBola > xPosicaoWR1) {
                        xPosicaoBola -= 0.2;
                    }
                    break;
                case 'W':
                    if (zPosicaoBola > zPosicaoWR2) {                        
                        zPosicaoBola -= 0.2f;
                    }
                    if (xPosicaoBola < xPosicaoWR2) {
                        xPosicaoBola += 0.2;
                    }
                    break;
                case 'E':
                    if (zPosicaoBola > zPosicaoWR3) {                        
                        zPosicaoBola -= 0.2f;
                    }
                    if (xPosicaoBola < xPosicaoWR3) {
                        xPosicaoBola += 0.2;
                    }
                    break;                
            }
        }
                                       
        desenhaWR1();
        desenhaWR2();
        desenhaWR3();
        
        desenhaQb();

        desenhaBola();
        
    }

    private void defineIluminacao() {
        
        float posicaoLuz[]={-1.0f, 1.0f, -1.0f, 0.0f}; // �ltimo par�metro: 0-direcional, 1-pontual/posicional 
        
        //Define os parâmetros através de vetores RGBA - o último valor deve ser sempre 1.0f 
      //float   vetor[]=  {  r ,   g ,   b ,   a };  
        float luzDifusa[]={1.0f, 1.0f, 1.0f, 1.0f};  

        //Define os parâmetros da luz de n�mero 0
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicaoLuz, 0 );
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, luzDifusa, 0 );
        
        // Brilho do material
        float especularidade[]={1.0f, 1.0f, 1.0f, 1.0f};
        int especMaterial = 20;

        // Define a reflectância do material 
        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, especularidade, 0);
        // Define a concentração do brilho
        gl.glMateriali(GL.GL_FRONT, GL2.GL_SHININESS, especMaterial);

    }

    @Override
    public void reshape(GLAutoDrawable glad, int x, int y, int width, int heigth) {
                     
        gl.glMatrixMode(GL2.GL_PROJECTION);
        glu.gluPerspective(65.0, (float) width / (float) heigth, 1.0, 20.0);
        gl.glTranslatef(0.0f, 0.0f, -10.0f);
                
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {               
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_F1:
                luz = !luz;
                System.out.println("luz = "+luz);
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_UP:
                if (zPosicaoQB > 6.2) {
                    zPosicaoQB -= 0.2f; 
                    zPosicaoBola -= 0.2f;
                }                
                break;
            case KeyEvent.VK_DOWN:
                if (zPosicaoQB < 7.0) {
                    zPosicaoQB += 0.2f;   
                    zPosicaoBola += 0.2f;
                }                
                break;
            case KeyEvent.VK_RIGHT:
                if (xPosicaoQB < 2.2) {
                    xPosicaoQB += 0.2f;
                    xPosicaoBola += 0.2f;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (xPosicaoQB > -2.2) {
                    xPosicaoQB -= 0.2f;
                    xPosicaoBola -= 0.2f;
                }
                break;
            case KeyEvent.VK_Q:
                movimentacaoBola = true;
                teclaApertada = 'Q'; 
                break;
            case KeyEvent.VK_W:
                movimentacaoBola = true;
                teclaApertada = 'W';
                break;
            case KeyEvent.VK_E:
                movimentacaoBola = true;
                teclaApertada = 'E';
                break;
        }

        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    //Métodos que desenham os jogadores ------------------------------------------------------------------
    
    public void desenhaQb() {         
        
        gl.glPushMatrix();
        
            gl.glRotatef(0, 0.0f, 0.0f, 1.0f);
            gl.glTranslatef (this.xPosicaoQB, this.yPosicaoQB, this.zPosicaoQB);
        
            //Tronco ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glPushMatrix();
                    gl.glTranslatef (0.0f, -3.2f, 0.0f);
                    gl.glScalef (1.0f, 1.3f, 0.4f);
                    gl.glColor3f(0.0f, 0.0f, 1.0f);
                    glut.glutSolidCube(0.3f);
                    //gl.glColor3f(255.0f, 255.0f, 255.0f);
                    //glut.glutWireCube (0.3f);
                gl.glPopMatrix();              
                            
            gl.glPopMatrix();
            
            //Cabeça ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glTranslatef (0.0f, -2.85f, 0.0f);
                gl.glColor3f(1, 0.87f, 0.67f);
                glut.glutSolidCube (0.2f);
                gl.glColor3f(255.0f, 255.0f, 255.0f);
            
            gl.glPopMatrix();                                               
                        
            //Perda Esquerda ----------------------------------------------------------------------------
            gl.glPushMatrix();                                            
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);  
                gl.glTranslatef (-0.1f, -3.6f, 0.0f);                                                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    gl.glColor3f(1, 0.87f, 0.67f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();   
                            
                                                            
            gl.glPopMatrix();
            
            //Perda Direita -----------------------------------------------------------------------------
            gl.glPushMatrix();                                        
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);    
                gl.glTranslatef (0.1f, -3.6f, 0.0f);                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();               
                                                                                            
            gl.glPopMatrix();
        
            //Braço Direito -----------------------------------------------------------------------------                          
            gl.glPushMatrix();

                gl.glTranslatef (0.3f, -2.9f, 0.0f);  
                gl.glRotatef(55.0f, 0.0f, 0.0f, 1.0f);   
                gl.glTranslatef (0.0f, -0f, 0.0f);  
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);                    
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();

            gl.glPopMatrix();     
            
            //Braço Esquerdo -----------------------------------------------------------------------------
            gl.glPushMatrix();       
                            
                gl.glTranslatef (-0.25f, -3.2f, 0.0f);
                gl.glRotatef(70.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (-0.0f, -0f, 0.0f);
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();          
    
            gl.glPopMatrix();
        
        gl.glPopMatrix();
           
        gl.glFlush();
        
    }   
    
    public void desenhaWR1() {         
        
        gl.glPushMatrix();
        
            gl.glRotatef(0, 0.0f, 0.0f, 1.0f);
            gl.glTranslatef (this.xPosicaoWR1, this.yPosicaoWR1, this.zPosicaoWR1);
        
            //Tronco ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glPushMatrix();
                    gl.glTranslatef (0.0f, -3.2f, 0.0f);
                    gl.glScalef (1.0f, 1.3f, 0.4f);
                    gl.glColor3f(1.0f, 0.0f, 1.0f);
                    glut.glutSolidCube(0.3f);
                    //gl.glColor3f(255.0f, 255.0f, 255.0f);
                    //glut.glutWireCube (0.3f);
                gl.glPopMatrix();              
                            
            gl.glPopMatrix();
            
            //Cabeça ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glTranslatef (0.0f, -2.85f, 0.0f);
                gl.glColor3f(1, 0.87f, 0.67f);
                glut.glutSolidCube (0.2f);
                gl.glColor3f(255.0f, 255.0f, 255.0f);
            
            gl.glPopMatrix();                                               
                        
            //Perda Esquerda ----------------------------------------------------------------------------
            gl.glPushMatrix();                                            
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);  
                gl.glTranslatef (-0.1f, -3.6f, 0.0f);                                                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    gl.glColor3f(1, 0.87f, 0.67f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();   
                            
                                                            
            gl.glPopMatrix();
            
            //Perda Direita -----------------------------------------------------------------------------
            gl.glPushMatrix();                                        
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);    
                gl.glTranslatef (0.1f, -3.6f, 0.0f);                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();               
                                                                                            
            gl.glPopMatrix();
        
            //Braço Direito -----------------------------------------------------------------------------                          
            gl.glPushMatrix();
            
                gl.glTranslatef (0.25f, -3.2f, 0.0f);
                gl.glRotatef(110.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (0.0f, 0.0f, 0.0f); 
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);                   
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();
                                    
            gl.glPopMatrix();   
                
            
            //Braço Esquerdo -----------------------------------------------------------------------------
            gl.glPushMatrix();       
                            
                gl.glTranslatef (-0.25f, -3.2f, 0.0f);
                gl.glRotatef(70.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (-0.0f, -0f, 0.0f);
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();          
    
            gl.glPopMatrix();
        
        gl.glPopMatrix();
           
        gl.glFlush();
        
    }  
    
    public void desenhaWR2() {         
        
        gl.glPushMatrix();
        
            gl.glRotatef(0, 0.0f, 0.0f, 1.0f);
            gl.glTranslatef (this.xPosicaoWR2, this.yPosicaoWR2, this.zPosicaoWR2);
        
            //Tronco ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glPushMatrix();
                    gl.glTranslatef (0.0f, -3.2f, 0.0f);
                    gl.glScalef (1.0f, 1.3f, 0.4f);
                    gl.glColor3f(1.0f, 0.0f, 0.0f);
                    glut.glutSolidCube(0.3f);
                    //gl.glColor3f(255.0f, 255.0f, 255.0f);
                    //glut.glutWireCube (0.3f);
                gl.glPopMatrix();              
                            
            gl.glPopMatrix();
            
            //Cabeça ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glTranslatef (0.0f, -2.85f, 0.0f);
                gl.glColor3f(1, 0.87f, 0.67f);
                glut.glutSolidCube (0.2f);
                gl.glColor3f(255.0f, 255.0f, 255.0f);
            
            gl.glPopMatrix();                                               
                        
            //Perda Esquerda ----------------------------------------------------------------------------
            gl.glPushMatrix();                                            
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);  
                gl.glTranslatef (-0.1f, -3.6f, 0.0f);                                                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    gl.glColor3f(1, 0.87f, 0.67f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();   
                            
                                                            
            gl.glPopMatrix();
            
            //Perda Direita -----------------------------------------------------------------------------
            gl.glPushMatrix();                                        
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);    
                gl.glTranslatef (0.1f, -3.6f, 0.0f);                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();               
                                                                                            
            gl.glPopMatrix();
        
            //Braço Direito -----------------------------------------------------------------------------                          
            gl.glPushMatrix();
            
                gl.glTranslatef (0.25f, -3.2f, 0.0f);
                gl.glRotatef(110.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (0.0f, 0.0f, 0.0f); 
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);                   
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();
                                    
            gl.glPopMatrix();   
                
            
            //Braço Esquerdo -----------------------------------------------------------------------------
            gl.glPushMatrix();       
                            
                gl.glTranslatef (-0.25f, -3.2f, 0.0f);
                gl.glRotatef(70.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (-0.0f, -0f, 0.0f);
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();          
    
            gl.glPopMatrix();
        
        gl.glPopMatrix();
           
        gl.glFlush();
        
    }
    
    public void desenhaWR3() {         
        
        gl.glPushMatrix();
        
            gl.glRotatef(0, 0.0f, 0.0f, 1.0f);
            gl.glTranslatef (this.xPosicaoWR3, this.yPosicaoWR3, this.zPosicaoWR3);
        
            //Tronco ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glPushMatrix();
                    gl.glTranslatef (0.0f, -3.2f, 0.0f);
                    gl.glScalef (1.0f, 1.3f, 0.4f);
                    gl.glColor3f(0.0f, 1.0f, 0.0f);
                    glut.glutSolidCube(0.3f);
                    //gl.glColor3f(255.0f, 255.0f, 255.0f);
                    //glut.glutWireCube (0.3f);
                gl.glPopMatrix();              
                            
            gl.glPopMatrix();
            
            //Cabeça ------------------------------------------------------------------------------------            
            gl.glPushMatrix();
                
                gl.glTranslatef (0.0f, -2.85f, 0.0f);
                gl.glColor3f(1, 0.87f, 0.67f);
                glut.glutSolidCube (0.2f);
                gl.glColor3f(255.0f, 255.0f, 255.0f);
            
            gl.glPopMatrix();                                               
                        
            //Perda Esquerda ----------------------------------------------------------------------------
            gl.glPushMatrix();                                            
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);  
                gl.glTranslatef (-0.1f, -3.6f, 0.0f);                                                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    gl.glColor3f(1, 0.87f, 0.67f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();   
                            
                                                            
            gl.glPopMatrix();
            
            //Perda Direita -----------------------------------------------------------------------------
            gl.glPushMatrix();                                        
                
                //gl.glRotatef((float) perna, 1.0f, 0.0f, 0.0f);    
                gl.glTranslatef (0.1f, -3.6f, 0.0f);                             
                gl.glPushMatrix();                    
                    gl.glScalef (0.5f, 2.0f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();               
                                                                                            
            gl.glPopMatrix();
        
            //Braço Direito -----------------------------------------------------------------------------                          
            gl.glPushMatrix();
            
                gl.glTranslatef (0.25f, -3.2f, 0.0f);
                gl.glRotatef(110.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (0.0f, 0.0f, 0.0f); 
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);                   
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();
                                    
            gl.glPopMatrix();   
                
            
            //Braço Esquerdo -----------------------------------------------------------------------------
            gl.glPushMatrix();       
                            
                gl.glTranslatef (-0.25f, -3.2f, 0.0f);
                gl.glRotatef(70.0f, 0.0f, 0.0f, 1.0f);    
                gl.glTranslatef (-0.0f, -0f, 0.0f);
                gl.glPushMatrix();
                    gl.glScalef (2.0f, 0.5f, 0.4f);
                    glut.glutSolidCube (0.2f);
                gl.glPopMatrix();          
    
            gl.glPopMatrix();
        
        gl.glPopMatrix();
           
        gl.glFlush();
        
    }
    
    //Método que desenha a bola --------------------------------------------------------------------------

    public void desenhaBola() {
        
        gl.glPushMatrix();            
            
            gl.glTranslatef (xPosicaoBola, yPosicaoBola, zPosicaoBola);
            gl.glColor3f(0.6f, 0.29f, 0.0f);
            gl.glPushMatrix();
                gl.glScalef (1.0f, 0.5f, 0.0f);
                glut.glutSolidSphere(0.15f, 30, 30);            
            gl.glPopMatrix(); 
            
        gl.glPopMatrix();
        
    }
    
}
