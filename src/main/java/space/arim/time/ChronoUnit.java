package space.arim.time;

/**
 * ChronoUnit enums are used for simple conversions when
 * an amount of days other than milliseconds is already known. <br>
 * <br>
 * A ChronoUnit can represent a unit of time in either
 * old time or NeoTime depending on the context it is used in
 * 
 * <br><br>Helpful methods:<br>
 * {@link #neoValue()}
 * {@link #oldValue()}
 * {@link #equivalentOld()}
 * {@link #equivalentNeo()}
 * 
 * @author anandbeh
 * @since NeoTime 1.0
 *
 */
public enum ChronoUnit {
	
	MILLENIUM(1000000000000L, 31536000000000L),
	CENTURY(100000000000L, 3153600000000L),
	DECADE(10000000000L, 315360000000L),
	YEAR(1000000000L, 31536000000L),
	MONTH(100000000L, 2592000000L),
	WEEK(10000000L, 604800000L),
	DAY(1000000L, 86400000L),
	HOUR(100000L, 3600000L),
	MINUTE(10000L, 60000L),
	SECOND(1000L, 1000L),
	MILLISECOND(1L, 1L);
	
	private final long neoValue;
	private final long oldValue;
	
	private ChronoUnit(long neoValue, long oldValue) {
		this.neoValue = neoValue;
		this.oldValue = oldValue;
	}
	
	/**
	 * Supposing this ChronoUnit to be in NeoTime (e.g. neo day or neo minute),
	 * this method gets the timespan's value in neomilliseconds.
	 * 
	 * <br>Since NeoTime is simply scaled by factors of ten,
	 * you could just do simple math.
	 * <br>
	 * <br>e.g.:
	 * <br>DAY.neoValue() == 1000000 == 10^6
	 * <br>MINUTE.neoValue() == 10000 == 10^4
	 * <br>SECOND.neoValue() == 1000 == 10^3
	 * 
	 * @return neomilliseconds of the timespan
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public long neoValue() {
		return this.neoValue;
	}
	
	/**
	 * Supposing this ChronoUnit to be in old time,
	 * this method gets the old timespan's value in old milliseconds
	 * <br>
	 * <br>e.g.:
	 * <br>DAY.oldValue() == 86400
	 * <br>SECOND.oldValue() == 1000
	 * 
	 * @return old milliseconds of the timespan
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public long oldValue() {
		return this.oldValue;
	}
	
	/**
	 * Converts a NeoTime timespan to an old timespan
	 * <br>Relies on the fact: 1 NeoDay = 1 old day
	 * 
	 * @return an old milliseconds value
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 * 
	 */
	public long equivalentOld() {
		return NeoTime.scaleNeo(neoValue());
	}
	
	/**
	 * Converts an old timespan to a NeoTime timespan
	 * <br>Relies on the fact: 1 NeoDay = 1 old day
	 * 
	 * @return a neomilliseconds value
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 * 
	 */
	public long equivalentNeo() {
		return NeoTime.scaleOld(oldValue());
	}
	
}
