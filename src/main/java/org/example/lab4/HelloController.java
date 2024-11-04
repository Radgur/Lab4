package org.example.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Rectangle;

public class HelloController {

    int soup_counter = 0;
    int shawarma_counter = 0;
    int pizza_counter = 0;
    List<String> colors = new ArrayList<>();
    String equation1 = "";
    String equation2 = "";
    String equationText = "0";
    String operator = "";
    Double result = 0.0;

    @FXML
    private Button button_arrow, button_shawarma, button_pizza, button_soup;

    @FXML
    private Label label_shawarma, label_pizza, label_soup, label_equationField, check_list;

    @FXML
    private TextArea textArea1, textArea2;

    @FXML
    private CheckBox checkBox1, checkBox2, checkBox3;

    @FXML
    private DatePicker widget1;

    @FXML
    private RadioButton widget2, box_blue, box_red, box_white;

    @FXML
    private ProgressBar widget3;

    @FXML
    private Rectangle rectangle_bottom, rectangle_center, rectangle_top;

    @FXML
    void button_arrow_clicked() {
        String text;
        text = textArea1.getText();
        textArea1.setText(textArea2.getText());
        textArea2.setText(text);

        System.out.println("Сработал Перекидыватель слов");

        if (Objects.equals(button_arrow.getText(), "---------->")) {
            button_arrow.setText("<----------");
        } else {
            button_arrow.setText("---------->");
        }
    }

    @FXML
    void checkBox_selected(ActionEvent event) {
        if (event.getSource() == checkBox1) {
            widget1.setVisible(checkBox1.isSelected());
            System.out.println("Нажат чекбокс №1");
        } else if (event.getSource() == checkBox2) {
            widget2.setVisible(checkBox2.isSelected());
            System.out.println("Нажат чекбокс №2");
        } else {
            widget3.setVisible(checkBox3.isSelected());
            System.out.println("Нажат чекбокс №3");
        }
    }

    @FXML
    void order_click(ActionEvent event) {

        if (event.getSource() == button_soup) {
            soup_counter++;
            label_soup.setText(String.valueOf(soup_counter));
        } else if (event.getSource() == button_shawarma) {
            shawarma_counter++;
            label_shawarma.setText(String.valueOf(shawarma_counter));
        } else if (event.getSource() == button_pizza) {
            pizza_counter++;
            label_pizza.setText(String.valueOf(pizza_counter));
        }
    }

    @FXML
    void button_order_clicked() {
        String text;
        int sum = soup_counter * 45 + shawarma_counter * 280 + pizza_counter * 519;
        text = "Борщ ----- " + soup_counter + " шт.\n" + "Шаурма ----- " + shawarma_counter + " шт.\n"
                + "Пицца ----- " + pizza_counter + " шт.\n\n" + "---------------------------\nИтого: " + sum + " руб.";

        soup_counter = 0;
        shawarma_counter = 0;
        pizza_counter = 0;

        label_soup.setText("---");
        label_shawarma.setText("---");
        label_pizza.setText("---");

        check_list.setText(text);
        System.out.println("Вы сделали заказ");
    }

    @FXML
    void button_number_clicked(ActionEvent event) {
        Button but = (Button) event.getSource();
        if (equationText.equals("0")) {
            equationText = equationText.substring(1);
        } else if (equationText.equals("-0")) {
            equationText = equationText.substring(1);
            equationText = equationText.substring(1);
        }
        equationText += but.getText();
        label_equationField.setText(equationText);
    }

    @FXML
    void button_operator_clicked(ActionEvent event) {
        Button but = (Button) event.getSource();
        if (Objects.equals(but.getText(), ".")) {
            if (!equationText.contains(".")) {
                equationText += ".";
                label_equationField.setText(equationText);
            }
        }
        if (Objects.equals(but.getText(), "AC")) {
            equationText = "0";
            label_equationField.setText(equationText);
        }
        if (Objects.equals(but.getText(), "+")) {
            equation1 = equationText;
            equationText = "";
            operator = "+";
            label_equationField.setText("+");
        }
        if (Objects.equals(but.getText(), "-")) {
            equation1 = equationText;
            equationText = "";
            operator = "-";
            label_equationField.setText("-");
        }
        if (Objects.equals(but.getText(), "*")) {
            equation1 = equationText;
            equationText = "";
            operator = "*";
            label_equationField.setText("*");
        }
        if (Objects.equals(but.getText(), "/")) {
            equation1 = equationText;
            equationText = "";
            operator = "/";
            label_equationField.setText("/");
        }

    }

