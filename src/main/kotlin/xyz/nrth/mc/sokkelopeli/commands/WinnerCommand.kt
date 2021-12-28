package xyz.nrth.mc.sokkelopeli.commands

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.BlockCommandSender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.nrth.mc.sokkelopeli.Command
import xyz.nrth.mc.sokkelopeli.Sokkelopeli
import xyz.nrth.mc.sokkelopeli.getNearestPlayer

class WinnerCommand : Command("winner") {
    override fun command(sender: CommandSender, args: Array<out String>) {
        if (!Sokkelopeli.running) return

        if (sender is Player) {
            Bukkit.broadcastMessage("${sender.name} tried to cheat!")
            return
        }

        val commandBlock = sender as BlockCommandSender
        val player = commandBlock.getNearestPlayer()
        val score = Sokkelopeli.playerScore(player)

        if (Sokkelopeli.checkpoints != score.score) {
            player.sendMessage("You haven't collected all the checkpoints!")
            return
        }

        Bukkit.broadcastMessage("${player.name} won the current Sokkelopeli!")

        Sokkelopeli.reset()
    }
}
