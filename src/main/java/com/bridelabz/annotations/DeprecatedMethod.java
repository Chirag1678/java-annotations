package com.bridelabz.annotations;

// Testing class
class LegacyAPI {
    @Deprecated
    public void oldFeature() {
        System.out.println("This is the old feature of legacy API, please use new feature.");
    }

    public void newFeature() {
        System.out.println("This is the new feature of API.");
    }
}

// Class to check and display warning by deprecated method
public class DeprecatedMethod {
    public static void main(String[] args) {
        // Create instance of Class
        LegacyAPI api = new LegacyAPI();

        // Call both methods
        api.oldFeature();
        api.newFeature();
    }
}
// Sample Output ->
// This is the old feature of legacy API, please use new feature.
// This is the new feature of API.

// Warning message ->
// Deprecated method oldFeature() is used in class com.bridelabz.annotations.DeprecatedMethod