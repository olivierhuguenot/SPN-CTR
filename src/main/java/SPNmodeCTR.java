import java.util.Arrays;
import java.util.Random;

public class SPNmodeCTR {

    final private int r = 4;
    final private int n = 4;
    final private int m = 4;

    final static private String[] sBox = {"1110", "0100", "1101", "0001", "0010", "1111",
            "1011", "1000", "0011", "1010", "0110", "1100", "0101", "1001", "0000", "0111"};

    // CTR Algorithm

    public static String ctrAlgorithm(String x, String key) {
        // 16er Klartext Block kommt rein
        // 16er Schlüssel Block kommt rein

        // Die 5 Schlüssel
        String[] keys = {key.substring(0,16), key.substring(4,20), key.substring(8,24), key.substring(12,28), key.substring(16,32)};
        System.out.println("Keys: " + Arrays.toString(keys));

        // Das zufällige Y-1 berechnen
        String y = generateRandomY();
        System.out.println("Y-1: " + y);

        // SPN: Y mit Schlüssel verschlüsseln
        spnAlgorithm(y, keys);

        return null;
    }



    // SPN Algorithm

    public static String spnAlgorithm(String yMinus, String[] keys) {
        for(int i = 0; i < 4; i++) {

            // y0 = yMinus + i
            int y = (Integer.parseInt(yMinus, 2) + i) % 16;

            // Initialer Weisschritt: y0 XOR k[0]
            y = y ^ Integer.parseInt(keys[0], 2);

            // Regulärer Schritt: y1 -> S-Box -> Bitpermutation -> XOR mit k[1]
            y = Integer.parseInt(sBox[y], 2);


            // Regulärer Schritt: y2
            // Letzte Verkürzte Runde: y3 XOR k[3]
        }



        return null;
    }

    public static String generateRandomY() {
        Random random = new Random();
        int randomNumber = random.nextInt(1 << 4);
        return String.format("%4s", Integer.toBinaryString(randomNumber)).replace(' ', '0');
    }

    public static int sInvers(int xi) {
        return 0;
    }

    public static int permutation(int xi) {
        return 0;
    }
}