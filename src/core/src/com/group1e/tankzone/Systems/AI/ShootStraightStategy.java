package com.group1e.tankzone.Systems.AI;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.SizeComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.EntityFactory;
import com.group1e.tankzone.Entities.TankBarrel;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;

public class ShootStraightStategy implements AIShootStrategy {

    // TODO: I don't know what this code does so, refactor this code later
    // Taken from: https://stackoverflow.com/a/1084899/180343
    private Boolean collides(Vector2 lineStart, Vector2 lineEnd, Vector2 sphereCenter, float radius)
    {
        Vector2 d = lineEnd.sub(lineStart);
        Vector2 f = lineStart.sub(sphereCenter);

        /*

Taking

E is the starting point of the ray,
L is the end point of the ray,
C is the center of sphere you're testing against
r is the radius of that sphere
Compute:
d = L - E ( Direction vector of ray, from start to end )
f = E - C ( Vector from center sphere to ray start )
         */



        float a = d.dot( d ) ;
        float b = 2*f.dot( d ) ;
        float c = f.dot( f ) - radius*radius ;

        float discriminant = b*b-4*a*c;
        if( discriminant < 0 )
        {
            // no intersection
            return false;
        }
        else
        {
            // ray didn't totally miss sphere,
            // so there is a solution to
            // the equation.

            discriminant = (float)Math.sqrt( discriminant );

            // either solution may be on or off the ray so need to test both
            // t1 is always the smaller value, because BOTH discriminant and
            // a are nonnegative.
            float t1 = (-b - discriminant)/(2*a);
            float t2 = (-b + discriminant)/(2*a);

            // 3x HIT cases:
            //          -o->             --|-->  |            |  --|->
            // Impale(t1 hit,t2 hit), Poke(t1 hit,t2>1), ExitWound(t1<0, t2 hit),

            // 3x MISS cases:
            //       ->  o                     o ->              | -> |
            // FallShort (t1>1,t2>1), Past (t1<0,t2<0), CompletelyInside(t1<0, t2>1)

            if( t1 >= 0 && t1 <= 1 )
            {
                // t1 is the intersection, and it's closer than t2
                // (since t1 uses -b - discriminant)
                // Impale, Poke
                return true ;
            }

            // here t1 didn't intersect so we are either started
            // inside the sphere or completely past it
            if( t2 >= 0 && t2 <= 1 )
            {
                // ExitWound
                return true ;
            }

            // no intn: FallShort, Past, CompletelyInside
            return false ;
        }
    }

    @Override
    public void shoot(TankBarrel ai, TankBody enemy, Array<TankBarrel> friendlies) {
        if (!ai.canShoot())
            return;

        Entity target = ai.getComponent(TargetComponent.class).target;
        PositionComponent aiPos = target.getComponent(PositionComponent.class);
        PositionComponent enemyPos = enemy.getComponent(PositionComponent.class);

        float shootAngle = Util.getAngleBetweenTwoPoints(enemyPos.x, enemyPos.y, aiPos.x, aiPos.y);

        // If we shoot in this angle, our bullet may destroy other friendlies
        // Try to avoid this possibility

        for (TankBarrel friendlyBarrel : friendlies) {
            if (friendlyBarrel == ai)
                continue;

            TankBody friendly = (TankBody) friendlyBarrel.getComponent(TargetComponent.class).target;
            PositionComponent friendlyPos = friendly.getComponent(PositionComponent.class);
            SizeComponent friendlySize = friendly.getComponent(SizeComponent.class);

            Vector2 sphereCenter = new Vector2(friendlyPos.x, friendlyPos.y);
            Vector2 lineStart = new Vector2(aiPos.x, aiPos.y);
            Vector2 lineEnd = new Vector2(enemyPos.x, enemyPos.y);

            if (collides(lineStart, lineEnd, sphereCenter, friendlySize.size)) {
                return;
            }
        }

        ai.getComponent(AngleComponent.class).angle = shootAngle;

        EntityFactory.createBullet(ai, 500);
    }
}
