public class Cryptosystem {
    public static void main(String[] args) {
        //Klartext und Schlüssel (32-Bit) ohne Leerzeichen
        /*
        String plain = "Dies ist ein Test";
        String keyEncryption = "00010001001010001000110000000000";
        String resultEncryption = Encryption.encrypt(plain, keyEncryption);
        */
        //Chiffretext und Schlüssel (32-Bit) ohne Leerzeichen
        String chiffre = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
        String keyDecryption = "00010001001010001000110000000000";
        String resultDecryption = Decryption.decrypt(chiffre, keyDecryption);
        System.out.println("Resultat Decryption:" + resultDecryption);

        // SPN Test
        String key = "00010001001010001000110000000000";
        String[] keys = {key.substring(0,16), key.substring(4,20), key.substring(8,24), key.substring(12,28), key.substring(16,32)};
        System.out.println("SPN Test: " + (Integer.parseInt("1010111010110100", 2) == Encryption.spnAlgorithm(Integer.parseInt("0001001010001111", 2), keys)));
    }
}