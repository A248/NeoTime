package space.arim.time;

/**
 * Utility class for NeoTime calculations, current time, and conversions.
 * 
 * @author anandbeh
 *
 */
public final class NeoTime {
	
	private NeoTime() {}
	
	/**
	 * Equivalent of old time's {@link System#nanoTime() System.nanoTime()}
	 * 
	 * @return  current neonanoseconds
	 * @author anandbeh
	 */
	public static long nanoTime() {
		return scaleNeoFromOld(System.nanoTime() - 946684800000L);
	}
	
	/**
	 * Returns the value of the current time in neomilliseconds
	 * Equivalent of old time's {@link System#currentTimeMillis() System.currentTimeMillis()}
	 * 
	 * @return  current neomilliseconds.
	 * @author anandbeh
	 */
	public static long currentTimeMillis() {
		return scaleNeoFromOld(System.currentTimeMillis() - 946684800L);
	}
	
	/**
	 * Same as {@link #currentTimeMillis() currentTimeMillis()}
	 */
	public static long getCurrentTime() {
		return currentTimeMillis();
	}
	
	/**
	 * Converts NeoTime to old time
	 * Not to be confused with scaling.
	 * 
	 * <br><br><b>Difference between scaling and conversion</b>
	 * <br>- Scaling simply multiplies an old value by a constant. In pure scaling, 0 always maps to 0.
	 * <br>- Conversion accounts for differences in starting values. Sometimes conversion also includes scaling.
	 * 
	 * <br><br>For instance, Celsius <-> Kelvin is conversion, where:
	 * <br><code>Kelvin = Celsius + 273</code>
	 * <br>This is because Kelvin starts at absolute zero. It cannot be negative, since you can't go below absolute zero.
	 * Celsius starts at the boiling point of water. Its lowest possible value is -273.15.
	 * <br><br>Fahrenheith <-> Celsius is <i>also</i> a conversion, but it also involves scaling.
	 * To get Fahrenheit (the good-for-nothing temperature), one must
	 * first scale Celsius and THEN add 32 degrees, like so:
	 * <br><code>Fahrenheit = (9/5 * Celsius) + 32</code>
	 * <br><code>Celsius = (Fahrenheit - 32) * 5/9</code>
	 * <br><br>However, these formulas, intended for <i>temperature conversions</i>, should not be used for <i>changes in temperature</i>
	 * If the temperature increases by 5/9 degree Celsius, that is <i>NOT</i> equivalent to
	 * a 33 degree Fahrenheit increase.
	 * <br>The lesson: Use conversion for absolute numbers (e.g. temperature in the room) and scaling for relative values (e.g. change in temperature)
	 * 
	 * <br><br><b>TL;DR</b>
	 * <br>- Use scaling for timespans (10 seconds, 14 minutes, 3 years)
	 * <br>- Use conversion for dates and absolute times (1000000 NMS, 14 January 2019)
	 * 
	 * @param neomilliseconds - long to be converted
	 * @return long - old milliseconds equivalence
	 * 
	 * @author anandbeh
	 */
	public static long convertNeo(long neomilliseconds) {
		return scaleOldFromNeo(neomilliseconds) + 946684800L;
	}
	
	/**
	 * Converts old time to NeoTime
	 * 
	 * See {@link NeoTime#convertNeo(long)} for explanation on
	 * the distinction between scaling and conversion.
	 * 
	 * @param oldmilliseconds - long to be converted
	 * @return long - neomilliseconds equivalence
	 * 
	 * @author anandbeh
	 */
	public static long convertOld(long oldmilliseconds) {
		return scaleNeoFromOld(oldmilliseconds - 946684800L);
	}
	
	/**
	 * Scales, not converts, NeoTime to old time
	 * <br>See {@link NeoTime#convertNeo} for conversion
	 * 
	 * <br><br>It doesn't matter whether you specify a value in neomilliseconds
	 * or neonanoseconds, because that's how math works
	 * 
	 * @param long - the time, in neomilliseconds (or nano), you want to convert
	 * @return long - equivalent value in old time
	 * 
	 * @author anandbeh
	 * 
	 */
	public static long scaleOldFromNeo(long neomilliseconds) {
		return neomilliseconds*864L/10L;
	}
	
	/**
	 * Scales, not converts, old time to NeoTime
	 * <br>See {@link NeoTime#convertOld} for conversion
	 * 
	 * <br><br>It doesn't matter whether you specify a value in old milliseconds
	 * or old nanoseconds, because that's how math works
	 * 
	 * @param long - the time, in neomilliseconds (or nano), you want to convert
	 * @return long - equivalent value in NeoTime
	 * 
	 * @author anandbeh
	 * 
	 */
	public static long scaleNeoFromOld(long oldmilliseconds) {
		return oldmilliseconds*10L/864L;
	}
	
	/**
	 * TimeSpan enums are used for simple conversions when
	 * an amount of days other than milliseconds is already known.
	 * 
	 * A TimeSpan can represent a unit of time in either
	 * old time or NeoTime depending on the context it is used in
	 * 
	 * <br><br>Helpful methods:<br>
	 * {@link TimeSpan#neoValue()}
	 * {@link TimeSpan#oldValue()}
	 * {@link TimeSpan#equivalentOld()}
	 * {@link TimeSpan#equivalentNeo()}
	 * 
	 * @author anandbeh
	 *
	 */
	public enum TimeSpan {
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
		private TimeSpan(long neoValue, long oldValue) {
			this.neoValue = neoValue;
			this.oldValue = oldValue;
		}
		/**
		 * Supposing this TimeSpan to be in NeoTime (e.g. NeoDay or NenMinute),
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
		 * @author anandbeh
		 */
		public long neoValue() {
			return this.neoValue;
		}
		/**
		 * Supposing this TimeSpan to be in old time,
		 * this method gets the old timespan's value in old milliseconds
		 * <br>
		 * <br>e.g.:
		 * <br>DAY.oldValue() == 86400
		 * <br>SECOND.oldValue() == 1000
		 * 
		 * @return old milliseconds of the timespan
		 * @author anandbeh
		 */
		public long oldValue() {
			return this.oldValue;
		}
		/**
		 * Converts a NeoTime timespan to an old timespan
		 * <br>Relies on the fact: 1 NeoDay = 1 old day
		 * 
		 * @return an old milliseconds value
		 * @author anandbeh
		 */
		public long equivalentOld() {
			return neoValue()*DAY.oldValue()/DAY.neoValue();
		}
		/**
		 * Converts an old timespan to a NeoTime timespan
		 * Relies on the fact: 1 NeoDay = 1 old day
		 * 
		 * @return a neomilliseconds value
		 * @author anandbeh
		 */
		public long equivalentNeo() {
			return oldValue()*DAY.neoValue()/DAY.oldValue();
		}
	}
}
