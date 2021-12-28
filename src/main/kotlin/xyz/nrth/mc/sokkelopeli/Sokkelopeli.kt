package xyz.nrth.mc.sokkelopeli

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Score
import org.bukkit.scoreboard.Scoreboard
import xyz.nrth.mc.sokkelopeli.commands.*

class Sokkelopeli : JavaPlugin() {
    companion object {
        lateinit var instance: Sokkelopeli
        val players = mutableListOf<Player>()
        lateinit var scoreboard: Scoreboard
        lateinit var objective: Objective
        var checkpoints = 0
        var running = false

        fun registerCommand(command: Command) {
            val pluginCommand = instance.getCommand(command.name) ?: return
            pluginCommand.setExecutor(command)
        }

        fun addPlayer(player: Player) {
            players.add(player)
            player.scoreboard = scoreboard
            playerScore(player).score = 0
        }

        fun removePlayer(player: Player) {
            players.remove(player)
            scoreboard.resetScores(player.name)
            player.scoreboard = Bukkit.getScoreboardManager().mainScoreboard
            player.gameMode = GameMode.CREATIVE

            if (players.isEmpty()) {
                reset()
            }
        }

        fun reset() {
            running = false
            players.forEach {
                Bukkit.getScheduler().scheduleSyncDelayedTask(instance, {
                  removePlayer(it)
                }, 0)
            }
        }

        fun playerScore(player: Player): Score {
            return objective.getScore(player.name)
        }
    }

    override fun onEnable() {
        // Plugin startup logic
        instance = this
        registerCommand(JoinCommand())
        registerCommand(LeaveCommand())
        registerCommand(StartCommand())
        registerCommand(ResetCommand())
        registerCommand(CheckpointCommand())
        registerCommand(WinnerCommand())
        scoreboard = Bukkit.getScoreboardManager().newScoreboard
        objective = scoreboard.registerNewObjective("Sokkelopeli", "dummy", "Sokkelopeli")
        objective.displaySlot = DisplaySlot.SIDEBAR
        server.pluginManager.registerEvents(SokkeloListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        objective.unregister()
    }
}