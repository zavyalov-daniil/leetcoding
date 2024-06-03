package arrays.hashing;

import java.util.List;
import java.util.stream.Collectors;

public class StringEncodeAndDecode {
    public static void main(String[] args) {
        List<String> list = List.of("neet","code","love","you");
        String encoded = encode(list);
        assert decode(encoded).equals(list);
        List<String> list2 = List.of("we","say",":","yes");
        String encoded2 = encode(list2);
        assert decode(encoded2).equals(list2);
        List<String> list3 = List.of("");
        String encoded3 = encode(list3);
        assert decode(encoded3).equals(list3);
        List<String> list4 = List.of();
        String encoded4 = encode(list4);
        assert decode(encoded4).equals(list4);
    }

    //TODO: solution with StringBuilder
    public static String encode(List<String> strs) {
        if (strs.isEmpty()) {
            return null;
        }
        return String.join("|", strs);
    }

    public static List<String> decode(String str) {
        if (str == null) {
            return List.of();
        }
        return List.of(str.split("\\|", -42));
    }
}