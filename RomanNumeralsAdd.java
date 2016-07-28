package romannumeralsadd;

/**
 * 
 * @author Shreyas Bhat
 * Date - 22 Jul, 2016
 * 
 * 
 * Program to add 2 Roman Numerals without converting them to decimal radix or any other radix
 * 
 * Logic as per the addition algorithm mentioned at http://turner.faculty.swau.edu/mathematics/materialslibrary/roman/
 * 
 * Steps - 
 * 1. Validate input until the user enters valid input.
 * Validation done against the Pattern - ^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$
 * 
 * 2. Substitute for any subtractives to the substituted Numerals 
 * substituteSubtractives(num1) - Pass the original input to obtain the subtractives substituted in the Numeral
 * HashMap used to demonstrate its one use.
 * 
 * CCCLXIX will be substituted with CCCLXVIIII
 * 
 * write	instead of	value
 * ----------------------------------
 * IV           IIII            4
 * IX           VIIII           9
 * XL           XXXX            40
 * XC           LXXXX           90
 * CD           CCCC            400
 * CM           DCCCC           900
 * 
 * 3. Catenate catenateNumbers(String, String) the 2 numerals to obtain the catenatedString using the StringBuilder class
 * 
 * 4. Instead of custom sorting and combining the concatenated String, the characters are counted and string is generated based on the count
 * 
 * 5. The number groups are then merged starting from Units place
 *  IIIII replaced with V then
 *  VV    replaced with X then
 *  XXXXX replaced with L then
 *  LL    replaced with C then
 *  CCCCC replaced with D then
 *  DD    replaced with M.
 * 
 * 6. The subtractives left are then replaced with the corresponding numbers to obtain the final reduced result.
 *  Non-HashMap use demonstrated here
 */


