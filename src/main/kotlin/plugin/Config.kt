// This is free and unencumbered software released into the public domain.
// Author: Sekalol15
package plugin

import java.io.File
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration

object Config {
    private lateinit var config: FileConfiguration
    private lateinit var file: File

    fun load() {
        file = File(BuffRatesOG.plugin.dataFolder, "config.yml")
        if (!file.exists()) {
            BuffRatesOG.plugin.saveDefaultConfig()
        }

        config = YamlConfiguration.loadConfiguration(file)

        this.save()
    }

    private fun save() {
        config.save(file)
    }

    fun getSlimeChanceEnabled(): Boolean {
        return config.get("slimeChanceEnabled") as Boolean
    }

    fun getSlimeChance(): Double {
        return config.get("slimeChance") as Double
    }

    fun getRaiderQuantity(): Int {
        return config.get("raiderQuantity") as Int
    }

    fun getZombieIronEnabled(): Boolean {
        return config.get("zombieIronEnabled") as Boolean
    }

    fun getZombieIron(): Double {
        return config.get("zombieIron") as Double
    }

    fun getWardenChanceEnabled(): Boolean {
        return config.get("wardenChanceEnabled") as Boolean
    }

    fun getWardenChance(): Double {
        return config.get("wardenChance") as Double
    }
}
