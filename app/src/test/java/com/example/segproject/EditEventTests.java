import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.os.Build;

import com.example.segproject.ClubDBHandler;
import com.example.segproject.EditEvent;
import com.example.segproject.EventDBHandler;
import com.example.segproject.EventTypeDBHandler;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})  // Specify an Android API level to use
public class EditEventTests {

    private EditEvent editEventActivity;
    private EventTypeDBHandler mockEventTypeDBHandler;
    private ClubDBHandler mockClubDBHandler;
    private EventDBHandler mockEventDBHandler;

    @Before
    public void setUp() {
        editEventActivity = new EditEvent();
        mockEventTypeDBHandler = mock(EventTypeDBHandler.class);
        mockClubDBHandler = mock(ClubDBHandler.class);
        mockEventDBHandler = mock(EventDBHandler.class);
        editEventActivity.db = mockEventTypeDBHandler;
        editEventActivity.cdb = mockClubDBHandler;
        editEventActivity.edb = mockEventDBHandler;
    }

    @Test
    public void testValidDate() {
        // Test case for valid date
        assertTrue(EditEvent.validDate(2023, 5, 15));
    }

    @Test
    public void testInvalidYear() {
        // Test case for invalid date
        assertFalse(EditEvent.validDate(1999, 12, 12));
    }

    @Test
    public void testInvalidDateDay() {
        // Test case for invalid date
        assertFalse(EditEvent.validDate(2022, 12, 70));
    }



    @Test
    public void testInvalidDateMonth() {
        // Test case for invalid date
        assertFalse(EditEvent.validDate(199, 15, 1));
    }


}
