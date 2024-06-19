package util;
import java.io.*;

public class SerializacaoUtil {

    public static void serializar(Object obj, String arquivo) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(arquivo);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(obj);
        }
    }

    public static Object desserializar(String arquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(arquivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return in.readObject();
        }
    }
}
