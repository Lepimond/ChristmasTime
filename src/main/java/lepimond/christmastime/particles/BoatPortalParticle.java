package lepimond.christmastime.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BoatPortalParticle extends PortalParticle {
    public BoatPortalParticle(ClientLevel worldIn, double x, double y, double z, double xd, double yd, double zd) {
        super(worldIn, x, y, z, xd, yd, zd);

        this.lifetime = 50;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            System.out.println(this.lifetime);
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            this.x += this.xd * (double)f;
            this.y += this.yd * (double)f;
            this.z += this.zd * (double)f;
            this.setPos(this.x, this.y, this.z); // FORGE: update the particle's bounding box
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class BoatPortalProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public BoatPortalProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel worldIn, double x, double y, double z, double xd, double yd, double zd) {
            BoatPortalParticle particle = new BoatPortalParticle(worldIn, x, y, z, xd, yd, zd);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}
