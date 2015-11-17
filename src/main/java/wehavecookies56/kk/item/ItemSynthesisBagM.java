package wehavecookies56.kk.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import wehavecookies56.kk.KingdomKeys;
import wehavecookies56.kk.inventory.ContainerSynthesisBagS;
import wehavecookies56.kk.inventory.InventorySynthesisBagS;
import wehavecookies56.kk.network.packet.PacketDispatcher;
import wehavecookies56.kk.network.packet.server.OpenGui;
import wehavecookies56.kk.util.GuiHelper;

public class ItemSynthesisBagM extends Item {

	public ItemSynthesisBagM() {this.setMaxStackSize(1);}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer player) {
		//if (!world.isRemote){
			//player.openGui(KingdomKeys.instance, KingdomKeys.GUI_SYNTHESISBAG_INV, player.worldObj, 0, 0, 0);
			PacketDispatcher.sendToServer(new OpenGui(KingdomKeys.GUI_SYNTHESISBAGM_INV));
		//}
		return itemStackIn;
	}
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add("By holding the synthesis bag in your inventory items will be sent into the synthesis table");
	}
}
