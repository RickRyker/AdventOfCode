package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.List;

import com.rykerstudios.advent.Day;

public class Day20 extends Day<Long> {

	public Day20() {
		super(0L, 0L);
	}

	/**
	 * --- Day 20: Jurassic Jigsaw ---
	 * 
	 * The high-speed train leaves the forest and quickly carries you south. You can
	 * even see a desert in the distance! Since you have some spare time, you might
	 * as well see if there was anything interesting in the image the Mythical
	 * Information Bureau satellite captured.
	 * 
	 * After decoding the satellite messages, you discover that the data actually
	 * contains many small images created by the satellite's camera array. The
	 * camera array consists of many cameras; rather than produce a single square
	 * image, they produce many smaller square image tiles that need to be
	 * reassembled back into a single image.
	 * 
	 * Each camera in the camera array returns a single monochrome image tile with a
	 * random unique ID number. The tiles (your puzzle input) arrived in a random
	 * order.
	 * 
	 * Worse yet, the camera array appears to be malfunctioning: each image tile has
	 * been rotated and flipped to a random orientation. Your first task is to
	 * reassemble the original image by orienting the tiles so they fit together.
	 * 
	 * To show how the tiles should be reassembled, each tile's image data includes
	 * a border that should line up exactly with its adjacent tiles. All tiles have
	 * this border, and the border lines up exactly when the tiles are both oriented
	 * correctly. Tiles at the edge of the image also have this border, but the
	 * outermost edges won't line up with any other tiles.
	 * 
	 * For example, suppose you have the following nine tiles:
	 * 
	 * Tile 2311: <br/>
	 * ..##.#..#. <br/>
	 * ##..#..... <br/>
	 * #...##..#. <br/>
	 * ####.#...# <br/>
	 * ##.##.###. <br/>
	 * ##...#.### <br/>
	 * .#.#.#..## <br/>
	 * ..#....#.. <br/>
	 * ###...#.#. <br/>
	 * ..###..### <br/>
	 * 
	 * Tile 1951: <br/>
	 * #.##...##. <br/>
	 * #.####...# <br/>
	 * .....#..## <br/>
	 * #...###### <br/>
	 * .##.#....# <br/>
	 * .###.##### <br/>
	 * ###.##.##. <br/>
	 * .###....#. <br/>
	 * ..#.#..#.# <br/>
	 * #...##.#.. <br/>
	 * 
	 * Tile 1171: <br/>
	 * ####...##. <br/>
	 * #..##.#..# <br/>
	 * ##.#..#.#. <br/>
	 * .###.####. <br/>
	 * ..###.#### <br/>
	 * .##....##. <br/>
	 * .#...####. <br/>
	 * #.##.####. <br/>
	 * ####..#... <br/>
	 * .....##... <br/>
	 * 
	 * Tile 1427: <br/>
	 * ###.##.#.. <br/>
	 * .#..#.##.. <br/>
	 * .#.##.#..# <br/>
	 * #.#.#.##.# <br/>
	 * ....#...## <br/>
	 * ...##..##. <br/>
	 * ...#.##### <br/>
	 * .#.####.#. <br/>
	 * ..#..###.# <br/>
	 * ..##.#..#. <br/>
	 * 
	 * Tile 1489: <br/>
	 * ##.#.#.... <br/>
	 * ..##...#.. <br/>
	 * .##..##... <br/>
	 * ..#...#... <br/>
	 * #####...#. <br/>
	 * #..#.#.#.# <br/>
	 * ...#.#.#.. <br/>
	 * ##.#...##. <br/>
	 * ..##.##.## <br/>
	 * ###.##.#.. <br/>
	 * 
	 * Tile 2473: <br/>
	 * #....####. <br/>
	 * #..#.##... <br/>
	 * #.##..#... <br/>
	 * ######.#.# <br/>
	 * .#...#.#.# <br/>
	 * .######### <br/>
	 * .###.#..#. <br/>
	 * ########.# <br/>
	 * ##...##.#. <br/>
	 * ..###.#.#. <br/>
	 * 
	 * Tile 2971: <br/>
	 * ..#.#....# <br/>
	 * #...###... <br/>
	 * #.#.###... <br/>
	 * ##.##..#.. <br/>
	 * .#####..## <br/>
	 * .#..####.# <br/>
	 * #..#.#..#. <br/>
	 * ..####.### <br/>
	 * ..#.#.###. <br/>
	 * ...#.#.#.# <br/>
	 * 
	 * Tile 2729: <br/>
	 * ...#.#.#.# <br/>
	 * ####.#.... <br/>
	 * ..#.#..... <br/>
	 * ....#..#.# <br/>
	 * .##..##.#. <br/>
	 * .#.####... <br/>
	 * ####.#.#.. <br/>
	 * ##.####... <br/>
	 * ##..#.##.. <br/>
	 * #.##...##. <br/>
	 * 
	 * Tile 3079: <br/>
	 * #.#.#####. <br/>
	 * .#..###### <br/>
	 * ..#....... <br/>
	 * ######.... <br/>
	 * ####.#..#. <br/>
	 * .#...#.##. <br/>
	 * #.#####.## <br/>
	 * ..#.###... <br/>
	 * ..#....... <br/>
	 * ..#.###... <br/>
	 * 
	 * By rotating, flipping, and rearranging them, you can find a square
	 * arrangement that causes all adjacent borders to line up:
	 * 
	 * #...##.#.. ..###..### #.#.#####. <br/>
	 * ..#.#..#.# ###...#.#. .#..###### <br/>
	 * .###....#. ..#....#.. ..#....... <br/>
	 * ###.##.##. .#.#.#..## ######.... <br/>
	 * .###.##### ##...#.### ####.#..#. <br/>
	 * .##.#....# ##.##.###. .#...#.##. <br/>
	 * #...###### ####.#...# #.#####.## <br/>
	 * .....#..## #...##..#. ..#.###... <br/>
	 * #.####...# ##..#..... ..#....... <br/>
	 * #.##...##. ..##.#..#. ..#.###... <br/>
	 * 
	 * #.##...##. ..##.#..#. ..#.###... <br/>
	 * ##..#.##.. ..#..###.# ##.##....# <br/>
	 * ##.####... .#.####.#. ..#.###..# <br/>
	 * ####.#.#.. ...#.##### ###.#..### <br/>
	 * .#.####... ...##..##. .######.## <br/>
	 * .##..##.#. ....#...## #.#.#.#... <br/>
	 * ....#..#.# #.#.#.##.# #.###.###. <br/>
	 * ..#.#..... .#.##.#..# #.###.##.. <br/>
	 * ####.#.... .#..#.##.. .######... <br/>
	 * ...#.#.#.# ###.##.#.. .##...#### <br/>
	 * 
	 * ...#.#.#.# ###.##.#.. .##...#### <br/>
	 * ..#.#.###. ..##.##.## #..#.##..# <br/>
	 * ..####.### ##.#...##. .#.#..#.## <br/>
	 * #..#.#..#. ...#.#.#.. .####.###. <br/>
	 * .#..####.# #..#.#.#.# ####.###.. <br/>
	 * .#####..## #####...#. .##....##. <br/>
	 * ##.##..#.. ..#...#... .####...#. <br/>
	 * #.#.###... .##..##... .####.##.# <br/>
	 * #...###... ..##...#.. ...#..#### <br/>
	 * ..#.#....# ##.#.#.... ...##..... <br/>
	 * 
	 * For reference, the IDs of the above tiles are:
	 * 
	 * 1951 2311 3079 2729 1427 2473 2971 1489 1171
	 * 
	 * To check that you've assembled the image correctly, multiply the IDs of the
	 * four corner tiles together. If you do this with the assembled tiles from the
	 * example above, you get 1951 * 3079 * 2971 * 1171 = 20899048083289.
	 * 
	 * Assemble the tiles into an image. What do you get if you multiply together
	 * the IDs of the four corner tiles?
	 */
	@Override
	public Long part1() {
		final JurassicJigsaw jigsaw = new JurassicJigsaw(getLines());
		jigsaw.markTilesWithMatchingEdges();
		long count = 0;
		return count;
	}

