import java.util.ArrayList;
import java.util.List;

public class LCS {


   /* private static int getLCS(String x, String y) {
        int xlen = x.length();
        int ylen = y.length();

        // 此处的棋盘长度要比字符串长度多加1，需要多存储一行0和一列0
        int[][] commonSublen = new int[xlen + 1][ylen + 1];
        // 1代表上 2 代表向左上 3代表向左 4代表上或者左
        int[][] direction = new int[xlen + 1][ylen + 1];
        // 将整个数组commonSublen填充值
        for (int i = 1; i <= xlen; i++) {
            char xi = x.charAt(i - 1);
            for (int j = 1; j <= ylen; j++) {
                char yj = y.charAt(j - 1);
                if (xi == yj) {
                    commonSublen[i][j] = commonSublen[i - 1][j - 1] + 1;
                    // 2 代表向左上
                    direction[i][j] = 2;
                } else if (commonSublen[i - 1][j] > commonSublen[i][j - 1]) {
                    commonSublen[i][j] = commonSublen[i - 1][j];
                    // 1代表上
                    direction[i][j] = 1;
                } else if (commonSublen[i - 1][j] < commonSublen[i][j - 1]) {
                    commonSublen[i][j] = commonSublen[i][j - 1];
                    // 3代表左
                    direction[i][j] = 3;
                } else {
                    // 如果commonSublen[i - 1][j] == commonSublen[i][j - 1]
                    // 向上或者向左不影响结果
                    // 4代表上 或者 左
                    commonSublen[i][j] = commonSublen[i - 1][j];
                    // 1代表上
                    direction[i][j] = 4;
                }
            }
        }
        int maxSublen = commonSublen[xlen][ylen];
        String lcs = "";
        printSublen(commonSublen);
        lcs= printAllLcs(direction, x, lcs, maxSublen, xlen, ylen);
        System.out.println(lcs);
        return maxSublen;
    }


    private static String printAllLcs(int[][] direction, String x, String lcs, int maxSublen, int i, int j) {
        if (i == 0 || j == 0) {
            StringBuilder sb = new StringBuilder(lcs);
            lcs = sb.reverse().toString();
            if (lcs.length() == maxSublen) {
               System.out.println(lcs);
                return lcs;
            }
            return lcs;
        }
        int dir = direction[i][j];
        switch (dir) {
            case 1:// 表示向上
                printAllLcs(direction, x, lcs, maxSublen, i - 1, j);
                break;
            case 2:// 代表向左上
                lcs += x.charAt(i - 1);
                printAllLcs(direction, x, lcs, maxSublen, i - 1, j - 1);
                break;
            case 3:// 3代表向左
                printAllLcs(direction, x, lcs, maxSublen, i, j - 1);
                ;
                break;
            case 4:// 表示向上 或 代表向左
                // 表示向上
                printAllLcs(direction, x, lcs, maxSublen, i - 1, j);
                // 代表向左
                printAllLcs(direction, x, lcs, maxSublen, i, j - 1);
                break;
        }
        return lcs;
    }


    private static void printSublen(int[][] commonSublen) {
        for (int i = 0; i < commonSublen.length; i++) {
            for (int j = 0; j < commonSublen[0].length; j++) {
                System.out.print(commonSublen[i][j] + "\t");
            }
            System.out.println("\n\n");
        }
    }

    public static void main(String[] args) {
        String S1 = "ACCGGTCGAGATGCAG";
        String S2 = " GTCGTTCGGAATGCAT";
        getLCS(S1, S2);
    }*/


    // 求解两个字符号的最长公共子串

    public static String maxSubseq(String strOne, String strTwo) {

        // 参数检查

        if (strOne == null || strTwo == null) {

            return null;

        }

        if (strOne.equals("") || strTwo.equals("")) {

            return null;

        }

        // 矩阵的横向长度

        int len1 = strOne.length();

        // 矩阵的纵向长度

        int len2 = strTwo.length();

        // 二维数组用来保存中间结果

        int[][] datas = new int[len1 + 1][len2 + 1];

        // 使用另外一个二维数组作为标记数组，用来保存从前一步到这一步的路径

        String[][] index = new String[len1 + 1][len2 + 1];

        // 填充二维数组

        for (int i = 1; i <= len1; i++) {

            for (int j = 1; j <= len2; j++) {

                int leftTop = datas[i - 1][j - 1];

                int top = datas[i - 1][j];

                int left = datas[i][j - 1];

                if (strOne.charAt(i - 1) == strTwo.charAt(j - 1)) {

                    leftTop++;

                }

                int maxTemp = Math.max(leftTop, top);

                datas[i][j] = Math.max(maxTemp, left);

                // 填写标记数组

                if (datas[i][j] == leftTop) {

                    index[i][j] = "leftTop";

                } else if (datas[i][j] == left) {

                    index[i][j] = "left";

                } else {

                    index[i][j] = "top";

                }

            }

        }

        StringBuilder sBuilder = new StringBuilder();

        // 从二维矩阵的最后一个元素向前查找结果，当（左上， 左， 上）数字相同时，查找顺序：左上-> 左 -> 上

        int maxLen = datas[len1][len2];

        System.out.println("LCS长度为:" + maxLen);

        int i = len1;

        int j = len2;

        String indexStr = "";

        char currentCh = ' ';

        int currentLen = 0;

        while (i > 0 && j > 0) {

            currentLen = datas[i][j];

            currentCh = strOne.charAt(i - 1);

            indexStr = index[i][j];

            if (indexStr.equals("leftTop")) {

                i--;

                j--;

            } else if (indexStr.equals("left")) {

                j--;

            } else {

                i--;

            }

            if (currentLen > datas[i][j]) {

                sBuilder.insert(0, currentCh);

            }

        }

        return sBuilder.toString();

    }


    public static void main(String[] args) {

        String str1 = "ACCGGTCGAGATGCAG";

        String str2 = "GTCGTTCGGAATGCAT";

        String result = LCS.maxSubseq(str1, str2);

        System.out.println(result);

    }

}



