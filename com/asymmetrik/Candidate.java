package com.asymmetrik;

public class Candidate implements Comparable<Candidate> {
    private String word;
    private int confidence;

    public Candidate(String word) {
        this.word = word;
        this.confidence = 1;
    }

    //returns candidate word
    public String getWord() {
        return this.word;
    }

    //returns numerical confidence of candidate word
    public int getConfidence() {
        return this.confidence;
    }

    //increment confidence
    public void increaseConfidence() {
        this.confidence += 1;
    }

    //checks if word is equal to current object's word
    public boolean isEqual(String word) {
        return this.word.equals(word);
    }

    /*
    //checks if candidate's word is equal to current object's word
    public boolean isEqual(Candidate candidate) {
        return this.word.equals(candidate.getWord());
    }
     */

    @Override
    //sort by confidence, then length (short -> long), then alphabet
    public int compareTo(Candidate candidate) {
        int result;
        //same word returns 0; equal
        if (this.word.equals(candidate.getWord())) {
            return 0;
        }
        //if not same, greater confidence wins
        else {
            result = candidate.getConfidence() - this.confidence;
        }

        //if equal confidence, shorter word wins
        if (result == 0) {
            result = this.word.length() - candidate.getWord().length();
        }

        //if words are same length, result is still 0, so alphabetize
        //since they are different words, result will not be 0, and no further tests are needed
        if (result == 0) {
            result = this.word.compareTo(candidate.getWord());
        }
        return result;
    }

}