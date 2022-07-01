package org.ylplabs.encorachallenge.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MainChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantityStrings = scanner.nextInt();
        for (int i = 0; i < quantityStrings; i++) {
            String characters = scanner.next();
            System.out.println(sortingOperations(characters));
        }
    }

    public static String sortingOperations(String input) {
        char[] chars = input.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char character : chars) {
            characters.add(character);
        }
        Set<CountChars> countCharsSet = new HashSet<>();
        for (char character : characters) {
            long count = characters.stream().filter(element -> element == character).count();
            countCharsSet.add(new CountChars(character, count));
        }
        Set<CountChars> charsSet = countCharsSet.stream().sorted((o1, o2) -> {
            int compare = Long.compare(o2.quantity, o1.quantity);
            return compare == 0 ? Character.compare(o1.element, o2.element) : compare;
        }).collect(Collectors.toCollection(LinkedHashSet::new));
        StringBuilder builder = new StringBuilder();
        charsSet.forEach(countChars -> {
            for (int i = 0; i < countChars.quantity; i++) {
                builder.append(countChars.element);
            }
        });
        return builder.toString();
    }

    public static class CountChars {

        char element;

        long quantity;

        public CountChars(char element, long quantity) {
            this.element = element;
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CountChars that = (CountChars) o;
            return element == that.element;
        }

        @Override
        public int hashCode() {
            return element;
        }
    }
}
