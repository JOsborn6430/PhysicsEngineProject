public class Op {
    public static double[] vectorAdditionD(double[] v1, double[] v2) {
        double[] result = new double[v1.length];
        if (v1.length != v2.length) {
            System.out.println("Error: Vector dimensions do not match");
            return new double[]{0};
        } else for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    public static double[] scalarMultiplyD(double[] v, double a) {
        double[] result = new double[v.length];
        for (int i = 0; i < v.length; i++) result[i] = v[i] * a;
        return result;
    }

    public static void printArrayD(double[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i == array.length-1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }
}
