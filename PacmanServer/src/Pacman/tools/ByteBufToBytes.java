package Pacman.tools;/*字节数组转换为字节*/
import io.netty.buffer.ByteBuf;

public class ByteBufToBytes {
    //将网络中传输的字节数组读取为字节
    public byte[] read(ByteBuf datas) {
        byte[] bytes = new byte[datas.readableBytes()];
        datas.readBytes(bytes);
        return bytes;
    }
}