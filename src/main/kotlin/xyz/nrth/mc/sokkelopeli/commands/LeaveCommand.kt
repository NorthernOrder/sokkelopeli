package xyz.nrth.mc.sokkelopeli.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.nrth.mc.sokkelopeli.Command
import xyz.nrth.mc.sokkelopeli.Sokkelopeli

class LeaveCommand : Command("leave") {
    override fun command(sender: CommandSender, args: Array<out String>) {
        if (sender !is Player) return

        if (sender !in Sokkelopeli.players) return

        Sokkelopeli.removePlayer(sender)
        sender.sendMessage("You are no longer in the game")
    }
}