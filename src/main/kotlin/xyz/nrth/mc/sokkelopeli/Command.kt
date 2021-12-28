package xyz.nrth.mc.sokkelopeli

import org.bukkit.command.*
import org.bukkit.command.Command
import org.bukkit.entity.Player

abstract class Command(val name: String) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        this.command(sender, args)

        return true
    }

    abstract fun command(sender: CommandSender, args: Array<out String>)

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        return mutableListOf()
    }
}

fun BlockCommandSender.getNearestPlayer(): Player {
    return this.block.location.getNearbyPlayers(4.0).first()
}
