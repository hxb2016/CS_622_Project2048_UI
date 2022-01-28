package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The purpose of this class is to save data into a local file (Data.txt)
 */
public class SaveUsersData {
    /**
     * In the method, It uses Generic, because the type of the object call the method can be different
     */
    public static <T> void saveUsersData(T object, String path) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }
}
