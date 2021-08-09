package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.rykerstudios.advent.Day;

public class Day17 extends Day<Integer> {

	public Day17() {
		super(207, 2308);
	}

	/**
	 * --- Day 17: Conway Cubes ---
	 * 
	 * As your flight slowly drifts through the sky, the Elves at the Mythical
	 * Information Bureau at the North Pole contact you. They'd like some help
	 * debugging a malfunctioning experimental energy source aboard one of their
	 * super-secret imaging satellites.
	 * 
	 * The experimental energy source is based on cutting-edge technology: a set of
	 * Conway Cubes contained in a pocket dimension! When you hear it's having
	 * problems, you can't help but agree to take a look.
	 * 
	 * The pocket dimension contains an infinite 3-dimensional grid. At every
	 * integer 3-dimensional coordinate (x,y,z), there exists a single cube which is
	 * either active or inactive.
	 * 
	 * In the initial state of the pocket dimension, almost all cubes start
	 * inactive. The only exception to this is a small flat region of cubes (your
	 * puzzle input); the cubes in this region start in the specified active (#) or
	 * inactive (.) state.
	 * 
	 * The energy source then proceeds to boot up by executing six cycles.
	 * 
	 * Each cube only ever considers its neighbors: any of the 26 other cubes where
	 * any of their coordinates differ by at most 1. For example, given the cube at
	 * x=1,y=2,z=3, its neighbors include the cube at x=2,y=2,z=2, the cube at
	 * x=0,y=2,z=3, and so on.
	 * 
	 * During a cycle, all cubes simultaneously change their state according to the
	 * following rules:
	 * 
	 * If a cube is active and exactly 2 or 3 of its neighbors are also active, the
	 * cube remains active. Otherwise, the cube becomes inactive. If a cube is
	 * inactive but exactly 3 of its neighbors are active, the cube becomes active.
	 * Otherwise, the cube remains inactive. The engineers responsible for this
	 * experimental energy source would like you to simulate the pocket dimension
	 * and determine what the configuration of cubes should be at the end of the
	 * six-cycle boot process.
	 * 
	 * For example, consider the following initial state:
	 * 
	 * .#. <br/>
	 * ..# <br/>
	 * ### <br/>
	 * 
	 * Even though the pocket dimension is 3-dimensional, this initial state
	 * represents a small 2-dimensional slice of it. (In particular, this initial
	 * state defines a 3x3x1 region of the 3-dimensional space.)
	 * 
	 * Simulating a few cycles from this initial state produces the following
	 * configurations, where the result of each cycle is shown layer-by-layer at
	 * each given z coordinate (and the frame of view follows the active cells in
	 * each cycle):
	 * 
	 * Before any cycles:
	 * 
	 * z=0 <br/>
	 * .#. <br/>
	 * ..# <br/>
	 * ### <br/>
	 * 
	 * 
	 * After 1 cycle:
	 * 
	 * z=-1 <br/>
	 * #.. <br/>
	 * ..# <br/>
	 * .#. <br/>
	 * 
	 * z=0 <br/>
	 * #.# <br/>
	 * .## <br/>
	 * .#. <br/>
	 * 
	 * z=1 <br/>
	 * #.. <br/>
	 * ..# <br/>
	 * .#. <br/>
	 * 
	 * 
	 * After 2 cycles:
	 * 
	 * z=-2 <br/>
	 * ..... <br/>
	 * ..... <br/>
	 * ..#.. <br/>
	 * ..... <br/>
	 * ..... <br/>
	 * 
	 * z=-1 <br/>
	 * ..#.. <br/>
	 * .#..# <br/>
	 * ....# <br/>
	 * .#... <br/>
	 * ..... <br/>
	 * 
	 * z=0 <br/>
	 * ##... <br/>
	 * ##... <br/>
	 * #.... <br/>
	 * ....# <br/>
	 * .###. <br/>
	 * 
	 * z=1 <br/>
	 * ..#.. <br/>
	 * .#..# <br/>
	 * ....# <br/>
	 * .#... <br/>
	 * ..... <br/>
	 * 
	 * z=2 <br/>
	 * ..... <br/>
	 * ..... <br/>
	 * ..#.. <br/>
	 * ..... <br/>
	 * ..... <br/>
	 * 
	 * 
	 * After 3 cycles:
	 * 
	 * z=-2 <br/>
	 * ....... <br/>
	 * ....... <br/>
	 * ..##... <br/>
	 * ..###.. <br/>
	 * ....... <br/>
	 * ....... <br/>
	 * ....... <br/>
	 * 
	 * z=-1 <br/>
	 * ..#.... <br/>
	 * ...#... <br/>
	 * #...... <br/>
	 * .....## <br/>
	 * .#...#. <br/>
	 * ..#.#.. <br/>
	 * ...#... <br/>
	 * 
	 * z=0 <br/>
	 * ...#... <br/>
	 * ....... <br/>
	 * #...... <br/>
	 * ....... <br/>
	 * .....## <br/>
	 * .##.#.. <br/>
	 * ...#... <br/>
	 * 
	 * z=1 <br/>
	 * ..#.... <br/>
	 * ...#... <br/>
	 * #...... <br/>
	 * .....## <br/>
	 * .#...#. <br/>
	 * ..#.#.. <br/>
	 * ...#... <br/>
	 * 
	 * z=2 <br/>
	 * ....... <br/>
	 * ....... <br/>
	 * ..##... <br/>
	 * ..###.. <br/>
	 * ....... <br/>
	 * ....... <br/>
	 * ....... <br/>
	 * 
	 * After the full six-cycle boot process completes, 112 cubes are left in the
	 * active state.
	 * 
	 * Starting with your given initial configuration, simulate six cycles. How many
	 * cubes are left in the active state after the sixth cycle?
	 */
	@Override
	public Integer part1() {
		final ConwayCubes3D cubes = new ConwayCubes3D(getLines());
		cubes.cycle(6);
		return cubes.getActive();
//		final ConwayCubesD cubes = ConwayCubesD.from(3, getLines());
//		cubes.cycle(6);
//		System.out.println("Part 1: " + cubes.getActiveCubeCount());
//		return cubes.getActiveCubeCount();
	}

