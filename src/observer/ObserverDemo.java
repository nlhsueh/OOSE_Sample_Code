package observer;

import java.util.Observable;

public class ObserverDemo {

	public static void main(String[] args) {

	}

}

class Subject extends java.util.Observable {

}

class View1 implements java.util.Observer {
	public void update(Observable arg0, Object arg1) {

	}
}

class View2 implements java.util.Observer {

	public void update(Observable o, Object arg) {

	}

}