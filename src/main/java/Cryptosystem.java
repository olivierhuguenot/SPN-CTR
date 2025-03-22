import java.util.Arrays;

public class Cryptosystem {
    public static void main(String[] args) {
      /*  //Klartext und Schlüssel (32-Bit) ohne Leerzeichen
        String plain = "Dies ist ein Test";
        String keyEncryption = "00010001001010001000110000000000";
        String resultEncryption = Encryption.encrypt(plain, keyEncryption);
        */
        //Chiffretext und Schlüssel (32-Bit) ohne Leerzeichen
        String chiffre = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
        String keyDecryption = "00010001001010001000110000000000";
        String resultDecryption = Decryption.decrypt(chiffre, keyDecryption);
        System.out.println("Resultat Decryption: " + resultDecryption);
    }

    /*
    public static void main(String[] args) {
        String[] test = {"0001000100101000", "0001001010001000", "0010100010001100", "1000100011000000", "1000110000000000"};
        System.out.println("Resultat des SPN: " + spnAlgorithm(Integer.parseInt("0001001010001111", 2), test));
    }
    */
}