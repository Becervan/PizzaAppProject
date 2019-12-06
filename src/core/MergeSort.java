package core;

import java.util.Arrays;

//Merge sort algorithm derived from: https://www.geeksforgeeks.org/merge-sort/

public class MergeSort {
	// Sorts an array using MergeSort
	public static void sort(Customer[] a) {
		if(a.length >= 2) {
			// splitting the array in "half"
			Customer[] left = Arrays.copyOfRange(a, 0, a.length/2);
			Customer[] right = Arrays.copyOfRange(a, a.length/2, a.length);
			
			// sort the two halves, and merge them into a sorted whole
			sort(left);
			sort(right);
			merge(left, right, a);
		}
	}
	
	private static Customer[] merge(Customer[] left, Customer[] right, Customer[] a) {
	  int iLeft = 0; // Next element to consider in the first array
	  int iRight = 0; // Next element to consider in the second array
	  int j = 0; // Next open position in a
	  
	  // As long as neither iLeft nor iRight is past the end, move the smaller element into a
	  while(iLeft < left.length && iRight < right.length) {
	     if(left[iLeft].compareTo(right[iRight]) < 0) a[j++] = left[iLeft++];
	     else a[j++] = right[iRight++];
	  }

	  // Note that only one of the two loops below copies entries
	  while(iLeft < left.length) a[j++] = left[iLeft++]; // Copy any remaining entries of the first array
	  while(iRight < right.length) a[j++] = right[iRight++]; // Copy any remaining entries of the second half
	  return a;
	}
}
