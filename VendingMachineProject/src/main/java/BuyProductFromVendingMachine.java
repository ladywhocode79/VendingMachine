import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BuyProductFromVendingMachine {

    public static final int MAX = 50;

    public static void main(String[] args) {
        //set product lists,product id and product price
        String[] arrayOfItems = {"Choco Bar", "WaterBottle", "Soda Bottle", "Eraser", "Pen"};
        int[] arrayOfItemsId = {1, 2, 3, 4, 5};
        float[] arrayOfItemPrice = {10.00f, 23.4f, 30.00f, 15.00f, 23.90f};
        int countOfMenuOptions = arrayOfItems.length + 2;
        char flagToBuy = 'Y';
        int itemId;
        float itemPrice;
        float customerBalance;
        float paidAmount;
        float vendingMachineBalance = 05.00f;
        //arrays to store ledger details
        int[] arrayOfPurchasedItemsId = new int[MAX];
        float[] arrayOfAmountPaidByUserForATransaction = new float[MAX];
        float[] arrayOfBalanceReturnedToUserForATransaction = new float[MAX];
        float[] arrayOfAmountInVendingMachine = new float[MAX];
        String[] arrayOfTransactionTime = new String[MAX];
        int transactionCount = 0;


        //System.in is a standard input stream
        Scanner sc = new Scanner(System.in);
        //set flag to purchase product true;
        while (flagToBuy == 'Y') {
            //print the vending machine menu
            printVendingMachineMenu(arrayOfItemsId, arrayOfItems, arrayOfItemPrice);
            System.out.println("Enter the item id from menu to buy product: ");
            itemId = sc.nextInt();
            if (isInvalidOption(countOfMenuOptions, itemId)) {
                //customer input number other than vending machine option
                printInvalidMessage();
                continue;
            }
            if (isLedgerBalanceOption(itemId, countOfMenuOptions)) {
                printCustomerLedger(arrayOfPurchasedItemsId, arrayOfAmountPaidByUserForATransaction, arrayOfBalanceReturnedToUserForATransaction, arrayOfAmountInVendingMachine, arrayOfTransactionTime, transactionCount);
                continue;
            }
            if (isVendingMachineBalanceOption(itemId, countOfMenuOptions)) {
                //customer wants to check vending machine balance
                printVendingMachineBalance(vendingMachineBalance);
                continue;
            }

            //customer enter item id to buy product
            System.out.println("Pay the amount: ");
            paidAmount = sc.nextInt();

            itemPrice = arrayOfItemPrice[itemId - 1];
            //check if customer paid amount less than item price
            if (noBalanceChangeAvailable(itemPrice, paidAmount, vendingMachineBalance)) {
                System.out.println("Please provide exact change");
                continue;
            }
            if (itemPrice <= paidAmount) {

                vendingMachineBalance = vendingMachineBalance + itemPrice;
                customerBalance = buyProduct(itemPrice, paidAmount);
                updateTransaction(itemId, customerBalance, paidAmount, vendingMachineBalance, arrayOfPurchasedItemsId, arrayOfAmountPaidByUserForATransaction, arrayOfBalanceReturnedToUserForATransaction, arrayOfAmountInVendingMachine, transactionCount);
                updateTransactionTime(arrayOfTransactionTime, transactionCount);

                transactionCount++;

                printBalanceMessage(customerBalance);
            } else {
                //customer paid less amount
                System.out.println("Insufficient amount,please try again ");
            }

            System.out.println("Do you want to buy another item,press Y/y-Yes and N/n -No: ");
            flagToBuy = sc.next().charAt(0);
            //converts user input to upper case
            flagToBuy = Character.toUpperCase(flagToBuy);
        }

        System.out.println("Thank you!!");
    }

    private static boolean noBalanceChangeAvailable(float itemPrice, float paidAmount, float vendingMachineBalance) {
        return paidAmount - itemPrice > vendingMachineBalance;
    }

    private static boolean isVendingMachineBalanceOption(int itemId, int countOfMenuOptions) {
        return itemId == countOfMenuOptions - 1;
    }

    private static boolean isLedgerBalanceOption(int itemId, int countOfMenuOptions) {
        return itemId == countOfMenuOptions;
    }

    private static boolean isInvalidOption(int countOfMenuOptions, int itemId) {
        return itemId > countOfMenuOptions;
    }

    private static void printBalanceMessage(float customerBalance) {
        System.out.println("Returning balance amount to customer : " + customerBalance + ".\n" +
                "Dispensing the product.Thank you for purchasing the product");
    }

    private static void updateTransactionTime(String[] arrayOfTransactionTime, int transactionCount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        arrayOfTransactionTime[transactionCount] = getFormattedDate(formatter);
    }

    private static void updateTransaction(int itemId, float customerBalance, float paidAmount, float vendingMachineBalance, int[] arrayOfPurchasedItemsId, float[] arrayOfAmountPaidByUserForATransaction, float[] arrayOfBalanceReturnedToUserForATransaction, float[] arrayOfAmountInVendingMachine, int transactionCount) {
        arrayOfAmountInVendingMachine[transactionCount] = vendingMachineBalance;
        arrayOfBalanceReturnedToUserForATransaction[transactionCount] = customerBalance;
        arrayOfAmountPaidByUserForATransaction[transactionCount] = paidAmount;
        arrayOfPurchasedItemsId[transactionCount] = itemId;
    }

    private static String getFormattedDate(DateTimeFormatter formatter) {
        LocalDateTime transactionTime;
        String formattedDateTime;
        transactionTime = LocalDateTime.now(); //save the transaction time
        formattedDateTime = transactionTime.format(formatter);
        return formattedDateTime;
    }

    private static void printVendingMachineBalance(float vendingMachineBalance) {
        System.out.println("Vending Machine balance is: " + vendingMachineBalance);
    }

    private static void printCustomerLedger(int[] arrayOfPurchasedItemsId,
                                            float[] arrayOfAmountPaidByUserForATransaction, float[] arrayOfBalanceReturnedToUserForATransaction, float[] arrayOfAmountInVendingMachine, String[] arrayOfTransactionTime, int transactionCount) {
        if (transactionCount == 0) {
            System.out.println("No ledger records found!!");
        } else {
            System.out.println("Printing customer ledger");
            System.out.println("Transaction Time\t\tItemId\t\tUser Amount\t\tBalance Amount" +
                    "\t\tVending Machine Balance");
            System.out.println("=================================================================");
            for (int i = 0; i < transactionCount; i++) {
                System.out.println(arrayOfTransactionTime[i] + "\t\t" + arrayOfPurchasedItemsId[i] + "\t\t" +
                        arrayOfAmountPaidByUserForATransaction[i] + "\t\t" + arrayOfBalanceReturnedToUserForATransaction[i]
                        + "\t\t" + arrayOfAmountInVendingMachine[i]);
            }
        }
    }

    private static void printInvalidMessage() {
        System.out.println("Invalid item id,please try again!!");
    }

    static void printVendingMachineMenu(int[] arrayOfItemsId, String[] arrayOfItems, float[] arrayOfItemPrice) {
        {
            System.out.println("Select itemId from the menu to buy the product :");
            System.out.println("ItemId \t\tItem Name\t\tItem Price");
            System.out.println("=================================================================");
            for (int i = 0; i < arrayOfItemsId.length; i++) {
                System.out.println(arrayOfItemsId[i] + "\t\t\t" + arrayOfItems[i] + "\t\t\t" + arrayOfItemPrice[i]);
            }
            System.out.println(arrayOfItemsId.length + 1 + " \t\t\tVending Machine Balance ");
            System.out.println(arrayOfItemsId.length + 2 + " \t\t\tUser Transaction History ");
        }
    }

    static float buyProduct(float itemPrice, float paidAmount) {
        //method to calculate customer transaction for an item
        return paidAmount - itemPrice;
    }

}


