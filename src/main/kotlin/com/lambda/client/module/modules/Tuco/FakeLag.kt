package com.lambda.client.module.modules.Tuco

import com.lambda.client.event.listener.listener
import com.lambda.client.module.Category
import com.lambda.client.module.Module
import com.lambda.client.module.modules.player.Blink
import com.lambda.client.util.text.MessageSendHelper.sendServerMessage
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.util.Random

object FakeLag : Module(
    name = "FakeLag",
    description = "Toggles blink every few ticks",
    category = Category.TUCOCLIENT,
    showOnArray = true,
    modulePriority = 1000
) {
    private val Delay by setting("Delay",  1,1..100, 1, description = "tick delay")
    private var counter = 0;
    init {
        onEnable { counter =0; }
        listener<TickEvent.ClientTickEvent> {
            if(this.isEnabled == true) {
                counter = counter +1;
                if(counter == Delay *20) {
                    counter =0;
                    Blink.toggle()

                }
            }
          }
        }
}

