package org.elsys.postfix;

import org.elsys.postfix.operations.Plus;

public class MainClass {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.addOperation(new Plus(calculator, "+"));
		calculator.run();
	}

}
