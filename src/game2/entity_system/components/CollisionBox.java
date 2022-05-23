package game2.entity_system.components;

import org.joml.Vector3f;

import game2.entity_system.Entity;

public class CollisionBox extends Component{
	
	/**
	 * <pre>
	 * 0 = back, down, left    = -0.5f, -0.5f, -0.5f
	 * 1 = front, down, left   =  0.5f, -0.5f, -0.5f
	 * 2 = front, up, left     =  0.5f,  0.5f, -0.5f
	 * 3 = back, up, left      = -0.5f,  0.5f, -0.5f
	 * 4 = back, up, right     = -0.5f,  0.5f,  0.5f
	 * 5 = back, down, right   = -0.5f, -0.5f,  0.5f
	 * 6 = front, down, right  =  0.5f, -0.5f,  0.5f
	 * 7 = front, up, right    =  0.5f,  0.5f,  0.5f
	 * </pre>
	 */
	private Vector3f[] vertBox = new Vector3f[]{
		new Vector3f(-0.5f, -0.5f, -0.5f),//back, down, left
		new Vector3f( 0.5f, -0.5f, -0.5f),//front, down, left
		new Vector3f( 0.5f,  0.5f, -0.5f),//front, up, left
		new Vector3f(-0.5f,  0.5f, -0.5f),//back, up, left
		new Vector3f(-0.5f,  0.5f,  0.5f),//back, up, right
		new Vector3f(-0.5f, -0.5f,  0.5f),//back, down, right
		new Vector3f( 0.5f, -0.5f,  0.5f),//front, down, right
		new Vector3f( 0.5f,  0.5f,  0.5f) //front, up, right
	};
	
	@Override
	public void start() {
		Transformation transform = entity.getComponent(Transformation.class);
		for (int i = 0; i < vertBox.length; i++) {
			vertBox[i].add(transform.getPos());
			vertBox[i].add(transform.getScale());
			System.out.println(vertBox[i]);
		}
		
		super.start();
	}
	
	@Override
	public void update(float dt) {
		
	}

}
