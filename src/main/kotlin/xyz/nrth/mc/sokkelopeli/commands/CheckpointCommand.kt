package xyz.nrth.mc.sokkelopeli.commands

import org.bukkit.Bukkit
import org.bukkit.command.BlockCommandSender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.nrth.mc.sokkelopeli.Command
import xyz.nrth.mc.sokkelopeli.Sokkelopeli
import xyz.nrth.mc.sokkelopeli.getNearestPlayer

class CheckpointCommand: Command("checkpoint") {
    override fun command(sender: CommandSender, args: Array<out String>) {
        if (!Sokkelopeli.running) return

        if (sender is Player) {
            Bukkit.broadcastMessage("${sender.name} tried to cheat!")
            return
        }

        val checkpoint = args[0].toInt()
        val commandBlock = sender as BlockCommandSender

        val player = commandBlock.getNearestPlayer()

        if (player !in Sokkelopeli.players) return

        val score = Sokkelopeli.playerScore(player)

        if (checkpoint > score.score + 1) {
            sender.sendMessage("You haven't collected enough checkpoints to get this one (${score.score} of $checkpoint required)")
            return
        }

        if (score.score >= checkpoint) {
            return
        }

        score.score++
        Bukkit.broadcastMessage("${player.name} got checkpoint $checkpoint")
    }
}