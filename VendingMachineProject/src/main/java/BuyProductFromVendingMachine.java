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
        //print the vending machine menu



        //System.in is a standard input stream
        Scanner sc= new Scanner(System.in);
        //set flag to purchase product true;
        char flagToBuy ='Y';
        int itemId=0;
        float itemPrice=00.00f;
        float customerBalance =00.00f;
        float paidAmount=00.00f;
        float vendingMachineBalance =00.00f;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime transactionTime;



        while (flagToBuy == 'Y'){
            System.out.println("Select itemid from the menu to buy the product :");
            System.out.println("ItemId \t\tItem Name\t\tItem Price");
            for(int i=0;i<arrayOfItemsId.length;i++){
                System.out.println(arrayOfItemsId[i]+"\t\t\t"+arrayOfItems[i]+"\t\t\t"+arrayOfItemPrice[i] );
            }
            System.out.println("4 \t\t\tVending Machine Balance " );
            System.out.println("Enter the item id from menu to buy product: ");
            itemId = sc.nextInt();
            if(itemId>5){
                System.out.println("Invalid item id,please try again!!");
            }
            else if(itemId==4){
                System.out.println("Vending Machine balance is: "+vendingMachineBalance);
            }else{
            System.out.println("Pay the amount: ");
            paidAmount = sc.nextInt();
            transactionTime = LocalDateTime.now(); //save the transaction time
            itemPrice =  arrayOfItemPrice[itemId-1];
            if(itemPrice<=paidAmount){
                vendingMachineBalance = vendingMachineBalance +itemPrice;
                customerBalance = buyProduct(itemPrice,paidAmount,customerBalance);
                System.out.println("Returning balance amount to customer : "+customerBalance +".\n" +
                        "Dispensing the product.Thank you for purchasing the product");

            }else{
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

    private static float buyProduct(float itemPrice, float paidAmount, float customerBalance) {

        customerBalance = paidAmount - itemPrice;
        return customerBalance;
    }


}


