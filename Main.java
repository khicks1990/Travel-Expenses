import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
   Travel Expenses
*/

public class Main extends Application
{
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      // Create the controls for the number of days.
      Label daysPrompt = new Label("Days on the trip:");
      TextField daysTextField = new TextField();
      HBox daysHBox = new HBox(10, daysPrompt, daysTextField);
      daysHBox.setAlignment(Pos.CENTER);

      // Create the controls for the airfare.
      Label airPrompt = new Label("Airfare:");
      TextField airTextField = new TextField();
      HBox airHBox = new HBox(10, airPrompt, airTextField);
      airHBox.setAlignment(Pos.CENTER);

      // Create the controls for the car rental.
      Label carPrompt = new Label("Car rental:");
      TextField carTextField = new TextField();
      HBox carHBox = new HBox(10, carPrompt, carTextField);
      carHBox.setAlignment(Pos.CENTER);

      // Create the controls for the miles driven.
      Label milesPrompt = new Label("Miles driven:");
      TextField milesTextField = new TextField();
      HBox milesHBox = new HBox(10, milesPrompt, milesTextField);
      milesHBox.setAlignment(Pos.CENTER);

      // Create the controls for the parking fees.
      Label parkingPrompt = new Label("Parking fees:");
      TextField parkingTextField = new TextField();
      HBox parkingHBox = new HBox(10, parkingPrompt, parkingTextField);
      parkingHBox.setAlignment(Pos.CENTER);

      // Create the controls for the taxi fees.
      Label taxiPrompt = new Label("Taxi fees:");
      TextField taxiTextField = new TextField();
      HBox taxiHBox = new HBox(10, taxiPrompt, taxiTextField);
      taxiHBox.setAlignment(Pos.CENTER);

      // Create the controls for the conference registrarion fees.
      Label regPrompt = new Label("Registration fees:");
      TextField regTextField = new TextField();
      HBox regHBox = new HBox(10, regPrompt, regTextField);
      regHBox.setAlignment(Pos.CENTER);

      // Create the controls for the nightly lodging fees.
      Label lodgingPrompt = new Label("Nightly lodging fees:");
      TextField lodgingTextField = new TextField();
      HBox lodgingHBox = new HBox(10, lodgingPrompt, lodgingTextField);
      lodgingHBox.setAlignment(Pos.CENTER);

      // Create the total expenses output labels.
      Label totalDescriptor = new Label("Total expenses:");
      Label totalOutputLabel = new Label();
      HBox totalHBox = new HBox(10, totalDescriptor, totalOutputLabel);
      totalHBox.setAlignment(Pos.CENTER_LEFT);

      // Create the allowable expenses output labels.
      Label allowableDescriptor = new Label("Allowable expenses:");
      Label allowableOutputLabel = new Label();
      HBox allowableHBox = new HBox(10, allowableDescriptor, allowableOutputLabel);
      allowableHBox.setAlignment(Pos.CENTER_LEFT);

      // Create the excess expenses output labels.
      Label excessDescriptor = new Label("Excess expenses:");
      Label excessOutputLabel = new Label();
      HBox excessHBox = new HBox(10, excessDescriptor, excessOutputLabel);
      excessHBox.setAlignment(Pos.CENTER_LEFT);

      // Create the saved expenses output labels.
      Label savedDescriptor = new Label("Saved expenses:");
      Label savedOutputLabel = new Label();
      HBox savedHBox = new HBox(10, savedDescriptor, savedOutputLabel);
      savedHBox.setAlignment(Pos.CENTER_LEFT);

      // Create the calcButton control.
      Button calcButton = new Button("Calculate Expenses");
      HBox buttonHBox = new HBox(calcButton);
      buttonHBox.setAlignment(Pos.CENTER);

      // Register the event handler.
      calcButton.setOnAction(e ->
      {
         // Named constants
         final double MAX_MEALS_PER_DAY = 47.0;
         final double MAX_PARKING_PER_DAY = 20.0;
         final double MAX_TAXI_PER_DAY = 40.0;
         final double MAX_LODGING_PER_DAY = 195.0;
         final double PER_MILE = 0.42;

         // Get the values entered.
         double days = Double.parseDouble(daysTextField.getText());
         double air = Double.parseDouble(airTextField.getText());
         double car = Double.parseDouble(carTextField.getText());
         double miles = Double.parseDouble(milesTextField.getText());
         double parking = Double.parseDouble(parkingTextField.getText());
         double taxi = Double.parseDouble(taxiTextField.getText());
         double reg = Double.parseDouble(regTextField.getText());
         double nightlyLodging = Double.parseDouble(lodgingTextField.getText());

         // Calculate the total expenses spent.
         double totalSpent = (days * nightlyLodging) + air + car +
                             (miles * PER_MILE) + parking + taxi +
                             (days * MAX_MEALS_PER_DAY) + reg;

         // Calculate the total allowable expenses.
         double totalAllowable = (days * MAX_LODGING_PER_DAY) + air + car +
                                 (miles * PER_MILE) + (days * MAX_PARKING_PER_DAY) +
                                 (days * MAX_TAXI_PER_DAY) + (days * MAX_MEALS_PER_DAY) +
                                 reg;

         // Calculate the excess expenses.
         double excess = totalSpent - totalAllowable;
         if (excess < 0)
            excess = 0.0;

         // Calculate the amount saved.
         double saved = totalAllowable - totalSpent;
         if (saved < 0)
            saved = 0.0;

         // Display the output.
         totalOutputLabel.setText(String.format("%,.2f", totalSpent));
         allowableOutputLabel.setText(String.format("%,.2f", totalAllowable));
         excessOutputLabel.setText(String.format("%,.2f", excess));
         savedOutputLabel.setText(String.format("%,.2f", saved));
      });

      // Put everything into a VBox
      VBox mainVBox = new VBox(10, daysHBox, airHBox, carHBox, milesHBox,parkingHBox,
                                   taxiHBox, regHBox, lodgingHBox, totalHBox,
                                   allowableHBox, excessHBox, savedHBox, buttonHBox);
      mainVBox.setAlignment(Pos.CENTER);
      mainVBox.setPadding(new Insets(10));

      // Add the main VBox to a scene.
      Scene scene = new Scene(mainVBox);

      // Set the scene to the stage and display it.
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}