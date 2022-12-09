package com.example.encryptdecrypt;

public abstract class Mode {
    /*constants for bounds checking and stuff
    * 65-90 are the ASCII values for a-z
    * 97-122 are the ASCII values for A-Z
    */
    final static int U_CASE_LOWER = 65, U_CASE_UPPER = 90;
    final static int CRYPT_CONSTANT = 65;
    final static int L_CASE_LOWER = 97, L_CASE_UPPER = 122;
    final static int CONSTRAINT_NO = 26;
    protected static char charToAppend;

    //functions used to check whether ASCII value of charToCheck is within alphabet range
    private boolean withinRange(int charToCheck, int lowerBound, int upperBound){
        return charToCheck >= lowerBound && charToCheck <= upperBound;
    }
    protected abstract boolean withinRange(int charToCheck, int bound);

    //abstract function used to shift the value of a character
    protected abstract char changeChar(char codeWordChar, int lowerBound, int upperBound);

    //function that does the conversion, using the abstract changeChar method
    public String conversion(String codeWord, String originalMessage){
        StringBuilder resultString = new StringBuilder();
        int codeWordIterator = 0;

        //while the end of the original message has not been reached
        for (int i = 0; i < originalMessage.length(); i++){

            //resets codeWordIterator if greater than the length of the codeword
            if (codeWordIterator >= codeWord.length()){
                codeWordIterator -= codeWord.length();
            }
            charToAppend = (char)(originalMessage.charAt(i) - CRYPT_CONSTANT);

            //if charToAppend is uppercase
            if (withinRange(originalMessage.charAt(i), U_CASE_LOWER, U_CASE_UPPER)) {
                charToAppend = changeChar(codeWord.charAt(codeWordIterator), U_CASE_LOWER, U_CASE_UPPER);
                codeWordIterator++;
            }
            //if charToAppend is lowercase
            else if(withinRange(originalMessage.charAt(i), L_CASE_LOWER, L_CASE_UPPER)){
                charToAppend = changeChar(codeWord.charAt(codeWordIterator), L_CASE_LOWER, L_CASE_UPPER);
                codeWordIterator++;
            }
            //if charToAppend is not a letter
            else{
                charToAppend += CRYPT_CONSTANT;
            }
            //add the changed char to resultString
            resultString.append(charToAppend);
        }
        return resultString.toString();
    }
}
