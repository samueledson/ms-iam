package br.com.blendtecnologia.iam.core.domain.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.blendtecnologia.iam.core.domain.exceptions.DomainException;

class DomainExceptionTest {

    @Test
    void testConstructorWithMessage() {
        // Arrange
        String message = "Test exception message";

        // Act
        DomainException exception = new DomainException(message);

        // Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithNullMessage() {
        // Act
        DomainException exception = new DomainException(null);

        // Assert
        Assertions.assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        // Act
        DomainException exception = new DomainException("");

        // Assert
        Assertions.assertEquals("", exception.getMessage());
    }
}
