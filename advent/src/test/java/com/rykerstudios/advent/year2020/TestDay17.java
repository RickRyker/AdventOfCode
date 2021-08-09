package com.rykerstudios.advent.year2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rykerstudios.advent.TestDay;
import com.rykerstudios.advent.year2020.Day17.ConwayCubes3D;
import com.rykerstudios.advent.year2020.Day17.ConwayCubesND;
import com.rykerstudios.advent.year2020.Day17.Coordinate;

@RunWith(JUnitPlatform.class)
public class TestDay17 extends TestDay<Day17> {

	@Override
	public Day17 createDay() {
		return new Day17();
	}

	private static String[] EXAMPLE0 = new String[] { "###", "###", "###" };
	private static String[] EXAMPLE1 = new String[] { ".#.", "..#", "###" };

	@Test
	public void testConwayCubes3D_isActive() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE0);
		cubes.setLayer(0, cubes.getLayer(1));
		cubes.setLayer(2, cubes.getLayer(1));
		final int sz = cubes.getSize();
		Assertions.assertEquals(3, sz);
		for (int z = 0; z < sz; z++) {
			for (int y = 0; y < sz; y++) {
				for (int x = 0; x < sz; x++) {
					Assertions.assertTrue(cubes.isActive(z, y, x));
				}
			}
		}
	}

	@Test
	public void testConwayCubes3D_isActive_Example1() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		final int sz = cubes.getSize();
		Assertions.assertEquals(3, sz);
		for (int y = 0; y < sz; y++) {
			for (int x = 0; x < sz; x++) {
				Assertions.assertFalse(cubes.isActive(0, y, x));
				Assertions.assertFalse(cubes.isActive(2, y, x));
			}
		}
		Assertions.assertFalse(cubes.isActive(1, 0, 0));
		Assertions.assertTrue(cubes.isActive(1, 0, 1));
		Assertions.assertFalse(cubes.isActive(1, 0, 2));
		Assertions.assertFalse(cubes.isActive(1, 1, 0));
		Assertions.assertFalse(cubes.isActive(1, 1, 1));
		Assertions.assertTrue(cubes.isActive(1, 1, 2));
		Assertions.assertTrue(cubes.isActive(1, 2, 0));
		Assertions.assertTrue(cubes.isActive(1, 2, 1));
		Assertions.assertTrue(cubes.isActive(1, 2, 2));
	}

	@Test
	public void testConwayCubes3D_neighborsCenter() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE0);
		cubes.setLayer(0, cubes.getLayer(1));
		cubes.setLayer(2, cubes.getLayer(1));
		// Center 1
		Assertions.assertEquals(26, cubes.getNeighbors(1, 1, 1));
	}

	@Test
	public void testConwayCubes3D_neighborsCenterFace() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE0);
		cubes.setLayer(0, cubes.getLayer(1));
		cubes.setLayer(2, cubes.getLayer(1));
		// Center Face 6
		Assertions.assertEquals(17, cubes.getNeighbors(1, 1, 0));
		Assertions.assertEquals(17, cubes.getNeighbors(1, 1, 2));
		Assertions.assertEquals(17, cubes.getNeighbors(1, 0, 1));
		Assertions.assertEquals(17, cubes.getNeighbors(1, 2, 1));
		Assertions.assertEquals(17, cubes.getNeighbors(0, 1, 1));
		Assertions.assertEquals(17, cubes.getNeighbors(2, 1, 1));
	}

	@Test
	public void testConwayCubes3D_neighborsCenterEdge() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE0);
		cubes.setLayer(0, cubes.getLayer(1));
		cubes.setLayer(2, cubes.getLayer(1));
		// Center Edge 12
		Assertions.assertEquals(11, cubes.getNeighbors(1, 0, 0));
		Assertions.assertEquals(11, cubes.getNeighbors(0, 1, 0));
		Assertions.assertEquals(11, cubes.getNeighbors(2, 1, 0));
		Assertions.assertEquals(11, cubes.getNeighbors(1, 2, 0));
		Assertions.assertEquals(11, cubes.getNeighbors(0, 0, 1));
		Assertions.assertEquals(11, cubes.getNeighbors(2, 0, 1));
		Assertions.assertEquals(11, cubes.getNeighbors(0, 2, 1));
		Assertions.assertEquals(11, cubes.getNeighbors(2, 2, 1));
		Assertions.assertEquals(11, cubes.getNeighbors(1, 0, 2));
		Assertions.assertEquals(11, cubes.getNeighbors(0, 1, 2));
		Assertions.assertEquals(11, cubes.getNeighbors(2, 1, 2));
		Assertions.assertEquals(11, cubes.getNeighbors(1, 2, 2));
	}

	@Test
	public void testConwayCubes3D_neighborsCorner() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE0);
		cubes.setLayer(0, cubes.getLayer(1));
		cubes.setLayer(2, cubes.getLayer(1));
		// Corner 8
		Assertions.assertEquals(7, cubes.getNeighbors(0, 0, 0));
		Assertions.assertEquals(7, cubes.getNeighbors(2, 0, 0));
		Assertions.assertEquals(7, cubes.getNeighbors(0, 2, 0));
		Assertions.assertEquals(7, cubes.getNeighbors(2, 2, 0));
		Assertions.assertEquals(7, cubes.getNeighbors(0, 0, 2));
		Assertions.assertEquals(7, cubes.getNeighbors(2, 0, 2));
		Assertions.assertEquals(7, cubes.getNeighbors(0, 2, 2));
		Assertions.assertEquals(7, cubes.getNeighbors(2, 2, 2));
	}

	@Test
	public void testConwayCubes3D_neighbors_Example1() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		Assertions.assertEquals(1, cubes.getNeighbors(0, 0, 0));
		Assertions.assertEquals(3, cubes.getNeighbors(0, 1, 0));
		Assertions.assertEquals(2, cubes.getNeighbors(0, 2, 0));
		Assertions.assertEquals(2, cubes.getNeighbors(0, 0, 1));
		Assertions.assertEquals(5, cubes.getNeighbors(0, 1, 1));
		Assertions.assertEquals(4, cubes.getNeighbors(0, 2, 1));
		Assertions.assertEquals(5, cubes.getNeighbors(1, 1, 1));
		Assertions.assertEquals(3, cubes.getNeighbors(2, 2, 2));
		Assertions.assertEquals(5, cubes.getActive());
	}

	@Test
	public void testConwayCubes3D_newCell_Example1() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		final int sz = cubes.getSize();
		Assertions.assertEquals(3, sz);
		for (int z = -1; z <= sz; z++) {
			for (int y = -1; y <= sz; y++) {
				for (int x = -1; x <= sz; x++) {
					if (ConwayCubes3D.ACTIVE == cubes.getNewCell(z, y, x)) {
						System.out.println(z + "," + y + "," + x + ":" + ConwayCubes3D.ACTIVE);
					}
				}
			}
		}
		/*
		 * z=0 ... | #.. | ..# | .#.
		 * z=1 ... | #.# | .## | .#.
		 * z=2 ... | #.. | ..# | .#.
		 */
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(0, 1, 0));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(0, 2, 2));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(0, 3, 1));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(1, 1, 0));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(1, 1, 2));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(1, 2, 1));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(1, 2, 2));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(1, 3, 1));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(2, 1, 0));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(2, 2, 2));
		Assertions.assertEquals(ConwayCubes3D.ACTIVE, cubes.getNewCell(2, 3, 1));
	}

	@Test
	public void testConwayCubes3D_0cycles() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		cubes.cycle(0);
		Assertions.assertEquals(3, cubes.getSize());
		final int sz = cubes.getSize();
		Assertions.assertEquals(3, sz);
