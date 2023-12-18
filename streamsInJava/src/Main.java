import model.Specialist;
import model.Specialty;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        List<Specialist> specialists = OldApproachUtil.getSpecialists();
        List<Specialist> specialists = StreamAPIUtil.getSpecialists();

        //Filter
//        List<Specialist> engineers = OldApproachUtil.filterBySpeciality(specialists, Specialty.ENGINEER);
//        List<Specialist> engineers = StreamAPIUtil.filterBySpeciality(specialists, Specialty.ENGINEER);
//        System.out.println(engineers);

        //Sorting
//        List<Specialist> sortSpecialistsAsc = StreamAPIUtil.sortSpecialistsByNameAsc(specialists);
//        List<Specialist> sortSpecialistsDesc = StreamAPIUtil.sortSpecialistsByNameDesc(specialists);
//        System.out.println(sortSpecialistsDesc);
//        System.out.println(sortSpecialistsAsc);

        //Find min salary
//        Specialist withMinSalary = StreamAPIUtil.findMinSalary(specialists);
//        StreamAPIUtil.printSpecialists(withMinSalary);
        //Find max salary
//        Specialist withMaxSalary = StreamAPIUtil.findMaxSalary(specialists);
//        StreamAPIUtil.printSpecialists(withMaxSalary);

        //Grouping
        Map<Specialty,List<Specialist>> groupingBySpecialty = StreamAPIUtil.groupingBySpecialty(specialists);
//        System.out.println(groupingBySpecialty);




    }
}