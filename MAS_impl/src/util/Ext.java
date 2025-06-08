package util;

import java.io.*;
import java.util.*;

public class Ext implements Serializable {
    private static Map<Class, List> ext = new HashMap<>();

    public Ext() {
        add();
    }

    public int add() {
        try {
            List list = ext.computeIfAbsent(this.getClass(), _ -> new ArrayList<>());
            list.add(this);

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int remove() {
        try {
            List list = ext.get(this.getClass());
            if (list != null) {
                list.remove(this);
            }

            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

    public static int save() {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("ExtSave.pls"))) {
            os.writeObject(ext);

            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

    public static int load() {
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("ExtSave.pls"))) {
            ext = (Map<Class, List>) is.readObject();

            return 1;
        } catch (IOException | ClassNotFoundException e) {
            return 0;
        }
    }

    public static Map<Class, List> getExt() {
           return Collections.unmodifiableMap(ext);
    }

//    public <T extends util.Ext> T findById(int id) {
//        List list = getExt(this.getClass());
//
//        T res = list.stream().filter(T -> T.getId())
//    }

    public static <T> List<T> getExt(Class<T> o) {
           ext.computeIfAbsent(o, _ -> new ArrayList<>());

           return Collections.unmodifiableList(ext.get(o));
    }
}
