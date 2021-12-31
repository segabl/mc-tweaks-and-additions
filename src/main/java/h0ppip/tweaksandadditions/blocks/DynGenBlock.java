package h0ppip.tweaksandadditions.blocks;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public abstract class DynGenBlock extends Block {
	protected Identifier identifier;

	public DynGenBlock(Settings settings) {
		super(settings);
	}

	public Identifier getIdentifier() {
		return this.identifier;
	}

	public abstract void registerBlock();

	public void applyClientSettings() {
	}

	public abstract HashMap<Identifier, JsonObject> createRecipes();

	public abstract JsonUnbakedModel createModel(String type);

	public abstract HashMap<Identifier, JsonObject> createLootDrops();
}
