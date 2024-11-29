#!/bin/bash

# Define the source and destination directories
SOURCE_DIR_MAIN="C:/Users/NOTEBOOK 15/Downloads/TaskManagementAPI/src/main/java/com/example/taskmanagement"
DEST_DIR_TEST="C:/Users/NOTEBOOK 15/Downloads/TaskManagementAPI/src/test/java/com/example/taskmanagement"

# Create the destination directory if it doesn't exist
mkdir -p "$DEST_DIR_TEST"

# Move the test files from src/main/java to src/test/java
mv "$SOURCE_DIR_MAIN/TaskServiceTest.java" "$DEST_DIR_TEST/"
mv "$SOURCE_DIR_MAIN/TaskControllerTest.java" "$DEST_DIR_TEST/"

echo "Test files moved successfully!"
