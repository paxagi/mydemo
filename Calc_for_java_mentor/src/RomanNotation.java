import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNotation {
    final static String expression = "(?:-?)(?:M{0,3})(?:C[DM]|D?C{0,3})(?:X[LC]|L?X{0,3})(?:I[VX]|V?I{0,3})";
    final static String convertExpression = "^(?:-?)(M{0,3})(C[DM]|D?C{0,3})(X[LC]|L?X{0,3})(I[VX]|V?I{0,3})$";
    private int number;
    private String note;

    public RomanNotation(int number) {
        setNumber(number);
        note = toNote(number);
    }

    public RomanNotation(String note) {
        setNote(note);
        number = toNumber(note);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNumber() {
        return number;
    }

    public String getNote() {
        return note;
    }

    public static int toNumber(String note) {
        int result = 0;
        boolean negativ = (note.charAt(0) == '-');

        Pattern pattern = Pattern.compile(convertExpression);
        Matcher matcher = pattern.matcher(note);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "M" : result += 1000; break;
                case "MM" : result += 2000; break;
                case "MMM" : result += 3000; break;
            }
            switch (matcher.group(2)) {
                case "C" : result += 100; break;
                case "CC" : result += 200; break;
                case "CCC" : result += 300; break;
                case "CD" : result += 400; break;
                case "D" : result += 500; break;
                case "DC" : result += 600; break;
                case "DCC" : result += 700; break;
                case "DCCC" : result += 800; break;
                case "CM" : result += 900; break;
            }
            switch (matcher.group(3)) {
                case "X" : result += 10; break;
                case "XX" : result += 20; break;
                case "XXX" : result += 30; break;
                case "XL" : result += 40; break;
                case "L" : result += 50; break;
                case "LX" : result += 60; break;
                case "LXX" : result += 70; break;
                case "LXXX" : result += 80; break;
                case "XC" : result += 90; break;
            }
            switch (matcher.group(4)) {
                case "I" : result += 1; break;
                case "II" : result += 2; break;
                case "III" : result += 3; break;
                case "IV" : result += 4; break;
                case "V" : result += 5; break;
                case "VI" : result += 6; break;
                case "VII" : result += 7; break;
                case "VIII" : result += 8; break;
                case "IX" : result += 9; break;
            }
        } else {
            throw new RuntimeException("неправильная нотация");
        }
        if (negativ) result *= -1;
        return result;
    }

    public static String toNote(int number) {
        String result = "";
        boolean negativ = (number < 0);
        if (negativ) number = Math.abs(number);

        int[] ranks = new int[4];
        if (Math.abs(number) < 4000) {
            ranks[0] = number / 1000;
            ranks[1] = (number - ranks[0]*1000) / 100;
            ranks[2] = (number - ranks[1]*100) / 10;
            ranks[3] = (number - ranks[2]*10);

            switch (ranks[0]) {
                case 1 : result = String.format("%sM", result); break;
                case 2 : result = String.format("%sMM", result); break;
                case 3 : result = String.format("%sMMM", result); break;
            }
            switch (ranks[1]) {
                case 1 : result = String.format("%sC", result); break;
                case 2 : result = String.format("%sCC", result); break;
                case 3 : result = String.format("%sCCC", result); break;
                case 4 : result = String.format("%sCD", result); break;
                case 5 : result = String.format("%sD", result); break;
                case 6 : result = String.format("%sDC", result); break;
                case 7 : result = String.format("%sDCC", result); break;
                case 8 : result = String.format("%sDCCC", result); break;
                case 9 : result = String.format("%sCM", result); break;
            }
            switch (ranks[2]) {
                case 1 : result = String.format("%sX", result); break;
                case 2 : result = String.format("%sXX", result); break;
                case 3 : result = String.format("%sXXX", result); break;
                case 4 : result = String.format("%sXL", result); break;
                case 5 : result = String.format("%sL", result); break;
                case 6 : result = String.format("%sLX", result); break;
                case 7 : result = String.format("%sLXX", result); break;
                case 8 : result = String.format("%sLXXX", result); break;
                case 9 : result = String.format("%sXC", result); break;
            }
            switch (ranks[3]) {
                case 1 : result = String.format("%sI", result); break;
                case 2 : result = String.format("%sII", result); break;
                case 3 : result = String.format("%sIII", result); break;
                case 4 : result = String.format("%sIV", result); break;
                case 5 : result = String.format("%sV", result); break;
                case 6 : result = String.format("%sVI", result); break;
                case 7 : result = String.format("%sVII", result); break;
                case 8 : result = String.format("%sVIII", result); break;
                case 9 : result = String.format("%sIX", result); break;
            }
        } else {
            throw new RuntimeException("недопустимое число");
        }

        if (negativ) {
            result = String.format("-%s", result);
        }
        return result;
    }
}
