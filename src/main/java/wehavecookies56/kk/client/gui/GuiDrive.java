package wehavecookies56.kk.client.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import wehavecookies56.kk.entities.ExtendedPlayer;
import wehavecookies56.kk.lib.Reference;
import wehavecookies56.kk.lib.Strings;
import wehavecookies56.kk.util.TextHelper;

@SideOnly(Side.CLIENT)
public class GuiDrive extends GuiScreen {
	Minecraft mc = Minecraft.getMinecraft();

	ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/drivebar.png");

	int maxDrive = 1000;
	int maxLength = 100;
	int maxBars = 9;
	double oneValue = (46D / 100D);
	double currDrive;

	public GuiDrive(){

	}

	public int getCurrBar(int dp){
		int bar = 0;
		if(dp<100){
			bar = 0;
		}else if(dp<=200&&dp>100){
			bar=1;
		}else if(dp<=300&&dp>200){
			bar=2;
		}else if(dp<=400&&dp>300){
			bar=3;
		}else if(dp<=500&&dp>400){
			bar=4;
		}else if(dp<=600&&dp>500){
			bar=5;
		}else if(dp<=700&&dp>600){
			bar=6;
		}else if(dp<=800&&dp>700){
			bar=7;
		}else if(dp<=900&&dp>800){
			bar=8;
		}else if(dp<=1000&&dp>900){
			bar=9;
		}
		return bar;
	}

	@SubscribeEvent
	public void onRenderOverlayPost(RenderGameOverlayEvent event){

		ExtendedPlayer props = ExtendedPlayer.get(mc.thePlayer);
		int dp = props.getDP();

		currDrive = (float) ((oneValue * dp) - getCurrBar(dp)*46);

		if(dp == 100 || dp == 200 || dp == 300 || dp == 400 || dp == 500 || dp == 600 || dp == 700 || dp == 800 || dp == 900){
			currDrive = 0;
		}



		if(event.type == RenderGameOverlayEvent.ElementType.TEXT) {
			int guiWidth = 95;
			int guiBarWidth = 83;
			int guiHeight = 18;
			int screenWidth = event.resolution.getScaledWidth();
			int screenHeight = event.resolution.getScaledHeight();
			EntityPlayer player = mc.thePlayer;

			mc.renderEngine.bindTexture(texture);

			float scale = 0.65f;
			if(mc.gameSettings.guiScale == 0){
				scale = 0.65f;
			}else if (mc.gameSettings.guiScale == 3){
				scale = 0.65f;
			}else if (mc.gameSettings.guiScale == 2){
				scale = 0.65f;
			}else{
				scale = 1f;
			}
			float posX = 52*scale;
			float posY = 20*scale;

			GL11.glPushMatrix();
			GL11.glTranslatef((screenWidth - guiWidth*scale) - posX, (screenHeight - guiHeight*scale) - posY, 0);
			GL11.glScalef(scale, scale, scale);
			//Background
			this.drawTexturedModalRect(0, 0, 0, 0, guiWidth, guiHeight);
			GL11.glPopMatrix();
			//Yellow meter
			GL11.glPushMatrix();
			GL11.glTranslatef((screenWidth - guiWidth*scale) + (guiWidth - guiBarWidth)*scale + (24*scale) - posX, (screenHeight - guiHeight*scale) - (2*scale) - posY, 0);
			GL11.glScalef(scale, scale, scale);
			this.drawTexturedModalRect(0, 0, 0, 18, (int) currDrive, guiHeight);
			GL11.glPopMatrix();
			//Level
			GL11.glPushMatrix();
			GL11.glTranslatef((screenWidth - guiWidth*scale) + (85*scale) - posX, (screenHeight - guiHeight*scale) - (2*scale) - posY, 0);
			GL11.glScalef(scale, scale, scale);
			if(getCurrBar(dp) == 0)
			{
				this.drawTexturedModalRect(0, 0, 0, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 1)
			{
				this.drawTexturedModalRect(0, 0, 10, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 2)
			{
				this.drawTexturedModalRect(0, 0, 20, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 3)
			{
				this.drawTexturedModalRect(0, 0, 30, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 4)
			{
				this.drawTexturedModalRect(0, 0, 40, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 5)
			{
				this.drawTexturedModalRect(0, 0, 50, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 6)
			{
				this.drawTexturedModalRect(0, 0, 60, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 7)
			{
				this.drawTexturedModalRect(0, 0, 70, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 8)
			{
				this.drawTexturedModalRect(0, 0, 80, 38, 8, guiHeight);
			}
			else if(getCurrBar(dp) == 9)
			{
				this.drawTexturedModalRect(0, 0, 90, 38, 8, guiHeight);
			}

			GL11.glPopMatrix();
			if(dp >= 1000)
			{
				GL11.glPushMatrix();
				GL11.glTranslatef((screenWidth - guiWidth*scale), screenHeight - guiHeight*scale - 17, 0);
				this.drawTexturedModalRect((screenWidth - guiWidth - 2), screenHeight - guiHeight - 16, 0, 57, 30, guiHeight);
				GL11.glPopMatrix();
			}
			else
			{
				GL11.glPushMatrix();
				GL11.glTranslatef((screenWidth - guiWidth*scale), screenHeight - guiHeight*scale - 17, 0);
				this.drawTexturedModalRect((screenWidth - guiWidth - 23), screenHeight - guiHeight - 20, 0, 100, 30, guiHeight);
				GL11.glPopMatrix();
			}
		}
	}
}