	/**
	 * --- Part Two ---
	 * 
	 * For some reason, your simulated results don't match what the experimental
	 * energy source engineers expected. Apparently, the pocket dimension actually
	 * has four spatial dimensions, not three.
	 * 
	 * The pocket dimension contains an infinite 4-dimensional grid. At every
	 * integer 4-dimensional coordinate (x,y,z,w), there exists a single cube
	 * (really, a hypercube) which is still either active or inactive.
	 * 
	 * Each cube only ever considers its neighbors: any of the 80 other cubes where
	 * any of their coordinates differ by at most 1. For example, given the cube at
	 * x=1,y=2,z=3,w=4, its neighbors include the cube at x=2,y=2,z=3,w=3, the cube
	 * at x=0,y=2,z=3,w=4, and so on.
	 * 
	 * The initial state of the pocket dimension still consists of a small flat
	 * region of cubes. Furthermore, the same rules for cycle updating still apply:
	 * during each cycle, consider the number of active neighbors of each cube.
	 * 
	 * For example, consider the same initial state as in the example above. Even
	 * though the pocket dimension is 4-dimensional, this initial state represents a
	 * small 2-dimensional slice of it. (In particular, this initial state defines a
	 * 3x3x1x1 region of the 4-dimensional space.)
	 * 
	 * Simulating a few cycles from this initial state produces the following
	 * configurations, where the result of each cycle is shown layer-by-layer at
	 * each given z and w coordinate:
	 * 
	 * Before any cycles:
	 * 
	 * z=0, w=0 .#. ..# ###
	 * 
	 * 
	 * After 1 cycle:
	 * 
	 * z=-1, w=-1 #.. ..# .#.
	 * 
	 * z=0, w=-1 #.. ..# .#.
	 * 
	 * z=1, w=-1 #.. ..# .#.
	 * 
	 * z=-1, w=0 #.. ..# .#.
	 * 
	 * z=0, w=0 #.# .## .#.
	 * 
	 * z=1, w=0 #.. ..# .#.
	 * 
	 * z=-1, w=1 #.. ..# .#.
	 * 
	 * z=0, w=1 #.. ..# .#.
	 * 
	 * z=1, w=1 #.. ..# .#.
	 * 
	 * 
	 * After 2 cycles:
	 * 
	 * z=-2, w=-2 ..... ..... ..#.. ..... .....
	 * 
	 * z=-1, w=-2 ..... ..... ..... ..... .....
	 * 
	 * z=0, w=-2 ###.. ##.## #...# .#..# .###.
	 * 
	 * z=1, w=-2 ..... ..... ..... ..... .....
	 * 
	 * z=2, w=-2 ..... ..... ..#.. ..... .....
	 * 
	 * z=-2, w=-1 ..... ..... ..... ..... .....
	 * 
	 * z=-1, w=-1 ..... ..... ..... ..... .....
	 * 
	 * z=0, w=-1 ..... ..... ..... ..... .....
	 * 
	 * z=1, w=-1 ..... ..... ..... ..... .....
	 * 
	 * z=2, w=-1 ..... ..... ..... ..... .....
	 * 
	 * z=-2, w=0 ###.. ##.## #...# .#..# .###.
	 * 
	 * z=-1, w=0 ..... ..... ..... ..... .....
	 * 
	 * z=0, w=0 ..... ..... ..... ..... .....
	 * 
	 * z=1, w=0 ..... ..... ..... ..... .....
	 * 
	 * z=2, w=0 ###.. ##.## #...# .#..# .###.
	 * 
	 * z=-2, w=1 ..... ..... ..... ..... .....
	 * 
	 * z=-1, w=1 ..... ..... ..... ..... .....
	 * 
	 * z=0, w=1 ..... ..... ..... ..... .....
	 * 
	 * z=1, w=1 ..... ..... ..... ..... .....
	 * 
	 * z=2, w=1 ..... ..... ..... ..... .....
	 * 
	 * z=-2, w=2 ..... ..... ..#.. ..... .....
	 * 
	 * z=-1, w=2 ..... ..... ..... ..... .....
	 * 
	 * z=0, w=2 ###.. ##.## #...# .#..# .###.
	 * 
	 * z=1, w=2 ..... ..... ..... ..... .....
	 * 
	 * z=2, w=2 ..... ..... ..#.. ..... .....
	 * 
	 * After the full six-cycle boot process completes, 848 cubes are left in the
	 * active state.
	 * 
	 * Starting with your given initial configuration, simulate six cycles in a
	 * 4-dimensional space. How many cubes are left in the active state after the
	 * sixth cycle?
	 */
	@Override
	public Integer part2() {
		final ConwayCubesND cubes = new ConwayCubesND(4, getLines());
		cubes.cycle(6);
		return cubes.getActiveCubeCount();
	}

