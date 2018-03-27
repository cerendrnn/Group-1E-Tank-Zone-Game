package com.group1e.tankzone.Entities;

import com.badlogic.gdx.utils.ObjectMap;
import com.group1e.tankzone.Components.Component;

public abstract class Entity {
    private ObjectMap<Class, Component> components = new ObjectMap<Class, Component>();

    public <T extends Component> T getComponent(Class<T> cls) {
        return (T) components.get(cls);
    }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
    }

    public void removeComponent(Class cls) {
        components.remove(cls);
    }

    public void removeAllComponents() {
        components.clear();
    }
}
