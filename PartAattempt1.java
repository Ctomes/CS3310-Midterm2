import java.util.Random;
import java.lang.Math;
/*Tomes, Christopher 
 * 10/11/2020
 * This is my Midterm2 submission. This program initializes multiple sets of arrays of various predetermined sizes filled with random values between -100 and 100.
 * A number is randomly generated and then the individual arrays are searched for two values that add up to that number.
 * For the class submission I reduced the Set size to 1 and use of an array of size 100 for quicker running code. The Set size can be increased to generated a more representable avg-time but this will
 * negatively effect run-time 2 of the algorithms are O(n^2). For testing I used set sizes of 100 to 1000 to compile an avg.*/

public class PartAattempt1 {

	public static void main(String[] args) 
	{

		Random rand = new Random();
		int setOfArrays[] = {100}; //Set of Arrays to test algorithm on. reduced to just 100 for submittion.
		int sampleSpace = 1;		// put this to 1 for submition. was testing with 100->1000
		double avgTime[] = new double[sampleSpace]; //collection of timestamps to create an avg.
		int answer[] = new int[2];	//Where to store answers to algo.

		//Brute Force Method
		System.out.println("Presentation of Algorithm 1 (BruteForceMethod) for a (SORTED ARRAY): with a time O(n^2)");
		for(int i = 0; i < setOfArrays.length; i++)
		{
			for(int j = 0; j <sampleSpace; j++) {
			//create array of random vals
			int array[] = new int[setOfArrays[i]];
			randomfy(array);
			sort(array, 0, setOfArrays[i]-1); //Sort the array for part A.
			System.out.print("Collection 1:");
			printArr(array);
			
			int sum = rand.nextInt(400)-200; //generate random sum
			
			long start = 0;
			long end = 0;
			long duration = 0;
			start = System.nanoTime();//TimeStart.
			answer=calcSumA1(array, sum);
			end = System.nanoTime();//TimeStop.
			duration = end - start;
			
			System.out.println("Sum is: "+sum+", and values are: ("+ answer[0]+","+answer[1] + ") This took: " + duration + " nanoseconds to process.");
			
			avgTime[j]=(double)(duration/1000);//log for average
			duration = 0;
		}
			//System.out.println("The time to solve an array of size "+setOfArrays[i] + " is "+calcAVG(avgTime)+ "ms");
		
			
		}
		//Improved Method.
		System.out.println("Presentation of Improved Algorithm 2 for a (SORTED) array: with a time O(n)");
		for(int i = 0; i < setOfArrays.length; i++)
		{
			for(int j = 0; j <sampleSpace; j++) {
			//create array of random vals
			int array[] = new int[setOfArrays[i]];
			randomfy(array);
			sort(array, 0, setOfArrays[i]-1); //Sort the array for part A.
		
			System.out.print("Collection 2:");
			printArr(array);
			
			int sum = rand.nextInt(400)-200; //generate random sum
			
			long start = 0;
			long end = 0;
			long duration = 0;
			start = System.nanoTime();//TimeStart.
			answer=calcSumA2(array, sum);
			end = System.nanoTime();//TimeStop.
			duration = end - start;
			
			System.out.println("Sum is: "+sum+", and values are: ("+ answer[0]+","+answer[1] + ") This took: " + duration + " nanoseconds to process.");
			
			avgTime[j]=(double)(duration/1000);//log for average
			duration = 0;
		}
			//System.out.println("The time to solve an array of size "+setOfArrays[i] + " is "+calcAVG(avgTime)+ "ms");
		
			
		}
		System.out.println("Presentation of Algorithm 3 (BruteForce) Method for an (UNSORTED) array: with a time O(n^2)");
		for(int i = 0; i < setOfArrays.length; i++)
		{
			for(int j = 0; j <sampleSpace; j++) {
			//create array of random vals
			int array[] = new int[setOfArrays[i]];
			randomfy(array);
			
			System.out.print("Collection 3:");
			printArr(array);
			
			int sum = rand.nextInt(400)-200; //generate random sum
			
			long start = 0;
			long end = 0;
			long duration = 0;
			start = System.nanoTime();//TimeStart.
			answer=calcSumA1(array, sum);
			end = System.nanoTime();//TimeStop.
			duration = end - start;
			
			System.out.println("Sum is: "+sum+", and values are: ("+ answer[0]+","+answer[1] + ") This took: " + duration + " nanoseconds to process.");
			
			avgTime[j]=(double)(duration/1000);//log for average
			duration = 0;
		}
			//System.out.println("The time to solve an array of size "+setOfArrays[i] + " is "+calcAVG(avgTime)+ "ms");
		
			
		}
		System.out.println("Presentation of Improved Algorithm 4 (Hashing) Method for an (UNSORTED) array: with a time O(n)");
		for(int i = 0; i < setOfArrays.length; i++)
		{
			for(int j = 0; j <sampleSpace; j++) {
			//create array of random vals
			int array[] = new int[setOfArrays[i]];
			randomfy(array);

			System.out.print("Collection 4:");
			printArr(array);
			
			int sum = rand.nextInt(400)-200; //generate random sum
			
			long start = 0;
			long end = 0;
			long duration = 0;
			start = System.nanoTime();//TimeStart.
			answer=calcSumB1(array, sum);
			end = System.nanoTime();//TimeStop.
			duration = end - start;
			
			System.out.println("Sum is: "+sum+", and values are: ("+ answer[0]+","+answer[1] + ") This took: " + duration + " nanoseconds to process.");
			
			avgTime[j]=(double)(duration/1000);//log for average
			duration = 0;
		}
			//System.out.println("The time to solve an array of size "+setOfArrays[i] + " is "+calcAVG(avgTime)+ "ms");
		
			
		}
	}
	//Primary algorithms: A1 is used twice.
	static int[] calcSumA1(int arr[], int sum) {
		int outPut[] = {sum,sum};
		
		for(int i = 0;i<arr.length-1;i++) 
		{
			for(int j = i+1;j<arr.length;j++)
			{
				if((arr[i]+arr[j])==sum) 
				{
					outPut[0]=arr[i];
					outPut[1]=arr[j];
					return outPut;
				}
			}
		}
		return null;
	}
	static int[] calcSumB1(int arr[], int sum) {
		
		int outPut[] = {sum,sum};
		int neg_hash[] = new int[101];
		int pos_hash[] = new int[101];
		for(int i = 0;i<arr.length;i++)//mapHash
		{
			if(arr[i]<0) {
				neg_hash[100+arr[i]]+=1;
			}else {
				pos_hash[arr[i]]+=1;
			}
		}
		if(Math.abs(sum)<100) {
		
			if(sum > 0) {//handle case when Sum is Positive.
			int left = 100-sum;//negative lowerbound.
			int right= 100;//pos upperbound
			
			while(left>0) {
				if(neg_hash[left]>0){
					if(pos_hash[right]>0) {
						outPut[0]=right;
						outPut[1]=-left;
						return outPut;
					}
					}
					--left;
					--right;	
			}
			while(left<right) {
				if(pos_hash[left]>0) {
					if(pos_hash[right]>0) {
						outPut[0]=right;
						outPut[1]=left;
						return outPut;
					}
				}
				--right;
				++left;
			}		

			}else if(sum < 0){//sum is a negative value
				int right = 100;//negative lower bound
				int left= 100+sum;//pos upperbound
				while(left>0) {
					if(pos_hash[left]>0){
						if(neg_hash[right]>0) {
							outPut[0]=-right;
							outPut[1]=left;
							return outPut;
						}
					}
					--left;
					--right;
				}
				while(left<right) {
					if(neg_hash[left]>0) {
						if(neg_hash[right]>0) {
							outPut[0]=-right;
							outPut[1]=-left;
							return outPut;
						}
					}
					++left;
					--right;
				}
			}
			else {//sum == 0;
				int right = 100;
				int left  = 100;
				while(right>0) 
				{
					if(pos_hash[right]>0){
						if(neg_hash[left]>0) {
							outPut[0]=right;
							outPut[1]=-left;
							return outPut;
					}
					}
					--left;
					--right;
					
				}
			}
		}else {
			if(sum>0) {
				int left = sum - 100;
				int right = 100;
				while(left<right) 
				{
					if(pos_hash[right]>0){
						if(pos_hash[left]>0) {
							outPut[0]=left;
							outPut[1]=right;
							return outPut;
					}
					}
					++left;
					--right;
					
				}
			}else {//sum is -101 -> -200
				int left = Math.abs(sum)-100;
				int right = 100;
				while(left<right) 
				{
					if(neg_hash[right]>0){
						if(neg_hash[left]>0) {
							outPut[0]=-left;
							outPut[1]=-right;
							return outPut;
					}
					}
					++left;
					--right;
					
				}
						
			}
		}
		
		return null;
	}
	static int[] calcSumA2(int arr[], int sum) {
		int outPut[] = {sum,sum};
		int i=0;
		int j = arr.length-1;
		for(; i<j ; ) 
		{
				if((arr[i]+arr[j])==sum) 
				{
					outPut[0]=arr[i];
					outPut[1]=arr[j];
					return outPut;
					
				}else if((arr[i]+arr[j])>sum){
					--j;
				}else {
					++i;
			}
		}
		return null;
	}
	
