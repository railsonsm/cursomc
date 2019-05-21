package com.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Teste {
	public static void main(String[] args) {
		LinkedList<BigDecimal> valores =  new LinkedList<BigDecimal>();
		valores.add(new BigDecimal(1));
		valores.add(new BigDecimal(1.1));
		 BigDecimal valorTotal = valores.stream().reduce(new BigDecimal(0), (x,y) -> x.add(y));
		System.out.println(valorTotal);
	}
}
