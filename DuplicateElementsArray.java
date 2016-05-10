import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**

 Given an array of string, find duplicate elements.

 For instance, in array [“abc”, “dd”, “cc”, “abc”, “123”], the duplicate element is “abc”.
 Let’s start with this simple scenario and I’ll cover more follow-up questions soon.
 Also, as before, we only select questions that are asked by top companies.
 This one was asked by Google, Facebook, Amazon recently.

 */
public class DuplicateElementsArray {

    public static void main(String[] args) {
        List<String> elements = new ArrayList<>();
        elements.add("abc");
        elements.add("dd");
        elements.add("cc");
        elements.add("abc");
        elements.add("123");
        List<String> result = findDuplicates(elements);
        for(String s : result) System.out.println(s);
    }

    /**
     *
     * Algorithm Design
     *
     * Time: O(N), where N is the total number of string in the elements list
     * Space: O(S), where S is the number of unique strings
     *
     * - Iterate through the list of elements and insert them into a HashSet
     *      - Check if the element is already in the set
     *          - If an element is in a set then add it into our result list
     *      - Add each element into the set
     * - Return the result list that contains the duplicate elements
     *
     * @param elements - the list of elements to search through
     * @return - the duplicate elements from the list
     */
    static List<String> findDuplicates(List<String> elements) {
        if (elements.size() == 0 || elements == null) return null;
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(String s : elements) {
            if(set.contains(s)) result.add(s);
            set.add(s);
        }

        return result;
    }

}