	//helper funcs
	static double calcAVG(double arr[]) {
		double avg=0;
		for(int i=0;i<arr.length;i++) {
			avg +=arr[i];
		}
		return avg/arr.length;
	}
	static void printArr(int array[]) {
		for(int k = 0; k<array.length; ++k) {
			if(k%10==0) {
				System.out.println();
			}
			if(array[k]==-100) {
				System.out.print("["+array[k]+"]");
			}else if(array[k]<-9){
				System.out.print("[ "+array[k]+"]");
			}else if(array[k]<0){
				System.out.print("[  "+array[k]+"]");
			}else if(array[k]>9){
				System.out.print("[  "+array[k]+"]");
			}else{
				System.out.print("[   "+array[k]+"]");
			}
			
		}
		System.out.println();
		}
	static void randomfy(int arr[])//fills arr with radom vals between a preset min/max
		{
			int min = -100;
			int max = 100;
			Random rand = new Random();
			for(int i = 0; i < arr.length; i++)
			{
				arr[i] =rand.nextInt((max-min))+min;//generates a random val inbetween min and max.
			}
		}
	static void merge(int arr[], int l, int m, int r) 
	    { 
	        // Find sizes of two subarrays to be merged 
	        int n1 = m - l + 1; 
	        int n2 = r - m; 
	  
	        /* Create temp arrays */
	        int L[] = new int[n1]; 
	        int R[] = new int[n2]; 
	  
	        /*Copy data to temp arrays*/
	        for (int i = 0; i < n1; ++i) 
	            L[i] = arr[l + i]; 
	        for (int j = 0; j < n2; ++j) 
	            R[j] = arr[m + 1 + j]; 
	  
	        /* Merge the temp arrays */
	  
	        // Initial indexes of first and second subarrays 
	        int i = 0, j = 0; 
	  
	        // Initial index of merged subarry array 
	        int k = l; 
	        while (i < n1 && j < n2) { 
	            if (L[i] <= R[j]) { 
	                arr[k] = L[i]; 
	                i++; 
	            } 
	            else { 
	                arr[k] = R[j]; 
	                j++; 
	            } 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of L[] if any */
	        while (i < n1) { 
	            arr[k] = L[i]; 
	            i++; 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of R[] if any */
	        while (j < n2) { 
	            arr[k] = R[j]; 
	            j++; 
	            k++; 
	        } 
	    } 
	static void sort(int arr[], int l, int r) 
		{ 
		        if (l < r) { 
		            
		            int m = (l + r) / 2; 
		            sort(arr, l, m); 
		            sort(arr, m + 1, r); 
		            merge(arr, l, m, r); 
		        } 
		}
	}