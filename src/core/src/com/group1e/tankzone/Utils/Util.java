package com.group1e.tankzone.Utils;

import com.badlogic.gdx.math.MathUtils;

public class Util {
    public static float getAngleBetweenTwoPoints(float x1, float y1, float x2, float y2) {
        float delta_x = x2 - x1;
        float delta_y = y2 - y1;
        // NOTICE: the order is (y, x) as opposed to (x, y)
        float angle = 90 + MathUtils.atan2(delta_y, delta_x) * MathUtils.radiansToDegrees;

        return angle;
    }

}
