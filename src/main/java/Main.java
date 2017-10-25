import Goods.Product;
import Data.CSVOperation;
import Data.Generator;


import java.util.ArrayList;

public class Main {
    public static void main(String [] args){

        //reading data
        ArrayList <Product> drinkList = CSVOperation.read();


        Generator.start(30,8,21,1,10,0,10,drinkList);
        Generator.createReport(drinkList);

        //saving data
        CSVOperation.save(drinkList);



    }
}
