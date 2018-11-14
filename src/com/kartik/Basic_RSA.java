package com.kartik;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("Duplicates")

public class Basic_RSA {

    private static BigInteger P;
    private static BigInteger Q;            // large prime numbers p and q.
    private static BigInteger N;                                    // N
    private static BigInteger phi;                                // Phi(N) = ( p - 1 ) * ( q - 1 )
    private static BigInteger E = BigInteger.valueOf(3);        // Public E initially taken as 3.
    private static BigInteger D;                                   // Private D
    private static int bitLength = 1024;

    private static void createKeys() {

        Random rand1 = new Random(System.currentTimeMillis());
        Random rand2 = new Random(System.currentTimeMillis() * 10);

        /* probablePrime is used to randomly generate positive number that is probably prime */
        P = BigInteger.probablePrime(bitLength / 2, rand1);
        Q = BigInteger.probablePrime(bitLength / 2, rand2);
        phi = P.subtract(BigInteger.valueOf(1)).multiply(Q.subtract(BigInteger.valueOf(1)));
        N = P.multiply(Q);
        /* E value generation */
        while (true) {

            BigInteger GCD = phi.gcd(E); //calculating gcd
            if ((GCD.equals(BigInteger.ONE)) && (E.compareTo(phi) < 0) && (E.compareTo(BigInteger.ONE) > 0)) {
                break;
            } else {
                E = E.add(BigInteger.ONE);
            }
        }
        D = E.modInverse(phi);
        System.out.println("** Keys generated **");
        System.out.println("The Public Key Pair is: {" + E + "," + N + "]");
        System.out.println("The Private Key Pair is: {" + D + "," + N + "]");

    }

    private static void useKeys() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter P and Q");
        P = sc.nextBigInteger();
        Q = sc.nextBigInteger();
        phi = P.subtract(BigInteger.valueOf(1)).multiply(Q.subtract(BigInteger.valueOf(1)));
        N = P.multiply(Q);
        /* calculating E */
        while (true) {
            BigInteger GCD = phi.gcd(E); //calculating gcd
            if ((GCD.equals(BigInteger.ONE)) && (E.compareTo(phi) < 0) && (E.compareTo(BigInteger.ONE) > 0)) {
                break;
            } else {
                E = E.add(BigInteger.ONE);
            }
        }
        D = E.modInverse(phi);
        System.out.println("** Keys generated **");
        System.out.println("The Public Key Pair is: {" + E + "," + N + "]");
        System.out.println("The Private Key Pair is: {" + D + "," + N + "]");
    }

    private static void encryptDecryptUseKeys(String message, byte[] msg) {

        //BigInteger msg = new BigInteger(message);
        byte[] cipher;
        byte[] plain;
        String cipherTxt;
        String plainTxt;
        String ans = "";
        String ans2 = "";
        System.out.println();

        for (int i = 0; i < message.length(); i++) {

            msg = message.substring(i, i + 1).getBytes();
            cipher = encrypt(msg);
            cipherTxt = new String(cipher);
            ans += cipherTxt;
            plain = decrypt(cipher);
            plainTxt = new String(plain);
            ans2 += plainTxt;
        }

        System.out.println("CIPHER TEXT=" + ans);

        System.out.println("DECRYPTED TEXT =" + ans2);
    }

    private static byte[] encrypt(byte[] message) {

        BigInteger msg = new BigInteger(message);
        return msg.modPow(E, N).toByteArray();


    }

    private static byte[] decrypt(byte[] message) {

        BigInteger msg = new BigInteger(message);
        //System.out.println("Cipher Text in BigInteger form: " + msg);
        return msg.modPow(D, N).toByteArray();
    }

    public static void main(String[] args) {
        // write your code here
        long startTimeKeyGen = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        System.out.println("****** CRYPTOGRAPHY PROJECT ******");
        System.out.println("Press 1 to generate keys for RSA or Press 2 to enter your own values.");
        int input = sc.nextInt();
        if (input == 1) {
            createKeys();
        } else {
            useKeys();
        }
        long endTimeKeyGen = System.currentTimeMillis();
        long totalTimeKeyGen = endTimeKeyGen - startTimeKeyGen;
        System.out.println("The time taken to generate Keys: " + totalTimeKeyGen);
        int token = 1;
        while (token == 1) {
            System.out.println("Enter the message to be encrypted : ");
            Scanner sc1 = new Scanner(System.in);
            String message = sc1.nextLine();
            byte[] msg = message.getBytes();
            if (input == 1) {
                System.out.println("*** ENCRYPTION STARTS ***");
                long startTime = System.currentTimeMillis();
                byte[] cipher = encrypt(msg);
                String cipherTxt = new String(cipher);
                System.out.println("*** ENCRYPTION DONE ***");
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("The time taken in RSA encryption: " + totalTime);
                System.out.println("Cipher text: ");
                System.out.println(cipherTxt);
                System.out.println("*** DECRYPTION STARTS ***");
                long startTime1 = System.currentTimeMillis();
                byte[] plain = decrypt(cipher);
                String plainTxt = new String(plain);
                System.out.println("*** DECRYPTION DONE ***");
                long endTime1 = System.currentTimeMillis();
                long totalTime1 = endTime1 - startTime1;
                System.out.println("The time taken in RSA decryption: " + totalTime1);
                System.out.println("Plain text: ");
                System.out.println(plainTxt);
            } else {
                encryptDecryptUseKeys(message, msg);
            }
            System.out.println("Do you to enter again? (1/0)");
            Scanner sc2 = new Scanner(System.in);
            if (sc2.nextInt() == 0) token = 0;
        }
    }
}
