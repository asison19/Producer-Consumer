
public class Consumer extends Thread{
	
	Buffer buffer;
	int index = 0;
	int max;
	
	public Consumer(Buffer buffer, int max) {
		this.buffer = buffer;
		this.max = max;
	}
	
	public void run() {
		while(!buffer.isDone) {
			consume();
		}
	}
	
	private void consume() {
		if(!buffer.isEmpty())
			buffer.read();
	}
}
