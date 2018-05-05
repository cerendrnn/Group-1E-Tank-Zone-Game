package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class Wormhole extends Entity{

    public Wormhole(float pos1_x,float pos1_y,float pos2_x,float pos2_y){

          //texture???
          this.addComponent(new DamageComponent(1000, false));
          //this.addComponent(new GraphicsComponent());
    }
}
