import java.util.Scanner;

public class SetProductMenu {

    public static void main(String[] args) {

        ProductMaster [] arrayOfProducts;
        int itemId=0;
        double customerBalance =00.00;
        double paidAmount=00.00;
        //set flag to purchase product true;
        char flagToBuy ='Y';

        //System.in is a standard input stream
        Scanner sc= new Scanner(System.in);

        //set products for the vending machine
        arrayOfProducts=addProoductsToMenu();

        //Loop until user doesnt want to purchase any product
        while (flagToBuy == 'Y'){
            displayProductsInMenu(arrayOfProducts);
            System.out.println("Enter the item id from menu to buy product: ");
            itemId = sc.nextInt();
            if(itemId>4){
                System.out.println("Invalid item id,please try again!!");
               }else if(itemId == 4){
                viewBalanceInVendingMachine();
            }else{
                 System.out.println("Pay the amount: ");
                 paidAmount = sc.nextInt();
                 customerBalance = buyProduct(arrayOfProducts,itemId,paidAmount,customerBalance);
                 System.out.println("Returning balance amount to customer : "+customerBalance +".\n" +
                         "Dispensing the product.Thank you for purchasing the product");

             }
            System.out.println("Do you want to buy another item,press Y/y-Yes and N/n -No: ");
            flagToBuy = sc.next().charAt(0);

            //converts user input to upper case
                flagToBuy=Character.toUpperCase(flagToBuy);
            }
    }
    //display vending machine balance
    static void viewBalanceInVendingMachine(){
        System.out.println("Balance amount in vending machine is :" +ProductMaster.vendingMachineBalanceAmount);
    }
    //select product to buy
    static double buyProduct(ProductMaster [] arrayOfProduct,int itemId,double amountPaid,double balance){
        //ProductMaster [] arrOfProducts = new ProductMaster[3];
        for(int i=0;i<arrayOfProduct.length;i++){
            if(itemId == arrayOfProduct[i].productId && arrayOfProduct[i].productPrice<=amountPaid)
            {
                balance =amountPaid -arrayOfProduct[i].productPrice;
                ProductMaster.vendingMachineBalanceAmount =ProductMaster.vendingMachineBalanceAmount+
                        arrayOfProduct[i].productPrice;
            }
            }
        return balance;
        }

    //add products in menu
    static ProductMaster [] addProoductsToMenu(){
        ProductMaster [] arrayOfProductLists = new ProductMaster[3];
        ProductMaster productOne = new ProductMaster(1,"Chocolate  ",10.00,5);
        arrayOfProductLists[0]=productOne;
        ProductMaster productTwo = new ProductMaster(2,"Orange Juice",25.00,8);
        arrayOfProductLists[1]=productTwo;
        ProductMaster productThree = new ProductMaster(3,"Water Bottle",15.00,10);
        arrayOfProductLists[2]=productThree;
        return arrayOfProductLists;
    }

    static void displayProductsInMenu(ProductMaster [] prodList){
        //Display products in Menu
        System.out.println("Hello! Select items from Menu");
        System.out.println("Item id\t\t\t\t\tItem Name\t\t\t\t\tItem Price");
        for(int i=0;i<prodList.length;i++){
            System.out.println(prodList[i].productId+"\t\t\t\t\t"+ prodList[i].productName +
                    "\t\t\t\t\t" + prodList[i].productPrice);
        }
        System.out.println("4              Balance in Machine               ");
    }

}
