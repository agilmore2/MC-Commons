package eu.ha3.mc.haddon.supporting;

import net.minecraft.src.ChatMessageComponent;

/* x-placeholder-wtfplv2 */

public interface SupportsChatEvents
{
	/**
	 * Triggered when the OperatorChatter receives chat while it's enabled.
	 * 
	 * @param chat
	 * @param message
	 */
	public void onChat(ChatMessageComponent chat, String message);
	
}
