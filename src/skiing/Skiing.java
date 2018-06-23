package skiing;

import java.util.ArrayList;

public class Skiing {

    private int[][] inputArray;
    private ArrayList<Integer> [][] cached;

    public Skiing(int[][] input) {
        this.inputArray = input;
        this.cached = new ArrayList[input.length][input[0].length];
    }

    public ArrayList<Integer> longestPath() {
        ArrayList<Integer> curMax= null;
        for(int ii=0; ii<inputArray.length; ii++) {
            for (int jj=0; jj<inputArray[0].length; jj++){
                if(curMax == null){
                    curMax = getLongestPath(ii,jj);
                } else {
                    curMax = maximum(curMax, getLongestPath(ii,jj));
                }
            }
        }
        return curMax;
    }

    private ArrayList<Integer> getLongestPath(int row, int column) {

        if(cached[row][column] != null) {
            return cached[row][column];
        }

        ArrayList<Integer> lmax = new ArrayList<>();
        ArrayList<Integer> rmax = new ArrayList<>();
        ArrayList<Integer> tmax = new ArrayList<>();
        ArrayList<Integer> bmax = new ArrayList<>();

        lmax.add(inputArray[row][column]);
        rmax.add(inputArray[row][column]);
        tmax.add(inputArray[row][column]);
        bmax.add(inputArray[row][column]);

        if (column-1 >= 0 && inputArray[row][column-1] < inputArray[row][column]) {
            lmax.addAll(getLongestPath(row, column-1));
        }

        if (column+1 < inputArray[0].length && inputArray[row][column+1] < inputArray[row][column]) {
            rmax.addAll(getLongestPath(row, column+1));
        }

        if (row-1 >= 0 && inputArray[row-1][column] < inputArray[row][column]) {
            tmax.addAll(getLongestPath(row-1, column));
        }

        if (row+1 < inputArray.length && inputArray[row+1][column] < inputArray[row][column]) {
            bmax.addAll(getLongestPath(row+1, column));
        }

        ArrayList<Integer> max = maximum(lmax, maximum(rmax, maximum(tmax, bmax)));
        cached[row][column] = max;
        return max;
    }

    private ArrayList<Integer> maximum(ArrayList<Integer> a, ArrayList<Integer> b) {
        if(a.size() != b.size()) {
            return a.size() > b.size() ? a : b;
        } else if (a.size() != 0){
            int steepA = a.get(0) - a.get(a.size()-1);
            int steepB = b.get(0) - b.get(b.size()-1);
            return  (steepA > steepB) ? a : b;
        } else {
            return a;
        }
    }
}
