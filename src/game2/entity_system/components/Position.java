package game2.entity_system.components;

import org.joml.Vector3f;

import game2.Matrixes;
import game2.ShaderProg;

public class Position extends Component{
	private Vector3f position = new Vector3f(0,0,0);
	private ShaderProg prog;
	
	public Position(Vector3f pos, ShaderProg prog) {
		position = pos;
		this.prog = prog;
	}
	
	@Override
	public void update() {
		Matrixes.model.identity();
		Matrixes.model.translate(position);
		prog.setUniMatrix4f(Matrixes.model, "model");
		
	}
}
