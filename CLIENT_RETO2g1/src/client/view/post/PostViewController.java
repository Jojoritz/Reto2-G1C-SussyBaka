/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.post;

import client.beans.Comment;
import client.beans.Course;
import client.beans.Post;
import client.beans.enumerations.FilterTypes;
import client.beans.enumerations.UserPrivilege;
import client.logic.CommentController;
import client.logic.ControllerFactory;
import client.logic.PostController;
import client.logic.exception.BusinessLogicException;
import client.view.components.GenericController;
import client.view.components.MenuBarController;
import client.view.customNodes.EditingDateCell;
import client.view.customNodes.EditingStringCell;
import client.view.signUp.SignUpViewController;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.ws.rs.core.GenericType;
import jfxtras.scene.control.CalendarTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class for the post View
 *
 * @author Henri
 */
public class PostViewController extends GenericController {
    
    @FXML
    private TableView<Post> postTable;
    @FXML
    private TableColumn<Post, String> titleColumn;
    @FXML
    private TableColumn<Post, Date> dateColumn;
    @FXML
    private TableColumn<Post, String> linkColumn;
    @FXML
    private TableColumn<Post, String> contentColumn;
    @FXML
    private TableColumn<Post, String> imgColumn;
    @FXML
    private Button btnPostEnter;
    @FXML
    private Button btnPostSave;
    @FXML
    private Button btnPostAdd;
    @FXML
    private Button btnPostDelete;
    @FXML
    private Button btnPostCancel;
    @FXML
    private Button btnPostBack;
    @FXML
    private Button btnPostPrint;
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<FilterTypes> cmbxFilter;
    @FXML
    private TextField filterText;
    @FXML
    private CalendarTextField filterDate;
    @FXML
    private CalendarTextField filterDateRange;
    @FXML
    private MenuItem btnMenuEnter;
    @FXML
    private MenuItem btnMenuCopy;
    @FXML
    private MenuItem btnMenuPaste;
    @FXML
    private MenuItem btnMenuDelete;
    @FXML
    private MenuItem btnMenuAdd;
    @FXML
    private MenuItem btnMenuRefresh;
    
    private final MenuBarController menuBarController = new MenuBarController();

