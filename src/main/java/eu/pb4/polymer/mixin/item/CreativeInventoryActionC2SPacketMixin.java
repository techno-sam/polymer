package eu.pb4.polymer.mixin.item;

import eu.pb4.polymer.item.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreativeInventoryActionC2SPacket.class)
public class CreativeInventoryActionC2SPacketMixin {
    @Unique ItemStack polymer_cachedItemStack = null;

    @Inject(method = "getItemStack", at = @At("TAIL"), cancellable = true)
    private void polymer_replaceWithReal(CallbackInfoReturnable<ItemStack> cir) {
        if (this.polymer_cachedItemStack == null) {
            this.polymer_cachedItemStack = ItemHelper.getRealItemStack(cir.getReturnValue());
        }

        cir.setReturnValue(this.polymer_cachedItemStack);
    }
}