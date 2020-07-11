package CountVowels;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountVowels
{
    private String input;
    private int[] vowels = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};

    CountVowels()
    {
        this.input = "";
    }

    CountVowels(String input)
    {
        this.input = input;
    }

    public int getNumberOfVowels()
    {
        return getNumberOfVowels(this.input);
    }

    public int getNumberOfVowels(String input)
    {
        int numVowels = 0;

        int[] codePoints = input.codePoints().toArray();
        for(int cp : codePoints)
            for(int v : vowels)
                if(cp == v)
                    numVowels++;

        return numVowels;
    }

    public String getVowelReport()
    {
        return getVowelReport(this.input);
    }

    public String getVowelReport(String input)
    {
        HashMap<Integer, Integer> vowelMap = new HashMap<Integer, Integer>();
        vowelMap.put(Integer.valueOf('A'), 0);
        vowelMap.put(Integer.valueOf('E'), 0);
        vowelMap.put(Integer.valueOf('I'), 0);
        vowelMap.put(Integer.valueOf('O'), 0);
        vowelMap.put(Integer.valueOf('U'), 0);

        int[] codePoints = input.codePoints().toArray();
        for(int cp : codePoints)
            for(int v : vowels)
                if(cp == v)
                {
                    vowelMap.put(Character.toUpperCase(v), vowelMap.get(Character.toUpperCase(v)) + 1);
                }

        StringBuilder sb = new StringBuilder();
        sb.append("String Contains: ");
        for(Map.Entry<Integer,Integer> entry : vowelMap.entrySet())
        {
            sb.appendCodePoint(entry.getKey());
            sb.append(": " + entry.getValue() + " ");
        }

        return sb.toString();
    }
}

class CountVowelsTester
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a sentence: ");
        String input = in.nextLine();

        System.out.println("Number of vowels: " + new CountVowels(input).getNumberOfVowels());
        System.out.println("Vowel report: " + new CountVowels(input).getVowelReport());

        in.close();
    }
}