    /**
     * ID of the course
     */
    private Integer idCourse;
    /**
     * Post controller interface
     */
    private PostController postController;
    /**
     * The logger of this class
     */
    private static final Logger LOG = Logger.getLogger(SignUpViewController.class.getName());
    /**
     * Post original post list
     */
    private List<Post> postList;
    /**
     * Post copy list
     */
    private List<Post> copyList;
    /**
     * Date formatter object
     */
    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Initializes the controller class.
     *
     * @param root Parent object
     * @param postController Interface of the post
     * @param primaryStage Primary stage
     * @param postList List with all the post of the course
     * @param courseId The course ID
     */
    public void initStage(Parent root, PostController postController, Stage primaryStage, List<Post> postList, Integer courseId) {
        scene = new Scene(root);
        stage = new Stage();
        this.postController = postController;
        this.primaryStage = primaryStage;
        this.idCourse = courseId;

        // Copy of the post List
        if (!postList.isEmpty()) {
            this.copyList = copyArray(postList);
            this.postList = postList;
        }
        
        stage.setOnShowing((event) -> {
            LOG.info("Starting POST window and setting the components on the screen");
            stage.setTitle("Publicaciones");
            stage.setScene(scene);
            stage.setResizable(false);
            menuBarController.setStage(stage);
            menuBarController.setHelpHtml("/client/view/post/help/PostViewHelp.html");

            // States of the button when view is showing
            btnPostEnter.setDisable(true);
            btnPostSave.setDisable(true);
            btnPostDelete.setDisable(true);
            btnPostCancel.setDisable(true);
            btnPostPrint.setDisable(postList.isEmpty());
            btnPostBack.setDisable(false);
            btnMenuRefresh.setDisable(false);

            // Filter state
            filterText.setDisable(true);
            filterDate.setDisable(true);
            filterDate.setShowTime(false);
            filterDate.dateFormatProperty().set(FORMATTER);
            filterDateRange.setDisable(true);
            filterDateRange.setShowTime(false);
            filterDateRange.dateFormatProperty().set(FORMATTER);
            btnBuscar.setDisable(true);
            
            if (!postList.isEmpty()) {
                postTable.setItems(getItems(postList));
            }
            
            if (user.getPrivilege().equals(UserPrivilege.STUDENT)) {
                btnPostAdd.setDisable(true);
                postTable.setEditable(false);
            } else {
                btnPostAdd.setDisable(false);
            }

            // Enable only select cell instead row
            postTable.getSelectionModel().setCellSelectionEnabled(true);

            // Add type on the filter
            cmbxFilter.setItems(FXCollections.observableArrayList(
                    FilterTypes.NINGUNO, FilterTypes.TITULO,
                    FilterTypes.FECHA, FilterTypes.RANGO_FECHA));
        });

        // Callbacks
        Callback<TableColumn<Post, String>, TableCell<Post, String>> stringCellFactory255
                = (TableColumn<Post, String> p) -> new EditingStringCell(255);
        Callback<TableColumn<Post, String>, TableCell<Post, String>> stringCellFactory65535
                = (TableColumn<Post, String> p) -> new EditingStringCell(65535);
        Callback<TableColumn<Post, Date>, TableCell<Post, Date>> dateCellFactory
                = (TableColumn<Post, Date> p) -> new EditingDateCell();

        // Title column
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(stringCellFactory255);
        titleColumn.setOnEditCommit(
                (CellEditEvent<Post, String> t) -> {
                    ((Post) t.getTableView().getItems().get(
                            t.getTablePosition().getRow()))
                            .setTitle(t.getNewValue());
                    updateButtonDisableStates(false);
                    
                });

        // Date Column
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        dateColumn.setCellFactory(dateCellFactory);
        dateColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Post, Date> t) -> {
                    ((Post) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setPublicationDate(t.getNewValue());
                    updateButtonDisableStates(false);
                });

        // Content column
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        contentColumn.setCellFactory(stringCellFactory65535);
        contentColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Post, String> t) -> {
                    ((Post) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setContent(t.getNewValue());
                    updateButtonDisableStates(false);
                    
                });

        // YT link Column
        linkColumn.setCellValueFactory(new PropertyValueFactory<>("video"));
        linkColumn.setCellFactory(stringCellFactory255);
        linkColumn.setOnEditCommit(
                (CellEditEvent<Post, String> t) -> {
                    ((Post) t.getTableView().getItems().get(
                            t.getTablePosition().getRow()))
                            .setVideo(t.getNewValue());
                    updateButtonDisableStates(false);
                });

        // Img link column
        imgColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        imgColumn.setCellFactory(stringCellFactory255);
        imgColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Post, String> t) -> {
                    ((Post) t.getTableView().getItems().get(
                            t.getTablePosition().getRow()))
                            .setImage(t.getNewValue());
                    updateButtonDisableStates(false);
                });

        // Select row on table
        postTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        btnPostEnter.setDisable(false);
                        btnPostDelete.setDisable(false);
                    } else {
                        btnPostEnter.setDisable(true);
                        btnPostDelete.setDisable(true);
                    }
                });

        // Post listener when right click
        postTable.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    if (getSelectedCell() != null) {
                        if (user.getPrivilege().equals(UserPrivilege.STUDENT)) {
                            btnMenuPaste.setDisable(true);
                            btnMenuAdd.setDisable(true);
                            btnMenuDelete.setDisable(true);
                        } else { // User is privilege is different of student (admin, teacher)
                            btnMenuCopy.setDisable(false);
                            btnMenuDelete.setDisable(false);
                            if (((String) clipboard.getContent(DataFormat.PLAIN_TEXT)) != null) {
                                btnMenuPaste.setDisable(false);
                            }
                        }
                        btnMenuEnter.setDisable(false);
                    }
                } catch (Exception ex) {
                    LOG.info(ex.getMessage());
                    btnMenuDelete.setDisable(true);
                    btnMenuPaste.setDisable(true);
                    btnMenuCopy.setDisable(true);
                    btnMenuEnter.setDisable(true);
                    if (user.getPrivilege().equals(UserPrivilege.STUDENT)) {
                        btnMenuAdd.setDisable(true);
                    }
                    
                }
                
            }
        });
