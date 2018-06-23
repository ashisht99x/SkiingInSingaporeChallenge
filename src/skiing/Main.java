package skiing;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File(ClassLoader.getSystemResource("map.txt").getFile());
        Scanner scanner = new Scanner(file);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        scanner.nextLine();

        int[][] inputFromFile= new int[rows][columns];

        for(int ii=0; ii<rows; ii++) {
            for (int jj=0; jj<columns; jj++) {
                inputFromFile[ii][jj] = scanner.nextInt();
            }
            scanner.nextLine();
        }

        Skiing dynamic = new Skiing(inputFromFile);
        long t1 = System.currentTimeMillis();
        ArrayList<Integer> path = dynamic.longestPath();
        long t2 = System.currentTimeMillis();

        System.out.println(" ");
        System.out.print("Path: " );
        for (Integer item: path) {
            System.out.print(item + " ");
        }
        System.out.println(" ");
        System.out.println("time taken: " + (t2-t1));
        if(path.size() > 0) {
            System.out.println("mail to: "+path.size()+(path.get(0)-path.get(path.size()-1))+"@redmart.com");
        }
    }
}

