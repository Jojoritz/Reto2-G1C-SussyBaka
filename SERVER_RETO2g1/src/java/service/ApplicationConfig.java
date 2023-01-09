/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Set;
import javax.ws.rs.core.Application;

/*
 * @author Joritz
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /* Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(server.service.CommentFacadeREST.class);
        resources.add(server.service.CourseFacadeREST.class);
        resources.add(server.service.PostFacadeREST.class);
        resources.add(server.service.UserFacadeREST.class);
        resources.add(service.SubjectFacadeREST.class);
        resources.add(service.UserFacadeREST.class);
    }

}