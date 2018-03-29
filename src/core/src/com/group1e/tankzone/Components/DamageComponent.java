package com.group1e.tankzone.Components;

public class DamageComponent implements Component {
    public int damage;
    public boolean disposable;

    public DamageComponent(int damage, boolean disposable) {
        this.damage = damage;
        this.disposable = disposable;
    }
}
