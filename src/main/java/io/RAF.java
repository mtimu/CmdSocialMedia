package main.java.io;

import java.io.File;
import java.io.RandomAccessFile;

public abstract class RAF<T> implements AutoCloseable{
    public static final String BASE_ADDRESS = "main/resources/";
    private File file;

    private RandomAccessFile raf;

    public RAF(String address) {
        String dynamicAddress = RAF.getDynamicAddress(address);
        // TODO: 6/18/2020 create file and raf
    }

    public abstract void add(T obj);


    public static String getDynamicAddress(String file) {
        // TODO: 6/18/2020 complete this
        return null;
    }

    @Override
    public void close() {

    }
}
