
public class ProducerConsumer {
	
	static final int BUFFER_MAX = 10;
	static final int PRODUCE_AMOUNT = 10;
	
	public static void main(String[] args) {
		Buffer1 buffer1 = new Buffer1(BUFFER_MAX, 5, PRODUCE_AMOUNT);
		Producer[] producer1 = new Producer[5];
		Consumer[] consumer1 = new Consumer[2];
		
		for(Producer p : producer1) {
			p = new Producer(buffer1, PRODUCE_AMOUNT);
			p.start();
		}
		for(Consumer c : consumer1) {
			c = new Consumer(buffer1, BUFFER_MAX);
			c.start();
		}
	}
}
