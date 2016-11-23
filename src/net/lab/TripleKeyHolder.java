package net.lab;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by stoat on 11/23/16.
 */
class TripleKeyHolder {
    Lfsr lfsr1, lfsr2, lfsr3;
    public TripleKeyHolder(byte[] bits1, byte[] taps1, byte[] bits2, byte[] taps2, byte[] bits3, byte[] taps3){
        lfsr1 = new Lfsr(bits1, taps1);
        lfsr2 = new Lfsr(bits2, taps2);
        lfsr3 = new Lfsr(bits3, taps3);
    }
    private byte getByteOfKey() {
        byte result = 0;
        try(BufferedOutputStream resultedStream = new BufferedOutputStream(new FileOutputStream("KeyBinary", true));
            BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream("Lfsr1Binary", true));
            BufferedOutputStream stream2 = new BufferedOutputStream(new FileOutputStream("Lfsr2Binary", true));
            BufferedOutputStream stream3 = new BufferedOutputStream(new FileOutputStream("Lfsr3Binary", true))) {
            for (int i = 7; i >= 0; --i){
                byte bit1 = lfsr1.getStreamBit();
                stream1.write(bit1 + 48);
                byte bit2 = lfsr2.getStreamBit();
                stream2.write(bit2 + 48);
                byte bit3 = lfsr3.getStreamBit();
                stream3.write(bit3 + 48);
                byte resultedBit = (byte) ((bit1 & bit2) | (~bit1 & bit3));
                result |= resultedBit << i;
                resultedStream.write(resultedBit + 48);
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
