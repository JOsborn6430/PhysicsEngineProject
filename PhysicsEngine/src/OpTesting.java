public class OpTesting {
    public static void main(String[] args) {
        double[][] A = {{1,2},
                        {3,3}};
        double[] v = {-1,1};
        double[] r = {-2,-1};
        System.out.println(Op.vectorDotProduct(v,r));
    }
}
