package eu.pb4.polymer.ext.client.impl.client;

import eu.pb4.polymer.api.client.PolymerClientUtils;
import eu.pb4.polymer.ext.client.api.PolymerClientExtensions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

public class CERegistry {
    public final static Identifier RELOAD_LOGO_IDENTIFIER = new Identifier("polymer_client_ext", "reload_logo");
    public final static Identifier EMPTY_TEXTURE = new Identifier("polymer_client_ext", "empty");
    public static boolean customReloadLogo;
    public static int customReloadColor;
    public static int customReloadColorDark;
    public static PolymerClientExtensions.ReloadLogoOverride customReloadMode;
    public static int customReloadColorBar;
    public static int customReloadColorBarDark;


    public static void initialize() {
        PolymerClientUtils.ON_CLEAR.register(CERegistry::clear);
        PolymerClientUtils.ON_DISABLE.register(CERegistry::clear);

        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().getTextureManager().registerTexture(EMPTY_TEXTURE, new NativeImageBackedTexture(new NativeImage(512, 512, false)));
        });
    }

    public static void clear() {
        var client = MinecraftClient.getInstance();
        client.execute(() -> {
            customReloadLogo = false;
            var texture = client.getTextureManager().getOrDefault(RELOAD_LOGO_IDENTIFIER, null);
            if (texture != null) {
                client.getTextureManager().destroyTexture(RELOAD_LOGO_IDENTIFIER);
                texture.close();
            }
        });
    }
}