package game2.entity_system.components;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Drawable extends Component{
	
	public static final int vao = glGenVertexArrays();
	
	float[] vert;
	int vertCount;
	
	public Drawable(float[] shape) {
		vert = shape;
		vertCount = vert.length/3;
		glBindVertexArray(vao);
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, vert, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		glBindVertexArray(0);
	}
	
	public void draw() {
		glBindVertexArray(vao);
		glDrawArrays(GL_TRIANGLES, 0, vertCount);
		glBindVertexArray(0);
	}

	@Override
	public void update(float dt) {
		draw();
	}
	
	public float[] getVert() {
		return vert;
	}
	
	public static final float[]
		Rectangle = new float[] {
				-0.5f, -0.5f, -0.5f, 
			     0.5f, -0.5f, -0.5f, 
			     0.5f,  0.5f, -0.5f, 
			     0.5f,  0.5f, -0.5f, 
			    -0.5f,  0.5f, -0.5f, 
			    -0.5f, -0.5f, -0.5f, 

			    -0.5f, -0.5f,  0.5f, 
			     0.5f, -0.5f,  0.5f, 
			     0.5f,  0.5f,  0.5f, 
			     0.5f,  0.5f,  0.5f, 
			    -0.5f,  0.5f,  0.5f, 
			    -0.5f, -0.5f,  0.5f, 

			    -0.5f,  0.5f,  0.5f, 
			    -0.5f,  0.5f, -0.5f, 
			    -0.5f, -0.5f, -0.5f, 
			    -0.5f, -0.5f, -0.5f, 
			    -0.5f, -0.5f,  0.5f, 
			    -0.5f,  0.5f,  0.5f, 

			     0.5f,  0.5f,  0.5f, 
			     0.5f,  0.5f, -0.5f, 
			     0.5f, -0.5f, -0.5f, 
			     0.5f, -0.5f, -0.5f, 
			     0.5f, -0.5f,  0.5f, 
			     0.5f,  0.5f,  0.5f, 

			    -0.5f, -0.5f, -0.5f, 
			     0.5f, -0.5f, -0.5f, 
			     0.5f, -0.5f,  0.5f, 
			     0.5f, -0.5f,  0.5f, 
			    -0.5f, -0.5f,  0.5f, 
			    -0.5f, -0.5f, -0.5f, 

			    -0.5f,  0.5f, -0.5f, 
			     0.5f,  0.5f, -0.5f, 
			     0.5f,  0.5f,  0.5f, 
			     0.5f,  0.5f,  0.5f, 
			    -0.5f,  0.5f,  0.5f, 
			    -0.5f,  0.5f, -0.5f,
		};
}
