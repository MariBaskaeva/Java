import java.util.Scanner;
public class Tasks_3{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int a = 1;
		while(a != 0){
			System.out.println("1) ");
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

//число решений квадратного уравнения
	static void Ex_1(){
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		System.out.println("The result is: " + solutions(a, b, c));
	}
	static int solutions(int a, int b, int c){
		//записываем дискриминант
		int d = b * b - 4 * a * c;

//если больше 0, у нас 2 решения; если равен 0, одно
		if (d > 0)	return 2;
		else if (d == 0)	return 1;
		else return 0;	
	}

//позиция второго вхождения zip
	static void Ex_2(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		System.out.println("The result is: " + findZip(line));
	}
	static int findZip(String line){
		//длина и счётчик вхождений
		int l = line.length();
		int s = 0;
	//если находим zip, увеличиваем показатель счётчика на 1. Когда s = 2, возвращаем индекс второго вхождения
		for (int i = 0; i < l - 2; i++){
			if (line.charAt(i) == 'z'){
				if(line.charAt(i + 1) == 'i'){
					if(line.charAt(i + 2) == 'p'){
						s++;
						if(s == 2){
							return i;
						}
					}
				}
			}
		}

	//если вхождение zip не обнаружено, возвращаем -1
	return -1;
	}

//является ли число совершенным
	static void Ex_3(){
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		System.out.println("The result is: " + checkPerfect(number));
	}
	static boolean checkPerfect(int number){
		int sum = 0;


		for(int i = 1; i < number; i++){
	//ищем множители number и суммируем их всех
			if(number % i == 0){
				sum += i;
			};	
		}
	//получившуюся сумму множителей сравниваем со значением числа
		if (sum == number)	return true;
		return false;
	}

//замена первого и последнего символов строки
	static void Ex_4(){
		Scanner in = new Scanner(System.in);
		String a = in.nextLine();
		System.out.println("The result is: " + flipEndChars(a));
	}
	static String flipEndChars(String a){
		int l = a.length();

	//если длина меньше 2-х, возвращаю след.сообщение
		if (l < 2)	return "Incompatible.";
	//если 1 и последний символ строки одинаковые, возвращаю след.сообщение
		if (a.charAt(0) == a.charAt(l-1))	return "Two is a pair";
	//меняем местами 1 и последний символ, возвращаем значение
		return a.charAt(l-1) + a.substring(1, l-1) + a.charAt(0);

	}

//является ли строка допустимым 16-ричным кодом
	static void Ex_5(){
		Scanner in = new Scanner(System.in);
		String a = in.nextLine();
		System.out.println("The result is: " + isValidHexCode(a));
	}
	static boolean isValidHexCode(String code){
		//проверка на наличие # и на длину кода (должн быть равен 6, не считая знак #)
		if((code.charAt(0) != '#') || (code.length() != 7))	return false;

		int k = 0;
		char[] mas = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd' ,'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F'};

		for(int i = 1; i < code.length(); i++){
			for(int j = 0; j < mas.length; j++){
		//если символ кода соответствует символу из массива, то выход из цикла, иначе продолжить
				if (code.charAt(i) == mas[j]){
					k = 1;
					break;
				}else{
					k = 0;
				}
			}
		//если выйдя из цикла получили 0, значит символ не соответствует нужным, возвращаем false
			if(k == 0){
				return false;
			}
			k = 0;
		}
		//если все проверки пройдены успешно, вернется значение true
		return true;
	}

//У двух массивов одинаковое кол-во уник. элементов
	static void Ex_6(){
		Scanner in = new Scanner(System.in);
		int[] a;
		int[] b;
		System.out.println("Print the number of elements in the first massive: ");
		int l1 = in.nextInt();
		a = new int[l1];
		System.out.println("Print the number of elements in the second massive: ");
		int l2 = in.nextInt();
		b = new int[l2];
		System.out.println("Print elements of 1st massive: ");
		for(int i = 0; i < l1; i++){
			a[i] = in.nextInt();
		};
		System.out.println("Print elements of 2nd massive: ");
		for(int j = 0; j < l2; j++){
			b[j] = in.nextInt();
		};
		System.out.println("The result is: " + same(a, b));
	}
	static boolean same(int a[], int b[]){
		int k1 = 0, k2 = 0, r1 = 1, r2 = 1;

		for(int i = 1; i < a.length; i++){
			for(int j = 0; j < i; j++){
		//если элемент неуникален, увеличиваем k на 1
				if(a[i] == a[j]){
					k1++;
				}
			}
		//если элемент оказался уникален, увеличиваем r на 1
			if (k1 == 0){
				r1++;
			}
		//обнуляем k
			k1 = 0;
		}
	//то же самое для второго массива
		for(int i = 1; i < b.length; i++){
			for(int j = 0; j < i; j++){
				if(b[i] == b[j]){
					k2++;
				}
			}
			if (k2 == 0){
				r2++;
			}
			k2 = 0;
		}
	//сравниваем количество уникальных элементов массивов
		if(r1 == r2)	return true;
		return false;
	}

//Число Капрекара
	static void Ex_7(){
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		System.out.println("The result is: " + isKaprekar(a));
	}
	static boolean isKaprekar(int a){
		int c, b;
	//записываю в качестве строки значение числа в квадрате
		String n = Integer.toString(a * a);

	//тривиально 0 и 1 числа Капрекара
		if (n == "1" || n == "0")	return true;
	//любой квадрат числа меньше 10 нам не подходит
		if (Integer.parseInt(n) < 10)	return false;

		int l = n.length() / 2;

	//превращаем обратно в числовую переменную каждую половину n
		c = Integer.parseInt(n.substring(0, l));
		b = Integer.parseInt(n.substring(l));
	//складываем
		c += b;
	//если полученная сумма равна изначальному числу, то true
		if (c == a)	return true;
		return false;
	}

//самая длинная последовательность нулей в строке
	static void Ex_8(){
		Scanner in = new Scanner(System.in);
		String a = in.nextLine();
		System.out.println("The result is: " + longestZero(a));
	}
	static String longestZero(String a){
		int k = 0, resk = 0;
		String line = "";


		for (int i = 0; i < a.length(); i++){
		//считаем количество подряд идущих нулей
			if(a.charAt(i) == '0')	k++;
//если ряд нулей закончился или закончилась вся строка, проверить на длину
			if(a.charAt(i) != '0' || i == a.length() - 1){
				if (k > resk) resk = k;
				k = 0;
			}
		}
//если не найдены нули, вернуть пустую строку
		if (resk == 0){
			return line;
		}
//записываем в line наш ответ и возвращаем значение
		for (int i = 0; i < resk; i++){
			line += "0";
		}
		return line;
	}

//поиск простых чисел
	static void Ex_9(){
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		System.out.println("The result is: " + nextPrime(a));
	}
	static int nextPrime(int a){
//если число не простое, метод будет вызывать сам себя, пока не найдет простое,
//прибавляя к текущему чеслу 1
		for(int i = 2; i < a; i++){
			if(a % i == 0){
				return nextPrime(a + 1);
			}
		}

	return a;
	}

//рёбра прямоугольного треугольника
	static void Ex_10(){
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		int z = in.nextInt();
		System.out.println("The result is: " + rightTriangle(x, y, z));
	}
//в методе используется теорема пифагора
	static boolean rightTriangle(int x, int y, int z){
		x *= x;
		y *= y;
		z *= z;
		if (x + y == z || x + z == y || y + z == x){
			return true;
		}
		return false;
	}
	

}