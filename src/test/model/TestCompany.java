package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCompany {
    private Company company;

    @BeforeEach
    public void runBefore() {
        company = new Company("ABC Corp", 35, 10,40);
    }

    @Test
    public void testConstructor() {
        assertEquals("ABC Corp", company.getName());
        assertEquals(35, company.getWomenInLeadership());
        assertEquals(10, company.getPayGap());
        assertEquals(40, company.getDiversityRatio());
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
    public void testUpdateDiversityRatio() {
        company.updateDiversityRatio(45);
        assertEquals(45, company.getDiversityRatio());
    }

    @Test
    public void testGetCompanyDetails() {
        String expectedDetails = "Company Name: ABC Corp\n" +
                                 "Women in Leadership: 35.0%\n" +
                                 "Pay Gap: 10.0%\n"+
                                 "Overall Diversity Ratio: 40.0";
        assertEquals(expectedDetails, company.getCompanyDetails());
    }
}

