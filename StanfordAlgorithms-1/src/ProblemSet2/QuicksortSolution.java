package ProblemSet2;
//QuickSort using all three types of Partition subroutine mentioned in the course
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class QuicksortSolution {

	static int arr[];
	static int numberOfComparisons;

	public static void main(String[] args) throws IOException {

		numberOfComparisons = 0;
		QuicksortSolution qs = new QuicksortSolution();
		File txt;
		String address = "";
		StringBuilder sb=new StringBuilder();
		if (args != null && args.length != 0 && args[0] != null && !args[0].isEmpty()) {
			address = args[0];
			txt = new File(address);
		} else {
			System.err.println("args != null : " + (args != null));
			System.err.println("args.length != 0 : " + (args.length != 0));
			System.err.println("args[0] != null : " + (args[0] != null));
			System.err.println("!args[0].isEmpty() : " + (!args[0].isEmpty()));
			address = "QuickSort2.txt";
			txt = new File(address);
		}
		if (!txt.isFile()) {
			System.err.println("File Address is wrong");
			System.err.println(address);
			System.err.println(txt.getPath());
			System.exit(0);
		}

		Scanner scan = new Scanner(txt);

		numberOfComparisons = 0;
		ArrayList<String> al = new ArrayList<String>();
		while (scan.hasNext()) {
			al.add(scan.next().trim());
		}
		scan.close();
		arr = new int[al.size()];
		
		
		//First run with pivot as first element
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(al.get(i));
		}
		qs.Quicksort_first(arr, 0, arr.length - 1);
//		System.out.print(numberOfComparisons+"~~");
//		sb.append("\n");
		sb.append(numberOfComparisons);
//		sb.append("---");
		
		
		
		//Second run with pivot as last element
		numberOfComparisons=0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(al.get(i));
		}
		qs.Quicksort_last(arr, 0, arr.length - 1);
//		System.out.print(numberOfComparisons+"~~");
		sb.append("\r\n");
		sb.append(numberOfComparisons);
//		sb.append("---");
		
		
		
		//Third run with pivot as Median of three element
		numberOfComparisons=0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(al.get(i));
		}
		qs.Quicksort_median(arr, 0, arr.length - 1);
//		System.out.print(numberOfComparisons);
		sb.append("\r\n");
		sb.append(numberOfComparisons);
//		sb.append("---");
		System.out.print(sb);
	}

	void Quicksort_median(int arr[], int l, int r) {
		if (l >= r)
			return;
		int pivot_position = choosePivot_asMedianOfThree_PivotRule(arr, l, r);
		numberOfComparisons = numberOfComparisons + (r - l);
		pivot_position = partition(arr, l, r, pivot_position);//  new position of pivot
		Quicksort_median(arr, l, pivot_position - 1);
		Quicksort_median(arr, pivot_position + 1, r);
	}

	void Quicksort_first(int arr[], int l, int r) {
		if (l >= r)
			return;
		int pivot_position = choosePivot_asFirstElement(arr, l, r);
		numberOfComparisons = numberOfComparisons + (r - l);
		pivot_position = partition(arr, l, r, pivot_position);//  new position of pivot
		Quicksort_first(arr, l, pivot_position - 1);
		Quicksort_first(arr, pivot_position + 1, r);
	}

	void Quicksort_last(int arr[], int l, int r) {
		if (l >= r)
			return;
		int pivot_position = choosePivot_asLastElement(arr, l, r);
		numberOfComparisons = numberOfComparisons + (r - l);
		pivot_position = partition(arr, l, r, pivot_position);//  new position of pivot
		Quicksort_last(arr, l, pivot_position - 1);
		Quicksort_last(arr, pivot_position + 1, r);
	}

	int partition(int arr[], int l, int r, int pivot_position) {
//		First, exchange pivot element and the first element
		int temp;
		if (pivot_position != l) {
			temp = arr[pivot_position];
			arr[pivot_position] = arr[l];
			arr[l] = temp;
		}

		int pivot = arr[l]; // Since, the pivot has been placed at first position
		int i = l + 1;
		for (int j = l + 1; j <= r; j++) {
			if (arr[j] < pivot) {
//				 swap arr[j] and arr[i]
				temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
//				increment i
				i = i + 1;
			}
		}
//		swap arr[l] and arr[i-1]
		temp = arr[l];
		arr[l] = arr[i - 1];
		arr[i - 1] = temp;
		return i - 1;// new pivot_position
	}

	int choosePivot_asFirstElement(int arr[], int l, int r) {
		return l;
	}

	int choosePivot_asLastElement(int arr[], int l, int r) {
		return r;
	}

	int choosePivot_asMedianOfThree_PivotRule(int arr[], int l, int r) {
		int a, b, c;
		a = arr[l];
		b = arr[r];

		c = arr[l + (r - l) / 2];
//			checking for b
		if ((a < b && b < c) || (c < b && b < a))
// 	            return b; 
			return r;

//	        Checking for a 
		else if ((b < a && a < c) || (c < a && a < b))
// 	        return a; 
			return l;

		else
// 	        return c;
			return ((l + (r - l) / 2));
	}
}
