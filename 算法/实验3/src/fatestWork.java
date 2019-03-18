public class fatestWork {


    static int N = 6;

    static int last_f = 0;

    static int last_l = 0;

    public static void fastest_way(int[][] a, int[][] t, int[] e, int[] x, int[][] f, int[][] l, int n) {

        int i, j;

        f[0][0] = e[0] + a[0][0];

        f[1][0] = e[1] + a[1][0];

        l[0][0] = 1;

        l[1][0] = 2;

        for (j = 1; j < n; j++) {

            if (f[0][j - 1] < f[1][j - 1] + t[1][j - 1]) {

                f[0][j] = f[0][j - 1] + a[0][j];


                l[0][j] = 1;

            } else {

                f[0][j] = f[1][j - 1] + t[1][j - 1] + a[0][j];

                l[0][j] = 2;

            }

            if (f[1][j - 1] < f[0][j - 1] + t[0][j - 1]) {

                f[1][j] = f[1][j - 1] + a[1][j];

                l[1][j] = 2;

            } else {

                f[1][j] = f[0][j - 1] + t[0][j - 1] + a[1][j];

                l[1][j] = 1;

                l[0][j] = 1;

            }



        }

        if (f[0][n - 1] + x[0] < f[1][n - 1] + x[1]) {

            last_f = f[0][n - 1] + x[0];

            last_l = 1;

        } else {

            last_f = f[1][n - 1] + x[1];

            last_l = 2;

        }

     /*   for (int i = 1; i < n; ++i)
        {
            if (result[0][i - 1] + arrayA[0][i] <= result[1][i - 1] + arrayT[1][i - 1] + arrayA[0][i])
            {
                result[0][i] = result[0][i - 1] + arrayA[0][i];
                listNum[0][i-1] = 1;
            }
            else
            {
                result[0][i] = result[1][i - 1] + arrayT[1][i - 1] + arrayA[0][i];
                listNum[0][i-1] = 2;
            }

            if (result[1][i - 1] + arrayA[1][i] <= result[0][i - 1] + arrayT[0][i - 1] + arrayA[1][i])
            {
                result[1][i] = result[1][i - 1] + arrayA[1][i];
                listNum[1][i-1] = 2;
            }
            else
            {
                result[1][i] = result[0][i - 1] + arrayT[0][i - 1] + arrayA[1][i];
                listNum[1][i-1] = 1;
            }
        }
        if (result[0][n-1] + X[0] <= result[1][n-1] + X[1])
        {
            resultFinal = result[0][n-1] + X[0];
            listFinal = 1;
        }
        else
        {
            resultFinal = result[1][n-1] + X[1];
            listFinal = 2;
        }
    }
*/
    }

    public static void print_station_recursive(int[][] l, int last_l, int n) {


        int i = last_l;

        if (n == 1)

            System.out.println("生产线:" + i + "流程:" + n);

        else {

            print_station_recursive(l, l[i - 1][n - 1], n - 1);

            System.out.println("生产线:" + i + "流程:" + n);

        }


    }


    public static void main(String[] args) {


        int[][] a = new int[][]{{7, 9, 3, 4, 80}, {8, 5, 6, 4, 5}};

        int[][] t = new int[][]{{2, 3, 1, 3}, {2, 1, 2, 2}};


        int[][] f = new int[2][5];

        int[][] l = new int[2][5];


        int[] e = new int[]{2, 4};

        int[] x = new int[]{3, 6};

        int i, j;

        fastest_way(a, t, e, x, f, l, 5);

        System.out.println("所花最短时间是:" + last_f);


        print_station_recursive(l, last_l, 5);


    }


}


