import java.util.*;
import java.net.*;

public class Tasks_6{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a = 1;
        while(a != 0){
            System.out.println("Input a number of task from 1 to 10");

            System.out.println("0 - Exit");
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
    
   public static void Ex_1(){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(bell(num));     
    }
    public static int bell(int num) {
        int[][] bell = new int[num+1][num+1];
        bell[0][0] = 1;

        for (int i = 1; i <= num; i++) {
            bell[i][0] = bell[i-1][i-1];

            for (int j=1; j<=i; j++) {
                bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
            }
        }
        /**
        Пример: num = 3

        i\j 0 1 2 3
        0	1
        1	1 2
        2	2 3 5
        3	5

        Ответ: 5
        */
        return bell[num][0];
    }
    
    public static void Ex_2()
    {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        System.out.println(translateSentence(a));     
    }   
    public static String translateWord(String word)
    {
        String result = "";
        String vowels = "aeiouyAEIOUY";
        //если начинается с согласной, вытаскиваем все подряд идущие согласные
        if(vowels.indexOf(word.charAt(0)) == -1){
            String buffer = "";
            int i = 0;
            for(; i < word.length(); i++){
                if(vowels.indexOf(word.charAt(i)) == -1){
                    buffer += word.charAt(i);
                }else{
                    break;
                }
            }
            result += word.substring(i) + buffer + "ay";
        //если начинается с гласной
        }else{
            result = word + "yay";
        }
        return result;
    }
    public static String translateSentence(String sentence)
    {
        String result = "";
        String words[] = sentence.split(" ");
        for(String word : words){
            result += translateWord(word) + " ";
        }
        return result;
    }
    
    public static void Ex_3(){
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        System.out.println(validColor(a));    
    }   
    static boolean validColor(String line){
		int comp = 0;
		//записываем параметры цвета каждый как отдельный элемент массива
		String[] param = line.substring(line.indexOf('(') + 1, line.indexOf(')')).split(",");
		//проверяем, если строка пустая, чего быть не должно
		for(int i = 0; i < param.length; i++){
			if(param[i].equals(""))	return false;
		}
		//проверяем условия
		if(line.substring(0, line.indexOf('(')).equals("rgba") || line.substring(0, line.indexOf('(')).equals("rgb")){
			if(param.length == 4 || param.length == 3){
				if(param.length == 4){
					if((Double.parseDouble(param[param.length - 1]) <= 1 && Double.parseDouble(param[param.length - 1]) >= 0) == false)
						return false;
				}
					for(int i = 0; i < param.length; i++){
						if((Double.parseDouble(param[i]) <= 255 && Double.parseDouble(param[i]) >= 0) == false){
							return false;
						}
					}
			}
			return true;	
		}
		
		return false;
	}
    
    public static void Ex_4(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String a = in.nextLine();
        System.out.println(stripURLParam(str));       
    }   
    public static String stripURLParam(String str, String ... param){
        URL url = null;
        try
        {
            url = new URL(str);
        }
        catch(MalformedURLException e)
        {
            System.err.println("MalformedURLException: " + e.getMessage());
        }
        String query = url.getQuery();
        System.out.println(query);
        String[] arguments = query.split("&");
        ArrayList<String> results = new ArrayList<String>();
        boolean isDetected = false;
        for(int i = 0; i < arguments.length - 1; i++){
            for(int j = 1; j < arguments.length; j++){     
                if(arguments[i].split("=")[0].equals(arguments[j].split("=")[0]) && i != j){
                    System.out.println(arguments[i] + " - " + arguments[j]);
                    isDetected = true;         
                    break;
                }
            }
            for(int k = 0; k < param.length; k++){
                    if(arguments[i].split("=")[0].equals(param[k]))
                        isDetected = true;
            }
            if(!isDetected){
                results.add(arguments[i]);
            }
            else{
                isDetected = false;
            }
        }
        results.add(arguments[arguments.length - 1]);
        String resultUrl = str.substring(0, str.indexOf("?")) + "?";
        for(int i = 0; i < results.size() - 1; i++){
            resultUrl += results.get(i) + "&";   
        }
        resultUrl += results.get(results.size() - 1);
        return resultUrl;
    }
    public static String stripURLParam(String str, String a){
        return "";
    }
    
    public static void Ex_5(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(getHashTags(str));     
    }   
    static String getHashTags(String line){
		String result = "";
		int k = 0;

		//убираем все запятые из строки
		while(line.indexOf(',') != -1){
			line = line.substring(0, line.indexOf(',')) + line.substring(line.indexOf(',') + 1);
		}

		//все заглавные буквы превращаем в строчные
		line = line.toLowerCase();

		//запишем каждое слово как элемент массива
		String[] masLine = line.split(" ");

		//проверяем, если в заголовке меньше 3х слов
		if(masLine.length < 3){
			if(masLine.length < 2){
				return line;
			}
	//если 2, то вернуть в порядке убывания (касается длины слов)
			if(masLine[0].length() < masLine[1].length())
				return "#" + masLine[1] + " #" + masLine[0];
			else
				return "#" + masLine[0] + " #" + masLine[1];
		}

	//ищем 3 слова с наибольшей длиной
		for(int i = 0; i < 3; i++){
	//line при каждой попытке найти слово должен содержать первое слово
			line = masLine[0];
	//начинаем со 2 слова, тк у нас первое уже записано в line
	//line используется для поиска длины слова и в конечном счете для записи результата
	//k нужно для того, чтобы найти самое длинное слово и исключить его в повторных проверках
			for(int j = 1; j < masLine.length; j++){
				if(masLine[j].length() > line.length()){
					line = masLine[j];
					k = j;
				}
			}
	//исключаем полученное слово из проверки в дальнейшем
			masLine[k] = "";
	//записываем полученное слово
			result += "#" + line + " ";
		}

		return result;
	}
    
    public static void Ex_6()
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(ulam(n));      
    }   
    public static int ulam(int n)
    {
        int count, cur = 4, i = 3;
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        while(i <= n){
            count = 0;
            for(int j = 0; j < arr.size()-1; j++){
                for(int k = j; k < arr.size(); k++){
                    if(arr.get(j) + arr.get(k) == cur && j != k)
                        count++;
                }
            }
            if(count == 1){
                arr.add(cur);
                i++;
            }
            cur++;
        }
        return arr.get(n-1);
    }
    
