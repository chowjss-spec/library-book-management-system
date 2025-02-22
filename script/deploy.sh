#!/bin/bash

BUCKET_NAME="raw-data-test-1234"
APP_NAME="library-book-management-system-v2"
ENV_NAME="Library-book-management-system-2-env"
VERSION_LABEL="v$(date +%Y%m%d%H%M%S)"
JAR_PATH="../target/LibraryBookManagementSystem-0.0.1-SNAPSHOT.war"

# Upload JAR to S3
aws s3 cp $JAR_PATH s3://$BUCKET_NAME/ROOT.jar

# Create new application version
aws elasticbeanstalk create-application-version \
  --application-name $APP_NAME \
  --version-label $VERSION_LABEL \
  --source-bundle S3Bucket=$BUCKET_NAME,S3Key=ROOT.jar

# Deploy to Elastic Beanstalk environment
aws elasticbeanstalk update-environment \
  --environment-name $ENV_NAME \
  --version-label $VERSION_LABEL

echo "Deployment completed successfully!"