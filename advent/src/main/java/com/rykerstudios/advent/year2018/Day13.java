package com.rykerstudios.advent.year2018;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rykerstudios.advent.Day;

public class Day13 extends Day<String> {

	private static final Logger log = LoggerFactory.getLogger(Day13.class);

	protected class Cart implements Comparable<Cart> {

		private final Integer id;
		private char dir = 'v';
		private int turn = 0;

		public Cart(final char d) {
			this.id = nextId();
			this.dir = d;
		}

		@Override
		public int compareTo(final Cart that) {
			return new CompareToBuilder().append(this.id, that.id).build();
		}

		@Override
		public boolean equals(final Object o) {
			if (!(o instanceof Cart)) {
				return false;
			}
			return ((Cart) o).id == this.id;
		}

		public char getDirection() {
			return this.dir;
		}

		@Override
		public int hashCode() {
			return this.id.hashCode();
		}

		public Cart incTurn() {
			turn = (turn + 1) % 3;
			return this;
		}

		public void setDirection(final char dir) {
			this.dir = dir;
		}

		@Override
		public String toString() {
			return new ToStringBuilder(this)
				.append("id", id)
				.append("dir", dir)
				.append("turn", turn)
				.build();
		}

		public void turnAtIntersection() {
			if (turn == 0) {
				// Turn left
				if (dir == '<') {
					dir = 'v';
				} else if (dir == '>') {
					dir = '^';
				} else if (dir == '^') {
					dir = '<';
				} else if (dir == 'v') {
					dir = '>';
				}
			} else if (turn == 2) {
				// Turn right
				if (dir == '<') {
					dir = '^';
				} else if (dir == '>') {
					dir = 'v';
				} else if (dir == '^') {
					dir = '>';
				} else if (dir == 'v') {
					dir = '<';
				}
			} else {
				// Go Straight
			}
			incTurn();
		}

		public void updateDirection(final Track track) {
			if (track.getType() == '/') {
				if (dir == '<') {
					dir = 'v';
				} else if (dir == '>') {
					dir = '^';
				} else if (dir == '^') {
					dir = '>';
				} else if (dir == 'v') {
					dir = '<';
				}
			} else if (track.getType() == '\\') {
				if (dir == '<') {
					dir = '^';
				} else if (dir == '>') {
					dir = 'v';
				} else if (dir == '^') {
					dir = '<';
				} else if (dir == 'v') {
					dir = '>';
				}
			} else if (track.getType() == '+') {
				turnAtIntersection();
			}
			// no change
		}

	}

	protected class Track {

		private char type;
		private boolean hasCrash = false;
		private Cart cart = null;

		public Track(final char type) {
			if (type == '<' || type == '>') {
				this.type = '-';
			} else if (type == '^' || type == 'v') {
				this.type = '|';
			} else {
				this.type = type;
			}
		}

		public void clearCart() {
			cart = null;
		}

		public void clearCrash() {
			hasCrash = false;
		}

		public Cart getCart() {
			return cart;
		}

		public char getType() {
			return type;
		}

		public boolean hasCart() {
			return cart != null;
		}

		public boolean hasCrash() {
			return hasCrash;
		}

		public void setCart(final Cart cart) {
			this.cart = cart;
		}

		public void setCrash() {
			hasCrash = true;
		}

		@Override
		public String toString() {
			return new ToStringBuilder(this)
				.append("type", type)
				.append("hasCrash", hasCrash)
				.append("cart", cart)
				.build();
		}

	}

	private static int lastId = 0;

	protected static boolean isCart(final char c) {
		return c == '^' || c == 'v' || c == '<' || c == '>';
	}

	protected static boolean isTrack(final char c) {
		return c == '/' || c == '-' || c == '\\' || c == '|' || c == '+';
	}

	public static int nextId() {
		lastId++;
		return lastId;
	}

	protected boolean firstCrashFound = false;
	protected boolean logDuringTicks = false;
	protected int carts = 0;
	protected int maxX = 0;
	protected int maxY = 0;
	protected int tick = 0;
	protected int[] firstCrash = { 0, 0 };
	protected int[] lastCart = { 0, 0 };
	protected final Set<Cart> movedCarts = new HashSet<>();
	protected Track[][] tracks = null;
	protected String collissions = "";

	public Day13() {
		super("71,121", "71,76"); // not 70,76 or 71,75 or 71,76 either
	}

