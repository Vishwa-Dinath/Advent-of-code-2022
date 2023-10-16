import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DayThree {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int DIFFERENCE_FOR_UPPERCASE = 38;
    private static final int DIFFERENCE_FOR_LOWERCASE = 96;

    public static void main(String[] args) {
//        System.out.println(pipeLine_1("/problem_3/sample_input.txt"));
//        System.out.println(pipeLine_1("/problem_3/input.txt"));

        System.out.println(pipeLine_2("/problem_3/sample_input.txt"));
        System.out.println(pipeLine_2("/problem_3/input.txt"));

    }

    public static ArrayList<String> getItemList(String filePath) {
        InputStream is = DayThree.class.getResourceAsStream(filePath);
        ArrayList<String> itemList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line= br.readLine())!= null){
                itemList.add(line);
            }
            return itemList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // for part2
    public static ArrayList<ArrayList<String>> getItemListByGroups(ArrayList<String> itemList) {
        ArrayList<ArrayList<String>> itemListByGroup = new ArrayList<>();
        int count = 0;
        ArrayList<String> itemsOfAGroup = new ArrayList<>();

        for (String items : itemList) {
            itemsOfAGroup.add(items);
            if (itemsOfAGroup.size() == 3) {
                itemListByGroup.add(itemsOfAGroup);
                itemsOfAGroup = new ArrayList<>();
            }
        }
        return itemListByGroup;
    }


    public static String findTheSharedItemOfEachRucksack(String items) {
        int length = items.length();
        String itemsInFirstCompartment = items.substring(0, length / 2);
        String[] itemsInSecondCompartment = items.substring(length / 2, length).split("");

        for (int i = 0; i < itemsInSecondCompartment.length; i++) {
            if (itemsInFirstCompartment.contains(itemsInSecondCompartment[i])){
                return itemsInSecondCompartment[i];
            }
        }
        return null;
    }

    public static ArrayList<String> getSharedItems(ArrayList<String> itemList) {
        ArrayList<String> sharedItemList = new ArrayList<>();
        for (String items : itemList) {
            String sharedItem = findTheSharedItemOfEachRucksack(items);
            if (sharedItem != null){
                sharedItemList.add(sharedItem);
            }
        }
        return sharedItemList;
    }

    public static int getValueOfCharacter(String sharedItem) {
        int asciiValue = 0;
        if (UPPERCASE.contains(sharedItem)){
            asciiValue = sharedItem.charAt(0)-DIFFERENCE_FOR_UPPERCASE;
        }else{
            asciiValue = sharedItem.charAt(0)-DIFFERENCE_FOR_LOWERCASE;
        }
        return asciiValue;
    }

    public static ArrayList<Integer> getPriorityValues(ArrayList<String> sharedItemList) {
        ArrayList<Integer> priorityValueList = new ArrayList<>();
        for (String sharedItem : sharedItemList) {
            priorityValueList.add(getValueOfCharacter(sharedItem));
        }
        return priorityValueList;
    }

    public static int getTheSumOfPriorityValues(ArrayList<Integer> priorityValueList) {
        int sum = 0;
        for (Integer priorityValue : priorityValueList) {
            sum += priorityValue;
        }
        return sum;
    }

    public static String findTheBadgeType(ArrayList<String> itemsOfAGroup) {
        String firstItemList = itemsOfAGroup.get(0);
        String secondItemList = itemsOfAGroup.get(1);
        String[] items = itemsOfAGroup.get(2).split("");
        for (int i = 0; i < items.length; i++) {
            if (firstItemList.contains(items[i]) && secondItemList.contains(items[i])){
                return items[i];
            }
        }
        return null;
    }

    public static ArrayList<String> getAllBadgeTypes(ArrayList<ArrayList<String>> itemListByGroup) {
        ArrayList<String> badgeTypeList = new ArrayList<>();
        for (ArrayList<String> itemsOfAGroup : itemListByGroup) {
            badgeTypeList.add(findTheBadgeType(itemsOfAGroup));
        }
        return badgeTypeList;
    }

    public static int pipeLine_1(String filePath) {
        return getTheSumOfPriorityValues(
                getPriorityValues(
                        getSharedItems(
                                getItemList(filePath)
                        )
                )
        );
    }

    public static int pipeLine_2(String filePath) {
        return getTheSumOfPriorityValues(
                getPriorityValues(
                        getAllBadgeTypes(
                                getItemListByGroups(
                                        getItemList(filePath)
                                )
                        )
                )
        );
    }
}
