package game2.systems;

import static org.joml.Math.toRadians;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.opengl.GL11.glViewport;

import game2.Matrixes;
import game2.Setting;
import game2.ShaderProg;

public class Callbacks {
	private static boolean keyCode[];
	public static float mouseX, mouseY;
	public static void initCallbacks(long w) {
		keyCode = new boolean[GLFW_KEY_LAST];
		glfwSetKeyCallback(w, (window, key, scancode, action, mods) -> {
			if (action == GLFW_PRESS) {
				keyCode[key] = true;
			}
			if (action == GLFW_RELEASE) {
				keyCode[key] = false;
			}
		});
		glfwSetFramebufferSizeCallback(w, (window, width, height) -> {
			glViewport(0, 0, width, height);
			Matrixes.projection.identity();
			Matrixes.projection.perspective(toRadians(Setting.getSettingInt("fov")), (float)width/(float)height, 0, 100);
			ShaderProg.setUniMatrix4f(Matrixes.projection, "projection");
		});
		glfwSetCursorPosCallback(w, (window, xpos, ypos) -> {
			mouseX = (float)xpos;
			mouseY = (float)ypos;
		});
	}
	public static boolean getKey(int keycode) {
		return keyCode[keycode];
	}
}
