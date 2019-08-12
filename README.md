# CucumberExtentReports

Run the tests by using the below command

browser : chrome/firefox
os: mac/win

    ./gradlew clean test -Dbrowser=<browser> -Dos=<os>
    
    
Please note to change the drivers in the ./drivers/ folder for running tests against a specific version of browser

Extent reports have been added which are available under ./test-output/HtmlReport folder
