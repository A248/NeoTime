package space.arim.time;

/**
 * 
 * A NeoTime replacement for {@link java.util.Date Date}
 * 
 * Represents an instance in time.
 * 
 * @author anandbeh
 *
 * 
 */
public class NeoDate {
	private long milliseconds;
	/** Initialises a <code>NeoDate</code> object with the current time
	 * measured to the nearest neomillisecond.
	 * 
	 * @see NeoTime#currentTimeMillis()
     */
	public NeoDate() {
		this(NeoTime.currentTimeMillis());
	}
    /**
     * Initialises a <code>NeoDate</code> object with its milliseconds value set to
     * January 1, 2000 at 00:00:00 UTC
     *
     * @param   milliseconds   the milliseconds since January 1, 2000
     * @see     NeoTime#currentTimeMillis()
     */
	public NeoDate(long milliseconds) {
		this.milliseconds = milliseconds;
	}
	public NeoDate clone() {
		return new NeoDate(this.milliseconds);
	}
	/**
	 * Gets the <code>long</code> value corresponding to this date.
	 * (Similar to {@link Date#getTime})
	 * 
	 * @return milliseconds of this date
	 * @see NeoTime#currentTimeMillis()
	 */
	public long getTime() {
		return this.milliseconds;
	}
	public void setTime(long time) {
		this.milliseconds = time;
	}
	/**
	 * Checks if this date is earlier in time.
	 * (Similar to {@link Date#before})
	 * 
	 * @return true if this date is before the specified date.
	 */
	public boolean before(NeoDate when) {
		return this.milliseconds < when.getTime();
	}
	/**
	 * Checks if this date is later in time.
	 * (Similar to {@link Date#after})
	 * 
	 * @return true if this date is after the specified date.
	 */
	public boolean after(NeoDate when) {
		return this.milliseconds > when.getTime();
	}
	/**
	 * Compares this date to another for equality.
	 * (Similar to {@link Date#equals})
	 * 
	 * @return true if their {@link #getTime() getTime()} methods return the same value.
	 */
	public boolean equals(Object object) {
		if (object instanceof NeoDate) {
			return ((NeoDate) object).getTime() == getTime();
		}
		return false;
	}
	/**
	 * Compares this date to another for ordering.
	 * (Similar to {@link Date#compareTo})
	 * 
	 * Evaluation uses {@link NeoDate#getTime() getTime()}
	 * 
	 * @return 0 if dates are equal, -1 is this date is less than specified date,
	 * and 1 if this date is greater than specified date.
	 */
	public int compareTo(NeoDate anotherDate) {
		
		long thisTime = getTime();
		long otherTime = anotherDate.getTime();
		return (thisTime<otherTime ? -1 : (thisTime==otherTime ? 0 : 1));
	}
	/**
	 * Returns a hash code value for this object.
	 * (Similar to {@link Date#hashCode})
	 */
	public int hashCode() {
        long ht = this.getTime();
        return (int) ht ^ (int) (ht >> 32);
	}
	/**
	 * Converts this date to a String.
	 * 
	 * This method differs remarkably from {@link Date#toString} in that
	 * this simply returns the string value of {@link NeoDate#getTime()},
	 * rather than formatting this date.
	 * 
	 * To format a date use {@link NeoDateFormatter#format}
	 * 
	 * @return a string identical to String.valueOf(this.getTime())
	 */
	public String toString() {
		return Long.toString(getTime());
	}
	
	/**
	 * Convert NeoInstant to NeoDate
	 * 
	 * @param instant - a NeoInstant
	 * @return a NeoDate based on the specified NeoInstant.
	 * @see NeoInstant
	 */
	public static NeoDate from(NeoInstant instant) {
		return new NeoDate(instant.value());
	}
	/**
	 * Convert to NeoInstant
	 * 
	 * @return a NeoInstant based on this NeoDate's getTime() value
	 * @see NeoInstant
	 */
	public NeoInstant toInstant() {
		return new NeoInstant(getTime());
	}
}
