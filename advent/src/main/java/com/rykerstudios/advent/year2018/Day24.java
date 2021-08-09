package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rykerstudios.advent.Day;

public class Day24 extends Day<Integer> {

	protected static class Group implements Comparable<Group> {
		public String type = "infection";
		public final int id = nextId();
		public int units;
		public int hitPoints;
		public int initiative;
		public int attackDamage;
		public String attackType;
		public String[] immunities = new String[0];
		public String[] weaknesses = new String[0];
		public Group target;

		@Override
		public int compareTo(final Group that) {
			int result = that.power() - this.power();
			if (result == 0) {
				result = that.initiative - this.initiative;
			}
			return result;
		}

		public int power() {
			return attackDamage * units;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{type:").append(type);
			sb.append(",id:").append(id);
			sb.append(",units:").append(units);
			sb.append(",hp:").append(hitPoints);
			sb.append(",init:").append(initiative);
			sb.append(",atk:").append(attackDamage);
			sb.append(",atkType:").append(attackType);
			sb.append(",immunities:").append(Day.toString(immunities));
			sb.append(",weaknesses:").append(Day.toString(weaknesses));
			sb.append(",target:");
			if (target != null) {
				sb.append(target.type).append(" ").append(target.id);
			}
			return sb.toString();
		}

	}

	private static int lastId = 0;

	private static final Logger log = LoggerFactory.getLogger(Day24.class);

	public static int nextId() {
		lastId++;
		return lastId;
	}

	private List<Group> groups = new ArrayList<Group>();

	public Day24() {
		super(26914, 862);
	}

	protected void boost(final int amount) {
		for (final Group group : groups) {
			if ("Immune System".equals(group.type)) {
				group.attackDamage += amount;
			}
		}
	}

	// During the attacking phase, each group deals damage to the target it
	// selected, if any. Groups attack in decreasing order of initiative, regardless
	// of whether they are part of the infection or the immune system. (If a group
	// contains no units, it cannot attack.)
	//
	// The damage an attacking group deals to a defending group depends on the
	// attacking group's attack type and the defending group's immunities and
	// weaknesses. By default, an attacking group would deal damage equal to its
	// effective power to the defending group. However, if the defending group is
	// immune to the attacking group's attack type, the defending group instead
	// takes no damage; if the defending group is weak to the attacking group's
	// attack type, the defending group instead takes double damage.
	//
	// The defending group only loses whole units from damage; damage is always
	// dealt in such a way that it kills the most units possible, and any remaining
	// damage to a unit that does not immediately kill it is ignored. For example,
	// if a defending group contains 10 units with 10 hit points each and receives
	// 75 damage, it loses exactly 7 units and is left with 3 units at full health.
	protected void fight() {
		final Set<Group> deadUnits = new HashSet<Group>();
		Collections.sort(groups, new Comparator<Group>() {
			@Override
			public int compare(final Group o1, final Group o2) {
				return o2.initiative - o1.initiative;
			}
		});
		for (final Group attacker : groups) {
			if (attacker.units <= 0) {
				continue;
			}
			final Group defender = attacker.target;
			if (defender == null) {
				continue;
			}
			int damage = attacker.power();
			for (final String weakness : defender.weaknesses) {
				if (weakness.equals(attacker.attackType)) {
					damage *= 2;
					break;
				}
			}
			for (final String immunity : defender.immunities) {
				if (immunity.equals(attacker.attackType)) {
					damage = 0;
					break;
				}
			}
			if (damage == 0) {
				continue;
			}
			final int kills = damage / defender.hitPoints;
			log.debug(new StringBuilder().append(attacker.type).append(" group ").append(attacker.id)
					.append(" attacks defending group ").append(defender.id).append(", killing ").append(kills)
					.append(" units").toString());
			defender.units -= kills;
			if (defender.units <= 0) {
				deadUnits.add(defender);
				log.debug(new StringBuilder().append(defender.type).append(" group ").append(attacker.id)
						.append(" died.").toString());
			}
		}
		groups.removeAll(deadUnits);
	}

