package com.isrs.roster;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class JobSchedule {

    private List<Employee> employeeList;
    private List<Job> jobList;
    private List<JobAssignment> jobAssignmentList;
    private HardSoftScore score;


    // public JobSchedule(){
    // 	employeeList = new List<Employee>();
    // 	jobAssignmentList = new List<Job>();
    // }
    @ValueRangeProvider(id = "employeeRange")
    @ProblemFactCollectionProperty
    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    public void setEmployeeList(List<Employee> employeeList){
        this.employeeList = employeeList;
    }

    @ProblemFactCollectionProperty
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList){
        this.jobList = jobList;
    }

    @PlanningEntityCollectionProperty
    public List<JobAssignment> getJobAssignmentList() {
        return jobAssignmentList;
    }

    public void setJobAssignmentList(List<JobAssignment> jobAssignmentList) {
        this.jobAssignmentList = jobAssignmentList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}