    @FXML
    void button_equation_clicked() {
        equation2 = equationText;
        if (!equation1.isEmpty() && !equation2.isEmpty()) {
            if (Objects.equals(operator, "+")) {
                result = Double.parseDouble(equation1) + Double.parseDouble(equation2);
                label_equationField.setText(result.toString());
                equationText = result.toString();
            }
            if (Objects.equals(operator, "-")) {
                result = Double.parseDouble(equation1) - Double.parseDouble(equation2);
                label_equationField.setText(result.toString());
                equationText = result.toString();
            }
            if (Objects.equals(operator, "*")) {
                result = Double.parseDouble(equation1) * Double.parseDouble(equation2);
                label_equationField.setText(result.toString());
                equationText = result.toString();
            }
            if (Objects.equals(operator, "/")) {
                if (Objects.equals(equation2, "0")) {
                    label_equationField.setText("Ошибка");
                    equationText = "0";
                    System.out.println("Попытка деления на ноль!");
                } else {
                    result = Double.parseDouble(equation1) / Double.parseDouble(equation2);
                    label_equationField.setText(result.toString());
                    equationText = result.toString();
                }
            }
        }
        System.out.println("Воспользовались калькулятором");
    }

    @FXML
    void button_inverse_clicked() {
        if (!equationText.contains("-")) {
            equationText = "-" + equationText;
            label_equationField.setText(equationText);
        }else{
            equationText = equationText.substring(1);
            label_equationField.setText(equationText);
        }
    }

    @FXML
    void button_percent_clicked() {
        if (!equationText.isEmpty()) {
            double res = Double.parseDouble(equationText) / 100;
            equationText = String.valueOf(res);
            label_equationField.setText(equationText);
        }
    }

    @FXML
    void box_clicked(ActionEvent event) {
        if (event.getSource() == box_white) {
            colors.add("White");
            box_white.setDisable(true);
        } else if (event.getSource() == box_blue) {
            colors.add("Blue");
            box_blue.setDisable(true);
        } else if (event.getSource() == box_red) {
            colors.add("Red");
            box_red.setDisable(true);
        }
    }

    @FXML
    void button_draw_clicked() {
        System.out.println("Вы нарисовали флаг из цветов: " + colors);
        box_white.setSelected(false);
        box_blue.setSelected(false);
        box_red.setSelected(false);
        box_white.setDisable(false);
        box_blue.setDisable(false);
        box_red.setDisable(false);
        rectangle_top.setStyle("-fx-fill: #8d8d8d");
        rectangle_center.setStyle("-fx-fill: #8d8d8d");
        rectangle_bottom.setStyle("-fx-fill: #8d8d8d");
        if (colors.indexOf("White") == 0) {
            rectangle_top.setVisible(true);
            rectangle_top.setStyle("-fx-fill: white");
        }
        if (colors.indexOf("White") == 1) {
            rectangle_center.setVisible(true);
            rectangle_center.setStyle("-fx-fill: white");
        }
        if (colors.indexOf("White") == 2) {
            rectangle_bottom.setVisible(true);
            rectangle_bottom.setStyle("-fx-fill: white");
        }
        if (colors.indexOf("Blue") == 0) {
            rectangle_top.setVisible(true);
            rectangle_top.setStyle("-fx-fill: blue");
        }
        if (colors.indexOf("Blue") == 1) {
            rectangle_center.setVisible(true);
            rectangle_center.setStyle("-fx-fill: blue");
        }
        if (colors.indexOf("Blue") == 2) {
            rectangle_bottom.setVisible(true);
            rectangle_bottom.setStyle("-fx-fill: blue");
        }
        if (colors.indexOf("Red") == 0) {
            rectangle_top.setVisible(true);
            rectangle_top.setStyle("-fx-fill: red");
        }
        if (colors.indexOf("Red") == 1) {
            rectangle_center.setVisible(true);
            rectangle_center.setStyle("-fx-fill: red");
        }
        if (colors.indexOf("Red") == 2) {
            rectangle_bottom.setVisible(true);
            rectangle_bottom.setStyle("-fx-fill: red");
        }
        colors.clear();
    }

    @FXML
    void initialize() {}
}
