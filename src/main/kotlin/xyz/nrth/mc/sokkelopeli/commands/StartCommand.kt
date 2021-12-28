package xyz.nrth.mc.sokkelopeli.commands

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.nrth.mc.sokkelopeli.Command
import xyz.nrth.mc.sokkelopeli.Countdown
import xyz.nrth.mc.sokkelopeli.Sokkelopeli

class StartCommand : Command("start") {
    override fun command(sender: CommandSender, args: Array<out String>) {
        if (Sokkelopeli.running) {
            sender.sendMessage("Sokkelopeli is already running")
            return
        }

        Sokkelopeli.running = true
        val startPosition = (sender as Player).eyeLocation

        Sokkelopeli.checkpoints = args[0].toInt()

        Bukkit.broadcastMessage("Sokkelopeli is starting")
        Countdown("Sokkelopeli starts in", {
            Sokkelopeli.players.forEach {
                it.teleport(startPosition)
                it.gameMode = GameMode.SURVIVAL
            }
        }, Sokkelopeli.players).schedule()
    }
}