package PalindromChecker;

import java.util.Scanner;

public class PalindromeChecker {
    public static Boolean isPalindromeSB (String input)
    {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString().equals(input);
    }

    public static Boolean isPalindrome (String input)
    {
        return input.equals(reverseString(input));
    }

    private static String reverseString(String input)
    {
        int[] codePoints = input.codePoints().toArray();
        int temp;

        for(int i = 0; i < codePoints.length / 2; i++)
        {
            temp = codePoints[i];
            codePoints[i] = codePoints[codePoints.length - i - 1];
            codePoints[codePoints.length - i - 1] = temp;
        }
        return new String(codePoints, 0, codePoints.length);
    }
}

class PalindromeCheckerTester
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a single word: ");
        String input = in.next();
        System.out.printf("%s %s a palindrom (StringBuilder Method)\n", input, PalindromeChecker.isPalindromeSB(input)? "is" : "is not");
        System.out.printf("%s %s a palindrom", input, PalindromeChecker.isPalindrome(input)? "is" : "is not");
    }
}