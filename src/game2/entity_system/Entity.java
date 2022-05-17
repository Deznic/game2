package game2.entity_system;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import game2.entity_system.components.Component;

public class Entity {
	private List<Component> components = new ArrayList<>();
	private UUID id;
	
	public <T extends Component> T getComponent(Class<T> componentClass) {
		for (Component comp : components) {
			if (componentClass.isAssignableFrom(comp.getClass())) {
				try {
					return componentClass.cast(comp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public <T extends Component> void removeComponent(Class<T> componentClass) {
		for(int i = 0; i < components.size(); i++) {
			Component comp = components.get(i);
			if (componentClass.isAssignableFrom(comp.getClass())) {
				components.remove(i);
				return;
			}
		}
	}
	
	public void addComponent(Component comp) {
		components.add(comp);
		comp.entity = this;
	}
	
	public void update() {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).update();
		}
	}
	
	public void start() {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).start();
		}
	}
}
