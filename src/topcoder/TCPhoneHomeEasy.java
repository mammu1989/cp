public class TCPhoneHomeEasy {

    public int validNumbers(int digits, String[] specialPrefixes) {
        int pow[] = new int[7];
        pow[0] = 10;
        for (int i = 1; i < 7; i++) {
            pow[i] = pow[i - 1] * 10;
        }
        int ans = pow[digits - 1];
        for (int i = 0; i < specialPrefixes.length; i++) {
            ans -= pow[digits - specialPrefixes[i].length()] / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        TCPhoneHomeEasy tcPhoneHomeEasy = new TCPhoneHomeEasy();

        System.out.println(tcPhoneHomeEasy.validNumbers(7, new String[]{ "0", "1", "911" }));
        System.out.println(tcPhoneHomeEasy.validNumbers(5, new String[]{ "0", "1", "911"}));
        System.out.println(tcPhoneHomeEasy.validNumbers(6, new String[]{ "1", "2", "3" }));
        System.out.println(tcPhoneHomeEasy.validNumbers(6, new String[]{ "1", "23", "345"}));
        System.out.println(tcPhoneHomeEasy.validNumbers(3, new String[]{ "411" }));
    }
}
