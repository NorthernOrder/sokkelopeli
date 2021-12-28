package xyz.nrth.mc.sokkelopeli

import com.destroystokyo.paper.Title
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class Countdown(var title: String, var callback: () -> Unit, private val playerList: Collection<Player>) : Runnable {
    var time = 5

    fun schedule() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Sokkelopeli.instance, this, 20)
    }

    override fun run() {
        if (!Sokkelopeli.running) return

        if (time > 0) {
            playerList.forEach { p -> p.sendTitle(Title.builder().title("$title $time").build()) }
            time--
            Bukkit.getScheduler().scheduleSyncDelayedTask(Sokkelopeli.instance, this, 20)
            return
        }

        callback()
        playerList.forEach { p -> p.hideTitle() }
    }
}

