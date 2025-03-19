import java.util.HashMap;
import java.util.Random;

public class SPNmodeCTR {

    final private int r = 4;
    final private int n = 4;
    final private int m = 4;

    final private String[] sBox = {"1110", "0100", "1101", "0001", "0010", "1111",
            "1011", "1000", "0011", "1010", "0110", "1100", "0101", "1001", "0000", "0111"};
    final private String y = "";

    // SPN Algorithm

    public String spnAlgorithm(String x, String key) {
        // 16er Klartext Block kommt rein
        // 16er Schlüssel Block kommt rein

        // Ein zufälliges y berechnen
        Random random = new Random();
        int randomNumber = random.nextInt(1 << 16);
        String y = String.format("%16s", Integer.toBinaryString(randomNumber)).replace(' ', '0');
        System.out.println(y);

        // Die 4 benötigten Index der S-Box berechnen
        int[] sBoxIndexes = new int[4];
        for(int i = 0; i < 4; i++) {
            sBoxIndexes[i] = Integer.parseInt(x.substring(i, (i * 3) + 1), 2);
        }



        // Random Y mit Schlüssel verschlüsseln
        // Initialer Weissschritt

        //Reguläre Runde
        for(int i = 2; i < r; i++) {
            // S-Box
            // Bitpermutation
        }

        // Letzte Verkürzte Runde

        return null;
    }

    public int binaryToInt(int xi) {
        return 0;
    }

    public int sInvers(int xi) {
        return 0;
    }

    public int permutation(int xi) {
        return 0;
    }

    // CTR Algorithm

    public String ctrAlgorithm() {
        return null;
    }

    public String generateRandomY() {
        return null;
    }
}