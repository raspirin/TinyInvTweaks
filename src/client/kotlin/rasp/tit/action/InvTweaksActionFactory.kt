package rasp.tit.action

import net.minecraft.screen.slot.Slot

object InvTweaksActionFactory {
    public fun fromMouseEvent(button: Int, slot: Slot): InvTweaksAction {

        return InvTweaksAction(InvTweaksActionType.MoveAll, InvTweaksActionDirection.Upwards)
    }

    public fun fromKeyEvent(): InvTweaksAction {
        return InvTweaksAction(InvTweaksActionType.SortInventory, null)
    }
}