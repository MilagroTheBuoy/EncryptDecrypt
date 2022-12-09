package com.example.encryptdecrypt;

public class Decrypt extends Mode{
    //Some day, I have to sse aside some time to re-explain to myself exactly how the math of this works
    @Override
    protected char changeChar(char codeWordChar, int lowerBound, int upperBound) {
        charToAppend -= (codeWordChar - CRYPT_CONSTANT);
        charToAppend += CRYPT_CONSTANT;
        if (!(withinRange(charToAppend, lowerBound))){
            charToAppend +=CONSTRAINT_NO;
        }
        return charToAppend;
    }
    //Nothing crazy, just some bounds checking for wrap around stuff(I think)
    public boolean withinRange(int charToCheck, int bound){
        return charToCheck >= bound;
    }
}
