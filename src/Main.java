import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);
        List<String> conscripts = persons.stream()
                .filter(man -> man.getSex().equals(Sex.MAN))
                .filter(man -> man.getAge() > 18)
                .filter(man -> man.getAge() < 27)
                .map(s -> s.getFamily())
                .collect(Collectors.toList());
        List<Person> workable = persons.stream()
                .filter(m -> m.getEducation().equals(Education.HIGHER))
                .filter(m -> {
                    if(m.getSex().equals(Sex.MAN)){
                        if(m.getAge() > 18 || m.getAge() < 65){
                            return true;
                        }else{
                            return false;
                        }
                    }else {
                        if(m.getAge() > 18 || m.getAge() < 60){
                            return true;
                        }else{
                            return false;
                        }
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        }
    }
