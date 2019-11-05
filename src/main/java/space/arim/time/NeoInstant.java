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
 * @author anandbeh
 *
 */
public final class NeoInstant {

	public static final NeoInstant NEOTIME_EPOCH = new NeoInstant(0L, 0);
	
	private final long neoSeconds;
	private final int nanoAdjustment;
	
	private NeoInstant(long neoSeconds, int nanoAdjustment) {
		this.neoSeconds = neoSeconds;
		this.nanoAdjustment = nanoAdjustment;
	}
	public static NeoInstant ofSeconds(long neoSeconds) {
		return new NeoInstant(neoSeconds, 0);
	}
	
	public static NeoInstant ofSeconds(long neoSeconds, int nanoAdjustment) {
        return new NeoInstant(Math.addExact(neoSeconds, Math.floorDiv(nanoAdjustment, 1000_000_000)), Math.floorMod(nanoAdjustment, 1000_000_000));
	}
	
	public static NeoInstant ofMillis(long neoMilliseconds) {
        return new NeoInstant(Math.floorDiv(neoMilliseconds, 1000L), (int)Math.floorMod(neoMilliseconds, 1000L));
	}
	
	public static NeoInstant ofMillis(long neoMilliseconds, int nanoAdjustment) {
        return new NeoInstant(Math.addExact(Math.floorDiv(neoMilliseconds, 1000L), Math.floorDiv(nanoAdjustment, 1000_000_000)), Math.addExact(Math.floorMod(nanoAdjustment, 1000_000_000), Long.valueOf(Math.floorMod(neoMilliseconds, 1000L)).intValue()));
	}
	
	public long getSeconds() {
		return this.neoSeconds;
	}
	
	public int getAdjustment() {
		return this.nanoAdjustment;
	}
	
	public long getMillis() {
		return Math.addExact(Math.multiplyExact(getSeconds(), 1000L), Math.floorDiv(getAdjustment(), 1000_000L));
	}
}
