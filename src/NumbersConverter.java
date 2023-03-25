import java.util.HashMap;
import java.util.Map;

class NumbersConverter {
    Map<String, Integer> romanMap = new HashMap<>();

    {
        romanMap.put("M", 1000);
        romanMap.put("D", 500);
        romanMap.put("C", 100);
        romanMap.put("L", 50);
        romanMap.put("X", 10);
        romanMap.put("V", 5);
        romanMap.put("I", 1);
    }

    int romanToArabic(String roman) {

        int result = 0;
        int lastValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            int currentValue = romanMap.get(String.valueOf(c));
            if (currentValue < lastValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            lastValue = currentValue;
        }
        return result;
    }

    String arabicToRoman(int arabic) throws Exception {

        if (arabic < 1 || arabic > 3999) {
            throw new Exception("Invalid input: " + arabic);
        }
        StringBuilder roman = new StringBuilder();
        for (Map.Entry<String, Integer> entry : romanMap.entrySet()) {
            while (arabic >= entry.getValue()) {
                roman.append(entry.getKey());
                arabic -= entry.getValue();
            }
        }
        return roman.toString();
    }
}
