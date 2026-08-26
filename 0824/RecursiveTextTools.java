public class RecursiveTextTools {

    static String reverse(String text) {
        if (text == null || text.length() <= 1) {
            return text;
        }

        return reverse(text.substring(1)) + text.charAt(0);
    }

    static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String clean = text.replace(" ", "").toLowerCase();

        return isPalindrome(clean, 0, clean.length() - 1);
    }

    static boolean isPalindrome(String text, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindrome(text, left + 1, right - 1);
    }

    static int countCharacter(String text, char target) {
        if (text == null || text.length() == 0) {
            return 0;
        }

        int count = 0;

        if (text.charAt(0) == target) {
            count = 1;
        }

        return count + countCharacter(text.substring(1), target);
    }

    public static void main(String[] args) {
        System.out.println("reverse:");
        System.out.println(reverse(""));
        System.out.println(reverse("A"));
        System.out.println(reverse("Hello"));

        System.out.println();

        System.out.println("isPalindrome:");
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("A"));
        System.out.println(isPalindrome("Level"));
        System.out.println(isPalindrome("hello"));
        System.out.println(isPalindrome("A man a plan"));

        System.out.println();

        System.out.println("countCharacter:");
        System.out.println(countCharacter("banana", 'a'));
        System.out.println(countCharacter("Hello", 'l'));
        System.out.println(countCharacter("", 'a'));
    }
}