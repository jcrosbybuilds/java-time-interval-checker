/**
 * Jacob Crosby
 * Programming Project 4
 * 4/7/2026
 * This program provides a GUI to compare two time intervals and check whether
 * a specific time is inside one or both intervals.
 */


import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class Project4 extends JFrame {
    private final JTextField interval1StartField;
    private final JTextField interval1EndField;
    private final JTextField interval2StartField;
    private final JTextField interval2EndField;
    private final JTextField timeToCheckField;
    private final JTextField resultField;

    private final JButton compareIntervalsButton;
    private final JButton checkTimeButton;

    /**
     * Constructs the GUI for the time interval checker.
     */
    public Project4() {
        setTitle("Time Interval Checker");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 3, 5, 5));

        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel("Start Time", SwingConstants.CENTER));
        inputPanel.add(new JLabel("End Time", SwingConstants.CENTER));

        inputPanel.add(new JLabel("Time Interval 1"));
        interval1StartField = new JTextField();
        interval1EndField = new JTextField();
        inputPanel.add(interval1StartField);
        inputPanel.add(interval1EndField);

        inputPanel.add(new JLabel("Time Interval 2"));
        interval2StartField = new JTextField();
        interval2EndField = new JTextField();
        inputPanel.add(interval2StartField);
        inputPanel.add(interval2EndField);

        compareIntervalsButton = new JButton("Compare Intervals");
        inputPanel.add(new JLabel(""));
        inputPanel.add(compareIntervalsButton);
        inputPanel.add(new JLabel(""));

        inputPanel.add(new JLabel("Time to Check"));
        timeToCheckField = new JTextField();
        inputPanel.add(timeToCheckField);
        inputPanel.add(new JLabel(""));

        checkTimeButton = new JButton("Check Time");

        resultField = new JTextField();
        resultField.setEditable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        bottomPanel.add(checkTimeButton);
        bottomPanel.add(resultField);

        add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        compareIntervalsButton.addActionListener(e -> compareIntervals());
        checkTimeButton.addActionListener(e -> checkTime());
    }

    /**
     * Reads the interval values from the GUI and creates interval objects.
     *
     * @return an array containing interval1 and interval2
     * @throws InvalidTime if time input is invalid
     */
    private Interval<Time>[] buildIntervals() throws InvalidTime {
        Time start1 = new Time(interval1StartField.getText());
        Time end1 = new Time(interval1EndField.getText());
        Time start2 = new Time(interval2StartField.getText());
        Time end2 = new Time(interval2EndField.getText());

        Interval<Time> interval1 = new Interval<>(start1, end1);
        Interval<Time> interval2 = new Interval<>(start2, end2);

        @SuppressWarnings("unchecked")
        Interval<Time>[] intervals = new Interval[] { interval1, interval2 };
        return intervals;
    }

    /**
     * Compares the two intervals and displays the relationship.
     */
    private void compareIntervals() {
        try {
            Interval<Time>[] intervals = buildIntervals();
            Interval<Time> interval1 = intervals[0];
            Interval<Time> interval2 = intervals[1];

            if (interval2.subinterval(interval1)) {
                resultField.setText("Interval 1 is a sub-interval of interval 2");
            } else if (interval1.subinterval(interval2)) {
                resultField.setText("Interval 2 is a sub-interval of interval 1");
            } else if (interval1.overlaps(interval2)) {
                resultField.setText("The intervals overlap");
            } else {
                resultField.setText("The intervals are disjoint");
            }
        } catch (InvalidTime e) {
            resultField.setText("Invalid time: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            resultField.setText("Invalid interval: " + e.getMessage());
        }
    }

    /**
     * Checks whether the entered time falls within one or both intervals.
     */
    private void checkTime() {
        try {
            Interval<Time>[] intervals = buildIntervals();
            Interval<Time> interval1 = intervals[0];
            Interval<Time> interval2 = intervals[1];

            Time checkTime = new Time(timeToCheckField.getText());

            boolean inInterval1 = interval1.within(checkTime);
            boolean inInterval2 = interval2.within(checkTime);

            if (inInterval1 && inInterval2) {
                resultField.setText("Both intervals contain the time " + checkTime);
            } else if (inInterval1) {
                resultField.setText("Only interval 1 contains the time " + checkTime);
            } else if (inInterval2) {
                resultField.setText("Only interval 2 contains the time " + checkTime);
            } else {
                resultField.setText("Neither interval contains the time " + checkTime);
            }
        } catch (InvalidTime e) {
            resultField.setText("Invalid time: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            resultField.setText("Invalid interval: " + e.getMessage());
        }
    }

    /**
     * Launches the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Project4 frame = new Project4();
            frame.setVisible(true);
        });
    }
}