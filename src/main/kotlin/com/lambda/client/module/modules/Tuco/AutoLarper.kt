package com.lambda.client.module.modules.Tuco

import com.lambda.client.event.listener.listener
import com.lambda.client.module.Category
import com.lambda.client.module.Module
import com.lambda.client.util.text.MessageSendHelper.sendServerMessage
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.util.Random

object AutoLarper : Module(
    name = "AutoLarper",
    description = "Pov you are Mer00z the jewish larper",
    category = Category.TUCOCLIENT,
    showOnArray = true,
    modulePriority = 1000
) {
    private val Delay by setting("Delay",  0,0..100, 1, description = "Spam tick delay")
    private var counter = 0;
    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
    init {
        onEnable { counter =0; }
        listener<TickEvent.ClientTickEvent> {
            if(this.isEnabled == true) {
                counter = counter +1;
                if(counter == Delay *20) {
                    counter =0;

                        sendServerMessage("<Mer00z> I'm a mero, I can't do anything, I have negative braincells, So i have to larp [0x" + rand(0, 999999) + "]");
                }
            }
          }
        }
}

