package com.rykerstudios.advent.year2020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.rykerstudios.advent.Day;

public class Day18 extends Day<BigDecimal> {

	public Day18() {
		super(new BigDecimal("1891140942724"), BigDecimal.ZERO);
		// part 1 value
		// 3976876092 is too low
		// 1891140942724 is too large
		// 2250244771900 is wrong
	}

	/**
	 * --- Day 18: Operation Order ---
	 * 
	 * As you look out the window and notice a heavily-forested continent slowly
	 * appear over the horizon, you are interrupted by the child sitting next to
	 * you. They're curious if you could help them with their math homework.
	 * 
	 * Unfortunately, it seems like this "math" follows different rules than you
	 * remember.
	 * 
	 * The homework (your puzzle input) consists of a series of expressions that
	 * consist of addition (+), multiplication (*), and parentheses ((...)). Just
	 * like normal math, parentheses indicate that the expression inside must be
	 * evaluated before it can be used by the surrounding expression. Addition still
	 * finds the sum of the numbers on both sides of the operator, and
	 * multiplication still finds the product.
	 * 
	 * However, the rules of operator precedence have changed. Rather than
	 * evaluating multiplication before addition, the operators have the same
	 * precedence, and are evaluated left-to-right regardless of the order in which
	 * they appear.
	 * 
	 * For example, the steps to evaluate the expression 1 + 2 * 3 + 4 * 5 + 6 are
	 * as follows:
	 * 
	 * 1 + 2 * 3 + 4 * 5 + 6 <br/>
	 * _ 3 _ * 3 + 4 * 5 + 6 <br/>
	 * _ _ _ 9 _ + 4 * 5 + 6 <br/>
	 * _ _ _ _ _13 _ * 5 + 6 <br/>
	 * _ _ _ _ _ _ _65 _ + 6 <br/>
	 * _ _ _ _ _ _ _ _ _71 <br/>
	 * 
	 * Parentheses can override this order; for example, here is what happens if
	 * parentheses are added to form 1 + (2 * 3) + (4 * (5 + 6)):
	 * 
	 * 1 + (2 * 3) + (4 * (5 + 6)) <br/>
	 * 1 + _ _6_ _ + (4 * (5 + 6)) <br/>
	 * _ _ _7_ _ _ + (4 * (5 + 6)) <br/>
	 * _ _ _7_ _ _ + (4 * _ 11 _ ) <br/>
	 * _ _ _7_ _ _ + _ _ 44 <br/>
	 * 51 <br/>
	 * 
	 * Here are a few more examples:
	 * 
	 * 2 * 3 + (4 * 5) becomes 26. <br/>
	 * 5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 437. <br/>
	 * 5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 12240. <br/>
	 * ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 13632. <br/>
	 * 
	 * Before you can help with the homework, you need to understand it yourself.
	 * Evaluate the expression on each line of the homework; what is the sum of the
	 * resulting values?
	 */
	@Override
	public BigDecimal part1() {
		System.out.println("----------------------------------------");
		BigDecimal sum = BigDecimal.ZERO;
		for (final String problem : getLines()) {
			final long answer = ShuntingYard.calculate(problem);
			final BigDecimal result = new BigDecimal(answer);
			sum = sum.add(result);
		}
		System.out.println("----------------------------------------");
		return sum;
	}

	@Override
	public BigDecimal part2() {
		ShuntingYard.part2 = true;
		System.out.println("----------------------------------------");
		BigDecimal sum = BigDecimal.ZERO;
		for (final String problem : getLines()) {
			final long answer = ShuntingYard.calculate(problem);
			final BigDecimal result = new BigDecimal(answer);
			sum = sum.add(result);
		}
		System.out.println("----------------------------------------");
		return sum;
	}

	public static class ShuntingYard {

		static boolean part2 = false;

		public static long calculate(final String input) {
			// All operators and values are single characters
			// so strip spaces and split on individual characters
			final List<String> rpn = parse(input.replace(" ", "").split("|"));
			final long result = evaluate(rpn);
			System.out.println("Problem: " + input + " > " + rpn + " = " + result);
			return result;
		}

