package rasp.tit.action

object InvTweaksActionFactory {
    public fun fromMouseEvent(): InvTweaksAction {
        return InvTweaksAction(InvTweaksActionType.MoveAll, InvTweaksActionDirection.Upwards)
    }

    public fun fromKeyEvent(): InvTweaksAction {
        return InvTweaksAction(InvTweaksActionType.SortInventory, null)
    }
}