package at.h0ppip.tweaksandadditions.blocks;

import at.h0ppip.tweaksandadditions.TweaksAndAdditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class CompressedItemBlock extends GeneratedBlock {
	protected Item item;
	protected Identifier itemIdentifier;
	protected BlockItem blockItem;

	public CompressedItemBlock(Settings blockSettings, Item item, FabricItemSettings itemSettings, String type) {
		super(blockSettings);

		this.item = item;
		this.itemIdentifier = Registry.ITEM.getId(this.item);
		this.identifier = new Identifier(TweaksAndAdditions.MOD_ID, this.itemIdentifier.getPath() + "_" + type);
		this.blockItem = new BlockItem(this, itemSettings);
	}

	public void register() {
		Registry.register(Registry.BLOCK, this.identifier, this);
		Registry.register(Registry.ITEM, this.identifier, this.blockItem);
	}

	private JsonObject createCraftRecipe() {
		JsonObject json = new JsonObject();
		json.addProperty("type", "minecraft:crafting_shaped");
		JsonArray jsonArray = new JsonArray();
		jsonArray.add("###");
		jsonArray.add("###");
		jsonArray.add("###");
		json.add("pattern", jsonArray);

		JsonObject keyList = new JsonObject();
		JsonObject key = new JsonObject();
		key.addProperty("item", this.itemIdentifier.toString());
		keyList.add("#", key);
		json.add("key", keyList);

		JsonObject result = new JsonObject();
		result.addProperty("item", this.identifier.toString());
		result.addProperty("count", 1);
		json.add("result", result);

		return json;
	}

	private JsonObject createUncraftRecipe() {
		JsonObject json = new JsonObject();

		json.addProperty("type", "minecraft:crafting_shapeless");

		JsonObject ingredient = new JsonObject();
		ingredient.addProperty("item", this.identifier.toString());
		JsonArray ingredientsArray = new JsonArray();
		ingredientsArray.add(ingredient);
		json.add("ingredients", ingredientsArray);

		JsonObject result = new JsonObject();
		result.addProperty("item", this.itemIdentifier.toString());
		result.addProperty("count", 9);
		json.add("result", result);

		return json;
	}

	@Override
	public HashMap<Identifier, JsonObject> createRecipes() {
		HashMap<Identifier, JsonObject> map = new HashMap<>();

		map.put(this.identifier, createCraftRecipe());
		map.put(new Identifier(this.identifier.getNamespace(), this.itemIdentifier.getPath() + "_from_" + this.identifier.getPath()), createUncraftRecipe());

		return map;
	}

	@Override
	public HashMap<Identifier, JsonObject> createLootDrops() {
		HashMap<Identifier, JsonObject> map = new HashMap<>();

		JsonObject json = new JsonObject();
		json.addProperty("type", "minecraft:block");

		JsonArray pools = new JsonArray();
		JsonObject pool = new JsonObject();
		pool.addProperty("rolls", 1);

		JsonArray entries = new JsonArray();
		JsonObject entry = new JsonObject();
		entry.addProperty("type", "minecraft:item");
		entry.addProperty("name", this.identifier.toString());
		entries.add(entry);
		pool.add("entries", entries);

		JsonArray conditions = new JsonArray();
		JsonObject condition = new JsonObject();
		condition.addProperty("condition", "minecraft:survives_explosion");
		conditions.add(condition);
		pool.add("conditions", conditions);

		pools.add(pool);
		json.add("pools", pools);

		map.put(new Identifier(this.identifier.getNamespace(), "blocks/" + this.identifier.getPath()), json);

		return map;
	}

	@Override
	public JsonUnbakedModel createModel(String type) {
		String namespace = this.identifier.getNamespace();
		String path = this.identifier.getPath();
		switch (type) {
			case "block":
				return JsonUnbakedModel.deserialize("{\"parent\":\"minecraft:block/cube_all\"," +
						"\"textures\":{\"all\": \"" + namespace + ":block/" + path + "\"}}");
			case "item":
				return JsonUnbakedModel.deserialize("{\"parent\":\"" + namespace + ":block/" + path + "\"}");
		}
		return null;
	}
}
