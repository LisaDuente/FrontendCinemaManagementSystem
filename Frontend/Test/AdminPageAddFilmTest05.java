import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
// import org.junit.jupiter.api.Assertions.*;
// import static org.junit.jupiter.api.Assertions.*;
// import Panels.AdminPageAddFilm;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AdminPageAddFilmTest05 {

    @Test
    void fillList() {
    }

    @Test
    void errorMessageAddButton() {
    }

    @Test
    void getBack() {
    }

    @Test
    void updateButtonFunctionality() {
    }

    @Test
    void clearAllText() {
    }

    // @Test
    // void encodeToURL() {
    // }

    public String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest01() {
        String input = "Wizard Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard+Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest02() {
        String input = "Wizard&Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%26Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest03() {
        String input = "Wizard-Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard-Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest04() {
        String input = "Wizard+Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%2BGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest05() {
        String input = "Wizard/Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%2FGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest06() {
        String input = "Wizard_Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard_Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest07() {
        String input = "Wizard:Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%3AGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest08() {
        String input = "Wizard;Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%3BGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest09() {
        String input = "Wizard.Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard.Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest10() {
        String input = "Wizard,Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%2CGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest11() {
        String input = "Wizard*Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard*Gandolf");
    }
}