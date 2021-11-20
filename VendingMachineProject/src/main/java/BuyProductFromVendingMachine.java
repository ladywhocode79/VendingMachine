import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BuyProductFromVendingMachine {

    public static void main(String[] args) {
        //set product lists,product id and product price
        String [] arrayOfItems = {"Choco Bar","WaterBottle","Soda Bottle"};
        int [] arrayOfItemsId = {1,2,3};
        float [] arrayOfItemPrice ={10.00f,23.4f,30.00f};
        char flagToBuy ='Y';
        int itemId=0;
        float itemPrice=00.00f;
        float customerBalance =00.00f;
        float paidAmount=00.00f;
        float vendingMachineBalance =00.00f;
        //arrays to store ledger details
        int [] arrayOfPurchasedItemsId= new int[50];
        float [] arrayOfAmountPaidByUserForATransaction = new float[50];
        float [] arrayOfBalanceReturnedToUserForATransaction = new float[50];
        float [] arrayOfAmountInVendingMachine = new float[50];
        String [] arrayOfTransactionTime = new String[50];
        int transactionCount=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime transactionTime ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime;
        //System.in is a standard input stream
        Scanner sc= new Scanner(System.in);
        //set flag to purchase product true;
        while (flagToBuy == 'Y'){
            //print the vending machine menu
            printVendingMachineMenu(arrayOfItemsId,arrayOfItems,arrayOfItemPrice);
            System.out.println("Enter the item id from menu to buy product: ");
            itemId = sc.nextInt();
            if(itemId>5){
                //customer input number other than vending machine option
                System.out.println("Invalid item id,please try again!!");
            }else if(itemId ==5){
                System.out.println("Printing customer ledger");
                System.out.println("Transaction Time\t\tItemid\t\tUser Amount\t\tBalance Amount" +
                        "\t\tVending Machine Balance");
                for(int i=0;i<transactionCount;i++){
                    System.out.println(arrayOfTransactionTime[i]+"\t\t"+arrayOfPurchasedItemsId[i]+"\t\t"+
                            arrayOfAmountPaidByUserForATransaction[i]+"\t\t"+arrayOfBalanceReturnedToUserForATransaction[i]
                            +"\t\t"+arrayOfAmountInVendingMachine[i]);
                }
            }
            else if(itemId==4){
                //customer wants to check vending machine balance
                System.out.println("Vending Machine balance is: "+vendingMachineBalance);
            }else
            {//customer enter item id to buy product
                System.out.println("Pay the amount: ");
                paidAmount = sc.nextInt();

                itemPrice = arrayOfItemPrice[itemId - 1];
                //check if customer paid amount less than item price
                if (itemPrice <= paidAmount)
                {
                    transactionTime = LocalDateTime.now(); //save the transaction time
                    formattedDateTime = transactionTime.format(formatter);
                    vendingMachineBalance = vendingMachineBalance + itemPrice;
                    customerBalance = buyProduct(itemPrice, paidAmount, customerBalance);
                    arrayOfAmountInVendingMachine[transactionCount] = vendingMachineBalance;
                    arrayOfBalanceReturnedToUserForATransaction[transactionCount] = customerBalance;
                    arrayOfAmountPaidByUserForATransaction[transactionCount] = paidAmount;
                    arrayOfPurchasedItemsId[transactionCount] = itemId;
                    arrayOfTransactionTime[transactionCount] = formattedDateTime;
                    transactionCount++;

                    System.out.println("Returning balance amount to customer : " + customerBalance + ".\n" +
                            "Dispensing the product.Thank you for purchasing the product");
                } else{
                    //customer paid less amount
                    System.out.println("Insufficient amount,please try again ");
                }
            }
            System.out.println("Do you want to buy another item,press Y/y-Yes and N/n -No: ");
            flagToBuy = sc.next().charAt(0);
            //converts user input to upper case
            flagToBuy=Character.toUpperCase(flagToBuy);
        }

        System.out.println("Thank you!!");
    }

    static void printVendingMachineMenu(int[] arrayOfItemsId, String[] arrayOfItems, float[] arrayOfItemPrice){
        {
            System.out.println("Select itemid from the menu to buy the product :");
            System.out.println("ItemId \t\tItem Name\t\tItem Price");
            for(int i=0;i<arrayOfItemsId.length;i++){
                System.out.println(arrayOfItemsId[i]+"\t\t\t"+arrayOfItems[i]+"\t\t\t"+arrayOfItemPrice[i] );
            }
            System.out.println("4 \t\t\tVending Machine Balance " );
            System.out.println("5 \t\t\tUser Transaction History " );}
    }
    static float buyProduct(float itemPrice, float paidAmount, float customerBalance) {
        //method to calculate customer transaction for an item
        customerBalance = paidAmount - itemPrice;
        return customerBalance;
    }

}


