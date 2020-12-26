import java.util.Scanner;
public class Tasks_4{
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
	//сочинение для Бесси
	static void Ex_1(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();//наша строка
		int n = in.nextInt();//количество слов
		int k = in.nextInt();//k - макс.кол-во символов в строке (не считая пробелы)
		System.out.println(essay(n, k, line));
	}
	

	static String essay(int n, int k, String l){
		String[] line = l.split(" ");
		l = "";
		int k1 = k;

		//перебираем слова с проверкой по их длине
		for(int i = 0; i < n; i++){
		if(line[i].length() <= k1){//случай,когда оставляем в тек.строке
			l += line[i];
			k1 -= line[i].length();
		}else{//случай, когда переносим на новую строчку
			l += "\n";
			k1 = k - line[i].length();
			l += line[i];
		}
		if(i != n - 1)
			l += " ";
		}

		return l;
	}

//формирование кластера скобок
	static void Ex_2(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		System.out.println(split(line));
	}
	static String split(String line){
		int k = 0;
		String answer = "[\"";

//цикл сначала запишет в переменную все "(", а затем все ")". Переменная k нужна для выявления ошибки,
// в случае, если на закрывающую скобку не нашлось открывающей
		for (int i = 0; i < line.length(); i++){
			if (line.charAt(i) == '('){
				k++;
				answer += "(";
			}
			else if(line.charAt(i) == ')'){
				if (k < 1)	return "You've made a mistake. Be careful.";
				k--;
				answer += ")";
			}else
			return "You've made a mistake. Be careful.";//если есть посторонние символы, выводится этот текст
			
			if (k == 0 && i != line.length() - 1)
					answer += "\", \"";
		}
		if (k != 0)	return "You've made a mistake. Be careful.";//второй раз..зачем? ладно
		answer += "\"]";

		return answer;
	}

//преобразование строки
	static void Ex_3(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		int state = 0;
	//если в строке найдется(найдутся) символ(ы) '_', то вывод результата toCamelCase
	//иначе toSnakeCase
		for (int i = 0; i < line.length(); i++){
			if(line.charAt(i) == '_')	state = 1;
		}
		if (state == 0)	System.out.println(toSnakeCase(line));
		else System.out.println(toCamelCase(line));
	}
	static String toSnakeCase(String line){
		char[] a = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
//если находится заглавная буква в строке, заменим на строчную и добавим перед ней символ '_'
		for (int i = 0; i < line.length(); i++){
			for (int j = 0; j < a.length; j++){
				if (line.charAt(i) == a[j]){
					line = line.substring(0, i) + "_" + line.substring(i, i + 1).toLowerCase() + line.substring(i + 1);
				}
			}
		}
		return line;
	}

	static String toCamelCase(String line){
		int k = 0;
//если предыдущий символ '_', то удалим его из строчки и текущую букву сделаем заглавной
//если в строке изначально были заглавные буквы, превращаем их в строчные в местах (1) и (2)
		for (int i = 1; i < line.length(); i++){
			if(line.charAt(i - 1) == '_'){
				k++;
				if(k == 1)	line = line.substring(0, i - 1).toLowerCase() + line.substring(i - 1); //(1)
				line = line.substring(0, i - 1) + line.substring(i, i + 1).toUpperCase() + line.substring(i + 1).toLowerCase();//(2)
			}
		}
		return line;
	}
/**
	н
		е


	з
		а
	к
		о
	м
		м
	е
		н
	т
		и
	л
		а

*/
//вычисление зарплаты
	static void Ex_4(){
		Scanner in = new Scanner(System.in);
		double time1 = in.nextDouble();
		double time2 = in.nextDouble();
		double money = in.nextDouble();
		double multiple = in.nextDouble();

		System.out.println(overTime(time1, time2, money, multiple));
	}
	static double overTime(double time1, double time2, double money, double multiple){
		double result = 0;
		
		for (double i = time1; i < time2; i++){
		//проверяем часы работы
			if(i >= 9 && i < 17){
				//если часы работы не целочисленные
				if(Math.ceil(i) != i){
					result += (Math.ceil(i) - i) * money;
					i = Math.ceil(i) - 1;
				}
				//если целочисленные
				else result += money;
			}
			//если это сверхурочные часы
			else if(i >= 17)	result += multiple * money;
		}
		return result;

	}

//соответствие имт категории
	static void Ex_5(){
		Scanner in = new Scanner(System.in);
		String weight = in.nextLine();
		String height = in.nextLine();
		System.out.println(BMI(weight, height));
	}

	static String BMI(String weight, String height){
		String[] wght = weight.split(" ");
		String[] hght = height.split(" ");
		String result;
		double w = Double.parseDouble(wght[0]);
		double h = Double.parseDouble(hght[0]);

		//проверка единиц измерения
		if (wght[1].equals("pounds")){
			w *= 0.45359237;
		}
		if (hght[1].equals("inches")){
			h /= 39.37;
		}
		//делим вес на квадрат роста
		w = w / (h * h);
		//в переменную записываем значение w, округлённое до десятой
		result = String.format("%.1f", w);
		//проверка по параметрам и вывод результата
		if(w < 18.5)	return result + " Underweight";
		else if(w >= 25)	return result + " Overweight";
		else 	return result + " Normal weight";
	}

//мультипликативное постоянство числа
	static void Ex_6(){
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		System.out.println(bugger(a));
	}
	static int bugger(int a){
		String arg;
		int l, result = 1, k = 0;
		//пока значение переменной result не будет равно заданному
		while (result != a){
			arg = Integer.toString(a);
			l = arg.length();
//result будет содержать произведение всех цифр из arg
			for(int i = 0; i < l; i++){
//здесь в правой части от значения кода символа, который мы имеем, отнимаем
//значение кода символа 0, таким образом получая цифру, равную цифре из нашего числа
//но нам нужен тип int, поэтому char преобразовываем в int и присваиваем результат переменной
				result *= (int) (arg.charAt(i) - '0');
			}
		//считаем, сколько раз нам придется произвести умножение
		//пока result не будет равно a
			if (result != a){
				k++;
				a = result;
				result = 1;
			}
		}
		return k;
	}

//звездная стенография
	static void Ex_7(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		System.out.println(toStarShorthand(line));
	}
	
