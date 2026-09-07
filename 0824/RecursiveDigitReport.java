public class RecursiveDigitReport {

    static int digitSum(int number) {

        number = Math.abs(number);

        if (number < 10) {
            return number;
        }

        return number % 10 + digitSum(number / 10);
    }

    static int digitCount(int number) {

        number = Math.abs(number);

        if (number < 10) {
            return 1;
        }

        return 1 + digitCount(number / 10);
    }

    static int countDigit(int number, int target) {

        number = Math.abs(number);

        if (target < 0 || target > 9) {
            return 0;
        }

        if (number < 10) {
            if (number == target) {
                return 1;
            }

            return 0;
        }

        int current = number % 10;

        if (current == target) {
            return 1 + countDigit(number / 10, target);
        }

        return countDigit(number / 10, target);
    }

    public static void main(String[] args) {

        System.out.println("50205");
        System.out.println("digitSum = " + digitSum(50205));
        System.out.println("digitCount = " + digitCount(50205));
        System.out.println("countDigit(0) = "
                + countDigit(50205, 0));

        System.out.println();

        System.out.println("0");
        System.out.println("digitSum = " + digitSum(0));
        System.out.println("digitCount = " + digitCount(0));
        System.out.println("countDigit(0) = "
                + countDigit(0, 0));

        System.out.println();

        System.out.println("-731");
        System.out.println("digitSum = " + digitSum(-731));
        System.out.println("digitCount = " + digitCount(-731));
        System.out.println("countDigit(7) = "
                + countDigit(-731, 7));
    }
}