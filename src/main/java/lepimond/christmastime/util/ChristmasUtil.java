package lepimond.christmastime.util;

import net.minecraft.util.Mth;

public class ChristmasUtil {
    public static int calculateSkyColor(float num) {
        float $$1 = num / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
