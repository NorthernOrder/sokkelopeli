package xyz.nrth.mc.sokkelopeli.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.nrth.mc.sokkelopeli.Command
import xyz.nrth.mc.sokkelopeli.Sokkelopeli

class JoinCommand : Command("join") {
    override fun command(sender: CommandSender, args: Array<out String>) {
        if (Sokkelopeli.running) return

        if (sender !is Player) return

        if (sender in Sokkelopeli.players) {
            sender.sendMessage("You have already joined the next game")
            return
        }

        Sokkelopeli.addPlayer(sender)
        sender.sendMessage("You will now be in the next game")
    }
}