#!/bin/bash

if [[ -d "target" ]]; then
  java -jar target/AlgorithmModification-1.0-SNAPSHOT.jar
else
  echo "Unable to find execution directory (is the jar present?)"
  exit 1
fi
