// import java.io.*;
import java.util.*;

public class tokenring {
    
    public static void main(String args[]) {

        Scanner inp = new Scanner(System.in);
        System.out.println("Enter num of nodes : ");
        int n = inp.nextInt();

        System.out.println("Enter sender : ");
        int s = inp.nextInt();
        
        System.out.println("Enter receiver : ");
        int r = inp.nextInt();
        
        System.out.println("Enter data : ");
        String data = inp.nextLine();

        System.out.println("\nSending data : "+data);

        for(int i=s+1; i!=r; i = (i+1)%n){
            System.out.println("Forwarded to : "+ i);
        }
        
        System.out.println("Received data at : "+r);
        inp.close();
    }
}