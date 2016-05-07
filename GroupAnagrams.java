import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 Given a set of random string, write a function that returns a set that groups all the anagrams together.

 For example, suppose that we have the following strings:
 “cat”, “dog”, “act”, “door”, “odor”

 Then we should return these sets: {“cat”, “act”}, {“dog”}, {“door”, “odor”}.

 */
public class GroupAnagrams {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("dog");
        list.add("act");
        list.add("door");
        list.add("odor");

        for(List<String> mylist : groupAnagrams(list)) {
            for(String s : mylist) {
                System.out.print(s);
            }
            System.out.println();
        }
    }

    /**
     *
     * Algorithm Design
     * - for each string, count number of chars in a hashmap - O(s), where s is the length of the string
     *  - create a new list to store the strings with the same chars (are anagrams) O(1)
     *  - then search through the remainder of the list of strings counting number of chars O(n), where n is the size
     *    if the list
     *  - if the chars have the same number of chars then add it into the same list O(1)
     *
     *  Time: O(n ** m), where n is the size of the list of strings and m is the size of each string
     *  Space: O(m), where n is the size of the strings
     *
     *
     * @param setStrings - a list of strings
     * @return - a list of anagrams that are grouped together
     */
    static List<List<String>> groupAnagrams(List<String> setStrings) {
        if (setStrings == null || setStrings.size() == 0) return new ArrayList<>();

        List<List<String>> result = new ArrayList<>();

        // iterate through the entire list of strings
        for (int i = 0; i < setStrings.size(); i++) {
            String tmp = setStrings.get(i);
            List<String> newGroup = new ArrayList<>();
            newGroup.add(tmp); // add new string into this list
            Map<Character, Integer> map = new HashMap<>();
            boolean isNotSame = false;

            // insert the characters of our string into a map
            for (int j = 0; j < tmp.length(); j++) {
                if (map.containsKey(tmp.charAt(j))) {
                    int val = map.get(tmp.charAt(j));
                    map.put(tmp.charAt(j), ++val);
                } else {
                    map.put(tmp.charAt(j), 1);
                }
            }

            // check the rest of the list for anagrams
            for (int k = i + 1; k < setStrings.size(); k++) {
                isNotSame = false;
                String tmpStr = setStrings.get(k);
                Map<Character, Integer> tmpMap = new HashMap<>();

                // insert characters of tmpStr into the map
                for (int l = 0; l < tmpStr.length(); l++) {
                    if (tmpMap.containsKey(tmpStr.charAt(l))) {
                        int val = tmpMap.get(tmpStr.charAt(l));
                        tmpMap.put(tmpStr.charAt(l), ++val);
                    } else {
                        tmpMap.put(tmpStr.charAt(l), 1);
                    }
                }

                // compare the map to the tmpMap to see if the <K, V> are the same (boolean)
                // if not, continue on to the next iteration
                if (map.size() != tmpMap.size()) continue;

                for (Character c : map.keySet()) {
                    // check if the tmpMap has the same key
                    if (!tmpMap.containsKey(c)) {
                        isNotSame = true;
                        break;
                    } else {
                        // check if both maps have the same value for each key
                        if (map.get(c) != tmpMap.get(c)) {
                            isNotSame = true;
                            break;
                        }
                    }
                }

                // add string into list if all <K,V> are the same
                if(!isNotSame) newGroup.add(setStrings.remove(k));
            }

            // after iterating through the rest of the list add it to the main list
            result.add(newGroup);
        }
        return result;
    }
}
