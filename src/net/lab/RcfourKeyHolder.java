package net.lab;

import java.io.*;

/**
 * Created by stoat on 11/24/16.
 */
class RcfourKeyHolder extends StreamKey {
    private int[] sbox = new int[265];
    private int i = 0;
    private int j = 0;
    public RcfourKeyHolder(int[] userKey, int size){
        for (int i = 0; i < 256; ++i)
            sbox[i] = i;
        int j = 0;
        for (int i = 0; i < 256; ++i) {
            j = (j + sbox[i] + userKey[i % userKey.length]) % 256;
            int buf = sbox[i];
            sbox[i] = sbox[j];
            sbox[j] = buf;
        }
        generateBytes(size);
    }
    @Override
    protected byte generateByte() {
        int resultedByte = 0;
        try(BufferedWriter stream = new BufferedWriter(new FileWriter("Rc4", true))) {
            i = (i + 1) % 256;
            j = (j + sbox[i]) % 256;
            int buf = sbox[i];
            sbox[i] = sbox[j];
            sbox[j] = buf;
            resultedByte = sbox[(sbox[i] + sbox[j]) % 256];
            stream.write(resultedByte + " ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (byte)resultedByte;
    }
    /*public void displayKey() {
        try(PrintStream stream = new PrintStream("Rc4")) {
            for (int i = 0; i < 256; ++i){
                stream.format("%d ", sbox[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
