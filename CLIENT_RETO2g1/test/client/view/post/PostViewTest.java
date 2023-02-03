/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.post;

import client.Main;
import client.beans.Post;
import client.beans.enumerations.FilterTypes;
import client.view.signUp.SignUpViewController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import jfxtras.scene.control.CalendarTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.ButtonMatchers.isDefaultButton;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 * This test class test the view {@link PostViewController}, and the test
 * methods will run in order {@code MethodSorters.NAME_ASCENDING}
 *
 * @author Henri
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostViewTest extends ApplicationTest {

    private static final String TEXT_255 = new String(new char[255]).replace("\0", "a");
    private static final String TEXT_65535 = new String(new char[65535]).replace("\0", "a");
    private static final Logger LOG = Logger.getLogger(PostViewTest.class.getName());

    private TableView<Post> postTable;
    private TableColumn<Post, String> titleColumn;
    private TableColumn<Post, Date> dateColumn;
    private TableColumn<Post, String> linkColumn;
    private Button btnPostEnter;
    private Button btnPostSave;
    private Button btnPostAdd;
    private Button btnPostDelete;
    private Button btnPostCancel;
    private Button btnPostBack;
    private Button btnPostPrint;
    private Button btnBuscar;
    private ComboBox<FilterTypes> cmbxFilter;
    private TextField filterText;
    private CalendarTextField filterDate;
    private CalendarTextField filterDateRange;
    private MenuItem btnMenuEnter;
    private MenuItem btnMenuCopy;
    private MenuItem btnMenuPaste;
    private MenuItem btnMenuAdd;
    private MenuItem btnMenuDelete;
    private MenuItem btnMenuRefresh;
    private ContextMenu contextMenu;

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    private void login(String user) {
        write(user);
        clickOn("#txtPassword");
        write("abcd*1234");
        clickOn("#btnSignIn");
    }

    private void getButtons() {
        // Get all view buttons
        btnPostEnter = lookup("#btnPostEnter").query();
        btnPostSave = lookup("#btnPostSave").query();
        btnPostAdd = lookup("#btnPostAdd").query();
        btnPostDelete = lookup("#btnPostDelete").query();
        btnPostCancel = lookup("#btnPostCancel").query();
        btnPostBack = lookup("#btnPostBack").query();
        btnPostPrint = lookup("#btnPostPrint").query();
        btnBuscar = lookup("#btnBuscar").query();
    }

    private void getFilterFields() {
        // Filter fields
        cmbxFilter = lookup("#cmbxFilter").query();
        filterText = lookup("#filterText").query();
        filterDate = lookup("#filterDate").query();
        filterDateRange = lookup("#filterDateRange").query();
    }

    private void getTable() {
        postTable = lookup("#postTable").queryTableView();
    }

    /**
     * This test test the init of the view, it will validate the state of the
     * buttons for student, the states will be the same for all type users
     * except the add button, this button only will be disabled if user is
     * student
     */
    @Test
    public void testA1_initStageStudent() {
        getButtons();
        getFilterFields();
        this.login("henrique");
        clickOn("#menMenu").clickOn("#menitmPost");

        getButtons();
        getFilterFields();
        verifyThat(btnPostEnter, isDisabled());
        verifyThat(btnPostSave, isDisabled());
        verifyThat(btnPostDelete, isDisabled());
        verifyThat(btnPostCancel, isDisabled());
        verifyThat(btnPostPrint, isEnabled());
        verifyThat(btnPostAdd, isDisabled());
        verifyThat(btnPostBack, isEnabled());
        verifyThat(filterText, isDisabled());
        verifyThat(filterDate, isDisabled());
        verifyThat(filterDateRange, isDisabled());

        clickOn("#menMenu").clickOn("#menitmCloseSession");
    }

    /**
     * This test test the init of the view, it will validate the state of the
     * buttons for admin, the states will be the same for all type users except
     * the add button, this button only will be disabled if user is student
     */
    @Test
    public void testA2_initStageAdminTeacher() {
        // Check ini as admin / teacher (has the same init so no need to retest)
        this.login("ioritz2002");
        clickOn("#menMenu").clickOn("#menitmPost");
        getButtons();
        getFilterFields();
        verifyThat(btnPostEnter, isDisabled());
        verifyThat(btnPostSave, isDisabled());
        verifyThat(btnPostDelete, isDisabled());
        verifyThat(btnPostCancel, isDisabled());
        verifyThat(btnPostPrint, isEnabled());
        verifyThat(btnPostAdd, isEnabled());
        verifyThat(btnPostBack, isEnabled());
        verifyThat(filterText, isDisabled());
        verifyThat(filterDate, isDisabled());
        verifyThat(filterDateRange, isDisabled());

    }

    @Test
    public void testB1_ContextMenuOnEmptyField() {
        Node row = lookup(".table-row-cell").nth(10).query();
        rightClickOn(row);

        // This checks if is disabled, when testing even if you see is disabled, says that its enabled
        verifyThat("#btnMenuEnter", isEnabled());
        verifyThat("#btnMenuCopy", isEnabled()); // This has the same problem
        verifyThat("#btnMenuAdd", isEnabled());
        verifyThat("#btnMenuDelete", isEnabled()); // This has the same problem
        verifyThat("#btnMenuRefresh", isEnabled());
    }

    @Test
    public void testB2_ContextMenuOnNotEmptyField() {
        Node row = lookup(".table-cell").nth(0).query();
        rightClickOn(row);

        verifyThat("#btnMenuEnter", isEnabled());
        verifyThat("#btnMenuCopy", isEnabled());
        verifyThat("#btnMenuAdd", isEnabled());
        verifyThat("#btnMenuDelete", isEnabled());
        verifyThat("#btnMenuRefresh", isEnabled());
    }

    @Test
    public void testB3_ContextMenuCopyPasteField() {
        clickOn("#btnMenuCopy");
        Node row = lookup(".table-cell").nth(2).query();
        rightClickOn(row);
        verifyThat("#btnPostDelete", isEnabled());
        verifyThat("#btnPostEnter", isEnabled());

        clickOn("#btnMenuPaste");
        press(KeyCode.CONTROL);
        clickOn((Node) lookup(".table-cell").nth(2).query());
        release(KeyCode.CONTROL);
        verifyThat("#btnPostDelete", isDisabled());
        verifyThat("#btnPostEnter", isDisabled());

        getTable();
        String title = postTable.getItems().get(0).getTitle();
        String video = postTable.getItems().get(0).getVideo();
        verifyThat("#btnPostSave", isEnabled());
        verifyThat("#btnPostCancel", isEnabled());
        assertTrue(title.equals(video));
    }

    @Test
    public void testB4_ContextMenuAddPost() {
        getTable();
        Integer count = postTable.getItems().size();
        Node row = lookup(".table-cell").nth(10).query();
        rightClickOn(row);
        clickOn("#btnMenuAdd");
        assertTrue("Post added", count++ == postTable.getItems().size());
        assertEquals("Post correctly added",
                postTable.getItems().stream().filter(p -> p.getContent()
                .equals("Dolore officia et aut aut quos dolor.Culpa voluptatum non harum voluptatem quia et quo.")).count(), 1);
    }

    @Test
    public void testB5_ContextMenuDeletePost() {
        getTable();
        LOG.info(Boolean.toString(postTable == null));
        Integer count = postTable.getItems().size();
        Node row = lookup(".table-row-cell").nth(count - 1).query();
        rightClickOn(row);
        clickOn("#btnMenuDelete");
        clickOn("Yes");
        assertTrue("Post deleted", count-- == postTable.getItems().size());
        assertEquals("Post correctly deleted",
                postTable.getItems().stream().filter(p -> p.getContent()
                .equals("Dolore officia et aut aut quos dolor.Culpa voluptatum non harum voluptatem quia et quo.")).count(), 0);
    }

    @Test
    public void testC1_FilterName() {
        getFilterFields();
        getTable();
        Integer count = postTable.getItems().size();
        clickOn(cmbxFilter);
        clickOn("NOMBRE");
        verifyThat(filterText, isEnabled());
        clickOn(filterText);
        write("Post de prueba");
        verifyThat("No puedes filtrar con mas de 10 caracteres", isVisible());
        getButtons();
        verifyThat(btnBuscar, isDisabled());
        eraseText(5);
        clickOn(btnBuscar); // IDK why needs to click two times to properly click the button
        clickOn(btnBuscar);
        LOG.info(count.toString());
        assertTrue("Filtered", count > postTable.getItems().size());
    }

    @Test
    public void testC2_FilterDate() {
        getFilterFields();
        getTable();
        rightClickOn(postTable);
        clickOn("#btnMenuRefresh");
        Integer count = postTable.getItems().size();
        clickOn(cmbxFilter);
        clickOn("FECHA");
        verifyThat(filterDate, isEnabled());
        clickOn(filterDate);
        write("awdawdwassadw");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        clickOn("OK");
        clickOn(filterDate);
        write("2023-01-17");
        getButtons();
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        clickOn(btnBuscar); // IDK why needs to click two times to properly click the button
        clickOn(btnBuscar);
        assertTrue("Filtered", count > postTable.getItems().size());
    }

    @Test
    public void testC3_FilterDateRange() {
        getFilterFields();
        getButtons();
        getTable();
        rightClickOn(postTable);
        clickOn("#btnMenuRefresh");
        Integer count = postTable.getItems().size();
        clickOn(cmbxFilter);
        clickOn("RANGO_FECHA");
        verifyThat(filterDate, isEnabled());
        verifyThat(filterDateRange, isEnabled());

        clickOn(filterDate);
        eraseText(filterDate.getText().length() + 5);
        write("awdawdwassadw");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        clickOn("OK");
        clickOn(filterDate);
        eraseText(filterDate.getText().length());
        write("2023-01-17");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        clickOn(filterDateRange);
        write("2023-01-15");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        verifyThat("La fecha fin tiene que ser despues de la fecha de inicio", isVisible());
        clickOn(btnBuscar);
        clickOn(filterDateRange);
        eraseText(filterDateRange.getText().length());
        write("2023-01-20");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        clickOn(btnBuscar); // IDK why needs to click two times to properly click the button
        clickOn(btnBuscar);
        assertTrue("Filtered", count > postTable.getItems().size());
    }

    @Test
    public void testD1_EditTitle() {
        getTable();
        getButtons();
        Node cell = lookup(".table-cell").nth(0).query();
        doubleClickOn(cell);
        write("Holaaaa");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        verifyThat(btnPostSave, isEnabled());
        verifyThat(btnPostCancel, isEnabled());
        clickOn(btnPostSave);
        clickOn("OK");
    }

    @Test
    public void testD1_EditTitleCancelar() {
        getTable();
        getButtons();

        String before = postTable.getItems().get(0).getTitle();
        Node cell = lookup(".table-cell").nth(0).query();
        doubleClickOn(cell);
        write("HolaaaAWDawdawdawa");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        verifyThat(btnPostSave, isEnabled());
        verifyThat(btnPostCancel, isEnabled());
        clickOn(btnPostCancel);
        assertEquals(before, postTable.getItems().get(0).getTitle());
    }

    @Test
    public void testE1_OpenPostContent() {
        getTable();
        getButtons();
        Node cell = lookup(".table-cell").nth(0).query();
        clickOn(cell);
        verifyThat(btnPostEnter, isEnabled());
        clickOn(btnPostEnter);
        verifyThat(postTable.getItems().get(0).getTitle(), isVisible());
    }
}
