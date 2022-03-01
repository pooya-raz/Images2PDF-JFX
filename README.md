
#Images2PDF-JFX 

A JavaFX application that converts images to a single pdf.

## Getting Started

Photographers often need to share pictures via PDF. 
It is a tedious process to resize images and individually add them to a single PDF. 
Images2PDF saves time by resizing and collating the images into a single compressed PDF.

Simply download the appropriate binary and run it.

### Prerequisites

No prerequisites required. 

### Installing

No installation required.

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

## Built With

* [JavaFX](https://openjfx.io/) - A Java GUI library 
* [iText](https://itextpdf.com/en) - PDF library

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Christopher Pooya Razavian** - *Initial work* - [pooya-raz](https://github.com/pooya-raz)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License. 

