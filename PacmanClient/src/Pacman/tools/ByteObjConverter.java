package Pacman.tools;/*字节流转为对象，对象转换为字节流*/
import java.io.*;

public class ByteObjConverter {
    public static Object ByteToObject(byte[] bytes){
        //类对象
        Object obj = null;
        //字节数组输入流对象
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        //类输入流对象
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi);
            //读取对象
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭字节数组流对象
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //关闭类输入流对象
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //返回字节流里的对象
        return obj;
    }

    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        //字节数组输出流
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        //类输出流
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //返回字节数组
        return bytes;
    }
}
