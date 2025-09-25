// This is free and unencumbered software released into the public domain.
// Author: Sekalol15
package plugin

import kotlin.random.Random
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack

class Listeners : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onCreatureSpawn(event: CreatureSpawnEvent) {
        val slimeChanceEnabled = Config.getSlimeChanceEnabled()
        val slimeChance = Config.getSlimeChance()
        val raiderQuantity = Config.getRaiderQuantity()

        val reason = event.spawnReason
        val world = event.entity.world
        val pos = event.location
        val type = event.entityType

        if (
            type == EntityType.SLIME &&
                slimeChanceEnabled &&
                (reason == CreatureSpawnEvent.SpawnReason.NATURAL || reason == CreatureSpawnEvent.SpawnReason.CHUNK_GEN)
        ) {
            if (Random.nextDouble() < slimeChance) {
                world.spawnEntity(pos, EntityType.SLIME, CreatureSpawnEvent.SpawnReason.CUSTOM)
            }
        }

        if (reason == CreatureSpawnEvent.SpawnReason.RAID && raiderQuantity > 0) {
            repeat(raiderQuantity) { world.spawnEntity(pos, type, CreatureSpawnEvent.SpawnReason.CUSTOM) }
        }
    }

    @EventHandler(ignoreCancelled = true)
    fun onEntityDeath(event: EntityDeathEvent) {
        val zombieIronEnabled = Config.getZombieIronEnabled()
        val zombieIron = Config.getZombieIron()
        if (zombieIronEnabled && event.entityType == EntityType.ZOMBIE && Random.nextDouble() <= zombieIron) {
            event.entity.world.dropItemNaturally(event.entity.location, ItemStack(Material.IRON_INGOT))
        }
    }
}
