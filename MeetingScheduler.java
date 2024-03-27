import java.util.ArrayList;
import java.util.List;

public class MeetingScheduler {

    public static List<String[]> scheduleMeeting(String[][] calendar1, String[] dailyBounds1,
                                                 String[][] calendar2, String[] dailyBounds2,
                                                 int meetingDuration) {
        List<String[]> mergedCalendar = mergeCalendars(calendar1, calendar2, dailyBounds1, dailyBounds2);
        return findAvailableTimeSlots(mergedCalendar, meetingDuration);
    }

    private static List<String[]> mergeCalendars(String[][] calendar1, String[][] calendar2,
                                                 String[] dailyBounds1, String[] dailyBounds2) {
        List<String[]> mergedCalendar = new ArrayList<>();
        int i = 0, j = 0;

        // Parse daily bounds
        String earliestTime = dailyBounds1[0].compareTo(dailyBounds2[0]) < 0 ? dailyBounds1[0] : dailyBounds2[0];
        String latestTime = dailyBounds1[1].compareTo(dailyBounds2[1]) > 0 ? dailyBounds1[1] : dailyBounds2[1];

        while (i < calendar1.length && j < calendar2.length) {
            String[] event1 = calendar1[i];
            String[] event2 = calendar2[j];

            // Parse start and end times of events
            String startTime1 = event1[0], endTime1 = event1[1];
            String startTime2 = event2[0], endTime2 = event2[1];

            // Check which event starts first
            if (startTime1.compareTo(startTime2) < 0) {
                mergedCalendar.add(new String[]{startTime1, endTime1});
                i++;
            } else {
                mergedCalendar.add(new String[]{startTime2, endTime2});
                j++;
            }
        }

        // Add remaining events from calendar1
        while (i < calendar1.length) {
            mergedCalendar.add(calendar1[i]);
            i++;
        }

        // Add remaining events from calendar2
        while (j < calendar2.length) {
            mergedCalendar.add(calendar2[j]);
            j++;
        }

        // Trim calendar based on daily bounds
        if (mergedCalendar.size() > 0) {
            if (mergedCalendar.get(0)[0].compareTo(earliestTime) < 0) {
                mergedCalendar.get(0)[0] = earliestTime;
            }
            if (mergedCalendar.get(mergedCalendar.size() - 1)[1].compareTo(latestTime) > 0) {
                mergedCalendar.get(mergedCalendar.size() - 1)[1] = latestTime;
            }
        }

        return mergedCalendar;
    }

    private static List<String[]> findAvailableTimeSlots(List<String[]> calendar, int meetingDuration) {
        List<String[]> availableSlots = new ArrayList<>();
        String lastEndTime = calendar.get(0)[1];

        for (int i = 1; i < calendar.size(); i++) {
            String[] currentEvent = calendar.get(i);
            String currentStartTime = currentEvent[0];

            // Calculate available time between events
            int gap = timeDifference(lastEndTime, currentStartTime);
            if (gap >= meetingDuration) {
                availableSlots.add(new String[]{lastEndTime, currentStartTime});
            }

            // Update last end time
            lastEndTime = currentEvent[1];
        }

        return availableSlots;
    }

    private static int timeDifference(String startTime, String endTime) {
        int start = timeToMinutes(startTime);
        int end = timeToMinutes(endTime);
        return end - start;
    }

    private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }


}
