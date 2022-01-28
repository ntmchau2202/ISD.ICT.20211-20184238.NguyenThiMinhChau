package entities.strategies;

import entities.NormalOrder;
import entities.Order;
import entities.RushOrder;
import interfaces.OrderCalculatorInterface;

public class OrderFeeCalculatorFactory {
	public static OrderCalculatorInterface getDeliverFeeCalculator(Order order) {
		if (order instanceof RushOrder) {
			return new RushOrderFeeCalculator();
		}
		
		return new NormalOrderFeeCalculator();
	}
}
