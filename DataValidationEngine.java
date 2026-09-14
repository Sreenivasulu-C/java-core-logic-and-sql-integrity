package com.logic.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Technical Engine designed to validate core Java business logic 
 * and verify relational database schema data integrity.
 */
public class DataValidationEngine {

    // 1. Core Java Logic Flow Testing Module
    public boolean validateUserPayload(String email, int age) {
        System.out.println("[LOG] Initiating syntax validation flow for input payload...");
        
        // Edge-case constraint enforcement logic
        if (email == null || !email.contains("@")) {
            System.err.println("[ERROR] Edge-Case Violation: Invalid email structure detected.");
            return false;
        }
        
        if (age < 18 || age > 120) {
            System.err.println("[ERROR] Constraint Break: Provided biometric age values out of operational bounds.");
            return false;
        }

        System.out.println("[LOG] Logic validation successful. Code path executed without exceptions.");
        return true;
    }

    // 2. Relational Database Query & Schema Integrity Verification
    public boolean verifyDatabaseIntegrity(Connection connection, int recordId) {
        // Optimized query constructed to test data retrieval states and schema constraints
        String integrityCheckQuery = "SELECT record_status, schema_version FROM application_registry "
                                   + "WHERE record_id = ? AND status_flag = 'ACTIVE'";
        
        System.out.println("[LOG] Executing relational schema integrity audit query...");

        try (PreparedStatement preparedStatement = connection.prepareStatement(integrityCheckQuery)) {
            preparedStatement.setInt(1, recordId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String status = resultSet.getString("record_status");
                    System.out.println("[LOG] Data integrity confirmed. Schema status code: " + status);
                    return true;
                } else {
                    System.err.println("[WARN] Data Retrieval Failure: No records matched structural constraints.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("[CRITICAL] Database sandbox exception captured during validation: " + e.getMessage());
            return false;
        }
    }
}
