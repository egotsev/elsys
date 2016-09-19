package org.elsys.postfix.operations;

import org.elsys.postfix.Calculator;

public class Plus extends AbstractOperation implements Operation {

	public Plus(Calculator calculator, String token) {
		super(calculator, token);
	}

	@Override
	public void calculate() {
		Double value1 = getCalculator().popValue();
		Double value2 = getCalculator().popValue();
		double result = value1 + value2;
		getCalculator().addValue(result);
		System.out.println(result);
	}

}
