package modmuss50.hcmr.voidWorld;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;

public class VoidChunkGenerator implements IChunkGenerator {
	private final World world;

	public VoidChunkGenerator(World world) {
		this.world = world;
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();
		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		chunk.generateSkylightMap();
		byte[] abyte = chunk.getBiomeArray();
		for (int i1 = 0; i1 < abyte.length; ++i1) {
			abyte[i1] = (byte) Biome.getIdForBiome(Biome.getBiome(1)); //Plains
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		if(x == 0 && z == 0){
			if(HCMRWorldTypes.structure != null){
				Template template = TemplateUtils.read(HCMRWorldTypes.structure);
				Pair<Template, BlockPos> templatePair = TemplateUtils.getTemplateWithSpawn(template);
				templatePair.getLeft().addBlocksToWorld(world, new BlockPos(0, 96, 0).subtract(templatePair.getRight()), new PlacementSettings());

				HCMRWorldTypes.structure = null;
			} else {
				world.setBlockState(new BlockPos(0, 93, 0), Blocks.STONE.getDefaultState());
			}
		}
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {

	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}
