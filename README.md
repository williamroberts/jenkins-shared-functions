# Jenkins Shared Functions
A bunch of commands I use (or will use) in multiple Jenkinsfiles. Putting them into a shared library ensures that my Jenkinsfiles are DRY.


### To make user of these functions
To make these functions available to a Jenkinsfile, add the following statement as the first line:

```@Library('github.com/williamroberts/jenkins-shared-functions') _```
