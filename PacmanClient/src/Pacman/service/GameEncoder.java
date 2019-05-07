package Pacman.service;/*编码器*/

import Pacman.tools.ByteObjConverter;
import Pacman.message.Game;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class GameEncoder extends MessageToByteEncoder<Game> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Game game, ByteBuf out) throws Exception {
        byte[] datas = ByteObjConverter.ObjectToByte(game);
        out.writeBytes(datas);
        ctx.flush();
    }
}
