package net.lab;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by stoat on 11/21/16.
 */
class Lfsr {
    private final long maxKeyLength;
    private long totalBitsGenerated = 0;
    private byte[] bits;
    private final byte[] taps;
    private LinkedList<Byte> stream = new LinkedList<>();
    private ListIterator iterator;
    public Lfsr(byte[] bits, byte[] taps) {
        maxKeyLength = (long)Math.pow(2,(double)bits.length) - 1;
        this.bits = bits;
        this.taps = taps;
    }
    private byte shift() {
        byte output = bits[0];
        byte freshFirstBit = bits[taps[0] - 1];
        for (int j = 1; j < taps.length; ++j)
            freshFirstBit ^= bits[taps[j] - 1];
        for(int i = 0; i < bits.length - 1; ++i)
            bits[i] = bits[i + 1];
        bits[bits.length - 1] = freshFirstBit;
        return output;
    }
    public byte getStreamBit() {
        if(totalBitsGenerated != maxKeyLength) {
            ++totalBitsGenerated;
            stream.add(shift());
            return stream.getLast();
        }else {
            if (iterator == null || !iterator.hasNext())
                iterator = stream.listIterator();
            return (Byte)iterator.next();
        }
    }

}
