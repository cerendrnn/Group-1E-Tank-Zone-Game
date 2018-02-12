package com.group1e.tankzone.Components;

import com.badlogic.gdx.graphics.Texture;

public class GraphicsComponent implements Component {
    public Texture texture;

    public GraphicsComponent(Texture texture) {
        this.texture = texture;
    }
}
