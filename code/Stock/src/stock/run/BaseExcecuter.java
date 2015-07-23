package stock.run;


public abstract class BaseExcecuter {
	
	private void init() {
	}
	
	public void run() {
		init();
		excecute();
	}
	
	public abstract void excecute(); 

}
