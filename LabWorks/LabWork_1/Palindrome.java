import java.util.Scanner;
public class Palindrome{
	public static void main(String[] args) {
		isPalindrome(args[0]);
	}
	public static String reverseString(String s){
		String revwrd = "";
		for (int i = s.length() - 1; i >= 0; i--){
			revwrd += s.charAt(i);
		}
		return revwrd;
	}
	public static void isPalindrome(String s){
		if (s.equals(reverseString(s))){
			System.out.print("It is Palindrome");
		}
		else{
			System.out.print("It is not Palindrome");
		}
	}
}
