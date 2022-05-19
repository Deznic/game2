package game2.entity_system.components;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import java.nio.IntBuffer;

import org.lwjgl.system.MemoryStack;

import game2.Matrixes;
import game2.Setting;
import game2.systems.Callbacks;

public class Player extends Component{
	
	private float speed = 1.5f;
	
	private float lastX, lastY, pitch, yaw;
	private float sensitivity;
	
	public Player(long window) {
		sensitivity = Setting.getSettingFlt("sensitivity");
		try (MemoryStack stack = MemoryStack.stackPush()){
			IntBuffer w = stack.mallocInt(1), h = stack.mallocInt(1);
			glfwGetWindowSize(window, w, h);
			lastX = (float)w.get()/2.0f;
			lastY = (float)h.get()/2.0f;
		}
	}
	
	
	//TODO: continue this
	@Override
	public void update(float dt) {
		float moveSpeed = speed*dt;
		if (Callbacks.getKey(GLFW_KEY_LEFT_CONTROL)) {
			moveSpeed = (speed+2)*dt;
		}
		if (Callbacks.getKey(GLFW_KEY_A)) {
			Matrixes.view.translate(moveSpeed,0,0);
		}
		if (Callbacks.getKey(GLFW_KEY_D)) {
			Matrixes.view.translate(-moveSpeed,0,0);
		}
		if (Callbacks.getKey(GLFW_KEY_W)) {
			Matrixes.view.translate(0,0,moveSpeed);
		}
		if (Callbacks.getKey(GLFW_KEY_S)) {
			Matrixes.view.translate(0,0,-moveSpeed);
		}
		float xPos = Callbacks.mouseX, yPos = Callbacks.mouseY;
		
		float xOffset = xPos - lastX, yOffset = yPos - lastY;
		lastX = xPos;
		lastY = yPos;
		xOffset *= sensitivity;
		yOffset *= sensitivity;
		
		pitch += xOffset;
		yaw += yOffset;
		
		if(pitch > 89) {
			pitch = 89;
		}
		if(pitch < -89) {
			pitch = -89;
		}
	}

}
