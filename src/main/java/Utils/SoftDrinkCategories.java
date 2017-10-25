package Utils;

import java.util.HashMap;
import java.util.Map;

public enum SoftDrinkCategories implements Categories {
    WATER("мінеральні води"), JUICE("соки"), OTHER("інше");

    private static final class Helper {
        static Map<String,SoftDrinkCategories> STRING_TO_ENUM = new HashMap<String,SoftDrinkCategories>();
    }

    private String value;
    SoftDrinkCategories(String s) {
        this.value=s;
        Helper.STRING_TO_ENUM.put(s,this);
    }

    public static SoftDrinkCategories elementOf(String s) throws IllegalCategoryName {
        SoftDrinkCategories softDrinkCategories=Helper.STRING_TO_ENUM.get(s);
        if(softDrinkCategories!=null) {
            return softDrinkCategories;
        }else{
            throw new IllegalCategoryName("Invalid category name");
        }
    }

    public String getValue() {
        return value;
    }
}
