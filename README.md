A JavaFX application that converts images to a single pdf.


##Packaging

###Shade
Uses the maven-shade-plugin to create fat jars:

        mvn compile package

###Jpackage
Creates a standalone application from the fat jar made by shade:

        jpackage --input shade/ \
        --name Images2PDF\
        --main-jar images2pdf-jfx.jar \
        --main-class com.images2pdf.Launcher\
        --type dmg \
        --app-version "1.2.3" \
        --vendor "Pooya" \
        --copyright "Copyright 2021 Pooya" \
        --mac-package-name "Images2PDF" \
        --verbose \
        --java-options '--enable-preview'