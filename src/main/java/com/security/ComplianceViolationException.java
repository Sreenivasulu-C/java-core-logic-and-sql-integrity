package com.security;

/**
 * Custom runtime exception handling data syntax breaches
 * during structural logic analysis.
 */
public class ComplianceViolationException extends RuntimeException {
    public ComplianceViolationException(String diagnosticMessage) {
        super("[COMPLIANCE FAILURE] " + diagnosticMessage);
    }
}
