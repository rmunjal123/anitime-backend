package com.isrs.roster;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class JobSchedule {

    private List<Employee> employeeList = new ArrayList<>();
    private List<Job> jobList = new ArrayList<>();
    private List<JobAssignment> jobAssignmentList = new ArrayList<>();
    private HardSoftScore score;
    
    @ValueRangeProvider(id = "employee")
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