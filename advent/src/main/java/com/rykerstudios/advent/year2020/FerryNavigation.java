package com.rykerstudios.advent.year2020;

public class FerryNavigation {

	public class MovementInstruction {
		String action;
		int value;

		MovementInstruction(final String line) {
			action = line.substring(0, 1);
			value = Integer.parseInt(line.substring(1, line.length()));
		}
	}

	private boolean useWaypoint;

	private char facing = 'E';

	private long ewLocation = 0;
	private long nsLocation = 0;

	private long ewWaypoint = 10;
	private long nsWaypoint = 1;

	private MovementInstruction[] instructions;

	public FerryNavigation(final String[] lines, final boolean useWaypoint) {
		this.useWaypoint = useWaypoint;
		instructions = new MovementInstruction[lines.length];
		int i = 0;
		for (final String line : lines) {
			if (line.length() > 0) {
				instructions[i] = new MovementInstruction(line);
				i++;
			}
		}
	}

	public long getDistanceFromOrigin() {
		return Math.abs(getEWLocation()) + Math.abs(getNSLocation());
	}

	public char getFacing() {
		return facing;
	}

	public long getEWLocation() {
		return ewLocation;
	}

	public long getEWWaypoint() {
		return ewWaypoint;
	}

	public long getNSLocation() {
		return nsLocation;
	}

	public long getNSWaypoint() {
		return nsWaypoint;
	}

	public void move(final String action, final int value) {
		switch (action) {
		case "N":
			nsLocation += value;
			break;
		case "S":
			nsLocation -= value;
			break;
		case "E":
			ewLocation += value;
			break;
		case "W":
			ewLocation -= value;
			break;
		case "F":
			if ('E' == facing) {
				ewLocation += value;
			} else if ('N' == facing) {
				nsLocation += value;
			} else if ('W' == facing) {
				ewLocation -= value;
			} else if ('S' == facing) {
				nsLocation -= value;
			}
			break;
		case "R":
			if ('E' == facing && value == 90) {
				facing = 'S';
			} else if ('E' == facing && value == 180) {
				facing = 'W';
			} else if ('E' == facing && value == 270) {
				facing = 'N';
			} else if ('N' == facing && value == 90) {
				facing = 'E';
			} else if ('N' == facing && value == 180) {
				facing = 'S';
			} else if ('N' == facing && value == 270) {
				facing = 'W';
			} else if ('W' == facing && value == 90) {
				facing = 'N';
			} else if ('W' == facing && value == 180) {
				facing = 'E';
			} else if ('W' == facing && value == 270) {
				facing = 'S';
			} else if ('S' == facing && value == 90) {
				facing = 'W';
			} else if ('S' == facing && value == 180) {
				facing = 'N';
			} else if ('S' == facing && value == 270) {
				facing = 'E';
			} else {
				throw new IllegalStateException("Unknown value for R action " + value);
			}
			break;
		case "L":
			if ('E' == facing && value == 90) {
				facing = 'N';
			} else if ('E' == facing && value == 180) {
				facing = 'W';
			} else if ('E' == facing && value == 270) {
				facing = 'S';
			} else if ('N' == facing && value == 90) {
				facing = 'W';
			} else if ('N' == facing && value == 180) {
				facing = 'S';
			} else if ('N' == facing && value == 270) {
				facing = 'E';
			} else if ('W' == facing && value == 90) {
				facing = 'S';
			} else if ('W' == facing && value == 180) {
				facing = 'E';
			} else if ('W' == facing && value == 270) {
				facing = 'N';
			} else if ('S' == facing && value == 90) {
				facing = 'E';
			} else if ('S' == facing && value == 180) {
				facing = 'N';
			} else if ('S' == facing && value == 270) {
				facing = 'W';
			} else {
				throw new IllegalStateException("Unknown value for L action " + value);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown movement instruction action.");
		}
	}

	public void moveAll() {
		reset();
		for (final MovementInstruction instruction : instructions) {
			if (useWaypoint) {
				moveWaypoint(instruction.action, instruction.value);
			} else {
				move(instruction.action, instruction.value);
			}
		}
	}

	public void moveWaypoint(final String action, final int value) {
		switch (action) {
		case "N":
			nsWaypoint += value;
			break;
		case "S":
			nsWaypoint -= value;
			break;
		case "E":
			ewWaypoint += value;
			break;
		case "W":
			ewWaypoint -= value;
			break;
		case "F":
			ewLocation += ewWaypoint * value;
			nsLocation += nsWaypoint * value;
			break;
		case "R":
			if (value == 90) {
				final long temp = ewWaypoint;
				ewWaypoint = nsWaypoint;
				nsWaypoint = -temp;
			} else if (value == 180) {
				ewWaypoint = -ewWaypoint;
				nsWaypoint = -nsWaypoint;
			} else if (value == 270) {
				final long temp = ewWaypoint;
				ewWaypoint = -nsWaypoint;
				nsWaypoint = temp;
			} else {
				throw new IllegalStateException("Unknown value for R action " + value);
			}
			break;
		case "L":
			if (value == 90) {
				final long temp = ewWaypoint;
				ewWaypoint = -nsWaypoint;
				nsWaypoint = temp;
			} else if (value == 180) {
				ewWaypoint = -ewWaypoint;
				nsWaypoint = -nsWaypoint;
			} else if (value == 270) {
				final long temp = ewWaypoint;
				ewWaypoint = nsWaypoint;
				nsWaypoint = -temp;
			} else {
				throw new IllegalStateException("Unknown value for L action " + value);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown movement instruction action.");
		}
	}

	public void reset() {
		ewLocation = 0;
		nsLocation = 0;
		facing = 'E';
		ewWaypoint = 10;
		nsWaypoint = 1;
	}

}
