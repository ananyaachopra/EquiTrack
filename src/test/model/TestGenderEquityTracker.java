package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestGenderEquityTracker {
    private GenderEquityTracker tracker;
    private Company company1;
    private Company company2;
    private Company company3;

    @BeforeEach
    public void runBefore() {
        tracker = new GenderEquityTracker();
        company1 = new Company("ABC Corp", 35, 15, 30);
        company2 = new Company("XYZ Inc", 50, 8, 50);
        company3 = new Company("Tech Solutions", 20, 10, 40);
    }

    @Test
    public void testAddCompanies() {
        tracker.addCompany(company1);
        tracker.addCompany(company2);

        List<Company> companies = tracker.getCompanies();
        assertEquals(2, companies.size());
        assertTrue(companies.contains(company1));
        assertTrue(companies.contains(company2));
    }

    @Test
    public void testRemoveCompany() {
        tracker.addCompany(company1);
        tracker.addCompany(company2);
        tracker.removeCompany("ABC Corp");

        List<Company> companies = tracker.getCompanies();
        assertEquals(1, companies.size());
        assertFalse(companies.contains(company1));
    }

    @Test
    public void testAverageWomenInLeadership() {
        assertEquals(0, tracker.averageWomenInLeadership(), 0.01);

        tracker.addCompany(company1);
        tracker.addCompany(company2);
        tracker.addCompany(company3);

        assertEquals(35, tracker.averageWomenInLeadership(), 0.01);
    }

    @Test
    public void testAveragePayGap() {
        assertEquals(0, tracker.averagePayGap(), 0.01);

        tracker.addCompany(company1);
        tracker.addCompany(company2);
        tracker.addCompany(company3);

        assertEquals(11, tracker.averagePayGap(), 0.01);
    }

    @Test
    public void testAverageDiversityRatio() {
        assertEquals(0, tracker.averageDiversityRatio(), 0.01);
        tracker.addCompany(company1);
        tracker.addCompany(company2);
        tracker.addCompany(company3);

        assertEquals(40, tracker.averageDiversityRatio(), 0.01);
    }

    @Test
    public void testGetHighestWomenInLeadership() {
        assertEquals(null, tracker.getHighestWomenInLeadership());

        tracker.addCompany(company1);
        tracker.addCompany(company2);
        tracker.addCompany(company3);

        assertEquals(company2, tracker.getHighestWomenInLeadership());
    }

    @Test
    public void testGetLowestPayGap() {
        assertEquals(null, tracker.getLowestPayGap());

        tracker.addCompany(company1);
        tracker.addCompany(company2);
        tracker.addCompany(company3);

        assertEquals(company2, tracker.getLowestPayGap());
    }
}
