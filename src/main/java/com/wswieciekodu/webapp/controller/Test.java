package com.wswieciekodu.webapp.controller;

import java.util.Objects;

public class Test {
    private String test;
    private boolean testFlag;

    public Test() {

    }

    public String getTest() {
        return test;
    }

    public boolean isTestFlag() {
        return testFlag;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setTestFlag(boolean testFlag) {
        this.testFlag = testFlag;
    }


    // Explain me this code

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test1 = (Test) o;
        return testFlag == test1.testFlag && Objects.equals(test, test1.test);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