	static String toStarShorthand(String line){
		int k = 0;//сколько раз встерчается буква в строке
		String result = "";

		 for(int i = 0; i < line.length(); i++){
		 	for(int j = 0; j < line.length(); j++){
//если мы берем число предыдущее текущему значению i, то проверяем
		 		if(j < i){
//если предыдущий символ равен текущему, выходим из цикла
		 			if(line.charAt(i) == line.charAt(j)){
		 				k = -1;
		 				break;
		 			}
//если мы встречаем текущий символ или следующие поле него, которые
//идентичны этому символу, мы увеличиваем счетчик k на 1
		 		}else{
		 			if(line.charAt(i) == line.charAt(j)){
		 				k++;
		 			}
		 		}
		 	}
//если символ встретился впервые, записать его в result
		 	if(k != -1){
		 		result += line.charAt(i);
//а если он встретился больше одного раза, то "умножаем" на кол-во этих символов в строке
		 		if(k > 1){
		 			result += "*" + k;
		 		}
		 	}
		 	k = 0;
		 }

		return result;
	}

//проверка строк на рифму
	static void Ex_8(){
		Scanner in = new Scanner(System.in);
		String line1 = in.nextLine();
		String line2 = in.nextLine();
		System.out.println(doesRhyme(line1, line2));
	}
	static boolean doesRhyme(String line1, String line2){
		char[] massive = {'e', 'y', 'u', 'i', 'o', 'a'};
		String comp1 = "";
		String comp2 = "";
		String[] l1 = line1.split(" ");
		String[] l2 = line2.split(" ");
//цикл по символам последнего слова в 1й строке
		for(int i = 0; i < l1[l1.length - 1].length(); i++){
			for(int j = 0; j < massive.length; j++){
//если нашлась гласная буква, вне зависимости от регистра, запоминаем
				if(l1[l1.length - 1].charAt(i) == massive[j] || l1[l1.length - 1].charAt(i) == Character.toUpperCase(massive[j])){
					comp1 += massive[j];
				}
			}
		}
//тут то же самое, только для 2й строки
		for(int i = 0; i < l2[l2.length - 1].length(); i++){
			for(int j = 0; j < massive.length; j++){
				if(l2[l2.length - 1].charAt(i) == massive[j] || l2[l2.length - 1].charAt(i) == Character.toUpperCase(massive[j])){
					comp2 += massive[j];
				}
			}
		}
//сравниваем гласные 1й и 2й строк
		if (comp1.equals(comp2))	return true;
		else 	return false;
	}

//проверка на повторение числа 3 раза в X и 2 раза в Y
	static void Ex_9(){
		Scanner in = new Scanner(System.in);
		String x = in.nextLine();
		String y = in.nextLine();
		System.out.println(trouble(x, y));
	}
	static boolean trouble(String xline, String yline){
		char c = 'a';
//проверка, встречается ли цифра 3 раза подряд в числе
		for(int i = 1; i < xline.length() - 1; i++){
			if(xline.charAt(i) == xline.charAt(i - 1) && xline.charAt(i) == xline.charAt(i + 1)){
				c = xline.charAt(i);
				break;
			}
		}
//если переменная не изменилась, вернется значение false
		if (c == 'a')	return false;
		else{
//проверка, появляется ли выбранная цифра дважды в другом числе
			for(int i = 0; i < yline.length() - 1; i++){
				if(yline.charAt(i) == c && yline.charAt(i) == yline.charAt(i + 1))
					return true;
			}
		}
		return false;
	}

//количество уникальных символов
	static void Ex_10(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		String symbol = in.nextLine();
		System.out.println(countUniqueBooks(line, symbol));
	}
	static int countUniqueBooks(String line, String symb){
		char symbol = symb.charAt(0);
		int k = 0, p = 0;
		String resline = "";
//
		for(int i = 0; i < line.length(); i++){
//если нужный символ встретился нечетное число раз
			if(line.charAt(i) == symbol && k == 0){
				p = i + 1;//первый символ, который нам нужен
				k = 1;
			}
//если встретился второй раз
			else if (line.charAt(i) == symbol && k == 1){
				resline += line.substring(p, i);
				k = 0;
			}
		}
//если не нашлись уникальные символы (набор, из которого можно было бы найти уник.символы)
		if(resline.equals(""))	return 0;

		line = resline;
		resline = "" + line.charAt(0);
//убираем повторяющиеся символы
		for(int i = 1; i < line.length(); i++){
			for(int j = 0; j < resline.length(); j++){
				if (line.charAt(i) == resline.charAt(j)){
					k = 1;
				}
			}
			if (k != 1)		resline += line.charAt(i);
			k = 0;
		}
//у нас в resline уникальные символы. Возвращаем их кличество
		return resline.length();
	}
}