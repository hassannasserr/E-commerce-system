package strategy.expiration;

import strategy.expiration.ExpirationStrategy;

public class NonExpirableStrategy implements ExpirationStrategy {
    @Override
    public boolean isExpired() {
        return false;
    }
}
