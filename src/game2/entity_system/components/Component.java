package game2.entity_system.components;

import game2.entity_system.Entity;

public abstract class Component {
	public Entity entity = null;
	public void start() {};
	public abstract void update();
}
