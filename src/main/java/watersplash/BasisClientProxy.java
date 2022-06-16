package watersplash;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class BasisClientProxy extends BasisCommonProxy{
	
	@Override
	public void registerEvents() {

    	WeatherHelper weatherevent = new WeatherHelper();
        MinecraftForge.EVENT_BUS.register(weatherevent);
		FMLCommonHandler.instance().bus().register(weatherevent);
		
	}

}

