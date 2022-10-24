package pass;

import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.System.exit;
import java.util.Scanner;

public class Pass {
    

    // **********Constantes
    static final int MIN_LEN_PASSWORD = 8;
    static final int MAX_LEN_PASSWORD = 120;
    
    static final String LETTERS_LOWER = "abcdefghijklmnopqrtsuvwxyz";
    static final String LETTERS_UPPER = LETTERS_LOWER.toUpperCase();
    static final String LETTERS_SYMBOLS = "!#$%&/()=?/*-+";
    static final String LETTERS_NUMBERS = "0123456789";
       
   
    // *********Variables
    private static int lenPassword;
    private static boolean isWithCapitalLetters;
    private static boolean isWithLowercase;
    private static boolean isWithSymbols;
    private static boolean isWithNumbers;
    private static String randomPassword;

    public static void main(String[] args) {
        mainScreen();
    }
    
    // ****************
    private static void mainScreen(){
        
        while(true){
        
            clearScreen();
            System.out.println(">>> Password Generator <<<");
            System.out.println("**************************");
            System.out.println();
            System.out.println("This program is responsible for generating a password randomly");
            System.out.println("Select one of the menu options to continue.");
            System.out.println();
            System.out.println("1.- Generate a New Password");
            System.out.println("2.- Show the last generated password");
            System.out.println("3.- Exit");

            //Options
            // ***************************
            int option = readOption();
            
            // Choose an Option
            // ****************************
            switch(option){
                case 1:
                    String newAlphabet = questionary();
                    randomPassword = generateNewPassword(newAlphabet);
                    clearScreen();
                    showMessage("The new Password is >>> " + randomPassword);
                    break;
                case 2:
                    clearScreen();
                    showMessage("Last Password was >>> " + randomPassword);
                    break;
                case 3:
                    System.out.println();
                    System.out.println("The user has finished with the program");
                    exit(0);
                default:
  
                    showMessage("ERROR: You must Choose an Option between 1 and 3");
            }        
        }                
    }

    private static void showMessage(String message){
        System.out.println();
        System.out.println(message);
        System.out.println("Press a key to continue...");
        pressAnyKeyToContinue();        
    }
 
    private static int readOption(){
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.print("Select an option >>> ");
        int option = scan.nextInt();
        System.out.println(option);
        
        return option;
    }
   
    private static void pressAnyKeyToContinue() 
    { 
        try {
            System.in.read();
        } catch (IOException ex) 
        {
        }
    }    

    // ***********************************************
    private static void clearScreen()
    {
        // Octal 33 == Hex 27
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }


    // ******************************************
    private static int questionLengthPassword()
    {
        Scanner scan;
        scan = new Scanner(System.in);
        int length_password; 
        
        String message = String.format(
                "Indicate the length in characters of the password (MIN=%d, MAX=%d)>>> ",
                MIN_LEN_PASSWORD,
                MAX_LEN_PASSWORD
        );
        
        while (true){
            System.out.print(message);
            length_password = scan.nextInt();        
            if (length_password >= MIN_LEN_PASSWORD && length_password <= MAX_LEN_PASSWORD ){
                break;
            }                
        }
        return length_password;
    }
    
    // ************************************
    private static boolean questionHaveCapitalLetters()
    {
        int option;
               
        Scanner scan;
        scan = new Scanner(System.in);
        
        while (true) {
            System.out.print("Would you like to include UpperCase? (Yes=1, NO=0) >>> ");
            option = scan.nextInt();        
            if (option == 1 || option == 0 ){
                break;
            }                
        }
        return (option == 1);
    }
  
    // ************************************************
    private static boolean questionHaveLowercase()
    {
        int option;
        
        Scanner scan;
        scan = new Scanner(System.in);
        
        while (true) {
        System.out.print("Would you like to include Lower Case? (Yes=1, NO=0) >>> ");
            option = scan.nextInt();        
            if (option == 1 || option == 0 ){
                break;
            }                
        }
        return (option == 1);
    }
    // **********************************************
    private static boolean questionHaveSymbols()
    {
        int option;
                
        Scanner scan;
        scan = new Scanner(System.in);
        
        while (true) {
            System.out.print("Would you like to include Symbols? (Yes=1, No=0) >>> ");
            option = scan.nextInt();        
            if (option == 1 || option == 0 ){
                break;
            }                
        }
        return (option == 1);
    }

    // *********************************************
    private static boolean questionHaveNumbers()
    {
        int option;
        
        Scanner scan;
        scan = new Scanner(System.in);
        
        while (true) {
            System.out.print("Would you like to include Numbers? (Yes=1, No=0) >>> ");
            option = scan.nextInt();        
            if (option == 1 || option == 0 ){
                break;
            }                
        }
        return (option == 1);
    }

    // **************************
    private static String questionary(){
        String alphabet = "";
        

        // ****************************************
        lenPassword = questionLengthPassword();
        isWithCapitalLetters = questionHaveCapitalLetters();
        isWithLowercase = questionHaveLowercase();
        isWithSymbols = questionHaveSymbols();
        isWithNumbers = questionHaveNumbers();
        

        // ************************
        if(lenPassword == 0){
            return "";
        }


        // *************************
        if (isWithCapitalLetters){
            alphabet += LETTERS_UPPER;
        }
        if (isWithLowercase){
            alphabet += LETTERS_LOWER;
        }
        if (isWithSymbols){
            alphabet += LETTERS_SYMBOLS;
        }
        if (isWithNumbers){
            alphabet += LETTERS_NUMBERS;
        }
        return alphabet;
    }

    public static String generateNewPassword(String newAlphabet)
    {
        String s = "";        
        int min_alphabet = 0;
        int max_alphabet = newAlphabet.length();
        char[] arrayAlphabet = newAlphabet.toCharArray();
        
        for (int count = 0; count < lenPassword; count++) {
            int position = randomInt(min_alphabet, max_alphabet);
            s += arrayAlphabet[position];            
        }
        return s;
    }

    // *************************************************************
    private static int randomInt(int mmin, int mmax)
    {
        return (int)(random() * ((mmax - mmin) + 1)) + mmin;
    }
}
    