	@Override
	public Long part2() {
		final JurassicJigsaw jigsaw = new JurassicJigsaw(getLines());
		jigsaw.markTilesWithMatchingEdges();
		long count = 0;
		return count;
	}

	public static class JigsawTile implements Comparable<JigsawTile> {

		private final int TILE_SIZE = 10;

		final long id;
		final List<String> lines = new ArrayList<>();
//		final List<Integer> edges = new ArrayList<>();
//		final List<Integer> flippedEdges = new ArrayList<>();
		final List<String> edges = new ArrayList<>();
		int matchingEdges = 0;

		public JigsawTile(final String tileId) {
			this.id = Long.parseLong(tileId.substring(5, 9));
		}

		public void addEdge(final String edge) {
			edges.add(edge);
//			this.edges.add(Integer.parseInt(edge, 2));
			final String flippedEdge = new StringBuilder().append(edge).reverse().toString();
			edges.add(flippedEdge);
//			this.flippedEdges.add(Integer.parseInt(flippedEdge, 2));
		}

		public void addLine(final String line) {
			// this.lines.add(line.replace('#', '1').replace('.', '0'));
			this.lines.add(line);
		}

		@Override
		public int compareTo(final JigsawTile that) {
			return this.matchingEdges - that.matchingEdges;
		}

