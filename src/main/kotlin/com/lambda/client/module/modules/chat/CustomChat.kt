package com.lambda.client.module.modules.chat

import com.lambda.client.manager.managers.MessageManager.newMessageModifier
import com.lambda.client.module.Category
import com.lambda.client.module.Module
import com.lambda.client.util.text.MessageDetection
import com.lambda.client.util.text.MessageSendHelper
import kotlin.math.min
import java.util.Random

object CustomChat : Module(
    name = "ChatSuffix",
    description = "Add a custom ending to your message!",
    category = Category.CHAT,
    showOnArray = false,
    modulePriority = 200
) {
    private val textMode by setting("Message", TextMode.NAME)
    private val decoMode by setting("Separator", DecoMode.NONE)
    private val commands by setting("Commands", false)
    private val spammer by setting("Spammer", false)
    private val Antispamuwu by setting("Antispam", false)

    private val customText by setting("Custom Text", "Default")
    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
    private enum class DecoMode {
        SEPARATOR, CLASSIC, BRACKETS ,NONE
    }

    private enum class TextMode {
        NAME, ON_TOP,NIGGER,IMSTUPID,CUSTOM
    }

    private val modifier = newMessageModifier(
        filter = {
            (commands || MessageDetection.Command.ANY detectNot it.packet.message)
                && (spammer || it.source !is Spammer)
        },
        modifier = {
            if(Antispamuwu == true){
                val message = it.packet.message + getFull() +" [0x"+ SpammerPlus.rand(0, 999999) +"]"
                message.substring(0, min(256, message.length))

            }else {
                val message = it.packet.message + getFull()
                message.substring(0, min(256, message.length))

            }
        }
    )

    init {
        onEnable {
            if (textMode == TextMode.CUSTOM && customText.equals("Default", ignoreCase = true)) {
                MessageSendHelper.sendWarningMessage("$chatName In order to use the Custom message, please change the CustomText setting in ClickGUI")
                disable()
            } else {
                modifier.enable()
            }
        }

        onDisable {
            modifier.disable()
        }
    }

    private fun getText() = when (textMode) {
        TextMode.NAME -> "Lambda+"
        TextMode.ON_TOP -> "LAMBDA PLUS ON TOP!"
        TextMode.NIGGER -> "NIGGER!"
        TextMode.IMSTUPID -> "<---- IM FUCKING INSANE!"
        TextMode.CUSTOM -> customText
    }

    private fun getFull() = when (decoMode) {
        DecoMode.NONE -> " " + getText()
        DecoMode.CLASSIC -> " \u00ab " + getText() + " \u00bb"
        DecoMode.SEPARATOR -> " | " + getText()
        DecoMode.BRACKETS -> " [" + getText() + "]"
    }

}
