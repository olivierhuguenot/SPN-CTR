import java.util.Arrays;
import java.util.Random;

public class SPNmodeCTR {

    final private int r = 4;
    final private int n = 4;
    final private int m = 4;

    final static private int[] sBox = {14, 4, 13, 1, 2, 15,
            11, 8, 3, 10, 6, 12, 5, 9, 0, 7};

    // CTR Algorithm

    public static String ctrAlgorithm(String x, String key) {
        // 16er Klartext Block kommt rein
        // 16er Schlüssel Block kommt rein

        // Die 5 Schlüssel
        String[] keys = {key.substring(0,16), key.substring(4,20), key.substring(8,24), key.substring(12,28), key.substring(16,32)};
        System.out.println("Keys: " + Arrays.toString(keys));

        // Das zufällige Y-1 berechnen
        int y = generateRandomY();
        System.out.println("Y-1: " + y);

        // SPN: Y mit Schlüssel verschlüsseln
        spnAlgorithm(y, keys);

        return null;
    }



    // SPN Algorithm

    public static String spnAlgorithm(int yMinus, String[] keys) {
        for(int i = 0; i < 4; i++) {
            // Schlüssel

            // y0 = yMinus + i (Modulo falls Zahl grösser wird als 15)
            int y = (yMinus + i) % 16;
            System.out.println(y);

            // Initialer Weisschritt: y0 XOR k[0]
            y = y ^ Integer.parseInt(keys[0], 2);
            System.out.println("Weissschritt: " + y);

            // Regulärer Schritt: y1 -> S-Box -> Bitpermutation -> XOR mit k[1]
            y = sBox[y];

            // Regulärer Schritt: y2
            // Letzte Verkürzte Runde: y3 XOR k[3]
        }
        return null;
    }

    public static int generateRandomY() {
        Random random = new Random();
        return random.nextInt(16);

    }

    public static int sInvers(int xi) {
        return 0;
    }

    public static int permutation(int xi) {
        return 0;
    }
}