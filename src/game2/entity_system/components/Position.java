package game2.entity_system.components;

import org.joml.Vector3f;

import game2.Matrixes;
import game2.ShaderProg;

public class Position extends Component{
	private Vector3f position = new Vector3f(0,0,0);
	
	public Position(Vector3f pos) {
		position = pos;
	}
	
	@Override
	public void update(float dt) {
		Matrixes.model.identity();
		Matrixes.model.translate(position);
		ShaderProg.setUniMatrix4f(Matrixes.model, "model");
		
	}
}
