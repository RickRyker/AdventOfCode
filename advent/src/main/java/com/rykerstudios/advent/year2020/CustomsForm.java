package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CustomsForm {

	private boolean set = false;
	private Set<Character> answers = new HashSet<Character>();
	private Set<Character> allSame = new HashSet<Character>();

	public CustomsForm() {
	}

	public void parseFormData(final String line) {
		final Set<Character> current = new HashSet<Character>();
		for (final char c : line.toCharArray()) {
			current.add(c);
		}
		answers.addAll(current);
		if (set) {
			final Iterator<Character> it = allSame.iterator();
			while (it.hasNext()) {
				final Character c = it.next();
				if (!current.contains(c)) {
					it.remove();
				}
			}
		} else {
			allSame.addAll(answers);
			set = true;
		}
	}

	public int getAnswersCount() {
		return answers.size();
	}

	public int getAllSameCount() {
		return allSame.size();
	}

	public static List<CustomsForm> parse(final String[] lines) {
		final List<CustomsForm> forms = new ArrayList<>();
		CustomsForm form = null;
		for (final String line : lines) {
			if (line.isEmpty()) {
				form = null;
				continue;
			}
			if (form == null) {
				form = new CustomsForm();
				forms.add(form);
			}
			form.parseFormData(line);
		}
		return forms;
	}

}
