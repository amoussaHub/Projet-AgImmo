module Projet {
	 requires javafx.controls;
	    requires java.sql;
	    requires javafx.fxml;
	    requires org.apache.poi.ooxml;
	    requires javafx.graphics;
		requires javafx.base;
		requires javafx.web;
		requires passay;
		requires bcrypt;


	    opens controller to javafx.graphics, javafx.fxml;
	    exports controller;
	    
	
	    opens model to javafx.fxml, javafx.base;
	    exports model;
	    
}
