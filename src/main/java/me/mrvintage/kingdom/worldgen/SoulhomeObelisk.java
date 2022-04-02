package me.mrvintage.kingdom.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class SoulhomeObelisk  extends StructureFeature<DefaultFeatureConfig> {

    public SoulhomeObelisk(Codec<DefaultFeatureConfig> configCodec, StructureGeneratorFactory<DefaultFeatureConfig> piecesGenerator) {
        super(configCodec, piecesGenerator);
    }
}
