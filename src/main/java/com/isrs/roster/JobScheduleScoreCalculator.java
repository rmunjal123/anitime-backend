package com.isrs.roster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import java.lang.Math;

public class JobScheduleScoreCalculator implements EasyScoreCalculator<JobSchedule> {
	
    @Override
    public HardSoftScore calculateScore(JobSchedule jobSchedule) {
        int hardScore = 0;
        int softscore = 0;
        HashMap<Integer, ArrayList<Integer>> empCounts = new HashMap<Integer, ArrayList<Integer>>();

        for (JobAssignment jobAssignment : jobSchedule.getJobAssignmentList()){
            Job job = jobAssignment.getJob();
            Employee employee = jobAssignment.getEmployee();

            // Accumulate jobs assigned to each worker
            if (empCounts.containsKey(employee.getEmployeeID())){
                empCounts.get(employee.getEmployeeID()).add(job.getShift());
            } else {
                empCounts.put(employee.getEmployeeID(), new ArrayList<Integer>());
                empCounts.get(employee.getEmployeeID()).add(job.getShift());
            }


            // Hard Contraints

            // No unassigned Job
            //if (jobAssignment.getEmployee() == null) {
            //    hardScore += -1;
            //}

            // No unavailable employee assigned
            if (!employee.getShiftAvailability().contains(job.getShift())){
                hardScore += -1;
            }

            // No. of jobs follow grade
            switch(employee.getEmployeeGrade()) {
                case 1:
                    if (empCounts.get(employee.getEmployeeID()).size() > 3){
                        hardScore += -1;
                    }
                case 2:
                    if (empCounts.get(employee.getEmployeeID()).size() > 2){
                        hardScore += -1;
                    }
                case 3:
                    if (empCounts.get(employee.getEmployeeID()).size() > 1){
                        hardScore += -1;
                    }
            }

            // No consecutive shifts, same shift
            for (Integer assignedShift : empCounts.get(employee.getEmployeeID())) {
                if (Math.abs(assignedShift - job.getShift()) < 2){
                    hardScore += -1;
                }
            }


            // Soft Constraints
            if (employee.getPreferredLocation().contains(job.getJobLocation())){
                softscore += 1;
            }

        }

        return HardSoftScore.of(hardScore, softscore);
    }
}