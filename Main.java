import java.util.Scanner;

class Calc
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Введите выражение с двумя римскими или арабскими числами от 1 до 10 включительно.");
        Scanner scan = new Scanner(System.in);
        String example = scan.nextLine();
        System.out.println(parse(example));
    }

    public static String parse(String example) throws Exception
    {
        int num1, num2;
        String oper, result;
        boolean isRoman;
        String[] operands = example.split("[+\\-*/]");
        if (operands.length != 2)
            throw new Exception("Вы ввели неподдерживаемые данные, попробуйте снова!");
        oper = identifySing(example);
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1]))
        {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1]))
        {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else
        {
            throw new Exception("Числа должны быть в одном формате!");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть меньше 10!");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman)
        {
            if (arabian <= 0)
            {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Roman.convertToRoman(arabian);
        }
        else
        {
            result = String.valueOf(arabian);
        }
        return result;
    }
    static String identifySing(String example) // метод для идентификации знака выражения
    {
        if (example.contains("+"))
            return "+";
        else if (example.contains("-"))
            return "-";
        else if (example.contains("*"))
            return "*";
        else if (example.contains("/"))
            return "/";
        else return null;
    }
    static int calc(int a, int b, String oper)
    {
        if (oper.equals("+"))
            return a + b;
        if (oper.equals("-"))
            return a - b;
        if (oper.equals("*"))
            return a * b;
        else
            return a / b;
    }
}

    class Roman
    {
        static String[] romanArray = new String[]{"0","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII",
                "XIV","XV","XVI","XVII","XVIII","XIX","XX","XXI","XXIV","XXV","XXVII","XXVIII","XXX","XXX","XXXII",
                "XXXV","XXXVI","XL","XLII","XLV","XLVIII","XLIX","L","LIV","LVI","LX","LXIII","LXIV","LXX","LXXII",
                "LXXX","LXXXI","XC","C"};

        public static  boolean isRoman(String val)
        {
            for (int i = 0; i < romanArray.length; i++)
            {
                if (val.equals(romanArray[i]))
                {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabian(String roman)
        {
            for (int i = 0; i < romanArray.length; i++)
            {
                if (roman.equals(romanArray[i]))
                {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabian)
        {
            return romanArray[arabian];
        }
    }