	protected void combat() {
		logGroups();
		while (conflictExists()) {
			findTargets();
			fight();
			logGroups();
		}
	}

	protected boolean conflictExists() {
		final Set<String> types = new HashSet<String>();
		for (final Group group : groups) {
			if (!types.contains(group.type)) {
				types.add(group.type);
			}
			if (types.size() > 1) {
				return true;
			}
		}
		return false;
	}

	// During the target selection phase, each group attempts to choose one target.
	// In decreasing order of effective power, groups choose their targets; in a
	// tie, the group with the higher initiative chooses first. The attacking group
	// chooses to target the group in the enemy army to which it would deal the most
	// damage (after accounting for weaknesses and immunities, but not accounting
	// for whether the defending group has enough units to actually receive all of
	// that damage).
	//
	// If an attacking group is considering two defending groups to which it would
	// deal equal damage, it chooses to target the defending group with the largest
	// effective power; if there is still a tie, it chooses the defending group with
	// the highest initiative. If it cannot deal any defending groups damage, it
	// does not choose a target. Defending groups can only be chosen as a target by
	// one attacking group.
	//
	// At the end of the target selection phase, each group has selected zero or one
	// groups to attack, and each group is being attacked by zero or one groups.
	protected void findTargets() {
		final Set<Group> selected = new HashSet<Group>();
		Collections.sort(groups);
		// Collections.reverse(groups);
		for (final Group attacker : groups) {
			log.debug(new StringBuilder().append(attacker.type).append(" group ").append(attacker.id)
					.append(" with effective power ").append(attacker.power()).append(" choosing target.").toString());
			attacker.target = null;
			int mostDamage = 0;
			int mostEffDamage = 0;
			Group mostDamageGroup = null;
			for (final Group defender : groups) {
				if (attacker.type.equals(defender.type)) {
					continue;
				}
				if (selected.contains(defender)) {
					continue;
				}
				int damage = attacker.power();
				int effDamage = damage;
				for (final String weakness : defender.weaknesses) {
					if (weakness.equals(attacker.attackType)) {
						effDamage *= 2;
						break;
					}
				}
				for (final String immunity : defender.immunities) {
					if (immunity.equals(attacker.attackType)) {
						effDamage = 0;
						break;
					}
				}
				log.debug(new StringBuilder().append(attacker.type).append(" group ").append(attacker.id)
						.append(" would deal defending group ").append(defender.id).append(" ").append(effDamage)
						.append(" damage").toString());
				if (effDamage == 0) {
					continue;
				}
				if (mostDamage == damage) {
					if (mostEffDamage < effDamage) {
						mostEffDamage = effDamage;
						mostDamageGroup = defender;
					}
				} else if (mostDamage < damage) {
					mostDamage = damage;
					mostEffDamage = effDamage;
					mostDamageGroup = defender;
				}
			}
			if (mostDamageGroup != null) {
				selected.add(mostDamageGroup);
				attacker.target = mostDamageGroup;
			}
		}
	}

	public int getTotalUnits() {
		int count = 0;
		for (final Group group : groups) {
			count += group.units;
		}
		return count;
	}

