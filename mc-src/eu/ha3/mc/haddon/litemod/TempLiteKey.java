package eu.ha3.mc.haddon.litemod;

import net.minecraft.src.KeyBinding;

import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.util.ModUtilities;

import eu.ha3.mc.haddon.Haddon;
import eu.ha3.mc.haddon.OperatorKeyer;

/*
--filenotes-placeholder
*/

public class TempLiteKey extends LiteBase implements OperatorKeyer
{
	public TempLiteKey(Haddon haddon)
	{
		super(haddon);
	}
	
	@Override
	public void addKeyBinding(KeyBinding bind)
	{
		ModUtilities.registerKey(bind);
	}
	
	@Override
	public void removeKeyBinding(KeyBinding bind)// TODO
	{
		ModUtilities.unRegisterKey(bind);
	}
}
