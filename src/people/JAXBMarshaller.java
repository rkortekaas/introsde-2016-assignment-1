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

public class JAXBMarshaller {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {

		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Fulano", "De Tal", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "Jane", "Doe", "1985-03-20", hp);

		people.getData().add(pallino);
		people.getData().add(pallo);
		people.getData().add(john);
	}	

	// JAXBMarshaller marshals java person objects to xml persons in people.xml

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        System.out.println("Print output marshaller and saving to output.xml:");
        System.out.println("==========================");
        System.out.println();
        m.marshal(people, System.out);			  // marshalling into the system default output
        m.marshal(people,new File("output.xml")); // marshalling into a file
        
    }
}
