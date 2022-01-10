package at.h0ppip.tweaksandadditions.blocks;

import at.h0ppip.tweaksandadditions.TweaksAndAdditions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.item.Item;

public class ItemSackBlock extends CompressedItemBlock {

	public ItemSackBlock(Settings blockSettings, Item item) {
		super(blockSettings, item, new FabricItemSettings().group(item.getGroup()).rarity(item.getRarity(item.getDefaultStack())), "sack");

		TweaksAndAdditions.REGISTER_TRANSPARENT.add(this);
	}

	@Override
	public JsonUnbakedModel createModel(String type) {
		String namespace = this.identifier.getNamespace();
		String path = this.identifier.getPath();
		switch (type) {
			case "block":
				return JsonUnbakedModel.deserialize("{\"parent\":\"" + namespace + ":block/item_sack\"," +
						"\"textures\":{\"particle\":\"" + namespace + ":block/" + path + "\"," +
						"\"top\":\"" + namespace + ":block/" + path + "\"}}");
			case "item":
				return JsonUnbakedModel.deserialize("{\"parent\":\"" + namespace + ":block/" + path + "\"}");
		}
		return null;
	}
}
