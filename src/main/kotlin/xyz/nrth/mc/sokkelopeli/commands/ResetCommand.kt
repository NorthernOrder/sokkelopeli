package xyz.nrth.mc.sokkelopeli.commands

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.BlockCommandSender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.nrth.mc.sokkelopeli.Command
import xyz.nrth.mc.sokkelopeli.Sokkelopeli

class ResetCommand : Command("reset") {
    override fun command(sender: CommandSender, args: Array<out String>) {
        if (!Sokkelopeli.running) return

        Bukkit.broadcastMessage("Sokkelopeli was canceled")

        Sokkelopeli.reset()
    }
}