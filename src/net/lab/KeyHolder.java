package net.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by stoat on 11/22/16.
 */
public class KeyHolder {
    Lfsr lfsr;
    public void enterByUser() throws IOException {
        /*int size;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите степень многочлена: ");
        //size = (char)bufferedReader.read();
        size = Byte.parseByte(bufferedReader.readLine());
        byte[] bits = new byte[size];
        byte[] taps = new byte[size];
        for (int i = 0; i < size; ++i){
            System.out.print("bits["+i+"] = ");
            bits[i] = Byte.parseByte(bufferedReader.readLine());
        }
        for (int i = 0; i < size; ++i){
            System.out.print("taps["+i+"] = ");
            taps[i] = Byte.parseByte(bufferedReader.readLine());
        }
        lfsr = new Lfsr(bits, taps);*/
        lfsr = new Lfsr(new byte[]{1, 1, 1, 1}, new byte[]{1, 0, 0, 1});
    }
    private byte getByteOfKey() {
        byte result = 0;
        for (int i = 0; i < 8; ++i)
            result |= lfsr.getStreamBit() << i;
        return result;
    }
    public byte[] getBytes(int size){
        byte[] key = new byte[size];
        for (int i = 0; i < size; ++i)
            key[i] = getByteOfKey();
        return key;
    }

}

