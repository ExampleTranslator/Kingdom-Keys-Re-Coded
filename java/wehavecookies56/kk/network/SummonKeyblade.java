package wehavecookies56.kk.network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import wehavecookies56.kk.entities.ExtendedPlayer;
import wehavecookies56.kk.item.ItemKeyblade;
import wehavecookies56.kk.item.ItemKeychain;
import wehavecookies56.kk.network.AbstractMessage.AbstractServerMessage;

public class SummonKeyblade extends AbstractMessage<SummonKeyblade> {

	ItemStack stack;

	public SummonKeyblade() {}

	public SummonKeyblade(ItemKeyblade itemKeyblade) {
		this.stack = new ItemStack(itemKeyblade);
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		stack = buffer.readItemStackFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeItemStackToBuffer(stack);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
		PacketDispatcher.sendToServer(new SyncExtendedPlayer(player));

	}

}
