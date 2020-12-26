import java.util.Scanner;
public class Tasks_5{
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
		Scanner in = new Scanner(System.in);
		System.out.println("encrypt or decrypt?");
		String a = in.nextLine();
		switch(a){
			case "encrypt":
				String line = in.nextLine();
				encrypt(line);
				break;
			default:
				System.out.println("Input how many numbers are there in your massive:");
				int l = in.nextInt();
				int[] massive;
				massive = new int[l];
				for(int i = 0; i < l; i++){
					massive[i] = in.nextInt();
				}
				System.out.println(decrypt(massive));
				break;
		}
	}

	static void encrypt(String line){
		int[] mas;
		mas = new int[line.length()];
		mas[0] = (int) line.charAt(0);//первая буква строки
//находим разницу между значениями кодов символов
		for(int i = 1; i < line.length(); i++){
			mas[i] = (int) line.charAt(i) - (int) line.charAt(i - 1);
		}
//выводим результат
		for(int i = 0; i < mas.length; i++){
			System.out.print(mas[i] + " ");
		}
	}
	static String decrypt(int[] massive){
		String line = "" + (char) massive[0];//первый элемент
//разницу прибавляем к уже вычисленному члену массива
		for(int i = 1; i < massive.length; i++){
			line += (char) (massive[i] + (int) line.charAt(i - 1));
		}
//возвращаем результат
		return line;
	}

	static void Ex_2(){
		Scanner in = new Scanner(System.in);
		System.out.println("Input: pawn, knight, rook, bishop, queen or king");
		String line = in.nextLine();
		System.out.println("\nInput start position.");
		String position = in.nextLine();
		System.out.println("\nInput next position.");
		String aim = in.nextLine();
		System.out.println(canMove(line, position, aim)); 
	}
	static boolean canMove(String line, String x1, String x2){
		//если значения не подходят для шахматной доски
		if((int) x1.charAt(0) < (int) 'A' || (int) x1.charAt(0) > (int) 'H' || (int) x1.charAt(1) < (int) '1' || (int) x1.charAt(1) > (int) '8'){
			return false;
		}
		//то же самое для следующей позиции
		if((int) x2.charAt(0) < (int) 'A' || (int) x2.charAt(0) > (int) 'H' || (int) x2.charAt(1) < (int) '1' || (int) x2.charAt(1) > (int) '8'){
			return false;
		}
		//пешка
		if(line.equals("pawn")){
		//случай, если пешка на начальной позиции, то можем ходить вперед на 1 или 2 позиции
			if((int) x1.charAt(1) == (int) '2' && (int) x1.charAt(0) == (int) x2.charAt(0) && ((int) x2.charAt(1) == (int) x1.charAt(1) + 1 || (int) x2.charAt(1) == (int) x1.charAt(1) + 2)){
				return true;
			}
		//в остальных случаях только на 1 позицию вперед
			else if((int) x2.charAt(1) == (int) x1.charAt(1) + 1 && (int) x1.charAt(0) == (int) x2.charAt(0)){
				return true;
			}
		}
		//конь
		else if(line.equals("knight")){
		//если по букве разница 1, то по цифре разница 2
			if((int) x2.charAt(0) == (int) x1.charAt(0) + 1 || (int) x2.charAt(0) == (int) x1.charAt(0) - 1){
				if((int) x2.charAt(1) == (int) x1.charAt(1) + 2 || (int) x2.charAt(1) == (int) x1.charAt(1) - 2){
					return true;
				}
			}
		//тут наоборот
			else if((int) x2.charAt(0) == (int) x1.charAt(0) + 2 || (int) x2.charAt(0) == (int) x1.charAt(0) - 2){
				if((int) x2.charAt(1) == (int) x1.charAt(1) + 1 || (int) x2.charAt(1) == (int) x1.charAt(1) - 1){
					return true;
				}
			}
			return false;
		}
		//ладья
		else if(line.equals("rook")){
			//если цифры или буквы совпадают
			if ((int) x2.charAt(0) == (int) x1.charAt(0) || (int) x2.charAt(1) == (int) x1.charAt(1)){
				return true;
			}
		}
		//слон
		else if(line.equals("bishop")){
		//если разница между цифрами равна разнице между буквами
			if(Math.abs((int) x1.charAt(0) - (int) x2.charAt(0)) == Math.abs((int) x1.charAt(1) - (int) x2.charAt(1))){
				return true;
			}
		}
		//королева
		else if(line.equals("queen")){
		//как слон
			if(Math.abs((int) x1.charAt(0) - (int) x2.charAt(0)) == Math.abs((int) x1.charAt(1) - (int) x2.charAt(1))){
				return true;
			}
		//как ладья
			if ((int) x2.charAt(0) == (int) x1.charAt(0) || (int) x2.charAt(1) == (int) x1.charAt(1)){
				return true;
			}
		}
		//король
		else if(line.equals("king")){
		//как слон, только на 1 позицию
			if(Math.abs((int) x1.charAt(0) - (int) x2.charAt(0)) == Math.abs((int) x1.charAt(1) - (int) x2.charAt(1)) && Math.abs((int) x1.charAt(0) - (int) x2.charAt(0)) == 1){
				return true;
			}
		//как ладья, только на 1 позицию
			if (((int) x2.charAt(0) == (int) x1.charAt(0) || (int) x2.charAt(1) == (int) x1.charAt(1)) && (Math.abs((int) x1.charAt(0) - (int) x2.charAt(0)) == 1 || Math.abs((int) x1.charAt(1) - (int) x2.charAt(1)) == 1)){
				return true;
			}
		}
		return false;
	}

	static void Ex_3(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		String word = in.nextLine();
		System.out.println(canComplete(line, word)); 
	}
	static boolean canComplete(String line, String word){
		int k = -1;
		String copy = "";
		for(int i = 0; i < line.length(); i++){
			for(int j = 0; j < word.length(); j++){
				if(line.charAt(i) == word.charAt(j)){
					//если символ в слове находится после предыдущего, а не наоборот
					if(k < j){
						k = j;//записываем позицию последнего символа
						copy += word.charAt(j);//в copy должно получится то же, что и в line
						//убираем найденные буквы
						word = word.substring(0, j) + " " + word.substring(j + 1);
						break;
					}

				}
			}
		}
		if(copy.equals(line))
			return true;//возвращаем true, если условие выполнено
		return false;
	}
	static void Ex_4(){
		Scanner in = new Scanner(System.in);
		int[] mas;
		int length = in.nextInt();
		mas = new int[length];
		for(int i = 0; i < length; i++){
			mas[i] = in.nextInt();
		}
		System.out.println(sumDigProd(mas)); 
	}
	
	static int sumDigProd(int[] mas){
		int sum = 0;
		//складываем все цифры вместе
		for(int i = 0; i < mas.length; i++){
			sum += mas[i];
		}
		//пока ответ не станет длиной в 1 цифру, выполняем
		while (sum > 9){
			sum = (sum % 10) * (sum / 10);//считаем произведение цифр
		}
		return sum;
	}

	static void Ex_5(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter words separated by space");
		String line = in.nextLine();

		System.out.println(sameVowelGroup(line));
	}
	static String sameVowelGroup(String line){
		char[] vowel = {'a', 'e', 'i', 'o', 'u', 'y'};
		String[] masLine = line.split(" ");
		String[] masVowel;
		String line1 = "";
		int k = 0;

		//в line1 записываем все гласные слов через пробел
		for(int i = 0; i < line.length(); i++){
			for(int j = 0; j < vowel.length; j++){
				if(line.charAt(i) == vowel[j]){
					line1 += vowel[j];
				}
			}
			if(line.charAt(i) == ' ')	line1 += " ";
		}

		masVowel = line1.split(" ");//записываем глассные в массив
		line = masLine[0] + " ";//первое слово выводится всегда
		line1 = masVowel[0];/**на случай, если в следующем цикле
		мы не найдём повторяющихся гласных, записываем те, что есть в переменную*/

		//из набора гласных первого слова удаляем повторяющиеся гласные
		for(int i = 0; i < masVowel[0].length(); i++){
			for(int j = i + 1; j < masVowel[0].length(); j++){
				if(masVowel[0].charAt(i) == masVowel[0].charAt(j)){
					line1 = masVowel[0].substring(0, j) + masVowel[0].substring(j + 1);
				}
			}
		}

		//по гласным первого слова проверяем гласные остальных слов
		for(int i = 1; i < masVowel.length; i++){
			for(int j = 0; j < masVowel[i].length(); j++){
				for(int g = 0; g < line1.length(); g++){
					if(masVowel[i].charAt(j) == line1.charAt(g)){
						k++;
					}
				}
			}
/**если кол-во найденных нами ранее в цикле гласных не будет равно кол-ву гласных слова,
значит в нём присутствуют и другие гласные, а нас это не устраивает*/
			if(k == masVowel[i].length()){
				line += masLine[i] + " ";
			}
			k = 0;//обнуляем k для проверки следующих слов (их гласных)
		}
		
		return line;
	}
	static void Ex_6(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		String[] cardNum = line.split("");
		System.out.println(validateCard(cardNum));
	}
	static boolean validateCard(String[] cardNum){
		//проверяем длину номера кредитной карты
		if(cardNum.length > 19 || cardNum.length < 14){
			return false;
		}

		String h;
		int sum = 0;
		//переворачиваем число
		for(int i = 0; i < (cardNum.length - 1) / 2; i++){
			h = cardNum[i];
			cardNum[i] = cardNum[cardNum.length - 2 - i];//-2, тк последнее число не учитываем
			cardNum[cardNum.length - 2 - i] = h;
		}

		for(int i = 0; i < cardNum.length - 1; i++){
			//если нечетная позиция (0 это 1 позиция)
			if(i % 2 == 0){
			//если удвоенное знач-е цифры однозначно
				if((Integer.parseInt(cardNum[i]) * 2) % 10 == 0 && Integer.parseInt(cardNum[i]) * 2 != 10) {
					cardNum[i] = "" + Integer.parseInt(cardNum[i]) * 2;//записать удвоенной значение
				}
				//если не однозначно
				else{
				//сложить цифры удвоенной нами цифры
					cardNum[i] = "" + ((Integer.parseInt(cardNum[i]) * 2) / 10 + (Integer.parseInt(cardNum[i]) * 2) % 10);
				}
			}
			sum += Integer.parseInt(cardNum[i]);//складываем все цифры
		}
//если разность "10 - последняя цифра суммы" = контрольному значению
		if(10 - sum % 10 == Integer.parseInt(cardNum[cardNum.length - 1])){
			return true;//то возвращаем true
		}
		return false;
	}

	static void Ex_7(){
		Scanner in = new Scanner(System.in);
		int number = in.nextInt(); 
		System.out.println(numToEng(number));
	}
	static String numToEng(int n){
		String[] num1 = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String[] num2 = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
		String[] num3 = {"ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
		String line = "";

		if(n == 0)	return "zero";

		int i = n % 100;//записываем двузначную или однозначную часть числа
		n = n / 100;//записываем сотую часть числа

		if(n > 0){
			line += num1[n - 1] + " hundred" + " ";//сотые
		}

		if(i < 10){
			line += num1[i - 1];//если однозначное число
		}
		//если двузначное
		else{
			//если 10 <= i <= 19
			if(i / 10 == 1){
				//i = 10
				if(i == 10){
					line += num3[0];
				}
				//оставшиеся варианты
				else{
					line += num2[i - 11];
				}
			}
			//если 20 <= i <= 99
			else{
				line += num3[i / 10 - 1] + " ";//десятки
				
				if(i % 10 - 1 != -1)
					line += num1[i % 10 - 1];//единицы
			}
		}
		return line;
	}
//перепутала нумерацию, это девятое задание
	static void Ex_8(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		System.out.println(correctTitle(line));
	}
	static String correctTitle(String line){
		String[] title = line.split(" ");
		
		for(int i = 0; i < title.length; i++){
			for(int j = 0; j < title[i].length(); j++){
				//первую букву слова сделать заглавной
				if(j == 0){
					title[i] = title[i].substring(0, 1).toUpperCase() + title[i].substring(1);
				}
				//остальные буквы слова строчными
				else{
					if(title[i].charAt(j) != ',' || title[i].charAt(j) != '.'){
						title[i] = title[i].substring(0, j) + title[i].substring(j, j + 1).toLowerCase() + title[i].substring(j + 1);
					}
				}
			}
		}
		line = "";
		//слова исключения строчными делаем
		for(int i = 0; i < title.length; i++){
			if(title[i].equals("And") || title[i].equals("The") || title[i].equals("Of") || title[i].equals("In")){
				title[i] = title[i].toLowerCase();
			}
			//записываем всё в line, формируя предложение
			line += title[i] + " ";
		}
		
		return line;
	}
	static void Ex_9(){
		Scanner in = new Scanner(System.in);
		System.out.println();
	}
	

	static void Ex_10(){
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		System.out.println(hexLattice(num));
	}

	static String hexLattice(int number){
		int k = 0, r = 0;
		int num = number - 1;
		String result = "";
//формула такая: 1 + 6*1 + 6*2 + 6*3 + ... 6*n
//в k мы по итогу получить должны число n из формулы
		while(num > 0){
			k++;
			num -= 6 * k;
		}
//если в результате вычислений выше мы в num не получили 0,
//значит число нам не подходит
		if(num != 0)
			return "Invalid";
//формула ниже для того, чтобы узнать,сколько символов нужно в середине
//Пример: number = 7, тогда наша формула 7 = 1 + 1*6;
//очевидно, что посередине у нас 3 символа. из предыдущей формулы
//k = 1, тогда по след.формуле получим 3.
		k = k * 2 + 1;
		number -= k;//7 - 3 = 4
//ниже нужно получить r - кол-во рядов символов
//для number = 7 будет так выглядеть: 4 = 4 - 2*(3 - 1), а r = 1
		while(number > 0){
			r++;
			number -= 2 * (k - r);
		}
		r++;//r = 1+1=2
//для первой половины "рисунка" до наибольшей по длине строки
		for(int i = r; i < k; i++){
			for(int j = 0; j < k - i; j++){
				result += " ";
			}
			for(int j = 0; j < i; j++){
				result += "o ";
				if(j == i - 1)	result += "\n";
				
			}
		}
//для наибольшей по длине строки и до конца
		for(int i = k; i >= r; i--){
			for(int j = 0; j < k - i; j++){
				result += " ";
			}
			for(int j = 0; j < i; j++){
				result += "o ";
				if(j == i - 1)	result += "\n";
			}
		}
		return result;
	}
}