package org.naivedb.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.LinkedList;

public class TestRunner {
    private static int total_cnt = 0;
    private static int fail_cnt = 0;
    private static long run_time = 0;
    private static LinkedList<Result> results = new LinkedList<Result>();

    private static void addResult(Result result) {
        total_cnt += result.getRunCount();
        fail_cnt += result.getFailureCount();
        run_time += result.getRunTime();
        results.add(result);
    }

    private static void showResult() {
        System.out.printf("Ran %d tests in %d ms:\n  - succeeded test: %d \n  - failed tests: %d\n",
            total_cnt, run_time, total_cnt - fail_cnt, fail_cnt);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Failures:");
        for (Result res: results) {
            for (Failure fail: res.getFailures()) {
                System.out.printf("  - %s\n", fail.toString());
            }
        }
        System.out.println("---------------------------------------------------------------------");

        if (fail_cnt == 0) System.out.printf("\nFinal Result: Succeeded.\n");
        else  System.out.printf("\nFinal Result: Failed.\n");
    }

    public static void main(String[] args) {
        addResult(JUnitCore.runClasses(DatabaseTest.class));
        addResult(JUnitCore.runClasses(TableTest.class));

        showResult();
    }
}