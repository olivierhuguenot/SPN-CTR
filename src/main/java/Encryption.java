public class Encryption {
    public static String encrypt(String x, String k) {

        // x ganz
        String encoded = asciiEncoding(x);

        // Gesamter Klartext übergeben
        String result = SPNmodeCTR.ctrAlgorithm("0011110011011110", "00111010100101001101011000111111");
        System.out.println("Result: " + result);

        // y-1 zufällig wählen

        // fällt ins SPN und ruft Merthoden aus SPN Klasse auf

        return encoded;
    }

    public static String asciiEncoding(String x) {
        // x wird ASCII kodiert
        String binary = "";
        for (int i = 0; i < x.length(); i++) {
            // Füllt mit 0en auf wenn das kodierte ASCII-Zeichen nicht 8-Bit lang ist
            binary += String.format("%08d", Integer.parseInt(Integer.toBinaryString(x.charAt(i))));
        }

        // Padding wird wenn nötig hinzugefügt
        if (binary.length() % 16 == 8) {
            binary += "1";
            while (binary.length() % 16 != 0) {
                binary += "0";
            }
        }
        return binary;
    }

    public static void main(String[] args) {
        System.out.println("Encrypted: " + encrypt("abc", "00111010100101001101011000111111"));
    }
}
