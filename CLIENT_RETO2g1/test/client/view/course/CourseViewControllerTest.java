/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.course;

import client.Main;
import client.beans.Course;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;

/**
 *
 * @author Joritz
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseViewControllerTest extends ApplicationTest {

    private TextField txtCourseName;
    private TextField txtCreatedDate;
    private ComboBox cmbxSubject;
    private ComboBox cmbxTeacher;
    private ComboBox cmbxFilter;
    private TableView<Course> tableCourses;
    private Button btnCreate;
    private Button btnModify;
    private Button btnDelete;
    private Button btnSearchCourse;
    private Course curso;

    public CourseViewControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    /**
     * Method to obtain the components to use in the test
     */
    private void getFields() {
        txtCourseName = lookup("#txtCourseName").query();
        txtCreatedDate = lookup("#txtCreatedDate").query();
        cmbxTeacher = lookup("#cmbxTeacher").query();
        cmbxSubject = lookup("#cmbxSubject").query();
        cmbxFilter = lookup("#cmbxFilter").query();
        tableCourses = lookup("#tableCourses").query();
        btnCreate = lookup("#btnCreate").query();
        btnModify = lookup("#btnModify").query();
        btnDelete = lookup("#btnDelete").query();
        btnSearchCourse = lookup("#btnSearchCourse").query();
    }

    /**
     * Test of initStage method, of class CourseViewController.
     */
    @Test
    public void testACreate_InitStage() {
        getFields();
        clickOn("#txtCourseName");
        write("Test");
        clickOn("#txtCreatedDate");
        write("23/11/2018");
        clickOn("#cmbxTeacher");
        type(KeyCode.DOWN);
        clickOn("#cmbxSubject");
        type(KeyCode.DOWN);

        verifyThat("#btnCreate", isDisabled());
        clickOn("#btnCreate");

        testBCheck_InitStage();
    }

    @Test
    public void testBCheck_InitStage() {
        tableCourses.getSelectionModel().select(0);
        curso = (Course) tableCourses.getSelectionModel().getSelectedItem();
        assertEquals(curso.getName(), "Test");
    }
}
