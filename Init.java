import java.util.Scanner;

public class Init {
  public static void main (String[] args) {
    Scanner in = new Scanner("Володин Илья Сергеевич 19.11.2000");
    Person first = new Person(in.nextLine());
    System.out.println(first.format());
    in.close();
  }
}
