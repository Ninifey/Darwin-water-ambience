package watersplash.particles;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import watersplash.ConfigurationMoD;
import watersplash.WATERMAIN;

public class ParticleWaterRipple extends EntityFX
{
	private int p = 0;
	private double dde;
public ParticleWaterRipple(World par1World, double par2, double par4, double par6, float par8, float par9, float par10)
{
this(par1World, par2, par4, par6, 1.0F, par8, par9, par10);
}
public ParticleWaterRipple(World par1World, double par2, double par4, double par6, float par8, float par9, float par10, float par11)
{
super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
this.motionX = par9;
this.motionY = par10;
this.motionZ = par11;
dde=Math.random();
if (par9 == 0.0F)
{
par9 = 1.0F;
}
float var12 = (float)Math.random() * 0.4F + 0.6F;
this.particleTextureIndexX = 11; //
this.particleTextureIndexY = 2;
this.particleRed = 1.0F;//RGB of your particle
this.particleGreen = 1.0F;
this.particleBlue = 1.0F;
this.particleScale *= 2.4F*ConfigurationMoD.RIPPLE_SCALE_MODIFIER_SIZE;
this.particleScale *= dde;

this.particleMaxAge = 7;//how soon the particle dies. You can use randomizer for this
this.noClip = false;//does your particle collide with blocks?
}



@Override
public void onUpdate()
{
		++p;
		if(p >= 2) {p = 0;
		++particleTextureIndexX;}
	
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
if (this.particleAge++ >= this.particleMaxAge)
{
this.setDead();
}
}

@Override
@SideOnly(Side.CLIENT)
public int getBrightnessForRender(float p_70070_1_)
{
    return 200;   
}

@Override
@SideOnly(Side.CLIENT)
public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
{

par1Tessellator.draw();
GL11.glEnable(GL11.GL_BLEND);
GL11.glDepthMask(false);
GL11.glEnable(GL11.GL_ALPHA_TEST);
Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(WATERMAIN.MODID+":textures/gui/particles.png"));
par1Tessellator.startDrawingQuads();
par1Tessellator.setBrightness(200);//make sure you have this!!
render(par1Tessellator, par2, -0.99F, 1.22F, 0.002F, -0.01F, -0.99F);
par1Tessellator.draw();
par1Tessellator.startDrawingQuads();
Minecraft.getMinecraft().getTextureManager().bindTexture( new ResourceLocation("textures/particle/particles.png"));
}


public void render(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
{
    float f6 = (float)this.particleTextureIndexX / 16.0F;
    float f7 = f6 + 0.0624375F;
    float f8 = (float)this.particleTextureIndexY / 16.0F;
    float f9 = f8 + 0.0624375F;
    float f10 = 0.1F * this.particleScale;

    if (this.particleIcon != null)
    {
        f6 = this.particleIcon.getMinU();
        f7 = this.particleIcon.getMaxU();
        f8 = this.particleIcon.getMinV();
        f9 = this.particleIcon.getMaxV();
    }

    float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)p_70539_2_ - interpPosX);
    float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)p_70539_2_ - interpPosY);
    float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)p_70539_2_ - interpPosZ);
    p_70539_1_.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
    p_70539_1_.addVertexWithUV((double)(f11 - p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - 0 * f10), (double)(f13 - p_70539_5_ * f10 - p_70539_7_ * f10), (double)f7, (double)f9);
    p_70539_1_.addVertexWithUV((double)(f11 - p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + 0 * f10), (double)(f13 - p_70539_5_ * f10 + p_70539_7_ * f10), (double)f7, (double)f8);
    p_70539_1_.addVertexWithUV((double)(f11 + p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + 0 * f10), (double)(f13 + p_70539_5_ * f10 + p_70539_7_ * f10), (double)f6, (double)f8);
    p_70539_1_.addVertexWithUV((double)(f11 + p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - 0 * f10), (double)(f13 + p_70539_5_ * f10 - p_70539_7_ * f10), (double)f6, (double)f9);
}
}