	protected void init(final String[] lines) {
		String groupType = "";
		for (final String line : lines) {
			if (line == null || line.trim().isEmpty()) {
				continue;
			}
			if (line.contains("Immune System")) {
				groupType = line.substring(0, line.indexOf(":"));
				continue;
			}
			if (line.contains("Infection")) {
				groupType = line.substring(0, line.indexOf(":"));
				continue;
			}
			final int ndx1 = line.indexOf(" ");
			final int ndx2 = line.indexOf("with", ndx1) + 5;
			final int ndx3 = line.indexOf(" ", ndx2 + 1);
			final int ndx4 = line.indexOf("(", ndx3);
			final int ndx5 = line.indexOf(")", ndx4);
			final int ndx6 = line.indexOf("does", ndx5) + 5;
			final int ndx7 = line.indexOf(" ", ndx6);
			final int ndx8 = line.indexOf("damage");
			final int ndx9 = line.indexOf("initiative", ndx8) + "initiative".length() + 1;
			final Group group = new Group();
			group.type = groupType;
			group.units = Integer.parseInt(line.substring(0, ndx1));
			group.hitPoints = Integer.parseInt(line.substring(ndx2, ndx3));
			if (ndx4 > -1) {
				final String s = line.substring(ndx4, ndx5);
				final int ndx11 = s.indexOf("immune to");
				if (ndx11 > -1) {
					final int ndx12 = ndx11 + "immune to ".length();
					final int ndx13 = s.indexOf(";", ndx12);
					if (ndx13 > ndx12) {
						group.immunities = s.substring(ndx12, ndx13).split(", ");
					} else {
						group.immunities = s.substring(ndx12).split(", ");
					}
				}
				final int ndx14 = s.indexOf("weak to");
				if (ndx14 > -1) {
					final int ndx15 = ndx14 + "weak to ".length();
					final int ndx16 = s.indexOf(";", ndx15);
					if (ndx16 > ndx15) {
						group.weaknesses = s.substring(ndx15, ndx16).split(", ");
					} else {
						group.weaknesses = s.substring(ndx15).split(", ");
					}
				}
			}
			// with an attack that does
			group.attackDamage = Integer.parseInt(line.substring(ndx6, ndx7));
			group.attackType = line.substring(ndx7 + 1, ndx8 - 1);
			group.initiative = Integer.parseInt(line.substring(ndx9));
			groups.add(group);
			log.info(line);
			log.info(group.toString());
		}
		Collections.sort(groups);
	}

	protected void logGroups() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nImmune Sytem:");
		for (final Group group : groups) {
			if (group.type.equals("Immune System")) {
				sb.append("\n\tGroup ").append(group.id);
				sb.append(" contains ").append(group.units).append(" units");
			}
		}
		sb.append("\nInfection:");
		for (final Group group : groups) {
			if (group.type.equals("Infection")) {
				sb.append("\n\tGroup ").append(group.id);
				sb.append(" contains ").append(group.units).append(" units");
			}
		}
		log.info(sb.toString());
	}

