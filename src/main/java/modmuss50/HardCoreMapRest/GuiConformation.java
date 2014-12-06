package modmuss50.HardCoreMapRest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;

/**
 * Created by Mark on 19/07/14.
 */
public class GuiConformation extends GuiScreen {
    public GuiScreen parent;

    public GuiConformation() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(1, 10 , this.height - 38, "Yes"));
        this.buttonList.add(new GuiButton(2, this.width - 10 - 200 , this.height - 38 , "No"));
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.enabled && button.id == 1) {
           ResetMaps.resetMaps();
        }
        if (button.enabled && button.id == 2) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiSelectWorld(new GuiMainMenu()));
        }

    }

    @Override
    public void drawScreen(int x, int y, float f) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Are you sure?", this.width / 2, 40, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "You will loose all of your progress and worlds.", this.width / 2, 70, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "Your saves folder will be reset back to defaults.", this.width / 2, 80, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "THIS CANNOT BE UNDONE! ", this.width / 2, this .height - 50, 0xff0000);
        drawScreen2(x, y, f);
        super.drawScreen(x, y, f);
    }

    public void drawScreen2(int p_73863_1_, int p_73863_2_, float p_73863_3_) {

    }

    public void setParent(GuiScreen parent) {
        this.parent = parent;
    }
}