import java.util.Scanner;
public class Tasks_1{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int a = 1;
		while(a != 0){
			System.out.println("1) Остаток от деления");
			System.out.println("2) Площадь треугольника");

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
		int a, b;
		Scanner in = new Scanner(System.in);
		System.out.print("Введите 1 число: ");
		a = in.nextInt();
		System.out.print("Введите 2 число: ");
		b = in.nextInt();
    	System.out.println("Остаток от деления 1 на 2: " + Remainder(a, b));
	}
	static int Remainder(int i, int j){
		int result = i % j;
		return result;
	}
	static void Ex_2(){
		int base, height;
		Scanner in = new Scanner(System.in);
		System.out.print("Введите основание треугольника: ");
		base = in.nextInt();
		System.out.print("Введите высоту треугольника: ");
		height = in.nextInt();
		System.out.println("Площадь треугольника равна " + triArea(base, height));
	}
	static int triArea(int b, int h){
		int S = b * h / 2;
		return S;
	}
	static void Ex_3(){
		int chikens, cows, pigs;
		Scanner in = new Scanner(System.in);
		chikens = in.nextInt();
		cows = in.nextInt();
		pigs = in.nextInt();
		System.out.println(animals(chikens, cows, pigs));
	}
	static int animals(int a,int b,int c){
		int legs = (a + (b + c) * 2) * 2;
		return legs;
	}
	static void Ex_4(){
		double prob;
		int prize, pay;
		Scanner in = new Scanner(System.in);
		prob = in.nextDouble();
		prize = in.nextInt();
		pay = in.nextInt();
		System.out.println(profitableGamble(prob, prize, pay));
	}
	static boolean profitableGamble(double prob, int prize, int pay){
		if (pay < prob * prize){
		return true;
		}else{
		return false;
		}
	}
	static void Ex_5(){
		int N, a, b;
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		a = in.nextInt();
		b = in.nextInt();
		System.out.println(operation(N, a, b));
	}
	static String operation(int N, int a, int b){
		int r1 = a + b, r2 = a * b, r3 = a / b, r4 = a - b;
		if (r1 == N){
			return "added";
		}else if(r2 == N){
			return "multipled";
		}else if(r3 == N){
			return "divided";
		}else if(r4 == N){
			return "subtracted";
		}else{
			return "none";
		}	
	}
	static void Ex_6(){
		char a;
		Scanner in = new Scanner(System.in);
		a = in.next().charAt(0);

		System.out.println(ctoa(a));
	}
	static int ctoa(char a){
		return (int) a;
	}
	static void Ex_7(){
		int a;
		Scanner in = new Scanner(System.in);
		a = in.nextInt();
		System.out.println(addUpTo(a));
	}
	static int addUpTo(int a){
		int s = 0;
		for (int i = a; i > 0; i--){
			s += i;
		}
		return s;
	}
	static void Ex_8(){
		int l1, l2;
		Scanner in = new Scanner(System.in);
		l1 = in.nextInt();
		l2 = in.nextInt();
		System.out.println(nextEdge(l1, l2));
	}
	static int nextEdge(int i, int j){
		int s = i + j - 1;
		return s;
	}
	//доделать 9
	static void Ex_9(){
		int mas[] = {};
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < 3; i++){
			mas[i] = in.nextInt();
		}
		System.out.println(sumOfCubes(mas));
	}
	static int sumOfCubes(int a[]){
		int counter = 0;
		for (int i = 0; i < a.length; i++){
			counter += a[i] * a[i] * a[i];
		}
		return counter;
	}
	static void Ex_10(){
		int a, b, c;
		Scanner in = new Scanner(System.in);
		a = in.nextInt();
		b = in.nextInt();
		c = in.nextInt();
		System.out.println(abcmatch(a, b, c));
	}
	static boolean abcmatch(int a, int b, int c){
		int s = 0;
		for (int j = 0; j < b; j++){
			s = a + a;
			a = s;
		}
		if (s % b == 0){
			return true;
		}
		else{
			return false;
		}
	}
}