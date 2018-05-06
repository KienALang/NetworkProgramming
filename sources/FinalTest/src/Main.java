public class Main {

    public static void main(String[] args) {
        int number[] = new int[2];
        number[0] = 1;
        number[1] = 100;
        System.out.println(countLastZeroNumber(number));
    }

    public static int countLastZeroNumber(int number[]) {
        int count = 0;
        double t = 1;
        if (number.length == 2) {
            for (int i = number[0]; i <= number[1]; i++) {
                t *= i;
                while (t > 0 && t % 10 == 0) {
                    ++count;
                    t = t / 10;
                }
            }
        }else {
            if (number.length > 2) {
                for (int i = 0; i < number.length; i++) {
                    t *= number[i];
                    while (t > 0 && t % 10 == 0) {
                        ++count;
                        t = t / 10;
                    }
                }
            }
        }

        return  count;
    }
}
