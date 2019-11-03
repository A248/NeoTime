package space.arim.time;

public class NeoInstant {
	
	/**
	 * Represents the greatest nanoseconds value a NeoInstant can hold.
	 * Equivalent to {@link Long#MAX_VALUE}, or {@value Long#MAX_VALUE}
	 * 
	 * To store values farther in time than 9,223,372 nanoyears,
	 * use {@link NeoInstant(long, int)}
	 */
	public static final long MAX_NANO = Long.MAX_VALUE;
	/**
	 * Represents the smallest nanoseconds value a NeoInstant can hold.
	 * Equivalent to {@link Long#MIN_VALUE}, or {@value Long#MIN_VALUE}
	 * 
	 * To store values less in time than -9,223,372 nanoyears,
	 * use {@link NeoInstant(long, int)}
	 */
	public static final long MIN_NANO = Long.MIN_VALUE;
	/**
	 * The greatest anchor value of any NeoInstant.
	 * Equivalent to {@link Integer#MAX_VALUE}, or {@value Integer#MAX_VALUE}
	 * 
	 * See {@link NeoInstant(long, int)} for more info
	 * on how to use anchor values.
	 */
	public static final int MAX_ANCHOR = Integer.MAX_VALUE;
	/**
	 * The smallest anchor value of any NeoInstant.
	 * Equivalent to {@link Integer#MIN_VALUE}, or {@value Integer#MIN_VALUE}
	 * 
	 * See {@link NeoInstant(long, int)} for more info
	 * on how to use anchor values.
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
	 * nanoneoseconds and anchor value.
	 * 
	 * 
	 * 
	 * @param nanoseconds - amount of neonanoseconds
	 * @param anchor - value used for specifying greater time ranges
	 */
	public NeoInstant(long nanoseconds, int anchor) {
		this.nano = nanoseconds;
		this.anchor = anchor;
	}
	
	/**
	 * Gets a <code>long</code> representing the 
	 * neonanoseconds captured by this NeoInstant.
	 * 
	 * @return  the nanoseconds value
	 */
	public long value() {
		return this.nano;
	}
	
	/**
	 * Gets an <code>int</code> representing the
	 * anchor set for this NeoInstant
	 * 
	 * @return  the anchor
	 * @see {@link NeoInstant(long, int)}
	 */
	public int anchor() {
		return anchor;
	}
}
