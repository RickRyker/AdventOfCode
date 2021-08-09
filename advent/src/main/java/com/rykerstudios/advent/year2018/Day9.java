package com.rykerstudios.advent.year2018;

import com.rykerstudios.advent.Day;

public class Day9 extends Day<Long> {

	int lastMarbleWorth = 0;
	int marbleNumber = 0;
	int currentPosition = 0;
	int currentPlayer = 0;
	int totalPlayers = 0;
	int totalMarbles = 0;
	int[] circle = null;
	int[] newCircle = null;
	long[] scores = null;

	public Day9() {
		super(416424L, 3498287922L);
	}

	protected long highScore() {
		long highest = 0;
		for (int i = 0; i <= totalPlayers; i++) {
			if (highest < scores[i]) {
				highest = scores[i];
			}
		}
		return highest;
	}

	protected void init(final int totalPlayers, final int totalMarbles) {
		this.totalPlayers = totalPlayers;
		this.totalMarbles = totalMarbles;
		this.marbleNumber = 0;
		this.currentPosition = 1;
		this.currentPlayer = 0;
		this.circle = new int[] { 0 };
		this.scores = new long[totalPlayers + 1]; // 1-based
		// logCircle();
	}

	protected void logCircle() {
		if (marbleNumber <= 0) {
			System.out.println("[-] (0)");
			return;
		}
		System.out.print("[" + currentPlayer + "] ");
		for (int i = 0; i < currentPosition; i++) {
			System.out.print(" " + circle[i]);
		}
		System.out.print(" (" + circle[currentPosition] + ")");
		for (int i = currentPosition + 1; i < circle.length; i++) {
			System.out.print(" " + circle[i]);
		}
		System.out.println("");
	}

	protected void move() {
		currentPlayer++;
		if (currentPlayer > totalPlayers) {
			currentPlayer -= totalPlayers;
		}
		marbleNumber++;
		if (marbleNumber % 23 == 0) {
			currentPosition -= 7;
			if (currentPosition < 0) {
				currentPosition += circle.length;
				// System.out.println(marbleNumber);
			}
			lastMarbleWorth = marbleNumber + circle[currentPosition];
			scores[currentPlayer] += lastMarbleWorth;
			System.out.println(new StringBuilder().append("Current player ").append(currentPlayer)
					.append(" adding marble ").append(marbleNumber).append(" worth ").append(lastMarbleWorth)
					.append(" for a new total of ").append(scores[currentPlayer]).toString());
			// create new circle w/o current position
			newCircle = new int[this.newCircle.length - 1];
			System.arraycopy(circle, 0, newCircle, 0, currentPosition);
			final int length = newCircle.length - currentPosition;
			if (length > 0) {
				System.arraycopy(circle, currentPosition + 1, newCircle, currentPosition, length);
			}
		} else {
			currentPosition += 2;
			while (currentPosition > circle.length) {
				currentPosition -= circle.length;
			}
			// create new circle inserting at current position
			newCircle = new int[circle.length + 1];
			System.arraycopy(circle, 0, newCircle, 0, currentPosition);
			newCircle[currentPosition] = marbleNumber;
			final int length = circle.length - currentPosition;
			if (length > 0) {
				System.arraycopy(circle, currentPosition, newCircle, currentPosition + 1, length);
			}
		}
		circle = newCircle;
		// logCircle();
	}

	// --- Day 9: Marble Mania ---

