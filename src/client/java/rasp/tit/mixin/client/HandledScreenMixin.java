package rasp.tit.mixin.client;

import arrow.core.Option;
import kotlin.Pair;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
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
    private void mouseClickedHook(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        TinyInvTweaksConst.INSTANCE.getLogger().info("mouse clicked hit!");
        InvTweaksAction action = InvTweaksActionFactory.INSTANCE.fromMouseEvent();
        Pair<Boolean, Boolean> result = executeAction(action);
        if (result.component1()) {
            boolean rt = result.component2();
            cir.setReturnValue(rt);
            TinyInvTweaksConst.INSTANCE.getLogger().info("return value: {}", rt);
        }
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressedHook(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        TinyInvTweaksConst.INSTANCE.getLogger().info("key pressed hit!");
        InvTweaksAction action = InvTweaksActionFactory.INSTANCE.fromKeyEvent();
        Pair<Boolean, Boolean> result = executeAction(action);
        if (result.component1()) {
            boolean rt = result.component2();
            cir.setReturnValue(rt);
            TinyInvTweaksConst.INSTANCE.getLogger().info("return value: {}", rt);
        }
    }

    @Unique
    private Pair<Boolean, Boolean> executeAction(InvTweaksAction action) {
        Option<Boolean> result = TinyInvTweaksClient.INSTANCE.executeAction(action);
        if (result.isNone()) {
            // we have nothing to do with this case
            return new Pair<>(false, true);
        } else {
            Boolean unwrapped = result.getOrNull();
            assert unwrapped != null;
            return new Pair<>(true, unwrapped);
        }
    }
}
