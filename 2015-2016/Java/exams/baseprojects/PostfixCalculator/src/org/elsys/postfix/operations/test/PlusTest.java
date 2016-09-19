package org.elsys.postfix.operations.test;

import static org.junit.Assert.*;

import org.elsys.postfix.Calculator;
import org.elsys.postfix.operations.Plus;
import org.junit.Test;

public class PlusTest {

	@Test
	public final void testCalculate() {
		Calculator calculator = new Calculator();
		Plus plus = new Plus(calculator, "+");
		calculator.addOperation(plus);
		// the plus operation gets its operands from the calculator
		// so we have to add them to the calculator's stack
		calculator.addValue(5);
		calculator.addValue(10);
		// execute the operation
		plus.calculate();
		// the plus operation will put the result in the calculator's
		// stack so we get it from there
		assertEquals(15, calculator.popValue(), 0.00001);
	}

}
