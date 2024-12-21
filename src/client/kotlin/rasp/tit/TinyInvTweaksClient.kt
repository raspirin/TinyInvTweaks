package rasp.tit

import net.fabricmc.api.ClientModInitializer
import rasp.tit.action.InvTweaksAction

object TinyInvTweaksClient : ClientModInitializer {
	private val config = TinyInvTweaksClientConfig()

	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}

	public fun executeAction(action: InvTweaksAction) {

	}
}