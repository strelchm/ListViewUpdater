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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Johannes on 23.05.16.
 */
public class Student {

    private static int studentIdAct = 0;
    private int studentId;
    private StringProperty name;
    private ObjectProperty<GENDER> gender;

    enum GENDER {
        MALE,
        FEMALE;

        public static GENDER getRandomGender() {
            double rand = Math.random();
            if (rand < 0.5) {
                return MALE;
            } else {
                return FEMALE;
            }
        }
    }

    public Student(String name, GENDER gender) {
        studentId = studentIdAct++;
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleObjectProperty<>(gender);
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public GENDER getGender() {
        return gender.get();
    }

    public void setGender(GENDER gender) {
        this.gender.setValue(gender);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name=" + name +
                ", gender=" + gender +
                '}';
    }
}
