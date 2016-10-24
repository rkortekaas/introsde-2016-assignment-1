package people;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pojos.HealthProfile;
import pojos.Person;
import dao.PeopleStore;

public class JAXBUnMarshaller {  	
	public static PeopleStore people = new PeopleStore();

    // JAXBUnMarshaller unmarshals persons from people.xml to java person objects.

	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        System.out.println();
        System.out.println("Unmarshall persons from output.xml to java person objects: ");
        System.out.println("===========================================================");
        System.out.println();
        Unmarshaller um = jc.createUnmarshaller();
        PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("output.xml"));
        List<Person> list = people.getData();
        for (Person person : list) {
          System.out.println("Person: " + person.getFirstname() + " born on: "
              + person.getBirthdate());
        }

    }
}
