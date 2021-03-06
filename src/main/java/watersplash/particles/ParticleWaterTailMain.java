package watersplash.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import watersplash.ConfigurationMoD;

public class ParticleWaterTailMain extends EntityFX
{
	private double random = Math.random();
	private int timer = 0;
	private Entity entityL;
	
public ParticleWaterTailMain(World theWorld, Entity e){
	this(theWorld, e.posX, e.posY, e.posZ, 1.0F, e.motionX, e.motionY, e.motionZ, e);
}

public ParticleWaterTailMain(World par1World, double par2, double par4, double par6, float par8, double motionX, double motionY, double motionZ, Entity entity)
{
super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);

this.particleTextureIndexX = 0; 
this.particleTextureIndexY = 0;

this.particleRed = 1.0F;
this.particleGreen = 1.0F;
this.particleBlue = 1.0F;

entityL = entity;
this.posY-=1;

this.particleMaxAge = 15555;
this.noClip = false;
}

@Override
public void onUpdate()
{
	    if(entityL != null && !entityL.isDead) {
		this.posX = entityL.posX;
		this.posZ = entityL.posZ;
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
    	
    	int y = (int) entityL.posY;
		if(entityL instanceof EntityPlayer) {
			y -= 1;
		}
		
		Material m = entityL.worldObj.getBlock((int)entityL.posX, (int)entityL.posY-1, (int)entityL.posZ).getMaterial();
		Material m2 = entityL.worldObj.getBlock((int)entityL.posX, (int)entityL.posY, (int)entityL.posZ).getMaterial();

		if((m2 == Material.water)||(m == Material.water)&& (entityL.motionX != 0 || entityL.motionZ != 0) && ++timer > ConfigurationMoD.TRAIL_RARITY_MODIFIER){
			int poY = 0;
			int o = 0 ;
			 while((o<5)) {
				 ++o;
				 if(entityL.worldObj.getBlock((int)entityL.posX, (int)entityL.posY-2+o, (int)entityL.posZ).getMaterial() == Material.air) {
					poY = (int)entityL.posY-2+o;
				 	o = 10;
				 	if(entityL instanceof EntityItem)
				 		this.setDead();
				 } 
			 }
			ParticleEffects.spawnTail(entityL,poY,false);
			timer = 0;
		}else if(this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		} 
	}else {
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
	
	}
}