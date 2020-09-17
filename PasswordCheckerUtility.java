package sample;

import java.util.ArrayList;
import java.util.regex.Pattern;
import  java.util.regex.Matcher;

public final class PasswordCheckerUtility extends java.lang.Object{

    static boolean isValidPassword(String passwordString) throws LengthException, NoDigitException, NoUpperAlphaException,
            NoLowerAlphaException, NoSpecialSymbolException, InvalidSequenceException{

        boolean validPass;
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(passwordString);
        boolean specialCharas = matcher.matches();

        Pattern lowercase = Pattern.compile(".*[a-z].*");
        Matcher lcMatcher = lowercase.matcher(passwordString);
        boolean containsLC = lcMatcher.matches();

        Pattern upperCase = Pattern.compile(".*[A-Z].*");
        Matcher ucMatcher = upperCase.matcher(passwordString);
        boolean containsUC = ucMatcher.matches();

        Pattern digit = Pattern.compile(".*[0-9].*");
        Matcher digitMatcher = digit.matcher(passwordString);
        boolean containsDigit = digitMatcher.matches();

        char[] passArray = passwordString.toCharArray();
        boolean repeatingCharas = false;

        for(int counter = 0; counter < passArray.length; counter++){
            if((passArray[counter] == passArray[counter + 1]) && (passArray[counter] == passArray[counter + 2])){
                repeatingCharas = true;
            }
        }


        if(passwordString.length() < 6){
            throw new LengthException("The password must be at least 6 characters long");
        }

        else if(!specialCharas){
            throw new NoSpecialSymbolException("The password must contain at least one special character");
        }

        else if(!containsLC){
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
        }

        else if(!containsUC){
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        }

        else if(!containsDigit){
            throw new NoDigitException("The password must contain at least one digit");
        }

        else if(repeatingCharas){
            throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
        }

        else{
            validPass = true;
        }
        return validPass;
    }

    static boolean isWeakPassword(String passwordString){
        if(passwordString.length() >= 6 && passwordString.length() <= 9){
            return true;
        }
        else{
            return false;
        }
    }

    static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){

        ArrayList<String> checkedPasswords = new ArrayList<>();

        for(int x = 0; x < passwords.size(); x++){

            String currentPass = passwords.get(x);


            Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
            Matcher matcher = pattern.matcher(currentPass);
            boolean specialCharacter = matcher.matches();

            Pattern lowercase = Pattern.compile(".*[a-z].*");
            Matcher lcMatcher = lowercase.matcher(currentPass);
            boolean containsLC = lcMatcher.matches();

            Pattern upperCase = Pattern.compile(".*[A-Z].*");
            Matcher ucMatcher = upperCase.matcher(currentPass);
            boolean containsUC = ucMatcher.matches();

            Pattern digit = Pattern.compile(".*[0-9].*");
            Matcher digitMatcher = digit.matcher(currentPass);
            boolean containsDigit = digitMatcher.matches();

            char[] passArray = currentPass.toCharArray();
            boolean repeatingCharas = false;

            for(int counter = 0; counter < passArray.length; counter++){
                if((passArray[counter] == passArray[counter + 1]) && (passArray[counter] == passArray[counter + 2])){
                    repeatingCharas = true;
                }
            }

            if(currentPass.length() < 6){
                checkedPasswords.add(currentPass + " The password must be at least 6 characters long");
            }
            else if(!specialCharacter){
                checkedPasswords.add(currentPass + " The password must contain at least one special character");
            }
            else if(!containsLC){
                checkedPasswords.add(currentPass + " The password must contain at least one lowercase alphabetic character");
            }
            else if(!containsUC){
                checkedPasswords.add(currentPass + " The password must contain at least one uppercase alphabetic character");
            }
            else if(!containsDigit){
                checkedPasswords.add(currentPass + " The password must contain at least one digit");
            }
            else if(repeatingCharas){
                checkedPasswords.add(currentPass + " The password cannot contain more than two fo the same character in sequence");
            }
        }




        return checkedPasswords;
    }


}
