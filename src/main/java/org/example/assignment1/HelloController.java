package org.example.assignment1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.*;

public class HelloController {

    @FXML
    public TableColumn <Video, String> title;
    public TableColumn <Video, String> uploader;
    public TableColumn <Video, Double> dislikesm;
    public TableColumn <Video, Double> dislikes;
    public TableView <Video> table;
    public Button viewTableButton;
    public Button viewGraphButton;
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private BarChart<String, Number> barchart;

//    DatabaseConnection connector = new DatabaseConnection();
    static int x;

    @FXML
    public void initialize (){
        if (x == 0){
        try (Connection connection = DatabaseConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Title, `Dislikes(%)` FROM video")) {

            XYChart.Series<String, Number> series = new XYChart.Series<>();

            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                double dislikesPercentage = resultSet.getDouble("Dislikes(%)");
                series.getData().add(new XYChart.Data<>(title, dislikesPercentage));
            }

            try {
                barchart.getData().add(series);

                CategoryAxis xAxis = (CategoryAxis) barchart.getXAxis();
                xAxis.setLabel("Title");

                NumberAxis yAxis = (NumberAxis) barchart.getYAxis();
                yAxis.setLabel("Dislikes(%)");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("first and " + x);
        }

        if(x == 1) {

            title.setCellValueFactory(cellData -> cellData.getValue().getTitle());
            uploader.setCellValueFactory(cellData -> cellData.getValue().getUploader());
            dislikesm.setCellValueFactory(cellData -> cellData.getValue().getDislikes().asObject());
            dislikes.setCellValueFactory(cellData -> cellData.getValue().getDislikesPercentage().asObject());


            try {
                Connection connection = DatabaseConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM video");

                ObservableList<Video> videos = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    String title = resultSet.getString("Title");
                    String uploader = resultSet.getString("Uploader");
                    double dislikesM = resultSet.getInt("Dislikes(millions)");
                    double dislikesP = resultSet.getInt("Dislikes(%)");
                    videos.add(new Video(title, uploader, dislikesM, dislikesP));
                }
                table.setItems(videos);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }






    @FXML
    public void changeToTable (ActionEvent event) throws IOException {
        x++;
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void changeToBar (ActionEvent event) throws IOException{
        x--;
        Parent root = FXMLLoader.load(getClass().getResource("Graph-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}