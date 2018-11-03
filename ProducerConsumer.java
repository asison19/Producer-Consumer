/*
 * @author Andrew Sison
 */
public class ProducerConsumer {
	
	static final int BUFFER_MAX = 10;
	static final int PRODUCE_AMOUNT = 100;
	
	public static void main(String[] args) {
		
		locks_5p_2c();
	}
	
	private static void locks_5p_2c() {
		long startTime = System.nanoTime();
		Buffer1 buffer1 = new Buffer1(BUFFER_MAX, 5, PRODUCE_AMOUNT);
		Producer[] producers = new Producer[5];
		Consumer[] consumers = new Consumer[2];
		
		
		for(int i = 0; i < producers.length; i++) {
			producers[i] = new Producer(buffer1, PRODUCE_AMOUNT);
			producers[i].start();
		}
		
		for(int i = 0; i < consumers.length; i++) {
			consumers[i] = new Consumer(buffer1, BUFFER_MAX);
			consumers[i].start();
		}
		for(Consumer c : consumers) {
			try {
				c.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		double totalTime = ( (double) System.nanoTime() - startTime) / 1000000000.0;
		System.out.println("Using locks and 5 producers and 2 consumers it took, " + totalTime + " seconds.") ;
	}
}
