import java.util.Random;
import java.util.Arrays;

public class Laba1 {
    public static void main(String[] args) {
        int[] c = {22, 20, 18, 16, 14, 12, 10, 8, 6, 4, 2};
        Random random = new Random();
        double[] x = new double[20];
        for (int i = 0; i < x.length; i++) {
            x[i] = random.nextDouble() * 19 - 7;  // случайные числа в диапозоне от -7.0 до 12.0
        }
        double[][] dva = new double[11][20];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 20; j++) {
                if (c[i] == 8) {
                    dva[i][j] = Math.cbrt(((Math.asin(Math.sin(x[i])))));
                } else if (c[i] == 10 || c[i] == 12 || c[i] == 14 || c[i] == 16 || c[i] == 20) {
                    dva[i][j] = Math.log(Math.pow(5 / (Math.abs(x[i]) + 1) * (Math.pow(Math.tan(x[i]), 2) + 1), Math.tan(Math.tan(x[i]))));
                } else {
                    dva[i][j] = Math.log(Math.pow(Math.sin(Math.atan(0.4 * 1 / (Math.pow(Math.E, Math.abs(x[i]))))), 2));
                }
            }
        }
        for (double m1[] : dva) { // создадим одномерный массив  и заполним его элементами из двумерного массива dva
            for (double i : m1) {
                String str = String.format("%.4f", i); //форматируем каждое i до 4 знаков после запятой
                System.out.printf("%9s", str); //добавляем
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
