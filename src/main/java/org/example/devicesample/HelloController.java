package org.example.devicesample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private Tab tab1, tab2, tab3;
    @FXML
    private TextField textNameSmartphone, textNameLaptop, textNameTablet;
    @FXML
    private TextField textPriceSmartphone, textPriceLaptop, textPriceTablet;
    @FXML
    private TextField textWeightSmartphone, textWeightLaptop, textWeightTablet;
    @FXML
    private TextField textScreenSize, textCameraResolution; // Smartphone-specific
    @FXML
    private TextField textRamSize, textProcessorType;       // Laptop-specific
    @FXML
    private TextField textBatteryLife, textHasStylus;       // Tablet-specific
    @FXML
    private ListView<Device> listView;
    @FXML
    private Label label;

    // ObservableList to store devices and update the ListView automatically
    private ObservableList<Device> devices = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind the ObservableList to the ListView to automatically reflect changes
        listView.setItems(devices);
    }

    @FXML
    void onAddClick(ActionEvent event) {
        Device device;

        try {
            if (tab1.isSelected()) { // Smartphone
                if (textNameSmartphone.getText().isEmpty() || textPriceSmartphone.getText().isEmpty() ||
                        textWeightSmartphone.getText().isEmpty() || textScreenSize.getText().isEmpty() ||
                        textCameraResolution.getText().isEmpty()) {
                    label.setText("Error: Please fill in all fields for Smartphone.");
                    return;
                }

                // Parse values for Smartphone
                String name = textNameSmartphone.getText().trim();
                double price = Double.parseDouble(textPriceSmartphone.getText().trim());
                double weight = Double.parseDouble(textWeightSmartphone.getText().trim());
                int screenSize = Integer.parseInt(textScreenSize.getText().trim());
                int resolution = Integer.parseInt(textCameraResolution.getText().trim());

                device = new Smartphone(Devicetype.SMARTPHONE, name, price, weight, screenSize, resolution);

            } else if (tab2.isSelected()) { // Laptop
                if (textNameLaptop.getText().isEmpty() || textPriceLaptop.getText().isEmpty() ||
                        textWeightLaptop.getText().isEmpty() || textRamSize.getText().isEmpty() ||
                        textProcessorType.getText().isEmpty()) {
                    label.setText("Error: Please fill in all fields for Laptop.");
                    return;
                }

                // Parse values for Laptop
                String name = textNameLaptop.getText().trim();
                double price = Double.parseDouble(textPriceLaptop.getText().trim());
                double weight = Double.parseDouble(textWeightLaptop.getText().trim());
                int ramSize = Integer.parseInt(textRamSize.getText().trim());
                String processorType = textProcessorType.getText().trim();

                device = new Laptop(Devicetype.LAPTOP, name, price, weight, ramSize, processorType);

            } else if (tab3.isSelected()) { // Tablet
                if (textNameTablet.getText().isEmpty() || textPriceTablet.getText().isEmpty() ||
                        textWeightTablet.getText().isEmpty() || textBatteryLife.getText().isEmpty() ||
                        textHasStylus.getText().isEmpty()) {
                    label.setText("Error: Please fill in all fields for Tablet.");
                    return;
                }

                // Parse values for Tablet
                String name = textNameTablet.getText().trim();
                double price = Double.parseDouble(textPriceTablet.getText().trim());
                double weight = Double.parseDouble(textWeightTablet.getText().trim());
                double batteryLife = Double.parseDouble(textBatteryLife.getText().trim());
                boolean hasStylus = Boolean.parseBoolean(textHasStylus.getText().trim());

                device = new Tablet(Devicetype.TABLET, name, price, weight, batteryLife, hasStylus);

            } else {
                label.setText("Error: No device type selected.");
                return;
            }

            // Add the device to the ObservableList, which updates the ListView automatically
            devices.add(device);
            label.setText("Device added successfully.");

        } catch (NumberFormatException e) {
            label.setText("Error: Ensure all numeric fields contain valid numbers.");
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
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device removedDevice = devices.remove(selectedIndex); // Remove the selected device
            label.setText(removedDevice.getName() + " removed.");
        } else {
            label.setText("Error: No device selected to remove.");
        }
    }
}