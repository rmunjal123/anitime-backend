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
		for (int i = 1; i <= testSize; i++) {
			Employee emp = new Employee();
			emp.setName("Hello" + i);
			emp.setEmployeeID(i * 100);
			emp.setEmployeeGrade(1);
			// not added preffered shift and preffered location so far
			employeeList.add(emp);
		}
		
		// create conflict employee
		Employee emp = new Employee();
		emp.setName("Hi");
		emp.setEmployeeID(100);
		emp.setEmployeeGrade(1);
		emp.setPreferredLocation(null);
		emp.setShiftAvailability(null);
		
		List<Integer> shiftAvailabilty = new ArrayList<Integer>(2);
		//emp.setShiftAvailability(shiftAvailability);
		
		// Dummy JobList from frontend
		List<Job> jobList = new ArrayList<>();
		for (int i = 1; i <= testSize; i++) {
			Job job = new Job();
			job.setJobID(i);
			job.setJobLocation(i * 100);
			jobList.add(job);
		}
		
		// create conflict job
		
		
		unsolvedCourseSchedule.setEmployeeList(employeeList);
		unsolvedCourseSchedule.setJobList(jobList);
		unsolvedCourseSchedule.setJobAssignmentList(createJobAssignmentList(employeeList, jobList));
		
		System.out.println(jobList);
	}

//Initialization of Planning Entity
	private static List<JobAssignment> createJobAssignmentList(List<Employee> empList, List<Job> jobList) {
		List<JobAssignment> JobAssignmentList = new ArrayList<JobAssignment>(testSize);
		
		for (int i=0; i<=JobAssignmentList.size(); i++) {
			JobAssignment jobAssignment = new JobAssignment();
			//jobAssignment.setEmployee(empList.get(i));
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
