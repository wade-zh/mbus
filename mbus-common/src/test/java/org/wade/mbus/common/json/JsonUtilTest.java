package org.wade.mbus.common.json;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;


public class JsonUtilTest extends JsonUtil {

    @Test
    public void getJson() {
        Person person = new Person(1);
        String json = this.getJson(person);
        assert json != null;
        assert json.length() > 1;
        System.out.println(json);
    }

    @Test
    public void getModelAsList() {
        List<Person> personList = asList(
                new Person(1),
                new Person(2),
                new Person(3));
        String json = this.getJson(personList);
        List<Person> modelAsList = this.getModelAsList(json, Person.class);
        assert modelAsList != null;
        assert modelAsList.size() > 1;
        System.out.println(modelAsList);
    }

    @Test
    public void getModel() {
        Person person = new Person(1);
        String json = this.getJson(person);
        Person model = this.getModel(json, Person.class);
        assert model != null;
        System.out.println(model);
    }
}

class Person {
    public Person(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person() {

    }

    private Integer id;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                '}';
    }
}