/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author AnaGabriela Barbieru
 */
public class Exp4jExample {

    public static void main(String[] args) {
        Expression expression = new ExpressionBuilder("x^2").variables("x").build();
        expression.setVariable("x", 90);
        if (expression.validate().isValid()) {
            double result = expression.evaluate();
            System.out.println(result);
        }
    }

}
