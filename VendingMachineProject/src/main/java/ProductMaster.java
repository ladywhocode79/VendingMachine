public class ProductMaster {
    int productId;
    String productName;
    double productPrice;
    int productQuantity;
    static double vendingMachineBalanceAmount =00.00;

    //Default construtor
    ProductMaster(){

    }
    //Paramaterised construtor with all variables values
    ProductMaster(int id,String name,double price,int quantity){
        this.productId = id;
        this.productName = name;
        this.productPrice =price;
        this.productQuantity = quantity;
    }


}
