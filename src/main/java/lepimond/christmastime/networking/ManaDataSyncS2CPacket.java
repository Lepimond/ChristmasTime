package lepimond.christmastime.networking;

import lepimond.christmastime.client.ClientManaData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaDataSyncS2CPacket {
    private final int mana;

    public ManaDataSyncS2CPacket(int mana) {
        this.mana = mana;
    }

    public ManaDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(mana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Purely client code
            ClientManaData.set(mana);
        });
        return true;
    }
}
