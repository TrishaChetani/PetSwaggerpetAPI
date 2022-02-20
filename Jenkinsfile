

def testNode = 'node'
def testsBranch = params.TESTS_BRANCH
def gitRepo = 'https://github.com/TrishaChetani/petswaggerUserAPI.git'



//Pipeline
node(testNode) {
    properties([
            parameters([
                    choice(name: 'base_url', defaultValue: "https://petstore.swagger.io/v2"),
                    string(name: 'TESTS_BRANCH', defaultValue: 'master', description: 'Branch to pull from the Tests Repo'),
                    choice(name: 'propertiesFile', defaultValue: "dev.properties", description: 'Pass properties file', choices: ['dev.properties', 'sit.properties', 'uat.properties', 'prod.properties']),

            ])
    ])
    try {
        currentBuild.result = 'SUCCESS'
        triggers {
          //At every midnigth except weekend holidays
            cron('*/10 * * * *')
//         upstream(upstreamProjects: 'job1,job2', threshold: hudson.model.Result.SUCCESS)
          // check repo at every 5 minutes
           pollSCM('*/5 * * * *')

        }
        stage('Pulling Automation Code') {
            sh "git clone https://github.com/TrishaChetani/petswaggerUserAPI.git"
        }

        stage('Executing Test Scenarios') {
            try {
                err = sh " gradle clean test -DbaseURI=${params.url}"
            } catch (def e) {
                echo "Catch error ${e}"
                currentBuild.result = 'UNSTABLE'
            }
        }

        stage('Publishing Test Results') {
            report.cucumber()
            report.serenity()
        }
        }
     finally {
        if (err) {
            currentBuild.result = 'UNSTABLE'
        }
    }
}
