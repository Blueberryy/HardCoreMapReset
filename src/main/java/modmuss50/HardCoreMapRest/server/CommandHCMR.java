package modmuss50.HardCoreMapRest.server;

import modmuss50.HardCoreMapRest.MapReset;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;

public class CommandHCMR implements ICommand {
	@Override
	public String getCommandName() {
		return "hcmr";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/hcmr";
	}

	@Override
	public List getCommandAliases() {
		ArrayList<String> list = new ArrayList<String>();
		return list;
	}

	@Override
	public void processCommand(ICommandSender player, String[] args) {
		ArrayList<String> maps = new ArrayList<String>();
		maps.add("test");
		maps.add("hello");
		MapReset.networkWrapper.sendTo(new PacketOpen(maps), (EntityPlayerMP) player);
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender player) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender player, String[] args) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] player, int p_82358_2_) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
