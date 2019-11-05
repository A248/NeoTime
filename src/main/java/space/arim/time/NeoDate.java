package space.arim.time;

import java.io.Serializable;

/**
 * 
 * A NeoTime replacement for {@link java.util.Date Date}
 * 
 * Represents an slice in time. For better precision, use {@link space.arim.time.NeoInstant NeoInstant}
 * 
 * @author anandbeh
 * @since NeoTime 1.0
 * 
 */
public class NeoDate implements Serializable, Cloneable, Comparable<NeoDate> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2287011378139968722L;
	private long milliseconds;
	/** Initialises a <code>NeoDate</code> object with the current time
	 * measured to the nearest neomillisecond.
	 * 
	 * @see NeoTime#currentTimeMillis()
	 * 
	 * @author anandbeh
     */
	public NeoDate() {
		this(NeoTime.currentTimeMillis());
	}
    /**
     * Initialises a <code>NeoDate</code> object
     * with its milliseconds value set as specified
     *
     * @param   milliseconds   the neomilliseconds since January 1, 2000
     * 
     * @author anandbeh
     */
	public NeoDate(long milliseconds) {
		this.milliseconds = milliseconds;
	}
	/**
	 * Clones this NeoDate with the same exact amount of milliseconds.
	 * 
	 * @return NeoDate - an identical NeoDate
	 * 
	 * @author anandbeh
	 */
	public NeoDate clone() {
		return new NeoDate(getTime());
	}
	/**
	 * Gets the <code>long</code> value corresponding to this date.
	 * (Similar to {@link java.util.Date#getTime Date.getTime})
	 * 
	 * @return milliseconds of this date
	 * @see NeoTime#currentTimeMillis()
	 * 
	 * @author anandbeh
	 */
	public long getTime() {
		return this.milliseconds;
	}
	public void setTime(long time) {
		this.milliseconds = time;
	}
	/**
	 * Checks if this date is earlier in time.
	 * (Similar to {@link java.util.Date#before Data.before})
	 * 
	 * @return true if this date is before the specified date.
	 * 
	 * @author anandbeh
	 */
	public boolean before(NeoDate when) {
		return this.milliseconds < when.getTime();
	}
	/**
	 * Checks if this date is later in time.
	 * (Similar to {@link java.util.Date#after Date.after})
	 * 
	 * @return true if this date is after the specified date.
	 * 
	 * @author anandbeh
	 */
	public boolean after(NeoDate when) {
		return this.milliseconds > when.getTime();
	}
	/**
	 * Compares this date to another for equality.
	 * (Similar to {@link java.util.Date#equals Date.equals})
	 * 
	 * @return true if their {@link #getTime() getTime()} methods return the same value.
	 * 
	 * @author anandbeh
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof NeoDate) {
			return ((NeoDate) object).getTime() == getTime();
		}
		return false;
	}
	/**
	 * Compares this date to another for ordering.
	 * (Similar to {@link java.util.Date#compareTo Date.compareTo})
	 * 
	 * Evaluation uses {@link #getTime() getTime()}
	 * 
	 * @return 0 if dates are equal, -1 is this date is less than specified date,
	 * and 1 if this date is greater than specified date.
	 * 
	 * @author anandbeh
	 */
	@Override
	public int compareTo(NeoDate anotherDate) {
		
		long thisTime = getTime();
		long otherTime = anotherDate.getTime();
		return (thisTime<otherTime ? -1 : (thisTime==otherTime ? 0 : 1));
	}
	/**
	 * Returns a hash code value for this object.
	 * (Similar to {@link java.util.Date#hashCode Date.hashCode})
	 * 
	 * @author anandbeh
	 */
	@Override
	public int hashCode() {
        long ht = this.getTime();
        return (int) ht ^ (int) (ht >> 32);
	}
	/**
	 * Converts this date to a String.
	 * 
	 * This method differs remarkably from {@link java.util.Date#toString Date.toString} in that
	 * this simply returns the string value of {@link NeoDate#getTime() NeoDate.getTime()},
	 * rather than formatting this date.
	 * 
	 * To format a date use {@link space.arim.time.NeoDateFormatter#format NeoDateFormatter.format}
	 * 
	 * @return a string identical to Long.toString(this.getTime())
	 * 
	 * @author anandbeh
	 */
	@Override
	public String toString() {
		return Long.toString(getTime());
	}
	
	/**
	 * Convert NeoInstant to NeoDate
	 * 
	 * @param instant - a NeoInstant
	 * @return a NeoDate based on the specified NeoInstant.
	 * @see NeoInstant
	 * 
	 * @author anandbeh
	 */
	public static NeoDate from(NeoInstant instant) {
		return new NeoDate(instant.getMillis());
	}
	/**
	 * Convert to NeoInstant
	 * 
	 * @return a NeoInstant based on this NeoDate's getTime() value
	 * @see NeoInstant
	 * 
	 * @author anandbeh
	 */
	public NeoInstant toInstant() {
		return NeoInstant.ofMillis(getTime());
	}
}