	public static class ConwayCubes3D {

		static char ACTIVE = '#';
		static char INACTIVE = '.';

		private char[][][] area;

		public ConwayCubes3D(final String[] lines) {
			final int sz = lines[0].length();
			area = new char[sz][sz][sz];
			for (int y = 0; y < sz; y++) {
				area[1][y] = lines[y].toCharArray();
				for (int x = 0; x < sz; x++) {
					area[0][y][x] = INACTIVE;
					area[2][y][x] = INACTIVE;
				}
			}
		}

		public void cycle() {
			final int sz = area.length + 2;
			final char[][][] newArea = new char[sz][sz][sz];
			for (int z = 0; z < sz; z++) {
				for (int y = 0; y < sz; y++) {
					for (int x = 0; x < sz; x++) {
						newArea[z][y][x] = getNewCell(z - 1, y - 1, x - 1);
					}
				}
			}
			area = newArea;
		}

		public char getNewCell(final int z, final int y, final int x) {
			// If a cube is active and exactly 2 or 3 of its neighbors are also active,
			// the cube remains active.
			// Otherwise, the cube becomes inactive.
			// If a cube is inactive but exactly 3 of its neighbors are active,
			// the cube becomes active.
			// Otherwise, the cube remains inactive.
			final boolean active = isActive(z, y, x);
			final int neighbors = getNeighbors(z, y, x);
			if (active) {
				if (neighbors == 2 || neighbors == 3) {
					return ACTIVE;
				}
			} else if (neighbors == 3) {
				return ACTIVE;
			}
			return INACTIVE;
		}

		public void cycle(final int times) {
			for (int t = 0; t < times; t++) {
				cycle();
			}
		}

		public char[][] getLayer(final int z) {
			return area[z];
		}

