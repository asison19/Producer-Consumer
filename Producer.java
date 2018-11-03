
public class Producer extends Thread{
	
	Buffer buffer;
	int produceAmount;
	int produced = 0;
	
	public Producer(Buffer buffer, int produceAmount) {
		this.buffer = buffer;
		this.produceAmount = produceAmount;
	}
	public void run() {
		produce();
	}
	
	private void produce() {
		while(produced != produceAmount) {
			if (buffer.isFull()) {
				
			} else {
				if(buffer.write())
					produced++;
			}
		}
	}

}
