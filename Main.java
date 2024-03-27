import java.util.List;

//Calendar Task
//Schedule a meeting of a certain duration with a co-worker. You have access to your calendar and your co-worker's calendar (both of which contain your respective meetings for the day, in the form of `[startTime, endTime]` ), as well as both of your daily bounds (i.e., the earliest and latest times at which you're available for meetings every day, in the form of `[earliestTime, latestTime]` ).
//Write a method that takes in your calendar, your daily bounds, your co-worker's calendar, your coworker's daily bounds, and the duration of the meeting that you want to schedule, and that returns a list of all the time blocks (in the form of `[startTime, endTime]` ) during which you could schedule the meeting, ordered from earliest time block to latest.
//Note that times will be given and should be returned in military time. For example: 8:30 , 9:01 , and 23:56

// **Sample Input**
//`calendar1 = [['9:00', '10:30'], ['12:00', '13:00'], ['16:00', '18:00']]`
//`dailyBounds1 = ['9:00', '20:00']`
//`calendar2 = [['10:00', '11:30'], ['12:30', '14:30'], ['14:30', '15:00'], ['16:00', '17:00']]`
//`dailyBounds2 = ['10:00', '18:30']`
//`meetingDuration = 30`
public class Main {
    public static void main(String[] args) {
        // Sample Input
        String[][] calendar1 = {{"9:00", "10:30"}, {"12:00", "13:00"}, {"16:00", "18:00"}};
        String[] dailyBounds1 = {"9:00", "20:00"};
        String[][] calendar2 = {{"10:00", "11:30"}, {"12:30", "14:30"}, {"14:30", "15:00"}, {"16:00", "17:00"}};
        String[] dailyBounds2 = {"10:00", "18:30"};
        int meetingDuration = 30;

        // Schedule meeting
        List<String[]> availableSlots = MeetingScheduler.scheduleMeeting(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration);

        // Print available slots
        for (String[] slot : availableSlots) {
            System.out.println("[" + slot[0] + ", " + slot[1] + "]");
        }
    }
}