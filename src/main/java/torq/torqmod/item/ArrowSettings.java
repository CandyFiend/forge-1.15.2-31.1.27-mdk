package torq.torqmod.item;

public class ArrowSettings {

    private final float maxArrowVelocity;
    private final double arrowDamage;
    private final float arrowInaccuracy;

    public ArrowSettings(ArrowSettings.Properties properties){
        this.maxArrowVelocity = properties.maxArrowVelocity;
        this.arrowDamage = properties.arrowDamage;
        this.arrowInaccuracy = properties.arrowInaccuracy;
    }

    public float getMaxArrowVelocity() {
        return this.maxArrowVelocity;
    }

    public double getArrowDamage() {
        return this.arrowDamage;
    }

    public float getArrowInaccuracy() {
        return this.arrowInaccuracy;
    }

    public static class Properties {
        /**
         * Default arrow has 1.0F
         */
        private float maxArrowVelocity = 1.0F;
        /**
         * Default arrow has 2.0D.
         */
        private double arrowDamage = 2.0D;
        /**
         * Default arrow has 1.0F
         */
        private float arrowInaccuracy = 1.0F;

        public ArrowSettings.Properties maxArrowVelocity(float maxArrowVelocityIn) {
            this.maxArrowVelocity = maxArrowVelocityIn;
            return this;
        }

        public ArrowSettings.Properties arrowDamage(double arrowDamageIn) {
            this.arrowDamage = arrowDamageIn;
            return this;
        }

        public ArrowSettings.Properties arrowInaccuracy(float arrowInaccuracyIn) {
            this.arrowInaccuracy = arrowInaccuracyIn;
            return this;
        }
    }
}
