package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCompany {
    private Company company;

    @BeforeEach
    public void runBefore() {
        company = new Company("ABC Corp", 35, 10);
    }

    @Test
    public void testConstructor() {
        assertEquals("ABC Corp", company.getName());
        assertEquals(35, company.getWomenInLeadership());
        assertEquals(10, company.getPayGap());
    }

    @Test
    public void testUpdateWomenInLeadership() {
        company.updateWomenInLeadership(45);
        assertEquals(45, company.getWomenInLeadership());
    }

    @Test
    public void testUpdatePayGap() {
        company.updatePayGap(5);
        assertEquals(5, company.getPayGap());
    }

    @Test
    public void testGetCompanyDetails() {
        String expectedDetails = "Company Name: ABC Corp\n" +
                                 "Women in Leadership: 35.0%\n" +
                                 "Pay Gap: 10.0%";
        assertEquals(expectedDetails, company.getCompanyDetails());
    }
}

