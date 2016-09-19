package org.elsys.lambdas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MathUtils {
	public static double integrate(Integrable function, double begin,
			double end, int deltas) {
		double interval = (end - begin) / deltas;
		double sum = 0;
		for(; begin < end; begin += interval) {
			sum += interval * function.call(begin);
		}
		return sum;
	}
	
	public static void main(String[] args) {
		List<Double> result = new ArrayList<>();
		result.add(MathUtils.integrate(x -> x * x, -2, 15.5, 1000));
		result.add(MathUtils.integrate(new Integrable() {
			@Override
			public double call(double x) {
				return x * x;
			}
		}, -2, 15.5, 1000));

		
		
		result.add(MathUtils.integrate(x -> Math.sin(x), 0, Math.PI * 2, 1000));
		// method reference of a static method
		result.add(MathUtils.integrate(Math::sin, 0, Math.PI*2, 1000));
		// method reference of an instance method in System.out
		result.forEach(System.out::println);
		result.stream()
			// method reference of an instance method
			.map(Double::intValue)
			// equal to:
			// .map(x -> x.intValue())
			.forEach(System.out::println);
		
		result.stream()
			// constructor reference
			.map(BigDecimal::new)
			// .map(x -> new BigDecimal(x))
			.forEach(System.out::println);;
	}
}
