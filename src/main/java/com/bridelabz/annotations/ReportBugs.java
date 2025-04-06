package com.bridelabz.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Define the main annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

// Define the repeatable annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

// Class to apply annotation
class BugTracker {
    @BugReport(description = "Null point occurs when user is null")
    @BugReport(description = "Incorrect date format on older versions.")
    void processUserData() {
        System.out.println("Processing user data...");
    }
}

// Class to report bugs using custom annotation multiple times
public class ReportBugs {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Get the class object
            Class<BugTracker> cls = BugTracker.class;

            // Get method
            Method method = cls.getDeclaredMethod("processUserData");

            // Get repeatable annotations
            if(method.isAnnotationPresent(BugReport.class) || method.isAnnotationPresent(BugReports.class)) {
                BugReport[] reports = method.getAnnotationsByType(BugReport.class);

                System.out.println("Bug Reports for method: " + method.getName());
                for(BugReport report : reports) {
                    System.out.println("- " + report.description());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Bug Reports for method: processUserData
// - Null point occurs when user is null
// - Incorrect date format on older versions.