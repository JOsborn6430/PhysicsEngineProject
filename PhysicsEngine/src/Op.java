import java.util.Arrays;

// For the love of god we need to find something we can import for this shit
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

    public static void printMatrix(double[][] M) {
        for (int i = 0; i < M.length; i++) {
            System.out.println(Arrays.toString(M[i]));
        }
    }

    public static double[][] matrixInverse2d(double[][] M) {
        double[][] I = new double[2][2];
        I = cloneArray2d(M);
        double invDer = 1.0/(M[0][0]*M[1][1]-M[0][1]*M[1][0]);
        System.out.println("intDer = " + invDer);
        I[0][0] = M[1][1] * invDer;
        I[0][1] = -M[0][1] * invDer;
        I[1][0] = -M[1][0] * invDer;
        I[1][1] = M[0][0] * invDer;
        return I;
    }

    public static double[][] cloneArray2d(double[][] M) {
        double[][] I = new double[2][2];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                I[i][j] = M[i][j];
            }
        }
        return I;
    }

    public static double[][] matrixDotProduct(double[][] M1, double[][] M2) {
        double[][] A = new double[2][2];
        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M2[0].length; j++) {
                for (int k = 0; k < M2.length; k++) {
                    A[i][j] += M1[i][k] * M2[k][j];
                }
            }
        }
        return A;
    }

    public static double[] matrixVectorDotProduct(double[][] M, double[] v) {
        double[] b = new double[2];
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                b[i] += M[i][j] * v[j];
            }
        }
        return b;
    }

    public static double vectorDotProduct(double[] v1, double[] v2) {
        double b = 0;
        for (int i = 0; i < v1.length; i++) {
            b += v1[i] * v2[i];
        }
        return b;
    }


}
