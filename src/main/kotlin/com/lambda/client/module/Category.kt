package com.lambda.client.module

import com.lambda.client.commons.interfaces.DisplayEnum

enum class Category(override val displayName: String) : DisplayEnum {
    CHAT("Chat"),
    CLIENT("Client"),
    COMBAT("Combat"),
    MISC("Misc"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    EXPLOITS("Exploits"),
    TUCOCLIENT("Tuco");
    override fun toString() = displayName
}