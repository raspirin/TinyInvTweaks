package rasp.tit.mixin.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {
    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void mouseClickedHook(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        Logger logger = LoggerFactory.getLogger("tiny-inv-tweaks");
        logger.info("mouse clicked hit!");
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressedHook(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        Logger logger = LoggerFactory.getLogger("tiny-inv-tweaks");
        logger.info("key pressed hit!");
    }
}
