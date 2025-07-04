package strategy.expiration;

import strategy.expiration.ExpirationStrategy;

import java.time.LocalDate;

public class ExpirableStrategy implements ExpirationStrategy {
    private LocalDate expirationDate;

    public ExpirableStrategy(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
