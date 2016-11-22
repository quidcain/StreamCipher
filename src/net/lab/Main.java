package net.lab;

import java.io.*;
import java.lang.instrument.Instrumentation;
import java.util.BitSet;
/**
 * Created by stoat on 11/21/16.
 */
public class Main {
    public static void main(String[] args) {
        Lfsr lfsr = new Lfsr(new byte[]{1, 1, 1, 1}, new byte[]{1, 0, 0, 1});
        byte[] bytesFromFile = null;
        try(BufferedInputStream bfrInSt = new BufferedInputStream(new FileInputStream("Входной"))){
            bytesFromFile = new byte[bfrInSt.available()];
            bfrInSt.read(bytesFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyHolder keyHolder = new KeyHolder();
        try {
            keyHolder.enterByUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        encrypt(bytesFromFile, keyHolder.getBytes(bytesFromFile.length));
        try(BufferedOutputStream bfrOutSt = new BufferedOutputStream(new FileOutputStream("Выходной"))){
            bfrOutSt.write(bytesFromFile);
        }catch (FileNotFoundException e) {
            System.out.println("huevo" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitSet bitset = BitSet.valueOf(bytesFromFile);
        System.out.println();
        System.out.println("\nrabotaem dalshe");
    }
    public static void encrypt(byte[] bytesFromFile, byte[] bytesFromKey) {
        for (int i = 0; i < bytesFromFile.length; ++i) {
            bytesFromFile[i] ^= bytesFromKey[i];
        }
    }
}