		public static long evaluate(final List<String> tokens) {
			final Stack<String> stack = new Stack<String>();
			for (final String t : tokens) {
				// we only do + and * operators in this parser
				if (!"*+".contains(t)) {
					stack.push(t);
				} else {
					final long a = Long.valueOf(stack.pop());
					final long b = Long.valueOf(stack.pop());
					if ("+".equals(t)) {
						stack.push(String.valueOf(a + b));
					} else if ("*".equals(t)) {
						stack.push(String.valueOf(a * b));
					}
				}
			}
			return Long.valueOf(stack.pop());
		}

		private static boolean isFunction(final String token) {
			// we don't do functions in this parser
			return false;
		}

		private static boolean isHigherOrEqualPrecedence(final String stackToken, final String token) {
			if (part2) {
				// how does it change in part 2?
			}
			// always true in part 1
			return true;
		}

		private static boolean isLeftParenthesis(final String token) {
			return "(".equals(token);
		}

		private static boolean isNumeric(final String token) {
			return token.matches("^[0-9]*$");
		}

		private static boolean isOperator(final String token) {
			// we only do + and * in this parser
			return "+".equals(token) || "*".equals(token);
		}

		private static boolean isRightParenthesis(final String token) {
			return ")".equals(token);
		}

//		public static List<String> parse(final String input) {
//			final String[] tokens = input.split("|");
//			return parse(tokens);
//		}

		public static List<String> parse(final String[] tokens) {
			final List<String> outputQueue = new ArrayList<String>();
			final Stack<String> operatorStack = new Stack<String>();

			// while there are tokens to be read:
			for (final String token : tokens) {
				// read a token.
				if (isNumeric(token)) {
					// if the token is a number, then:
					// push it to the output queue.
					outputQueue.add(token);
				} else
				if (isFunction(token)) {
					// else if the token is a function then:
					// push it onto the operator stack 
					operatorStack.push(token);
				} else
				if (isOperator(token)) {
					// else if the token is an operator then:
		            // while ((there is an operator at the top of the operator stack)
			        //   and ((the operator at the top of the operator stack has greater precedence)
			        //     or (the operator at the top of the operator stack has equal precedence and the token is left associative))
			        //   and (the operator at the top of the operator stack is not a left parenthesis)):
					while (!operatorStack.isEmpty() &&
							isHigherOrEqualPrecedence(operatorStack.peek(), token) &&
							!isLeftParenthesis(operatorStack.peek())) {
						// pop operators from the operator stack onto the output queue.
						outputQueue.add(operatorStack.pop());
					}
		            // push it onto the operator stack.
					operatorStack.push(token);
				} else if (isLeftParenthesis(token)) {
				    // else if the token is a left parenthesis (i.e. "("), then:
			        // push it onto the operator stack.
					operatorStack.push(token);
				} else if (isRightParenthesis(token)) {
					// else if the token is a right parenthesis (i.e. ")"), then:
					// while the operator at the top of the operator stack is not a left parenthesis:
					while (!operatorStack.isEmpty() && !isLeftParenthesis(operatorStack.peek())) {
						// pop the operator from the operator stack onto the output queue.
						outputQueue.add(operatorStack.pop());
						/*
						 * If the stack runs out without finding a left parenthesis, then there are
						 * mismatched parentheses.
						 */
						if (!operatorStack.isEmpty() && isLeftParenthesis(operatorStack.peek())) {
							// if there is a left parenthesis at the top of the operator stack, then:
							// pop the operator from the operator stack and discard it
							operatorStack.pop();
						}
						if (!operatorStack.isEmpty() && isFunction(operatorStack.peek())) {
							// if there is a function token at the top of the operator stack, then:
							// pop the function from the operator stack onto the output queue.
							operatorStack.pop();
						}
					}
				}
			}
			/* After while loop, if operator stack not null, pop everything to output queue */
			// if there are no more tokens to read then:
			// while there are still operator tokens on the stack:
			while (!operatorStack.isEmpty()) {
		        /* If the operator token on the top of the stack is a parenthesis, then there are mismatched parentheses. */
				// pop the operator from the operator stack onto the output queue.
				outputQueue.add(operatorStack.pop());
			}
			// This output queue is now in Reverse Polish Notation
			return outputQueue;
		}

	}

}
