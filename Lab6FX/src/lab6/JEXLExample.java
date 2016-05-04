/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

/**
 *
 * @author AnaGabriela Barbieru
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.JxltEngine.Expression;
import org.apache.commons.jexl3.MapContext;

public class JEXLExample {

    private static final JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();
    
    public static void main(String[] args) {
        MyObject obj = new MyObject();
        String expr = "E1+E2+E3";
        JexlExpression e = jexl.createExpression( expr );
        JexlContext context = new MapContext();
        context.set("E1", obj.getNumber1());
        context.set("E2", obj.getNumber2());
        context.set("E3", obj.getNumber3());
        Number result = (Number) e.evaluate(context);
        System.out.println(result);
        
    }
}