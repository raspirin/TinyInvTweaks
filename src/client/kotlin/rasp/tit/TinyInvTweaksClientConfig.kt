package rasp.tit

import org.lwjgl.glfw.GLFW

class TinyInvTweaksClientConfig {
    // direction hint
    public val upwardsHint = hashSetOf(GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_UP)
    public val downwardsHint = hashSetOf(GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_DOWN)

    // action leader
}