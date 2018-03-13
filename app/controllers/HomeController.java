package controllers;

import play.mvc.*;

import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import models.*;
import models.users.*;
import views.html.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    private FormFactory formFactory;
    private Environment e;
    
        @Inject
        public HomeController(FormFactory f,Environment env) {
            this.formFactory = f;
            this.e = env;
        }
    
        public Result index(Long proj) {
            List<Employee> employeeList = null;
            List<Project> projectList = Project.findAll();
            if (proj == 0) {
                employeeList = Employee.findAll();
            }
            else {
                employeeList = Project.find.ref(proj).getEmployees();
            }
            return ok(index.render(employeeList, projectList, User.getUserById(session().get("email")),e));
        }

        @Security.Authenticated(Secured.class)
        @With(AuthAdmin.class)
        public Result addEmployee() {
            Form<Employee> employeeForm = formFactory.form(Employee.class);
            Form<Address> addressForm = formFactory.form(Address.class);
            return ok(addEmployee.render(employeeForm, addressForm, User.getUserById(session().get("email"))));
        }
        public Result addEmployeeSubmit() {
            Employee newEmployee;
            Form<Employee> newEmployeeForm = formFactory.form(Employee.class).bindFromRequest();
    
            Address newAddress;
            Form<Address> newAddressForm = formFactory.form(Address.class).bindFromRequest();
    
            if (newEmployeeForm.hasErrors() || newAddressForm.hasErrors()){
                return badRequest(addEmployee.render(newEmployeeForm, newAddressForm, User.getUserById(session().get("email"))));
            }
            else {
                newAddress = newAddressForm.get();
                newEmployee = newEmployeeForm.get();
    
                if (newEmployee.getId() == null) {
                    newAddress.save();
                    newEmployee.save();
    
                    for (Long proj : newEmployee.getProjSelect()) {
                        newEmployee.getProjects().add(Project.find.byId(proj));
                    }
                    newEmployee.setAddress(newAddress);
                    newEmployee.update();
                    
                flash("success", "Employee " + newEmployee.getFname() +" "+ newEmployee.getLname() + " was updated");
            }
            }
    
    
            MultipartFormData data = request().body().asMultipartFormData();
            FilePart<File> image = data.getFile("upload");
    
            String saveImageMsg = saveFile(newEmployee.getId(), image);
    
            flash("success", "Employee " + newEmployee.getFname() +" "+ newEmployee.getLname() + " has been created/updated " + saveImageMsg);
    
            return redirect(controllers.routes.HomeController.index(0));
        }
    

        @Security.Authenticated(Secured.class)
        @With(AuthAdmin.class)
        @Transactional
        public Result deleteEmployee(Long id) {
            // Employee.find.ref(id).getAddress().delete();
            Employee.find.ref(id).delete();
    
            flash("success", "Employee has been deleted");
            
            return redirect(routes.HomeController.index(0));
        }

        @Security.Authenticated(Secured.class)
        @With(AuthAdmin.class)
        @Transactional
        public Result updateEmployee(Long id) {
            Address a;
            Form<Address> addressForm;
            Employee e;
            Form<Employee> employeeForm;
    
            try {
                a = Employee.find.byId(id).getAddress();
                e = Employee.find.byId(id);
                employeeForm = formFactory.form(Employee.class).fill(e);
                addressForm = formFactory.form(Address.class).fill(a);
            } 
            catch (Exception ex) {
                return badRequest("error");
            }
            return ok(updateEmployee.render(id, employeeForm, addressForm, User.getUserById(session().get("email"))));
        }
        public Result updateEmployeeSubmit(Long id) {
        
            // Retrieve the submitted form object (bind from the HTTP request)
            Form<Address> updateAddressForm = formFactory.form(Address.class).bindFromRequest();
            Form<Employee> updateEmployeeForm = formFactory.form(Employee.class).bindFromRequest();
                List<Project> newProjs = new ArrayList<Project>();
                List<Employee> newEmps = new ArrayList<Employee>();
    
            // Check for errors (based on constraints set in the Employee class)
            if (updateEmployeeForm.hasErrors() || updateAddressForm.hasErrors()) {
                // Display the form again by returning a bad request
                return badRequest(updateEmployee.render(id,updateEmployeeForm, updateAddressForm, User.getUserById(session().get("email"))));
            } else {
                // No errors found - extract the employee detail from the form
                Employee e = updateEmployeeForm.get();
                Address a = updateAddressForm.get();
                e.setId(id);
                a.setId(Employee.find.byId(id).getAddress().getId());
                
                
                for (Long proj : e.getProjSelect()) {
                    newProjs.add(Project.find.byId(proj));
                    newEmps.add(Employee.find.byId(id));
                    
                }
                e.setProjects(newProjs);
                
                
                //update (save) this employee
                a.update();
                e.update();
    
                MultipartFormData data = request().body().asMultipartFormData();
                FilePart<File> image = data.getFile("upload");
    
                String saveImageMsg = saveFile(e.getId(), image);
    
                flash("success", "Employee " + e.getFname() +" "+ e.getLname() + " has been  updated " + saveImageMsg);
                
                // Redirect to the index page
                return redirect(controllers.routes.HomeController.index(0));
            }
        }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    public Result addProject() {
        Form<Project> projectForm = formFactory.form(Project.class);
        return ok(addProject.render(projectForm, User.getUserById(session().get("email"))));
    }
    public Result addProjectSubmit() {
        Project newProject;
        Form<Project> newProjectForm = formFactory.form(Project.class).bindFromRequest();

        if (newProjectForm.hasErrors()) {
            return badRequest(addProject.render(newProjectForm, User.getUserById(session().get("email"))));
        }
        else {
            newProject = newProjectForm.get();

            if (newProject.getId() == null) {
                newProject.save();    
                flash("success", "Project " + newProject.getName() + " was added");
                
            }
            else if (newProject.getId() != null) {
                newProject.update();
                flash("success", "Project " + newProject.getName() + " was updated");
            }
        }

        MultipartFormData data = request().body().asMultipartFormData();
        FilePart<File> image = data.getFile("upload");

        String saveImageMsg = saveFile(newProject.getId(), image);

        flash("success", "Project " + newProject.getName() + " has been created/updated " + saveImageMsg);

        return redirect(controllers.routes.HomeController.index(0));
    }
    
    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result deleteProject(Long id) {
        Project.find.ref(id).delete();

        flash("success", "Project has been deleted");
        
        return redirect(routes.HomeController.index(0));
    }
    
    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result updateProject(Long id) {
        Project p;
        Form<Project> projectForm;

        try {
            p = Project.find.byId(id);
            projectForm = formFactory.form(Project.class).fill(p);
        } 
        catch (Exception ex) {
            return badRequest("error");
        }
        return ok(addProject.render(projectForm,User.getUserById(session().get("email"))));
    }
    
    public String saveFile(Long id, FilePart<File> uploaded) {
        if (uploaded != null) {
            // make sure that the content is indeed an image
            String mimeType = uploaded.getContentType(); 
            if (mimeType.startsWith("image/")) {
                // get the file name
                String fileName = uploaded.getFilename();                
                // save the file object (created without a path, File saves
                // the content to a default location, usually the temp or tmp
                // directory)
                File file = uploaded.getFile();
                // create an ImageMagik operation - this object is used to specify
                // the required image processing
                IMOperation op = new IMOperation();
                // add the uploaded image to the operationop.addImage(file.getAbsolutePath());
                op.addImage(file.getAbsolutePath());
                // resize the image using height and width saveFileOld(Long id, FilePart<File> uploaded) {
                op.resize(300, 200);
                // save the image as jpg 
                op.addImage("public/images/projectImages/" + id + ".jpg");
                // create another Image Magick operation and repeat the process above to
                // specify how a thumbnail image should be processed - size 60px
                IMOperation thumb = new IMOperation();
                thumb.addImage(file.getAbsolutePath());
                thumb.resize(60);
                thumb.addImage("public/images/projectImages/thumbnails/" + id + ".jpg");
                // we must make sure that the directories exist before running the operations
                File dir = new File("public/images/projectImages/thumbnails/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // now we create an Image Magick command and execute the operations
                ConvertCmd cmd = new ConvertCmd();
                try {
                    cmd.run(op);
                    cmd.run(thumb);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return " and image saved";
            }
        }
        return "/ no file";
    }
    public String saveFileOld(Long id, FilePart<File> uploaded) {
        // make sure that the file exists
        String mimeType = uploaded.getContentType(); 
        if (uploaded != null) {
            // make sure that the content is indeed an image
            if (mimeType.startsWith("image/")) {
                // get the file name
                String fileName = uploaded.getFilename();      
                String extension = "";
                int i = fileName.lastIndexOf('.');
                if (i >= 0) {
                    extension = fileName.substring(i+1);
                }
                // save the file object (created without a path, File saves
                // the content to a default location, usually the temp or tmp
                // directory)
                File file = uploaded.getFile();
                // we must make sure that the directory for the images exists before we save it
                File dir = new File("public/images/projectImages");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // move the file to the required location (in a real application 
                // the path to where images are stored would be configurable, but 
                // for the lab we just hard-code it)
                if(file.renameTo(new File("public/images/projectImages/", id + "." + extension))) {
                    return "/ file uploaded";
                } else {
                    return "/ file upload failed";
                }
            }
        }
        return "/ no file";
    }
    public Result projectDetails(Long id) {
        Project p;

        p = Project.find.byId(id);
            
        return ok(projectDetails.render(p,User.getUserById(session().get("email")),e));
    }

}
