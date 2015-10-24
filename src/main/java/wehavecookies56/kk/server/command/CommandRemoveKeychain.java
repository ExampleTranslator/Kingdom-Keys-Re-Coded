package wehavecookies56.kk.server.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import wehavecookies56.kk.entities.ExtendedPlayer;
import wehavecookies56.kk.item.ItemKeyblade;
import wehavecookies56.kk.network.packet.PacketDispatcher;
import wehavecookies56.kk.network.packet.client.SyncExtendedPlayer;
import wehavecookies56.kk.network.packet.server.DeSummonKeyblade;
import wehavecookies56.kk.util.TextHelper;

public class CommandRemoveKeychain implements ICommand {

	private List aliases;
	public CommandRemoveKeychain() {
		this.aliases = new ArrayList();
		this.aliases.add("kkremovechain");
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getName() {
		return "removechain";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 2;
    }
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/removechain [player]";
	}

	@Override
	public List getAliases() {
		return this.aliases;
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return false;
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		if(sender.getCommandSenderEntity() instanceof EntityPlayer){
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) sender.getCommandSenderEntity());
			EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
			if(args.length == 0)
			{
				if(props.inventory.getStackInSlot(0) != null)
				{
					props.inventory.getStackInSlot(0).setItem(Items.paper);
					if(props.isKeybladeSummoned())
					{
						if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemKeyblade){// && props.isKeybladeSummoned() == true
							PacketDispatcher.sendToServer(new DeSummonKeyblade(player.inventory.getCurrentItem()));
						}
					}
					PacketDispatcher.sendToServer(new SyncExtendedPlayer(player));
					TextHelper.sendFormattedChatMessage("Your keychain has been removed!", EnumChatFormatting.YELLOW, (EntityPlayer) sender.getCommandSenderEntity());
				}
				else
				{
					TextHelper.sendFormattedChatMessage("The chain slot has no chain!", EnumChatFormatting.RED, (EntityPlayer) sender.getCommandSenderEntity());
				}
			}
			else if(args.length == 1)
			{
	            EntityPlayerMP playermp = args.length == 1 ? getPlayer(sender, args[0]) : getCommandSenderAsPlayer(sender);
				ExtendedPlayer propsmp = ExtendedPlayer.get(playermp);

				if(propsmp.inventory.getStackInSlot(0) != null)
				{
					propsmp.inventory.getStackInSlot(0).setItem(Items.paper);
					if(propsmp.isKeybladeSummoned())
					{
						if(playermp.inventory.getCurrentItem() != null && playermp.inventory.getCurrentItem().getItem() instanceof ItemKeyblade){// && propsmp.isKeybladeSummoned() == true
							PacketDispatcher.sendToServer(new DeSummonKeyblade(playermp.inventory.getCurrentItem()));
						}
					}
					PacketDispatcher.sendToServer(new SyncExtendedPlayer(playermp));
					TextHelper.sendFormattedChatMessage(args[0]+"'s keychain has been removed!", EnumChatFormatting.YELLOW, (EntityPlayer) sender.getCommandSenderEntity());
				}
				else
				{
					TextHelper.sendFormattedChatMessage("The chain slot has no chain!", EnumChatFormatting.RED, (EntityPlayer) sender.getCommandSenderEntity());
				}
			}
			else
			{
				TextHelper.sendFormattedChatMessage("Invalid arguments! Usage: /removechain [player]", EnumChatFormatting.RED, (EntityPlayer) sender.getCommandSenderEntity());
			}
		}
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender)
    {
        return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
    }

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return true;
	}
	
	public static EntityPlayerMP getCommandSenderAsPlayer(ICommandSender sender) throws PlayerNotFoundException
	{
	    if (sender instanceof EntityPlayerMP)
	    {
	        return (EntityPlayerMP)sender;
	    }
	    else
	    {
	        throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
	    }
	}
	
	public static EntityPlayerMP getPlayer(ICommandSender sender, String username) throws PlayerNotFoundException
    {
        EntityPlayerMP entityplayermp = PlayerSelector.matchOnePlayer(sender, username);

        if (entityplayermp == null)
        {
            try
            {
                entityplayermp = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(UUID.fromString(username));
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                ;
            }
        }

        if (entityplayermp == null)
        {
            entityplayermp = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(username);
        }

        if (entityplayermp == null)
        {
            throw new PlayerNotFoundException();
        }
        else
        {
            return entityplayermp;
        }
    }
}
