package com.isrs.roster.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import com.isrs.roster.Employee;
import com.isrs.roster.Job;
import com.isrs.roster.JobAssignment;
import com.isrs.roster.JobSchedule;

public class OptaPlannerUnitTest {

	 JobSchedule unsolvedCourseSchedule;
	static final int testSize = 3;

	@BeforeEach
	public  void setUp() {

		unsolvedCourseSchedule = new JobSchedule();

		// Dummy Employee List from frontend
		List<Employee> employeeList = new ArrayList<>();
		
		// create conflict employee
		Employee emp1 = new Employee();
		emp1.setName("A");
		emp1.setEmployeeID(100);
		emp1.setEmployeeGrade(1);
		List<Integer> shiftAvailabilty1 = new ArrayList<Integer>();
		List<Integer> preferedLocation1 = new ArrayList<Integer>();
		shiftAvailabilty1.add(0);
		shiftAvailabilty1.add(1);
		preferedLocation1.add(1);
		emp1.setPreferredLocation(preferedLocation1);
		emp1.setShiftAvailability(shiftAvailabilty1);
		employeeList.add(emp1);
		
		Employee emp2 = new Employee();
		emp2.setName("B");
		emp2.setEmployeeID(200);
		emp2.setEmployeeGrade(2);
		List<Integer> shiftAvailabilty2 = new ArrayList<Integer>();
		List<Integer> preferedLocation2 = new ArrayList<Integer>();
		shiftAvailabilty2.add(0);
		shiftAvailabilty2.add(1);
		preferedLocation2.add(1);
		emp2.setPreferredLocation(preferedLocation2);
		emp2.setShiftAvailability(shiftAvailabilty2);
		employeeList.add(emp2);

		
		/*Employee emp3 = new Employee();
		emp3.setName("C");
		emp3.setEmployeeID(300);
		emp3.setEmployeeGrade(3);
		List<Integer> shiftAvailabilty3 = new ArrayList<Integer>();
		//emp.setPreferredLocation(null);
		emp3.setShiftAvailability(shiftAvailabilty3);
		employeeList.add(emp3);*/
		
		// Dummy JobList from frontend
		List<Job> jobList = new ArrayList<>();	
		Job job1 = new Job();
		job1.setJobID(1);
		job1.setJobLocation(1);
		job1.setShift(5);
		jobList.add(job1);

		Job job2 = new Job();
		job2.setJobID(2);
		job2.setJobLocation(1);
		job2.setShift(2);
		jobList.add(job2);
		
		/*Job job3 = new Job();
		job3.setJobID(3);
		job3.setJobLocation(3);
		jobList.add(job3);*/

		
		unsolvedCourseSchedule.setEmployeeList(employeeList);
		unsolvedCourseSchedule.setJobList(jobList);
		unsolvedCourseSchedule.setJobAssignmentList(createJobAssignmentList(employeeList, jobList));
		
		System.out.println("Job:"+jobList);
		System.out.println("Employee:"+employeeList);

	}

//Initialization of Planning Entity
	private List<JobAssignment> createJobAssignmentList(List<Employee> empList, List<Job> jobList) {
		List<JobAssignment> JobAssignmentList = new ArrayList<JobAssignment>(testSize);
		
		/*for (int i=0; i<JobAssignmentList.size(); i++) {
			JobAssignment jobAssignment = new JobAssignment();
			jobAssignment.setEmployee(empList.get(i));
			jobAssignment.setJob(jobList.get(i));
			JobAssignmentList.add(jobAssignment);
		}*/
		
		JobAssignment jobAssignment = new JobAssignment();
		jobAssignment.setEmployee(empList.get(0));
		jobAssignment.setJob(jobList.get(0));
		JobAssignmentList.add(jobAssignment);
		jobAssignment.setEmployee(empList.get(1));
		jobAssignment.setJob(jobList.get(1));
		JobAssignmentList.add(jobAssignment);
		System.out.println("JobAssignmentList:"+JobAssignmentList.size());
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
		System.out.println("Solver:"+unsolvedCourseSchedule);
		SolverFactory<JobSchedule> solverFactory = SolverFactory.createFromXmlResource("courseScheduleSolverConfiguration.xml");
		Solver<JobSchedule> solver = solverFactory.buildSolver();
		JobSchedule solvedCourseSchedule = solver.solve(unsolvedCourseSchedule);
		System.out.println(solvedCourseSchedule.toString());

		// Assert.assertNotNull(solvedCourseSchedule.getScore());
		// Assert.assertEquals(0, solvedCourseSchedule.getScore().getHardScore());
	}

}
