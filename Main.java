import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static jdk.internal.org.jline.utils.InfoCmp.Capability.lines;


public class Main
{
    static Scanner scan;
    private static String[] text;

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("data.txt");
        scan = new Scanner(file);

        String inputFromFile = "";

        while (scan.hasNextLine())                                // checks if there is more lines in the file
        {
            inputFromFile += scan.nextLine();                     // adds each line to the inputFromFile string.
        }

        text = inputFromFile.split(" ");                    // Creates and array of strings, where each element is a single word from the file.

        System.out.println(text.length);

        printWordsStartingWith("Ø");

        printWordsOfLength(3);

        //test dine metoder ved at kalde dem her:

        printLongestWord();
        printFirstHalfOfEachWord();
        printMostFrequentLetter();
        printLessFrequentLetter();


    }

    private static void printWordsOfLength(int l)
    {
        print(lines() + "\n" + "The following words have the length 3:");
        boolean wordisvalid = true;

        for (String s : text)
        {
            if (s.length() == l)
            {
                if (s.contains(",") || s.contains("."))
                {
                    wordisvalid = false;
                }

                if (wordisvalid)
                {
                    System.out.println(s);
                }
            }
        }
    }

    private static void printWordsStartingWith(String pattern)
    {
        print("The following words starts with 'Ø':");

        for (String s : text) // for each word in text
        {
            if (s.startsWith(pattern) || s.startsWith(pattern.toLowerCase()))
            {
                System.out.println(s);
            }
        }
    }
    //skriv dine metoder herunder:


    //## Task 1:2. Create a method called printLongestWord() that prints the longest of all the words in the text variable.
    private static void printLongestWord()
    {
        print(lines() + "\n" + "The longest word is: ");
        String longestWord = "";

        for (String word : text)
        {
            if (longestWord.length() < word.length())
            {
                longestWord = word;
            }
        }
        System.out.println(longestWord);


    /* This for-loop does the same
        for (int i = 0; i < text.length; i++)
        {
            if (longestWord.length() < text[i].length())
            {
                longestWord = text[i];
            }
        }
        System.out.println(longestWord);
   */
    }

    // ## Task 2: Create a method called printFirstHalfOfEachWord() that uses substring to print the first half of all words in the ```text``` variable.

    private static void printFirstHalfOfEachWord()
    {
        print(lines() + "\n" + "This is the first half of each word:");
        for (String word : text)
        {
            System.out.println(word.substring(0, word.length() / 2));
        }
    }

    //  ## Task 3: Create a method called printMostFrequentLetter() that prints the most frequent found letter in the ```text``` variable.
    private static void printMostFrequentLetter()
    {
        print(lines());
        HashMap<Character, Integer> letters = new HashMap<>();
          /*
        Her instantierer jeg mit map. Et map er en samling af nøgle-værdier (key-values) par.
        Et hash-map indeholder et element(entry), som man deler op i 2 dele, den ene er "key", som er en unik identifier.
        Den anden er den "data"(value), som man ønsker opbevaret. key-values er vores element.
        For at hente den data, som vi har opbevaret, bruger man den unikke nøgle (key).
        I min metode er min key: Character og min value: Integer. Letters er navnet på mit map.
         */

        char c; // Declarer min char variabel som "c".

        for (String word : text)
        {
            for (int i = 0; i < word.length(); i++) //Her løber jeg hvert tegn i ordene igennem, samt tæller hvor mange gange det tegn forekommer.
            {
                c = word.toLowerCase().charAt(i); // Da der kan være forskel i store og små bogstaver, har jeg sat alle bogstaver til at være små

                if (letters.containsKey(c))
                {
                    letters.replace(c, letters.get(c) + 1);
                } else
                {
                    letters.put(c, 1);
                }
            }
        }
        char mostFrequentLetter = ' ';
        int count = 0;

        // Entry er det samme som element, dvs. key-values'ne, som jeg nævnte tidligere. Nu løber vi alle implementer igennem, for at se hvilket element der forekommer flest gange, samt hvor mange gange det optræder i teksten.

        for (Map.Entry<Character, Integer> element : letters.entrySet())
        {
            if (element.getValue() > count)
            {
                // Hvis værdien i et element er større end min count variabel (som er sat til 0, første gang loopet kører), så skal min variabel mostFrequentLetter sættes til at være det elements key, dvs. bogstav. Count skal så erstattes af key's værdi (value).

                mostFrequentLetter = element.getKey();
                count = element.getValue();
            }
            // System.out.println(element); // Denne er ikke nødvendig, men for at tjekke om min metode virker, kan jeg ved at printe "element" ud sammenligne, om det er rigtigt.
        }
        System.out.println("The most frequent letter in the text is: " + mostFrequentLetter + " and it appears: " + count + " times");
    }


    //## Task 4: Exactly the same as task 3 - but instead of _most_ frequent, then _less_ frequent. So create a method called printLessFrequentLetter().
    public static void printLessFrequentLetter()
    {
        print(lines());
        HashMap<Character, Integer> letters = new HashMap<>();

        char c;

        for (String word : text)
        {
            for (int i = 0; i < word.length(); i++)
            {
                c = word.toLowerCase().charAt(i);

                if (letters.containsKey(c))
                {
                    letters.replace(c, letters.get(c) + 1);
                } else
                {
                    letters.put(c, 1);
                }
            }
        }
        char lessFrequentLetter = ' ';
        int count = 1;

        for (Map.Entry<Character, Integer> element : letters.entrySet())
        {
            if (element.getValue() <= count) //forskellen her er så at mit elements værdi skal være mindre eller lig count.
            {
                lessFrequentLetter = element.getKey();
                count = element.getValue();
            }
            //System.out.println(element);
        }
        System.out.println("The less frequent letter in the text is: " + lessFrequentLetter + " and it appears: " + count + " times");
        print(lines());
    }


    public static void print(String message)
    {
        System.out.println(message);
    }

    public static String lines()
    {
        return "---------------------------------------------------";
    }

}


