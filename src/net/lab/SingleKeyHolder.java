package net.lab;

import java.io.*;

/**
 * Created by stoat on 11/22/16.
 */
class SingleKeyHolder {
    Lfsr lfsr;
    public SingleKeyHolder(byte[] bits, byte[] taps){
        lfsr = new Lfsr(bits, taps);
    }
    private byte getByteOfKey() {
        byte result = 0;
        try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("KeyBinary", true))) {
            for (int i = 7; i >= 0; --i){
                byte bit = lfsr.getStreamBit();
                stream.write(bit + 48);
                result |= bit << i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public byte[] getBytes(int size){
        byte[] key = new byte[size];
        for (int i = 0; i < size; ++i)
            key[i] = getByteOfKey();
        return key;
    }

}

