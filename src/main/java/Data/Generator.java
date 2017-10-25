package Data;

import Data.CSVOperation;
import Goods.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {

    private static int days=30;
    private static int maxClient=10;
    private static int minClient=0;
    private static int minBuy=0;
    private static int maxBuy=10;

    private final static double STANDART_MARK_UP=0.1;
    private final static double WEEKEND_MARK_UP=0.15;
    private final static double FROM18TO20_MARK_UP=0.08;
    private final static double MORE2_MARK_UP=0.07;

    private static String txtFile= CSVOperation.class.getResource("/report.txt").getFile();

    private static int randomClientCount(int min,int max){
        return (int) Math.round(Math.random()*(max-min)+min);
    }

    private static int randomBuyCount(int min, int max){
        return (int) Math.round(Math.random()*(max-min)+min);
    }

    private static double getMarkUp(int day, int hour, int buyNumber){
        if(buyNumber>=2){
            System.out.println("Націнка на третій товар і більше: "+MORE2_MARK_UP*100+"%");
            return MORE2_MARK_UP;
        } else if(hour>=18&&hour<20){
            System.out.println("Вечірня націнка: "+FROM18TO20_MARK_UP*100+"%");
            return FROM18TO20_MARK_UP;
        } else if((day%7==6)||(day%7==0)){
            System.out.println("Націнка вихідного дня: "+WEEKEND_MARK_UP*100+"%");
            return WEEKEND_MARK_UP;
        } else{
            System.out.println("Стандартна націнка: "+STANDART_MARK_UP*100+"%");
            return STANDART_MARK_UP;
        }
    }

    private static int ramdomProductNumber(int count){
        return (int)Math.round(Math.random()*(count-1));
    }

    public static void start(int dayCount, int startHour, int finishHour, int minClient, int maxClient,
                             int minBuy, int maxBuy, ArrayList<Product>drinkList){
        int clientCount;
        int buyCount;
        int productNumber;
        double markUp;
        double price;
        double oldPrice;
        String name;
        double volume;
        Product product;
        for(int day=1;day<=dayCount;day++){
            System.out.println("День: "+day);
            for(int hour=startHour;hour<finishHour;hour++){
                System.out.println("Година: "+hour);
                clientCount=randomClientCount(minClient,maxClient);
                for(int clientNumber=0;clientNumber<clientCount;clientNumber++){
                    System.out.println("Клієнт №"+(clientNumber+1)+ "(із "+clientCount+")");
                    buyCount=randomBuyCount(minBuy,maxBuy);
                    for(int buyNumber=0;buyNumber<buyCount;buyNumber++){
                        productNumber=ramdomProductNumber(drinkList.size());
                        product=drinkList.get(productNumber);
                        if(product.decreaseQuantity(1)){
                            name=product.getName();
                            volume=product.getVolume();
                            System.out.println("Продано одниницю товару "+name+" ємністю " + volume +" Категорія:"+product.getCategory());
                            oldPrice=product.getPrice();
                            markUp=Math.rint(oldPrice*getMarkUp(day,hour,buyNumber)*100)/100;
                            price=oldPrice+markUp;
                            System.out.println("Ціна "+price+" (закупка: "+oldPrice+")");
                            product.increaseOfftake(1);
                            product.increaseProfit(markUp);
                        }else{
                            System.out.print("Недостатньо товару: ");
                            System.out.println(product.getName()+" "+product.getVolume());
                        }

                    }
                }
            }
            for(Product p:drinkList){
                if(p.getQuantity()<10){
                    p.increaseQuantity(150);
                }
            }


        }

    }

    public static void createReport(ArrayList <Product> drinkList){
        try(FileWriter writer = new FileWriter(txtFile, true))
        {
            double totalProfit=0;
            double totalSpent=0;
            for(Product p:drinkList) {
                writer.write("Товар: "+p.getName()+" об'єму "+p.getVolume()+"\n");
                writer.write("Всього продано: "+p.getOfftake()+", прибуток: "+p.getProfit()+"\n");
                writer.write("Дозакуплено: "+p.getPurchasedProduct()+" на суму "+p.getSpentMoney()+"\n");
                totalProfit+=p.getProfit();
                totalSpent+=p.getSpentMoney();
            }
            writer.write("ЗАГАЛЬНИЙ ПРИБУТОК "+totalProfit+"\n");
            writer.write("ВСЬОГО ПОТРАЧЕНО НА ДОЗАКУПКУ "+totalSpent+"\n");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }

}