	// You talk to the Elves while you wait for your navigation system to
	// initialize. To pass the time, they introduce you to their favorite marble
	// game.
	//
	// The Elves play this game by taking turns arranging the marbles in a circle
	// according to very particular rules. The marbles are numbered starting with 0
	// and increasing by 1 until every marble has a number.
	//
	// First, the marble numbered 0 is placed in the circle. At this point, while it
	// contains only a single marble, it is still a circle: the marble is both
	// clockwise from itself and counter-clockwise from itself. This marble is
	// designated the current marble.
	//
	// Then, each Elf takes a turn placing the lowest-numbered remaining marble into
	// the circle between the marbles that are 1 and 2 marbles clockwise of the
	// current marble. (When the circle is large enough, this means that there is
	// one marble between the marble that was just placed and the current marble.)
	// The marble that was just placed then becomes the current marble.
	//
	// However, if the marble that is about to be placed has a number which is a
	// multiple of 23, something entirely different happens. First, the current
	// player keeps the marble they would have placed, adding it to their score. In
	// addition, the marble 7 marbles counter-clockwise from the current marble is
	// removed from the circle and also added to the current player's score. The
	// marble located immediately clockwise of the marble that was removed becomes
	// the new current marble.
	//
	// For example, suppose there are 9 players. After the marble with value 0 is
	// placed in the middle, each player (shown in square brackets) takes a turn.
	// The result of each of those turns would produce circles of marbles like this,
	// where clockwise is to the right and the resulting current marble is in
	// parentheses:
	//
	// [-] (0)
	// [1] 0 (1)
	// [2] 0 (2) 1
	// [3] 0 2 1 (3)
	// [4] 0 (4) 2 1 3
	// [5] 0 4 2 (5) 1 3
	// [6] 0 4 2 5 1 (6) 3
	// [7] 0 4 2 5 1 6 3 (7)
	// [8] 0 (8) 4 2 5 1 6 3 7
	// [9] 0 8 4 (9) 2 5 1 6 3 7
	// [1] 0 8 4 9 2(10) 5 1 6 3 7
	// [2] 0 8 4 9 2 10 5(11) 1 6 3 7
	// [3] 0 8 4 9 2 10 5 11 1(12) 6 3 7
	// [4] 0 8 4 9 2 10 5 11 1 12 6(13) 3 7
	// [5] 0 8 4 9 2 10 5 11 1 12 6 13 3(14) 7
	// [6] 0 8 4 9 2 10 5 11 1 12 6 13 3 14 7(15)
	// [7] 0(16) 8 4 9 2 10 5 11 1 12 6 13 3 14 7 15
	// [8] 0 16 8(17) 4 9 2 10 5 11 1 12 6 13 3 14 7 15
	// [9] 0 16 8 17 4(18) 9 2 10 5 11 1 12 6 13 3 14 7 15
	// [1] 0 16 8 17 4 18 9(19) 2 10 5 11 1 12 6 13 3 14 7 15
	// [2] 0 16 8 17 4 18 9 19 2(20)10 5 11 1 12 6 13 3 14 7 15
	// [3] 0 16 8 17 4 18 9 19 2 20 10(21) 5 11 1 12 6 13 3 14 7 15
	// [4] 0 16 8 17 4 18 9 19 2 20 10 21 5(22)11 1 12 6 13 3 14 7 15
	// [5] 0 16 8 17 4 18(19) 2 20 10 21 5 22 11 1 12 6 13 3 14 7 15
	// [6] 0 16 8 17 4 18 19 2(24)20 10 21 5 22 11 1 12 6 13 3 14 7 15
	// [7] 0 16 8 17 4 18 19 2 24 20(25)10 21 5 22 11 1 12 6 13 3 14 7 15
	//
	// The goal is to be the player with the highest score after the last marble is
	// used up. Assuming the example above ends after the marble numbered 25, the
	// winning score is 23+9=32 (because player 5 kept marble 23 and removed marble
	// 9, while no other player got any points in this very short example game).
	//
	// Here are a few more examples:
	//
	// 10 players; last marble is worth 1618 points: high score is 8317
	// 13 players; last marble is worth 7999 points: high score is 146373
	// 17 players; last marble is worth 1104 points: high score is 2764
	// 21 players; last marble is worth 6111 points: high score is 54718
	// 30 players; last marble is worth 5807 points: high score is 37305
	//
	// What is the winning Elf's score?

	// 413 players; last marble is worth 71082 points

	@Override
	public Long part1() {
		init(413, 71082);
		return play();
	}

	// --- Part Two ---
	//
	// Amused by the speed of your answer, the Elves are curious:
	//
	// What would the new winning Elf's score be if the number of the last marble
	// were 100 times larger?

	@Override
	public Long part2() {
		init(413, 71082 * 100);
		return play();
	}

	protected long play() {
		while (marbleNumber < totalMarbles) {
			move();
		}
		// logCircle();
		final long score = highScore();
		System.out.println(score);
		return score;
	}

}