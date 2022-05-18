package game2;

import static org.joml.Math.toRadians;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import org.joml.Vector3f;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import game2.entity_system.Entity;
import game2.entity_system.EntitySystem;
import game2.entity_system.components.Drawable;
import game2.entity_system.components.Position;

public class Game {
	private long windowId;
	EntitySystem entitySystem = new EntitySystem();
	ShaderProg shaderProgram;
	public void start() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) throw new RuntimeException("failed to initialize glfw");
		
		Setting.loadSetting();
		
		windowId = glfwCreateWindow(
				Setting.getSettingInt("window_width"), 
				Setting.getSettingInt("window_height"), 
				Setting.getSettingStr("window_name"), 
				0, 0);
		
		glfwMakeContextCurrent(windowId);
		glfwSwapInterval(1);
		GL.createCapabilities();
		
		Matrixes.projection.perspective(
			Setting.getSettingInt("fov"), 
			Setting.getSettingInt("window_width")/Setting.getSettingInt("window_height"), 
			0, 100);
		
		Matrixes.view.rotate(toRadians(90), new Vector3f(1,0,0));
		Matrixes.view.translate(new Vector3f(0,-5,0));
		
		shaderProgram = new ShaderProg("src/game2/data/vertex.vs", "src/game2/data/fragment.fs");
		ShaderProg.setUniMatrix4f(Matrixes.projection, "projection");
		ShaderProg.setUniMatrix4f(Matrixes.view, "view");
		ShaderProg.setUniMatrix4f(Matrixes.model, "model");
		shaderProgram.use();
		
		Entity test = new Entity(), test2 = new Entity();
		test.addComponent(new Drawable(Drawable.Rectangle));
		test2.addComponent(new Drawable(Drawable.Rectangle));
		test.addComponent(new Position(new Vector3f(-2,0,0)));
		test2.addComponent(new Position(new Vector3f(2,0,0)));
		
		entitySystem.addEntity(test);
		entitySystem.addEntity(test2);
		
		entitySystem.start();
		
		glClearColor(0, 0.5f, 1, 1);
		
		while (!glfwWindowShouldClose(windowId)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			loop();
			
			glfwSwapBuffers(windowId);
			
			glfwPollEvents();
		}
		
		Callbacks.glfwFreeCallbacks(windowId);
		glfwDestroyWindow(windowId);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	private void loop() {
		shaderProgram.use();
		entitySystem.update();
	}
	
}
