
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

    Jogador j1, j2, j3, j4;
    Bola b1;
    Campo campo;
    GL2 gl;
    GLU glu;
    GLUT glut; 
    
    public static void main(String[] args) {
        
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        canvas = new GLCanvas(caps);
        
        menu = new Menu(); // cria o objeto do tipo JPanel

        frame = new JFrame("CG - Futebol Americano");
        frame.setSize(menu.d);
        menu.setSize(menu.d);
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
        
        j1 = new Jogador();
        j2 = new Jogador();
        j3 = new Jogador();
        j4 = new Jogador();
        b1 = new Bola();
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


        
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        
    }

    @Override
    public void display(GLAutoDrawable glad) {
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        //gl.glColor3f(0.0f, 0.0f, 0.0f);
        
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
        
      //jx.desenhaJogador(gl, glu, glut,   x ,   y ,   z );
        j1.desenhaJogador(gl, glu, glut, 0.0f, 2.0f, 7.0f);
        j2.desenhaJogador(gl, glu, glut, 4.0f, 2.0f, 5.5f);
        j3.desenhaJogador(gl, glu, glut, -4.0f, 2.0f, 5.5f);
        j3.desenhaJogador(gl, glu, glut, 2.0f, 2.0f, 5.5f);
//        b1.desenhaBola(gl, glu, glut);
        
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
        // Define a concentra��o do brilho
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
        switch (e.getKeyCode()){
            case KeyEvent.VK_F1:        luz = !luz; System.out.println("luz = "+luz);
            break;
            case KeyEvent.VK_ESCAPE:    System.exit(0);
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
