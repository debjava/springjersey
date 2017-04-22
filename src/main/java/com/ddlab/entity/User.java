package com.ddlab.entity;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "firstName", "lastName", "id" })
@JsonPropertyOrder(value = { "firstName", "lastName", "id" })
public class User {

	@XmlElement(name = "firstName")
	@JsonProperty("firstName")
	private String firstName;

	@XmlElement(name = "lastName")
	@JsonProperty("lastName")
	private String lastName;

	@XmlElement(name = "id")
	@JsonProperty("id")
	private int id;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + "]";
	}

	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		String toJson = null;
		try {
			toJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toJson;
	}

	public String toXML() {
		String xmlString = "";
		try {
			JAXBContext context = JAXBContext.newInstance(this.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); 
			StringWriter sw = new StringWriter();
			m.marshal(this, sw);
			xmlString = sw.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	public static User toUser(String jsonString) {

		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = (User) mapper.readValue(jsonString, User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;

	}

	// public static void main(String[] args) {
	// User user = new User();
	// user.setFirstName("Deb");
	// user.setLastName("Mishra");
	// user.setId(1);
	// System.out.println(user.toJSON());
	//
	// System.out.println(user.toXML());
	//
	// }
}
