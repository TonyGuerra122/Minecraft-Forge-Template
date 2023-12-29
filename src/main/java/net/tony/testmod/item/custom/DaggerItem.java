package net.tony.testmod.item.custom;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class DaggerItem extends SwordItem{

    private float criticalMultiplier;

    public DaggerItem(Tier p_43269_, int p_43270_, float p_43271_, float criticalMultiplier, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
        this.criticalMultiplier = criticalMultiplier;
    }

    @Override
    public float getDamage() {
        return super.getDamage() * criticalMultiplier;
    }
    
}
