/**
 * Jacob Crosby
 * Programming Project 4
 * 4/7/2026
 * This generic immutable class represents an interval with a start and end.
 *
 * @param <T> any Comparable type
 */


public class Interval<T extends Comparable<T>> {
    private final T start;
    private final T end;

    /**
     * Constructs an interval from a start and end value.
     *
     * @param start starting point of the interval
     * @param end ending point of the interval
     * @throws IllegalArgumentException if start is greater than end
     */
    public Interval(T start, T end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end cannot be null.");
        }

        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start must not be after end.");
        }

        this.start = start;
        this.end = end;
    }

    /**
     * Determines whether a value lies within the interval, inclusive.
     *
     * @param value the value to test
     * @return true if inside the interval, otherwise false
     */
    public boolean within(T value) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    /**
     * Determines whether another interval is completely within this interval.
     *
     * @param other the interval to test
     * @return true if other is a subinterval of this interval
     */
    public boolean subinterval(Interval<T> other) {
        return other.start.compareTo(this.start) >= 0
                && other.end.compareTo(this.end) <= 0;
    }

    /**
     * Determines whether another interval overlaps this interval.
     *
     * @param other the interval to test
     * @return true if the intervals overlap
     */
    public boolean overlaps(Interval<T> other) {
        return this.start.compareTo(other.end) <= 0
                && other.start.compareTo(this.end) <= 0;
    }

    /**
     * Returns the start value.
     *
     * @return start of interval
     */
    public T getStart() {
        return start;
    }

    /**
     * Returns the end value.
     *
     * @return end of interval
     */
    public T getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + start + " - " + end + "]";
    }
}
