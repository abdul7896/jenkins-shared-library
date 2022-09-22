#!/usr/bin/env groovy

package com.example

class Docker implements Serializable {

        def script

        Docker(script){
            this.script = script
        }

        def buildDockerImage(String imageName){
            script.echo 'building the docker image'
            script.withCredentials([script.usernamePassword(
                    credentialsId: '71ef5aa5-53bc-40e9-bdf6-4cfd27c1aa7e',
                    passwordVariable: 'PASS',
                    usernameVariable: 'USER'
            )]) {
                script.sh "docker build -t $imageName ."
                script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
                script.sh "docker push $imageName"
            }
        }
    }