		public int getNeighbors(final int z, final int y, final int x) {
			final StringBuilder sb = new StringBuilder();
			sb.append("----------------\n");
			sb.append("Neighbors of " + x + "," + y + "," + z + "\n");
			final int sz = getSize();
			int count = 0;
			for (int z2 = z - 1; z2 <= z + 1; z2++) {
				if (z2 < 0 || z2 >= sz) {
					continue;
				}
				for (int y2 = y - 1; y2 <= y + 1; y2++) {
					if (y2 < 0 || y2 >= sz) {
						continue;
					}
					for (int x2 = x - 1; x2 <= x + 1; x2++) {
						if (x2 < 0 || x2 >= sz) {
							continue;
						}
						if (z2 == z && y2 == y && x2 == x) {
							continue;
						}
						if (isActive(z2, y2, x2)) {
							sb.append("\t" + x2 + "," + y2 + "," + z2 + "\n");
							count++;
						}
					}
				}
			}
			sb.append("Count: " + count + "\n");
			sb.append("----------------");
			// System.out.println(sb.toString());
			return count;
		}

		public int getSize() {
			return area.length;
		}

		public boolean isActive(final int z, final int y, final int x) {
			return !isOutOfBounds(z, y, x) && (area[z][y][x] == ACTIVE);
		}

		public boolean isOutOfBounds(final int z, final int y, final int x) {
			final int sz = getSize();
			return x < 0 || x >= sz || y < 0 || y >= sz || z < 0 || z >= sz;
		}

		public void setLayer(final int z, final char[][] layer) {
			area[z] = layer;
		}

		public int getActive() {
			final int sz = getSize();
			int count = 0;
			for (int z = 0; z < sz; z++) {
				for (int y = 0; y < sz; y++) {
					for (int x = 0; x < sz; x++) {
						if (isActive(z, y, x)) {
							count++;
						}
					}
				}
			}
			return count;
		}

	}

	public static class ConwayCubes4D {

		static char ACTIVE = '#';
		static char INACTIVE = '.';

		private char[][][][] area;

		public ConwayCubes4D(final String[] lines) {
			final int sz = lines[0].length();
			area = new char[sz][sz][sz][sz];
			final int w = sz / 2;
			final int z = sz / 2;
			for (int y = 0; y < sz; y++) {
				area[w][z][y] = lines[y].toCharArray();
			}
		}

		public void cycle() {
			final int sz = area.length + 2;
			final char[][][][] newArea = new char[sz][sz][sz][sz];
			for (int w = 0; w < sz; w++) {
				for (int z = 0; z < sz; z++) {
					for (int y = 0; y < sz; y++) {
						for (int x = 0; x < sz; x++) {
							newArea[w][z][y][x] = getNewCell(w - 1, z - 1, y - 1, x - 1);
						}
					}
				}
			}
			area = newArea;
		}

		public char getNewCell(final int w, final int z, final int y, final int x) {
			// If a cube is active and exactly 2 or 3 of its neighbors are also active,
			// the cube remains active.
			// Otherwise, the cube becomes inactive.
			// If a cube is inactive but exactly 3 of its neighbors are active,
			// the cube becomes active.
			// Otherwise, the cube remains inactive.
			final boolean active = isActive(w, z, y, x);
			final int neighbors = getNeighbors(w, z, y, x);
			if (active) {
				if (neighbors == 2 || neighbors == 3) {
					return ACTIVE;
				}
			} else if (neighbors == 3) {
				return ACTIVE;
			}
			return INACTIVE;
		}

		public void cycle(final int times) {
			for (int t = 0; t < times; t++) {
				cycle();
			}
		}

		public char[][] getLayer(final int w, final int z) {
			return area[w][z];
		}

