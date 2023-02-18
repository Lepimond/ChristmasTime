package lepimond.christmastime.client;

public class ClientManaData {
    private static int manaLeft;

    public static void set(int mana) {
        ClientManaData.manaLeft = mana;
    }

    public static int getMana() {
        return manaLeft;
    }
}
