package TestUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedTestData {
	
    private static final Map<String, String> testData = new ConcurrentHashMap<>();
    
    // Store user credentials (called from Registration module)
    public static void storeUserCredentials(String email, String password) {
        testData.put("registeredEmail", email);
        testData.put("registeredPassword", password);
        System.out.println("💾 Stored user credentials - Email: " + email);
    }
    
    // Get stored email (called from Login module)
    public static String getRegisteredEmail() {
        String email = testData.get("registeredEmail");
        if (email == null) {
            throw new RuntimeException("No registered email found. Registration module must run first!");
        }
        System.out.println("📖 Retrieved registered email: " + email);
        return email;
    }
    
    // Get stored password (called from Login module)
    public static String getRegisteredPassword() {
        String password = testData.get("registeredPassword");
        if (password == null) {
            throw new RuntimeException("No registered password found. Registration module must run first!");
        }
        return password;
    }
    
    // Check if user is registered
    public static boolean hasRegisteredUser() {
        return testData.containsKey("registeredEmail") && testData.containsKey("registeredPassword");
    }
    
    // Clear all data (for cleanup)
    public static void clearAll() {
        testData.clear();
        System.out.println("🧹 All test data cleared");
    }

}