		public int getNeighbors(final int w, final int z, final int y, final int x) {
			final StringBuilder sb = new StringBuilder();
			sb.append("----------------\n");
			sb.append("Neighbors of " + x + "," + y + "," + z + "," + w + "\n");
			final int sz = getSize();
			int count = 0;
			for (int w2 = w - 1; w2 <= w + 1; w2++) {
				if (w2 < 0 || w2 >= sz) {
					continue;
				}
				for (int z2 = z - 1; z2 <= z + 1; z2++) {
					if (z2 < 0 || z2 >= sz) {
						continue;
					}
					for (int y2 = y - 1; y2 <= y + 1; y2++) {
						if (y2 < 0 || y2 >= sz) {
							continue;
						}
						for (int x2 = x - 1; x2 <= x + 1; x2++) {
							if (x2 < 0 || x2 >= sz) {
								continue;
							}
							if (z2 == z && y2 == y && x2 == x) {
								continue;
							}
							if (isActive(w2, z2, y2, x2)) {
								sb.append("\t" + x2 + "," + y2 + "," + z2 + "," + w2 + "\n");
								count++;
							}
						}
					}
				}
			}
			sb.append("Count: " + count + "\n");
			sb.append("----------------");
			// System.out.println(sb.toString());
			return count;
		}

		public int getSize() {
			return area.length;
		}

		public boolean isActive(final int w, final int z, final int y, final int x) {
			return !isOutOfBounds(w, z, y, x) && (area[w][z][y][x] == ACTIVE);
		}

		public boolean isOutOfBounds(final int w, final int z, final int y, final int x) {
			final int sz = getSize();
			return x < 0 || x >= sz || y < 0 || y >= sz || z < 0 || z >= sz || w < 0 || w >= sz;
		}

		public void setLayer(final int w, final int z, final char[][] layer) {
			area[w][z] = layer;
		}

		public int getActive() {
			final int sz = getSize();
			int count = 0;
			for (int w = 0; w < sz; w++) {
				for (int z = 0; z < sz; z++) {
					for (int y = 0; y < sz; y++) {
						for (int x = 0; x < sz; x++) {
							if (isActive(w, z, y, x)) {
								count++;
							}
						}
					}
				}
			}
			return count;
		}

	}

	public static class ConwayCubesD {

		public static char ACTIVE_CELL = '#';
		public static char INACTIVE_CELL = '.';

		private final int dimensions;
		private Map<Coordinate, Character> cellMap;

		private ConwayCubesD(final int dimensions, final Map<Coordinate, Character> cellMap) {
			this.dimensions = dimensions;
			this.cellMap = cellMap;
		}

		public static ConwayCubesD from(final int dimensions, final String[] lines) {
			final Map<Coordinate, Character> cellMap = new HashMap<>();
			for (int y = 0; y < lines.length; y++) {
				final String line = lines[y];
				for (int x = 0; x < line.length(); x++) {
					cellMap.put(new Coordinate(dimensions, x, y), line.charAt(x));
				}
			}
			return new ConwayCubesD(dimensions, cellMap);
		}

		public void cycle() {
			final Map<Coordinate, Character> result = new HashMap<>();
			final Coordinate start = getMinCoordinate();
			final Coordinate finish = getMaxCoordinate();
			iterate(start, finish, (coord) -> {
				final Coordinate newCoord = new Coordinate(coord.values);
				result.put(newCoord, getNewCellValue(newCoord));
			});
			this.cellMap = result;
		}

		public void cycle(final int count) {
			for (int i=0;i<count;i++) {
				cycle();
			}
		}

		public int getActiveCubeCount() {
			return cellMap.values().stream().map(ch -> ch == ACTIVE_CELL ? 1 : 0).reduce(0, Integer::sum);
		}

		public Coordinate getMaxCoordinate() {
			final Coordinate first = cellMap.keySet().stream()
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("No Coordinates Found!"));
			final List<Integer> values = first.values.stream()
					.map(d -> Integer.MIN_VALUE)
					.collect(Collectors.toList());
			for (final Coordinate coordinate : cellMap.keySet()) {
				for (int i = 0; i < values.size(); i++) {
					values.set(i, Math.max(values.get(i), coordinate.values.get(i)));
				}
			}
			final Coordinate maxValue = new Coordinate(values);
			return new Coordinate(maxValue.values.stream()
					.map(d -> d - 1)
					.collect(Collectors.toList()));
		}

		public Coordinate getMinCoordinate() {
			final Coordinate first = cellMap.keySet().stream().findFirst()
					.orElseThrow(() -> new IllegalArgumentException("No Coordinates Found!"));
			final List<Integer> values = first.values.stream().map(d -> Integer.MAX_VALUE).collect(Collectors.toList());
			for (final Coordinate coordinate : cellMap.keySet()) {
				for (int i = 0; i < values.size(); i++) {
					values.set(i, Math.min(values.get(i), coordinate.values.get(i)));
				}
			}
			final Coordinate maxValue = new Coordinate(values);
			return new Coordinate(maxValue.values.stream().map(d -> d - 1).collect(Collectors.toList()));
		}

