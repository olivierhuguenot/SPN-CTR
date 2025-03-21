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

        // Generiert die 5 Schlüssel und speichert sie im keys array
        String[] keys = {key.substring(0,16), key.substring(4,20), key.substring(8,24), key.substring(12,28), key.substring(16,32)};
        System.out.println("Keys: " + Arrays.toString(keys));

        // Das zufällige Y-1 berechnen
        Random random = new Random();
        int y = random.nextInt(32768);
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
            result += ctrResultBinary;

            // Nächster 16 Bit Block des Klartextes
            start = start + 16;
            end = end + 16;
        }
        return result;
    }

    // SPN Algorithm
    public static String spnAlgorithm(int y, String[] keys) {

            // Initialer Weisschritt: 1. y XOR k[0]
            y = y ^ Integer.parseInt(keys[0], 2);
            System.out.println("Weissschritt: " + y);

            // Regulärer Schritt: - 1. y in sBox 2. y in permutation 3. XOR mit k[1]
            y = sBox[y];
            y = permutation[y];
            y = y ^ Integer.parseInt(keys[1], 2);

            // Regulärer Schritt: 1. y in sBox 2. y in permutation 3. XOR mit k[2]
            y = sBox[y];
            y = permutation[y];
            y = y ^ Integer.parseInt(keys[2], 2);
            // Regulärer Schritt: 1. y in sBox 2. y in permutation 3. XOR mit k[3]
            y = sBox[y];
            y = permutation[y];
            y = y ^ Integer.parseInt(keys[3], 2);
            // Letzte Verkürzte Runde: 1. y in sBox 2. XOR mit k[4]
            y = sBox[y];
            y = y ^ Integer.parseInt(keys[4], 2);
        return Integer.toString(y, 2);
    }
}