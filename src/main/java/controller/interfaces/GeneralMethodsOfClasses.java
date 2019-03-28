package controller.interfaces;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public interface GeneralMethodsOfClasses {

    /**
     *  <p>Method load the main window based on {@link fxmlFiles} / MainMenuPanel.fxml </p>
     *
     *  @param event
     *  @throws IOException
     */
    void backToPreviousWindow(ActionEvent event) throws IOException;

    /**
     * <p>Method load data from proper table to {@link javafx.scene.control.TableView} while use press "Wyświetl" button</p>
     * @throws SQLException
     */
    void selectAll() throws SQLException;

    /**
     * <p>Method open proper window
     * depending on whether the user wants to add an employee, supplier.</p>
     * <p> {@link fxmlFiles.employeeFXML} / AddEmployeeWindow.fxml </p>
     * <p> or </p>
     * <p> {@link fxmlFiles.supplierFXML} / AddSupplierWindow.fxml </p>
     */
    void add();

    /**
     * <p>The method use {@link javafx.scene.control.TableView.TableViewSelectionModel} to take SelectedModel and delete data from
     * proper table after confirmed.</p>
     * @throws IOException
     */
    void deleteSelected() throws IOException;
}
