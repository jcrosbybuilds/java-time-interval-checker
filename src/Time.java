/**
 * Jacob Crosby
 * Programming Project 4
 * 4/7/2026
 * This class represents an immutable time using hours, minutes, and meridian.
 */


public class Time implements Comparable<Time> {
    private final int hours;
    private final int minutes;
    private final String meridian;

    /**
     * Constructs a Time object from hour, minute, and meridian.
     *
     * @param hours the hour portion (1-12)
     * @param minutes the minute portion (0-59)
     * @param meridian AM or PM
     * @throws InvalidTime if any input is invalid
     */
    public Time(int hours, int minutes, String meridian) throws InvalidTime {
        validate(hours, minutes, meridian);
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian.toUpperCase();
    }

    /**
     * Constructs a Time object from a string in the format HH:MM AM.
     *
     * @param timeString string representation of time
     * @throws InvalidTime if the string format or values are invalid
     */
    public Time(String timeString) throws InvalidTime {
        if (timeString == null) {
            throw new InvalidTime("Time cannot be null.");
        }

        String trimmed = timeString.trim();
        String[] parts = trimmed.split("\\s+");

        if (parts.length != 2) {
            throw new InvalidTime("Time must be in the format HH:MM AM.");
        }

        String timePart = parts[0];
        String meridianPart = parts[1].toUpperCase();

        String[] hm = timePart.split(":");
        if (hm.length != 2) {
            throw new InvalidTime("Time must be in the format HH:MM AM.");
        }

        int parsedHours;
        int parsedMinutes;

        try {
            parsedHours = Integer.parseInt(hm[0]);
            parsedMinutes = Integer.parseInt(hm[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTime("Hours and minutes must be numeric.");
        }

        validate(parsedHours, parsedMinutes, meridianPart);

        this.hours = parsedHours;
        this.minutes = parsedMinutes;
        this.meridian = meridianPart;
    }

    /**
     * Validates hour, minute, and meridian.
     *
     * @param hours hour value
     * @param minutes minute value
     * @param meridian AM or PM
     * @throws InvalidTime if invalid
     */
    private void validate(int hours, int minutes, String meridian) throws InvalidTime {
        if (hours < 1 || hours > 12) {
            throw new InvalidTime("Hours must be between 1 and 12.");
        }

        if (minutes < 0 || minutes > 59) {
            throw new InvalidTime("Minutes must be between 0 and 59.");
        }

        if (meridian == null || !(meridian.equalsIgnoreCase("AM") || meridian.equalsIgnoreCase("PM"))) {
            throw new InvalidTime("Meridian must be AM or PM.");
        }
    }

    /**
     * Converts the time to total minutes after midnight for comparison.
     *
     * @return total minutes after midnight
     */
    private int toMinutes() {
        int militaryHour = hours % 12; // 12 becomes 0 for conversion
        if (meridian.equals("PM")) {
            militaryHour += 12;
        }
        return militaryHour * 60 + minutes;
    }

    /**
     * Compares this Time to another Time.
     *
     * @param other the other Time object
     * @return negative, zero, or positive according to Comparable contract
     */
    @Override
    public int compareTo(Time other) {
        return Integer.compare(this.toMinutes(), other.toMinutes());
    }

    /**
     * Returns the string form HH:MM AM.
     *
     * @return formatted time string
     */
    @Override
    public String toString() {
        return String.format("%d:%02d %s", hours, minutes, meridian);
    }
}
