
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;


public class Bola {

    public void desenhaBola(GL2 gl, GLU glu, GLUT glut){
        
        
        gl.glPushMatrix();
                
            gl.glTranslatef (2.7f, 4.2f, 0.0f);            
            glut.glutSolidSphere(0.5f, 30, 30);            

        gl.glPopMatrix();
        
    }
    
}
