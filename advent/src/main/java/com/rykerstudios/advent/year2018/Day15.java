package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rykerstudios.advent.Day;

public class Day15 extends Day<Integer> {

	protected class Enemy {
		final int id;
		final Unit unit;
		final int x;
		final int y;
		public Enemy(final Unit unit, final int x, final int y) {
			this.id = nextId();
			this.unit = unit;
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof Enemy) {
				if (this.id == ((Enemy) obj).id) {
					return true;
				}
			}
			return false;
		}
		@Override
		public int hashCode() {
			return Integer.valueOf(id).hashCode();
		}
		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id:").append(id);
			sb.append(",unit:").append(unit);
			sb.append(",x:").append(x);
			sb.append(",y:").append(y);
			sb.append("}");
			return sb.toString();
		}
	}

	protected class Space {
		final int id;
		final char type; // # or .
		Unit unit = null;
		Space(final char type) {
			this.id = nextId();
			this.type = type;
		}
		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof Space) {
				if (this.id == ((Space) obj).id) {
					return true;
				}
			}
			return false;
		}
		@Override
		public int hashCode() {
			return Integer.valueOf(id).hashCode();
		}
		public void setUnit(final Unit unit) {
			this.unit = unit;
		}
		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id:").append(id);
			sb.append(",type:").append(type);
			sb.append(",unit:").append(unit);
			sb.append("}");
			return sb.toString();
		}
	}

	protected class Unit {
		final int id;
		final char type; // E or G
		final int ap = 3; // attack power
		int hp = 200;
		boolean alive = true;
		public Unit(final char type) {
			this.id = nextId();
			this.type = type;
		}
		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof Unit) {
				if (this.id == ((Unit) obj).id) {
					return true;
				}
			}
			return false;
		}
		@Override
		public int hashCode() {
			return Integer.valueOf(id).hashCode();
		}
		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id:").append(id);
			sb.append(",type:").append(type);
			sb.append(",ap:").append(ap);
			sb.append(",hp:").append(hp);
			sb.append(",alive:").append(alive);
			sb.append("}");
			return sb.toString();
		}
	}

	protected static final Logger log = LoggerFactory.getLogger(Day15.class);

	private static int lastId = 0;

	protected static boolean isSpace(final char c) {
		return c == '#' || c == '.';
	}

	protected static boolean isUnit(final char c) {
		return c == 'E' || c == 'G';
	}

	private static int nextId() {
		lastId++;
		return lastId;
	}

	private int maxX = 0;
	private int maxY = 0;
	private int rounds = 0;
	private int units = 0;
	private Space[][] spaces = null;
	private final Set<Unit> movedUnits = new HashSet<>();

	public Day15() {
		super(0, 0);
	}

	private void addInRangeSpace(final List<int[]> locations, final int x, final int y) {
		if (spaces[x][y].type == '.') {
			locations.add(new int[] { x, y });
		}
	}

	private boolean attack(final Unit unit, final Unit enemy) {
		enemy.hp -= unit.ap;
		if (enemy.hp <= 0) {
			enemy.alive = false;
		}
		return !enemy.alive;
	}

	protected int combat() {
		rounds = 0;
		logUnits();
		while (combatRound()) {
			// continue combat
		}
		logSpaces();
		return getRounds() * getTotalHitPoints();
	}

	protected boolean combatRound() {
		logSpaces();
		rounds++;
		log.info("Round: " + rounds);
		movedUnits.clear();
		final List<Enemy> enemies = new ArrayList<Enemy>();
		for (int y = 1; y < maxY; y++) {
			for (int x = 1; x < maxX; x++) {
				final Space oldSpace = spaces[x][y];
				if (oldSpace == null || oldSpace.unit == null) {
					continue;
				}
				combatTurn(x, y, oldSpace, enemies);
				if (enemies.isEmpty()) {
					return false; // stop combat
				}
			}
		}
		return true; // continue combat
	}

	protected void combatTurn(final int x, final int y, final Space oldSpace, final List<Enemy> enemies) {
		final Unit unit = oldSpace.unit;
		if (movedUnits.contains(unit)) {
			return;
		}
		movedUnits.add(unit);
		enemies.clear();
		enemies.addAll(findAllEnemies(unit));
		if (enemies.isEmpty()) {
			return;
		}
		final List<int[]> adjacentToEnemyList = getAdjacentTo(enemies);
		final List<int[]> reachableSpaces = getReachable(x, y, adjacentToEnemyList);
		if (reachableSpaces.isEmpty()) {
			return;
		}
		final int[] nearest = getNearestReachable(x, y, reachableSpaces);
		int distance = getDistance(x, y, nearest[0], nearest[1]);
		if (distance > 0) {
			final Space newSpace = move(x, y, nearest);
			if (newSpace != null) {
				newSpace.unit = unit;
				oldSpace.unit = null;
			}
		}
		final Enemy enemy = getNearestEnemy(x, y, enemies);
		distance = getDistance(x, y, enemy.x, enemy.y);
		if (distance == 1) {
			if (attack(unit, enemy.unit)) {
				enemies.remove(enemy);
				spaces[enemy.x][enemy.y].unit = null;
				units--;
			}
		}
	}

	private List<Enemy> findAllEnemies(final Unit unit) {
		final List<Enemy> enemies = new ArrayList<Enemy>();
		for (int y = 1; y < maxY; y++) {
			for (int x = 1; x < maxX; x++) {
				final Space space = spaces[x][y];
				if (space == null || space.unit == null) {
					continue;
				}
				final Unit other = space.unit;
				if (isEnemy(unit, other)) {
					enemies.add(new Enemy(other, x, y));
				}
			}
		}
		return enemies;
	}

	protected int getDistance(final int x, final int y, final int x2, final int y2) {
		return Math.abs(x - x2) + Math.abs(y - y2);
	}

	private List<int[]> getAdjacentTo(final List<Enemy> enemies) {
		final List<int[]> locations = new ArrayList<int[]>();
		for (final Enemy enemy : enemies) {
			addInRangeSpace(locations, enemy.x + 1, enemy.y);
			addInRangeSpace(locations, enemy.x - 1, enemy.y);
			addInRangeSpace(locations, enemy.x, enemy.y - 1);
			addInRangeSpace(locations, enemy.x, enemy.y + 1);
		}
		return locations;
	}

	private Enemy getNearestEnemy(final int x, final int y, final List<Enemy> enemies) {
		Enemy closestEnemy = null;
		int closestDistance = Integer.MAX_VALUE;
		for (final Enemy enemy : enemies) {
			final int distance = getDistance(x, y, enemy.x, enemy.y);
			if (closestDistance > distance) {
				closestDistance = distance;
				closestEnemy = enemy;
				// } else if (closestDistance == distance) {
				// if (closestEnemy.unit.hp > enemy.unit.hp) {
				// closestEnemy = enemy;
				// }
			}
		}
		return closestEnemy;
	}

	private int[] getNearestReachable(final int x, final int y, final List<int[]> reachable) {
		int closestDistance = Integer.MAX_VALUE;
		int[] closestLocation = null;
		for (final int[] target : reachable) {
			final int distance = getDistance(x, y, target[0], target[1]);
			if (closestDistance > distance) {
				closestDistance = distance;
				closestLocation = target;
			}
		}
		return closestLocation;
	}

	private List<int[]> getReachable(final int x, final int y, final List<int[]> inRange) {
		final List<int[]> locations = new ArrayList<int[]>();
		for (final int[] location : inRange) {
			if (isReachable(x, y, location)) {
				locations.add(location);
			}
		}
		return locations;
	}

	protected int getRounds() {
		return rounds;
	}

	protected int getTotalHitPoints() {
		int hp = 0;
		for (int y = 1; y < maxY; y++) {
			for (int x = 1; x < maxX; x++) {
				final Space space = spaces[x][y];
				if (space != null && space.unit != null) {
					hp += space.unit.hp;
				}
			}
		}
		return hp;
	}

	public void init(final String[] lines) {
		maxY = lines.length;
		for (final String line : lines) {
			if (maxX < line.length()) {
				maxX = line.length();
			}
		}
		log.info("Combat area dimensions: [x,y] = [" + maxX + "," + maxY + "]");
		spaces = new Space[maxX][maxY];
		units = initSpaces(lines);
	}

	private int initSpaces(final String[] lines) {
		int units = 0;
		int y = 0;
		for (final String line : lines) {
			final char a[] = line.toCharArray();
			for (int x = 0; x < a.length; x++) {
				final char c = a[x];
				if (isUnit(c)) {
					spaces[x][y] = new Space('.');
					spaces[x][y].setUnit(new Unit(c));
					units++;
				} else if (isSpace(c)) {
					spaces[x][y] = new Space(c);
				} else {
					spaces[x][y] = new Space('.');
				}
			}
			y++;
		}
		return units;
	}

	protected boolean isEnemy(final Unit unit, final Unit other) {
		return unit.type != other.type;
	}

	private boolean isReachable(final int x, final int y,
			final int[] location) {
		// consider all reachable in a straight path for now.
		return true;
	}

	protected void logSpaces() {
		for (int y = 0; y < maxY; y++) {
			final StringBuffer sb = new StringBuffer();
			for (int x = 0; x < maxX; x++) {
				if (spaces[x][y] == null) {
					sb.append(' ');
				} else if (spaces[x][y].unit != null) {
					sb.append(spaces[x][y].unit.type);
				} else {
					sb.append(spaces[x][y].type);
				}
			}
			sb.append(' ');
			for (int x = 0; x < maxX; x++) {
				if (spaces[x][y] == null) {
					continue;
				}
				final Space space = spaces[x][y];
				final Unit unit = space.unit;
				if (unit != null) {
					sb.append(unit.type).append("(").append(unit.hp).append("), ");
				}
			}
			log.info(sb.toString());
		}
	}

	protected void logUnits() {
		final StringBuffer sb = new StringBuffer();
		sb.append("round=").append(rounds).append(";units=").append(units).append(" ");
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				if (spaces[x][y] == null) {
					continue;
				} else if (spaces[x][y].unit != null) {
					sb.append("\n\tunit=").append(spaces[x][y].unit);
				}
			}
		}
		log.info(sb.toString());
	}

	private Space move(final int x, final int y, final int[] nearest) {
		Space newSpace = null;
		final int diffX = x - nearest[0];
		final int diffY = y - nearest[1];
		if (diffX > diffY) {
			if (diffX > 0) {
				newSpace = spaces[x - 1][y];
			} else {
				newSpace = spaces[x + 1][y];
			}
			if (newSpace.type == '#') {
				newSpace = null;
			}
		} else {
			if (diffY > 0) {
				newSpace = spaces[x][y - 1];
			} else {
				newSpace = spaces[x][y + 1];
			}
			if (newSpace.type == '#') {
				newSpace = null;
			}
		}
		return newSpace;
	}

	// --- Day 15: Beverage Bandits ---
	//
	// Having perfected their hot chocolate, the Elves have a new problem: the
	// Goblins that live in these caves will do anything to steal it. Looks like
	// they're here for a fight.
	//
	// You scan the area, generating a map of the walls (#), open cavern (.), and
	// starting position of every Goblin (G) and Elf (E) (your puzzle input).
	//
	// Combat proceeds in rounds; in each round, each unit that is still alive takes
	// a turn, resolving all of its actions before the next unit's turn begins. On
	// each unit's turn, it tries to move into range of an enemy (if it isn't
	// already) and then attack (if it is in range).
	//
	// All units are very disciplined and always follow very strict combat rules.
	// Units never move or attack diagonally, as doing so would be dishonorable.
	// When multiple choices are equally valid, ties are broken in reading order:
	// top-to-bottom, then left-to-right. For instance, the order in which units
	// take their turns within a round is the reading order of their starting
	// positions in that round, regardless of the type of unit or whether other
	// units have moved after the round started. For example:
	//
	// These units would take their turns in this order:
	// ####### #######
	// #.G.E.# #.1.2.#
	// #E.G.E# #3.4.5#
	// #.G.E.# #.6.7.#
	// ####### #######
	//
	// Each unit begins its turn by identifying all possible targets (enemy units).
	// If no targets remain, combat ends.
	//
	// Then, the unit identifies all of the open squares (.) that are in range of
	// each target; these are the squares which are adjacent (immediately up, down,
	// left, or right) to any target and which aren't already occupied by a wall or
	// another unit. Alternatively, the unit might already be in range of a target.
	// If the unit is not already in range of a target, and there are no open
	// squares which are in range of a target, the unit ends its turn.
	//
	// If the unit is already in range of a target, it does not move, but continues
	// its turn with an attack. Otherwise, since it is not in range of a target, it
	// moves.
	//
	// To move, the unit first considers the squares that are in range and
	// determines which of those squares it could reach in the fewest steps. A step
	// is a single movement to any adjacent (immediately up, down, left, or right)
	// open (.) square. Units cannot move into walls or other units. The unit does
	// this while considering the current positions of units and does not do any
	// prediction about where units will be later. If the unit cannot reach (find an
	// open path to) any of the squares that are in range, it ends its turn. If
	// multiple squares are in range and tied for being reachable in the fewest
	// steps, the step which is first in reading order is chosen. For example:
	//
	// Targets: In range: Reachable: Nearest: Chosen:
	// ####### ####### ####### ####### #######
	// #E..G.# #E.?G?# #E.@G.# #E.!G.# #E.+G.#
	// #...#.#>#.?.#?#>#.@.#.#>#.!.#.#>#...#.#
	// #.G.#G# #?G?#G# #@G@#G# #!G.#G# #.G.#G#
	// ####### ####### ####### ####### #######
	//
	// In the above scenario, the Elf has three targets (the three Goblins):
	//
	// Each of the Goblins has open, adjacent squares which are in range (marked
	// with a ? on the map).
	// Of those squares, four are reachable (marked @); the other two (on the right)
	// would require moving through a wall or unit to reach.
	// Three of these reachable squares are nearest, requiring the fewest steps
	// (only 2) to reach (marked !).
	// Of those, the square which is first in reading order is chosen (+).
	// The unit then takes a single step toward the chosen square along the shortest
	// path to that square. If multiple steps would put the unit equally closer to
	// its destination, the unit chooses the step which is first in reading order.
	// (This requires knowing when there is more than one shortest path so that you
	// can consider the first step of each such path.) For example:
	//
	// In range: Nearest: Chosen: Distance: Step:
	// ####### ####### ####### ####### #######
	// #.E...# #.E...# #.E...# #4E212# #..E..#
	// #...?.#>#...!.#>#...+.#>#32101#>#.....#
	// #..?G?# #..!G.# #...G.# #432G2# #...G.#
	// ####### ####### ####### ####### #######
	//
	// The Elf sees three squares in range of a target (?), two of which are nearest
	// (!), and so the first in reading order is chosen (+). Under "Distance", each
	// open square is marked with its distance from the destination square; the two
	// squares to which the Elf could move on this turn (down and to the right) are
	// both equally good moves and would leave the Elf 2 steps from being in range
	// of the Goblin. Because the step which is first in reading order is chosen,
	// the Elf moves right one square.
	//
	// Here's a larger example of movement:
	//
	// Initially:
	// #########
	// #G..G..G#
	// #.......#
	// #.......#
	// #G..E..G#
	// #.......#
	// #.......#
	// #G..G..G#
	// #########
	//
	// After 1 round:
	// #########
	// #.G...G.#
	// #...G...#
	// #...E..G#
	// #.G.....#
	// #.......#
	// #G..G..G#
	// #.......#
	// #########
	//
	// After 2 rounds:
	// #########
	// #..G.G..#
	// #...G...#
	// #.G.E.G.#
	// #.......#
	// #G..G..G#
	// #.......#
	// #.......#
	// #########
	//
	// After 3 rounds:
	// #########
	// #.......#
	// #..GGG..#
	// #..GEG..#
	// #G..G...#
	// #......G#
	// #.......#
	// #.......#
	// #########
	// Once the Goblins and Elf reach the positions above, they all are either in
	// range of a target or cannot find any square in range of a target, and so none
	// of the units can move until a unit dies.
	//
	// After moving (or if the unit began its turn in range of a target), the unit
	// attacks.
	//
	// To attack, the unit first determines all of the targets that are in range of
	// it by being immediately adjacent to it. If there are no such targets, the
	// unit ends its turn. Otherwise, the adjacent target with the fewest hit points
	// is selected; in a tie, the adjacent target with the fewest hit points which
	// is first in reading order is selected.
	//
	// The unit deals damage equal to its attack power to the selected target,
	// reducing its hit points by that amount. If this reduces its hit points to 0
	// or fewer, the selected target dies: its square becomes . and it takes no
	// further turns.
	//
	// Each unit, either Goblin or Elf, has 3 attack power and starts with 200 hit
	// points.
	//
	// For example, suppose the only Elf is about to attack:
	//
	// HP: HP:
	// G.... 9 G.... 9
	// ..G.. 4 ..G.. 4
	// ..EG. 2>..E..
	// ..G.. 2 ..G.. 2
	// ...G. 1 ...G. 1
	// The "HP" column shows the hit points of the Goblin to the left in the
	// corresponding row. The Elf is in range of three targets: the Goblin above it
	// (with 4 hit points), the Goblin to its right (with 2 hit points), and the
	// Goblin below it (also with 2 hit points). Because three targets are in range,
	// the ones with the lowest hit points are selected: the two Goblins with 2 hit
	// points each (one to the right of the Elf and one below the Elf). Of those,
	// the Goblin first in reading order (the one to the right of the Elf) is
	// selected. The selected Goblin's hit points (2) are reduced by the Elf's
	// attack power (3), reducing its hit points to -1, killing it.
	//
	// After attacking, the unit's turn ends. Regardless of how the unit's turn
	// ends, the next unit in the round takes its turn. If all units have taken
	// turns in this round, the round ends, and a new round begins.
	//
	// The Elves look quite outnumbered. You need to determine the outcome of the
	// battle: the number of full rounds that were completed (not counting the round
	// in which combat ends) multiplied by the sum of the hit points of all
	// remaining units at the moment combat ends. (Combat only ends when a unit
	// finds no targets during its turn.)
	//
	// Below is an entire sample combat. Next to each map, each row's units' hit
	// points are listed from left to right.
	//
	// Initially:
	// #######
	// #.G...# G(200)
	// #...EG# E(200), G(200)
	// #.#.#G# G(200)
	// #..G#E# G(200), E(200)
	// #.....#
	// #######
	//
	// After 1 round:
	// #######
	// #..G..# G(200)
	// #...EG# E(197), G(197)
	// #.#G#G# G(200), G(197)
	// #...#E# E(197)
	// #.....#
	// #######
	//
	// After 2 rounds:
	// #######
	// #...G.# G(200)
	// #..GEG# G(200), E(188), G(194)
	// #.#.#G# G(194)
	// #...#E# E(194)
	// #.....#
	// #######
	//
	// Combat ensues; eventually, the top Elf dies:
	//
	// After 23 rounds:
	// #######
	// #...G.# G(200)
	// #..G.G# G(200), G(131)
	// #.#.#G# G(131)
	// #...#E# E(131)
	// #.....#
	// #######
	//
	// After 24 rounds:
	// #######
	// #..G..# G(200)
	// #...G.# G(131)
	// #.#G#G# G(200), G(128)
	// #...#E# E(128)
	// #.....#
	// #######
	//
	// After 25 rounds:
	// #######
	// #.G...# G(200)
	// #..G..# G(131)
	// #.#.#G# G(125)
	// #..G#E# G(200), E(125)
	// #.....#
	// #######
	//
	// After 26 rounds:
	// #######
	// #G....# G(200)
	// #.G...# G(131)
	// #.#.#G# G(122)
	// #...#E# E(122)
	// #..G..# G(200)
	// #######
	//
	// After 27 rounds:
	// #######
	// #G....# G(200)
	// #.G...# G(131)
	// #.#.#G# G(119)
	// #...#E# E(119)
	// #...G.# G(200)
	// #######
	//
	// After 28 rounds:
	// #######
	// #G....# G(200)
	// #.G...# G(131)
	// #.#.#G# G(116)
	// #...#E# E(113)
	// #....G# G(200)
	// #######
	//
	// More combat ensues; eventually, the bottom Elf dies:
	//
	// After 47 rounds:
	// #######
	// #G....# G(200)
	// #.G...# G(131)
	// #.#.#G# G(59)
	// #...#.#
	// #....G# G(200)
	// #######
	// Before the 48th round can finish, the top-left Goblin finds that there are no
	// targets remaining, and so combat ends. So, the number of full rounds that
	// were completed is 47, and the sum of the hit points of all remaining units is
	// 200+131+59+200 = 590. From these, the outcome of the battle is 47 * 590 =
	// 27730.
	//
	// Here are a few example summarized combats:
	//
	// ####### #######
	// #G..#E# #...#E# E(200)
	// #E#E.E# #E#...# E(197)
	// #G.##.#>#.E##.# E(185)
	// #...#E# #E..#E# E(200), E(200)
	// #...E.# #.....#
	// ####### #######
	//
	// Combat ends after 37 full rounds
	// Elves win with 982 total hit points left
	// Outcome: 37 * 982 = 36334
	// ####### #######
	// #E..EG# #.E.E.# E(164), E(197)
	// #.#G.E# #.#E..# E(200)
	// #E.##E#>#E.##.# E(98)
	// #G..#.# #.E.#.# E(200)
	// #..E#.# #...#.#
	// ####### #######
	//
	// Combat ends after 46 full rounds
	// Elves win with 859 total hit points left
	// Outcome: 46 * 859 = 39514
	// ####### #######
	// #E.G#.# #G.G#.# G(200), G(98)
	// #.#G..# #.#G..# G(200)
	// #G.#.G#>#..#..#
	// #G..#.# #...#G# G(95)
	// #...E.# #...G.# G(200)
	// ####### #######
	//
	// Combat ends after 35 full rounds
	// Goblins win with 793 total hit points left
	// Outcome: 35 * 793 = 27755
	// ####### #######
	// #.E...# #.....#
	// #.#..G# #.#G..# G(200)
	// #.###.#>#.###.#
	// #E#G#G# #.#.#.#
	// #...#G# #G.G#G# G(98), G(38), G(200)
	// ####### #######
	//
	// Combat ends after 54 full rounds
	// Goblins win with 536 total hit points left
	// Outcome: 54 * 536 = 28944
	// ######### #########
	// #G......# #.G.....# G(137)
	// #.E.#...# #G.G#...# G(200), G(200)
	// #..##..G# #.G##...# G(200)
	// #...##..#>#...##..#
	// #...#...# #.G.#...# G(200)
	// #.G...G.# #.......#
	// #.....G.# #.......#
	// ######### #########
	//
	// Combat ends after 20 full rounds
	// Goblins win with 937 total hit points left
	// Outcome: 20 * 937 = 18740
	//
	// What is the outcome of the combat described in your puzzle input?

	@Override
	public Integer part1() {
		init(getLines());
		return combat();
	}

	@Override
	public Integer part2() {
		init(getLines());
		return combat();
	}

}
