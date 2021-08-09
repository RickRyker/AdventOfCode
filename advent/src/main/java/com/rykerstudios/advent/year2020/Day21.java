package com.rykerstudios.advent.year2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.rykerstudios.advent.Day;

public class Day21 extends Day<Integer> {

	public Day21() {
		super(2635, 2635);
	}

	/**
	 * --- Day 21: Allergen Assessment ---
	 * 
	 * You reach the train's last stop and the closest you can get to your vacation
	 * island without getting wet. There aren't even any boats here, but nothing can
	 * stop you now: you build a raft. You just need a few days' worth of food for
	 * your journey.
	 * 
	 * You don't speak the local language, so you can't read any ingredients lists.
	 * However, sometimes, allergens are listed in a language you do understand. You
	 * should be able to use this information to determine which ingredient contains
	 * which allergen and work out which foods are safe to take with you on your
	 * trip.
	 * 
	 * You start by compiling a list of foods (your puzzle input), one food per
	 * line. Each line includes that food's ingredients list followed by some or all
	 * of the allergens the food contains.
	 * 
	 * Each allergen is found in exactly one ingredient. Each ingredient contains
	 * zero or one allergen. Allergens aren't always marked; when they're listed (as
	 * in (contains nuts, shellfish) after an ingredients list), the ingredient that
	 * contains each listed allergen will be somewhere in the corresponding
	 * ingredients list. However, even if an allergen isn't listed, the ingredient
	 * that contains that allergen could still be present: maybe they forgot to
	 * label it, or maybe it was labeled in a language you don't know.
	 * 
	 * For example, consider the following list of foods:
	 * 
	 * mxmxvkd kfcds sqjhc nhms (contains dairy, fish) <br/>
	 * trh fvjkl sbzzf mxmxvkd (contains dairy) <br/>
	 * sqjhc fvjkl (contains soy) <br/>
	 * sqjhc mxmxvkd sbzzf (contains fish)
	 * 
	 * The first food in the list has four ingredients (written in a language you
	 * don't understand): mxmxvkd, kfcds, sqjhc, and nhms. While the food might
	 * contain other allergens, a few allergens the food definitely contains are
	 * listed afterward: dairy and fish.
	 * 
	 * The first step is to determine which ingredients can't possibly contain any
	 * of the allergens in any food in your list. In the above example, none of the
	 * ingredients kfcds, nhms, sbzzf, or trh can contain an allergen. Counting the
	 * number of times any of these ingredients appear in any ingredients list
	 * produces 5: they all appear once each except sbzzf, which appears twice.
	 * 
	 * Determine which ingredients cannot possibly contain any of the allergens in
	 * your list. How many times do any of those ingredients appear?
	 */
	@Override
	public Integer part1() {
		final AllergenAssessment assessment = new AllergenAssessment(getLines());
		return assessment.getSafeIngredients().size();
	}

	/**
	 * --- Part Two ---
	 * 
	 * Now that you've isolated the inert ingredients, you should have enough
	 * information to figure out which ingredient contains which allergen.
	 * 
	 * In the above example:
	 * 
	 * mxmxvkd contains dairy. <br/>
	 * sqjhc contains fish. <br/>
	 * fvjkl contains soy. <br/>
	 * 
	 * Arrange the ingredients alphabetically by their allergen and separate them by
	 * commas to produce your canonical dangerous ingredient list. (There should not
	 * be any spaces in your canonical dangerous ingredient list.) In the above
	 * example, this would be mxmxvkd,sqjhc,fvjkl.
	 * 
	 * Time to stock your raft with supplies. What is your canonical dangerous
	 * ingredient list?
	 */
	@Override
	public Integer part2() {
		final AllergenAssessment assessment = new AllergenAssessment(getLines());
		return assessment.getSafeIngredients().size();
	}

	public static class AllergenAssessment {

		List<Food> foodItems = new ArrayList<>();
		Map<String, Set<String>> allergenPotentialIngredients = new HashMap<>();
		Map<String, String> allergenIngredients = new HashMap<>();
		List<String> safeIngredients = new ArrayList<>(); // may contain duplicates

		public AllergenAssessment(final String[] lines) {
			for (final String line : lines) {
				this.foodItems.add(new Food(line));
			}
			// System.out.println(foodItems);
		}

		public void processFoodList() {
			for (final Food food : foodItems) {
				for (final String allergen : food.allergens) {
					if (allergenIngredients.containsKey(allergen)) {
						continue;
					}
					if (!allergenPotentialIngredients.containsKey(allergen)) {
						allergenPotentialIngredients.put(allergen, new HashSet<>(food.ingredients));
					} else {
						final Set<String> ingredients = allergenPotentialIngredients.get(allergen);
						ingredients.retainAll(food.ingredients);
						removeKnownAllergensFrom(ingredients);
						if (ingredients.size() == 1) {
							allergenIngredients.put(allergen, ingredients.iterator().next());
						}
					}
				}
			}
			while (allergenIngredients.size() != allergenPotentialIngredients.size()) {
				for (final String allergen : allergenPotentialIngredients.keySet()) {
					final Set<String> ingredients = allergenPotentialIngredients.get(allergen);
					removeKnownAllergensFrom(ingredients);
					if (ingredients.size() == 1) {
						allergenIngredients.put(allergen, ingredients.iterator().next());
					}
				}
			}
		}

		public List<String> findSafeIngredients() {
			processFoodList();
			final List<String> safeIngredients = new ArrayList<>();
			for (final Food food : foodItems) {
				for (final String ingredient : food.ingredients) {
					if (!allergenIngredients.containsValue(ingredient)) {
						safeIngredients.add(ingredient);
					}
				}
			}
			return safeIngredients;
		}

		private void removeKnownAllergensFrom(final Set<String> ingredients) {
			for (final String ingredient : allergenIngredients.values()) {
				ingredients.remove(ingredient);
			}
		}

		public List<String> getSafeIngredients() {
			if (safeIngredients.isEmpty()) {
				safeIngredients.addAll(findSafeIngredients());
			}
			return safeIngredients;
		}

		public String getCanonicalDangerousIngredients() {
			processFoodList();
			final StringBuilder sb = new StringBuilder();
			final Set<String> allergens = new TreeSet<>(allergenIngredients.keySet());
			for (final String allergen : allergens) {
				sb.append(allergenIngredients.get(allergen)).append(",");
			}
			sb.setLength(sb.length() - 1);
			return sb.toString();
		}

	}

	public static class Food {

		Set<String> ingredients;
		Set<String> allergens;

		public Food(final String line) {
			final int beginIndex = line.indexOf("(");
			final int endIndex = line.indexOf(")");
			this.ingredients = new HashSet<>(Arrays.asList(line.substring(0, beginIndex).split(" ")));
			this.allergens = new HashSet<>(
					Arrays.asList(line.substring(beginIndex + 1, endIndex).replace("contains ", "").split(", ")));
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("ingredients:").append(ingredients);
			sb.append(" allergens:").append(allergens);
			return sb.toString();
		}

	}

}
