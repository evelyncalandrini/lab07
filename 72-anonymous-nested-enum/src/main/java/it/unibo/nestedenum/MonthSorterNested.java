package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;
        
        Month(final int days) {
            this.days = days;
        }
        
        public int getDays(){
            return days;
        }

        public static Month fromString(final String monthStr) {
            Objects.requireNonNull(monthStr, "Month string cannot be null");
            String searchName = monthStr.trim().toUpperCase(Locale.ROOT);
            Month found = null;
            for (Month m: Month.values()) {
                if (m.name().startsWith(searchName)) {
                    if(found != null) {
                        throw new IllegalArgumentException("Ambiguous month string: " + monthStr);
                    }
                    found= m;
                }
            }
            if(found == null) {
                throw new IllegalArgumentException("No month matches the given string: " + monthStr);
            }
            return found;
        }
    }
    private class sortByDays implements Comparator<String> {
        public int compare(final String month1, final String month2) {
            Month m1 = Month.fromString(month1);
            Month m2 = Month.fromString(month2);

            return Integer.compare(m1.getDays(), m2.getDays());
        }
    }
    private class sortByOrder implements Comparator<String> {
        public int compare(final String month1, final String month2) {
            Month m1 = Month.fromString(month1);
            Month m2 = Month.fromString(month2);

            return m1.compareTo(m2);
        }

    }
    @Override
    public Comparator<String> sortByDays() {
        return new sortByDays();        
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new sortByOrder();
    }
}
