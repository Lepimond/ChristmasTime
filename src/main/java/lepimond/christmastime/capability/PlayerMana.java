package lepimond.christmastime.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private final int MIN_MANA = 0;
    private final int MAX_MANA = 2;

    public int getMana() {
        return mana;
    }

    public void addMana(int number) {
        this.mana = Math.min(mana + number, MAX_MANA);
    }

    public void subMana(int number) {
        this.mana = Math.max(mana - number, MIN_MANA);
    }

    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mana", mana);
    }

    public void loadNBTData(CompoundTag nbt) {
        mana = nbt.getInt("mana");
    }
}
