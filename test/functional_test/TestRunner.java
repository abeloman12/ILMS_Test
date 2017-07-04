/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional_test;

import top_down_integration_test.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Biruk Adera
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        
        for (Failure failer : result.getFailures()) {
            System.out.println(failer);
        }
        
        System.out.println("[*] Test Result : " + result.wasSuccessful());
    }
}