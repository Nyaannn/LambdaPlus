package com.lambda.client.module.modules.chat

import com.lambda.client.event.listener.listener
import com.lambda.client.module.Category
import com.lambda.client.module.Module
import com.lambda.client.module.modules.chat.CustomChat.setting
import com.lambda.client.util.FolderUtils
import com.lambda.client.util.text.MessageDetection
import com.lambda.client.util.text.MessageSendHelper
import com.lambda.client.util.text.MessageSendHelper.sendServerMessage
import com.lambda.client.util.text.formatValue
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.io.File
import java.io.FileNotFoundException
import java.util.Random

object SpammerPlus : Module(
    name = "Spammer+",
    description = "When someone sends a chat message Lambda+ will automatically say your message",
    category = Category.CHAT,
    showOnArray = true,
    modulePriority = 1000
) {
    private val customText by setting("Spammer Text", "Default")
    private val Antispam by setting("Antispam", true)
    private val Delay by setting("Delay",  0,0..100, 1, description = "Spam tick delay")
    private var counter = 0;
    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
    init {
        onEnable {

            MessageSendHelper.sendChatMessage("Starting Spammer");
            counter=0;
        }
        onDisable {

            MessageSendHelper.sendChatMessage("Stopping Spammer");

        }


        listener<TickEvent.ClientTickEvent> {
            if(this.isEnabled == true) {
                counter=counter+1;
                if(counter==Delay*20) {
                    counter=0;
                    if (Antispam == true) {
                        sendServerMessage(customText + " [0x" + rand(0, 999999) + "]");
                    }
                    if (Antispam == false) {
                        sendServerMessage(customText);
                    }

                }

            }

            }
        }

}

