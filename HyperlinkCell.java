/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import javafx.application.HostServices;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Sml
 */
public abstract class HyperlinkCell  implements  Callback<TableColumn<DIAGNOZ, Hyperlink>, TableCell<DIAGNOZ, Hyperlink>> {

    private static HostServices hostServices ;

    public static HostServices getHostServices() {
        return hostServices ;
    }

    public static void setHostServices(HostServices hostServices) {
        HyperlinkCell.hostServices = hostServices ;
    }

    /*public TableCell<DIAGNOZ, Hyperlink> call(TableColumn<DIAGNOZ, String> arg) {
        TableCell<DIAGNOZ, Hyperlink> cell = new TableCell<DIAGNOZ, Hyperlink>() {

            /*private final Hyperlink hyperlink = new Hyperlink();

            {
                hyperlink.setOnAction(event -> {
                    String url = getItem();
                    hostServices.showDocument(url);
                });
            }

            @Override
            protected void updateItem(String url, boolean empty) {
                super.updateItem(url, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    hyperlink.setText(url);
                    setGraphic(hyperlink);
                }
            }
        };
        return cell;
    }*/
}
