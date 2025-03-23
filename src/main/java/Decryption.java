import java.util.Arrays;

public class Decryption {

    public static String decrypt(String y, String k) {
        // Generiert die 5 Schlüssel und speichert sie im keys array
        String[] keys = {k.substring(0,16), k.substring(4,20), k.substring(8,24), k.substring(12,28), k.substring(16,32)};
        //System.out.println("Keys: " + Arrays.toString(keys));

        String res = "";
        // der Chiffretext y in Blöcke der länge l aufteilen, l = 16
        int yBlocks = y.length() / 16;
        String[] ys = new String[yBlocks];

        // Alle Chiffretextblöcke in ein array speichern von y-1 bis yn-1
        for (int i = 0; i < yBlocks; i++) {
            int index1 = i * 16;
            int index2 = (i + 1) * 16;
            ys[i] = y.substring(index1, index2);
        }
        System.out.println("ys: " + Arrays.toString(ys));

        // Y-1 und Schlüssel in das SPN geben
        // Das SPN Resultat XOR Chiffretextblock
        int yMinus1 = Integer.parseInt(ys[0], 2);
        System.out.println("yMinus1: " + yMinus1);

        for (int i = 0; i < ys.length-1; i++) {
            int yn = Integer.parseInt(ys[i+1], 2);
            int xn = Encryption.spnAlgorithm(yMinus1, keys) ^ yn;
            System.out.println("xTemp: " + xn);
            res += String.format("%16s", Integer.toBinaryString(xn)).replace(' ', '0');
            System.out.println("res: " + res + " - Länge res: " + res.length());
            yMinus1 = (yMinus1 + 1) % 65536;
        }

        // den Klartext Als Ascii Zeichen decoden
        System.out.println("Resultat nach SPN: " + res + "Länge nach SPN: " + res.length());
        return asciiDecoding(res);
    }

    public static String asciiDecoding(String binary) {

        // Padding entfernen
        /*
        int counter = 0;
        int l = binary.length();
        while (binary.charAt(binary.length()-1) == '0') {
            counter++;
            binary = binary.substring(0, l-counter);
        }
        binary = binary.substring(0, binary.length()-1);

        System.out.println("Zeichen ohne Padding: " + binary.length());
        */
        // 8er Blöcke des ganzen String nehmen dann zu int casten und wieder char für char zu einem String konkatinieren
        String result = "";
        for (int i = 0; i < binary.length(); i+=8) {
            String tempS = binary.substring(i,i+8);
            int tempI = Integer.parseInt(tempS, 2);
            char tempC = (char) tempI;
            result += tempC;
        }

        return result;

    }
}
