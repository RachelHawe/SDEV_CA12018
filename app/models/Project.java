package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Project extends Model {
    
        // Properties
        @Id
        private Long id;
        @Constraints.Required
        private String name;
        @ManyToOne
        private Department department;
        @Constraints.Required
        private String description; 
        

        public static final Finder<Long, Project> find = new Finder<>(Project.class);
    

        public static final List<Project> findAll() { 
            
                    return Project.find.all();
        }
        // Default Constructor
        public Project() {
        }
    
        // Constructor to initialise object
        public Project(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
            
        }
    
        // Accessor methods
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Department getDepartment() {
            return department;
        }
        
        public void setDepartment(Department c) {
            this.department = c;
        }
        public String getDescription() { 
            return description; 
        } 
        public void setDescription(String description) { 
            this.description = description; 
        } 
        
         
    }
    