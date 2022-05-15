package game2;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;

import org.lwjgl.glfw.GLFWErrorCallback;

public class Game {
	private long windowId;
	public void start() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) throw new RuntimeException("failed to initialize glfw");
		windowId = glfwCreateWindow(0, 0, null, windowId, windowId);
		
	}
}
