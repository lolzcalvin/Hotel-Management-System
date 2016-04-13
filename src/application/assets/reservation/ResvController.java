package application.assets.reservation;

import application.assets.AutoCompleteCBoxListener;
import application.assets.ForAddButton;
import application.assets.ModelGroupMember;
import application.assets.ModelRoom;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import application.Validation;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ResvController implements Initializable {

    @FXML
    private AnchorPane resvPane;
    @FXML
    private TextField tf_resvno;
    @FXML
    private TextField tf_fname;
    @FXML
    private TextField tf_lname;
    @FXML
    private TextField tf_phoneno;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_postcode;
    @FXML
    private TextField tf_city;
    @FXML
    private TextField tf_idno;
    @FXML
    private ComboBox<String> cbox_country;
    @FXML
    private ComboBox<String> cbox_idtype;

    @FXML
    private Button btn_resvNext;
    @FXML
    private Button btn_addguest;
    @FXML
    private Button btn_delguest;
    @FXML
    private Button btn_addroom;
    @FXML
    private Button btn_delroom;
    @FXML
    private Button btn_addfac;
    @FXML
    private Button btn_delfac;

    @FXML
    private CheckBox chkbox_gmembers;

    @FXML
    private TableView<ModelGroupMember> table_gmembers;
    @FXML
    private TableColumn<ModelGroupMember, String> tbcol_memfname;
    @FXML
    private TableColumn<ModelGroupMember, String> tbcol_memlname;
    @FXML
    private TableColumn<ModelGroupMember, String> tbcol_memidno;
    @FXML
    private TableColumn<ModelGroupMember, String> tbcol_memroomno;

    @FXML
    private TableView<ModelRoom> table_resvRoom;
    @FXML
    private TableColumn<ModelRoom, String> tbcol_roomcat;
    @FXML
    private TableColumn<ModelRoom, String> tbcol_roomtype;
    @FXML
    private TableColumn<ModelRoom, String> tbcol_roomno;
    @FXML
    private TableColumn<ModelRoom, String> tbcol_roomci;
    @FXML
    private TableColumn<ModelRoom, String> tbcol_roomco;
    @FXML
    private TableColumn<ModelRoom, String> tbcol_xtrabed;
    @FXML
    private TableColumn<ModelRoom, String> tbcol_rprice;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cellValueFactory();

        validations();

        addGuest();

        addRoom();

        delRoom();

        //store short form of countries in array
        String[] locale = Locale.getISOCountries();
        //loop the array
        for (String countryCode : locale) {
            Locale country = new Locale("", countryCode);
            //System.out.println(country.getDisplayCountry());
            //add countries into cbox!
            cbox_country.getItems().add(country.getDisplayCountry());
        }

        new AutoCompleteCBoxListener<>(cbox_country);

        //bottom onwards are how I access back button from the payment controller (of payment fxml)
        FXMLLoader loadpayment = new FXMLLoader(getClass().getResource("/application/assets/" +
                "reservation/resvpay.fxml")); //create a fxmlLoader
        AnchorPane payment = null;
        try {
            payment = loadpayment.load(); //load it into an anchorPane
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane finalPayment = payment; //set as another anchorPane called finalPayment
        btn_resvNext.setOnMouseClicked(me -> {
            FadeTransition ft = new FadeTransition(Duration.millis(320), finalPayment);
            ft.setFromValue(0.0); //add a simple fade in transition
            ft.setToValue(1.0);
            ft.play();
            resvPane.getChildren().add(finalPayment); //add as children of resvPane
        });

        //get the controller of the fxmlLoader (which is the payment controller)
        ResvPayController controller = loadpayment.getController();

        controller.getBtn_resvBack().setOnMouseClicked(me -> { //getter from the payment controller
            Timeline timeline = new Timeline(); //set fade out
            assert finalPayment != null;
            KeyFrame kf = new KeyFrame(Duration.millis(320), new KeyValue(finalPayment.opacityProperty(), 0));
            timeline.getKeyFrames().add(kf);
            //when the timeline is finished (finished fade out) then invoke remove the finalPayment
            timeline.setOnFinished(se -> resvPane.getChildren().removeAll(finalPayment));
            timeline.play();
        });
    }

    //pressing add guest button
    public void addGuest() {
        FXMLLoader loadGuest = new FXMLLoader(getClass().getResource("/application/assets" +
                "/reservation/resvaddgroup.fxml"));
        AnchorPane guestPane = new AnchorPane();
        try {
            guestPane = loadGuest.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane finalGuestPane = guestPane;
        new ForAddButton(finalGuestPane, btn_addguest);

    }//add guest ends

    //pressing add button for room as well as in room popup
    public void addRoom() {
        //VERY IMPORTANT: please use like the below, because can retrieve controller from fxmlloader easily
        FXMLLoader loadRoom = new FXMLLoader(getClass().getResource("/application/assets" +
                "/reservation/resvroom.fxml"));
        AnchorPane roomPane = new AnchorPane();
        try {
            roomPane = loadRoom.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane finalRoomPane = roomPane;

        new ForAddButton(finalRoomPane, btn_addroom);


        ResvRoomController rc = loadRoom.getController();

        rc.getBtn_roomAdd().setOnMouseClicked(me -> {
            ModelRoom room = new ModelRoom();
            ObservableList<ModelRoom> rd = FXCollections.observableArrayList();
            room.setRoomcat(rc.getRoomCat());
            room.setRtype(rc.getRoomType());
            room.setRoomno(rc.getRoomNo());
            room.setCidate(rc.getCI());
            room.setCodate(rc.getCO());
            room.setExtbedtype(rc.getExtBed());
            room.setRoomprice(rc.getTotal());

            rd.add(room);
            table_resvRoom.getItems().add(room);

            //manually clearing data from the add room stage after adding
            rc.getCbox_roomcat().getItems().clear();
            rc.getCbox_roomtype().getItems().clear();
            rc.getTable_rooms().getItems().clear();
            rc.getCbox_xtrabed().getItems().clear();
            rc.getLbl_extBedPrice().setText(null);
            rc.getLbl_roomPrice().setText(null);
            rc.getLbl_totalRoomPrice().setText(null);
            rc.getTf_roomno().setText(null);

            Stage stage = (Stage) rc.getBtn_roomAdd().getScene().getWindow();
            stage.close();
        });

    }//add room resv ends

    //delete room resv
    public void delRoom(){
        btn_delroom.setOnMouseClicked(me->{
            int selectedRow = table_resvRoom.getSelectionModel().getSelectedIndex();
            if (selectedRow >= 0){
                ModelRoom roomNo = new ModelRoom();
                roomNo = table_resvRoom.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete Room");
                alert.setContentText("Are you sure you want to delete room " + roomNo.getRoomno() + "?");
                //detect user presses ok or cancel (must do like this)
                Optional<ButtonType> select = alert.showAndWait();
                if (select.isPresent()) {
                    if (select.get() == ButtonType.OK){
                        table_resvRoom.getItems().remove(selectedRow);
                    } else {
                        alert.close();
                    }
                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("No Selection");
                alert2.setHeaderText("No Room is Selected");
                alert2.setContentText("Please select a room in the table to be deleted.");
                alert2.showAndWait();
            }
        });
    } //delete room resv ends

    private void validations() {
        tf_resvno.addEventFilter(KeyEvent.KEY_TYPED, Validation.validCharNo(10));
        tf_fname.addEventFilter(KeyEvent.KEY_TYPED, Validation.validChar(20));
        tf_lname.addEventFilter(KeyEvent.KEY_TYPED, Validation.validChar(20));
        tf_phoneno.addEventFilter(KeyEvent.KEY_TYPED, Validation.validNo(15));
        tf_address.addEventFilter(KeyEvent.KEY_TYPED, Validation.validCharNoCommaDot(50));
        tf_postcode.addEventFilter(KeyEvent.KEY_TYPED, Validation.validNo(12));
        tf_city.addEventFilter(KeyEvent.KEY_TYPED, Validation.validChar(25));
        tf_idno.addEventFilter(KeyEvent.KEY_TYPED, Validation.validNo(20));
    }

    public void cellValueFactory() {
        tbcol_roomcat.setCellValueFactory(new PropertyValueFactory<>("roomcat"));
        tbcol_roomtype.setCellValueFactory(new PropertyValueFactory<>("rtype"));
        tbcol_roomno.setCellValueFactory(new PropertyValueFactory<>("roomno"));
        tbcol_roomci.setCellValueFactory(new PropertyValueFactory<>("cidate"));
        tbcol_roomco.setCellValueFactory(new PropertyValueFactory<>("codate"));
        tbcol_xtrabed.setCellValueFactory(new PropertyValueFactory<>("extbedtype"));
        tbcol_rprice.setCellValueFactory(new PropertyValueFactory<>("roomprice"));

        tbcol_memfname.setCellValueFactory(new PropertyValueFactory<>("memFName"));
        tbcol_memlname.setCellValueFactory(new PropertyValueFactory<>("memLName"));
        tbcol_memidno.setCellValueFactory(new PropertyValueFactory<>("idNo"));
        tbcol_memroomno.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
    }

}
