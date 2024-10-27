package org.example.devicesample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private Tab tab1, tab2, tab3;

//    @FXML
//    private ToggleGroup device;
//
//    @FXML
//    private RadioButton rbLaptop;
//
//    @FXML
//    private RadioButton rbSmartphone;
//
//    @FXML
//    private RadioButton rbTablet;

    @FXML
    private TextField textNameSmartphone, textNameLaptop, textNameTablet;

    @FXML
    private TextField textPrice;

    @FXML
    private TextField textWeight;

    @FXML
    private TextField textScreenSize;     // Smartphone-specific
    @FXML
    private TextField textCameraResolution; // Smartphone-specific

    @FXML
    private TextField textRamSize;        // Laptop-specific
    @FXML
    private TextField textProcessorType;   // Laptop-specific

    @FXML
    private TextField textBatteryLife;     // Tablet-specific
    @FXML
    private TextField textHasStylus;       // Tablet-specific

    @FXML
    private ListView<Device> listView;

    @FXML
    private Label label;

    ObservableList<Device> devices = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(devices);

        // Toggle visibility of device-specific fields based on selected radio button
        device.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (rbSmartphone.isSelected()) {
                setVisibility(true, false, false);
            } else if (rbLaptop.isSelected()) {
                setVisibility(false, true, false);
            } else if (rbTablet.isSelected()) {
                setVisibility(false, false, true);
            }
        });
    }

    private void setVisibility(boolean smartphone, boolean laptop, boolean tablet) {
        // Toggle visibility of fields for each device type
        textScreenSize.setVisible(smartphone);
        textCameraResolution.setVisible(smartphone);

        textRamSize.setVisible(laptop);
        textProcessorType.setVisible(laptop);

        textBatteryLife.setVisible(tablet);
        textHasStylus.setVisible(tablet);
    }

    @FXML
    void onAddClick(ActionEvent event) {
        try {
            // Ensure numeric fields are parsed correctly
            String name = textNameSmartphone.getText();
            double price = Double.parseDouble(textPrice.getText());
            double weight = Double.parseDouble(textWeight.getText());
            Device device;

            if (rbSmartphone.isSelected()) {
                int screenSize = Integer.parseInt(textScreenSize.getText());
                int cameraResolution = Integer.parseInt(textCameraResolution.getText());
                device = new Smartphone(Devicetype.SMARTPHONE, name, price, weight, screenSize, cameraResolution);
            } else if (rbLaptop.isSelected()) {
                int ramSize = Integer.parseInt(textRamSize.getText());
                String processorType = textProcessorType.getText();
                device = new Laptop(Devicetype.LAPTOP, name, price, weight, ramSize, processorType);
            } else if (rbTablet.isSelected()) {
                double batteryLife = Double.parseDouble(textBatteryLife.getText());
                boolean hasStylus = Boolean.parseBoolean(textHasStylus.getText());
                device = new Tablet(Devicetype.TABLET, name, price, weight, batteryLife, hasStylus);
            } else {
                // If no device type is selected, don't add anything
                return;
            }

            devices.add(device); // Add the device to the ObservableList, which updates the ListView
        } catch (NumberFormatException e) {
            System.out.println("Error: Please ensure all numeric fields contain valid numbers.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    protected void onListClicked() {
        int id = listView.getSelectionModel().getSelectedIndex();
        if (id >= 0) {
            label.setText(devices.get(id).toString());
        }
    }

    @FXML
    private void onRemoveClick() {
        int id = listView.getSelectionModel().getSelectedIndex();
        if (id >= 0) {
            label.setText(devices.get(id).getName() + " is removed.");
            devices.remove(id);
        }
    }
}
