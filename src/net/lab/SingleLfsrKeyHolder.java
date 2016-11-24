package net.lab;

import java.io.*;

/**
 * Created by stoat on 11/22/16.
 */
class SingleLfsrKeyHolder extends StreamKey {
    private static final byte[][] polynomials = {{24, 4, 3, 1}, {32, 28, 27, 1}, {40, 21, 19, 2}};
    private Lfsr lfsr;
    public SingleLfsrKeyHolder(byte[] bits, int size){
        int i = 0;
        while (polynomials[i][0] != bits.length) {
            ++i;
        }
        byte[] taps = polynomials[i];
        lfsr = new Lfsr(bits, taps);
        generateBytes(size);
    }
    @Override
    protected byte generateByte() {
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
}

