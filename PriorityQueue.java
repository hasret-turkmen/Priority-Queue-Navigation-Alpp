import java.util.Scanner;


//Helper class to store coordinates
class Coordinates {
	int x, y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	// A method to calculate distances between starting point and any given point
	double distance(Coordinates b) {
		double dist;
		dist = Math.sqrt((b.y - 0) * (b.y - 0) + (b.x - 0) * (b.x - 0));
		return dist;

	}
}
//A CLASS FOR PRIORITY QUEUE
public class PriorityQueue {
	static double[] heap = new double[20];
	static int lastIndex;
	static int size = -1;


	// A METHOD TO FIND PARENT NODES
	static int parent(int index) {
		if (index == 1)
			return 0;
		return index / 2;
	}

	//CONSIDERING THE ROOT IS AT heap[0]
	// A METHOD TO FIND LEFT CHILDREN
	static int left(int index) {
		return ((2 * index) + 1);
	}

	// A METHOD TO FIND RIGHT CHILDREN
	static int right(int index) {
		return ((2 * index) + 2);
	}

	// INSERTS AN ELEMENT TO HEAP
	static void add(double p) {
		size = size + 1;
		heap[size] = p;
		swimUp(size);
	}

	// DELETES THE MAX ELEMENT
	static double removeMax() {
		double toBeRemoved = heap[0];
		heap[0] = heap[size];
		size = size - 1;
		swimDown(0);
		return toBeRemoved;
	}
//SHIFTS THE ELEMENTS SO THAT HEAP IS MAINTAINED
	static void swimUp(int i) {
		  while (i > 0 && heap[parent(i)] < heap[i]) {
		  swap(parent(i), i);
		 i = parent(i); }
	}
	
//SHIFTS THE ELEMENTS SO THAT HEAP IS MAINTAINED
	static void swimDown(int i) {
	int max = i;
		int l = left(i);
		if (l <= size && heap[l] > heap[max]) {
			max = l;
		}
		int r = right(i);
		if (r <= size && heap[r] > heap[max]) {
			max = r;
		}
		if (i != max) {
			swap(i, max);
			swimDown(max);
		}
	}

	//SWAPS THE ELEMENTS IN TWO GIVEN INDEXES
	static void swap(int i1, int i2) {
		double temp = heap[i1];
		heap[i1] = heap[i2];
		heap[i2] = temp;
	}


//MAIN METHOD
	public static void main(String[] args) {

		Scanner bili = new Scanner(System.in);

   int userX=0; int userY=0;
		// Creates a location for starting point
		Coordinates start = new Coordinates(0, 0);

		String input = bili.nextLine();

		// Removes paranthesis so that I can use the coordinates as int
		String input1 = input.replace("[", "").replace("]", "").replace(" ", "");

		// Splits the string when there is comma so that they are only integers now
		String[] split = input1.split(",");
		
//--------------------------------------------------------
 // Summary: COUNTS THE NUMBER OF COORDINATES AND CREATES AN ARRAY WITH IT
 //--------------------------------------------------------
	int c=0;
		if(split.length<=2) {
			c=1;
		}
		else {
		while(c<(split.length/2))
			c++;
		}
		
		// 2D array to store the coordinates
		int[][] has = new int[c][2];

		// location array to store the coordinates as locations
		Coordinates[] arr = new Coordinates[has.length];
		int j = 1;
		for (int a = 0; a < has.length; a++) {
			// Turns the string into integers
			has[a][0] = Integer.parseInt(split[j - 1]);
			has[a][1] = Integer.parseInt(split[j]);
			// Creates locations at every coordinate
			arr[a] = new Coordinates(has[a][0], has[a][1]);
			j++;
			j++;
		}

		// an array to store the distances
		double distances[] = new double[has.length];

		//System.out.println("How many locations do you want to see?");
		int number = bili.nextInt();

		// Updates the distances while calculating every coordinate with 0,0
		for (int i = 0; i < distances.length; i++) {
			distances[i] = start.distance(arr[i]);
				//ADDS THE DISTANCES TO THE HEAP
				add(distances[i]);
		}
//I CHOSE TO ADD THE DISTANCES TO HEAP INSTEAD OF COORDINATES
//IF I HAD USED LINKEDLIST IN MY PRIORTY QUEUE, 
//I'D STORE THE COORDINATES


		// A location array to store the wanted number of closest locations
		Coordinates[] results = new Coordinates[number];

//--------------------------------------------------------
 // Summary: REMOVES THE UNNECESSARY ELEMENTS CONSIDERING
 //THE GIVEN NUMBER
 //--------------------------------------------------------
		while(c-number>0) {
			removeMax();
			c--;
		}

//--------------------------------------------------------
 // Summary: PRINTS THE VALUES IN ORDER
 //--------------------------------------------------------
		if(number==1) {
			System.out.println("[["+userX + "," + userY + "]]");
		}
		else {
			if(arr.length-number!=0) {
		for (int i = 0; i < number; i++) {
			for (int k = 0; k <=number; k++) {
				// if the first n sorted distance is equal to the calculated distance
				// add it to the results array
				if (heap[i] == start.distance(arr[k])) {
					results[i] = arr[k];
				}
			}
		}}
			else if(arr.length==number) {
				for (int i = 0; i < number; i++) {
					for (int k = 0; k <number; k++) {
						// if the first n sorted distance is equal to the calculated distance
						// add it to the results array
						if (heap[i] ==start.distance(arr[k])) {
							results[i] = arr[k];
						}
					}
				}
			}
		System.out.print("[");
		for (int i = number-1; i >0; i--) {
			System.out.print("["+results[i].x + "," + results[i].y + "], ");
		}
		System.out.print( "["+ results[0].x + "," + results[0].y + "]]");
		
		}
		}
		}
	

