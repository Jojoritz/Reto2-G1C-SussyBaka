/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.post;

import client.beans.Comment;
import client.beans.Post;
import client.beans.Student;
import client.beans.Teacher;
import client.beans.enumerations.UserPrivilege;
import client.logic.CommentController;
import client.logic.PostController;
import client.logic.exception.BusinessLogicException;
import client.view.components.GenericController;
import client.view.components.MenuBarController;
import client.view.customNodes.EditingDateCell;
import client.view.customNodes.EditingStringCell;
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
 * FXML Controller class for the Post content View
 *
 * @author Henri
 */
public class PostContentViewController extends GenericController {

    @FXML
    private Label txtTitle;
    @FXML
    private TableView<Comment> tableComment;
    @FXML
    private TableColumn<Comment, String> columnUserName;
    @FXML
    private TableColumn<Comment, Date> columnDate;
    @FXML
    private TableColumn<Comment, String> columnComment;
    @FXML
    private Button btnPostContentSave;
    @FXML
    private Button btnPostContentAdd;
    @FXML
    private Button btnPostContentDelete;
    @FXML
    private Button btnPostContentCancel;
    @FXML
    private Button btnPostContentBack;
    @FXML
    private Button btnPostContentPrint;
    @FXML
    private MenuItem contextCommentCopy;
    @FXML
    private MenuItem contextCommentPaste;
    @FXML
    private MenuItem contextCommentDelete;
    @FXML
    private MenuItem contextCommentRefresh;

    /**
     * Controller of the menu bar
     */
    private final MenuBarController menuBarController = new MenuBarController();

    /**
     * Copy list of the comments
     */
    private List<Comment> copyList;

    /**
     * Comment list
     */
    private List<Comment> commentList;

    /**
     * Post object
     */
    private Post copyPost;

    /**
     * Comment controller interface
     */
    private CommentController commentController;

    /**
     * The logger of this class
     */
    private static final Logger LOG = Logger.getLogger(PostContentViewController.class.getName());

