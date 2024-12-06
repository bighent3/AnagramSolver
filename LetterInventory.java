//Henton Hailey-Marshall
//CSE 143 AA with Ido Avnon
//Homework A3: LetterInventory

//This class accepts a string, reads in the characters and adds them to an alphabetically order
//array. For each spot in the array where a letter corresponds to the number of each letter
//present in that array is stored. 
public class LetterInventory {
    //Constructs an inventory (a count) or the alphabetic letters in the given string, ignoring 
    //the case of letters and ignoring any non-alphabetic characters.
    public static final int NUM_OF_LETTERS = 26;
    public static final int ASCII_DIFF =  97;
    public static final int LETTER_Z = 25;

    //Fields:
    private int[] letterValues;
    private int size;

    //Empty Constructor calls constructor with empty string
    public LetterInventory() {
        this("");
    }

    //Pre: Constructor that accepts string parameter of data to be processed
    //Post: Creates an array the length of the number of letters in the alphabet. Formats letters
    //to have the count of them stored in mentioned array. Size of array is updated.
    public LetterInventory(String data) {
        letterValues = new int[NUM_OF_LETTERS];
        size = 0;
        data = data.toLowerCase();
        for (int i = 0; i < data.length(); i++) {
            char ltr = data.charAt(i);
            int ascii = (int) ltr - ASCII_DIFF;
            if (ascii >= 0 && ascii <= LETTER_Z) {
                letterValues[ascii]++;
                size++;    
            }
        }
    }

    //Pre: accepts parameter of a letter, formats letter for processing.
    //Post: throws illegal argument if a non alphabet character is used. Returns number of
    //occurences of that letter in the string
    //Returns a count of how many of this letter are in the inventory. Letter might be lowercase 
    //or uppercase (your method shouldnt care). If a nonalphabetic character is passed, your 
    //method should throw an IllegalArgumentException.
    public int get(char letter) {
        letter = Character.toLowerCase(letter);
        int asciiLetter = (int) letter - ASCII_DIFF;
        if (asciiLetter < 0 || asciiLetter > LETTER_Z) {
            throw new IllegalArgumentException("Symbol given must be a letter");    
        }
        return letterValues[asciiLetter];
    }

    //Pre:accepts parameter of letter and value to be assigned to that character
    //Post:throws illegal argument exception if not alphabet character is given.
    //has no returns
    //Sets the count for the given letter to the given value. Letter might be lowecase or 
    //uppercase. If a nonalphabetic character is passed or if value is negative, your 
    //method should throw an IllegalArgumentExceptio9n
    public void set(char letter, int value) {
        letter = Character.toLowerCase(letter);
        int asciiLetter = (int) letter - ASCII_DIFF;
            if (asciiLetter < 0 || asciiLetter > LETTER_Z) {
                throw new IllegalArgumentException("Symbol given must be a letter");    
            }
            int minus = letterValues[asciiLetter];
            size = size - minus + value;
            letterValues[asciiLetter] = value;
    }

    //Post: returns size of number of letters in string
    //Returns the sum of all tof the counts in this inventory. This operation should be fast in 
    //that it should not need to examine each of the 36 counts when it is called.
    public int size() {
        return size;
    }

    //post: returns whether or not string is empty
    //Returns true if this inventory is empty (all counts are 0). This operation should be fast 
    //in that it should not need to examine each of the 26 counts when it is called.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns a bracketized string of letters with each letter present shown the number of times 
    //it appears in the string and in alphabetical order.
    //Returns a string representation of the inventory with the letters all in lowercase and in 
    //sorted order and surrounded by square brackets. The number of occurences of each letter 
    //should match its count in the inventory. For example an inventory of 4 a's, 1 b, 1 ; and 
    //1 m would be represented as "[aaaablm]".
    public String toString() {
        String inventory = "[";
        for(int i = 0; i < letterValues.length; i++) {
            for(int j = 0; j < letterValues[i]; j++) {
                char toAscii = (char) (i + ASCII_DIFF);
                inventory = inventory + toAscii;
            }
        }
        inventory = inventory + "]";
        return inventory;
    }

    //Pre accepts LetterInventory object other to be added to initial object. 
    //Post: adds letter values and returns new array of letter counts
    //into one array of 26 charcters and returns that new array
    //Constructs and returns a new LetterInventory object that represents the sum of this letter 
    //inventory and the other given LetterInventory. THe counts for each letter should be added 
    //together. The two LetterInventory objects being added together (this and other) should not 
    //be changed by this method.
    public LetterInventory add(LetterInventory other) {
        LetterInventory addedValues = new LetterInventory();
        for(int i = 0; i <= LETTER_Z; i++) {
            addedValues.letterValues[i] = this.letterValues[i] + other.letterValues[i];
        }
        addedValues.size = this.size + other.size;
        return addedValues;
    }

    //Pre: accepts LetterInventory object and subtracts it from this object
    //Post: If any values go negative values go to null. null is returned
    //Constructs and returns a new LetterInventory object that represents the result of 
    //subtracting the other inventory from this inventory (i.e., subtracting the counts in the 
    //other inventory from this object's count). If any resulting count would be negative your 
    //method should return null. The two LetterInventory objects being subtracted (this and 
    //other) should not be changed by this method.
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory subtractedValues = new LetterInventory();
        for(int i = 0; i <= LETTER_Z; i++) {
            subtractedValues.letterValues[i] = this.letterValues[i] - other.letterValues[i];
            if (subtractedValues.letterValues[i] < 0) {
                return null;
            }
        }
        subtractedValues.size = 0;
        for(int j = 0; j <= LETTER_Z; j++) {
            subtractedValues.size = subtractedValues.size + subtractedValues.letterValues[j];
        }
        return subtractedValues;
    }

    //Testing only Please Ignore
    // public static void main(String[] args) {
    //     LetterInventory inventory1 = new LetterInventory("massive pottato salda");
    //     System.out.println(inventory1);
    //     System.out.println(inventory1.size);
    // }
}
