/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = 0;
        for (String i : asciis) {
            if (i.length() > max) {
                max = i.length();
            }
        }
        String[] temp = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            temp[i] = asciis[i];
        }
        for (int i = max - 1; i >= 0; i--) {
            sortHelperLSD(temp, i);
        }
        return temp;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] R = new int[256];
        int[] T = new int[256];
        for (String i : asciis) {
            int temp ;
            if (i.length() < index + 1) {
                temp = 0;
            } else {
                char c = i.charAt(index);
                temp = c;
            }
            R[temp] += 1;
        }
        int pos = 0;
        for (int i = 0; i < 256; i += 1) {
            T[i] = pos;
            pos += R[i];
        }
        String[] sorted = new String[asciis.length];
        for (String i : asciis) {
            int a ;
            if (i.length() < index + 1) {
                a = 0;
            } else {
                char c = i.charAt(index);
                a = c;
            }
            sorted[T[a]] = i;
            T[a] += 1;
        }
        System.arraycopy(sorted, 0, asciis, 0, asciis.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
