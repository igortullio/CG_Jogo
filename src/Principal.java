
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
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Principal  implements GLEventListener, KeyListener {

    Jogador j1, j2, j3, j4;
    Bola b1;
    Campo campo;
    GL2 gl;
    GLU glu;
    GLUT glut; 
    
    public static void main(String[] args) {
        
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("CG - Futebol Americano");
        frame.setSize(1900, 1000);
        frame.add(canvas);
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);  //Deitar frame maximizado      

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Principal app = new Principal();
        canvas.addGLEventListener(app);
        canvas.addKeyListener(app);
        canvas.setVisible(true);
        
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
        
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        
    }

    @Override
    public void display(GLAutoDrawable glad) {
        
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //gl.glColor3f(0.0f, 0.0f, 0.0f);
        
        //gl.glLoadIdentity();
        
        /*glu.gluLookAt(0f, 0f, 0f, 
                      0, 0, 0, 
                      0, 0, 0);*/
                      
        campo.renderizaCampo(gl, glu, glut);
        
        j1.desenhaJogador(gl, glu, glut, 0.0f, 2.0f, 7.0f);
        j2.desenhaJogador(gl, glu, glut, 4.0f, 2.0f, 5.5f);
        j3.desenhaJogador(gl, glu, glut, -4.0f, 2.0f, 5.5f);
        j3.desenhaJogador(gl, glu, glut, 2.0f, 2.0f, 5.5f);
        //b1.desenhaBola(gl, glu, glut);
        
    }

    @Override
    public void reshape(GLAutoDrawable glad, int x, int y, int w, int h) {
     
        gl.glMatrixMode(GL2.GL_PROJECTION);
        glu.gluPerspective(65.0, (float) w / (float) h, 1.0, 20.0);
        gl.glTranslatef(0.0f, 0.0f, -10.0f);
        
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
    
}
