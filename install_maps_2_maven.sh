#!/bin/sh
mvn install:install-file \
  -Dfile=maps.jar \
  -DgroupId=com.google.android.maps \
  -DartifactId=maps \
  -Dversion=3_r3 \
  -DcreateChecksum=true \
  -DgeneratePom=true \
  -Dpackaging=jar
