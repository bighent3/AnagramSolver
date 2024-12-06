//Henton Hailey-Marshall
//CSE 143 AA with Ido Avnon
//Homework A7: AnagramSolver
import java.util.*;

public class AnagramSolver {
    private Map<String, LetterInventory> inventory;
    private List<String> dictionary;
    
    //This method constructs an anagram solver that will use the given list as its dictionary
    //You should not change the list in any way. You may assume that the dictionary is nonempty
    //Collection of nonempty sequences of letters, that it contains no duplicates, and that it
    //dpesnt change in state as the program executes
    //Builds a LetterInventory for each word used
    //Parameters: List<String> list: dictionary words to be process
    //Exceptions:
    //  -   If list is null
    public AnagramSolver(List<String> list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        dictionary = new ArrayList<>(list);
        inventory = new HashMap<>();
        
        for(String word : list) {
            inventory.put(word, new LetterInventory(word));
        }

    }

    //Find all working anagram from dictionary
    //Parameters:
    //  -   s: string to be converted into anagrams
    //  -   max: max number of words allowed
    //Exceptions:
    //  -  if phrase is null
    //  -   max must be positive integer (0 included)
    //Return: List<list<String>> list of list of anagrams
    public List<List<String>> getAnagrams(String s, int max) {
        if (s == null) {
            throw new IllegalArgumentException("Phrase cannot be null");
        }
        if (max < 0) {
            throw new IllegalArgumentException("Max must be positive integer or 0");
        }

        LetterInventory phrases = new LetterInventory(s);
        List<String> diction = pruneDictionary(phrases);
        List<List<String>> trimmed = new ArrayList<>();

        reviewAnagrams(phrases, diction, new ArrayList<>(), trimmed, max);

        return trimmed;
    }
    //recursive call: choose, explore, unchoose
    //Discovers anagrams by choosing exploring then unchoosing possibilities
    //Parameters: 
    //  LeterInventory reduced: remaining letters
    //  List<String> diction: dictionary words left
    //  List<String> current: current working words
    //  List<List<String>> trimmed: valid anagrams
    private void reviewAnagrams(LetterInventory reduced, List<String> diction, 
                                List<String> current, List<List<String>> trimmed, int max) {
        if (reduced.isEmpty()) {
            trimmed.add(new ArrayList<>(current));
        } else if (max == 0 || current.size() < max) {
            for (String word : diction) {
                LetterInventory subtracted = reduced.subtract(inventory.get(word));
                if(subtracted != null) {
                    current.add(word);
                    reviewAnagrams(subtracted, diction, current, trimmed, max);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    //This is your method that will use recursive backtracking to find combinations of words that
    //have the same letters as the given string. It should print to System.out all combinations 
    //of words from the dictionarry that are anagrams of s and that include at most max words 
    //(or unlimited number of words if max is 0). It should throw an IllegalArgumentException 
    //if max isw less than 0.
    //Prints all anagrams from phrase
    //Parameters:
    //  String s: user given expression
    //  int max: number of words allowed
    public void print(String s, int max) {
        List<List<String>> anagrams = getAnagrams(s, max);
        for (List<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    //Reduce dictionary words to dictionary of working anagrams
    //Parameters:
    //  LetterInventory phrases; remainder to find anagrams for
    //Returns:
    //  list<String> pruned: pruned dictionary words
    private List<String> pruneDictionary(LetterInventory phrases) {
        List<String> pruned = new ArrayList<>();
        for (String word : dictionary) {
            if (phrases.subtract(inventory.get(word)) != null) {
                pruned.add(word);
            }
        }
        return pruned;
    }
}

