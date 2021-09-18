package jp.trans_it.hit_blow;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jp.trans_it.hit_blow.model.History;
import jp.trans_it.hit_blow.model.Numbers;
import jp.trans_it.hit_blow.model.Result;

public class MainFrame implements Initializable {
	private final static String SPIN_STYLE = "-fx-font-size: 20;";

	private int counter;
	private Numbers correctNumbers;

	@FXML
	private TableView<History> table;

	@FXML
	private TableColumn<History, Integer> counterColumn;

	@FXML
	private TableColumn<History, Numbers> numbersColumn;

	@FXML
	private TableColumn<History, Integer> hitColumn;

	@FXML
	private TableColumn<History, Integer> blowColumn;

	@FXML
	private Spinner<Integer> firstSpinner;

	@FXML
	private Spinner<Integer> secondSpinner;

	@FXML
	private Spinner<Integer> thirdSpinner;

	@FXML
	private Spinner<Integer> fourthSpinner;

	@FXML
	private Button applyButton;

	@FXML
	private void onApply(ActionEvent event) {
		String string = String.format(
			"%d%d%d%d",
			this.firstSpinner.getValue(),
			this.secondSpinner.getValue(),
			this.thirdSpinner.getValue(),
			this.fourthSpinner.getValue()
		);

		Numbers numbers = new Numbers(4);
		numbers.input(string);

		Result result = this.correctNumbers.check(numbers);

		History history = new History(
			this.counter, numbers, result
		);
		this.table.getItems().add(history);
		this.counter++;
		this.table.scrollTo(history);

		if(result.getHit() == 4) {
			Alert alert = new Alert(
				AlertType.INFORMATION,
				"おめでとうございます。"
			);
			alert.showAndWait();
			this.applyButton.setDisable(true);
		}
	}

	@FXML
	private void onReset(ActionEvent event) {
		this.reset();
	}

	@FXML
	private void onClose(ActionEvent event) {
		Button button = (Button)event.getSource();
		Scene scene = button.getScene();
		Stage stage = (Stage)scene.getWindow();
		stage.close();
	}

	private void createTable() {
		this.counterColumn.setCellValueFactory(
			new PropertyValueFactory<History, Integer>("counter")
		);

		this.numbersColumn.setCellValueFactory(
			new PropertyValueFactory<History, Numbers>("numbers")
		);

		this.hitColumn.setCellValueFactory(
			(entry) -> {
				History history = entry.getValue();
				Integer hit = history.getResult().getHit();
				ObservableValue<Integer> value = new ReadOnlyObjectWrapper<Integer>(hit);
				return value;
			}
		);

		this.blowColumn.setCellValueFactory(
			(entry) -> {
				History history = entry.getValue();
				Integer blow = history.getResult().getBlow();
				ObservableValue<Integer> value = new ReadOnlyObjectWrapper<Integer>(blow);
				return value;
			}
		);
	}

	private void createSpinners() {
		List<Spinner<Integer>> list = new ArrayList<Spinner<Integer>>();
		list.add(this.firstSpinner);
		list.add(this.secondSpinner);
		list.add(this.thirdSpinner);
		list.add(this.fourthSpinner);

		for(Spinner<Integer> spinner : list) {
			spinner.setValueFactory(
				new SpinnerValueFactory<Integer>() {
					@Override
					public void decrement(int steps) {
						int value = this.getValue();
						value--;
						if(value < 1) {
							value = 9;
						}
						this.setValue(value);
					}

					@Override
					public void increment(int steps) {
						int value = this.getValue();
						value++;
						if(value > 9) {
							value = 1;
						}
						this.setValue(value);
					}
				}
			);
			spinner.getEditor().setStyle(SPIN_STYLE);
		}
	}

	private void createControls() {
		this.createTable();
		this.createSpinners();
	}

	private void reset() {
		this.correctNumbers = new Numbers(4);
		this.correctNumbers.random(9);
		this.counter = 1;
		
		this.firstSpinner.getValueFactory().setValue(1);
		this.secondSpinner.getValueFactory().setValue(2);
		this.thirdSpinner.getValueFactory().setValue(3);
		this.fourthSpinner.getValueFactory().setValue(4);

		this.applyButton.setDisable(false);

		this.table.getItems().clear();
	}

	public void initialize(URL location, ResourceBundle resources) {
		this.createControls();
		this.reset();
	}
}
