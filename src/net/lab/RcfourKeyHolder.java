package net.lab;

/**
 * Created by stoat on 11/24/16.
 */
public class RcfourKeyHolder {
    int[] sbox = new int[265];
    int i = 0;
    int j = 0;
    public RcfourKeyHolder(int[] userKey){
        for (int i = 0; i < 256; ++i)
            sbox[i] = i;
        int j = 0;
        for (int i = 0; i < 256; ++i) {
            j = (j + sbox[i] + userKey[i % userKey.length]) % 256;
            int buf = sbox[i];
            sbox[i] = sbox[j];
            sbox[j] = buf;
        }
    }
    private byte getByteOfKey() {
        i = (i + 1) % 256;
        j = (j + sbox[i]) % 256;
        int buf = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = buf;
        return (byte)sbox[(sbox[i] + sbox[j]) % 256];
    }
    public byte[] getBytes(int size){
        byte[] key = new byte[size];
        for (int i = 0; i < size; ++i)
            key[i] = getByteOfKey();
        return key;
    }
}
