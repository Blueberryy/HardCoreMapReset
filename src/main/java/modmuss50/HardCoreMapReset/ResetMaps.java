package modmuss50.HardCoreMapReset;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiWorldSelection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class ResetMaps {
	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { //some JVMs return null for empty dirs
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	public static void copymap(String from, String to, GuiMapList mapList) {
		Minecraft mc = Minecraft.getMinecraft();


		GuiCopyProgress.progress.setStage("Evalutating files to copy");


		Thread copyThread = new Thread(() -> {
			File saveDir = new File(mc.mcDataDir, "saves");
			File backupDir = new File(mc.mcDataDir, "maps");
			File target = new File(saveDir, to);
			File source = new File(backupDir, from);
			if (target.exists()) {
				// TODO
				System.err.println("TODO");
				return;
			}
			target.mkdir();
			try {
				GuiCopyProgress.progress.setStage("Finding files to copy");
				GuiCopyProgress.progress.setStep(0);
				GuiCopyProgress.progress.setSteps(0);

				Set<Path> paths = Files.walk(source.toPath())
					.parallel().collect(Collectors.toSet());
				long files = paths.size();
				GuiCopyProgress.progress.setSteps((int) files);

				Path sourcePath = source.toPath();
				Path targetPath = target.toPath();
				paths.parallelStream()
					.forEach(path -> {
						try {
							GuiCopyProgress.progress.setStage("Copying: " + path.getFileName().toString());
							GuiCopyProgress.progress.next();
							Path targetFilePath = targetPath.resolve(sourcePath.relativize(path));
							if(targetFilePath.getParent().toFile().exists()){

							}
							Files.copy(path, targetFilePath);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
			} catch (IOException e) {
				e.printStackTrace();
			}
			Minecraft.getMinecraft().getSaveLoader().renameWorld(mapList.folderString, mapList.nameField.getText().trim());
			Minecraft.getMinecraft().addScheduledTask(() -> mc.displayGuiScreen(new GuiWorldSelection(new GuiMainMenu())));

		});
		mc.displayGuiScreen(new GuiCopyProgress(copyThread));

	}
}
