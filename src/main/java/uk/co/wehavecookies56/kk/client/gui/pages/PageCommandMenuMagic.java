package uk.co.wehavecookies56.kk.client.gui.pages;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class PageCommandMenuMagic extends Page {

	String text = "\"Magic\" is an option on the Command Menu which brings up a list of available magics to cast. For more information on the different magics and casting magic look at the pages under the \"Magic\" section.";

	public PageCommandMenuMagic (int xPos, int yPos) {
		super("CommandMenu_Magic", xPos, yPos);
		setxPos(xPos);
		setyPos(yPos);
	}

	@Override
	public void drawPageForeground (int width, int height) {
		super.drawPageForeground(width, height);
		FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
		String[] count = text.split("\n");
		fontRendererObj.drawSplitString(text, getxPos(), getyPos() + (fontRendererObj.FONT_HEIGHT * 2), (new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - getxPos() - (fontRendererObj.FONT_HEIGHT * 2), 0xFFFFFF);
		int length = fontRendererObj.splitStringWidth(text, (new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - getxPos() - (fontRendererObj.FONT_HEIGHT * 2));
		// fontRendererObj.drawString("TESTING SO THAT THIS IS ALWAYS AT THE
		// BOTTOM.", this.getxPos(), (this.getyPos() +
		// (fontRendererObj.FONT_HEIGHT * 2)) + length, 0xFFFFFF);
	}
}
