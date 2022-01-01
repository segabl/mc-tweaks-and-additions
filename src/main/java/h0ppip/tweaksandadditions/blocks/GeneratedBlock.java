package h0ppip.tweaksandadditions.blocks;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GeneratedBlock extends Block {
	public static final ArrayList<GeneratedBlock> BLOCKS = new ArrayList<>();

	protected Identifier identifier;

	public GeneratedBlock(Settings settings) {
		super(settings);

		BLOCKS.add(this);
	}

	public Identifier getIdentifier() {
		return this.identifier;
	}

	public abstract BlockItem getBlockItem();

	public abstract void register();

	public void applyClientSettings() {
	}

	public abstract HashMap<Identifier, JsonObject> createRecipes();

	public abstract HashMap<Identifier, JsonObject> createLootDrops();

	public abstract JsonUnbakedModel createModel(String type);
}
