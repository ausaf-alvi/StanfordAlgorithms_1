package homework_Problems;
//test1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Homework1_ProgrammingProblem1 {	

	public static void main(String[] args)throws IOException {
		
		final int noOfIntegers = 100000;
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
		array_and_inversionsCount mainArray_and_Count; 
		{
			//inputting values
			
			int input[] = new int[noOfIntegers];
			int i=0;//int i=0
			for( ; i<noOfIntegers ; i++) {

				try{
					String inString = obj.readLine();
					if(!inString.isBlank())input[i] = Integer.parseInt(inString);
					else {System.out.println("error at line no:"+i);i++;}
//					input[i] = Integer.parseInt(obj.readLine());
				}
				catch(Exception e) {
					System.out.println("Error at line no : "+i);
					e.printStackTrace();
				}
			}
			mainArray_and_Count = new array_and_inversionsCount(input , 0);
		}
		mainArray_and_Count = sort_and_Count(mainArray_and_Count , mainArray_and_Count.arrayLength);
		System.out.println("Inversions = "+mainArray_and_Count.count);
//		System.out.println("Sorted Array :"+Arrays.toString( mainArray_and_Count.array ) );
		System.out.println("Arry length: "+mainArray_and_Count.arrayLength+" == "+mainArray_and_Count.array.length);
		for(int i=0 ; i<noOfIntegers ; i++) {
			if(mainArray_and_Count.array[i]!=(i+1)) {
				System.out.println("Wrong number at "+i+"\t No is : "+mainArray_and_Count.array[i]);
			}
		}
		System.out.println("Inversions = "+mainArray_and_Count.count);
		
		
		
	}
	
	public static array_and_inversionsCount sort_and_Count(array_and_inversionsCount A , int n) {
		if(n==1) new array_and_inversionsCount(A.array , 0);
		else {
			array_and_inversionsCount B = new array_and_inversionsCount( Arrays.copyOfRange(A.array, 0, (n+1)/2) , 0);
			array_and_inversionsCount C = new array_and_inversionsCount( Arrays.copyOfRange(A.array, (n+1)/2 , n) , 0);
			array_and_inversionsCount D = new array_and_inversionsCount();
			B = sort_and_Count(B, B.arrayLength);
			C = sort_and_Count(C, C.arrayLength);
			D = merge_and_CountSplitInv( B , C , n);
			double sum = ( (B.count + C.count + D.count) );
			array_and_inversionsCount finalArray = new array_and_inversionsCount(D.array , sum);
			return finalArray;
		}
		return A;
	}
	
	public static array_and_inversionsCount merge_and_CountSplitInv(array_and_inversionsCount B , array_and_inversionsCount C , int totalLength) {
		int tempArray[] = new int[totalLength];
		double count = 0;
		int i=0 , j=0 , k=0 ;
		for(; i<B.arrayLength && j<C.arrayLength ; k++) {
			if(B.array[i]<C.array[j]) {
				tempArray[k] = B.array[i];
				i++;
			} else {
				tempArray[k] = C.array[j];
				j++;
				double remainingItems = ( B.arrayLength - (i+1) );
				count = count + remainingItems;
//				System.out.println("Inversion counted for :  "+C.array[j-1]);
//				System.out.println("No of invesions added: "+remainingItems+"\n");
			}
		}
		for(; i<B.arrayLength ; i++,k++) {
			tempArray[k] = B.array[i];
		}
		for(; j<C.arrayLength ; j++,k++) {
			tempArray[k] = C.array[j];
		}
		array_and_inversionsCount temp = new array_and_inversionsCount(tempArray , count);
		return temp;
	}

}
class array_and_inversionsCount{
	int array[];
	double count;
	int arrayLength;
	public array_and_inversionsCount() {
		array = new int[0];
		count = 0;
		arrayLength = 0;
	}
	public array_and_inversionsCount( int a[] , double c) {
		array = a;
		count = c;
		arrayLength = a.length;
	}
	public int[] getArray() {
		return array;
	}
	public double getCount() {
		return count;
	}
	public void setArray(int[] array) {
		this.array = array;
		this.arrayLength = array.length;
	}
	public void setCount(double count) {
		this.count = count;
	}
}
