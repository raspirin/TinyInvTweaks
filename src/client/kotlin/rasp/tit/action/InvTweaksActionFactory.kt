package rasp.tit.action

object InvTweaksActionFactory {
    public fun fromMouseEvent(button: Int): InvTweaksAction {

        return InvTweaksAction(InvTweaksActionType.MoveAll, InvTweaksActionDirection.Upwards)
    }

    public fun fromKeyEvent(): InvTweaksAction {
        return InvTweaksAction(InvTweaksActionType.SortInventory, null)
    }
}