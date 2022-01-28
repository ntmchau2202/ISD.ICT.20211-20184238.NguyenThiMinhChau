package entities.strategies;

import entities.NormalOrder;
import entities.Order;
import interfaces.OrderCalculatorInterface;
import utils.Configs;

public class NormalOrderFeeCalculator implements OrderCalculatorInterface {

	@Override
	public double calculateShippingFee(Order order) {
		if(order.getAmount() > Configs.priceFreeShipping) {
			return 0;
		} else {
			if (order.getCustomer().getCity().equalsIgnoreCase("Hanoi") || order.getCustomer().getCity().equalsIgnoreCase("Tp. HCM")) {
				return Configs.HNHCMShippingFee;
			} else {
				return Configs.otherShippingFee;
			}
		}
	}

	@Override
	public double calculateVAT(Order order) {
		return order.getAmount() * Configs.vatPercentage;
	}
}
