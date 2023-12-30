package net.tony.testmod.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tony.testmod.TestMod;
import net.tony.testmod.entity.ModEntities;
import net.tony.testmod.entity.custom.RhinoEntity;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
    }

}
