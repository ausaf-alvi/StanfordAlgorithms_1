package homework_Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberOfInversions {

	static long splitInversions;
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int arraySize = 100000;
		int array[] = new int[arraySize];
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
		{
//			String input[] = obj.readLine().split("\n");
//			for(int i=0 ; i<input.length ; i++) {
//				array[i] = Integer.parseInt(input[i]);
//			}
//			long ans= 2407905288l;
//			System.out.println("answer: 2407905288="+ans);
			for(int i=0 ; i<arraySize; i++) {
				try{
					array[i] = Integer.parseInt(obj.readLine());
				} catch (java.lang.NumberFormatException e) {
					System.out.println("Error at nos:"+i);
					e.printStackTrace();
				}
			}
		}
		mergeSort(array , 0 , array.length-1);
		System.out.println("Inversions = "+splitInversions);
//		System.out.println();
//		for(int a:array) System.out.print(a+",");
	}
	
	public static void mergeSort(int arr[] ,long l , long r) {
		if(l<r) {
			long m=(l+r)/2;
			
			mergeSort(arr , l , m);
			mergeSort(arr , m+((long)1.0) , r);
			merge(arr , l , m , r);
		}
	}
	public static void merge(int arr[] , long l , long m , long r) {
		
		//Sizes of two subarrays to be merged = n1 , n2
		long n1 = m - l + 1;
		long n2 = r - m;
		
		//Create two subarrays
		int L[] = new int[(int) n1];
		int R[] = new int[(int) n2];
		for(long i=0 ; i<n1 ; ++i) 
			L[(int) i] = arr[(int) (l + i)];
		for(long j=0 ; j<n2 ; ++j)
			R[(int) j] = arr[(int) (m + 1.0 + j)];
		
		//Merging of both the arrays
		long i=0 ;long j =0 ;//initial indexes of both the arrays
		long k = l; //	k=L(lower limit of aray passed in argument)
		while(i<n1 && j<n2) {
			if(L[(int) i]<R[(int) j]) {
				arr[(int) k] = L[(int) i];
				i++;
			} else {
				arr[(int) k] = R[(int) j];
				j++;
				splitInversions = splitInversions + (long)((m+1.0)-(l+i));
			}
			k++;
		}
		while(i<n1) {
			arr[(int) k] = L[(int) i];
			i++;
			k++;
		}
		while(j<n2) {
			arr[(int) k] = R[(int) j];
			k++;
			j++;
		}
		
	}

}
