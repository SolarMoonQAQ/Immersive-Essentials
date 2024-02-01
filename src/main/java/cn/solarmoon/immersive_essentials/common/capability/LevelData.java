package cn.solarmoon.immersive_essentials.common.capability;

import cn.solarmoon.immersive_essentials.api.ILevelData;
import cn.solarmoon.immersive_essentials.common.IECapabilities;
import cn.solarmoon.immersive_essentials.common.capability.serializable.SpawnData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LevelData implements ILevelData, ICapabilitySerializable<CompoundTag> {

    private final Level level;
    private final LazyOptional<ILevelData> levelData = LazyOptional.of(() -> this);

    private final SpawnData spawnData;

    public LevelData(Level level) {
        this.level = level;
        this.spawnData = new SpawnData();
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public SpawnData getSpawnData() {
        return spawnData;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == IECapabilities.LEVEL_DATA) {
            return levelData.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.put("spawnData", spawnData.serializeNBT());

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        spawnData.deserializeNBT(tag.getCompound("spawnData"));
    }

}
