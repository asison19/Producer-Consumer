/*
 * @author Andrew Sison
 */
public class ProducerConsumer {
	
	static final int BUFFER_MAX = 10;
	static final int PRODUCE_AMOUNT = 100;
	
	public static void main(String[] args) {
		
		//locks(5, 2);
		//locks(2, 5);
		//isolated(5, 2);
		//isolated(2, 5);
		atomic(5, 2);
		atomic(2, 5);

	}
	
	private static void boundedBuffer(int producerAmount, int consumerAmount, Buffer buffer, String str) {
		
		long startTime = System.nanoTime();
		
		Producer[] producers = new Producer[producerAmount];
		Consumer[] consumers = new Consumer[consumerAmount];
		
		
		for(int i = 0; i < producers.length; i++) {
			producers[i] = new Producer(buffer, PRODUCE_AMOUNT);
			producers[i].start();
		}
		
		for(int i = 0; i < consumers.length; i++) {
			consumers[i] = new Consumer(buffer, BUFFER_MAX);
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
		System.out.println("\nUsing " + str + " and " + producerAmount + " producers and " + consumerAmount + " consumers it took, " + totalTime + " seconds.") ;
	}
	
	private static void locks(int producerAmount, int consumerAmount) {
		Buffer1 buffer = new Buffer1(BUFFER_MAX, producerAmount, PRODUCE_AMOUNT);
		boundedBuffer(producerAmount, consumerAmount, buffer, "Locks");
	}
	
	private static void isolated(int producerAmount, int consumerAmount) {
		Buffer2 buffer = new Buffer2(BUFFER_MAX, producerAmount, PRODUCE_AMOUNT);
		boundedBuffer(producerAmount, consumerAmount, buffer, "Isolated Sections");
	}
	
	private static void atomic(int producerAmount, int consumerAmount) {
		Buffer3 buffer = new Buffer3(BUFFER_MAX, producerAmount, PRODUCE_AMOUNT);
		boundedBuffer(producerAmount, consumerAmount, buffer, "Atomic Variables");
	}

}
