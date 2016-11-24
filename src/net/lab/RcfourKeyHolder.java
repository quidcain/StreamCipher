package net.lab;

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
        i = (i + 1) % 256;
        j = (j + sbox[i]) % 256;
        int buf = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = buf;
        return (byte)sbox[(sbox[i] + sbox[j]) % 256];
    }
}
