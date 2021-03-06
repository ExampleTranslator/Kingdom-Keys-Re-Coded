package uk.co.wehavecookies56.kk.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import uk.co.wehavecookies56.kk.client.sound.ModSounds;
import uk.co.wehavecookies56.kk.common.lib.Strings;
import uk.co.wehavecookies56.kk.common.network.packet.PacketDispatcher;
import uk.co.wehavecookies56.kk.common.network.packet.server.RemoveItemInSlot;

import java.util.List;

public class ItemPowerBoost extends Item {
	public ItemPowerBoost() {
		super();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer playerIn, EnumHand hand) {
		itemStackIn.stackSize--;
		if (world.isRemote) {
			PacketDispatcher.sendToServer(new RemoveItemInSlot(Strings.PowerBoost, playerIn.inventory.currentItem));
		}
		playerIn.worldObj.playSound(playerIn, playerIn.getPosition(), ModSounds.itemget, SoundCategory.MASTER, 1.0f, 1.0f);
		return super.onItemRightClick(itemStackIn, world, playerIn, hand);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Permanently increases Strength by 1");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}
