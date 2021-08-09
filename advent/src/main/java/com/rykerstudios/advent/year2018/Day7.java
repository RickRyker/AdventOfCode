package com.rykerstudios.advent.year2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day7 extends Day<String> {

	final private Map<String, List<String>> dependencies = new HashMap<String, List<String>>();

	public Day7() {
		super("AEMNPOJWISZCDFUKBXQTHVLGRY", "");
	}

	protected Map<String, List<String>> getDependencies() {
		return dependencies;
	}

	protected String getOrderedSteps(final Map<String, List<String>> dependencies) {
		final StringBuilder sb = new StringBuilder();
		final List<String> steps = new ArrayList<String>(dependencies.keySet());
		Collections.sort(steps);
		while (!steps.isEmpty()) {
			final Iterator<String> it = steps.iterator();
			while (it.hasNext()) {
				final String step = it.next();
				boolean prereqsRemain = false;
				final List<String> pList = dependencies.get(step);
				for (final String prereq : pList) {
					if (sb.indexOf(prereq) > -1) {
						continue;
					}
					prereqsRemain = true;
					break;
				}
				if (prereqsRemain) {
					continue;
				}
				sb.append(step);
				it.remove();
				break;
			}
		}
		return sb.toString();
	}

	protected void loadDependencies(final String[] lines) {
		for (final String s : lines) {
			// "Step A must be finished before step N can begin."
			final String a = s.substring(5, 6);
			final String b = s.substring(36, 37);
			if (dependencies.get(a) == null) {
				dependencies.put(a, new ArrayList<String>());
			}
			if (dependencies.get(b) == null) {
				dependencies.put(b, new ArrayList<String>());
			}
			if (!dependencies.get(b).contains(a)) {
				dependencies.get(b).add(a);
			}
		}
		System.out.println(dependencies);
	}

	// --- Day 7: The Sum of Its Parts ---
	//
	// You find yourself standing on a snow-covered coastline; apparently, you
	// landed a little off course. The region is too hilly to see the North Pole
	// from here, but you do spot some Elves that seem to be trying to unpack
	// something that washed ashore. It's quite cold out, so you decide to risk
	// creating a paradox by asking them for directions.
	//
	// "Oh, are you the search party?" Somehow, you can understand whatever Elves
	// from the year 1018 speak; you assume it's Ancient Nordic Elvish. Could the
	// device on your wrist also be a translator? "Those clothes don't look very
	// warm; take this." They hand you a heavy coat.
	//
	// "We do need to find our way back to the North Pole, but we have higher
	// priorities at the moment. You see, believe it or not, this box contains
	// something that will solve all of Santa's transportation problems - at least,
	// that's what it looks like from the pictures in the instructions." It doesn't
	// seem like they can read whatever language it's in, but you can: "Sleigh kit.
	// Some assembly required."
	//
	// "'Sleigh'? What a wonderful name! You must help us assemble this 'sleigh' at
	// once!" They start excitedly pulling more parts out of the box.
	//
	// The instructions specify a series of steps and requirements about which steps
	// must be finished before others can begin (your puzzle input). Each step is
	// designated by a single letter. For example, suppose you have the following
	// instructions:
	//
	// Step C must be finished before step A can begin.
	// Step C must be finished before step F can begin.
	// Step A must be finished before step B can begin.
	// Step A must be finished before step D can begin.
	// Step B must be finished before step E can begin.
	// Step D must be finished before step E can begin.
	// Step F must be finished before step E can begin.
	// Visually, these requirements look like this:
	//
	// ..-->A--->B--
	// ./....\......\
	// C......-->D----->E
	// .\.........../
	// ..---->F-----
	//
	// Your first goal is to determine the order in which the steps should be
	// completed. If more than one step is ready, choose the step which is first
	// alphabetically. In this example, the steps would be completed as follows:
	//
	// Only C is available, and so it is done first.
	// Next, both A and F are available. A is first alphabetically, so it is done
	// next.
	// Then, even though F was available earlier, steps B and D are now also
	// available, and B is the first alphabetically of the three.
	// After that, only D and F are available. E is not available because only some
	// of its prerequisites are complete. Therefore, D is completed next.
	// F is the only choice, so it is done next.
	// Finally, E is completed.
	// So, in this example, the correct order is CABDFE.
	//
	// In what order should the steps in your instructions be completed?

	@Override
	public String part1() {
		loadDependencies(getLines());
		final String steps = getOrderedSteps(dependencies);
		System.out.println(steps);
		return steps;
	}

	// --- Part Two ---
	//
	// As you're about to begin construction, four of the Elves offer to help. "The
	// sun will set soon; it'll go faster if we work together." Now, you need to
	// account for multiple people working on steps simultaneously. If multiple
	// steps are available, workers should still begin them in alphabetical order.
	//
	// Each step takes 60 seconds plus an amount corresponding to its letter: A=1,
	// B=2, C=3, and so on. So, step A takes 60+1=61 seconds, while step Z takes
	// 60+26=86 seconds. No time is required between steps.
	//
	// To simplify things for the example, however, suppose you only have help from
	// one Elf (a total of two workers) and that each step takes 60 fewer seconds
	// (so that step A takes 1 second and step Z takes 26 seconds). Then, using the
	// same instructions as above, this is how each second would be spent:
	//
	//			Second   Worker 1   Worker 2   Done
	//			   0        C          .        
	//			   1        C          .        
	//			   2        C          .        
	//			   3        A          F       C
	//			   4        B          F       CA
	//			   5        B          F       CA
	//			   6        D          F       CAB
	//			   7        D          F       CAB
	//			   8        D          F       CAB
	//			   9        D          .       CABF
	//			  10        E          .       CABFD
	//			  11        E          .       CABFD
	//			  12        E          .       CABFD
	//			  13        E          .       CABFD
	//			  14        E          .       CABFD
	//			  15        .          .       CABFDE
	//
	// Each row represents one second of time. The Second column identifies how many
	// seconds have passed as of the beginning of that second. Each worker column
	// shows the step that worker is currently doing (or . if they are idle). The
	// Done column shows completed steps.
	//
	// Note that the order of the steps has changed; this is because steps now take
	// time to finish and multiple workers can begin multiple steps simultaneously.
	//
	// In this example, it would take 15 seconds for two workers to complete these
	// steps.
	//
	// With 5 workers and the 60+ second step durations described above, how long
	// will it take to complete all of the steps?

	@Override
	public String part2() {
		loadDependencies(getLines());
		createWorkTree(getDependencies());
		return "";
	}

	protected int createWorkTree(final Map<String, List<String>> dependencies) {
		final StringBuilder sb = new StringBuilder();
		int t = 0;
		final String[] workerStep = new String[] {"A","E","M","P",""};
		final int[] workerEnd = new int[] { 61, 65, 73, 76, -1 };
		sb.append("A").append("E").append("M").append("");
		t = 61;
		workerStep[0] = "N"; // @ t=61
		workerEnd[0] = 135; // (61+74) @ t=61
		sb.append("N");

		final List<String> steps = new ArrayList<String>(dependencies.keySet());
		Collections.sort(steps);
		while (!steps.isEmpty()) {
			final Iterator<String> it = steps.iterator();
			while (it.hasNext()) {
				final String step = it.next();
				boolean prereqsRemain = false;
				final List<String> pList = dependencies.get(step);
				for (final String prereq : pList) {
					if (sb.indexOf(prereq) > -1) {
						continue;
					}
					prereqsRemain = true;
					break;
				}
				if (prereqsRemain) {
					continue;
				}
				// found next step to perform
				sb.append(step);
				it.remove();
				// find first idle worker
				// put step in worker and calc worker end time
				// then advance time to next end time and clear work from that worker
				break;
			}
		}
		return t;
	}

}
