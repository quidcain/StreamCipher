package net.lab;

/**
 * Created by stoat on 11/24/16.
 */
abstract class StreamKey {
    protected byte[] key;
    abstract protected byte generateByte();
    protected void generateBytes(int size){
        key = new byte[size];
        for (int i = 0; i < size; ++i)
            key[i] = generateByte();
    }
    public byte getByte(int index) {
        return key[index];
    }
}
