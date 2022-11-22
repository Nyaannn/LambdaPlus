package com.lambda.client.module.modules.chat

import com.lambda.client.event.events.PacketEvent
import com.lambda.client.event.listener.listener
import com.lambda.client.manager.managers.FriendManager
import com.lambda.client.module.Category
import com.lambda.client.module.Module
import com.lambda.client.util.text.MessageDetection
import com.lambda.client.util.text.MessageSendHelper.sendServerMessage
import net.minecraft.network.play.server.SPacketChat

object AutoTPA0b0t : Module(
    name = "AutoTPA0b0t",
    description = "Automatically accept or decline /TPAs on 0b0t",
    category = Category.CHAT
) {
    private val friends by setting("Always Accept Friends", true)
    private val mode by setting("Response", Mode.DENY)

    private enum class Mode {
        ACCEPT, DENY
    }

    init {
        listener<PacketEvent.Receive> {
            if (it.packet !is SPacketChat || MessageDetection.Other.TPA_REQUEST detectNot it.packet.chatComponent.unformattedText) return@listener

            /* I tested that getting the first word is compatible with chat timestamp, and it as, as this is Receive and chat timestamp is after Receive */
            val name = it.packet.chatComponent.unformattedText.split(" ")[0]

            when (mode) {
                Mode.ACCEPT -> sendServerMessage("/tpy $name")
                Mode.DENY -> {
                    if (friends && FriendManager.isFriend(name)) {
                        sendServerMessage("/tpy $name")
                    } else {
                        sendServerMessage("/tpn $name")
                    }
                }
            }
        }
    }
}