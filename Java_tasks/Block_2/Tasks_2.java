import java.util.Scanner;
public class Tasks_2{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int a = 1;
		while(a != 0){
			System.out.println("\n1) ");
			System.out.println("2) ");

			System.out.println("0) Выход");
			a = in.nextInt();
			switch(a){
				case 0:
					break;
				case 1:
					Ex_1();
					break;
				case 2:
					Ex_2();
					break;
				case 3:
					Ex_3();
					break;
				case 4:
					Ex_4();
					break;
				case 5:
					Ex_5();
					break;
				case 6:
					Ex_6();
					break;
				case 7:
					Ex_7();
					break;
				case 8:
					Ex_8();
					break;
				case 9:
					Ex_9();
					break;
				case 10:
					Ex_10();
					break;
				default:
					System.out.println("Такого пунтка нет!");
					break;
			}
		}
	}

	static void Ex_1(){
		String word;
		int k;
		Scanner in = new Scanner(System.in);
		word = in.nextLine();
		k = in.nextInt();
		repeat(word, k);
	}
	static void repeat(String w, int k){
		int l = w.length();
		for (int i = 0; i <= l - 1; i++){
			char c = w.charAt(i);
			for(int j = 0; j < k; j++){
				System.out.print(c);
			}
		}
	}
	//доделать циклом for
	static void Ex_2(){
		int[] massive;
		Scanner in = new Scanner(System.in);
		System.out.println("How many elements are there in the massive?\n");
		int a = in.nextInt();
		massive = new int[a];
		System.out.println("Print the elements of the massive\n");
		for (int i = 0; i < a; i++){
			massive[i] = in.nextInt();
		}
		System.out.println("The result is: " + differenceMaxMin(massive));
		}
	static int differenceMaxMin(int m[]){
		int l = m.length;
		int max = m[0];
		int min = m[0];
		for(int i = 1; i <= l - 1; i++){
			if(m[i] >= max){
			max = m[i];
		}
			if(m[i] <= min){
				min = m[i];
			}
		}
		int result = max - min;
		return result;
	}
	static void Ex_3(){
		int[] massive;
		Scanner in = new Scanner(System.in);
		System.out.println("How many elements are there in the massive?\n");
		int a = in.nextInt();
		massive = new int[a];
		System.out.println("Print the elements of the massive\n");
		for (int i = 0; i < a; i++){
			massive[i] = in.nextInt();
		}
		System.out.println(isAvgWhole(massive));
	}
	static boolean isAvgWhole(int m[]){
		int l = m.length;
		int sum = 0;
		for(int i = 0; i <= l - 1; i++){
			sum += m[i];
		}
		if (sum % l == 0){
			return true;
		}else{
			return false;
		}
	}
	static void Ex_4(){
		int[] massive;
		Scanner in = new Scanner(System.in);
		System.out.println("How many elements are there in the massive?\n");
		int a = in.nextInt();
		massive = new int[a];
		System.out.println("Print the elements of the massive\n");
		for (int i = 0; i < a; i++){
			massive[i] = in.nextInt();
		}
		massive = cumulativeSum(massive);
		System.out.print("{");
		for (int i = 0; i < massive.length; i++){
			System.out.print(" " + massive[i]);
		}
		System.out.print("}\n");
	}
	static int[] cumulativeSum(int m[]){
		int l = m.length;
		int[] mas;
		mas = new int[l];
		int a = 0;
		for (int i = 0; i < l; i++){
			a += m[i];
			mas[i] = a;
		}
		return mas;
	}
	static void Ex_5(){
		String s;
		Scanner in = new Scanner(System.in);
		s = in.nextLine();
		System.out.print(getDecimalPlaces(s));
	}
	static int getDecimalPlaces(String s){
		char c;
		int n = 0;
		for (int i = 0; i < s.length(); i++){
			c = s.charAt(i);
			if (c == '.'){
				n = s.length() - i - 1;
				break;
			}
		}
		return n;
	}
	static void Ex_6(){
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		Fibonacci(n);
	}
	static void Fibonacci(int n){
		int[] mas;
		mas = new int[n];
		mas[0] = 1;
		mas[1] = 2;
		for ( int i = 2; i < n; i++){
			mas[i] = mas[i-2] + mas[i - 1];
		}
		int j = mas[n - 1];
		System.out.println(j);
	}
	static void Ex_7(){
		String a;
		Scanner in = new Scanner(System.in);
		a = in.nextLine();
		System.out.print(isValid(a)); 
	}
	static boolean isValid(String index){ 
	String massive = "0123456789";
	int compare = 0; 

	int l1 = index.length();
	int l2 = massive.length();
	if (l1 > 5){ 
	return false; 
	} 
	for (int i = 0; i < l1; i++){ 
		for (int j = 0; j < l2; j++){ 
			if (index.charAt(i) == massive.charAt(j)){ 
				compare = 1; 
			} 
		} 
		if(compare == 0)	return false; 
		compare = 0; 
	} 
	return true; 
	} 
	static void Ex_8(){
		String a;
		String b;
		Scanner in = new Scanner(System.in);
		a = in.nextLine();
		b = in.nextLine();
		System.out.print(isStrangePair(a, b));
	}
	static boolean isStrangePair(String a, String b){
		//если обе строки пустые
		if(a.equals("") && b.equals(""))	return true;
		//если одна из строк пустая
		if(a.equals("") || b.equals(""))	return false;
		//если 1 буква 1 строки = последней букве 2 строки и
		//последняя буква 1 строки = первой букве 2 строки
		if ((a.charAt(0) == b.charAt(b.length() - 1)) && (b.charAt(0) == a.charAt(a.length() - 1))){
			return true;
		}
		//иначе
		return false;
	}
	static void Ex_9(){
		Scanner in = new Scanner(System.in);
		String a = in.nextLine();
		String b = in.nextLine();
		if (b.charAt(0) == '-')	System.out.println(isSuffix(a, b));
		else if(b.charAt(b.length() - 1) == '-') System.out.println(isPrefix(a, b));
	}
	static boolean isSuffix(String a, String b){
		//если слово меньше суффикса
		if(a.length() < b.length())	return false;

		return (b.substring(1, b.length()).equals(a.substring(a.length() - b.length() + 1, a.length())));
	}
	static boolean isPrefix(String a, String b){
		//если слово меньше префикса
		if(a.length() < b.length())	return false;
		//начинается ли слово с префикса
		return (b.substring(0, b.length() - 1).equals(a.substring(0, b.length() - 1)));
	}
	static void Ex_10(){
		Scanner in = new Scanner(System.in);
		int step = in.nextInt();
		System.out.println(boxSeq(step));
	}
	static int boxSeq(int step){
		int status = 0;
		if (step == 0)	return status;
		for (int i = 1; i < step + 1; i++){
			if (i % 2 == 0)	--status;
			else status +=3;
		}
		return status;
	}
	

}