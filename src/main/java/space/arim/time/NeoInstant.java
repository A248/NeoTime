package space.arim.time;

import space.arim.time.NeoTime.TimeSpan;

/**
 * 
 * A NeoTime replacement for {@link java.time.Instant Instant}
 * 
 * Represents a precise point in time
 * 
 * @see NeoDate
 * @author anandbeh
 *
 */
public class NeoInstant {
	
	/**
	 * Represents the greatest nanoseconds value a NeoInstant can hold.
	 * Equivalent to {@link Long#MAX_VALUE}, or {@value Long#MAX_VALUE}
	 * 
	 * To store values farther in time than 9,223,372 nanoyears,
	 * use {@link NeoInstant(long, int)}
	 * 
	 * @author anandbeh
	 */
	public static final long MAX_NANO = Long.MAX_VALUE;
	/**
	 * Represents the smallest nanoseconds value a NeoInstant can hold.
	 * Equivalent to {@link Long#MIN_VALUE}, or {@value Long#MIN_VALUE}
	 * 
	 * To store values less in time than -9,223,372 nanoyears,
	 * use {@link NeoInstant(long, int)}
	 * 
	 * @author anandbeh
	 */
	public static final long MIN_NANO = Long.MIN_VALUE;
	/**
	 * The greatest anchor value of any NeoInstant.
	 * Equivalent to {@link Integer#MAX_VALUE}, or {@value Integer#MAX_VALUE}
	 * 
	 * See {@link NeoInstant(long, int)} for more info
	 * on how to use anchor values.
	 * 
	 * @author anandbeh
	 */
	public static final int MAX_ANCHOR = Integer.MAX_VALUE;
	/**
	 * The smallest anchor value of any NeoInstant.
	 * Equivalent to {@link Integer#MIN_VALUE}, or {@value Integer#MIN_VALUE}
	 * 
	 * See {@link NeoInstant(long, int)} for more info
	 * on how to use anchor values.
	 * 
	 * @author anandbeh
	 */
	public static final int MIN_ANCHOR = Integer.MIN_VALUE;
	private final int anchor;
	private final long nano;
	
	public NeoInstant(long nanoseconds) {
		this.nano = nanoseconds;
		this.anchor = 0;
	}
	
	/**
	 * Creates a NeoInstant from a specified
	 * neonanoseconds and anchor value.
	 * 
	 * <br><br>Without the anchor value, the nanoseconds specified are limited
	 * to the range of a <code>long</code>. Thus, in order to store
	 * values beyond {@link #MAX_NANO} or below {@link #MIN_NANO},
	 * the anchor value may be used.
	 * 
	 * <br><br>The anchor value is an int representing a millenium of NeoTime. A millenium is 1000 years / a trillion neomilliseconds / a quadrillion neonanoseconds.
	 * <br>Accordingly, the formula for the true time of a NeoInstant in nanoseconds is:
	 * <br><code>Time == {@link #anchor() anchor_value}*10^15 + {@link #value() nano_value}</code>
	 * <br>This formula is provided in the {@link NeoInstant#value()} method
	 * 
	 * <br><br>However, if you were to misuse that formula, you might receive an
	 * {@link ArithmeticException} if your long experienced numeric overflow. Therefore, the anchor value should be used to store the value of
	 * extra millenium in your NeoInstant, but computing the true value of
	 * your NeoInstant is risky unless you are careful.
	 * 
	 * Note, in the extreme, that the max value for the true total nanoseconds of
	 * a NeoInstant according to the given formula is <code>(2^32-1)*10^15 + 2^63 - 1</code>
	 * 
	 * @param nanoseconds - amount of neonanoseconds
	 * @param anchor - value used for specifying greater time ranges
	 * 
	 * @author anandbeh
	 */
	public NeoInstant(long nanoseconds, int anchor) {
		this.nano = nanoseconds;
		this.anchor = anchor;
	}
	
	/**
	 * Gets a <code>long</code> equal to
	 * the neonanoseconds for this NeoInstant.
	 * 
	 * @return  the nanoseconds value
	 * 
	 * @author anandbeh
	 */
	public long nano() {
		return this.nano;
	}
	
	/**
	 * Gets the total milliseconds value of this NeoInstant,
	 * specifying whether to truncate out-of-range values
	 * 
	 * @param truncate whether to cut off excess values (prevents ArithmeticException)
	 * @return long - the milliseconds equivalence of this NeoInstant
	 * @throws ArithmeticException if truncate is false && nanoseconds and anchor values are big enough to cause numeric overflow
	 * 
	 * @author anandbeh
	 */
	public long millis(boolean truncate) throws ArithmeticException {
		return (anchor() == 0) ? nano()/1000L : nano()/1000L + anchor()*TimeSpan.MILLISECOND.equivalentNeo();
	}
	
	/**
	 * Gets the total milliseconds value of this NeoInstant, 
	 * truncating excess values to prevent numeric overflow.
	 * 
	 * Equivalent to {@link #millis(boolean) millis(true)}
	 * 
	 * @return long - a neomilliseconds value
	 * @see #millis(boolean)
	 * 
	 * @author anandbeh
	 */
	public long millis() {
		return millis(true);
	}
	
	/**
	 * Gets a <code>long</code> representing the 
	 * true total neonanoseconds captured by this NeoInstant.
	 * 
	 * May throw an exception if you are using anchors.
	 * 
	 * @return  the nanoseconds value
	 * @throws  ArithmeticException if numeric overflow is experienced.
	 * @see #NeoInstant(long, int)
	 * 
	 * @author anandbeh
	 */
	public long value() throws ArithmeticException {
		return (anchor() == 0) ? nano() : nano() + anchor()*TimeSpan.MILLENIUM.equivalentNeo()*1000L;
	}
	
	/**
	 * Gets an <code>int</code> representing the
	 * anchor set for this NeoInstant
	 * 
	 * @return  the anchor
	 * @see {@link NeoInstant(long, int)}
	 * 
	 * @author anandbeh
	 */
	public int anchor() {
		return this.anchor;
	}
}
