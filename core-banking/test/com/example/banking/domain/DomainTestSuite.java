package com.example.banking.domain;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@SelectClasses({AccountTest.class,CheckingAccountTest.class,CustomerTest.class})
@SuiteDisplayName("Domain Test Suite")
@Suite
public class DomainTestSuite {

}
