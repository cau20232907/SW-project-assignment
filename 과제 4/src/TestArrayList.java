import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestArrayList {
	public static void main1(String[] args) {
		MyArrayList ArrayList1 = new MyArrayList<>();
		printArrayInfo(ArrayList1);
		
		int a=2;
		double b=3.14159265358979323846;
		ArrayList1.add("firstObject");
		ArrayList1.add(a);
		printArrayInfo(ArrayList1);
		
		ArrayList1.add(0,b);
		ArrayList1.add(2,"SecondObject");
		ArrayList1.add(LocalDateTime.parse("2024-04-30 22:02:03:345",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
		printArrayInfo(ArrayList1);
		
		try {
			ArrayList1.add(-1, "underObject");
			System.out.println("Succeed to add Object at -1");
			printArrayInfo(ArrayList1);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding at -1: "+e.getMessage());
		}
		
		try {
			System.out.println("Object "+-1+": "+ArrayList1.get(-1)+
					", its Class: "+ArrayList1.get(-1).getClass());
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception getting Object at -1: "+e.getMessage());
		}
		
		try {
			ArrayList1.add("moreObject");
			System.out.println("Succeed to add 6th Object");
			printArrayInfo(ArrayList1);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding more than 5: "+e.getMessage());
		}
		
		System.out.println("remove: "+ArrayList1.remove(0));
		printArrayInfo(ArrayList1);
		
		try {
			ArrayList1.add(-1, "underObject");
			System.out.println("Succeed to add Object at -1");
			printArrayInfo(ArrayList1);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding at -1: "+e.getMessage());
		}
		
		System.out.println("remove: "+ArrayList1.remove(1));
		printArrayInfo(ArrayList1);
		
		try {
			System.out.println("Object "+4+": "+ArrayList1.get(4)+
					", its Class: "+ArrayList1.get(4).getClass());
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception getting fourth: "+e.getMessage());
		}
		
		try {
			ArrayList1.add(5,b);
			System.out.println("Succeed to add at 5");
			printArrayInfo(ArrayList1);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding at 5: "+e.getMessage());
		}
	}
	
	private static void printArrayInfo(MyArrayList array) {
		int size=array.size();
		System.out.println("size: "+size);
		System.out.println("isEmpty: "+array.isEmpty());
		for(int i=0;i<size;i++) {
			if(array.get(i)!=null) {
				System.out.println("Object "+i+": "+array.get(i)+
						", its Class: "+array.get(i).getClass());
			} else {
				System.out.println("Object "+i+" is null");
			}
		}
		System.out.println();
	}
	
	public static void main2(String[] args) {
		MyArrayList ArrayList1 = new MyArrayList();
		printArrayInfo(ArrayList1);
		
		int a=2;
		double b=3.14159265358979323846;
		ArrayList1.add("firstObject");
		ArrayList1.add(a);
		ArrayList1.add(0,b);
		ArrayList1.add(2,"SecondObject");
		ArrayList1.add(LocalDateTime.now());
		printArrayInfo(ArrayList1);
		
		try {
			ArrayList1.add(-1, "underObject");
			System.out.println("Succeed to add Object at -1");
			printArrayInfo(ArrayList1);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding at -1: "+e.getMessage());
		}
		
		ArrayList1.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		ArrayList1.add(5,LocalDateTime.parse("2024-05-02 23:35:09.872",
				(DateTimeFormatter) ArrayList1.get(5)));
		ArrayList1.add(ArrayList1);

		System.out.println("Result printing Array: "+ArrayList1);
		printArrayInfo(ArrayList1);
		
		try {
			ArrayList1.add("moreObject");
			System.out.println("Succeed to add 9th Object");
			printArrayInfo(ArrayList1);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding more than 8: "+e.getMessage());
		}
		
		System.out.println("remove: "+ArrayList1.remove(0));
		printArrayInfo(ArrayList1);
		
		try {
			ArrayList1.remove(-1);
			System.out.println("Succeed to remove Object at -1");
			printArrayInfo(ArrayList1);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception removing at -1: "+e.getMessage());
		}

		System.out.println("remove: "+ArrayList1.remove(1));
		System.out.println("remove: "+ArrayList1.remove(4));
		ArrayList1.add(ArrayList1.size()-1,ArrayList1.isEmpty());
		printArrayInfo(ArrayList1);
		
		try {
			System.out.println("Object "+9+": "+ArrayList1.get(9)+
					", its Class: "+ArrayList1.get(9).getClass());
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception getting nineth: "+e.getMessage());
		}
		
		try {
			ArrayList1.add(5,b);
			System.out.println("Succeed to add at 5");
			printArrayInfo(ArrayList1);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding at 5: "+e.getMessage());
		}

		
		try {
			ArrayList1.add(ArrayList1.size()-1,"moreObject");
			System.out.println("Succeed to add more Object");
			printArrayInfo(ArrayList1);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception adding more: "+e.getMessage());
		}
	}
	
	public static void main3(String[]args) {
		MyArrayList<Integer> intArray=new MyArrayList<>();
		MyArrayList<Double> doubleArray=new MyArrayList<>();
		MyArrayList<String> stringArray=new MyArrayList<>();
		MyArrayList<LocalDateTime> datetimeArray=new MyArrayList<>();
		MyArrayList<Boolean> boolArray=new MyArrayList<>();
		MyArrayList<MyArrayList> arrayArray=new MyArrayList<>();
		
		DateTimeFormatter dateTimeFormat=
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		
		//intArray.add("firstObject");
		intArray.add((int)0);
		//doubleArray.add(1);
		doubleArray.add((double)1.0);
		//stringArray.add(1);
		stringArray.add(doubleArray.get(0).toString());
		//datetimeArray.add(dateTimeFormat);
		datetimeArray.add(LocalDateTime.now());
		//boolArray.add(1);
		boolArray.add(boolArray.isEmpty());
		//arrayArray.add(new Object[5]);
		arrayArray.add(arrayArray);

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);
		
		intArray.add((Integer)1);
		doubleArray.add((Double) 0.0);
		stringArray.add(0,"firstObject");
		datetimeArray.add(LocalDateTime.parse("2024-05-03 11:12:26.665", dateTimeFormat));
		boolArray.add(boolArray.isEmpty());
		arrayArray.add(intArray);

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);
		
		intArray.add(intArray.size());
		doubleArray.add(Math.PI);
		//stringArray.add(arrayArray);
		stringArray.add(arrayArray.toString());
		datetimeArray.add(1, LocalDateTime.parse("2024-05-02 11:16:52.225", dateTimeFormat));
		boolArray.add(true);
		arrayArray.add(doubleArray);

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);
		
		intArray.add(datetimeArray.get(2).getDayOfMonth());
		doubleArray.add(Math.E);
		stringArray.add("2024-05-05 17:23:36.549");
		datetimeArray.add(LocalDateTime.parse(stringArray.get(stringArray.size()-1), dateTimeFormat));
		boolArray.add(doubleArray.get(2).equals(doubleArray.get(3)));
		arrayArray.add(stringArray);

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);

		intArray.add(0,null);
		doubleArray.add(0,null);
		stringArray.add(0,null);
		datetimeArray.add(0,null);
		boolArray.add(0,null);
		arrayArray.add(datetimeArray);

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);
		
		stringArray.add(datetimeArray.get(1).format(dateTimeFormat));
		datetimeArray.add(LocalDateTime.now());
		boolArray.add(datetimeArray.get(1).isBefore(datetimeArray.get(3)));
		arrayArray.add(boolArray);

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);

		System.out.println("remove int: "+intArray.remove(0));
		System.out.println("remove double: "+doubleArray.remove(4));
		System.out.println("remove string: "+stringArray.remove(3));
		System.out.println("remove datetime: "+datetimeArray.remove(0));
		System.out.println("remove boolean: "+boolArray.remove(3));
		System.out.println("remove array: "+arrayArray.remove(0));

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);
		
		doubleArray.add(intArray.remove(0)+doubleArray.remove(doubleArray.size()-1));
		boolArray.add(boolArray.remove(1)&&boolArray.remove(2));
		arrayArray.get(0).add(null);
		stringArray.remove(0);

		printArrayInfo(intArray);
		printArrayInfo(doubleArray);
		printArrayInfo(stringArray);
		printArrayInfo(datetimeArray);
		printArrayInfo(boolArray);
		printArrayInfo(arrayArray);
		
		try {
			int a=(int)intArray.get(2);
			double b=(double)doubleArray.get(3);
			System.out.println("No problem casting Integer to int and Double to double");
		} catch(Exception e) {
			System.out.println("Exception casting Integer to int or Double to double: "
					+e.getClass()+" "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		//printArrayInfo(new MyArrayList<Integer>(new int[] {0, 1, 2, 3, 4, 5}));
		Double[] doubleArray=new Double[] {Math.PI,Math.E,0.0};
		MyArrayList<Double> doubleArrayList=new MyArrayList<Double>(doubleArray);
		printArrayInfo(new MyArrayList<Integer>(new Integer[] {0, 1, 2, 3, 4, 5}));
		printArrayInfo(new MyArrayList<String>(new String[] {"first","second","and","more"}));
		printArrayInfo(new MyArrayList<Boolean>(new Boolean[0]));
		printArrayInfo(doubleArrayList);
		doubleArray[2]=3.0;
		printArrayInfo(doubleArrayList);
	}
}