import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RomanNumeralsAdd {
    
    /**
     * String array containing all possible subtractives and their replacements
     */
    static String[] items = new String[]{"IV", "IX", "XL", "XC", "CD", "CM", "IIII", "VIIII", "XXXX", "LXXXX", "CCCC", "DCCCC"};

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a Valid Roman Numeral-");
        String num1 = reader.nextLine();
        
        // Validate input until the user enters valid input.
        if (!RomanNumeralsAdd.validateInput(num1)) {
            do {
                System.out.println("The Numeral you entered was invalid. Please enter a valid Numeral...");
            } while (!RomanNumeralsAdd.validateInput(num1 = reader.nextLine()));
        }
        
        System.out.println("Enter another Valid Roman Numeral to add to the previous one-");
        String num2 = reader.nextLine();
        
        // Validate input until the user enters valid input.
        if (!RomanNumeralsAdd.validateInput(num2)) {
            do {
                System.out.println("The Numeral you entered was invalid. Please enter a valid Numeral...");
            } while (!RomanNumeralsAdd.validateInput(num2 = reader.nextLine()));
        }
        
        System.out.println("The First Numeral-" + num1);
        System.out.println("The Second Numeral-" + num2);
        
        //
        String substitutedNum1 = RomanNumeralsAdd.substituteSubtractives(num1);
        //System.out.println("substitutedNum1-" + substitutedNum1);
        
        String substitutedNum2 = RomanNumeralsAdd.substituteSubtractives(num2);
        //System.out.println("substitutedNum2-" + substitutedNum2);
        
        String catenatedString = RomanNumeralsAdd.catenateNumbers(substitutedNum1, substitutedNum2);
        //System.out.println("catenatedString-" + catenatedString);
        
        String combinedString = RomanNumeralsAdd.combineString(catenatedString);
        //System.out.println("combinedString-" + combinedString);
        
        String finalCombinedString = RomanNumeralsAdd.finalCombineString(combinedString);
        //System.out.println("finalCombinedString-" + finalCombinedString);
        
        String result = RomanNumeralsAdd.reverseSubstituteSubtractives(finalCombinedString);
        System.out.println("Result-" + result);
    }

    /**
     * Method to validate the given Roman numeral input
     * 
     * @param input
     * @return 
     */
    private static boolean validateInput(String input) {
        /**
         * Pattern matching to check for the input Roman Numeral
         */
        Pattern pattern = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        return pattern.matcher(input).matches();
    }
    
    /**
     * Substitute for any subtractives in the input Roman Numeral
     * 
     * @param num
     * @return 
     */
    private static String substituteSubtractives(String num) {
        
        /**
         * HashMap to store the mappings between subtractives and their replacements
         */
        HashMap<String, String> subtractives = new HashMap<>();
        subtractives.put("IV", "IIII");
        subtractives.put("IX", "VIIII");
        subtractives.put("XL", "XXXX");
        subtractives.put("XC", "LXXXX");
        subtractives.put("CD", "CCCC");
        subtractives.put("CM", "DCCCC");
        String output = num;
        
        /**
         * find the subtractives from a list and for that subtractive, 
         * substitute it with the corresponding String
         * IV replaced with IIII
         */
         
        switch (RomanNumeralsAdd.stringContainsItemFromList(num, items)) {
            case "IV": {
                output = num.replace("IV", (CharSequence)subtractives.get("IV"));
                break;
            }
            case "IX": {
                output = num.replace("IX", (CharSequence)subtractives.get("IX"));
                break;
            }
            case "XL": {
                output = num.replace("XL", (CharSequence)subtractives.get("XL"));
                break;
            }
            case "XC": {
                output = num.replace("XC", (CharSequence)subtractives.get("XC"));
                break;
            }
            case "CD": {
                output = num.replace("CD", (CharSequence)subtractives.get("CD"));
                break;
            }
            case "CM": {
                output = num.replace("CM", (CharSequence)subtractives.get("CM"));
            }
        }
        return output;
    }

    /**
     * Method to concatenate two Strings given as input
     * Returns - concatenated String
     * 
     * @param num1
     * @param num2
     * @return 
     */
    private static String catenateNumbers(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        sb.append(num1);
        sb.append(num2);
        return sb.toString();
    }

    /**
     * Method to replace the leftover subtractive in the final combined String
     * 
     * HashMap not used to demonstrate the other way possible
     * 
     * @param combinedString
     * @return 
     */
    private static String reverseSubstituteSubtractives(String combinedString) {
        String output = combinedString;
        loop : while (!"".equals(RomanNumeralsAdd.stringContainsItemFromList(output, items))) {
            switch (RomanNumeralsAdd.stringContainsItemFromList(combinedString, items)) {
                case "IIII": {
                    output = combinedString.replace("IIII", "IV");
                    break loop;
                }
                case "VIIII": {
                    output = combinedString.replace("VIIII", "IX");
                    break loop;
                }
                case "XXXX": {
                    output = combinedString.replace("XXXX", "XL");
                    break loop;
                }
                case "LXXXX": {
                    output = combinedString.replace("LXXXX", "XC");
                    break loop;
                }
                case "CCCC": {
                    output = combinedString.replace("CCCC", "CD");
                    break loop;
                }
                case "DCCCC": {
                    output = combinedString.replace("DCCCC", "CM");
                    break loop;
                }
                default:
                    break loop;
            }
        }
        return output;
    }

    /**
     * Method to count the number of characters in the Concatenated String
     * Then generate the String back from the Counts
     * 
     * This was used instead of custom sorting the String
     * 
     * @param catenatedString
     * @return 
     */
    private static String combineString(String catenatedString) {
        int ICount = RomanNumeralsAdd.countCharacter(catenatedString, 'I');
        int VCount = RomanNumeralsAdd.countCharacter(catenatedString, 'V');
        int XCount = RomanNumeralsAdd.countCharacter(catenatedString, 'X');
        int LCount = RomanNumeralsAdd.countCharacter(catenatedString, 'L');
        int CCount = RomanNumeralsAdd.countCharacter(catenatedString, 'C');
        int DCount = RomanNumeralsAdd.countCharacter(catenatedString, 'D');
        int MCount = RomanNumeralsAdd.countCharacter(catenatedString, 'M');
        String IString = RomanNumeralsAdd.generateString('I', ICount);
        String VString = RomanNumeralsAdd.generateString('V', VCount);
        String XString = RomanNumeralsAdd.generateString('X', XCount);
        String LString = RomanNumeralsAdd.generateString('L', LCount);
        String CString = RomanNumeralsAdd.generateString('C', CCount);
        String DString = RomanNumeralsAdd.generateString('D', DCount);
        String MString = RomanNumeralsAdd.generateString('M', MCount);
        StringBuilder combinedString = new StringBuilder();
        return combinedString.append(MString).append(DString).append(CString).append(LString).append(XString).append(VString).append(IString).toString();
    }

    /**
     * Method to find if substring is present in the Input String
     * 
     * Example - returns 'IV' if 'CIV' is given as input
     * 
     * items[] contains all the possible subtractives.
     * 
     * @param inputString
     * @param items
     * @return 
     */
    public static String stringContainsItemFromList(String inputString, String[] items) {
        for (String item : items) {
            if (!inputString.contains(item)) continue;
            return item;
        }
        return "";
    }

    /**
     * Method to obtain count of a specific character in a String
     * 
     * @param catenatedString
     * @param c
     * @return 
     */
    private static int countCharacter(String catenatedString, char c) {
        int counter = 0;
        for (int i = 0; i < catenatedString.length(); ++i) {
            if (catenatedString.charAt(i) != c) continue;
            ++counter;
        }
        return counter;
    }

    /**
     * Method to obtain String of characters of character 'c' for a 'count'
     * 
     * @param c
     * @param count
     * @return 
     */
    private static String generateString(char c, int count) {
        StringBuilder outputBuffer = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            outputBuffer.append(c);
        }
        return outputBuffer.toString();
    }

    /**
     * Method to obtain final reduced String after replacing all groups
     * 
     * 'VV' can be replaced with 'X'
     * 
     * Replacements start from the Units place - start with 'I' till 'M'
     * 
     * @param combinedString
     * @return 
     */
    private static String finalCombineString(String combinedString) {
        String finalCombineString1 = combinedString.replace("IIIII", "V");
        String finalCombineString2 = finalCombineString1.replace("VV", "X");
        String finalCombineString3 = finalCombineString2.replace("XXXXX", "L");
        String finalCombineString4 = finalCombineString3.replace("LL", "C");
        String finalCombineString5 = finalCombineString4.replace("CCCCC", "D");
        String finalCombineString6 = finalCombineString5.replace("DD", "M");
        return finalCombineString6;
    }
}