		public List<Coordinate> getNeighbors(final Coordinate coordinate) {
			final List<Coordinate> neighbors = new ArrayList<>();
			final Coordinate start = new Coordinate(Collections.nCopies(dimensions, -1));
			final Coordinate finish = new Coordinate(Collections.nCopies(dimensions, 1));
			iterate(start, finish, (coord) -> {
				final List<Integer> values = IntStream.range(0, coord.values.size())
						.mapToObj(i -> coordinate.values.get(i) + coord.values.get(i)).collect(Collectors.toList());
				final Coordinate neighbor = new Coordinate(values);
				if (coordinate.equals(neighbor)) {
					// do not count yourself as a neighbor
					return;
				}
				neighbors.add(neighbor);
			});
			return neighbors;
		}

		public Character getNewCellValue(final Coordinate coordinate) {
			final List<Coordinate> neighbors = getNeighbors(coordinate);
			final int activeNeighbors = neighbors.stream()
					.map(coord -> cellMap.getOrDefault(coord, INACTIVE_CELL) == ACTIVE_CELL ? 1 : 0)
					.reduce(0, Integer::sum);
			if (cellMap.getOrDefault(coordinate, INACTIVE_CELL) == ACTIVE_CELL) {
				return activeNeighbors == 2 || activeNeighbors == 3 ? ACTIVE_CELL : INACTIVE_CELL;
			} else if (cellMap.getOrDefault(coordinate, INACTIVE_CELL) == INACTIVE_CELL) {
				return activeNeighbors == 3 ? ACTIVE_CELL : INACTIVE_CELL;
			} else {
				throw new IllegalStateException(
						"Unknown cell state: " + cellMap.getOrDefault(coordinate, INACTIVE_CELL));
			}
		}

		private void iterate(final Coordinate start, final Coordinate finish, Consumer<Coordinate> consumer) {
			iterate(start, finish, new ArrayList<>(), 0, consumer);
		}

