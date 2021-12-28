package xyz.nrth.mc.sokkelopeli

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class SokkeloListener : Listener {
    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        if (event.player !in Sokkelopeli.players) return

        Sokkelopeli.removePlayer(event.player)
    }
}