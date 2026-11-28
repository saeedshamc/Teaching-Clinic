// DashboardController.java — پاسخ پروژهٔ جلسه ۶۹
// نیازمند JavaFX SDK
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DashboardController {

    @FXML private TextField searchField;
    @FXML private Label statusLabel;

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            statusLabel.setText("Enter search query");
        } else {
            statusLabel.setText("Searching: " + query);
        }
    }

    @FXML
    private void handleRefresh() {
        statusLabel.setText("Refreshed at " + System.currentTimeMillis());
    }
}
