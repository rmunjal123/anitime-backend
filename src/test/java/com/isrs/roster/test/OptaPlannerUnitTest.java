package com.isrs.roster.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import com.isrs.roster.Employee;
import com.isrs.roster.Job;
import com.isrs.roster.JobAssignment;
import com.isrs.roster.JobSchedule;

public class OptaPlannerUnitTest {

	static JobSchedule unsolvedCourseSchedule;
	static final int testSize = 10;

	@BeforeAll
	public static void setUp() {

		unsolvedCourseSchedule = new JobSchedule();

		// Dummy Employee List from frontend
		List<Employee> employeeList = new ArrayList<>();
		
		// create conflict employee
		Employee emp1 = new Employee();
		emp1.setName("A");
		emp1.setEmployeeID(100);
		emp1.setEmployeeGrade(1);
		//List<Integer> shiftAvailabilty = new ArrayList<Integer>();
		//emp.setPreferredLocation(null);
		//emp.setShiftAvailability(shiftAvailibility);
		employeeList.add(emp1);
		
		Employee emp2 = new Employee();
		{
		emp2.setName("B");
		emp2.setEmployeeID(200);
		emp2.setEmployeeGrade(2);
		//List<Integer> shiftAvailabilty = new ArrayList<Integer>();
		//emp.setPreferredLocation(null);
		//emp.setShiftAvailability(shiftAvailibility);
		employeeList.add(emp2);
		}
		
		Employee emp3 = new Employee();
		emp3.setName("C");
		emp3.setEmployeeID(300);
		emp3.setEmployeeGrade(3);
		//List<Integer> shiftAvailabilty = new ArrayList<Integer>();
		//emp.setPreferredLocation(null);
		//emp.setShiftAvailability(shiftAvailibility);
		employeeList.add(emp3);
		
		// Dummy JobList from frontend
		List<Job> jobList = new ArrayList<>();	
		Job job1 = new Job();
		job1.setJobID(1);
		job1.setJobLocation(1);
		jobList.add(job1);

		Job job2 = new Job();
		job2.setJobID(2);
		job2.setJobLocation(2);
		jobList.add(job2);
		
		
		unsolvedCourseSchedule.setEmployeeList(employeeList);
		unsolvedCourseSchedule.setJobList(jobList);
		unsolvedCourseSchedule.setJobAssignmentList(createJobAssignmentList(employeeList, jobList));
		
		System.out.println(jobList);
		System.out.println(employeeList);
	}

//Initialization of Planning Entity
	private static List<JobAssignment> createJobAssignmentList(List<Employee> empList, List<Job> jobList) {
		List<JobAssignment> JobAssignmentList = new ArrayList<JobAssignment>(testSize);
		
		for (int i=0; i<=JobAssignmentList.size(); i++) {
			JobAssignment jobAssignment = new JobAssignment();
			jobAssignment.setEmployee(empList.get(i));
			jobAssignment.setJob(jobList.get(i));
		}

		System.out.println(JobAssignmentList);
		
		return JobAssignmentList;
	}

	/*
	 * @Test public void test_whenDroolsSolver() {
	 * System.out.println(unsolvedCourseSchedule); SolverFactory<JobSchedule>
	 * solverFactory =
	 * SolverFactory.createFromXmlResource("courseScheduleSolverConfigDrools.xml");
	 * Solver<JobSchedule> solver = solverFactory.buildSolver(); JobSchedule
	 * solvedCourseSchedule = solver.solve(unsolvedCourseSchedule);
	 * System.out.println(solvedCourseSchedule);
	 * 
	 * Assert.assertNotNull(solvedCourseSchedule.getScore()); Assert.assertEquals(0,
	 * solvedCourseSchedule.getScore().getHardScore()); }
	 */

	@Test
	public void test_whenCustomJavaSolver() {
		System.out.println(unsolvedCourseSchedule);
		SolverFactory<JobSchedule> solverFactory = SolverFactory.createFromXmlResource("courseScheduleSolverConfiguration.xml");
		Solver<JobSchedule> solver = solverFactory.buildSolver();
		JobSchedule solvedCourseSchedule = solver.solve(unsolvedCourseSchedule);
		System.out.println(solvedCourseSchedule);

		// Assert.assertNotNull(solvedCourseSchedule.getScore());
		// Assert.assertEquals(0, solvedCourseSchedule.getScore().getHardScore());
	}

}
