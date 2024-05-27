package arrays.hashing;

import java.util.HashMap;

public class ValidAnagram {
    public static void main(String[] args) {
        assert isAnagram("anagram", "nagaram");

        assert !isAnagram("rat", "car");
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alp = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            alp[character - 'a'] += 1;
            character = t.charAt(i);
            alp[character - 'a'] -= 1;
        }

        for (int j : alp) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> elements = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            elements.put(character, elements.getOrDefault(character, 0) + 1);
            character = t.charAt(i);
            elements.put(character, elements.getOrDefault(character, 0) - 1);
        }

        for (int value : elements.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}