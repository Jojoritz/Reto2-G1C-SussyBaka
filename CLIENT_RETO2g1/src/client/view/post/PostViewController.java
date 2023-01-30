/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.post;

import client.beans.Post;
import client.beans.User;
import client.beans.enumerations.FilterTypes;
import client.logic.PostController;
import client.logic.exception.BusinessLogicException;
import client.view.components.GenericController;
import client.view.components.MenuBarController;
import client.view.customNodes.EditingDateCell;
import client.view.customNodes.EditingStringCell;
import client.view.signUp.SignUpViewController;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.ws.rs.core.GenericType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
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
    private DatePicker filterDate;
    @FXML
    private DatePicker filterDateRange;
    @FXML
    private MenuItem btnMenuEnter;
    @FXML
    private MenuItem btnMenuCopy;
    @FXML
    private MenuItem btnMenuPaste;
    @FXML
    private MenuItem btnMenuDelete;
    @FXML
    private MenuBarController menuBarController = new MenuBarController();

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

    private List<Post> copyList;

    private List<Post> postList;

    /**
     * Initializes the controller class.
     *
     * @param root
     * @param postController
     * @param primaryStage
     * @param user
     * @param postList
     * @param courseId
     */
    public void initStage(Parent root, PostController postController, Stage primaryStage, User user, List<Post> postList, Integer courseId) {
        scene = new Scene(root);
        stage = new Stage();
        //primaryStage.hide();;

        // Copy of the post List
        this.copyList = copyArray(postList);
        this.postList = postList;
        this.idCourse = courseId;
        this.postController = postController;

        stage.setOnShowing((event) -> {
            LOG.info("Starting POST window and setting the components on the screen");
            stage.setTitle("Publicaciones");
            stage.setScene(scene);
            stage.setResizable(false);
            menuBarController.setStage(stage);

            // States of the button when view is showing
            btnPostEnter.setDisable(true);
            btnPostSave.setDisable(true);
            btnPostDelete.setDisable(true);
            btnPostCancel.setDisable(true);
            btnPostPrint.setDisable(copyList.isEmpty());
            btnPostAdd.setDisable(false);
            btnPostBack.setDisable(false);

            // Filter state
            filterText.setDisable(true);
            //TODO VALIDATE TEXT FIELD
            filterDate.setDisable(true);
            // TODO VALIDATE RANGE
            filterDateRange.setDisable(true);
            btnBuscar.setDisable(true);

            if (!copyList.isEmpty()) {
                postTable.setItems(getItems(copyList));
            }
            postTable.getSelectionModel().setCellSelectionEnabled(true);
            cmbxFilter.setItems(
                    FXCollections.observableArrayList(FilterTypes.NINGUNO, FilterTypes.NOMBRE, FilterTypes.FECHA, FilterTypes.RANGO_FECHA));
        });

        // Callbacks
        Callback<TableColumn<Post, String>, TableCell<Post, String>> stringCellFactory
                = (TableColumn<Post, String> p) -> new EditingStringCell();
        Callback<TableColumn<Post, Date>, TableCell<Post, Date>> dateCellFactory
                = (TableColumn<Post, Date> p) -> new EditingDateCell();

        // Title column
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(stringCellFactory);
        titleColumn.setOnEditCommit(
                (CellEditEvent<Post, String> t) -> {
                    ((Post) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTitle(t.getNewValue());
                    btnPostSave.setDisable(false);
                    btnPostCancel.setDisable(false);
                });

        // Date Column
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        dateColumn.setCellFactory(dateCellFactory);
        dateColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Post, Date> t) -> {
                    ((Post) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setPublicationDate(t.getNewValue());
                    btnPostSave.setDisable(false);
                    btnPostCancel.setDisable(false);
                });

        // YT link Column
        linkColumn.setCellValueFactory(new PropertyValueFactory<>("video"));
        linkColumn.setCellFactory(stringCellFactory);
        //HyperlinkCell.setHostServices(HyperlinkCell.getHostServices());
        linkColumn.setOnEditCommit(
                (CellEditEvent<Post, String> t) -> {
                    ((Post) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setVideo(t.getNewValue());
                    btnPostSave.setDisable(false);
                    btnPostCancel.setDisable(false);
                });

        // Select row on table
        postTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        btnPostEnter.setDisable(false);
                        btnPostDelete.setDisable(false);
                        //LOG.info(newValue.toString());
                    } else {
                        btnPostEnter.setDisable(true);
                        btnPostDelete.setDisable(true);
                    }
                });
        postTable.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    if (getSelectedCell() != null) {
                        btnMenuDelete.setDisable(false);
                        btnMenuPaste.setDisable(false);
                        btnMenuCopy.setDisable(false);
                        btnMenuEnter.setDisable(false);
                    }
                } catch (Exception ex) {
                    LOG.info(ex.getMessage());
                    btnMenuDelete.setDisable(true);
                    btnMenuPaste.setDisable(true);
                    btnMenuCopy.setDisable(true);
                    btnMenuEnter.setDisable(true);
                }
            }
        });

        cmbxFilter.getSelectionModel().selectedItemProperty().addListener(
                (options, oldValue, newValue) -> {
                    switch (newValue) {
                        case NOMBRE:
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
                            btnBuscar.setDisable(true);
                            break;
                    }
                });

        //Buttons properties
        btnPostSave.setOnAction(event -> {
            LOG.info("Saving modified data, sending them to the server");

            List<Post> modifiedList = copyList.stream().filter(p -> postList.stream()
                    .anyMatch(p2 -> p.getPostId().equals(p2.getPostId())
                    && (!p.getTitle().equals(p2.getTitle()) || !p.getVideo().equals(p2.getVideo())
                    || !p.getPublicationDate().equals(p2.getPublicationDate())))
            ).collect(Collectors.toList());

            try {
                for (Post post : modifiedList) {
                    postController.edit(post);
                }
                // After commit edit copy new list to both copy and original list
                this.copyList = copyArray(postTable.getItems());
                this.postList = copyArray(postTable.getItems());
            } catch (BusinessLogicException ex) {
                LOG.severe(ex.getMessage());
                showAlert(ex.getMessage(), Alert.AlertType.ERROR);
            }

        });
        btnPostDelete.setOnAction(this::deletePost);

        btnPostBack.setOnAction(event -> {
            LOG.info("Closing the window");
            stage.close();
            //primaryStage.show();
        });
        btnPostCancel.setOnAction(event -> {
            LOG.info("Cancel the edited table from commiting to the server");
            postTable.setItems(getItems(copyArray(postList)));
            postTable.refresh();
            btnPostCancel.setDisable(true);
        });
        btnPostAdd.setOnAction(this::commentView);
        btnPostEnter.setOnAction(this::commentView);

        btnPostPrint.setOnAction(event -> {
            try {
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
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
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
        stage.showAndWait();
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

    private List<Post> copyArray(List<Post> original) {
        List<Post> copy = new ArrayList<>();
        original.stream().forEach(p -> copy.add(
                new Post(p.getPostId(), p.getTitle(), p.getContent(),
                        p.getPublicationDate(), p.getImage(), p.getVideo(), p.getCourse())));

        return copy;
    }

    private Post getSelected() {
        return postTable.getSelectionModel().getSelectedItem();
    }

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
            throw new Exception(e.getMessage() + "\nClicked on emtpy row");
        }
    }

    private void deletePost(ActionEvent event) {
        showAlert("¿Estas seguro de que quieres eliminar esta publicacion?", Alert.AlertType.CONFIRMATION);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            LOG.info("Deleting row from table and from the server");
            Post postDelete = getSelected();
            try {
                postController.remove(postDelete.getPostId().toString());
                postTable.getItems().remove(postDelete);
            } catch (BusinessLogicException ex) {
                LOG.severe(ex.getMessage());
                showAlert(ex.getMessage(), Alert.AlertType.ERROR);

            }
        }
    }

    private void commentView(ActionEvent event) {

    }

    @FXML
    private void menuEnter(ActionEvent event) {
        commentView(event);
    }

    @FXML
    private void menuCopy(ActionEvent event) {
        try {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(getSelectedCell());
            clipboard.setContent(content);
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            showAlert("No se ha podido copiar el contenido de la celda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void menuPaste(ActionEvent event) {
        try {
            boolean modified = false;
            Clipboard clipboard = Clipboard.getSystemClipboard();
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
                    LOG.info(e.getMessage() + " Pasted item is a text, if it was date it wasn't formatted properly");
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
            postTable.setItems(getItems(copyArray(postList)));
            postTable.refresh();

        }
    }

    @FXML
    private void menuAdd(ActionEvent event) {
        commentView(event);
    }

    @FXML
    private void menuDelete(ActionEvent event) {
        deletePost(event);
    }

    @FXML
    private void menuRefresh(ActionEvent event) {
        try {
            List<Post> updatedList = postController.getCoursePosts(new GenericType<ArrayList<Post>>() {
            }, idCourse.toString());
            postTable.setItems(getItems(updatedList));
            postTable.refresh();
            alert = new Alert(Alert.AlertType.INFORMATION, "Se ha actualizado la tabla", ButtonType.OK);
            alert.showAndWait();

        } catch (BusinessLogicException e) {
            LOG.severe(e.getMessage());
        }
    }

}
