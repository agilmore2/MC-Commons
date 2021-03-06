package eu.ha3.mc.haddon.litemod;

import java.io.File;

import net.minecraft.client.Minecraft;

import com.mumfrey.liteloader.InitCompleteListener;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.core.LiteLoader;

import eu.ha3.mc.haddon.Haddon;
import eu.ha3.mc.haddon.OperatorCaster;
import eu.ha3.mc.haddon.implem.HaddonUtilityImpl;
import eu.ha3.mc.haddon.supporting.SupportsFrameEvents;
import eu.ha3.mc.haddon.supporting.SupportsTickEvents;

/*
--filenotes-placeholder
*/

public class LiteBase implements LiteMod, InitCompleteListener, OperatorCaster
{
	protected final Haddon haddon;
	protected final boolean shouldTick;
	protected final boolean suTick;
	protected final boolean suFrame;
	
	protected int tickCounter;
	protected boolean enableTick;
	protected boolean enableFrame;
	
	public LiteBase(Haddon haddon)
	{
		this.haddon = haddon;
		this.suTick = haddon instanceof SupportsTickEvents;
		this.suFrame = haddon instanceof SupportsFrameEvents;
		this.shouldTick = this.suTick || this.suFrame;
		
		this.haddon.setUtility(new HaddonUtilityImpl() {
			@Override
			public long getClientTick()
			{
				return getTicks();
			}
		});
		this.haddon.setOperator(this);
	}
	
	@Override
	public String getName()
	{
		return this.haddon.getIdentity().getHaddonName();
	}
	
	@Override
	public String getVersion()
	{
		return this.haddon.getIdentity().getHaddonHumanVersion();
	}
	
	@Override
	public void onInitCompleted(Minecraft minecraft, LiteLoader loader)
	{
		this.haddon.onLoad();
	}
	
	@Override
	public void init(File configPath)
	{
	}
	
	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath)
	{
	}
	
	@Override
	public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock)
	{
		if (!this.shouldTick)
			return;
		
		if (!inGame)
			return;
		
		if (this.enableTick && clock)
		{
			if (this.suTick)
			{
				((SupportsTickEvents) this.haddon).onTick();
			}
			this.tickCounter++;
		}
		
		if (this.enableFrame)
		{
			if (this.suFrame)
			{
				((SupportsFrameEvents) this.haddon).onFrame(partialTicks);
			}
		}
	}
	
	@Override
	public void setTickEnabled(boolean enabled)
	{
		this.enableTick = enabled;
	}
	
	@Override
	public void setFrameEnabled(boolean enabled)
	{
		this.enableFrame = enabled;
	}
	
	@Override
	public int getTicks()
	{
		return this.tickCounter;
	}
}
