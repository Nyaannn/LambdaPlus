package com.lambda.client.module.modules.client

import com.lambda.client.module.Category
import com.lambda.client.module.Module

object NotifColor : Module(
    name = "NotifColor",
    description = "Customize notif colors",
    category = Category.CLIENT,
    showOnArray = false,
    alwaysEnabled = true
) {
    public val chatcolorslider by setting("Notif Color", clor.BLACK)

    public val colorcode by setting("ColorCode", "d")
    public val ecolor by setting("Enabled Color", "d")
    public val dcolor by setting("Disabled Color", "d")

    public enum class clor {
        RED,
        GREEN,
        BLUE,
        PURPLE,
        PINK,
        BLACK
    }
}
