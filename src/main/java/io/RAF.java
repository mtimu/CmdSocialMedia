package main.java.io;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class RAF<T> implements AutoCloseable {
    public static final String BASE_ADDRESS = "/src/main/resources/";

    @Getter
    private RandomAccessFile raf;

    public RAF(String address) {
        String dynamicAddress = RAF.getDynamicAddress(address);
        try {
            raf = new RandomAccessFile(dynamicAddress , "rw");
        } catch (FileNotFoundException e) {
            System.err.println("Failed to create RandomAccessFile for address '" + dynamicAddress + "'");
            e.printStackTrace();
        }
    }

    public abstract void add(T obj) throws IOException;


    public long getEndOfRaf() throws IOException {
        return getRaf().length();
    }

    public static String getDynamicAddress(String fileAddress) {
        File file = new File(BASE_ADDRESS + fileAddress);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create file with address: " + fileAddress);
                e.printStackTrace();
            }
        }
        return file.getPath();
    }

    @Override
    public void close() {

    }
}