    public static void Ex_7(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(longestNonRepeat(str));    
    }   
    public static String longestNonRepeat(String str){
        String result = "";
        for(int i = 0; i < str.length(); i++){
            if(result.indexOf(str.charAt(i)) == -1){
                result += str.charAt(i);
            }
            else{
            	break;
            }
        }
        return result;
    }
    
    public static void Ex_8(){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(convertToRoman(num));   
    }   
    public static String convertToRoman(int num){
        String roman = "";
        int a = 0, k = 0;
        while(num != 0){
            a = num % 10;
            if(k == 0)
                roman = units(a) + roman;
            else if (k == 1)
                roman = tens(a) + roman;
            else if(k == 2)
                roman = hundreds(a) + roman;
            else if(k == 3)
                roman = thousends(a) + roman;
            num /= 10;
            k++;
        }
        return roman;
    }
    public static String units(int a){
        switch(a){
            case 1: 
                return "I";
            case 2: 
                return "II";
            case 3: 
                return "III";
            case 4: 
                return "IV";
            case 5: 
                return "V";
            case 6: 
                return "VI";
            case 7: 
                return "VII";
            case 8: 
                return "VIII";
            case 9: 
                return "IX";
            default:
                return "";
        }
    }
    
    public static String tens(int a){
        switch(a){
            case 1: 
                return "X";
            case 2: 
                return "XX";
            case 3: 
                return "XXX";
            case 4: 
                return "XL";
            case 5: 
                return "L";
            case 6: 
                return "LX";
            case 7: 
                return "LXX";
            case 8: 
                return "LXXX";
            case 9: 
                return "XC";
            default:
                return "";
        }
    }
    
    public static String hundreds(int a){
        switch(a){
            case 1: 
                return "C";
            case 2: 
                return "CC";
            case 3: 
                return "CCC";
            case 4: 
                return "CD";
            case 5: 
                return "D";
            case 6: 
                return "DC";
            case 7: 
                return "DCC";
            case 8: 
                return "DCCC";
            case 9: 
                return "CM";
            default:
                return "";
        }
    }
    
    public static String thousends(int a){
        switch(a){
            case 1: 
                return "M";
            case 2: 
                return "MM";
            case 3: 
                return "MMM";
            default:
                return "";
        }
    }
    
    public static void Ex_9(){
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        System.out.println(formula(a));      
    }   
    public static boolean formula(String a) {
        boolean res = false;
        int equalsPos = a.indexOf("=");
        if ((equalsPos > -1) && (a.lastIndexOf("=") == equalsPos)) {
            int mathAns = Integer.parseInt(a.substring(equalsPos+1).trim());
            String mathExpress = a.substring(0, equalsPos);
            if ((a.contains("+")) && (a.indexOf("+") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\+ ");
                if (Integer.parseInt(mathVars[0]) + Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
            else if ((a.contains("*")) && (a.indexOf("*") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\* ");
                if (Integer.parseInt(mathVars[0]) * Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
            else if ((a.contains("/")) && (a.indexOf("/") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\/ ");
                if (Integer.parseInt(mathVars[0]) / Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
            else if ((a.contains("-")) && (a.indexOf("-") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\- ");
                if (Integer.parseInt(mathVars[0]) - Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
        }
        return res;
    }
    
    public static void Ex_10(){
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        System.out.println(palindrome(num));       
    }
    //проверяет длину строки, больше ли она единицы. Если она палиндром, сразу выводит, иначе
    //складывает парные цифры и проверяет заново
    public static boolean palindrome(String num){
        while(num.length() > 1){
            System.out.println(num);
            if(isPal(num))
                return true;
            String buf = "";
            for(int i = 0; i < num.length() - 1; i += 2){
                buf += "" +  (Character.getNumericValue(num.charAt(i)) + Character.getNumericValue(num.charAt(i+1)));
            }
            num = buf;
        }
        return false;
    }
    public static boolean isPal(String str){
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}