package rasp.tit.mixin.client;

import arrow.core.Option;
import kotlin.Pair;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rasp.tit.TinyInvTweaksClient;
import rasp.tit.TinyInvTweaksConst;
import rasp.tit.action.InvTweaksAction;
import rasp.tit.action.InvTweaksActionFactory;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {
    @Shadow
    protected abstract @Nullable Slot getSlotAt(double x, double y);

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        Logger logger = TinyInvTweaksConst.INSTANCE.getLogger();
        logger.info("mouse clicked hit!");

        Slot slot = this.getSlotAt(mouseX, mouseY);
        if (slot == null) {
            return;
        }

        InvTweaksAction action = InvTweaksActionFactory.INSTANCE.fromMouseEvent(button, slot);
        Option<Boolean> result = TinyInvTweaksClient.INSTANCE.executeAction(action);
        boolean earlyReturn = tinyInvTweaks$processActionResult(result, cir);
        if (earlyReturn && !cir.getReturnValue()) {
            logger.warn("mixin(mouseClicked) will cancel the method and return false.");
        }
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        Logger logger = TinyInvTweaksConst.INSTANCE.getLogger();
        logger.info("key pressed hit!");
        InvTweaksAction action = InvTweaksActionFactory.INSTANCE.fromKeyEvent();
        Option<Boolean> result = TinyInvTweaksClient.INSTANCE.executeAction(action);
        boolean earlyReturn = tinyInvTweaks$processActionResult(result, cir);
        if (earlyReturn && !cir.getReturnValue()) {
            logger.warn("mixin(keyPressed) will cancel the method and return false.");
        }
    }

    @Unique
    private <R> boolean tinyInvTweaks$processActionResult(@NotNull Option<R> result, CallbackInfoReturnable<R> cir) {
        if (result.isSome()) {
            R ret = result.getOrNull();
            cir.setReturnValue(ret);
            return true;
        }
        return false;
    }
}
