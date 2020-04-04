package hu.iac.webshop.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    @Test
    @DisplayName("Should be active")
    void shouldBeActive() {
        /*arrange*/
        // Get Instant of now, minus 1 day and extract a Date from that
        Date yesterday = Date.from(Instant.now().minus(Duration.ofDays(1)));
        Account account = new Account("test", yesterday);

        /*act*/
        Boolean result = account.isActive();

        /*assert*/
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should NOT be active")
    void shouldNotBeActive() {
        /*arrange*/
        // Get Instant of now, minus 1 day and extract a Date from that
        Date tomorrow = Date.from(Instant.now().plus(Duration.ofDays(1)));
        Account account = new Account("test", tomorrow);

        /*act*/
        Boolean result = account.isActive();

        /*assert*/
        assertEquals(false, result);
    }
}
