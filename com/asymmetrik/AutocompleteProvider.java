package com.asymmetrik;

import java.util.TreeSet;

public class AutocompleteProvider {
    private static TreeSet<Candidate> candidateList = new TreeSet<Candidate>();

    //returns list of candidates ordered by confidence, then length (short -> long), then alphabet
    public static TreeSet<Candidate> getWords(String fragment) {
        TreeSet<Candidate> finalList = new TreeSet<Candidate>();

        //checks all candidates; returns those that start with the given fragment
        for (Candidate candidate : candidateList) {
            String potential = candidate.getWord();

            if (potential.startsWith(fragment)) {
                finalList.add(candidate);
            }
        }
        return finalList;
    }

    //Takes line from user, converts words to candidates, updates data with new candidates,
    //and increases confidence of pre-existing candidates
    public static void train(String passage) {
        //cleans up input text by removing punctuation
        //but retaining word internal apostrophes and dashes
        passage = passage.replaceAll("[\\p{Punct}&&[^'-]]", "");

        for (String word : passage.split(" ")) {
            word = word.replaceAll("^'|'$|^-|-$", "");

            //retains I as necessarily capital word in English; lower cases all
            //other words to prevent duplicate entries
            if (!word.equals("I")) {
                word = word.toLowerCase();
            }
            //capitalizes solo lowercase "i" to prevent duplicate "I" and "i"
            if(word.equals("i")) {
                word = word.toUpperCase();
            }

            //updates pre-existing candidates
            boolean hasWord = false;
            for(Candidate candidate : candidateList) {
                if(candidate.isEqual(word)) {
                    hasWord = true;
                    candidate.increaseConfidence();
                    break;
                }
            }

            //adds new candidates as needed
            if (!hasWord) {
                candidateList.add(new Candidate(word));
            }
        }
    }
}