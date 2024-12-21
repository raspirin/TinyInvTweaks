package rasp.tit

import arrow.core.Option
import arrow.core.Some
import arrow.core.None
import net.fabricmc.api.ClientModInitializer
import rasp.tit.action.InvTweaksAction

object TinyInvTweaksClient : ClientModInitializer {
	private val config = TinyInvTweaksClientConfig()

	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}

	public fun executeAction(action: InvTweaksAction): Option<Boolean> {
		return None
	}
}