
class problema2 {
    public static int binarToDecimal(String binarNumber){
        try {
            int rez;
            rez = Integer.parseInt(binarNumber,2);
            return rez;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(binarToDecimal("1101"));
    }
}

class problema3{
    public static int hexToDecimal(String hexNumber){
        try {
            int rez;
            rez = Integer.parseInt(hexNumber,16);
            return rez;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(hexToDecimal("a"));
    }
}

class problema9{

}