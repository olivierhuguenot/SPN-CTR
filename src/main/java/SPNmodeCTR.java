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
        int y = random.nextInt(32768); // 16 Bits lange (0 - 32768)
        int yMinus1 = y;
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
        return Integer.toBinaryString(yMinus1) + result;
    }

    // SPN Algorithm
    public static String spnAlgorithm(int y, String[] keys) {
        // ----------------------Initialer Weissschritt-------------------------------

        y = y ^ Integer.parseInt(keys[0], 2);

        // ----------------------01 Reguläre Runde------------------------------------

        // 0xF ist 0000 0000 0000 1111 in Binär. Kombiniert mit & Operator erhalten wir nur die letzten 4 Bits
        int y1 = y & 0xF;           // Letzten 4 Bits extrahieren
        int y2 = (y >> 4) & 0xF;    // Nächste 4 Bits extrahieren
        int y3 = (y >> 8) & 0xF;    // Nächste 4 Bits extrahieren
        int y4 = (y >> 12) & 0xF;   // Erste 4 Bits extrahieren

        // Mit S-Box ersetzen
        y1 = sBox[y1];
        y2 = sBox[y2];
        y3 = sBox[y3];
        y4 = sBox[y4];

        // Wieder zusammensetzen zu 16 Bits
        y = (y4 << 12) | (y3 << 8) | (y2 << 4) | y1;

        // Permutation
        y = permutation(y);

        // y XOR Schlüssel
        y = y ^ Integer.parseInt(keys[1], 2);

        // ----------------------02 Reguläre Runde------------------------------------

        // 0xF ist 0000 0000 0000 1111 in Binär. Kombiniert mit & Operator erhalten wir nur die letzten 4 Bits
        y1 = y & 0xF;           // Letzten 4 Bits extrahieren
        y2 = (y >> 4) & 0xF;    // Nächste 4 Bits extrahieren
        y3 = (y >> 8) & 0xF;    // Nächste 4 Bits extrahieren
        y4 = (y >> 12) & 0xF;   // Erste 4 Bits extrahieren

        // Mit S-Box ersetzen
        y1 = sBox[y1];
        y2 = sBox[y2];
        y3 = sBox[y3];
        y4 = sBox[y4];

        // Wieder zusammensetzen zu 16 Bits
        y = (y4 << 12) | (y3 << 8) | (y2 << 4) | y1;

        // Permutation
        y = permutation(y);

        // y XOR Schlüssel
        y = y ^ Integer.parseInt(keys[2], 2);

        // ----------------------03 Reguläre Runde------------------------------------

        // 0xF ist 0000 0000 0000 1111 in Binär. Kombiniert mit & Operator erhalten wir nur die letzten 4 Bits
        y1 = y & 0xF;           // Letzten 4 Bits extrahieren
        y2 = (y >> 4) & 0xF;    // Nächste 4 Bits extrahieren
        y3 = (y >> 8) & 0xF;    // Nächste 4 Bits extrahieren
        y4 = (y >> 12) & 0xF;   // Erste 4 Bits extrahieren

        // Mit S-Box ersetzen
        y1 = sBox[y1];
        y2 = sBox[y2];
        y3 = sBox[y3];
        y4 = sBox[y4];

        // Wieder zusammensetzen zu 16 Bits
        y = (y4 << 12) | (y3 << 8) | (y2 << 4) | y1;

        // Permutation
        y = permutation(y);

        // y XOR Schlüssel
        y = y ^ Integer.parseInt(keys[3], 2);

        // ----------------------Letzte Verkürzte Runde----------------------------------

        // 0xF ist 0000 0000 0000 1111 in Binär. Kombiniert mit & Operator erhalten wir nur die letzten 4 Bits
        y1 = y & 0xF;           // Letzten 4 Bits extrahieren
        y2 = (y >> 4) & 0xF;    // Nächste 4 Bits extrahieren
        y3 = (y >> 8) & 0xF;    // Nächste 4 Bits extrahieren
        y4 = (y >> 12) & 0xF;   // Erste 4 Bits extrahieren

        // Mit S-Box ersetzen
        y1 = sBox[y1];
        y2 = sBox[y2];
        y3 = sBox[y3];
        y4 = sBox[y4];

        // Wieder zusammensetzen zu 16 Bits
        y = (y4 << 12) | (y3 << 8) | (y2 << 4) | y1;

        // y XOR Schlüssel
        y = y ^ Integer.parseInt(keys[4], 2);

        return Integer.toBinaryString(y);
    }

    public static int permutation(int y) {
        int newY = 0;
        for (int i = 0; i < 16; i++) {
            int bit = (y >> i) & 1;  // Extrahiere das Bit an Position i
            newY |= (bit << permutation[i]);  // Setze es an die neue Position
        }
        return newY;
    }

    public static void main(String[] args) {
        String[] test = {"0001000100101000", "0001001010001000", "0010100010001100", "1000100011000000", "1000110000000000"};
        System.out.println("Resultat des SPN: " + spnAlgorithm(Integer.parseInt("0001001010001111", 2), test));
    }
}