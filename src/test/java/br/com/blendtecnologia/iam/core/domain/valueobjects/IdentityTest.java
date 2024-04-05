package br.com.blendtecnologia.iam.core.domain.valueobjects;

import org.junit.jupiter.api.Test;

import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;

import static org.junit.jupiter.api.Assertions.*;

class IdentityTest {

    @Test
    void testNothing() {
        Identity identity = Identity.nothing();
        assertEquals(Long.MIN_VALUE, identity.getNumber());
    }

    @Test
    void testEqualsSameInstance() {
        Identity identity = new Identity(123L);

        assertEquals(identity, identity);
    }

    @Test
    void testEqualsDifferentClass() {
        Identity identity = new Identity(123L);
        Object other = new Object();

        assertNotEquals(identity, other);
    }

    @Test
    void testEqualsDifferentNonNullNumbers() {
        Identity identity1 = new Identity(123L);
        Identity identity2 = new Identity(456L);

        assertNotEquals(identity1, identity2);
    }

    @Test
    void testEqualsNullNumber() {
        Identity identity1 = new Identity(null);
        Identity identity2 = new Identity(null);

        assertEquals(identity1, identity2);
    }

    @Test
    void testEqualsThisNumberNullOtherNumberNonNull() {
        Identity identity1 = new Identity(null);
        Identity identity2 = new Identity(123L);

        assertNotEquals(identity1, identity2);
    }

    @Test
    void testHashCode() {
        Identity identity1 = new Identity(123L);
        Identity identity2 = new Identity(123L);
        assertEquals(identity1.hashCode(), identity2.hashCode());
    }

    @Test
    void testHashCodeEquality() {
        Identity identity1 = new Identity(null);
        Identity identity2 = new Identity(null);
        Identity identity3 = new Identity(123L);
        assertEquals(identity1.hashCode(), identity2.hashCode());
        assertNotEquals(identity1.hashCode(), identity3.hashCode());
    }

    @Test
    void testToString() {
        Identity identity = new Identity(123L);
        String expectedToString = "Identity(number=123)";
        assertEquals(expectedToString, identity.toString());
    }
    
}