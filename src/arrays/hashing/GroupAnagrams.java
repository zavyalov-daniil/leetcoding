package arrays.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GroupAnagrams {
    public static void main(String[] args) {
        List<List<String>> res = groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
        assert res.size() == 3;
        for (List<String> group : res) {
            assert group.size() == 1 && group.contains("bat") ||
                    group.size() == 2 && new HashSet<>(group).containsAll(List.of("nat","tan")) ||
                    group.size() == 3 && new HashSet<>(group).containsAll(List.of("ate","eat","tea"));
        }
        res = groupAnagrams(new String[] { "" });
        assert res.size() == 1;
        assert res.getFirst().size() == 1 && res.getFirst().contains("");

        res = groupAnagrams(new String[] { "a" });
        assert res.size() == 1;
        assert res.getFirst().size() == 1 && res.getFirst().contains("a");
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> res = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (res.containsKey(key)) {
                res.get(key).add(str);
            } else {
                ArrayList<String> newGroup = new ArrayList<>();
                newGroup.add(str);
                res.put(key, newGroup);
            }
        }
        return List.copyOf(res.values());
    }
}