package game2.entity_system.components;

import javax.swing.border.LineBorder;

import org.joml.Vector3f;

import game2.Matrixes;
import game2.ShaderProg;

public class Transformation extends Component{
	private Vector3f 
		position = new Vector3f(0,0,0),
		rotation = new Vector3f(0,0,0),
		scale = new Vector3f(1,1,1);
	private float rotationAngle = 0;
	
	/** @param pos - position
	 *  @param scl - scale
	 *  @param rot - rotation axis
	 *  @param rotangle - rotation angle
	 *  */
	public Transformation(Vector3f pos, Vector3f scl, Vector3f rot, float rotangle) {
		if (pos != null) {position = pos;}
		if (scl != null) {scale = scl;}
		if (rot != null) {rotation = rot; rotationAngle = rotangle;}
		
	}
	
	/** @param mag - magnitude */
	public void move(Vector3f mag) {
		position.add(mag);
	}
	
	/** @param scl - scale */
	public void resize(Vector3f scl) {
		scale = scl;
	}
	
	public void rotate(float angle, Vector3f axis) {
		rotation = axis;
		rotationAngle = angle;
	}
	
	/** reset rotations */
	public void resetRot() {
		rotation.zero();
		rotationAngle = 0;
	}
	
	public Vector3f getPos() {
		return position;
	}
	
	/** return rotation axis, to get rotation angle use {@link #getRotAngle()} */
	public Vector3f getRot() {
		return rotation;
	}
	
	public Vector3f getScale() {
		return scale;
	}
	
	/** return rotation angle, to get rotation axis use {@link #getRot()} */
	public float getRotAngle() {
		return rotationAngle;
	}
	
	@Override
	public void update(float dt) {
		Matrixes.model.identity();
		Matrixes.model.translate(position);
		Matrixes.model.rotate(rotationAngle, rotation);
		Matrixes.model.scale(scale);
		ShaderProg.setUniMatrix4f(Matrixes.model, "model");
		
	}
}
