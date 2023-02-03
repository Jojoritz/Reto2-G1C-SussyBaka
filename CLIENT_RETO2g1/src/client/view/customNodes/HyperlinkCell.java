/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.customNodes;

import client.beans.Post;
import javafx.application.HostServices;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Henri
 */
public class HyperlinkCell implements Callback<TableColumn<Post, String>, TableCell<Post, String>> {

    private static HostServices hostServices;

    public static HostServices getHostServices() {
        return hostServices;
    }

    public static void setHostServices(HostServices hostServices) {
        HyperlinkCell.hostServices = hostServices;
    }

    public HyperlinkCell() {
        
    }

    @Override
    public TableCell<Post, String> call(TableColumn<Post, String> param) {
        TableCell<Post, String> cell = new TableCell<Post, String>() {
            private final Hyperlink hyperlink = new Hyperlink();

            {
                hyperlink.setOnAction(event -> {
                    String url = getItem();
                    hostServices.showDocument(url);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setGraphic(null);
                } else {
                    hyperlink.setText(item);
                    setGraphic(hyperlink);
                }
            }
        };
        return cell;

    }

}
