package Utils;

import java.util.HashMap;
import java.util.Map;

public enum AlcoholDrinkCategories implements Categories {
    WINE("вино"), SPIRIT("міцний алкоголь"), BEER("пиво"), LIQUOR("лікер");

    private static final class Helper {
        static Map<String,AlcoholDrinkCategories> STRING_TO_ENUM = new HashMap<String,AlcoholDrinkCategories>();
    }

    private String value;
    AlcoholDrinkCategories(String s) {
        this.value=s;
        Helper.STRING_TO_ENUM.put(s,this);
    }


    public static AlcoholDrinkCategories elementOf(String s) throws IllegalCategoryName {
        AlcoholDrinkCategories alcoholDrinkCategories=Helper.STRING_TO_ENUM.get(s);
        if(alcoholDrinkCategories!=null) {
            return alcoholDrinkCategories;
        } else {
            throw new IllegalCategoryName("Invalid category name");
        }
    }

    public String getValue() {
        return value;
    }
}
