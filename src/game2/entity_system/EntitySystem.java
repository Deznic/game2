package game2.entity_system;

import java.util.ArrayList;
import java.util.List;

public class EntitySystem {
	List<Entity> entities = new ArrayList<>();
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
	public void start() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).start();
		}
	}
}