// Listener when combo box changes 
        cmbxFilter.getSelectionModel().selectedItemProperty().addListener(
                (options, oldValue, newValue) -> {
                    switch (newValue) {
                        case TITULO:
                            filterText.setDisable(false);
                            filterDate.setDisable(true);
                            filterDateRange.setDisable(true);
                            break;
                        case FECHA:
                            filterText.setDisable(true);
                            filterDate.setDisable(false);
                            filterDateRange.setDisable(true);
                            break;
                        case RANGO_FECHA:
                            filterText.setDisable(true);
                            filterDate.setDisable(false);
                            filterDateRange.setDisable(false);
                            break;
                        default:
                            filterText.setDisable(true);
                            filterDate.setDisable(true);
                            filterDateRange.setDisable(true);
                            btnBuscar.setDisable(false);
                            break;
                    }
                });
        // Validate the text field for the filter
        filterText.textProperty().addListener(observable -> {
            try {
                String text = filterText.getText();
                if (text.length() == 0) {
                    throw new Exception("No puedes filtrar con un texto vacio");
                } else if (text.length() >= VARCHAR_LIMIT) {
                    throw new Exception("No puedes filtrar con mas de " + VARCHAR_LIMIT + " caracteres");
                } else {
                    btnBuscar.setDisable(false);
                }
                
            } catch (Exception e) {
                showTooltip(stage, filterText, e.getMessage(), tooltip);
                btnBuscar.setDisable(true);
            }
            
        });
        // Validate the dates of the filter
        filterDate.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                btnBuscar.setDisable(false);
            }
        });
        // Date end can't be before date start
        filterDateRange.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                if (!filterDate.getText().isEmpty()) {
                    if (filterDate.getCalendar().after(filterDateRange.getCalendar())) {
                        btnBuscar.setDisable(true);
                        showTooltip(stage, filterDateRange, "La fecha fin tiene que ser despues de la fecha de inicio", tooltip);
                    } else {
                        btnBuscar.setDisable(false);
                    }
                }
            }
        });
        // Show error when user manually inserts a date and its not formatted correctly
        filterDate.setParseErrorCallback((throwable) -> {
            showAlert("No se puede formatear esa fecha, por favor introduzca la fecha en formato año/mes/dia", Alert.AlertType.ERROR);
            return null;
        });

        //Buttons properties
        btnPostSave.setOnAction(event -> {
            try {
                LOG.info("Saving modified data, sending them to the server");

                // Get only the modified elements from the table
                List<Post> modifiedList = postTable.getItems().stream()
                        .filter(p1 -> !copyList.stream()
                        .anyMatch(p2 -> p2.equals(p1)))
                        .collect(Collectors.toList());
                for (Post post : modifiedList) {
                    postController.edit(post);
                }
                // After commit edit, copy modified list from table to both copy and original list
                this.copyList = copyArray(postTable.getItems());
                this.postList = copyArray(postTable.getItems());
                showAlert("Se han guardado los cambios correctamente", Alert.AlertType.INFORMATION);
                updateButtonDisableStates(true);
                LOG.info("Data saved succesfully");
            } catch (BusinessLogicException ex) {
                LOG.severe(ex.getMessage());
                showAlert(ex.getMessage(), Alert.AlertType.ERROR);
            }
            
        });
        btnPostDelete.setOnAction(this::deletePost);
        btnPostBack.setOnAction(event -> {
            try {
                if (menuBarController.getEdited()) {
                    openViewWithoutSaving();
                    throw new Exception("User has to save or discard changes before changing view");
                }
                LOG.info("Closing the window");
                stage.close();
                menuBarController.setEdited(false);
                primaryStage.show();
            } catch (Exception e) {
                LOG.info(e.getMessage());
            }
        });
        btnPostCancel.setOnAction(event -> {
            LOG.info("Cancel the edited table from commiting to the server");
            postTable.setItems(getItems(copyArray(copyList)));
            postTable.refresh();
            updateButtonDisableStates(true);
        });
        btnPostAdd.setOnAction(this::addPost);
        btnPostEnter.setOnAction(this::commentView);
        btnPostPrint.setOnAction(event -> {
            try {
                if (postList.isEmpty()) {
                    throw new JRException("No se puede imprimir los datos de una tabla vacia");
                }
                JasperReport report
                        = JasperCompileManager.compileReport(getClass()
                                .getResourceAsStream("/client/view/post/PostReport.jrxml"));
                JRBeanCollectionDataSource dataItems
                        = new JRBeanCollectionDataSource((List<Post>) this.postTable.getItems());
                Map<String, Object> parameters = new HashMap<>();
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);
                
            } catch (JRException e) {
                LOG.severe(e.getMessage());
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }
        });
        btnBuscar.setOnAction(event -> {
            try {
                List<Post> filterPostList = new ArrayList<>();
                switch (cmbxFilter.getValue()) {
                    case TITULO:
                        filterPostList = postController.findByTitle(new GenericType<List<Post>>() {
                        }, courseId.toString(), filterText.getText());
                        LOG.info(String.format("Searching with the title: %s", filterText.getText()));
                        break;
                    case FECHA:
                        filterPostList = postController.findByDate(new GenericType<List<Post>>() {
                        }, courseId.toString(), FORMATTER.format(filterDate.getCalendar().getTime()));
                        LOG.info(String.format("Searching with date: %s", filterDate.getCalendar().getTime()));
                        break;
                    case RANGO_FECHA:
                        filterPostList = postController.findByDateRange(new GenericType<List<Post>>() {
                        }, courseId.toString(), FORMATTER.format(filterDate.getCalendar().getTime()),
                                FORMATTER.format(filterDateRange.getCalendar().getTime()));
                        LOG.info(String.format("Searching with date range from: %s to: %s",
                                filterDate.getCalendar().getTime(), filterDateRange.getCalendar().getTime()));
                        break;
                    default:
                        discardEditTable();
                        LOG.info("No filter, setting table with all posts");
                        break;
                }
                if (!cmbxFilter.getValue().equals(FilterTypes.NINGUNO)) {
                    postTable.setItems(getItems(filterPostList));
                    postTable.refresh();
                }
                
            } catch (BusinessLogicException e) {
                LOG.severe(e.getMessage());
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            } catch (Exception ex) {
                LOG.info(ex.getMessage());
            }
        });

        // Close alert
        stage.setOnCloseRequest(
                (WindowEvent windowEvent) -> {
                    LOG.info("Opening exit alert confirmation");
                    showAlert("¿Quieres cerrar el programa?", Alert.AlertType.CONFIRMATION);
                    
                    if (alert.getResult().equals(ButtonType.YES)) {
                        LOG.info("Closing the application");
                        Platform.exit();
                    } else {
                        LOG.info("Canceled application close");
                        windowEvent.consume();
                    }
                }
        );
        primaryStage.hide();
        stage.show();
    }

    /**
     * From a list of post creates an observable list
     *
     * @param postList The post list to create a observable list of that
     * @return The observable list of the posts
     */
    private ObservableList<Post> getItems(List<Post> postList) {
        ObservableList<Post> postItems = FXCollections.observableArrayList(postList);
        return postItems;
    }

    /**
     * This method will create new objects from a list, so if there is some
     * changes any object there will be a list with the unchanged data.
     *
     * This is done in this way because if you copy like this:
     * {@code copyList = originalList} this will make a reference to the
     * originalList and any changes will be done in both variables because both
     * are pointing to the same list.
     *
     * @param original List that will be copied
     * @return Returns a list with the same data of the original list
     */
    private List<Post> copyArray(List<Post> original) {
        List<Post> copy = new ArrayList<>();
        original.stream().forEach(p -> copy.add(
                new Post(p.getPostId(), p.getTitle(), p.getContent(),
                        p.getPublicationDate(), p.getImage(), p.getVideo(), p.getCourse())));
        
        return copy;
    }

    /**
     * Returns the post object of the selected row in the table
     *
     * @return Post object of the selected row
     */
    private Post getSelected() {
        return postTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Updates the states of the buttons when a cell is edited
     *
     * @param bool passes the boolean for the state of the buttons
     */
    private void updateButtonDisableStates(Boolean bool) {
        btnPostSave.setDisable(bool);
        btnPostCancel.setDisable(bool);
        menuBarController.setEdited(!bool);
    }
    
    private void openViewWithoutSaving() {
        showAlert("No has guardado los cambios que has hecho a la tabla, "
                + "\nTienes que guardar o descartar los cambios para salir", Alert.AlertType.INFORMATION);
    }

    /**
     * This method returns a string value of the table selected cell
     *
     * @throws Exception It throws an exception if the selected cell is
     * {@code NULL}
     * @return the selected table cell value
     */
    private String getSelectedCell() throws Exception {
        try {
            TablePosition pos = postTable.getSelectionModel().getSelectedCells().get(0);
            TableColumn col = pos.getTableColumn();
            Post item = getSelected();
            Object cell = col.getCellObservableValue(item).getValue();
            String cellString;
            if (cell instanceof Date) {
                final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.systemDefault());
                cellString = ((Date) cell).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateFormat);
            } else {
                cellString = ((String) cell);
            }
            return cellString;
        } catch (Exception e) {
            throw new Exception("Clicked on emtpy row");
        }
    }

    /**
     * Deletes the row of post that is currently selected
     *
     * @param event Event thrown (its not used in this method)
     */
    private void deletePost(ActionEvent event) {
        showAlert("¿Estas seguro de que quieres eliminar esta publicacion?", Alert.AlertType.CONFIRMATION);
        if (alert.getResult().equals(ButtonType.YES)) {
            LOG.info("Deleting row from table and from the server");
            try {
                Post postDelete = getSelected();
                postController.remove(postDelete.getPostId().toString());
                postTable.getItems().remove(postDelete);
                LOG.info("Row deleted successfully from the table and the server");
            } catch (BusinessLogicException ex) {
                LOG.severe(ex.getMessage());
                showAlert(ex.getMessage(), Alert.AlertType.ERROR);
                
            }
        }
    }

    /**
     * Adds a row with defaults value and it will send that data to the server
     *
     * @param event Event thrown (its not used in this method)
     */
    private void addPost(ActionEvent event) {
        try {
            LOG.info("Creating new post with default values...");
            Course newCourse = new Course();
            newCourse.setCourseId(this.idCourse);
            Post newPost = new Post(null, "Inserte un titulo" + LocalDateTime.now(), "Dolore officia et aut aut quos dolor. "
                    + "Culpa voluptatum non harum voluptatem quia et quo.",
                    new Date(), null, null, newCourse);
            postController.create(newPost);
            btnPostPrint.setDisable(false);
            menuRefresh(event);
            LOG.info("New Post with default values created succesfully");
        } catch (BusinessLogicException e) {
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * This method discards any modification of the table, getting a new list
     * from the server and setting it to the table view.
     */
    private void discardEditTable() {
        try {
            this.postList = postController.getCoursePosts(new GenericType<ArrayList<Post>>() {
            }, idCourse.toString());
            postTable.setItems(getItems(postList));
            postTable.refresh();
            btnPostSave.setDisable(true);
            btnPostCancel.setDisable(true);
            menuBarController.setEdited(false);
        } catch (BusinessLogicException e) {
            LOG.severe(e.getMessage());
        }
    }

    /**
     * Opens the {@link PostContentViewController} with the data of a post
     *
     * @param event Event thrown (its not used in this method)
     */
    private void commentView(ActionEvent event) {
        try {
            if (menuBarController.getEdited()) {
                openViewWithoutSaving();
                throw new Exception("User has to save or discard changes before changing view");
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostContentView.fxml"));
            Parent rootPostContentView = (Parent) loader.load();
            PostContentViewController postContent = ((PostContentViewController) loader.getController());
            Post post = getSelected();
            menuBarController.setEdited(false);
            CommentController commentController = ControllerFactory.getCommentController();
            postContent.initStage(rootPostContentView, postController, commentController, this.stage,
                    commentController.findAll(new GenericType<ArrayList<Comment>>() {
                    }, post.getPostId().toString()), post);
        } catch (IOException | BusinessLogicException e) {
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception ex) {
            LOG.info(ex.getMessage());
        }
    }

    /**
     * This is the method that calls {@link #commentView(javafx.event.ActionEvent)
     * }, this method its for the context menu.
     *
     * @param event Event thrown (its not used in this method)
     */
    @FXML
    private void menuEnter(ActionEvent event) {
        commentView(event);
    }

    /**
     * This context menu option will copy any string (if its not NULL) from the
     * current selected cell from the table
     *
     * @param event Event thrown (its not used in this method)
     */
    @FXML
    private void menuCopy(ActionEvent event) {
        try {
            ClipboardContent content = new ClipboardContent();
            content.putString(getSelectedCell());
            clipboard.setContent(content);
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            showAlert("No se ha podido copiar el contenido de la celda", Alert.AlertType.ERROR);
        }
    }

    /**
     * This context menu option will paste the string saved in the system
     * clipboard if its not {@code NULL or empty}
     *
     * @param event Event thrown (its not used in this method)
     */
    @FXML
    private void menuPaste(ActionEvent event) {
        try {
            boolean modified = false;
            String content = (String) clipboard.getContent(DataFormat.PLAIN_TEXT);
            
            TablePosition selectedCell = postTable.getSelectionModel().getSelectedCells().get(0);
            int col = selectedCell.getColumn();
            TableColumn column = postTable.getColumns().get(col);
            Post item = getSelected();
            if (column.getText().equalsIgnoreCase("titulo")) {
                item.setTitle(content);
                modified = true;
            } else if (column.getText().equalsIgnoreCase("yt link")) {
                item.setVideo(content);
                modified = true;
            } else {
                try {
                    final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.systemDefault());
                    LocalDateTime dateTime = LocalDateTime.parse(content, dateFormat);
                    item.setPublicationDate(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
                    modified = true;
                } catch (Exception e) {
                    modified = false;
                    LOG.info(e.getMessage() + " \nPasted item is a text, if it was date it wasn't formatted properly");
                }
            }
            if (modified) {
                btnPostSave.setDisable(false);
                btnPostCancel.setDisable(false);
            }
            postTable.refresh();
        } catch (Exception e) {
            LOG.info(e.getMessage() + " Rolling back any modification to the table");
            showAlert("Rolling back any modification to the table", Alert.AlertType.INFORMATION);
            postTable.setItems(getItems(copyArray(copyList)));
            postTable.refresh();
            
        }
    }

    /**
     * This context menu adds a new post calling the method {@link #addPost(javafx.event.ActionEvent)
     * }
     *
     * @param event Event thrown (its not used in this method)
     */
    @FXML
    private void menuAdd(ActionEvent event) {
        addPost(event);
    }

    /**
     * This context menu deletes de selected post row, calling the method {@link #deletePost(javafx.event.ActionEvent)
     * }
     *
     * @param event Event thrown (its not used in this method)
     */
    @FXML
    private void menuDelete(ActionEvent event) {
        deletePost(event);
    }

    /**
     * This context menu refreshes the table by getting again the list from the
     * server, and setting it to the table
     *
     * @param event Event thrown (its not used in this method)
     */
    @FXML
    private void menuRefresh(ActionEvent event) {
        discardEditTable();
    }
    
}
