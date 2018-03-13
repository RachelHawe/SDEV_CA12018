package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Employee extends Model {
        @Id
        private Long id;

        @ManyToMany(cascade = CascadeType.ALL, mappedBy= "employees")
        public List<Project> projects;

        @Constraints.Required
        private String fname;

        @Constraints.Required
        private String lname;
        
        @ManyToOne
        private Department department;

        @OneToOne(cascade = CascadeType.REMOVE)
        private Address address;
       

        public List<Long> projSelect = new ArrayList<Long>();

        public static final Finder<Long, Employee> find = new Finder<>(Employee.class);
    

        public static List<Employee> findAll() {
            return Employee.find.query().where().orderBy("id asc").findList();
        }

        public Employee() {
        }

        public Employee(Long id, String fname, String lname) {
            this.id = id;
            this.fname = fname;
            this.lname = lname;
            
        }
        public List<Long> getProjSelect(){
            return this.projSelect;
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getFname() {
            return fname;
        }
        public String getLname() {
            return lname;
        }
        public void setFname(String fname) {
            this.fname = fname;
        }
        public void setLname(String lname) {
            this.lname = lname;
        }
        public Department getDepartment(){
            return this.department;
        }
        public void setDepartment(Department d){
            this.department=d;
        }
        public List<Project> getProjects() {
            return projects;
        }
        public void setProjects(List<Project> projects) {
            this.projects = projects;
        }
        public void setAddress(Address address){
            this.address=address;
        }
        public Address getAddress(){
            return this.address;
        }
    }
    