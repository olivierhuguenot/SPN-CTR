import java.util.Random;

public class Encryption {

    final static private int[] sBox = {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7};
    final static private int[] permutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};

    public static String encrypt(String x, String k) {

        // Klartext mit Ascii Encoden
        String encoded = asciiEncoding(x);

        // Gesamter Klartext für Verschlüsselung übergeben
        String result = ctrAlgorithm(encoded, k);
        System.out.println("Encryption Result: " + result);

        return result;
    }

    private static String ctrAlgorithm(String x, String key) {
        // Erhalte durch 16 Teilbarer Klartext
        // Erhalte 16 Bit langer Schlüssel

        // Generiert die 5 Schlüssel und speichert sie im keys array
        String[] keys = {key.substring(0,16), key.substring(4,20), key.substring(8,24), key.substring(12,28), key.substring(16,32)};
        //System.out.println("Keys: " + Arrays.toString(keys));

        // Das zufällige Y-1 berechnen
        Random random = new Random();
        int y = random.nextInt(65536); // 16 Bits lang
        int yMinus1 = y;
        //System.out.println("Y-1: " + yMinus1 + " = " + String.format("%16s", Integer.toBinaryString(yMinus1)).replace(' ', '0'));

        // Anzahl Iterationen: Klartext Länge / 16
        int iterations = x.length() / 16;
        int start = 0;
        int end = 16;

        String result = "";

        for(int i = 0; i < iterations; i++) {
            // Übergabe des y und Schlüssels an SPN
            int spnResult = spnAlgorithm(y, keys);

            // SPN Resultat XOR Klartext Block
            String plaintextBlock = x.substring(start, end);
            int plaintextNumber = Integer.parseInt(plaintextBlock, 2); // Binär zu Integer

            int ctrResult = plaintextNumber ^ spnResult; // XOR Operation
            String ctrResultBinary = String.format("%16s", Integer.toBinaryString(ctrResult)).replace(' ', '0');

            result = result + ctrResultBinary;

            // Y erhöhen (Modulo falls Zahl grösser wird als 15)
            y = (y + 1) % 65536;

            // Nächster 16 Bit Block des Klartextes
            start = start + 16;
            end = end + 16;
        }
        return String.format("%16s", Integer.toBinaryString(yMinus1)).replace(' ', '0') + result;
    }

    public static int spnAlgorithm(int y, String[] keys) {
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

        // System.out.println("Resultat des SPN: " + String.format("%16s", Integer.toBinaryString(y)).replace(' ', '0'));
        return y;
    }

    private static int permutation(int y) {
        int newY = 0;
        for (int i = 0; i < 16; i++) {
            int bit = (y >> i) & 1;  // Extrahiere das Bit an Position i
            newY |= (bit << permutation[i]);  // Setze es an die neue Position
        }
        return newY;
    }

    public static String asciiEncoding(String x) {
        // x wird ASCII kodiert
        String binary = "";
        for (int i = 0; i < x.length(); i++) {
            // Füllt mit 0en auf wenn das kodierte ASCII-Zeichen nicht 8-Bit lang ist
            binary += String.format("%8s", Integer.toBinaryString(x.charAt(i))).replace(' ', '0');
        }

        // Padding wird wenn nötig hinzugefügt
        binary += "1";
        while (binary.length() % 16 != 0) {
            binary += "0";
        }

        System.out.println("Ascii Encoding % 16 = " + binary.length() % 16);
        return binary;
    }
}
