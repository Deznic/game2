package game2;

import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_SHADER_TYPE;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import util.FileUtils;

/**
 * 
 * @author Deznic
 * @version 1.0.0
 * @apiNote Personal use
 * @since 2022
 */
public class ShaderProg {
	
	static int id;
	int vertId;
	int fragId;
	
	/**
	 * @param vertex shader path
	 * @param fragment shader path
	 */
	public ShaderProg(String vert, String frag) {
		id = glCreateProgram();
		vertId = glCreateShader(GL_VERTEX_SHADER);
		fragId = glCreateShader(GL_FRAGMENT_SHADER);
		
		createShader(vert, vertId);
		createShader(frag, fragId);
		
		glLinkProgram(id);
		if (glGetProgrami(id, GL_LINK_STATUS) == 0) 
			throw new RuntimeException("Failed to link program reason: \n"+glGetProgramInfoLog(id));
		
		use();
	}
	
	void createShader(String path, int sId) {
		String type = "";
		switch(glGetShaderi(sId, GL_SHADER_TYPE)) {
		case GL_VERTEX_SHADER:
			type = "Vertex shader";
			break;
		case GL_FRAGMENT_SHADER:
			type = "Fragment shader";
			break;
		}
		glShaderSource(sId, FileUtils.readShaderFile(path));
		glCompileShader(sId);
		if (glGetShaderi(sId, GL_COMPILE_STATUS) == 0) 
			throw new RuntimeException("Unable to compile "+type+" reason: \n"+glGetShaderInfoLog(sId));
		glAttachShader(id, sId);
		glDeleteShader(sId);
	}
	
	public static void setUniMatrix4f(Matrix4f val, String name) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			FloatBuffer vBuffer = stack.mallocFloat(16);
			val.get(vBuffer);
			glUniformMatrix4fv(glGetUniformLocation(ShaderProg.id, name), false, vBuffer);
		}
	}
	
	/**
	 * glUseProgram(id);
	 */
	public void use() {
		glUseProgram(id);
	}
	/**
	 * glUseProgram(0);
	 */
	public void unUse() {
		glUseProgram(0);
	}
}
