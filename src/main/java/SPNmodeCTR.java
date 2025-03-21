import java.util.Arrays;
import java.util.Random;

public class SPNmodeCTR {

    final static private int[] sBox = {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7};

    final static private int[] sInverse = {14, 3, 4, 8, 1, 12, 10, 15, 7, 13, 9, 6, 11, 2, 5};

    final static private int[] permutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};

    final static private int[] permutationInverse = {0, 4, 8, 12, 1, 5, 9, 10, 14, 3, 7, 11, 15};

    // CTR Algorithm

    public static String ctrAlgorithm(String x, String key) {
        // Erhalte durch 16 Teilbarer Klartext
        // Erhalte 16 Bit langer Schlüssel

        // Die 5 Schlüssel
        String[] keys = {key.substring(0,16), key.substring(4,20), key.substring(8,24), key.substring(12,28), key.substring(16,32)};
        System.out.println("Keys: " + Arrays.toString(keys));

        // Das zufällige Y-1 berechnen
        int y = generateRandomY();
        System.out.println("Y-1: " + y);

        // SPN: Y mit Schlüssel verschlüsseln
        // Iterationen: Klartext Länge / 16
        int iterations = x.length() / 16;
        int start = 0;
        int end = 16;

        String result = "";

        for(int i = 0; i < iterations; i++) {
            // Y erhöhen (Modulo falls Zahl grösser wird als 15)
            y = (y + i) % 16;
            System.out.println(y);

            // Übergabe des y und Schlüssels an SPN
            String spnResult = spnAlgorithm(y, keys);

            // SPN Resultat XOR Klartext Block
            String plaintextBlock = x.substring(start, end);
            int plaintextNumber = Integer.parseInt(plaintextBlock, 2); // Binär zu Integer
            int  spnResultNumber= Integer.parseInt(spnResult, 2);

            int ctrResult = plaintextNumber ^ spnResultNumber; // XOR Operation
            String ctrResultBinary= Integer.toBinaryString(ctrResult);
            result = result + ctrResultBinary;

            // Nächster 16 Bit Block des Klartextes
            start = start + 16;
            end = end + 16;
        }
        return result;
    }

    // SPN Algorithm

    public static String spnAlgorithm(int y, String[] keys) {
        for(int i = 0; i < 4; i++) {
            // Schlüssel


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
}