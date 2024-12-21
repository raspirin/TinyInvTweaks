package rasp.tit

import arrow.core.Option
import arrow.core.Some
import arrow.core.none
import net.fabricmc.api.ClientModInitializer
import rasp.tit.action.InvTweaksAction

object TinyInvTweaksClient : ClientModInitializer {
    private val config = TinyInvTweaksClientConfig()

    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
    }

    /**
     * Execute the action.
     *
     * @return The LHS indicates whether we should return early, and the RHS indicates the return value.
     */
    public fun executeAction(action: InvTweaksAction): Pair<Boolean, Option<Boolean>> {
        return Pair(false, none())
    }
}