	// --- Day 24: Immune System Simulator 20XX ---
	//
	// After a weird buzzing noise, you appear back at the man's cottage. He seems
	// relieved to see his friend, but quickly notices that the little reindeer
	// caught some kind of cold while out exploring.
	//
	// The portly man explains that this reindeer's immune system isn't similar to
	// regular reindeer immune systems:
	//
	// The immune system and the infection each have an army made up of several
	// groups; each group consists of one or more identical units. The armies
	// repeatedly fight until only one army has units remaining.
	//
	// Units within a group all have the same hit points (amount of damage a unit
	// can take before it is destroyed), attack damage (the amount of damage each
	// unit deals), an attack type, an initiative (higher initiative units attack
	// first and win ties), and sometimes weaknesses or immunities. Here is an
	// example group:
	//
	// 18 units each with 729 hit points (weak to fire; immune to cold, slashing)
	// with an attack that does 8 radiation damage at initiative 10
	// Each group also has an effective power: the number of units in that group
	// multiplied by their attack damage. The above group has an effective power of
	// 18 * 8 = 144. Groups never have zero or negative units; instead, the group is
	// removed from combat.
	//
	// Each fight consists of two phases: target selection and attacking.
	//
	// During the target selection phase, each group attempts to choose one target.
	// In decreasing order of effective power, groups choose their targets; in a
	// tie, the group with the higher initiative chooses first. The attacking group
	// chooses to target the group in the enemy army to which it would deal the most
	// damage (after accounting for weaknesses and immunities, but not accounting
	// for whether the defending group has enough units to actually receive all of
	// that damage).
	//
	// If an attacking group is considering two defending groups to which it would
	// deal equal damage, it chooses to target the defending group with the largest
	// effective power; if there is still a tie, it chooses the defending group with
	// the highest initiative. If it cannot deal any defending groups damage, it
	// does not choose a target. Defending groups can only be chosen as a target by
	// one attacking group.
	//
	// At the end of the target selection phase, each group has selected zero or one
	// groups to attack, and each group is being attacked by zero or one groups.
	//
	// During the attacking phase, each group deals damage to the target it
	// selected, if any. Groups attack in decreasing order of initiative, regardless
	// of whether they are part of the infection or the immune system. (If a group
	// contains no units, it cannot attack.)
	//
	// The damage an attacking group deals to a defending group depends on the
	// attacking group's attack type and the defending group's immunities and
	// weaknesses. By default, an attacking group would deal damage equal to its
	// effective power to the defending group. However, if the defending group is
	// immune to the attacking group's attack type, the defending group instead
	// takes no damage; if the defending group is weak to the attacking group's
	// attack type, the defending group instead takes double damage.
	//
	// The defending group only loses whole units from damage; damage is always
	// dealt in such a way that it kills the most units possible, and any remaining
	// damage to a unit that does not immediately kill it is ignored. For example,
	// if a defending group contains 10 units with 10 hit points each and receives
	// 75 damage, it loses exactly 7 units and is left with 3 units at full health.
	//
	// After the fight is over, if both armies still contain units, a new fight
	// begins; combat only ends once one army has lost all of its units.
	//
	// For example, consider the following armies:
	//
	// Immune System:
	// 17 units each with 5390 hit points (weak to radiation, bludgeoning) with
	// an attack that does 4507 fire damage at initiative 2
	// 989 units each with 1274 hit points (immune to fire; weak to bludgeoning,
	// slashing) with an attack that does 25 slashing damage at initiative 3
	//
	// Infection:
	// 801 units each with 4706 hit points (weak to radiation) with an attack
	// that does 116 bludgeoning damage at initiative 1
	// 4485 units each with 2961 hit points (immune to radiation; weak to fire,
	// cold) with an attack that does 12 slashing damage at initiative 4
	// If these armies were to enter combat, the following fights, including details
	// during the target selection and attacking phases, would take place:
	//
	// Immune System:
	// Group 1 contains 17 units
	// Group 2 contains 989 units
	// Infection:
	// Group 1 contains 801 units
	// Group 2 contains 4485 units
	//
	// Infection group 1 would deal defending group 1 185832 damage
	// Infection group 1 would deal defending group 2 185832 damage
	// Infection group 2 would deal defending group 2 107640 damage
	// Immune System group 1 would deal defending group 1 76619 damage
	// Immune System group 1 would deal defending group 2 153238 damage
	// Immune System group 2 would deal defending group 1 24725 damage
	//
	// Infection group 2 attacks defending group 2, killing 84 units
	// Immune System group 2 attacks defending group 1, killing 4 units
	// Immune System group 1 attacks defending group 2, killing 51 units
	// Infection group 1 attacks defending group 1, killing 17 units
	// Immune System:
	// Group 2 contains 905 units
	// Infection:
	// Group 1 contains 797 units
	// Group 2 contains 4434 units
	//
	// Infection group 1 would deal defending group 2 184904 damage
	// Immune System group 2 would deal defending group 1 22625 damage
	// Immune System group 2 would deal defending group 2 22625 damage
	//
	// Immune System group 2 attacks defending group 1, killing 4 units
	// Infection group 1 attacks defending group 2, killing 144 units
	// Immune System:
	// Group 2 contains 761 units
	// Infection:
	// Group 1 contains 793 units
	// Group 2 contains 4434 units
	//
	// Infection group 1 would deal defending group 2 183976 damage
	// Immune System group 2 would deal defending group 1 19025 damage
	// Immune System group 2 would deal defending group 2 19025 damage
	//
	// Immune System group 2 attacks defending group 1, killing 4 units
	// Infection group 1 attacks defending group 2, killing 143 units
	// Immune System:
	// Group 2 contains 618 units
	// Infection:
	// Group 1 contains 789 units
	// Group 2 contains 4434 units
	//
	// Infection group 1 would deal defending group 2 183048 damage
	// Immune System group 2 would deal defending group 1 15450 damage
	// Immune System group 2 would deal defending group 2 15450 damage
	//
	// Immune System group 2 attacks defending group 1, killing 3 units
	// Infection group 1 attacks defending group 2, killing 143 units
	// Immune System:
	// Group 2 contains 475 units
	// Infection:
	// Group 1 contains 786 units
	// Group 2 contains 4434 units
	//
	// Infection group 1 would deal defending group 2 182352 damage
	// Immune System group 2 would deal defending group 1 11875 damage
	// Immune System group 2 would deal defending group 2 11875 damage
	//
	// Immune System group 2 attacks defending group 1, killing 2 units
	// Infection group 1 attacks defending group 2, killing 142 units
	// Immune System:
	// Group 2 contains 333 units
	// Infection:
	// Group 1 contains 784 units
	// Group 2 contains 4434 units
	//
	// Infection group 1 would deal defending group 2 181888 damage
	// Immune System group 2 would deal defending group 1 8325 damage
	// Immune System group 2 would deal defending group 2 8325 damage
	//
	// Immune System group 2 attacks defending group 1, killing 1 unit
	// Infection group 1 attacks defending group 2, killing 142 units
	// Immune System:
	// Group 2 contains 191 units
	// Infection:
	// Group 1 contains 783 units
	// Group 2 contains 4434 units
	//
	// Infection group 1 would deal defending group 2 181656 damage
	// Immune System group 2 would deal defending group 1 4775 damage
	// Immune System group 2 would deal defending group 2 4775 damage
	//
	// Immune System group 2 attacks defending group 1, killing 1 unit
	// Infection group 1 attacks defending group 2, killing 142 units
	// Immune System:
	// Group 2 contains 49 units
	// Infection:
	// Group 1 contains 782 units
	// Group 2 contains 4434 units
	//
	// Infection group 1 would deal defending group 2 181424 damage
	// Immune System group 2 would deal defending group 1 1225 damage
	// Immune System group 2 would deal defending group 2 1225 damage
	//
	// Immune System group 2 attacks defending group 1, killing 0 units
	// Infection group 1 attacks defending group 2, killing 49 units
	// Immune System:
	// No groups remain.
	// Infection:
	// Group 1 contains 782 units
	// Group 2 contains 4434 units
	// In the example above, the winning army ends up with 782 + 4434 = 5216 units.
	//
	// You scan the reindeer's condition (your puzzle input); the white-bearded man
	// looks nervous. As it stands now, how many units would the winning army have?