    /**
     * Initializes the controller class.
     *
     * @param root The parent root
     * @param postController Implementation of the Post controller interface
     * @param commentController Implementation of the Comment controller
     * interface
     * @param primaryStage Primary stage
     * @param commentList List with all the comments
     * @param post The post object that has all the data
     */
    public void initStage(Parent root, PostController postController, CommentController commentController, Stage primaryStage, List<Comment> commentList, Post post) {
        scene = new Scene(root);
        stage = new Stage();
        primaryStage.hide();

        // Copy of the comment List and set other variables
        if (!commentList.isEmpty()) {
            if (post != null) {
                this.copyPost = new Post(post.getPostId(), post.getTitle(), post.getContent(),
                        post.getPublicationDate(), post.getImage(), post.getVideo(), post.getCourse());
            }
            this.copyList = copyArray(commentList);
            this.commentList = commentList;
            btnPostContentPrint.setDisable(false);
            tableComment.setItems(getItems(copyList));
            tableComment.getSelectionModel().setCellSelectionEnabled(true);
        }
        this.commentController = commentController;
        this.primaryStage = primaryStage;

        stage.setOnShowing(event -> {
            LOG.info("Starting Post Content window and setting the components on the screen");
            stage.setTitle("Contenido del Post");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            menuBarController.setStage(stage);
            menuBarController.setHelpHtml("/client/view/post/help/PostContentViewHelp.html");

            if (post != null) {
                txtTitle.setText(post.getTitle());
            }

            btnPostContentDelete.setDisable(true);
            btnPostContentCancel.setDisable(true);
            btnPostContentSave.setDisable(true);
            btnPostContentBack.setDisable(false);
            contextCommentRefresh.setDisable(false);
            btnPostContentBack.setDefaultButton(true);
        });

        // Callbacks
        Callback<TableColumn<Comment, String>, TableCell<Comment, String>> stringCellFactory
                = (TableColumn<Comment, String> p) -> new EditingStringCell(65535);
        Callback<TableColumn<Comment, Date>, TableCell<Comment, Date>> dateCellFactory
                = (TableColumn<Comment, Date> p) -> new EditingDateCell();

        // User name column
        columnUserName.setCellValueFactory(comment
                -> new SimpleStringProperty(comment.getValue().getStudent().getLogin()));
        // Date column
        columnDate.setCellValueFactory(comment
                -> new SimpleObjectProperty<>(comment.getValue().getDateComment()));
        columnDate.setCellFactory(dateCellFactory);
        columnDate.setOnEditCommit(
                (TableColumn.CellEditEvent<Comment, Date> t) -> {
                    ((Comment) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setDateComment(t.getNewValue());

                }
        );
        // Comment column
        columnComment.setCellValueFactory(comment
                -> new SimpleStringProperty(comment.getValue().getCommentText()));
        columnComment.setCellFactory(stringCellFactory);
        columnComment.setOnEditCommit(
                (TableColumn.CellEditEvent<Comment, String> t) -> {
                    ((Comment) t.getTableView().getItems().
                            get(t.getTablePosition().getRow())).
                            setCommentText(t.getNewValue());
                }
        );

        // Select row on table
        tableComment.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        btnPostContentDelete.setDisable(false);
                    } else {
                        btnPostContentDelete.setDisable(true);
                    }
                });
        tableComment.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    if (getSelectedCell() != null) {
                        contextCommentCopy.setDisable(false);
                        contextCommentDelete.setDisable(false);
                    }
                    if (((String) clipboard.getContent(DataFormat.PLAIN_TEXT)) != null) {
                        contextCommentPaste.setDisable(false);
                    }
                } catch (Exception ex) {
                    LOG.info(ex.getMessage());
                    contextCommentCopy.setDisable(true);
                    contextCommentPaste.setDisable(true);
                    contextCommentDelete.setDisable(true);
                }
            }
        });

        btnPostContentSave.setOnAction(event -> {
            LOG.info("Saving modified data, sending them to the server");
            // Get only the modified elements from the table
            List<Comment> modifiedList = tableComment.getItems().stream()
                    .filter(c1 -> !copyList.stream()
                    .anyMatch(c2 -> c2.equals(c1)))
                    .collect(Collectors.toList());
            try {
                for (Comment comment : modifiedList) {
                    commentController.edit(comment);
                }
                // After commit edit, copy modified list from table to both copy and original list
                this.copyList = copyArray(tableComment.getItems());
                this.commentList = copyArray(tableComment.getItems());
                showAlert("Se han guardado los cambios correctamente", Alert.AlertType.INFORMATION);
            } catch (BusinessLogicException e) {
                LOG.severe(e.getMessage());
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }

        });
        btnPostContentAdd.setOnAction(event -> {
            try {
                LOG.info("Creating new comment with default values...");
                Comment comment = new Comment(null, (Student) user, post, new Date(), "Lorem ipsum");
                tableComment.getItems().add(comment);
                commentController.create(comment);
            } catch (BusinessLogicException e) {
                LOG.severe(e.getMessage());
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }
        });
        btnPostContentDelete.setOnAction(this::deleteComment);
        btnPostContentCancel.setOnAction(event -> {
            LOG.info("Cancel the edited table or post text fields");
            if (user instanceof Teacher) {

            } else if (user instanceof Student) {
                tableComment.setItems(getItems(copyArray(commentList)));
                tableComment.refresh();
                btnPostContentCancel.setDisable(true);
            }
        });
        btnPostContentBack.setOnAction(event -> {
            LOG.info("Closing the window");
            stage.close();
            primaryStage.show();
        });
        btnPostContentPrint.setOnAction(event -> {
            try {
                JasperReport report
                        = JasperCompileManager.compileReport(getClass()
                                .getResourceAsStream("/client/view/post/PostContentReport.jrxml"));
                JRBeanCollectionDataSource dataItems
                        = new JRBeanCollectionDataSource((List<Comment>) this.tableComment.getItems());
                Map<String, Object> parameters = new HashMap<>();
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);

            } catch (JRException e) {
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }

        });

        stage.show();
    }

    private ObservableList<Comment> getItems(List<Comment> list) {
        ObservableList<Comment> items = FXCollections.observableArrayList(list);
        return items;
    }

    private List<Comment> copyArray(List<Comment> original) {
        List<Comment> copy = new ArrayList<>();
        original.stream().forEach(c -> copy.add(
                new Comment(c.getCommentID(), c.getStudent(), c.getPost(),
                        c.getDateComment(), c.getCommentText())
        ));
        return copy;
    }

    private Comment getSelected() {
        return tableComment.getSelectionModel().getSelectedItem();
    }

    private String getSelectedCell() throws Exception {
        try {
            TablePosition pos = tableComment.getSelectionModel().getSelectedCells().get(0);
            TableColumn col = pos.getTableColumn();
            Comment item = getSelected();
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

    private void deleteComment(ActionEvent event) {
        showAlert("¿Estas seguro de que quieres eliminar esta publicacion?", Alert.AlertType.CONFIRMATION);
        if (alert.getResult().equals(ButtonType.YES)) {
            LOG.info("Deleting row from table and from the server");
            Comment comment = getSelected();
            try {
                commentController.remove(comment.getCommentID().toString());
                tableComment.getItems().remove(comment);
            } catch (BusinessLogicException e) {
                LOG.severe(e.getMessage());
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }

        }

    }

    private void updateImage(String url, ImageView imgView) {
        try {
            if (!url.isEmpty()) {
                imgView.setImage(new Image(url));
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    @FXML
    private void handleCommentCopy(ActionEvent event) {
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
    private void handleCommentPaste(ActionEvent event) {
        try {
            boolean modified = false;
            Clipboard clipboard = Clipboard.getSystemClipboard();
            String content = (String) clipboard.getContent(DataFormat.PLAIN_TEXT);

            TablePosition selectedCell = tableComment.getSelectionModel().getSelectedCells().get(0);
            int col = selectedCell.getColumn();
            TableColumn column = tableComment.getColumns().get(col);
            Comment item = getSelected();
            if (column.getText().equalsIgnoreCase("comentario")) {
                item.setCommentText(content);
                modified = true;
            } else {
                try {
                    final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.systemDefault());
                    LocalDateTime dateTime = LocalDateTime.parse(content, dateFormat);
                    item.setDateComment(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
                    modified = true;
                } catch (Exception e) {
                    modified = false;
                    LOG.info(e.getMessage() + " Pasted item is a text, if it was date it wasn't formatted properly");
                }
            }
            if (modified) {
                btnPostContentSave.setDisable(false);
                btnPostContentCancel.setDisable(false);
            }
            tableComment.refresh();
        } catch (Exception e) {
            LOG.info(e.getMessage() + " Rolling back any modification to the table");
            showAlert("Rolling back any modification to the table", Alert.AlertType.INFORMATION);
            tableComment.setItems(getItems(copyArray(commentList)));
            tableComment.refresh();

        }
    }

    @FXML
    private void handleCommentDelete(ActionEvent event) {
        deleteComment(event);
    }

    @FXML
    private void handleCommentRefresh(ActionEvent event) {
        try {
            List<Comment> updatedList = commentController.findAll(new GenericType<ArrayList<Comment>>() {
            }, copyPost.getPostId().toString());
            tableComment.setItems(getItems(updatedList));
            tableComment.refresh();
            showAlert("Se ha actualizado la tabla", Alert.AlertType.INFORMATION);

        } catch (BusinessLogicException e) {
            LOG.severe(e.getMessage());
        }
    }

}
