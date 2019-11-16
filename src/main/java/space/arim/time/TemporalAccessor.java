package space.arim.time;

/**
 * Interface to represent a <b>timespan</b> to which other timespans may be added
 * 
 * <br><br>Much simpler than the <code>java.time.temporal</code> package because
 * NeoTime is so much more efficient.
 * 
 * @author anandbeh
 * @since NeoTime 1.0
 */
public interface TemporalAccessor {
	
	/**
	 * Adds the specified ChronoUnit to this TemporalAccessor
	 * <br>To subtract values specify a negative <code>long</code> argument
	 * 
	 * @param neoUnit a NeoTime timespan
	 * 
	 * @see ChronoUnit
	 */
	void add(ChronoUnit neoUnit, long amount);
	
	/**
	 * Converts this TemporalAccessor to a NeoDate
	 * 
	 * <br><br>Equivalent to TemporalAccessor.toDate(NeoDate.EPOCH)
	 * 
	 * @return a NeoDate
	 * 
	 * @see #toDate(NeoDate)
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	
	default NeoDate toDate() {
		return toDate(NeoDate.EPOCH);
	}
	
	/**
	 * Converts this TemporalAccessor to a specific date
	 * Takes a starting date and calculates a consequent date
	 * 
	 * @param startTime - the initial date from which to calculate
	 * @return a NeoDate
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	NeoDate toDate(NeoDate startTime);
	
	/**
	 * Converts this TemporalAccessor to a NeoInstant
	 * 
	 * <br><br>Equivalent to TemporalAccessor.toInstant(NeoInstant.EPOCH)
	 * 
	 * @return a NeoInstant
	 * 
	 * @see #toInstant(NeoInstant)
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	default NeoInstant toInstant() {
		return toInstant(NeoInstant.EPOCH);
	}
	
	/**
	 * Similar to {@link #toDate(NeoDate), but takes a NeoInstant rather
	 * than a NeoDate.
	 * 
	 * @param startTime - the initial date from which to calculate
	 * @return a NeoInstant
	 * 
	 * @see NeoInstant
	 */
	NeoInstant toInstant(NeoInstant startTime);
}
