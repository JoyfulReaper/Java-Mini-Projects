import java.util.Scanner;
import javax.swing.JOptionPane;

public class StringReverse
{
    public static void main(String[] args)
    {
        if(args[0].equals("-G") || args[0].equals("-g"))
        {
            String input = JOptionPane.showInputDialog("Enter a string to reverse");
            JOptionPane.showMessageDialog(null, reverseStringSB(input), "Reversed String", JOptionPane.PLAIN_MESSAGE); 
            System.exit(0);
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string to reverse: ");
        String input = in.nextLine();

        System.out.println("\nReversed using StringBuilder: ");
        System.out.println(reverseStringSB(input));
        System.out.println("\nReversed using an array of code points: ");
        System.out.println(reverseString(input));
        System.out.println();
        in.close();
    }

    private static String reverseStringSB(String input)
    {
        return new StringBuilder(input).reverse().toString();
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