	protected int[] getLastCart () {
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				final Track track = tracks[x][y];
				if (track != null && track.hasCart()) {
					return new int[] { x, y };
				}
			}
		}
		return new int[] { -1, -1 };
	}

	protected int[] getNewLocation(final int x, final int y, final int dir) {
		int newX = x;
		int newY = y;
		switch (dir) {
		case 'v':
			newY++;
			break;
		case '<':
			newX--;
			break;
		case '>':
			newX++;
			break;
		case '^':
			newY--;
			break;
		default:
			log.error("Weird direction!");
			break;
		}
		return new int[] { newX, newY };
	}

	protected void init(final String[] lines) {
		maxY = lines.length;
		for (final String line : lines) {
			if (maxX < line.length()) {
				maxX = line.length();
			}
		}
		log.info("Track dimensions: [x,y] = [" + maxX + "," + maxY + "]");
		tracks = new Track[maxX][maxY];
		initTracks(tracks, lines);
	}

	protected void initTracks(final Track[][] tracks, final String[] lines) {
		int y = 0;
		for (final String line : lines) {
			final char a[] = line.toCharArray();
			for (int x = 0; x < a.length; x++) {
				final char c = a[x];
				if (isCart(c)) {
					tracks[x][y] = new Track(c);
					tracks[x][y].setCart(new Cart(c));
					carts++;
				} else if (isTrack(c)) {
					tracks[x][y] = new Track(c);
				} else {
					tracks[x][y] = null;
				}
			}
			y++;
		}
	}

	public String logCartLocation(final int x, final int y, final Track track) {
		final StringBuffer sb = new StringBuffer();
		final Cart cart = track.getCart();
		sb.append("cart={id:").append(cart.id);
		sb.append(",x:").append(x).append(",y:").append(y);
		sb.append(",d:").append(cart.getDirection());
		sb.append(",on:").append(track.getType()).append("}");
		return sb.toString();
	}

	public void logCartLocations(final int carts, final int maxX, final int maxY, final int tick,
			final Track tracks[][]) {
		final StringBuffer sb = new StringBuffer();
		sb.append("tick=").append(tick).append(";carts=").append(carts).append(" ");
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				if (tracks[x][y] == null) {
					// nothing
				} else if (tracks[x][y].hasCart()) {
					sb.append("\n");
					sb.append("\t");
					sb.append(logCartLocation(x, y, tracks[x][y]));
				}
			}
		}
		log.info(sb.toString());
	}

	protected void logHorizontalAxis() {
		log.info("00000000000000000000000000000000000000000000000000"
				+ "00000000000000000000000000000000000000000000000000"
				+ "11111111111111111111111111111111111111111111111111");
		log.info("00000000001111111111222222222233333333334444444444"
				+ "55555555556666666666777777777788888888889999999999"
				+ "00000000001111111111222222222233333333334444444444");
		log.info("01234567890123456789012345678901234567890123456789"
				+ "01234567890123456789012345678901234567890123456789"
				+ "01234567890123456789012345678901234567890123456789");
	}

	public void logTracks(final int maxX, final int maxY, final Track tracks[][]) {
		final StringBuffer sb = new StringBuffer();
		sb.append("tracks:");
		for (int y = 0; y < maxY; y++) {
			sb.append("\n\t");
			for (int x = 0; x < maxX; x++) {
				if (tracks[x][y] == null) {
					sb.append(' ');
				} else if (tracks[x][y].hasCart()) {
					sb.append(tracks[x][y].getCart().getDirection());
				} else {
					sb.append(tracks[x][y].getType());
				}
			}
		}
		log.info(sb.toString());
	}

	protected void moveAll() {
		while (carts > 1) {
			logCartLocations(carts, maxX, maxY, tick, tracks);
			if (logDuringTicks) {
				logTracks(maxX, maxY, tracks);
			}
			tick++;
			// log.info("Tick: " + tick);
			movedCarts.clear();
			for (int y = 0; y < maxY; y++) {
				for (int x = 0; x < maxX; x++) {
					final Track oldTrack = tracks[x][y];
					if (oldTrack == null || !oldTrack.hasCart()) {
						continue;
					}
					final Cart cart = oldTrack.getCart();
					if (movedCarts.contains(cart)) {
						continue;
					}
					movedCarts.add(cart);
					final int[] newLoc = getNewLocation(x, y, cart.getDirection());
					final Track newTrack = tracks[newLoc[0]][newLoc[1]];
					if (newTrack == null) {
						throw new IllegalStateException("No track @ " + newLoc[0] + "," + newLoc[1] + " !");
					}
					cart.updateDirection(newTrack);
					if (newTrack.hasCart()) {
						logCollision(newLoc, oldTrack, newTrack);
						collissions += "" + newLoc[0] + "," + newLoc[1] + "; ";
                        if (!firstCrashFound) {
							firstCrash = newLoc;
							firstCrashFound = true;
                        }
						// Clear the carts that collided
						oldTrack.clearCart();
						newTrack.clearCart();
						carts -= 2;
						if (carts < 2) {
							// break;
						}
					} else {
						oldTrack.clearCart();
						newTrack.setCart(cart);
						lastCart = newLoc;
					}
				}
				if (carts < 2) {
				//	break;
				}
			}
		}
		logTracks(maxX, maxY, tracks);
		logCartLocations(carts, maxX, maxY, tick, tracks);
		lastCart = getLastCart();
		log.info("Tick: " + tick);
		log.info("Collissions: " + collissions);
		log.info("First crash coordinates: " + firstCrash[0] + "," + firstCrash[1]);
		log.info("Last cart coordinates: " + lastCart[0] + "," + lastCart[1]);
	}

	private void logCollision(final int[] location, final Track oldTrack, final Track newTrack) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Collision: ");
		sb.append(logCartLocation(location[0], location[1], newTrack));
		sb.append("\t");
		sb.append(logCartLocation(location[0], location[1], oldTrack));
		log.warn(sb.toString());
	}

	// --- Day 13: Mine Cart Madness ---
	//
	// A crop of this size requires significant logistics to transport produce,
	// soil, fertilizer, and so on. The Elves are very busy pushing things around in
	// carts on some kind of rudimentary system of tracks they've come up with.
	//
	// Seeing as how cart-and-track systems don't appear in recorded history for
	// another 1000 years, the Elves seem to be making this up as they go along.
	// They haven't even figured out how to avoid collisions yet.
	//
	// You map out the tracks (your puzzle input) and see where you can help.
	//
	// Tracks consist of straight paths (| and -), curves (/ and \), and
	// intersections (+). Curves connect exactly two perpendicular pieces of track;
	// for example, this is a closed loop:
	//
	// /----\
	// |... |
	// |... |
	// \----/
	//
	// Intersections occur when two perpendicular paths cross. At an intersection, a
	// cart is capable of turning left, turning right, or continuing straight. Here
	// are two loops connected by two intersections:
	//
	// /-----\
	// |.... |
	// | ./--+--\
	// | .| .|..|
	// \--+--/. |
	// ...|.....|
	// ...\-----/
	// Several carts are also on the tracks. Carts always face either up (^), down
	// (v), left (<), or right (>). (On your initial map, the track under each cart
	// is a straight path matching the direction the cart is facing.)
	//
	// Each time a cart has the option to turn (by arriving at any intersection), it
	// turns left the first time, goes straight the second time, turns right the
	// third time, and then repeats those directions starting again with left the
	// fourth time, straight the fifth time, and so on. This process is independent
	// of the particular intersection at which the cart has arrived - that is, the
	// cart has no per-intersection memory.
	//
	// Carts all move at the same speed; they take turns moving a single step at a
	// time. They do this based on their current location: carts on the top row move
	// first (acting from left to right), then carts on the second row move (again
	// from left to right), then carts on the third row, and so on. Once each cart
	// has moved one step, the process repeats; each of these loops is called a
	// tick.
	//
	// For example, suppose there are two carts on a straight track:
	//
	// | | | | |
	// v | | | |
	// | v v | |
	// | | | v X
	// | | ^ ^ |
	// ^ ^ | | |
	// | | | | |
	// First, the top cart moves. It is facing down (v), so it moves down one
	// square. Second, the bottom cart moves. It is facing up (^), so it moves up
	// one square. Because all carts have moved, the first tick ends. Then, the
	// process repeats, starting with the first cart. The first cart moves down,
	// then the second cart moves up - right into the first cart, colliding with it!
	// (The location of the crash is marked with an X.) This ends the second and
	// last tick.
	//
	// Here is a longer example:
	//
	// /->-\
	// | | /----\
	// | /-+--+-\ |
	// | | | | v |
	// \-+-/ \-+--/
	// \------/
	//
	// /-->\
	// | | /----\
	// | /-+--+-\ |
	// | | | | | |
	// \-+-/ \->--/
	// \------/
	//
	// /---v
	// | | /----\
	// | /-+--+-\ |
	// | | | | | |
	// \-+-/ \-+>-/
	// \------/
	//
	// /---\
	// | v /----\
	// | /-+--+-\ |
	// | | | | | |
	// \-+-/ \-+->/
	// \------/
	//
	// /---\
	// | | /----\
	// | /->--+-\ |
	// | | | | | |
	// \-+-/ \-+--^
	// \------/
	//
	// /---\
	// | | /----\
	// | /-+>-+-\ |
	// | | | | | ^
	// \-+-/ \-+--/
	// \------/
	//
	// /---\
	// | | /----\
	// | /-+->+-\ ^
	// | | | | | |
	// \-+-/ \-+--/
	// \------/
	//
	// /---\
	// | | /----<
	// | /-+-->-\ |
	// | | | | | |
	// \-+-/ \-+--/
	// \------/
	//
	// /---\
	// | | /---<\
	// | /-+--+>\ |
	// | | | | | |
	// \-+-/ \-+--/
	// \------/
	//
	// /---\
	// | | /--<-\
	// | /-+--+-v |
	// | | | | | |
	// \-+-/ \-+--/
	// \------/
	//
	// /---\
	// | | /-<--\
	// | /-+--+-\ |
	// | | | | v |
	// \-+-/ \-+--/
	// \------/
	//
	// /---\
	// | | /<---\
	// | /-+--+-\ |
	// | | | | | |
	// \-+-/ \-<--/
	// \------/
	//
	// /---\
	// | | v----\
	// | /-+--+-\ |
	// | | | | | |
	// \-+-/ \<+--/
	// \------/
	//
	// /---\
	// | | /----\
	// | /-+--v-\ |
	// | | | | | |
	// \-+-/ ^-+--/
	// \------/
	//
	// /---\
	// | | /----\
	// | /-+--+-\ |
	// | | | X | |
	// \-+-/ \-+--/
	// \------/
	//
	// After following their respective paths for a while, the carts eventually
	// crash. To help prevent crashes, you'd like to know the location of the first
	// crash. Locations are given in X,Y coordinates, where the farthest left column
	// is X=0 and the farthest top row is Y=0:
	//
	// ...........111
	// .0123456789012
	// 0/---\
	// 1| ..| ./----\
	// 2| /-+--+-\. |
	// 3| | | .X |. |
	// 4\-+-/ .\-+--/
	// 5 .\------/
	//
	// In this example, the location of the first crash is 7,3.

	@Override
	public String part1() {
		init(getLines());
		moveAll();
		logTracks(maxX, maxY, tracks);
		return firstCrash[0] + "," + firstCrash[1];
	}

	// --- Part Two ---
	//
	// There isn't much you can do to prevent crashes in this ridiculous system.
	// However, by predicting the crashes, the Elves know where to be in advance and
	// instantly remove the two crashing carts the moment any crash occurs.
	//
	// They can proceed like this for a while, but eventually, they're going to run
	// out of carts. It could be useful to figure out where the last cart that
	// hasn't crashed will end up.
	//
	// For example:
	//
	// />-<\
	// | . |
	// | /<+-\
	// | | | v
	// \>+</ |
	// . | . ^
	// . \<->/
	//
	// /---\
	// | . |
	// | v-+-\
	// | | | |
	// \-+-/ |
	// . | . |
	// . ^---^
	//
	// /---\
	// | . |
	// | /-+-\
	// | v | |
	// \-+-/ |
	// . ^ . ^
	// . \---/
	//
	// /---\
	// | . |
	// | /-+-\
	// | | | |
	// \-+-/ ^
	// . | . |
	// . \---/
	//
	// After four very expensive crashes, a tick ends with only one cart remaining;
	// its final location is 6,4.
	//
	// What is the location of the last cart at the end of the first tick where it
	// is the only cart left?

	@Override
	public String part2() {
		init(getLines());
		moveAll();
		return lastCart[0] + "," + lastCart[1];
	}

}
