package watersplash;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class TextureRegister {
	
	public static Color getColorForBlock(Block bl,int side, int meta) {
		
		if(bl.getIcon(side, meta) != null) {
			
			String  name = bl.getIcon(side, meta).getIconName();
		
		if(name != null) {
			
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(name);
		
		if(sprite != null) {
			
			int[][] arr = sprite.getFrameTextureData(1);
			
			if(arr != null) {
				
				int xLenght = arr[0].length/2;
				int yLength = arr[0].length/2;
				BufferedImage b = new BufferedImage(xLenght, yLength, 3);

				for(int x = 0; x <  arr[0].length/2-1; x++) {
					for(int y = 0; y <  arr[0].length/2-1; y++) {
						int rgb = (int)arr[0][y+x]<<16 | (int)arr[0][y+x] << 8 | (int)arr[0][y+x];
						b.setRGB(x, y, rgb);
					}
				}
				Color c = null;
				IIcon icocn = bl.getIcon(side, meta);
				if(b != null) {
					c = new Color(((BufferedImage) b).getRGB((int)icocn.getMinU(), (int)icocn.getMaxU()));
				}
					return c;

					}
				}
			}
		}
		return null;
	}
}