//		for (int z = 0; z < sz; z++) {
//			System.out.println(z + ":" + Day17.toString(cubes.getLayer(z)));
//		}
		Assertions.assertEquals(5, cubes.getActive());
	}

	@Test
	public void testConwayCubes3D_1cycles() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		cubes.cycle(1);
		final int sz = cubes.getSize();
		Assertions.assertEquals(5, sz);
		for (int z = 0; z < sz; z++) {
			System.out.println(z + ":" + Day17.toString(cubes.getLayer(z)));
		}
		Assertions.assertEquals(11, cubes.getActive());
		for (int z = 0; z < sz; z++) {
			for (int y = 0; y < sz; y++) {
				for (int x = 0; x < sz; x++) {
					if (cubes.isActive(z, y, x)) {
						System.out.println(z + "," + y + "," + x + ":" + ConwayCubes3D.ACTIVE);
					}
				}
			}
		}
		Assertions.assertEquals("[.,.,.,.,.],[.,.,.,.,.],[.,.,.,.,.],[.,.,.,.,.],[.,.,.,.,.]",
				Day17.toString(cubes.getLayer(0)));
		Assertions.assertEquals("[.,.,.,.,.],[.,.,.,.,.],[.,#,.,.,.],[.,.,.,#,.],[.,.,#,.,.]",
				Day17.toString(cubes.getLayer(1)));
		Assertions.assertEquals("[.,.,.,.,.],[.,.,.,.,.],[.,#,.,#,.],[.,.,#,#,.],[.,.,#,.,.]",
				Day17.toString(cubes.getLayer(2)));
		Assertions.assertEquals("[.,.,.,.,.],[.,.,.,.,.],[.,#,.,.,.],[.,.,.,#,.],[.,.,#,.,.]",
				Day17.toString(cubes.getLayer(3)));
		Assertions.assertEquals("[.,.,.,.,.],[.,.,.,.,.],[.,.,.,.,.],[.,.,.,.,.],[.,.,.,.,.]",
				Day17.toString(cubes.getLayer(4)));
	}

	@Test
	public void testConwayCubes3D_2cycles() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		cubes.cycle(2);
		final int sz = cubes.getSize();
		Assertions.assertEquals(7, sz);
		for (int z = 0; z < sz; z++) {
			System.out.println(z + ":" + Day17.toString(cubes.getLayer(z)));
		}
		Assertions.assertEquals(21, cubes.getActive());
	}

	@Test
	public void testConwayCubes3D_3cycles() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		cubes.cycle(3);
		final int sz = cubes.getSize();
		for (int z = 0; z < sz; z++) {
			System.out.println(z + ":" + Day17.toString(cubes.getLayer(z)));
		}
		Assertions.assertEquals(38, cubes.getActive());
		/**
		 * * z=-2 <br/>
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
		 */
	}

	@Test
	public void testConwayCubes3D_6cycles() {
		final ConwayCubes3D cubes = new ConwayCubes3D(EXAMPLE1);
		cubes.cycle(6);
		Assertions.assertEquals(112, cubes.getActive());
	}

	// -------- -------- Conway Cubes N Dimensions -------- --------

	@Test
	public void testConwayCubesND_isCellActive_example0() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE0);
		Assertions.assertTrue(cubes.isCellActive(0, 0));
		Assertions.assertTrue(cubes.isCellActive(1, 0));
		Assertions.assertTrue(cubes.isCellActive(2, 0));
		Assertions.assertTrue(cubes.isCellActive(0, 1));
		Assertions.assertTrue(cubes.isCellActive(1, 1));
		Assertions.assertTrue(cubes.isCellActive(2, 1));
		Assertions.assertTrue(cubes.isCellActive(0, 2));
		Assertions.assertTrue(cubes.isCellActive(1, 2));
		Assertions.assertTrue(cubes.isCellActive(2, 2));
		Assertions.assertEquals(9, cubes.getActiveCubeCount());
	}

	@Test
	public void testConwayCubesND_isCellActive_example1() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE1);
		Assertions.assertTrue(cubes.isCellActive(1, 0));
		Assertions.assertTrue(cubes.isCellActive(2, 1));
		Assertions.assertTrue(cubes.isCellActive(0, 2));
		Assertions.assertTrue(cubes.isCellActive(1, 2));
		Assertions.assertTrue(cubes.isCellActive(2, 2));
		Assertions.assertEquals(5, cubes.getActiveCubeCount());
	}

	@Test
	public void testConwayCubesND_getMaxCoordinate_example1() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE1);
		final Coordinate expected = new Coordinate(4, 3, 3, 1, 1);
		final Coordinate minCoord = cubes.getMaxCoordinate();
		Assertions.assertEquals(expected, minCoord);
	}

	@Test
	public void testConwayCubesND_getMinCoordinate_example1() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE1);
		final Coordinate expected = new Coordinate(4, -1, -1, -1, -1);
		final Coordinate minCoord = cubes.getMinCoordinate();
		Assertions.assertEquals(expected, minCoord);
	}

	@Test
	public void testConwayCubesND_getNeighbors_example1() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE1);
		Assertions.assertEquals(80, cubes.getNeighbors(0, 0).size());
	}

	@Test
	public void testConwayCubesND_getNewCellActive_example1() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE1);
		Assertions.assertTrue(cubes.isCellActive(1, 0, 0, 0));
		Assertions.assertTrue(cubes.isCellActive(2, 1, 0, 0));
		Assertions.assertTrue(cubes.isCellActive(0, 2, 0, 0));
		Assertions.assertTrue(cubes.isCellActive(1, 2, 0, 0));
		Assertions.assertTrue(cubes.isCellActive(2, 2, 0, 0));
		Assertions.assertEquals(5, cubes.getActiveCubeCount());
		// And new cell active
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, -1, -1));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, -1, 0));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, -1, 1));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, 0, -1));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, 0, 0));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, 0, 1));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, 1, -1));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, 1, 0));
		Assertions.assertTrue(cubes.isNewCellActive(0, 1, 1, 1));
		Assertions.assertTrue(cubes.isNewCellActive(1, 2, 0, 0));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, -1, -1));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, -1, 0));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, -1, 1));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, 0, -1));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, 0, 0));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, 0, 1));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, 1, -1));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, 1, 0));
		Assertions.assertTrue(cubes.isNewCellActive(1, 3, 1, 1));
		Assertions.assertTrue(cubes.isNewCellActive(2, 1, 0, 0));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, -1, -1));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, -1, 0));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, -1, 1));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, 0, -1));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, 0, 0));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, 0, 1));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, 1, -1));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, 1, 0));
		Assertions.assertTrue(cubes.isNewCellActive(2, 2, 1, 1));
		Assertions.assertEquals(5, cubes.getActiveCubeCount());
	}

	@Test
	public void testConwayCubesND_1Cycle_example1() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE1);
		cubes.cycle();
		Assertions.assertEquals(29, cubes.getActiveCubeCount());
	}

	@Test
	public void testConwayCubesND_6cycles_example1() {
		final ConwayCubesND cubes = new ConwayCubesND(4, EXAMPLE1);
		cubes.cycle(6);
		Assertions.assertEquals(848, cubes.getActiveCubeCount());
	}

}