	@Override
	public Integer part1() {
		init(getLines());
		combat();
		return getTotalUnits();
	}

	// --- Part Two ---
	//
	// Things aren't looking good for the reindeer. The man asks whether more milk
	// and cookies would help you think.
	//
	// If only you could give the reindeer's immune system a boost, you might be
	// able to change the outcome of the combat.
	//
	// A boost is an integer increase in immune system units' attack damage. For
	// example, if you were to boost the above example's immune system's units by
	// 1570, the armies would instead look like this:
	//
	// Immune System:
	// 17 units each with 5390 hit points (weak to radiation, bludgeoning) with
	// an attack that does 6077 fire damage at initiative 2
	// 989 units each with 1274 hit points (immune to fire; weak to bludgeoning,
	// slashing) with an attack that does 1595 slashing damage at initiative 3
	//
	// Infection:
	// 801 units each with 4706 hit points (weak to radiation) with an attack
	// that does 116 bludgeoning damage at initiative 1
	// 4485 units each with 2961 hit points (immune to radiation; weak to fire,
	// cold) with an attack that does 12 slashing damage at initiative 4
	// With this boost, the combat proceeds differently:
	//
	// Immune System:
	// Group 2 contains 989 units
	// Group 1 contains 17 units
	// Infection:
	// Group 1 contains 801 units
	// Group 2 contains 4485 units
	//
	// Infection group 1 would deal defending group 2 185832 damage
	// Infection group 1 would deal defending group 1 185832 damage
	// Infection group 2 would deal defending group 1 53820 damage
	// Immune System group 2 would deal defending group 1 1577455 damage
	// Immune System group 2 would deal defending group 2 1577455 damage
	// Immune System group 1 would deal defending group 2 206618 damage
	//
	// Infection group 2 attacks defending group 1, killing 9 units
	// Immune System group 2 attacks defending group 1, killing 335 units
	// Immune System group 1 attacks defending group 2, killing 32 units
	// Infection group 1 attacks defending group 2, killing 84 units
	// Immune System:
	// Group 2 contains 905 units
	// Group 1 contains 8 units
	// Infection:
	// Group 1 contains 466 units
	// Group 2 contains 4453 units
	//
	// Infection group 1 would deal defending group 2 108112 damage
	// Infection group 1 would deal defending group 1 108112 damage
	// Infection group 2 would deal defending group 1 53436 damage
	// Immune System group 2 would deal defending group 1 1443475 damage
	// Immune System group 2 would deal defending group 2 1443475 damage
	// Immune System group 1 would deal defending group 2 97232 damage
	//
	// Infection group 2 attacks defending group 1, killing 8 units
	// Immune System group 2 attacks defending group 1, killing 306 units
	// Infection group 1 attacks defending group 2, killing 29 units
	// Immune System:
	// Group 2 contains 876 units
	// Infection:
	// Group 2 contains 4453 units
	// Group 1 contains 160 units
	//
	// Infection group 2 would deal defending group 2 106872 damage
	// Immune System group 2 would deal defending group 2 1397220 damage
	// Immune System group 2 would deal defending group 1 1397220 damage
	//
	// Infection group 2 attacks defending group 2, killing 83 units
	// Immune System group 2 attacks defending group 2, killing 427 units
	// After a few fights...
	//
	// Immune System:
	// Group 2 contains 64 units
	// Infection:
	// Group 2 contains 214 units
	// Group 1 contains 19 units
	//
	// Infection group 2 would deal defending group 2 5136 damage
	// Immune System group 2 would deal defending group 2 102080 damage
	// Immune System group 2 would deal defending group 1 102080 damage
	//
	// Infection group 2 attacks defending group 2, killing 4 units
	// Immune System group 2 attacks defending group 2, killing 32 units
	// Immune System:
	// Group 2 contains 60 units
	// Infection:
	// Group 1 contains 19 units
	// Group 2 contains 182 units
	//
	// Infection group 1 would deal defending group 2 4408 damage
	// Immune System group 2 would deal defending group 1 95700 damage
	// Immune System group 2 would deal defending group 2 95700 damage
	//
	// Immune System group 2 attacks defending group 1, killing 19 units
	// Immune System:
	// Group 2 contains 60 units
	// Infection:
	// Group 2 contains 182 units
	//
	// Infection group 2 would deal defending group 2 4368 damage
	// Immune System group 2 would deal defending group 2 95700 damage
	//
	// Infection group 2 attacks defending group 2, killing 3 units
	// Immune System group 2 attacks defending group 2, killing 30 units
	// After a few more fights...
	//
	// Immune System:
	// Group 2 contains 51 units
	// Infection:
	// Group 2 contains 40 units
	//
	// Infection group 2 would deal defending group 2 960 damage
	// Immune System group 2 would deal defending group 2 81345 damage
	//
	// Infection group 2 attacks defending group 2, killing 0 units
	// Immune System group 2 attacks defending group 2, killing 27 units
	// Immune System:
	// Group 2 contains 51 units
	// Infection:
	// Group 2 contains 13 units
	//
	// Infection group 2 would deal defending group 2 312 damage
	// Immune System group 2 would deal defending group 2 81345 damage
	//
	// Infection group 2 attacks defending group 2, killing 0 units
	// Immune System group 2 attacks defending group 2, killing 13 units
	// Immune System:
	// Group 2 contains 51 units
	// Infection:
	// No groups remain.
	// This boost would allow the immune system's armies to win! It would be left
	// with 51 units.
	//
	// You don't even know how you could boost the reindeer's immune system or what
	// effect it might have, so you need to be cautious and find the smallest boost
	// that would allow the immune system to win.
	//
	// How many units does the immune system have left after getting the smallest
	// boost it needs to win?

	@Override
	public Integer part2() {
		for (int i = 48; i <= 48; i++) {
			init(getLines());
			boost(i);
			combat();
			if ("Immune System".equals(groups.get(0).type)) {
				break;
			}
		}
		return getTotalUnits();
	}

}
