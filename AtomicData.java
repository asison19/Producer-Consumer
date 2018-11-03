import java.util.concurrent.atomic.AtomicInteger;

public class AtomicData extends Data{
	AtomicInteger  value;
	public AtomicData(int val) {
		super(val);
		value = new AtomicInteger(val);
	}
}
