package two.pointers;

public class ValidPalindrome {
    public static void main(String[] args) {
        assert isPalindrome("A man, a plan, a canal: Panama");
        assert isPalindrome("aboba");
        assert isPalindrome(" ");
        assert !isPalindrome("race a car");
    }

    //faster
    //2 ms without regex
    public static boolean isPalindrome(String s) {
        int p1 = 0;
        int p2 = s.length() - 1;
        while (p1 < p2) {
            char char1 = s.charAt(p1);
            if (!Character.isLetterOrDigit(char1)) {
                ++p1;
                continue;
            }
            char char2 = s.charAt(p2);
            if (!Character.isLetterOrDigit(char2)) {
                --p2;
                continue;
            }
            ++p1;
            --p2;
            if (Character.toLowerCase(char1) != Character.toLowerCase(char2)) {
                return false;
            }
        }
        return true;
    }

    //14 ms with regex
    public static boolean isPalindrome1(String s) {
        String s1 = s.replaceAll("[^0-9A-Za-z]", "").toLowerCase();
        int l = s1.length();
        for (int i = 0; i < l/2; i++) {
            if (s1.charAt(i) != s1.charAt(l-i-1)) {
                return false;
            }
        }
        return true;
    }
}
