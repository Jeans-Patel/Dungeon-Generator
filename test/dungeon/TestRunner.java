package dungeon;

import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String [] args) {

        System.out.println("\n***** Chamber Test ****\n");
		Result result1 = JUnitCore.runClasses(ChamberTest.class);
        System.out.println("\n*****Failed Chamber Test Report****\n");
        List<Failure> failedList1 = result1.getFailures();
        failedList1.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Chamber Tests = " + result1.getFailureCount());

        System.out.println("\n***** Door Test ****\n");
        Result result2 = JUnitCore.runClasses(DoorTest.class);
        System.out.println("\n*****Failed Door Test Report****\n");
        List<Failure> failedList2 = result2.getFailures();
        failedList2.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Door Tests = " + result2.getFailureCount());

        System.out.println("\n***** Passage Section Test ****\n");
        Result result3 = JUnitCore.runClasses(PassageSectionTest.class);
        System.out.println("\n*****Failed Passage Section Test Report****\n");
        List<Failure> failedList3 = result3.getFailures();
        failedList3.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Passage Section Tests = " + result3.getFailureCount());

        System.out.println("\n***** Passage Test ****\n");
        Result result4 = JUnitCore.runClasses(PassageTest.class);
        System.out.println("\n*****Failed Passage Test Report****\n");
        List<Failure> failedList4 = result4.getFailures();
        failedList4.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Passage Tests = " + result4.getFailureCount());

        /*repeat steps the above for each junit test file you have*/
	}
}