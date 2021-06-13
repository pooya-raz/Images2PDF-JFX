module com.images2pdf {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires itextpdf;

    opens com.images2pdf to javafx.fxml;
    exports com.images2pdf;
}