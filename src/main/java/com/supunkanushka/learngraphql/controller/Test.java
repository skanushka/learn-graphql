package com.supunkanushka.learngraphql.controller;

import java.util.Arrays;

public class Test {

	/*
	1 2 4 3 <-- original
	1 2 3 4 <-- desired

	1 2 4 3 <-- NO

	1 2 4 X <-- NO
	X 1 2 4 <-- NO

	1 2 X X <-- YES
	*/

	private static int min(int[] original, int[] desired) {
		return doJob(original, original, desired, 0);
	}

	public static void main(String[] args) {

		int[] original = {1, 2, 4, 3};
		int[] desired = {1, 2, 3, 4};

		int min = min(original, desired);
		System.out.println(min);

	}

	private static int doJob(int[] arr, int[] original, int[] desired, int chunks) {

		System.out.println("Processing arr " + Arrays.toString(arr) + " against original " + Arrays.toString(original) + " with chunks "+ chunks);
		if (arr.length == 0) {
			return chunks;
		}

		boolean allMatches = true;
		for (int i = 0; (i + arr.length) <= desired.length; i++) {
			allMatches = true;
			for (int x = 0; x < arr.length; x++) {
				System.out.println("Checking desired " + desired[i + x] + " against arr " + arr[x]);
				if (desired[i + x] != arr[x]) {
					allMatches = false;
					break;
				}
			}
			if (allMatches) {
				chunks++;
				System.out.println("All matched when " + Arrays.toString(arr) + " new original " + Arrays.toString(original));

				int[] nextOriginal = new int[original.length - arr.length];

				int addedIndex = 0;
				for (int j : original) {
					boolean matched = false;
					for (int k : arr) {
						if (j == k) {
							matched = true;
							break;
						}
					}
					if (!matched) {
						nextOriginal[addedIndex] = j;
						addedIndex++;
					}
				}

				int[] nextChunks = new int[nextOriginal.length - arr.length];
				for (int k = (arr.length - 1); k < (nextOriginal.length - 1); k++) {
					nextChunks[k - (arr.length - 1)] = nextOriginal[k + 1];
				}
				System.out.println("Calling again after match with " + Arrays.toString(nextChunks) + " chunks so far : " + chunks);
				chunks = doJob(nextChunks, nextOriginal, desired, chunks);
			}
		}
		System.out.println();

		if (!allMatches) {
			int[] nextChunks = new int[arr.length - 1];
			for (int i = 0; i < arr.length - 1; i++) {
				nextChunks[i] = arr[i];
			}
			System.out.println("Calling again with " + Arrays.toString(nextChunks) + " new original " + Arrays.toString(original));
			chunks = doJob(nextChunks, original, desired, chunks);
		}

		return chunks;
	}
}
