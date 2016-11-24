package net.lab;

import java.io.*;

/**
 * Created by stoat on 11/23/16.
 */
class TreatedFile {
    private byte[] bytesFromFile = null;
    public boolean read(String fileName) {
        try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(fileName));
            FileOutputStream resetFull = new FileOutputStream("KeyBinary");
            FileOutputStream reset1 = new FileOutputStream("Lfsr1Binary");
            FileOutputStream reset2 = new FileOutputStream("Lfsr2Binary");
            FileOutputStream reset3 = new FileOutputStream("Lfsr3Binary")){
            bytesFromFile = new byte[stream.available()];
            stream.read(bytesFromFile);
        } catch(NullPointerException e){
            return false;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            //imposible
            return false;
        }
        return true;
    }
    public int getLength() {
        return bytesFromFile.length;
    }
    public void encrypt(byte[] bytesFromKey) {
        for (int i = 0; i < bytesFromFile.length; ++i) {
            bytesFromFile[i] ^= bytesFromKey[i];
        }
    }
    public boolean write(String fileName) {
        try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName))){
            stream.write(bytesFromFile);
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            //imposible
            return false;
        }
        return true;
    }

}
