
public class Cryptosystem {
    public static void main(String[] args) {
        //Klartext und Schlüssel (32-Bit) ohne Leerzeichen
        String plain = "Das Wetter ist schlecht";
        String keyEncryption = "00010001001010001000110000000000";
        String resultEncryption = Encryption.encrypt(plain, keyEncryption);

        //Chiffretext und Schlüssel (32-Bit) ohne Leerzeichen
        String chiffre = "";
        String keyDecryption = "";
        String resultDecryption = Decryption.decrypt("","");
    }

    /*
    public static void main(String[] args) {
        String[] test = {"0001000100101000", "0001001010001000", "0010100010001100", "1000100011000000", "1000110000000000"};
        System.out.println("Resultat des SPN: " + spnAlgorithm(Integer.parseInt("0001001010001111", 2), test));
    }
 */
}