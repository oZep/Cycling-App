package com.example.segproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.google.android.material.textfield.TextInputEditText;

@RunWith(RobolectricTestRunner.class)
public class Tests {

    private Login loginActivity;
    private TextInputEditText mockEmailEditText;
    private TextInputEditText mockPasswordEditText;

    @Before
    public void setUp() {
        loginActivity = new Login();
        mockEmailEditText = mock(TextInputEditText.class);
        mockPasswordEditText = mock(TextInputEditText.class);
        loginActivity.editTextEmail = mockEmailEditText;
        loginActivity.editTextPassword = mockPasswordEditText;

        // Create a mock for AccountDBHandler within the test class
        Login.db = mock(AccountDBHandler.class);
    }



    @Test
    @Config(manifest=Config.NONE)
    public void testLoginInvalidUser() {
        // Arrange
        String email = "nonexistent@example.com";
        String password = "password";
        when(Login.db.getUser(email)).thenReturn(null);

        // Act
        boolean result = loginActivity.login(email, password, false);

        // Assert
        assertFalse(result);
    }

    @Test
    @Config(manifest=Config.NONE)
    public void testlogin_invalidcharacters(){
        // Arrange
        String email = "email$$$@gmail.com";
        String password = "£$%^&*<>:;#~_-+";
        when(Login.db.getUser(email)).thenReturn(null);

        // Act
        boolean result = loginActivity.login(email, password, false);

        // Assert
        assertFalse(result);
    }

    @Test
    @Config(manifest=Config.NONE)
    public void testLoginNullEmail() {
        // Arrange
        String password = "password";
        when(Login.db.getUser(null)).thenReturn(null);

        // Act
        boolean result = loginActivity.login(null, password, false);

        // Assert
        assertFalse(result);
    }
    @Test
    public void testLoginEmptyFields() {
        // Arrange
        String email = "";
        String password = "";

        // Act
        boolean result = loginActivity.login(email, password, false);

        // Assert
        assertFalse(result);
    }

    @Test
    @Config(manifest=Config.NONE)
    public void testLoginEmptyEmail() {
        // Arrange
        String password = "password";
        when(Login.db.getUser("")).thenReturn(null);

        // Act
        boolean result = loginActivity.login("", password, false);

        // Assert
        assertFalse(result);
    }

    @Test
    @Config(manifest=Config.NONE)
    public void testLoginEmptyPassword() {
        // Arrange
        String email = "user@example.com";
        when(Login.db.getUser(email)).thenReturn(null);

        // Act
        boolean result = loginActivity.login(email, "", false);

        // Assert
        assertFalse(result);
    }

}
