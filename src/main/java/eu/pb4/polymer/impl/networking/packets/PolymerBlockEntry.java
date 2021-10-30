package eu.pb4.polymer.impl.networking.packets;

import eu.pb4.polymer.api.block.PolymerBlock;
import eu.pb4.polymer.api.block.PolymerBlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public record PolymerBlockEntry(Identifier identifier, int numId, Text text, BlockState visual) implements BufferWritable {
    public void write(PacketByteBuf buf) {
        buf.writeIdentifier(identifier);
        buf.writeVarInt(numId);
        buf.writeText(text);
        buf.writeVarInt(Block.getRawIdFromState(PolymerBlockUtils.getBlockStateSafely((PolymerBlock) visual.getBlock(), visual)));
    }

    public static PolymerBlockEntry of(Block block) {
        return new PolymerBlockEntry(Registry.BLOCK.getId(block), Registry.BLOCK.getRawId(block), block.getName(), block.getDefaultState());
    }

    public static PolymerBlockEntry read(PacketByteBuf buf) {
        return new PolymerBlockEntry(buf.readIdentifier(), buf.readVarInt(), buf.readText(), Block.getStateFromRawId(buf.readVarInt()));
    }
}