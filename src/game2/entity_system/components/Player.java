package game2.entity_system.components;

import static org.joml.Math.ceil;
import static org.joml.Math.cos;
import static org.joml.Math.floor;
import static org.joml.Math.round;
import static org.joml.Math.sin;
import static org.joml.Math.toRadians;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_HIDDEN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import game2.Matrixes;
import game2.Setting;
import game2.ShaderProg;
import game2.systems.Callbacks;

public class Player extends Component{
	
	private float speed = 1.5f;
	
	private float sensitivity;
	
	camera camera;
	
	public Player(long window, Vector3f pos) {
		sensitivity = Setting.getSettingFlt("sensitivity");
		
		camera = new camera(pos);
	}
	
	
	//TODO: continue this
	@Override
	public void update(float dt) {
		camera.update(dt);
	}
	
	private class camera {
		public Vector3f cameraPos;
		private Vector3f cameraUp, cameraRight, cameraFront, cameraDirection;
		private float pitch, yaw;
		private float lastX, lastY;
		public camera(Vector3f pos) {
			/*
			 * 
			 */
			
			lastX = (float)Callbacks.windowWidth/2.0f;
			lastY = (float)Callbacks.windowHeight/2.0f;
			
			cameraPos = pos;
			cameraUp = new Vector3f(0,1,0);
			cameraDirection = new Vector3f();
			cameraRight = new Vector3f();
			cameraFront = new Vector3f();
			
			cameraDirection.x = cos(toRadians(yaw)) * cos(toRadians(pitch));
			cameraDirection.y = sin(toRadians(pitch));
			cameraDirection.z = sin(toRadians(yaw)) * cos(toRadians(pitch));
			
			cameraUp.cross(cameraDirection, cameraRight);//camera right
			cameraRight.normalize();
			
			cameraRight.cross(cameraUp, cameraFront);//camera front
			System.out.println(cameraFront);
			
			Callbacks.setCursorInputMode(GLFW_CURSOR_DISABLED);
		}
		public void update(float dt) {
			
			float xPos = Callbacks.getMousePos()[0], yPos = Callbacks.getMousePos()[1];
			
			float xOffset = xPos - lastX, yOffset = lastY - yPos;
			lastX = xPos;
			lastY = yPos;
			xOffset *= sensitivity;
			yOffset *= sensitivity;
			
			yaw += xOffset;
			pitch += yOffset;
			
			if(pitch > 89) {
				pitch = 89;
			}
			if(pitch < -89) {
				pitch = -89;
			}
			
			cameraDirection.x = cos(toRadians(yaw)) * cos(toRadians(pitch));
			cameraDirection.y = sin(toRadians(pitch));
			cameraDirection.z = sin(toRadians(yaw)) * cos(toRadians(pitch));
			
			cameraDirection.normalize(cameraFront);
			cameraDirection.y = 0;
			cameraDirection.normalize();
			
			Vector3f result = new Vector3f();
			float moveSpeed = speed*dt;
			if (Callbacks.getKey(GLFW_KEY_LEFT_CONTROL)) {
				moveSpeed = (speed+2)*dt;
			}
			if (Callbacks.getKey(GLFW_KEY_A)) {//getting camera right or left idk
				cameraDirection.cross(cameraUp, result);
				result.normalize();
				result.mul(moveSpeed);
				cameraPos.x -= result.x;
				cameraPos.z -= result.z;
			}
			if (Callbacks.getKey(GLFW_KEY_D)) {
				cameraDirection.cross(cameraUp, result);
				result.normalize();
				result.mul(moveSpeed);
				cameraPos.x += result.x;
				cameraPos.z += result.z;
			}
			if (Callbacks.getKey(GLFW_KEY_W)) {
				cameraDirection.mul(moveSpeed, result);
				cameraPos.x += result.x;
				cameraPos.z += result.z;
			}
			if (Callbacks.getKey(GLFW_KEY_S)) {
				cameraDirection.mul(moveSpeed, result);
				cameraPos.x -= result.x;
				cameraPos.z -= result.z;
			}
			
			Matrixes.view.identity();
			Matrixes.view.lookAt(cameraPos, cameraPos.add(cameraFront, new Vector3f()), cameraUp);
			ShaderProg.setUniMatrix4f(Matrixes.view, "view");
		}
	}
}
