package space.arim.time;

/**
 * Utility class for NeoTime calculations, current time, and conversions.
 * 
 * @author anandbeh
 *
 */
public final class NeoTime {
	
	/*
	 * Ensures no object can be constructed
	 */
	private NeoTime() {}
	
	/**
	 * Functions similarly to {@link System#nanoTime()}
	 * 
	 * <br><br>Identical to:<br><code>NeoTime.scaleOld(System.nanoTime())</code>
	 * 
	 * @see #scaleOld(long)
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public static long nanoTime() {
		return scaleOld(System.nanoTime());
	}
	
	/**
	 * Returns the value of the current time in neomilliseconds
	 * <br>Equivalent of old time's {@link System#currentTimeMillis() System.currentTimeMillis()}
	 * 
	 * @return  current neomilliseconds.
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis()*5L/432L - 946_684_800_000L;
	}
	
	/**
	 * Returns {@link #currentTimeMillis() NeoTime.currentTimeMillis()}
	 * 
	 * <br><br><b>Deprecated because it's an unnecessary layer</b>
	 * 
	 * @see #currentTimeMillis()
	 */
	@Deprecated
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
	 * @since NeoTime 1.0
	 */
	public static long convertNeo(long neoMilliseconds) throws ArithmeticException {
		return Math.addExact(scaleNeo(neoMilliseconds), 946_684_800_000L);
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
	 * @since NeoTime 1.0
	 */
	public static long convertOld(long oldMilliseconds) throws ArithmeticException {
		return scaleOld(Math.subtractExact(oldMilliseconds, 946_684_800_000L));
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
	 * @since NeoTime 1.0
	 * 
	 */
	public static long scaleNeo(long neoMilliseconds) throws ArithmeticException {
		return Math.multiplyExact(neoMilliseconds, 432L)/5L;
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
	 * @since NeoTime 1.0
	 * 
	 */
	public static long scaleOld(long oldMilliseconds) throws ArithmeticException {
		return Math.multiplyExact(oldMilliseconds, 5L)/432L;
	}
}
