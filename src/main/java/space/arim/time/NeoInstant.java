package space.arim.time;

// TODO Add documentation for NeoInstant methods
/**
 * 
 * A NeoTime replacement for {@link java.time.Instant Instant}.
 * 
 * <br><br>Represents a precise point in time
 * 
 * @see NeoDate
 * 
 * @implSpec
 * Immutable and thread-safe.
 * 
 * @author anandbeh
 * @since NeoTime 1.0
 */
public final class NeoInstant implements Comparable<NeoInstant> {

	//private static final long serialVersionUID = 3198830659677472215L;

	public static final NeoInstant EPOCH = new NeoInstant(0L, 0);
	
	private final long neoSeconds;
	private final int nanoAdjustment;
	
	private NeoInstant(long neoSeconds, int nanoAdjustment) {
		this.neoSeconds = neoSeconds;
		this.nanoAdjustment = nanoAdjustment;
	}
	
	/**
	 * Creates a new NeoInstant with given seconds
	 * 
	 * @param neoSeconds seconds since time=0
	 * @return a NeoInstant
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public static NeoInstant ofSeconds(long neoSeconds) {
		return new NeoInstant(neoSeconds, 0);
	}
	
	/**
	 * Creates a new NeoInstant with given seconds and nanoAdjustment.
	 * 
	 * The nanoAdjustment may be used when increased precision is needed.
	 * See {@link NeoInstant} for more info on a nanoAdjustment value
	 * 
	 * @param neoSeconds seconds since time=0
	 * @param nanoAdjustment for nanosecond precision
	 * @return a NeoInstant
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public static NeoInstant ofSeconds(long neoSeconds, int nanoAdjustment) {
        return new NeoInstant(Math.addExact(neoSeconds, Math.floorDiv(nanoAdjustment, 1000_000_000)), Math.floorMod(nanoAdjustment, 1000_000_000));
	}
	
	/**
	 * Creates a new NeoInstant with given milliseconds
	 * 
	 * @param neoMilliseconds milliseconds since time=0
	 * @return a NeoInstant
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public static NeoInstant ofMillis(long neoMilliseconds) {
        return new NeoInstant(Math.floorDiv(neoMilliseconds, 1000L), (int)Math.floorMod(neoMilliseconds, 1000L));
	}
	
	/**
	 * Creates a new NeoInstant with given milliseconds and nanoAdjustment.
	 * 
	 * The nanoAdjustment may be used when increased precision is needed.
	 * See {@link NeoInstant} for more info on a nanoAdjustment value
	 * 
	 * @param neoMilliseconds milliseconds since time=0
	 * @param nanoAdjustment for added nanosecond precision
	 * @return a NeoInstant
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public static NeoInstant ofMillis(long neoMilliseconds, int nanoAdjustment) {
        return new NeoInstant(Math.addExact(Math.floorDiv(neoMilliseconds, 1000L), Math.floorDiv(nanoAdjustment, 1000_000_000)), Math.addExact(Math.floorMod(nanoAdjustment, 1000_000_000), Long.valueOf(Math.floorMod(neoMilliseconds, 1000L)).intValue()));
	}
	
	/**
	 * Gets the seconds value for this NeoInstant
	 * 
	 * @return long - in neoseconds
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public long getSeconds() {
		return this.neoSeconds;
	}
	
	/**
	 * Gets the nanoAdjustment value for this NeoInstant
	 * 
	 * The nanoAdjustment may be used when increased precision is needed.
	 * See {@link NeoInstant} for more info on a nanoAdjustment value
	 * 
	 * @return int - nanoAdjustment
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public int getAdjustment() {
		return this.nanoAdjustment;
	}
	
	/**
	 * Evaluates this NeoInstant in milliseconds.
	 * 
	 * <br><br>Formula =
	 * {@link #getSeconds() getSeconds()}*1,000 + {@link #getAdjustment() getAdjustment}/1,000,000.
	 * 
	 * @return long - neomilliseconds value
	 * @throws ArithmeticException if the resulting value overflows a long.
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public long getMillis() throws ArithmeticException {
		return Math.addExact(Math.multiplyExact(getSeconds(), 1000L), Math.floorDiv(getAdjustment(), 1000_000L));
	}
	
	/**
	 * Evaluates this NeoInstant in microseconds.
	 * 
	 * <br><br>Formula =
	 * {@link #getSeconds() getSeconds()}*1,000,000 + {@link #getAdjustment() getAdjustment}/1,000.
	 * 
	 * @return long - neomicroseconds value
	 * @throws ArithmeticException if the resulting value overflows a long.
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public long getMicro() throws ArithmeticException {
		return Math.addExact(Math.multiplyExact(getSeconds(),  1000_000L), Math.floorDiv(getAdjustment(), 1000L));
	}
	
	/**
	 * Evaluates this NeoInstant in nanoseconds.
	 * 
	 * <br><br>Formula =
	 * {@link #getSeconds() getSeconds()}*1,000,000,000 + {@link #getAdjustment() getAdjustment}.
	 * 
	 * @return long - neonanoseconds value
	 * @throws ArithmeticException if the resulting value overflows a long.
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	public long getNano() throws ArithmeticException {
		return Math.addExact(Math.multiplyExact(getSeconds(), 1000_000_000L), getAdjustment());
	}

	@Override
	public int hashCode() {
        return (int) getSeconds() ^ (getAdjustment() >> 32);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof NeoInstant) {
			return this.compareTo((NeoInstant) object) == 0;
		}
		return false;
	}
	
	/**
	 * Compares this NeoInstant to another for ordering.
	 * 
	 * @return int - 1 if later than the other instant, -1 if earlier than the other instant, 0 if at the same exact time.
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	@Override
	public int compareTo(NeoInstant otherInstant) {
		return (getSeconds() < otherInstant.getSeconds()) ? -1 : (getSeconds() > otherInstant.getSeconds()) ? 1 : (getAdjustment() < otherInstant.getAdjustment()) ? -1 : (getAdjustment() > otherInstant.getAdjustment()) ? 1 : 0;
	}
	
	/**
	 * Clones this NeoInstant with the same exact amount of
	 * neoseconds and nanoAdjustment.
	 * 
	 * @return NeoInstant - an identical NeoInstant
	 * 
	 * @author anandbeh
	 * @since NeoTime 1.0
	 */
	@Override
	public NeoInstant clone() {
		return new NeoInstant(getSeconds(), getAdjustment());
	}
}
