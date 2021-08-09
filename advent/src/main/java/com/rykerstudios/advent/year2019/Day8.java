package com.rykerstudios.advent.year2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rykerstudios.advent.Day;

public class Day8 extends Day<Integer> {

	public Day8() {
		super(1905, 0);
	}

	// --- Day 8: Space Image Format ---

	// The Elves' spirits are lifted when they realize you have an opportunity
	// to reboot one of their Mars rovers, and so they are curious if you would
	// spend a brief sojourn on Mars. You land your ship near the rover.

	// When you reach the rover, you discover that it's already in the process 
	// of rebooting! It's just waiting for someone to enter a BIOS password.
	// The Elf responsible for the rover takes a picture of the password 
	// (your puzzle input) and sends it to you via the Digital Sending Network.

	// Unfortunately, images sent via the Digital Sending Network aren't 
	// encoded with any normal encoding; instead, they're encoded in a special
	// Space Image Format. None of the Elves seem to remember why this is the case.
	// They send you the instructions to decode it.

	// Images are sent as a series of digits that each represent the color of 
	// a single pixel. The digits fill each row of the image left-to-right,
	// then move downward to the next row, filling rows top-to-bottom until
	// every pixel of the image is filled.

	// Each image actually consists of a series of identically-sized layers 
	// that are filled in this way. So, the first digit corresponds to the 
	// top-left pixel of the first layer, the second digit corresponds to the
	// pixel to the right of that on the same layer, and so on until the last
	// digit, which corresponds to the bottom-right pixel of the last layer.

	// For example, given an image 3 pixels wide and 2 pixels tall, the image
	// data 123456789012 corresponds to the following image layers:

	// Layer 1: 123
	//          456

	// Layer 2: 789
	//          012

	// The image you received is 25 pixels wide and 6 pixels tall.

	// To make sure the image wasn't corrupted during transmission, the Elves
	// would like you to find the layer that contains the fewest 0 digits. 
	// On that layer, what is the number of 1 digits multiplied by the number
	// of 2 digits?

	@Override
	public Integer part1() {
		int fewestZeroDigits = Integer.MAX_VALUE;
		int result = 0;
		final String s = getContent().trim();
		System.out.println("s.length: " + s.length());
		System.out.println("layers: " + s.length() / (25 * 6));
		System.out.println("remainder: " + s.length() % (25 * 6));
		for (int i = 0; i < s.length(); i = i + 25 * 6) {
			final String layer = (i + 25 * 6 < s.length()) ? s.substring(i, i + 25 * 6) : s.substring(i);
			final char[] chars = layer.toCharArray();
			final Map<Character, Integer> map = new HashMap<Character, Integer>();
			for (char c : chars) {
				if (map.get(c) == null) {
					map.put(c, 0);
				}
				map.put(c, map.get(c) + 1);
			}
			if (fewestZeroDigits > map.get('0')) {
				fewestZeroDigits = map.get('0');
				result = map.get('1') * map.get('2');
				System.out.println("Map: " + map + "; result=" + result);
			}
			map.clear();
		}
		return result;
	}

	// --- Part Two ---

	// Now you're ready to decode the image. The image is rendered by stacking
	// the layers and aligning the pixels with the same positions in each layer.
	// The digits indicate the color of the corresponding pixel: 
	// 0 is black, 1 is white, and 2 is transparent.

	// The layers are rendered with the first layer in front and the last layer
	// in back. So, if a given position has a transparent pixel in the first
	// and second layers, a black pixel in the third layer, and a white pixel
	// in the fourth layer, the final image would have a black pixel at that
	// position.

	// For example, given an image 2 pixels wide and 2 pixels tall, the image
	// data 0222112222120000 corresponds to the following image layers:

	// Layer 1: 02
    //          22

	// Layer 2: 11
	//          22

	// Layer 3: 22
	//          12

	// Layer 4: 00
	//          00

	// Then, the full image can be found by determining the top visible pixel
	// in each position:

	// The top-left pixel is black because the top layer is 0.
	// The top-right pixel is white because the top layer is 2 (transparent),
	// but the second layer is 1.
	// The bottom-left pixel is white because the top two layers are 2, 
	// but the third layer is 1.
	// The bottom-right pixel is black because the only visible pixel in that
	// position is 0 (from layer 4).
	// So, the final image looks like this:

	// 01
	// 10

	// What message is produced after decoding your image?
			
	@Override
	public Integer part2() {
		final String s = getContent().trim();
		System.out.println("s.length: " + s.length());
		System.out.println("layers: " + s.length() / (25 * 6));
		System.out.println("remainder: " + s.length() % (25 * 6));
		final List<String> layers = new ArrayList<String>();
		for (int i = 0; i < s.length(); i = i + 25 * 6) {
			final String layer = (i + 25 * 6 < s.length()) ? s.substring(i, i + 25 * 6) : s.substring(i);
			layers.add(layer);
		}
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 150; i++) {
			char pixel = '2';
			for (final String layer : layers) {
				if (layer.charAt(i) == '2') {
					continue;
				}
				if (layer.charAt(i) == '1') {
					pixel = '#';
				}
				if (layer.charAt(i) == '0') {
					pixel = ' ';
				}
				break;
			}
			sb.append(pixel);
		}
		final String combined = sb.toString();
		System.out.println(combined.substring(0, 25));
		System.out.println(combined.substring(25, 50));
		System.out.println(combined.substring(50, 75));
		System.out.println(combined.substring(75, 100));
		System.out.println(combined.substring(100, 125));
		System.out.println(combined.substring(125, 150));
		return 0; // ACKPZ
	}

}
