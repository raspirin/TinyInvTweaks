package rasp.tit.mixin.client;

import arrow.core.Option;
import kotlin.Pair;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
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
    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        Logger logger = TinyInvTweaksConst.INSTANCE.getLogger();
        logger.info("mouse clicked hit!");
        InvTweaksAction action = InvTweaksActionFactory.INSTANCE.fromMouseEvent(button);
        Option<Boolean> result = TinyInvTweaksClient.INSTANCE.executeAction(action);
        tinyInvTweaks$processActionResult(result, cir);
        if (!cir.getReturnValue()) {
            logger.warn("mixin(mouseClicked) will cancel the method and return false.");
        }
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        Logger logger = TinyInvTweaksConst.INSTANCE.getLogger();
        logger.info("mouse clicked hit!");
        InvTweaksAction action = InvTweaksActionFactory.INSTANCE.fromKeyEvent();
        Option<Boolean> result = TinyInvTweaksClient.INSTANCE.executeAction(action);
        tinyInvTweaks$processActionResult(result, cir);
        if (!cir.getReturnValue()) {
            logger.warn("mixin(keyPressed) will cancel the method and return false.");
        }
    }

    @Unique
    private <R> void tinyInvTweaks$processActionResult(@NotNull Option<R> result, CallbackInfoReturnable<R> cir) {
        if (result.isSome()) {
            R ret = result.getOrNull();
            cir.setReturnValue(ret);
        }
    }
}
