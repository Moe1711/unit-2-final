import java.util.Scanner;


//array of objects
public class Values {
  Scanner input = new Scanner(System.in);
  private double myTax = ' ';
  double finalPrice = 0;
  private int tryNum = 0;
  String itemName;
  private int itemPrice;
  int numItems;
  long itemNum;

  public void setVariables() {

    //taking user input and making object
    System.out.println("Enter the item name!");
    itemName = input.nextLine();

    System.out.println("Enter the price!");
    itemPrice = Integer.parseInt(input.nextLine());

    System.out.println("Enter the quantity!");
    numItems = Integer.parseInt(input.nextLine());

    //try and catch fro item code
    while (tryNum < 1) {
      System.out.println("Enter the item number(10 digits)!");

      try {
        itemNum = Long.parseLong(input.nextLine());
        String hold = String.valueOf(itemNum);
        System.out.println(hold.length());
        if (hold.length() != 10) {
          throw new Exception();
        } 
      } catch (Exception E) {
        System.out.println("Please make sure the code is 10 digits long!");
        tryNum--;
      }
      tryNum++;

    }

    //tax calculation
    tryNum--;
    myTax = itemPrice * 0.13;
    myTax = (Math.round(myTax * 100.0 / 5) * 5 / 100.0);
    finalPrice = itemPrice + myTax;

    
  }

}