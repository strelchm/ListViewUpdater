package samples;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.ListCell;

/**
 * Created by Johannes on 23.05.16.
 *
 */

public class StudentListViewCell extends ListCell<Student> {

    private StudentCell mLLoader;

    @Override
    protected void updateItem(Student student, boolean empty) {
        super.updateItem(student, empty);


        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            System.out.println("update " + student.getStudentId());

            if (mLLoader == null) {
                mLLoader = new StudentCell();
            }

            mLLoader.setLabel1Text(String.valueOf(student.getStudentId()));
            mLLoader.setLabel2Text(student.getName());

            if(student.getGender().equals(Student.GENDER.MALE)) {
                setStyle("-fx-background-color: yellow");
//                fxIconGender.setIcon(FontAwesomeIcon.MARS);
            } else if(student.getGender().equals(Student.GENDER.FEMALE)) {
//                fxIconGender.setIcon(FontAwesomeIcon.VENUS);
                setStyle("-fx-background-color: grey");
            } else {
//                fxIconGender.setIcon(FontAwesomeIcon.GENDERLESS);
                setStyle("-fx-background-color: red");
            }

            setText(null);
            setGraphic(mLLoader);
        }

    }
}
