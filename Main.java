import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

class Main {

  // meathod to output array of objects and date
  // code to read file at start
  public static void getResults(Values[] arr, int maxNum)  {
    String date = java.time.LocalDate.now().toString();
    for (int i = 0; i < maxNum; i++) {
      System.out.println("Item: " + arr[i].itemName + "\nQuantity: " + Integer.toString(arr[i].numItems) + "\nCode: "+ Long.toString(arr[i].itemNum) + "\nPrice: $" + Double.toString(arr[i].finalPrice) + "\n");
      date = java.time.LocalDate.now().toString();
    }

    System.out.println("The date this was last updated is: " + date);

  }

  // meathod to write to file
  public static void fileWrite(Values[] arr, int maxNum) throws IOException {
    FileWriter myWord = new FileWriter("userInventory.txt");
    for (int i = 0; i < maxNum; i++) {
      myWord.write(arr[i].itemName + "\n" + Double.toString(arr[i].finalPrice) + "\n" + Integer.toString(arr[i].numItems) + "\n" + Long.toString(arr[i].itemNum) + "\n");

    }
    myWord.close();
  }

  // main meathod
  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);
    
    // Creates file
    File userInventory = new File("userInventory.txt");
    userInventory.createNewFile();

    // Variables
    int moreItems = 0;
    byte userCont = 0;
    int itemCount = 0;
    byte tryLetter = 0;
    int i = 0;

    //object array declared and set
    Values[] storeAll = new Values[5000];

    //reads file and adds to array
    Scanner myRead = new Scanner(userInventory);
    String itemName;
    double finalPrice;
    int numItems; 
    long itemNum ;

    while (myRead.hasNextLine()) {
      itemName = myRead.nextLine();
      finalPrice = Double.parseDouble(myRead.nextLine());
      numItems = Integer.parseInt(myRead.nextLine());
      itemNum = Long.parseLong(myRead.nextLine());
      Values item = new Values();
      itemCount++;
      item.itemName = itemName;
      item.finalPrice = finalPrice;
      item.numItems = numItems;
      item.itemNum = itemNum;
      storeAll[i] = item;
      i++;
    }
    myRead.close();

    // Inital question
    System.out.print("Please enter 5 items to start!\n");

    // seprate code for first 5 inputs
    for ( i = i; i < 5; i++) {
      storeAll[i] = new Values();
      storeAll[i].setVariables();
      itemCount++;
    }

    // user menu
    String userChoice = " ";
    while (userCont < 1) {
      tryLetter = 0;
      while (tryLetter < 1) {
        System.out.println("What would you like to do? A) Add items , B) View inventory, C) Exit");

        try {
          userChoice = input.nextLine();
          if (!((userChoice.equalsIgnoreCase("a")
              || ((userChoice.equalsIgnoreCase("b")) || (userChoice.equalsIgnoreCase("c")))))) {
            throw new Exception();
          }

        } catch (Exception E) {
          System.out.println("Please select a valid input!");
          tryLetter--;
        }
        tryLetter++;

      }

      // adding to inital inventory
      if (userChoice.equalsIgnoreCase("a")) {
        System.out.print("How many items would you like to add?\n");
        moreItems = Integer.parseInt(input.nextLine());
        moreItems += itemCount;
        for ( i = itemCount; i < moreItems; i++) {
          storeAll[i] = new Values();
          storeAll[i].setVariables();
          itemCount++;
        }

      }

      // viewing inventory
      if (userChoice.equalsIgnoreCase("b")) {
        getResults(storeAll, itemCount);

      }

      // exit option and writing to file
      if (userChoice.equalsIgnoreCase("c")) {
        fileWrite(storeAll, itemCount);
        System.out.print("Session Terminated!");
        userCont++;
      }
    }

  }
}
