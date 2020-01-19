/*
        Licensed to the Apache Software Foundation (ASF) under one
        or more contributor license agreements.  See the NOTICE file
        distributed with this work for additional information
        regarding copyright ownership.  The ASF licenses this file
        to you under the Apache License, Version 2.0 (the
        "License"); you may not use this file except in compliance
        with the License.  You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing,
        software distributed under the License is distributed on an
        "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
        KIND, either express or implied.  See the License for the
        specific language governing permissions and limitations
        under the License.
 */

package samples;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ListView<Student> listView;

    private ObservableList<Student> studentObservableList;

    public static <T> void triggerUpdate(ListView<T> listView, T newValue, int i) {
        System.out.println("*** " + listView + " *** " + newValue + " *** " + i);
        EventType<? extends ListView.EditEvent<T>> type = ListView.editCommitEvent();
        Event event = new ListView.EditEvent<>(listView, type, newValue, i);
        listView.fireEvent(event);
    }

    public Controller()  {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentObservableList = FXCollections.observableArrayList();

        Student s1 = new Student("John Doe", Student.GENDER.MALE);


        //add some Students
        studentObservableList.addAll(
                s1,
                new Student("Jane Doe", Student.GENDER.FEMALE),
                new Student("Donte Dunigan", Student.GENDER.MALE),
                new Student("Gavin Genna", Student.GENDER.MALE),
                new Student("Darin Dear", Student.GENDER.MALE),
                new Student("Pura Petty", Student.GENDER.FEMALE),
                new Student("Herma Hines", Student.GENDER.FEMALE)
        );


        listView.setItems(studentObservableList);
        listView.setCellFactory(studentListView -> new StudentListViewCell());


        new Thread(){
            @Override
            public void run() {
                super.run();

                while (true) {

                    Student.GENDER g = Student.GENDER.getRandomGender();
                    System.out.println("g ________ " + g);
                    s1.setGender(g);
                    triggerUpdate(listView,s1,0);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }

                }
            }
        }.start();

    }

}
