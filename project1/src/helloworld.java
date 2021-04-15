import java.util.Scanner;

public class helloworld{
    public static void main(String[] args) {
        helloworlddrive h = new helloworlddrive();
        h.doSomething();
    }
}
class helloworlddrive {
    public static void doSomething() {
        int x = 0;
        while (x < 1) {
            int number = getNumber();
            boolean check_prime = isPrime(number);
            if (check_prime) {
                System.out.println(number + " is a prime number.\n");
            } else {
                System.out.println(number + " is not a prime number.\n");
            }
            char e = end();
            if (e != 'y') {
                ++x;
            }
            System.out.print('\n');
        }
    }

    public static int getNumber(){
        Scanner number = new Scanner(System.in);

        System.out.print("Enter a number to check if prime: ");
        int entered_number = number.nextInt();
        return entered_number;
    }

    public static char end() {
        Scanner char_end = new Scanner(System.in);
        System.out.println("\nEnter y to restart, anythin else to end.\n");
        char end = char_end.next().charAt(0);
        return end;
    }

    public static boolean isPrime(int x){
        int division = 1;
        for (int test = 2; test <= x; ++test){
            if (x % test == 0) ++division;
        }
        return (division == 2);
    }
}