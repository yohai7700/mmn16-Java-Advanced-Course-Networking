package CitiesForecast.ClientSide;

import CitiesForecast.City;
import CitiesForecast.Forecast;

import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JPanel{
    private static final Color LABEL_COLOR = Color.LIGHT_GRAY;

    private final JComboBox<City> cityComboBox;
    private final JLabel yesterdayLabel, todayLabel, tomorrowLabel;

    public ClientPanel(){
        setLayout(new BorderLayout());
        cityComboBox = new JComboBox<>(City.values());
        yesterdayLabel = new JLabel();
        todayLabel = new JLabel();
        tomorrowLabel = new JLabel();
        setLabels();
        addComponents();
    }

    //letting user decide what to do when city is selected
    public void setCityListener(CityListener cityListener){
        cityComboBox.addActionListener(actionEvent -> cityListener.onCity((City)(cityComboBox.getSelectedItem())));
    }

    private void setLabels(){
        yesterdayLabel.setOpaque(true);
        yesterdayLabel.setBackground(LABEL_COLOR);
        todayLabel.setOpaque(true);
        todayLabel.setBackground(LABEL_COLOR);
        tomorrowLabel.setOpaque(true);
        tomorrowLabel.setBackground(LABEL_COLOR);
    }

    private void addComponents(){
        add(cityComboBox, BorderLayout.NORTH);
        JPanel forecastsPanel = new JPanel();
        forecastsPanel.setLayout(new BoxLayout(forecastsPanel, BoxLayout.PAGE_AXIS));//to have forecasts be vertical
        forecastsPanel.add(yesterdayLabel);
        forecastsPanel.add(todayLabel);
        forecastsPanel.add(tomorrowLabel);
        //adding forecasts to scroll pane to have horizontal scroll bar
        JScrollPane scrollPane = new JScrollPane(forecastsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setForecast(Forecast forecast){
        if(forecast == null) return;
        yesterdayLabel.setText("Yesterday: " + forecast.getYesterday());
        todayLabel.setText("Today: " + forecast.getToday());
        tomorrowLabel.setText("Tomorrow: " + forecast.getTomorrow());
    }
}