		private void iterate(final Coordinate start, final Coordinate finish, final List<Integer> values,
				final int dimension, final Consumer<Coordinate> consumer) {
			if (dimension == start.values.size()) {
				consumer.accept(new Coordinate(values));
				return;
			}
			for (int x = start.values.get(dimension); x <= finish.values.get(dimension); x++) {
				final List<Integer> nextValues = new ArrayList<>(values);
				nextValues.add(x);
				iterate(start, finish, nextValues, dimension + 1, consumer);
			}
		}
	}

	public static class Coordinate {

		public final List<Integer> values;

		public Coordinate(final int size, final Integer... values) {
			final List<Integer> list = new ArrayList<>(Collections.nCopies(size, 0));
			for (int i = 0; i < values.length; i++) {
				list.set(i, values[i]);
			}
			this.values = Collections.unmodifiableList(list);
		}

		public Coordinate(final List<Integer> values) {
			this.values = Collections.unmodifiableList(values);
		}

		@Override
		public boolean equals(final Object obj) {
			if (super.equals(obj)) {
				return true;
			}
			if (!(obj instanceof Coordinate)) {
				return false;
			}
			final Coordinate that = (Coordinate) obj;
			return Arrays.equals(this.values.toArray(), that.values.toArray());
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(values.toArray());
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("[");
//			for (final Integer value : values) {
//				sb.append(value).append(",");
//			}
			for (int i = 0; i < this.values.size(); i++) {
				sb.append(values.get(i)).append(",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("]");
			return sb.toString();
		}

	}

	public static class ConwayCubesND {

		private int dimensions; // 2D, 3D, 4D, etc.
		private List<Coordinate> activeCells = new ArrayList<>();

		public ConwayCubesND(final int dimensions, final String[] lines) {
			this.dimensions = dimensions;
			for (int y = 0; y < lines.length; y++) {
				final String line = lines[y];
				for (int x = 0; x < line.length(); x++) {
					if (line.charAt(x) == '#') {
						activeCells.add(new Coordinate(dimensions, x, y));
					}
				}
			}
		}

		public void cycle() {
			final List<Coordinate> activeCells = new ArrayList<>();
			final Coordinate start = getMinCoordinate();
			final Coordinate finish = getMaxCoordinate();
			iterate(start, finish, (coord) -> {
				final Coordinate newCoord = new Coordinate(coord.values);
				if (isNewCellActive(newCoord)) {
					activeCells.add(newCoord);
				}
			});
			this.activeCells = activeCells;
		}

		public void cycle(final int count) {
			for (int i = 0; i < count; i++) {
				cycle();
			}
		}

		public int getActiveCubeCount() {
			return activeCells.size();
		}

		public Coordinate getMaxCoordinate() {
			final Coordinate first = activeCells.get(0);
			final List<Integer> values = first.values.stream().map(d -> Integer.MIN_VALUE).collect(Collectors.toList());
			for (final Coordinate coordinate : activeCells) {
				for (int i = 0; i < values.size(); i++) {
					values.set(i, Math.max(values.get(i), coordinate.values.get(i)));
				}
			}
			final Coordinate maxValue = new Coordinate(values);
			return new Coordinate(maxValue.values.stream().map(d -> d + 1).collect(Collectors.toList()));
		}

		public Coordinate getMinCoordinate() {
			final Coordinate first = activeCells.get(0);
			final List<Integer> values = first.values.stream().map(d -> Integer.MAX_VALUE).collect(Collectors.toList());
			for (final Coordinate coordinate : activeCells) {
				for (int i = 0; i < values.size(); i++) {
					values.set(i, Math.min(values.get(i), coordinate.values.get(i)));
				}
			}
			final Coordinate minValue = new Coordinate(values);
			return new Coordinate(minValue.values.stream().map(d -> d - 1).collect(Collectors.toList()));
		}

		public List<Coordinate> getNeighbors(final Coordinate coordinate) {
			final List<Coordinate> neighbors = new ArrayList<>();
			final Coordinate start = new Coordinate(Collections.nCopies(dimensions, -1));
			final Coordinate finish = new Coordinate(Collections.nCopies(dimensions, 1));
			iterate(start, finish, (coord) -> {
				final List<Integer> values = IntStream.range(0, coord.values.size())
						.mapToObj(i -> coordinate.values.get(i) + coord.values.get(i)).collect(Collectors.toList());
				final Coordinate neighbor = new Coordinate(values);
				if (coordinate.equals(neighbor)) {
					// do not count yourself as a neighbor
					return;
				}
				neighbors.add(neighbor);
			});
			return neighbors;
		}

		public List<Coordinate> getNeighbors(final Integer... values) {
			return getNeighbors(new Coordinate(dimensions, values));
		}

		public boolean isCellActive(final Coordinate coordinates) {
			return (activeCells.contains(coordinates));
		}

		public boolean isCellActive(final Integer... values) {
			return isCellActive(new Coordinate(dimensions, values));
		}

		public boolean isNewCellActive(final Coordinate coordinates) {
			final List<Coordinate> neighbors = getNeighbors(coordinates);
			final int activeNeighbors = neighbors.stream().map(coord -> isCellActive(coord) ? 1 : 0).reduce(0,
					Integer::sum);
			if (isCellActive(coordinates)) {
				return activeNeighbors == 2 || activeNeighbors == 3;
			} else {
				return activeNeighbors == 3;
			}
		}

		public boolean isNewCellActive(final Integer... values) {
			return isNewCellActive(new Coordinate(dimensions, values));
		}

		private void iterate(final Coordinate start, final Coordinate finish, Consumer<Coordinate> consumer) {
			iterate(start, finish, new ArrayList<>(), 0, consumer);
		}

		private void iterate(final Coordinate start, final Coordinate finish, final List<Integer> values,
				final int dimension, final Consumer<Coordinate> consumer) {
			if (dimension == start.values.size()) {
				consumer.accept(new Coordinate(values));
				return;
			}
			for (int x = start.values.get(dimension); x <= finish.values.get(dimension); x++) {
				final List<Integer> nextValues = new ArrayList<>(values);
				nextValues.add(x);
				iterate(start, finish, nextValues, dimension + 1, consumer);
			}
		}

	}

}
