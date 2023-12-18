import model.Specialist;
import model.Specialty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OldApproachUtil {
    public static List<Specialist> getSpecialists() {
        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Ivan",new BigDecimal(5000),Specialty.ENGINEER));
        specialists.add(new Specialist("Alex",new BigDecimal(4000),Specialty.ENGINEER));
        specialists.add(new Specialist("Sergey",new BigDecimal(3000),Specialty.DEVOPS));
        specialists.add(new Specialist("Fedor",new BigDecimal(2000),Specialty.DEVOPS));
        specialists.add(new Specialist("Kiril",new BigDecimal(10000),Specialty.MANAGER));
        specialists.add(new Specialist("Petr",new BigDecimal(10000),Specialty.MANAGER));
        return specialists;
    }
    public static List<Specialist> filterBySpeciality(List<Specialist> specialists, Specialty specialty) {
        List<Specialist> result = new ArrayList<>();
        for (Specialist s:specialists) {
            if (s.getSpecialty().equals(specialty)){
                result.add(s);
            }
        }
        return result;
    }


    public static List<Specialist> sortSpecialistsByName(List<Specialist> specialists) {
        specialists.sort(Comparator.comparing(Specialist::getName));
        return specialists;
    }
}
