package watersplash;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = WATERMAIN.MODID, version = WATERMAIN.VERSION)
public class WATERMAIN
{
    public static final String MODID = "watermod";
	public static final String name = "Darwin Water Ambience";
    public static final String VERSION = "1.0";

	public static WATERMAIN instance;

	public static Configuration config;
	
	public WATERMAIN() {
		instance = this;
	}
	
	@SidedProxy
	(
	clientSide = "watersplash.BasisClientProxy", 
	serverSide = "watersplash.BasisCommonProxy"
	)
	
	public static BasisCommonProxy proxy;
	public static BasisClientProxy proxyclient;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent){

    	proxy.registerEvents();
		config = new Configuration(preEvent.getSuggestedConfigurationFile());
		ConfigurationMoD.loadConfig();
	}

    @EventHandler
    public void init(FMLInitializationEvent event){
    }

    
    @EventHandler
	public void postInit(FMLPostInitializationEvent postEvent){
    }

}
	