package Data;


import Goods.AlcoholDrink;
import Goods.Product;
import Goods.SoftDrink;
import Utils.AlcoholDrinkCategories;
import Utils.SoftDrinkCategories;

import java.io.*;
import java.util.ArrayList;

public class CSVOperation {

    private static String csvFile= CSVOperation.class.getResource("/DataTest.csv").getFile();
    private static String csvSplitBy = ",";
    private static String line = " ";

    public static ArrayList<Product> read(){

        ArrayList <Product> arrayList =new ArrayList<Product>();

        long id;
        int alcohol;
        String name;
        double price;
        String textCategory;
        double volume;
        String description;
        long quantity;
        SoftDrink softDrink;
        AlcoholDrink alcoholDrink;
        int step=-1;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(csvFile), "Cp1251"))) {
            while ((line = br.readLine()) !=null) {
                step++;
                if(step==0){
                    continue;
                }


                String[] data = line.split(csvSplitBy);

                id=Long.parseLong(data[0]);
                alcohol=Integer.parseInt(data[1]);
                name=data[2];
                price=Double.parseDouble(data[3]);
                textCategory=data[4];
                volume=Double.parseDouble(data[5]);
                description=data[6];
                quantity=Long.parseLong(data[7]);

                if(alcohol==1){
                    AlcoholDrinkCategories category= AlcoholDrinkCategories.elementOf(textCategory);
                    alcoholDrink=new AlcoholDrink(id,name,price,category,volume,description,quantity);
                    arrayList.add(alcoholDrink);
                }
                if(alcohol==0){
                    SoftDrinkCategories category=SoftDrinkCategories.elementOf(textCategory);
                    softDrink=new SoftDrink(id,name,price,category,volume,description,quantity);
                    arrayList.add(softDrink);
                }



                /*System.out.println(data[0]+ "  " + data[1]+ "  " + data[2]+" "+data[3]+" "+data[4]+" "+data[5]+" "+
                " "+data[6]+" "+data[7]);*/

            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return arrayList;
    }

    public static void save(ArrayList<Product> drinkList){
        try(OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(csvFile, false), "Cp1251")){
            writer.write("id,alcohol,name,price,category,volume,description,quantity"+"\n");
            for(Product p:drinkList){
                writer.write(String.valueOf(p.getId())+",");
                if(p instanceof  SoftDrink){
                    writer.write("0"+",");
                } else if(p instanceof AlcoholDrink){
                    writer.write("1"+",");
                }
                writer.write(p.getName()+",");
                writer.write(p.getPrice()+",");
                writer.write(p.getCategory()+",");
                writer.write(String.valueOf(p.getVolume())+",");
                writer.write(p.getDescription()+",");
                writer.write(String.valueOf(p.getQuantity())+","+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
