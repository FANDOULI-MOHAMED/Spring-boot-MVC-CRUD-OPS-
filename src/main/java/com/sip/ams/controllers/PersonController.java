package com.sip.ams.controllers;

import com.sip.ams.entities.Person;
import com.sip.ams.forms.PersonForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/personController")
public class PersonController {

    static int count=2;
    private static List<Person> persons = new ArrayList<Person>();
    static {
        persons.add(new Person(0,"Albert", "Einstein"));
        persons.add(new Person(1,"Frederic", "Gauss"));
    }
    // from application.propreties
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/indexPerson", "/Person"})
    public String index(Model model) {

        model.addAttribute("message", message);

        return "pages/index";
    }
    @GetMapping("/personList" )
    public String personList(Model model) {

        model.addAttribute("Persons", persons);

        return "pages/personList";
    }
    @GetMapping("/addPerson" )
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "pages/addPerson";
    }
    @PostMapping("/addPerson" )
    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm) {
        int id=count++;
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(id,firstName, lastName);
            persons.add(newPerson);

            return "redirect:personList";
          // return "pages/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "pages/addPerson";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("fieldId") int id){
    System.out.println(id);
    Person p=findObjectById(persons,id);
        int pos=persons.indexOf(p);
    persons.remove(pos);
    return "redirect:personList";

    }




    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editObject(@PathVariable("id") int id, Model model, @ModelAttribute("personForm") PersonForm personForm) {

        Person obj = findObjectById(persons,id);
        System.out.println(obj);
        model.addAttribute("myObject", obj);
        return "pages/edit-object";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateObject(@ModelAttribute("myObject") Person obj, BindingResult bindingResult) {
       //Person p= findObjectById(persons,obj.getId());
       System.out.println(obj);
        Person p=findObjectById(persons,obj.getId());
        int pos=persons.indexOf(p);
       persons.get(pos).setFirstName(obj.getFirstName());
        persons.get(pos).setLastName(obj.getLastName());
        System.out.print("this is my list content "+persons);
        return "redirect:personList";
    }


    public static Person findObjectById(List<Person> list, int id) {
        for (Person obj : persons) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }




}
