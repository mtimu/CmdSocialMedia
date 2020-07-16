package main.java.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class RAF<T> implements AutoCloseable {
    public static final String BASE_ADDRESS = "\\src\\main\\resources\\";
    private final File file;

    private RandomAccessFile raf;

    public RAF(String address) {
        file = getFile(address);
        try {
            raf = new RandomAccessFile(file , "rw");
            if (isEmpty()) writeDefaults();
        } catch (FileNotFoundException e) {
            // "Failed to create RandomAccessFile for address '" + file.getAbsolutePath() + "'"
            e.printStackTrace();
        }
    }

    public abstract void add(T obj) throws IOException;

    public abstract void writeDefaults();

    public final int newId() throws IOException {
        seek(0);
        int id = readInt();
        seek(0);
        writeInt(id+1);
        return id;
    }

    //region Read Write Methods
    public final void writeInt(int value) throws IOException {
        raf.writeInt(value);
    }

    public final void writeStr(String value) throws IOException {
        raf.writeUTF(value);
    }

    public final int readInt() throws IOException {
        return raf.readInt();
    }

    public final String readStr() throws IOException {
        return raf.readUTF();
    }
    //endregion

    //region Seek methods
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public final boolean isPointerAtEnd() throws IOException {
        return raf.getFilePointer() >= raf.length();
    }

    public final long getPointer() throws IOException {
        return raf.getFilePointer();
    }

    public final void skip(int size) throws IOException {
        raf.skipBytes(size);
    }

    public final void seek(long position) throws IOException {
        raf.seek(position);
    }

    public final void seekStart() throws IOException {
        seek(0);
        skip(4);
    }

    public final void seekEnd() throws IOException {
        seek(raf.length());
    }

    public final boolean isEmpty() {
        return file.length() == 0;
    }
    //endregion

    @Override
    public final void close() throws IOException {
        raf.close();
    }

    //region Static methods
    public static File getFile(String address) {
        return new File(getDynamicAddress(address));
    }


    // TODO: 7/14/2020 this looks like shit! clean it up!
    public static String getDynamicAddress(String fileAddress) {
        String projectAddress = new File("").getAbsolutePath();

        File file = new File(projectAddress + BASE_ADDRESS + fileAddress);
        if (!file.exists()) {
            try {
                System.out.println("Create " + fileAddress + " in resources folder");
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) throw new IOException("Failed to create file with address: " + fileAddress);
            } catch (IOException e) {
                System.err.println("Failed to create file with address: " + fileAddress);
                e.printStackTrace();
            }
        }
        return file.getPath();
    }
    //endregion

}
