package interfaces;

import entities.NormalOrder;
import entities.Order;

public interface OrderCalculatorInterface {
	public double calculateShippingFee(Order order);
	public double calculateVAT(Order order);
}
