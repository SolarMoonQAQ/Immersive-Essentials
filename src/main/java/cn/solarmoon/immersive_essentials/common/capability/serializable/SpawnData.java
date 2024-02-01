package cn.solarmoon.immersive_essentials.common.capability.serializable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

public class SpawnData implements INBTSerializable<CompoundTag> {

    private BlockPos pos;
    private ResourceLocation dimension;
    public ResourceLocation world = Registries.LEVEL_STEM.location();
    private float yaw;
    private float pitch;

    public SpawnData() {}

    public void put(ServerPlayer player) {
        pos = player.getOnPos().above();
        dimension = player.level().dimension().location();
        yaw = player.getYRot();
        pitch = player.getXRot();
    }

    public BlockPos getPos() {
        return pos;
    }

    public ServerLevel getDimension(ServerPlayer player) {
        ResourceKey<Level> key = ResourceKey.create(ResourceKey.createRegistryKey(this.world), this.dimension);
        MinecraftServer server = player.server;
        return server.getLevel(key);
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isValid() {
        return pos != null;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        if (isValid()) {
            tag.putInt("x", pos.getX());
            tag.putInt("y", pos.getY());
            tag.putInt("z", pos.getZ());
            tag.putString("dimension", dimension.toString());
            tag.putFloat("yaw", yaw);
            tag.putFloat("pitch", pitch);
        }

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.pos = new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
        this.dimension = new ResourceLocation(tag.getString("dimension"));
        this.yaw = tag.getFloat("yaw");
        this.pitch = tag.getFloat("pitch");
    }

}
