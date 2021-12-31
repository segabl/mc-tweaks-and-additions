package h0ppip.tweaksandadditions.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ItemSackBlock extends CompressedItemBlock {
	private Item item;
	private Identifier itemIdentifier;

	public ItemSackBlock(Settings settings, Item item) {
		super(settings, item, "sack");
	}

	@Override
	public void applyClientSettings() {
		BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
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
