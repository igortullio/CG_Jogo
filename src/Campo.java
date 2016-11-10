
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Campo {

    private BufferedImage imagem;
    private TextureData texData;
    private ByteBuffer buffer;
    private int largura;
    private int altura;
    private Texture texturaCampo;
    private int[] idTextura;
    
    public void renderizaCampo(GL2 gl, GLU glu, GLUT glut) {
        
        //Habilita o uso da textura
        gl.glEnable(GL2.GL_TEXTURE_2D);

        // Comandos de inicialização para textura
        loadImage("campo.jpg");// somente carrega a imagem

        // Gera identificador de textura
        idTextura = new int[10];
        gl.glGenTextures(1, idTextura, 1);

        // Especifica qual é a textura corrente pelo identificador 
        gl.glBindTexture(GL.GL_TEXTURE_2D, idTextura[0]);

        // Envio da textura para OpenGL
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, largura,
                altura, 0, GL.GL_BGR, GL.GL_UNSIGNED_BYTE, buffer);

        // Define os filtros de magnificação e minificação 
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        
        try {
            InputStream stream = getClass().getResourceAsStream("campo.jpg");
            TextureData data = TextureIO.newTextureData(GLProfile.getDefault(), stream, false, "jpg");
            texturaCampo = TextureIO.newTexture(data);
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

        //Habilita a textura do campo
        texturaCampo.enable(gl);
        texturaCampo.bind(gl);

        // Desenha um cubo no qual a textura é aplicada
        gl.glEnable(GL.GL_TEXTURE_2D);	// Primeiro habilita uso de textura	  	

        gl.glPushMatrix();
        
            gl.glTranslatef(0.0f, 7.0f, 0.0f);
            gl.glScalef(10.0f, 10.0f, 10.0f);
            gl.glColor3f(1.0f, 1.0f, 1.0f);

            gl.glBegin(GL2.GL_QUADS);
                gl.glNormal3f(0.0f, -1.0f, 0.0f);
                gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(1.0f, -1.0f, 1.0f);
                gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, 1.0f);
            gl.glEnd();
        
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_TEXTURE_2D);	//	Desabilita uso de textura
        
    }
    
    public void loadImage(String nomeArq) {
        // Tenta carregar o arquivo		
        imagem = null;
        try {
            imagem = ImageIO.read(new File("src\\" + nomeArq));
            // Obtém largura e altura
            largura = imagem.getWidth();
            altura = imagem.getHeight();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + nomeArq);
            System.out.println(e.getStackTrace());
        }

        //Carrega a textura		
        try {
            InputStream stream = getClass().getResourceAsStream(nomeArq);
            texData = TextureIO.newTextureData(GLProfile.getDefault(), stream, false, "jpg");
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
        // ...e obtém um ByteBuffer a partir dela
        buffer = (ByteBuffer) texData.getBuffer();
    }
    
}
