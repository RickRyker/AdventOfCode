package com.rykerstudios.advent.year2020;

import com.rykerstudios.advent.Day;

public class Day11 extends Day<Integer> {

	public Day11() {
		super(2338, 2134); // part two too high
	}

	/**
	 * --- Day 11: Seating System ---
	 * 
	 * Your plane lands with plenty of time to spare. The final leg of your journey
	 * is a ferry that goes directly to the tropical island where you can finally
	 * start your vacation. As you reach the waiting area to board the ferry, you
	 * realize you're so early, nobody else has even arrived yet!
	 * 
	 * By modeling the process people use to choose (or abandon) their seat in the
	 * waiting area, you're pretty sure you can predict the best place to sit. You
	 * make a quick map of the seat layout (your puzzle input).
	 * 
	 * The seat layout fits neatly on a grid. Each position is either floor (.), an
	 * empty seat (L), or an occupied seat (#). For example, the initial seat layout
	 * might look like this:
	 * 
	 * L.LL.LL.LL <br/>
	 * LLLLLLL.LL <br/>
	 * L.L.L..L.. <br/>
	 * LLLL.LL.LL <br/>
	 * L.LL.LL.LL <br/>
	 * L.LLLLL.LL <br/>
	 * ..L.L..... <br/>
	 * LLLLLLLLLL <br/>
	 * L.LLLLLL.L <br/>
	 * L.LLLLL.LL <br/>
	 * 
	 * Now, you just need to model the people who will be arriving shortly.
	 * Fortunately, people are entirely predictable and always follow a simple set
	 * of rules. All decisions are based on the number of occupied seats adjacent to
	 * a given seat (one of the eight positions immediately up, down, left, right,
	 * or diagonal from the seat). The following rules are applied to every seat
	 * simultaneously:
	 * 
	 * If a seat is empty (L) and there are no occupied seats adjacent to it, the
	 * seat becomes occupied. <br/>
	 * If a seat is occupied (#) and four or more seats adjacent to it are also
	 * occupied, the seat becomes empty. <br/>
	 * Otherwise, the seat's state does not change. <br/>
	 * Floor (.) never changes; seats don't move, and nobody sits on the floor.
	 * <br/>
	 * 
	 * After one round of these rules, every seat in the example layout becomes
	 * occupied:
	 * 
	 * #.##.##.## <br/>
	 * #######.## <br/>
	 * #.#.#..#.. <br/>
	 * ####.##.## <br/>
	 * #.##.##.## <br/>
	 * #.#####.## <br/>
	 * ..#.#..... <br/>
	 * ########## <br/>
	 * #.######.# <br/>
	 * #.#####.## <br/>
	 * 
	 * After a second round, the seats with four or more occupied adjacent seats
	 * become empty again:
	 * 
	 * #.LL.L#.## <br/>
	 * #LLLLLL.L# <br/>
	 * L.L.L..L.. <br/>
	 * #LLL.LL.L# <br/>
	 * #.LL.LL.LL <br/>
	 * #.LLLL#.## <br/>
	 * ..L.L..... <br/>
	 * #LLLLLLLL# <br/>
	 * #.LLLLLL.L <br/>
	 * #.#LLLL.## <br/>
	 * 
	 * This process continues for three more rounds:
	 * 
	 * #.##.L#.## <br/>
	 * #L###LL.L# <br/>
	 * L.#.#..#.. <br/>
	 * #L##.##.L# <br/>
	 * #.##.LL.LL <br/>
	 * #.###L#.## <br/>
	 * ..#.#..... <br/>
	 * #L######L# <br/>
	 * #.LL###L.L <br/>
	 * #.#L###.## <br/>
	 *
	 * #.#L.L#.## <br/>
	 * #LLL#LL.L# <br/>
	 * L.L.L..#.. <br/>
	 * #LLL.##.L# <br/>
	 * #.LL.LL.LL <br/>
	 * #.LL#L#.## <br/>
	 * ..L.L..... <br/>
	 * #L#LLLL#L# <br/>
	 * #.LLLLLL.L <br/>
	 * #.#L#L#.## <br/>
	 *
	 * #.#L.L#.## <br/>
	 * #LLL#LL.L# <br/>
	 * L.#.L..#.. <br/>
	 * #L##.##.L# <br/>
	 * #.#L.LL.LL <br/>
	 * #.#L#L#.## <br/>
	 * ..L.L..... <br/>
	 * #L#L##L#L# <br/>
	 * #.LLLLLL.L <br/>
	 * #.#L#L#.## <br/>
	 * 
	 * At this point, something interesting happens: the chaos stabilizes and
	 * further applications of these rules cause no seats to change state! Once
	 * people stop moving around, you count 37 occupied seats.
	 * 
	 * Simulate your seating area by applying the seating rules repeatedly until no
	 * seats change state. How many seats end up occupied?
	 */
	@Override
	public Integer part1() {
		final WaitingArea waitingArea = new WaitingArea(getLines(), 4);
		while (waitingArea.applySeatingRules(false)) {
			// System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		}
		System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		return waitingArea.getOccupiedSeats();
	}

	/**
	 * --- Part Two ---
	 * 
	 * As soon as people start to arrive, you realize your mistake. People don't
	 * just care about adjacent seats - they care about the first seat they can see
	 * in each of those eight directions!
	 * 
	 * Now, instead of considering just the eight immediately adjacent seats,
	 * consider the first seat in each of those eight directions. For example, the
	 * empty seat below would see eight occupied seats:
	 * 
	 * .......#. <br/>
	 * ...#..... <br/>
	 * .#....... <br/>
	 * ......... <br/>
	 * ..#L....# <br/>
	 * ....#.... <br/>
	 * ......... <br/>
	 * #........ <br/>
	 * ...#..... <br/>
	 * 
	 * The leftmost empty seat below would only see one empty seat, but cannot see
	 * any of the occupied ones:
	 * 
	 * ............. <br/>
	 * .L.L.#.#.#.#. <br/>
	 * ............. <br/>
	 * 
	 * The empty seat below would see no occupied seats:
	 * 
	 * .##.##. <br/>
	 * #.#.#.# <br/>
	 * ##...## <br/>
	 * ...L... <br/>
	 * ##...## <br/>
	 * #.#.#.# <br/>
	 * .##.##. <br/>
	 * 
	 * Also, people seem to be more tolerant than you expected: it now takes five or
	 * more visible occupied seats for an occupied seat to become empty (rather than
	 * four or more from the previous rules). The other rules still apply: empty
	 * seats that see no occupied seats become occupied, seats matching no rule
	 * don't change, and floor never changes.
	 * 
	 * Given the same starting layout as above, these new rules cause the seating
	 * area to shift around as follows:
	 * 
	 * L.LL.LL.LL <br/>
	 * LLLLLLL.LL <br/>
	 * L.L.L..L.. <br/>
	 * LLLL.LL.LL <br/>
	 * L.LL.LL.LL <br/>
	 * L.LLLLL.LL <br/>
	 * ..L.L..... <br/>
	 * LLLLLLLLLL <br/>
	 * L.LLLLLL.L <br/>
	 * L.LLLLL.LL <br/>
	 * 
	 * #.##.##.## <br/>
	 * #######.## <br/>
	 * #.#.#..#.. <br/>
	 * ####.##.## <br/>
	 * #.##.##.## <br/>
	 * #.#####.## <br/>
	 * ..#.#..... <br/>
	 * ########## <br/>
	 * #.######.# <br/>
	 * #.#####.## <br/>
	 * 
	 * #.LL.LL.L# <br/>
	 * #LLLLLL.LL <br/>
	 * L.L.L..L.. <br/>
	 * LLLL.LL.LL <br/>
	 * L.LL.LL.LL <br/>
	 * L.LLLLL.LL <br/>
	 * ..L.L..... <br/>
	 * LLLLLLLLL# <br/>
	 * #.LLLLLL.L <br/>
	 * #.LLLLL.L# <br/>
	 * 
	 * #.L#.##.L# <br/>
	 * #L#####.LL <br/>
	 * L.#.#..#.. <br/>
	 * ##L#.##.## <br/>
	 * #.##.#L.## <br/>
	 * #.#####.#L <br/>
	 * ..#.#..... <br/>
	 * LLL####LL# <br/>
	 * #.L#####.L <br/>
	 * #.L####.L# <br/>
	 * 
	 * #.L#.L#.L# <br/>
	 * #LLLLLL.LL <br/>
	 * L.L.L..#.. <br/>
	 * ##LL.LL.L# <br/>
	 * L.LL.LL.L# <br/>
	 * #.LLLLL.LL <br/>
	 * ..L.L..... <br/>
	 * LLLLLLLLL# <br/>
	 * #.LLLLL#.L <br/>
	 * #.L#LL#.L# <br/>
	 * 
	 * #.L#.L#.L# <br/>
	 * #LLLLLL.LL <br/>
	 * L.L.L..#.. <br/>
	 * ##L#.#L.L# <br/>
	 * L.L#.#L.L# <br/>
	 * #.L####.LL <br/>
	 * ..#.#..... <br/>
	 * LLL###LLL# <br/>
	 * #.LLLLL#.L <br/>
	 * #.L#LL#.L# <br/>
	 * 
	 * #.L#.L#.L# <br/>
	 * #LLLLLL.LL <br/>
	 * L.L.L..#.. <br/>
	 * ##L#.#L.L# <br/>
	 * L.L#.LL.L# <br/>
	 * #.LLLL#.LL <br/>
	 * ..#.L..... <br/>
	 * LLL###LLL# <br/>
	 * #.LLLLL#.L <br/>
	 * #.L#LL#.L# <br/>
	 * 
	 * Again, at this point, people stop shifting around and the seating area
	 * reaches equilibrium. Once this occurs, you count 26 occupied seats.
	 * 
	 * Given the new visibility method and the rule change for occupied seats
	 * becoming empty, once equilibrium is reached, how many seats end up occupied?
	 */
	@Override
	public Integer part2() {
		final WaitingArea waitingArea = new WaitingArea(getLines(), 5);
		while (waitingArea.applySeatingRules(true)) {
			// System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		}
		System.out.println("Occupied Seats: " + waitingArea.getOccupiedSeats());
		return waitingArea.getOccupiedSeats();
	}

}
