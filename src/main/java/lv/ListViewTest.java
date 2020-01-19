package lv;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListViewTest extends Application {

    /**
     * Informs the ListView that one of its items has been modified.
     *
     * @param listView The ListView to trigger.
     * @param newValue The new value of the list item that changed.
     * @param i The index of the list item that changed.
     */
    public static <T> void triggerUpdate(ListView<T> listView, T newValue, int i) {
        EventType<? extends ListView.EditEvent<T>> type = ListView.editCommitEvent();
        Event event = new ListView.EditEvent<>(listView, type, newValue, i);
        listView.fireEvent(event);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a list of mutable data.  StringBuffer works nicely.
        final List<StringBuffer> listData = Stream.of("Fee", "Fi", "Fo", "Fum")
                .map(StringBuffer::new)
                .collect(Collectors.toList());
        final ListView<StringBuffer> listView = new ListView<>();
        listView.getItems().addAll(listData);
        final StackPane root = new StackPane();
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // Modify an item in the list every 2 seconds.
        new Thread(() -> {
            IntStream.range(0, listData.size()).forEach(i -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(listData.get(i));
                Platform.runLater(() -> {
                    // Where the magic happens.
                    listData.get(i).append("!");
                    triggerUpdate(listView, listData.get(i), i);
                });
            });
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