		public void complete() {
			// calculate the edges
			final StringBuilder sbLeft = new StringBuilder();
			final StringBuilder sbRight = new StringBuilder();
			for (int y = 0; y < TILE_SIZE; y++) {
				final String line = lines.get(y);
				sbLeft.append(line.charAt(0));
				sbRight.append(line.charAt(TILE_SIZE - 1));
			}
			addEdge(lines.get(0));
			addEdge(lines.get(TILE_SIZE - 1));
			addEdge(sbLeft.toString());
			addEdge(sbRight.toString());
			// Collections.sort(edges);
			// Collections.sort(flippedEdges);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id:").append(id);
			sb.append(",edges:").append(edges);
			// sb.append(",").append(flippedEdges);
			sb.append(",matchingEdges:").append(matchingEdges);
			sb.append("}");
			return sb.toString();
		}

	}

	public static class JurassicJigsaw {

		public final List<JigsawTile> tiles = new ArrayList<>();

		public JurassicJigsaw(final String[] lines) {
			JigsawTile current = null;
			for (final String line : lines) {
				if (line.isEmpty()) {
					// depends on input file ending with a blank line
					// check your input file
					if (current != null) {
						current.complete();
						tiles.add(current);
					}
					current = null;
					continue;
				}
				if (current == null) {
					current = new JigsawTile(line);
					continue;
				}
				current.addLine(line);
			}
		}

		public void markTilesWithMatchingEdges() {
			for (int i = 0; i < tiles.size() - 1; i++) {
				final JigsawTile tile1 = tiles.get(i);
				for (int j = i + 1; j < tiles.size(); j++) {
					final JigsawTile tile2 = tiles.get(j);
					if (tilesMatch(tile1, tile2)) {
						tile1.matchingEdges++;
						tile2.matchingEdges++;
					}
				}
			}
		}

		public boolean tilesMatch(final JigsawTile tile1, final JigsawTile tile2) {
			for (final String edge1 : tile1.edges) {
				for (final String edge2 : tile2.edges) {
					if (edge1.equals(edge2)) {
						return true;
					}
				}
//				for (final Integer edge2 : tile2.flippedEdges) {
//					if (edge == edge2) {
//						return true;
//					}
//				}
			}
//			for (final Integer edge : tile1.flippedEdges) {
//				for (final Integer edge2 : tile2.edges) {
//					if (edge == edge2) {
//						return true;
//					}
//				}
//				for (final Integer edge2 : tile2.flippedEdges) {
//					if (edge == edge2) {
//						return true;
//					}
//				}
//			}
			return false;
		}

	}

}
