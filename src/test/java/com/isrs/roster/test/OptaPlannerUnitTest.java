package com.isrs.roster.test;

import com.isrs.roster.JobSchedule;
import com.isrs.roster.Employee;
import com.isrs.roster.Job;
import com.isrs.roster.JobAssignment;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.List;
import java.util.ArrayList;

public class OptaPlannerUnitTest {

    static JobSchedule unsolvedCourseSchedule;


    @BeforeAll
    public static void setUp() {
    	
    	unsolvedCourseSchedule = new JobSchedule();
    	
    	//Dummy Employee List from frontend
    	List <Employee> EmployeeList = new ArrayList<Employee>(10);
    	Employee emp = new Employee();
        for (int i = 1; i<=10; i++)
        {
        emp.setName("Hello"+i);
		emp.setEmployeeID(i*100);
		emp.setEmployeeGrade(1);
		//not added preffered shift and preffered location so far
		EmployeeList.add(emp);
		System.out.println(emp);
        }
        //Dummy JobList from frontend
        List <Job> JobList = new ArrayList<Job>(10);
        for (int i = 1; i <=10; i++) {
        Job job = new Job();
        job.setJobID(i);
        job.setJobLocation(i*100);
        JobList.add(job);
        System.out.println(job);
        }
        System.out.println(JobList);
       }
//Initialization of Planning Entity
                private List<JobAssignment> createJobAssignmentList(JobAssignment jobAssignment){
                List<JobAssignment> JobAssignmentList = new ArrayList<JobAssignment>(10);	
                for (Job job : unsolvedCourseSchedule.getJobList()) {
                            JobAssignment jobAssign = new JobAssignment();
                            jobAssign.setJob(job);
                            System.out.println(jobAssign);
                            JobAssignmentList.add(jobAssign);     
                        }
                System.out.println(JobAssignmentList);
                    return JobAssignmentList;
     
    }

    
    
            
    /*@Test
    public void test_whenDroolsSolver() {
    	System.out.println(unsolvedCourseSchedule);
        SolverFactory<JobSchedule> solverFactory = SolverFactory.createFromXmlResource("courseScheduleSolverConfigDrools.xml");
        Solver<JobSchedule> solver = solverFactory.buildSolver();
        JobSchedule solvedCourseSchedule = solver.solve(unsolvedCourseSchedule);
        System.out.println(solvedCourseSchedule);

        Assert.assertNotNull(solvedCourseSchedule.getScore());
        Assert.assertEquals(0, solvedCourseSchedule.getScore().getHardScore());
    }*/
    
    @Test
    public void test_whenCustomJavaSolver() {
    	System.out.println(unsolvedCourseSchedule);
        SolverFactory<JobSchedule>solverFactory = SolverFactory.createFromXmlResource("courseScheduleSolverConfiguration.xml");
        Solver<JobSchedule> solver = solverFactory.buildSolver();
        JobSchedule solvedCourseSchedule = solver.solve(unsolvedCourseSchedule);
        System.out.println(solvedCourseSchedule);

        //Assert.assertNotNull(solvedCourseSchedule.getScore());
        //Assert.assertEquals(0, solvedCourseSchedule.getScore().getHardScore());
    }

}
