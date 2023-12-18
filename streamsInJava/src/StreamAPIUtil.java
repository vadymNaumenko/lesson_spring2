import model.Specialist;
import model.Specialty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAPIUtil {

    public static List<Specialist> getSpecialists() {
        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Ivan", new BigDecimal(5000), Specialty.ENGINEER));
        specialists.add(new Specialist("Alex", new BigDecimal(4000), Specialty.ENGINEER));
        specialists.add(new Specialist("Sergey", new BigDecimal(3000), Specialty.DEVOPS));
        specialists.add(new Specialist("Fedor", new BigDecimal(2000), Specialty.DEVOPS));
        specialists.add(new Specialist("Kiril", new BigDecimal(10000), Specialty.MANAGER));
        specialists.add(new Specialist("Petr", new BigDecimal(10000), Specialty.MANAGER));
        return specialists;
    }

    public static List<Specialist> filterBySpeciality(List<Specialist> specialists, Specialty specialty) {
        return specialists.stream()
                .filter(specialist -> specialist.getSpecialty().equals(specialty))
                .toList();
    }

    public static List<Specialist> sortSpecialistsByNameAsc(List<Specialist> specialists) {
        return specialists.stream()
                .sorted(Comparator.comparing(Specialist::getName))
                .toList();
    }

    public static List<Specialist> sortSpecialistsByNameDesc(List<Specialist> specialists) {
        return specialists.stream()
                .sorted(Comparator.comparing(Specialist::getName).reversed())
                .toList();
    }

    public static Specialist findMinSalary(List<Specialist> specialists) {
        return specialists.stream()
                .min(Comparator.comparing(Specialist::getSalary))
                .orElse(null);
    }

    public static Map<Specialty, List<Specialist>> groupingBySpecialty(List<Specialist> specialists) {
        return specialists.stream()
                .collect(Collectors.groupingBy(specialist -> specialist.getSpecialty()));
    }
}
