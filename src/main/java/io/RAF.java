package main.java.io;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class RAF<T> implements AutoCloseable{
    public static final String BASE_ADDRESS = "main/resources/";
    private File file;

    @Getter
    private RandomAccessFile raf;

    public RAF(String address) {
        String dynamicAddress = RAF.getDynamicAddress(address);
        // TODO: 6/18/2020 create file and raf
    }

    public abstract void add(T obj) throws IOException;


    public long getEndOfRaf() throws IOException {
        return getRaf().length();
    }

    public static String getDynamicAddress(String file) {
        // TODO: 6/18/2020 complete this
        return null;
    }

    @Override
    public void close() {

    }
}
