package com.lambda.client.module.modules.chat

import com.lambda.client.commons.utils.MathUtils
import com.lambda.client.manager.managers.MessageManager.newMessageModifier
import com.lambda.client.module.Category
import com.lambda.client.module.Module
import com.lambda.client.util.text.MessageDetection
import kotlin.math.min

object ColorChat : Module(
    name = "ChatColor",
    description = "Adds a symbol to the start of your messages to color your chat on anarchy servers",
    category = Category.CHAT,
    showOnArray = false,
    modulePriority = 100
) {

    private val green by setting("Green Text", false)
    private val blue by setting("Blue Text", false)
    private val red by setting("Red Text", false)
    private val yellow by setting("Yellow Text", false)
    private val Light_BLue by setting("Light Blue Text", false)
    private val commands by setting("Commands", false)
    private val spammer by setting("Spammer", false)

    private val modifier = newMessageModifier(
        filter = {
            (commands || MessageDetection.Command.ANY detectNot it.packet.message)
                && (spammer || it.source !is Spammer)
        },
        modifier = {
            val message = getText(it.packet.message)
            message.substring(0, min(256, message.length))
        }
    )

    init {
        onEnable {
            modifier.enable()
        }

        onDisable {
            modifier.disable()
        }
    }

    private fun getText(s: String): String {
        var string = s
        if (green) string = greenConverter(string)
        if (blue) string = blueConverter(string)
        if (red) string = redConverter(string)
        if (yellow) string = yellowcvt(string)
        if (Light_BLue) string = LightBluecvt(string)
        return string
    }
    private fun yellowcvt(input: String): String {
        return "! $input"
    }
    private fun LightBluecvt(input: String): String {
        return ": $input"
    }
    private fun greenConverter(input: String): String {
        return "> $input"
    }
    private fun redConverter(input: String): String {
        return "< $input"
    }

    private fun blueConverter(input: String): String {
        return "`$input"
    }

    override fun getHudInfo(): String {
        val returned = StringBuilder()
        if (green) returned.append(" >")
        if (blue) returned.append(" `")
        if (red) returned.append(" <")
        if (yellow) returned.append(" !")
        if (Light_BLue) returned.append(" :")
        return returned.toString()
    }

}
