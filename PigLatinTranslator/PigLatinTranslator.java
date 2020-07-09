/***
 * Progam to translate English into Pig Latin
 * 
 * For an exercise on exercism.io
 * My extend it later if I feel like it :)
 * 
 * Please note currently uppercase letters and punctuation will break the translation.
 * This may be corrected in a fucture version
 * 
 * @author Kyle Givler
 * @version 0.0.1
 */
public class PigLatinTranslator
{
    public static void main(String[] args)
    {
        var p = new PigLatinTranslator();
        System.out.println(p.translate("this is a sentence in pig latin enjoy it is not allowed to have uppercase letters or punctuation as those things will break this progam"));
    }

    /***
     * Translates an English word or phrase into Pig Latin
     * Does not support uppercase letters or punctuation
     * @param phrase to translate
     * @return the translated phrase
     */
    public String translate(String phrase)
    {
        StringBuilder sb = new StringBuilder();
        var words = phrase.split(" ");

        for(int i = 0; i < words.length; i++)
        {
            sb.append(beginTranslate(words[i]));
            if(i != words.length - 1)
                sb.append(" ");
        }

        return sb.toString();
    }

    private String beginTranslate(String word)
    {
        String[] vowelSounds = { "a", "e", "i", "o", "u", "xr", "yt"};

        for(String s : vowelSounds)
        {
            if(word.startsWith(s))
                return word + "ay";
        }

        return beginsWithConsonant(word);
    }

    private String beginsWithConsonant(String word)
    {
        String[] consonantCluster = { "ch", "qu", "thr", "sch", "th"};

        //if(word.substring(1, 3).equals("qu"))
        if(word.indexOf("qu") >=1)
            return beginsWithConsonantThenQu(word);

        for(String s : consonantCluster)
        {
            if(word.startsWith(s))
                return word.substring(s.length(), word.length()) + word.substring(0, s.length()) + "ay";
        }

        if(word.indexOf("y") >= 1)
            return hasYAfterConsonant(word);

        return word.substring(1, word.length()) + word.substring(0,1) + "ay";
    }

    private String beginsWithConsonantThenQu(String word)
    {
        return word.substring(3, word.length()) + word.substring(0, 3) + "ay";
    }

    private String hasYAfterConsonant(String word)
    {
        int yIndex = word.indexOf("y");
        return word.substring(yIndex, word.length()) + word.substring(0, yIndex) + "ay";
    }
}